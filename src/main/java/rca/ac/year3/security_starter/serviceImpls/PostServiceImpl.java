package rca.ac.year3.security_starter.serviceImpls;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import rca.ac.year3.security_starter.dto.PostDTO;
import rca.ac.year3.security_starter.exceptions.ResourceNotFoundException;
import rca.ac.year3.security_starter.models.Author;
import rca.ac.year3.security_starter.models.Comment;
import rca.ac.year3.security_starter.models.Post;
import rca.ac.year3.security_starter.models.UserData;
import rca.ac.year3.security_starter.repository.IPostRepository;
import rca.ac.year3.security_starter.services.IAuthorService;
import rca.ac.year3.security_starter.services.IPostService;
import rca.ac.year3.security_starter.services.IUserService;
import rca.ac.year3.security_starter.utils.ExceptionUtils;
import rca.ac.year3.security_starter.utils.Mapper;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements IPostService {

    private final IPostRepository postRepository;

    private final IAuthorService authorService;
    private final IUserService userService;

    public PostServiceImpl(IPostRepository postRepository, IAuthorService authorService, IUserService userService) {
        this.postRepository = postRepository;
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



}
