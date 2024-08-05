package rca.ac.year3.security_starter.serviceImpls;

import org.springframework.stereotype.Service;
import rca.ac.year3.security_starter.dto.CommentDTO;
import rca.ac.year3.security_starter.exceptions.ResourceNotFoundException;
import rca.ac.year3.security_starter.models.Author;
import rca.ac.year3.security_starter.models.Comment;
import rca.ac.year3.security_starter.repository.ICommentRepository;
import rca.ac.year3.security_starter.services.IAuthorService;
import rca.ac.year3.security_starter.services.ICommentService;
import rca.ac.year3.security_starter.utils.ExceptionUtils;
import rca.ac.year3.security_starter.utils.Mapper;

import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements ICommentService {

    private final ICommentRepository commentRepository;

    private final IAuthorService authorService;

    public CommentServiceImpl(ICommentRepository commentRepository, IAuthorService authorService) {
        this.commentRepository = commentRepository;
        this.authorService = authorService;
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
            Author author = authorService.findById(commentDTO.getAuthorId());
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