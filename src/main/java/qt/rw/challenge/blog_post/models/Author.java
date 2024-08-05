package qt.rw.challenge.blog_post.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "id")
public class Author extends UserData{

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Post> posts;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Comment> comments;

}
