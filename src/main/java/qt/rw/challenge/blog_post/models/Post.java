package qt.rw.challenge.blog_post.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import qt.rw.challenge.blog_post.audits.InitiatorAudit;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "posts")
public class Post extends InitiatorAudit {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;


}
