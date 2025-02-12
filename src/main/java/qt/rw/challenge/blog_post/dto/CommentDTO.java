package qt.rw.challenge.blog_post.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CommentDTO {
    private UUID id;

    @NotNull(message = "Content cannot be null")
    @NotEmpty(message = "Content cannot be empty")
    private String content;

    @NotNull(message = "Post ID cannot be null")
    private UUID postId;
}