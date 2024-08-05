package qt.rw.challenge.blog_post.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qt.rw.challenge.blog_post.dto.CommentDTO;
import qt.rw.challenge.blog_post.dto.PostDTO;
import qt.rw.challenge.blog_post.payload.ApiResponse;
import qt.rw.challenge.blog_post.services.IPostService;
import qt.rw.challenge.blog_post.utils.ExceptionUtils;

import java.util.UUID;

//TODO: add logging
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final IPostService postService;

    public PostController(IPostService accountService, IPostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody PostDTO post) {
        try {
            return ResponseEntity.ok(ApiResponse.success(postService.create(post)));
        } catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable UUID id, @Valid @RequestBody PostDTO post) {
        try {
            return ResponseEntity.ok(ApiResponse.success(postService.update(id, post)));
        } catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable UUID id) {
        try {
            postService.delete(id);
            return ResponseEntity.ok(ApiResponse.success("Post deleted successfully."));
        } catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAll() {
        try {
            return ResponseEntity.ok(ApiResponse.success(postService.findAll()));

        }catch(Exception e){
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(ApiResponse.success(postService.findById(id)));

        }catch(Exception e){
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<ApiResponse> findByCustomer(@PathVariable("customerId") UUID id) {
        try {
            return ResponseEntity.ok(ApiResponse.success(postService.findById(id)));

        }catch(Exception e){
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<ApiResponse> addCommentToPost(@PathVariable UUID postId, @Valid @RequestBody String comment) {
        try {
            return ResponseEntity.ok(ApiResponse.success(postService.addCommentToPost(postId, comment)));
        } catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<ApiResponse> getCommentsByPost(@PathVariable UUID postId) {
        try {
            return ResponseEntity.ok(ApiResponse.success(postService.getCommentsByPost(postId)));
        } catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }



}