package epam.com.springBoot.service;

import epam.com.springBoot.dto.UserDTO;
import epam.com.springBoot.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    UserDTO createUser(UserDTO userDTO);

    UserDTO getById(Long id);

    UserDTO update(UserDTO dto, String email);

    UserDTO getByEmail(String email);

    void deleteByEmail(String email);
}
