package qt.rw.challenge.blog_post.serviceImpls;

import org.springframework.stereotype.Service;
import qt.rw.challenge.blog_post.dto.CommentDTO;
import qt.rw.challenge.blog_post.exceptions.ResourceNotFoundException;
import qt.rw.challenge.blog_post.models.Author;
import qt.rw.challenge.blog_post.models.Comment;
import qt.rw.challenge.blog_post.models.UserData;
import qt.rw.challenge.blog_post.repository.ICommentRepository;
import qt.rw.challenge.blog_post.services.IAuthorService;
import qt.rw.challenge.blog_post.services.ICommentService;
import qt.rw.challenge.blog_post.services.IUserService;
import qt.rw.challenge.blog_post.utils.ExceptionUtils;
import qt.rw.challenge.blog_post.utils.Mapper;

import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements ICommentService {

    private final ICommentRepository commentRepository;

    private final IAuthorService authorService;

    private final IUserService userService;
    public CommentServiceImpl(ICommentRepository commentRepository, IAuthorService authorService, IUserService userService) {
        this.commentRepository = commentRepository;
        this.authorService = authorService;
        this.userService = userService;
    }

    @Override
    public List<Comment> findAll() {
        try {
            return commentRepository.findAll();
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public Comment findById(UUID id) {
        try {
            return commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public List<Comment> findByAuthor(UUID id) {
        try {
            Author author = authorService.findById(id);
            return commentRepository.findByAuthor(author);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public Comment create(CommentDTO postCreateDTO) {
        Comment comment = Mapper.getCommentfromDTO(postCreateDTO);
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(UUID id, CommentDTO commentDTO) {
        Comment existingComment = commentRepository.findById(id).orElse(null);
        if (existingComment != null) {
            existingComment.setContent(commentDTO.getContent());
            UserData userData = userService.getLoggedInUser();
            Author author = authorService.findById(userData.getId());
            existingComment.setAuthor(author);
            return commentRepository.save(existingComment);
        }
        return null;
    }

    @Override
    public void delete(UUID id) {
        commentRepository.deleteById(id);
    }
}