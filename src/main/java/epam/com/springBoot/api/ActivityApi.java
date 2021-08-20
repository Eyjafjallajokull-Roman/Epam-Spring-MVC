package epam.com.springBoot.api;

import epam.com.springBoot.controller.model.ActivityModel;
import epam.com.springBoot.dto.activity.ActivityAdminDTO;
import epam.com.springBoot.dto.group.OnCreate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Activity management API")
@RequestMapping("/api/v1/activities")
public interface ActivityApi {

    @ApiOperation("Create Activity")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ActivityModel createActivity(@Valid @Validated(OnCreate.class) @RequestBody ActivityAdminDTO dto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Activity id"),
    })
    @ApiOperation("Update Activity")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ActivityModel updateActivity(@RequestBody ActivityAdminDTO dto, @PathVariable Long id);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Activity id"),
    })
    @ApiOperation("Get Activity")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ActivityModel getActivity(@PathVariable Long id);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", paramType = "path", required = true, value = "Activity id"),
    })
    @ApiOperation("Set Activity on Delete")
    @GetMapping("/{activityId}/setOnDelete")
    ResponseEntity<Void> setOnDelete(@PathVariable Long activityId);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", paramType = "path", required = true, value = "Activity id"),
    })
    @ApiOperation("Set End Time for TimeTracker")
    @GetMapping("{activityId}/end_time")
    ResponseEntity<Void> setEndTimeForTimeTracker(@PathVariable Long activityId);

}
