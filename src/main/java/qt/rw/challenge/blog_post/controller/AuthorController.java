package qt.rw.challenge.blog_post.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qt.rw.challenge.blog_post.dto.UserRegistrationDTO;
import qt.rw.challenge.blog_post.payload.ApiResponse;
import qt.rw.challenge.blog_post.services.IAuthorService;
import qt.rw.challenge.blog_post.utils.ExceptionUtils;

import java.util.UUID;

//TODO: add logging
@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {
    private final IAuthorService authorService;

    public AuthorController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAll() {
        try {
            return ResponseEntity.ok(ApiResponse.success(authorService.findAll()));

        }catch(Exception e){
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(ApiResponse.success(authorService.findById(id)));

        }catch(Exception e){
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> save(@Valid @RequestBody UserRegistrationDTO dto) {

//        try {
            return ResponseEntity.ok(ApiResponse.success(authorService.save(dto)));
//
//        }catch(Exception e){
//            System.out.println(e);
//           return ExceptionUtils.handleControllerExceptions(e);
//        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable UUID id) {
        try {
            authorService.deleteById(id);
            return ResponseEntity.ok(ApiResponse.success("Deleted successfully!"));
        }catch(Exception e){
           return ExceptionUtils.handleControllerExceptions(e);
        }
    }
}
