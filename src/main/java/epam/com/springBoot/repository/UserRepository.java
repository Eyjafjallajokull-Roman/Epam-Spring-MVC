package epam.com.springBoot.repository;

import epam.com.springBoot.dto.UserDTO;
import epam.com.springBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByEmail (String email);

}
