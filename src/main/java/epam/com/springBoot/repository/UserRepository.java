package epam.com.springBoot.repository;

import epam.com.springBoot.dto.UserDTO;
import epam.com.springBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByEmail(String email);

    void delete(User user);

    Optional<User> findByEmail(String email);
}
