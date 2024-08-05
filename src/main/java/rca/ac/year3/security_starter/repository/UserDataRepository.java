package rca.ac.year3.security_starter.repository;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rca.ac.year3.security_starter.models.UserData;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, UUID> {
    Optional<UserData> findByEmail(String email);

}
