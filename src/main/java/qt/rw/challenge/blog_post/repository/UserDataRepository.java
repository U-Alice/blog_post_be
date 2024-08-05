package qt.rw.challenge.blog_post.repository;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qt.rw.challenge.blog_post.models.UserData;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, UUID> {
    Optional<UserData> findByEmail(String email);

}
