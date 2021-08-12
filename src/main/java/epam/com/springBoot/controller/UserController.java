package epam.com.springBoot.controller;

import epam.com.springBoot.api.UserApi;
import epam.com.springBoot.controller.assembler.UserAssembler;
import epam.com.springBoot.controller.model.ActivityModel;
import epam.com.springBoot.controller.model.UserModel;
import epam.com.springBoot.dto.user.UserDTO;
import epam.com.springBoot.dto.group.OnCreate;
import epam.com.springBoot.dto.group.OnUpdate;
import epam.com.springBoot.model.Status;
import epam.com.springBoot.service.ActivityService;
import epam.com.springBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class UserController implements UserApi {
    @Autowired
    private UserService userService;
    @Autowired
    private UserAssembler userAssembler;
    @Autowired
    private ActivityService activityService;

    @Override
    public UserModel createUser(@RequestBody @Validated(OnCreate.class) UserDTO dto) {
        return userAssembler.toModel(userService.createUser(dto));
    }

    @Override
    public UserModel update(@RequestBody @Validated(OnUpdate.class) UserDTO dto, @PathVariable String email) {
        return userAssembler.toModel(userService.update(dto, email));
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable String email) {
        userService.deleteByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @Override
    public UserModel getUserByEmail(@PathVariable String email) {
        return userAssembler.toModel(userService.getByEmail(email));
    }


    //todo mapping
    @Override
    public ResponseEntity<Void> addUserToActivity(String email, Long activityId) {
        userService.addUserToActivity(email, activityId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteUserFromActivity(String email, Long activityId) {
        userService.deleteUserFromActivity(email, activityId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> setOnDelete(Long id) {
        activityService.setOnDelete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public PagedModel<ActivityModel> findActivitiesByCreatedByUserIdAndStatusMainPage(String email, Pageable pageable) {
        return activityService.findActivitiesByCreatedByUserIdAndStatus(email, Status.ACCEPT, pageable);
    }

    @Override
    public PagedModel<ActivityModel> findActivitiesByCreatedByUserIdAndStatus(@PathVariable String email, Status status, Pageable pageable) {
        return activityService.findActivitiesByCreatedByUserIdAndStatus(email, status, pageable);
    }


}
