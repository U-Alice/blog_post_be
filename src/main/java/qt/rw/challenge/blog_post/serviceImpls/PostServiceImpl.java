package qt.rw.challenge.blog_post.serviceImpls;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import qt.rw.challenge.blog_post.dto.CommentDTO;
import qt.rw.challenge.blog_post.dto.PostDTO;
import qt.rw.challenge.blog_post.exceptions.ResourceNotFoundException;
import qt.rw.challenge.blog_post.models.Author;
import qt.rw.challenge.blog_post.models.Comment;
import qt.rw.challenge.blog_post.models.Post;
import qt.rw.challenge.blog_post.models.UserData;
import qt.rw.challenge.blog_post.repository.ICommentRepository;
import qt.rw.challenge.blog_post.repository.IPostRepository;
import qt.rw.challenge.blog_post.services.IAuthorService;
import qt.rw.challenge.blog_post.services.IPostService;
import qt.rw.challenge.blog_post.services.IUserService;
import qt.rw.challenge.blog_post.utils.ExceptionUtils;
import qt.rw.challenge.blog_post.utils.Mapper;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements IPostService {

    private final IPostRepository postRepository;
    private final ICommentRepository commentRepository;
    private final IAuthorService authorService;
    private final IUserService userService;

    public PostServiceImpl(IPostRepository postRepository, ICommentRepository commentRepository, IAuthorService authorService, IUserService userService) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.authorService = authorService;
        this.userService = userService;
    }

    @Override
    public List<Post> findAll() {
        try {
            return postRepository.findAll();
        }catch(Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public Post findById(UUID id) {
        try {
            return postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
        }catch(Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public List<Post> findByAuthor(UUID id) {
        try {
            Author author =  authorService.findById(id);
            return postRepository.findByAuthor(author);
        }catch(Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }
    @Override
    public Post create(PostDTO postCreateDTO) {
        UserData user = userService.getLoggedInUser();
        Post post = Mapper.getPostFromDTO(postCreateDTO);
        Author author = authorService.findById(user.getId());
        post.setAuthor(author);
        return postRepository.save(post);
    }

    @Override
    public Post update(UUID id, PostDTO postDTO) {
        Post existingPost = postRepository.findById(id).orElse(null);

        if (existingPost != null) {
            existingPost.setContent(postDTO.getContent());
            existingPost.setTitle(postDTO.getTitle());
            UserData user = userService.getLoggedInUser();
            Author author = authorService.findById(user.getId());            existingPost.setAuthor(author  );
            return postRepository.save(existingPost);
        }
        return null;
    }

    @Override
    public void delete(UUID id) {
        postRepository.deleteById(id);
    }


    @Override
    public Comment addCommentToPost(UUID postId, String text) {
        try {
            Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
            Comment comment = new Comment();
            UserData user = userService.getLoggedInUser();
            Author author =authorService.findById(user.getId());
            comment.setAuthor(author);
            comment.setPost(post);
            comment.setContent(text);
            return commentRepository.save(comment);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public List<Comment> getCommentsByPost(UUID postId) {
        try {
            Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
            return commentRepository.findByPost(post);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

}
