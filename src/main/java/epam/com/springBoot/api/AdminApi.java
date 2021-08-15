package epam.com.springBoot.api;

import epam.com.springBoot.controller.model.ActivityAdminUsersModel;
import epam.com.springBoot.controller.model.ActivityModel;
import epam.com.springBoot.controller.model.UserActivitiesModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api(tags = "Admin management API")
@RequestMapping("/api/v1/admin")
public interface AdminApi {

    @GetMapping("/all-activities")
    @ResponseStatus(HttpStatus.OK)
    PagedModel<ActivityAdminUsersModel> findAllActivities(Pageable pageable);

    @GetMapping("/all-activities/{id}")
    @ResponseStatus(HttpStatus.OK)
    ActivityAdminUsersModel findActivity(@PathVariable Long id);

    @ApiOperation("Get all Users with pagination")
    @GetMapping("/all-users")
    @ResponseStatus(HttpStatus.ACCEPTED)
    PagedModel<UserActivitiesModel> getAllUsers(Pageable pageable);

    @GetMapping("/all-users/{email}")
    @ResponseStatus(HttpStatus.OK)
    UserActivitiesModel findUser(@PathVariable String email);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeOfActivity", paramType = "query", required = true, value = "Activity TypeOfActivity"),
    })
    @ApiOperation("Find all activity by type of activity and status")
    @GetMapping("/activities")
    @ResponseStatus(HttpStatus.OK)
    PagedModel<ActivityAdminUsersModel> findActivitiesByTypeOfActivityAndStatus(String typeOfActivity, Pageable pageable);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "User Email"),
            @ApiImplicitParam(name = "typeOfActivity", paramType = "query", required = true, value = "Activity TypeOfActivity"),
            @ApiImplicitParam(name = "status", paramType = "query", required = true, value = "Activity Status")
    })
    @ApiOperation("Find all Activities by User with pagination, With Status and TypeOfActivityParam")
    @GetMapping("/activities/{email}")
    @ResponseStatus(HttpStatus.OK)
    PagedModel<ActivityAdminUsersModel> findActivitiesByUser(@PathVariable String email, String typeOfActivity, String status, Pageable pageable);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", paramType = "path", required = true, value = "Activity Id"),
    })
    @ApiOperation("Find all Users by Activity with pagination")
    @GetMapping("/users/{activityId}")
    @ResponseStatus(HttpStatus.OK)
    PagedModel<UserActivitiesModel> findAllUsersByActivityId(@PathVariable Long activityId, Pageable pageable);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", paramType = "path", required = true, value = "Activity Id"),
    })
    @ApiOperation("Set Activity status 'ACCEPT' ")
    @GetMapping("/activities/accept/{activityId}")
    ResponseEntity<Void> acceptActivity(@PathVariable Long activityId);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", paramType = "path", required = true, value = "Activity Id"),
    })
    @ApiOperation("Set Activity status 'DECLINE' ")
    @GetMapping("/activities/decline/{activityId}")
    ResponseEntity<Void> declineActivity(@PathVariable Long activityId);


}
