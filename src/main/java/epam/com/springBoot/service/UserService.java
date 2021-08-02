package epam.com.springBoot.service;

import epam.com.springBoot.dto.UserDTO;
import epam.com.springBoot.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User save(UserDTO userDTO);

    User findById(Long id);

    User update(UserDTO dto);

    void delete(Long id);
}
