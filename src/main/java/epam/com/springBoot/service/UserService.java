package epam.com.springBoot.service;

import epam.com.springBoot.controller.model.UserActivitiesModel;
import epam.com.springBoot.dto.user.UserActivitiesDTO;
import epam.com.springBoot.dto.user.UserDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

public interface UserService {
    PagedModel<UserActivitiesModel> findAll(Pageable pageable);

    UserDTO createUser(UserDTO userDTO);


    UserDTO update(UserDTO dto, String email);

    UserDTO getByEmail(String email);

    PagedModel<UserActivitiesModel> findAllUsersByActivityId(Long activityId, Pageable pageable);

    UserActivitiesDTO getByEmailUADTO(String email);

    void deleteByEmail(String email);

    void addUserToActivity(String email, Long activityId);

    void deleteUserFromActivity(String email, Long activityId);


}
