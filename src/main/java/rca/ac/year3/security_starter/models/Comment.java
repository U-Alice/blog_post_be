package rca.ac.year3.security_starter.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import rca.ac.year3.security_starter.audits.InitiatorAudit;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends InitiatorAudit {
    @Id
    @GeneratedValue
    private UUID id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
