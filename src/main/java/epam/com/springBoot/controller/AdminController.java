package epam.com.springBoot.controller;

import epam.com.springBoot.controller.assembler.UserAssembler;
import epam.com.springBoot.controller.model.ActivityModel;
import epam.com.springBoot.controller.model.UserModel;
import epam.com.springBoot.model.Status;
import epam.com.springBoot.service.ActivityService;
import epam.com.springBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserAssembler userAssembler;


    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PagedModel<UserModel> getAllUsers(Pageable pageable) {
        return userService.findAll(pageable);
    }

    @GetMapping
    @RequestMapping("/activities")
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<ActivityModel> findActivitiesByTypeOfActivityAndStatus(String typeOfActivity, Pageable pageable) {
        return activityService.findActivitiesByTypeOfActivityAndStatus(typeOfActivity, Status.ON_CHECK, pageable);
    }

    @GetMapping
    @RequestMapping("/activities/{email}")
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<ActivityModel> findActivitiesByUser(@PathVariable String email, String typeOfActivity, String status, Pageable pageable) {
        return activityService.findActivitiesByCreatedByUserEmailOrUserId(email, typeOfActivity, status, pageable);
    }

    @GetMapping
    @RequestMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<UserModel> findAllUsersByActivityId(@PathVariable Long id, Pageable pageable){
        return userService.findAllUsersByActivityId(id, pageable);
    }


}
