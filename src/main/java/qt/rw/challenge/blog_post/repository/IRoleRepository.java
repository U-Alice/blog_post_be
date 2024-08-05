package qt.rw.challenge.blog_post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qt.rw.challenge.blog_post.enums.Erole;
import qt.rw.challenge.blog_post.models.Role;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface IRoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(Erole role);
}