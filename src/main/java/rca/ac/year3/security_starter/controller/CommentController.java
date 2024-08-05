package rca.ac.year3.security_starter.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rca.ac.year3.security_starter.dto.CommentDTO;
import rca.ac.year3.security_starter.models.Comment;
import rca.ac.year3.security_starter.payload.ApiResponse;
import rca.ac.year3.security_starter.services.ICommentService;
import rca.ac.year3.security_starter.services.IPostService;
import rca.ac.year3.security_starter.utils.ExceptionUtils;

import java.util.UUID;

//TODO: add logging
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final ICommentService commentService;

    public CommentController( ICommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping
    public ResponseEntity<ApiResponse> findAll() {
        try {
            return ResponseEntity.ok(ApiResponse.success(commentService.findAll()));

        }catch(Exception e){
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(ApiResponse.success(commentService.findById(id)));

        }catch(Exception e){
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }
    @GetMapping("/{authorId}")
    public ResponseEntity<ApiResponse> findByAuthor(@PathVariable("authorId") UUID id) {
        try {
            return ResponseEntity.ok(ApiResponse.success(commentService.findByAuthor(id)));

        }catch(Exception e){
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody CommentDTO commentCreateDTO) {
        try {
            Comment createdComment = commentService.create(commentCreateDTO);
            return ResponseEntity.ok(ApiResponse.success(createdComment));
        } catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable UUID id, @Valid @RequestBody CommentDTO commentDTO) {
        try {
            Comment updatedComment = commentService.update(id, commentDTO);
            return ResponseEntity.ok(ApiResponse.success(updatedComment));
        } catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable UUID id) {
        try {
            commentService.delete(id);
            return ResponseEntity.ok(ApiResponse.success("Comment deleted successfully."));
        } catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }



}