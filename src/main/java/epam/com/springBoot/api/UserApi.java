package epam.com.springBoot.api;

import epam.com.springBoot.controller.model.ActivityModel;
import epam.com.springBoot.controller.model.UserModel;
import epam.com.springBoot.dto.UserDTO;
import epam.com.springBoot.dto.group.OnCreate;
import epam.com.springBoot.dto.group.OnUpdate;
import epam.com.springBoot.model.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "User management API")
@RequestMapping("/api/v1/users")
public interface UserApi {

    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "User email"),
    })
    @ApiOperation("Get User")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{email}")
    UserModel getUserByEmail(@PathVariable String email);

    @ApiOperation("Create User")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    UserModel createUser(@Valid @RequestBody @Validated(OnCreate.class) UserDTO dto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "User email"),
    })
    @ApiOperation("Update User")
    @PatchMapping("/{email}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    UserModel update(@RequestBody @Validated(OnUpdate.class) UserDTO dto, @PathVariable String email);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "User email"),
            @ApiImplicitParam(name = "status", paramType = "query", required = true, value = "Activity Status")
    })
    @ApiOperation("Find all activity created by this User")
    @GetMapping("/my-activities/{email}")
    @ResponseStatus(HttpStatus.OK)
    PagedModel<ActivityModel> findActivitiesByCreatedByUserIdAndStatus(@PathVariable String email, Status status, Pageable pageable);


    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "User email"),
    })
    @ApiOperation("Find all activity created by this User with Status Accept")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    PagedModel<ActivityModel> findActivitiesByCreatedByUserIdAndStatusMainPage(String email, Pageable pageable);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Activity id"),
    })
    @ApiOperation("Set activity status delete")
    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> setOnDelete(@PathVariable Long id);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", paramType = "query", required = true, value = "User email"),
            @ApiImplicitParam(name = "activityId", paramType = "query", required = true, value = "Activity Id")
    })
    @ApiOperation("Add User to Activity")
    @PostMapping("/addUserToActivity")
    ResponseEntity<Void> addUserToActivity(String email, Long activityId);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", paramType = "query", required = true, value = "User email"),
            @ApiImplicitParam(name = "activityId", paramType = "query", required = true, value = "Activity Id")
    })
    @ApiOperation("Delete User from Activity")
    @DeleteMapping("/deleteUserToActivity")
    ResponseEntity<Void> deleteUserFromActivity(String email, Long activityId);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "Activity Id"),
    })
    @ApiOperation("Delete user")
    @GetMapping("/delete/{email}")
        //todo to delete self account not others
    ResponseEntity<Void> delete(@PathVariable String email);

}
