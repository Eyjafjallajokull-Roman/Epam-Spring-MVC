package epam.com.springBoot.controller;

import epam.com.springBoot.controller.assembler.ActivityAssembler;
import epam.com.springBoot.controller.model.ActivityModel;
import epam.com.springBoot.dto.ActivityDTO;
import epam.com.springBoot.model.Activity;
import epam.com.springBoot.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityAssembler activityAssembler;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActivityModel createActivity(@RequestBody ActivityDTO dto) {
        return activityAssembler.toModel(activityService.createActivity(dto));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ActivityModel updateActivity(@RequestBody ActivityDTO dto, @PathVariable Long id) {
        return activityAssembler.toModel(activityService.updateActivity(dto, id));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ActivityModel getActivity(@PathVariable Long id) {
        return activityAssembler.toModel(activityService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activityService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Activity>> getActivities() {
        return new ResponseEntity<>(activityService.findAll(), HttpStatus.ACCEPTED);
    }

}
