package epam.com.springBoot.service;

import epam.com.springBoot.controller.model.UserModel;
import epam.com.springBoot.dto.UserDTO;
import epam.com.springBoot.model.Activity;
import epam.com.springBoot.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.List;

public interface UserService {
    PagedModel<UserModel> findAll(Pageable pageable);

    UserDTO createUser(UserDTO userDTO);

    UserDTO getById(Long id);

    UserDTO update(UserDTO dto, String email);

    UserDTO getByEmail(String email);

    PagedModel<UserModel> findAllUsersByActivityId(Long activityId, Pageable pageable);

    void deleteByEmail(String email);

    void addUserToActivity(String email, Long activityId);

    void deleteUserFromActivity(String email, Long activityId);



}
