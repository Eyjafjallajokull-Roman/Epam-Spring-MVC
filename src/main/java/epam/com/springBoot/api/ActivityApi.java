package epam.com.springBoot.api;

import epam.com.springBoot.controller.model.ActivityModel;
import epam.com.springBoot.dto.ActivityDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Activity management API")
@RequestMapping("/api/v1/activities")
public interface ActivityApi {

    @ApiOperation("Create Activity")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ActivityModel createActivity(@RequestBody ActivityDTO dto);


    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Activity id"),
    })
    @ApiOperation("Update Activity")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ActivityModel updateActivity(@RequestBody ActivityDTO dto, @PathVariable Long id);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Activity id"),
    })
    @ApiOperation("Get Activity")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ActivityModel getActivity(@PathVariable Long id);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Activity id"),
    })
    @ApiOperation("Delete Activity")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteActivity(@PathVariable Long id);

}
