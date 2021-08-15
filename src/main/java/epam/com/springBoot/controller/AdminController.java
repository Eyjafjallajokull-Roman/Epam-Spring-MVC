package epam.com.springBoot.controller;

import epam.com.springBoot.api.AdminApi;
import epam.com.springBoot.controller.assembler.ActivityAssembler;
import epam.com.springBoot.controller.assembler.ActivityUsersAssembler;
import epam.com.springBoot.controller.assembler.UserActivitiesAssembler;
import epam.com.springBoot.controller.model.ActivityAdminUsersModel;
import epam.com.springBoot.controller.model.ActivityModel;
import epam.com.springBoot.controller.model.UserActivitiesModel;
import epam.com.springBoot.model.Status;
import epam.com.springBoot.service.ActivityService;
import epam.com.springBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController implements AdminApi {
    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    ActivityAssembler activityAssembler;

    @Autowired
    ActivityUsersAssembler activityUsersAssembler;

    @Autowired
    UserActivitiesAssembler userActivitiesAssembler;


    @Override
    public PagedModel<UserActivitiesModel> getAllUsers(Pageable pageable) {
        return userService.findAll(pageable);
    }

    @Override
    public UserActivitiesModel findUser(String email) {
        return userActivitiesAssembler.toModel(userService.getByEmailUADTO(email));
    }

    @Override
    public PagedModel<ActivityAdminUsersModel> findAllActivities(Pageable pageable) {
        return activityService.findAllActivities(pageable);
    }

    @Override
    public ActivityAdminUsersModel findActivity(Long id) {
        return activityUsersAssembler.toModel(activityService.getByIdAU(id));
    }

    @Override
    public PagedModel<ActivityAdminUsersModel> findActivitiesByTypeOfActivityAndStatus(String typeOfActivity, Pageable pageable) {
        return activityService.findActivitiesByTypeOfActivityAndStatus(typeOfActivity, Status.ON_CHECK, pageable);
    }

    @Override
    public PagedModel<ActivityAdminUsersModel> findActivitiesByUser(@PathVariable String email, String typeOfActivity, String status, Pageable pageable) {
        return activityService.findActivitiesByCreatedByUserEmailOrUserId(email, typeOfActivity, status, pageable);
    }

    @Override
    public PagedModel<UserActivitiesModel> findAllUsersByActivityId(@PathVariable Long id, Pageable pageable) {
        return userService.findAllUsersByActivityId(id, pageable);
    }

    @Override
    public ResponseEntity<Void> declineActivity(@PathVariable Long activityId) {
        activityService.declineActivity(activityId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> acceptActivity(@PathVariable Long activityId) {
        activityService.acceptActivity(activityId);
        return ResponseEntity.noContent().build();
    }


}
