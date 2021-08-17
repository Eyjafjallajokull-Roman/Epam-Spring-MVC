package epam.com.springBoot.controller;

import epam.com.springBoot.api.ActivityApi;
import epam.com.springBoot.controller.assembler.ActivityAssembler;
import epam.com.springBoot.controller.model.ActivityModel;
import epam.com.springBoot.dto.activity.ActivityAdminDTO;
import epam.com.springBoot.dto.group.OnCreate;
import epam.com.springBoot.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ActivityController implements ActivityApi {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityAssembler activityAssembler;


    @Override
    public ActivityModel createActivity(@Valid @Validated(OnCreate.class) @RequestBody ActivityAdminDTO dto) {
        return activityAssembler.toModel(activityService.createActivity(dto));
    }
    
    @Override
    public ActivityModel updateActivity(@RequestBody ActivityAdminDTO dto, @PathVariable Long id) {
        return activityAssembler.toModel(activityService.updateActivity(dto, id));
    }

    @Override
    public ActivityModel getActivity(@PathVariable Long id) {
        return activityAssembler.toModel(activityService.getById(id));
    }

    @Override
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activityService.delete(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> setOnDelete(@PathVariable Long activityId) {
        activityService.setOnDelete(activityId);
        return ResponseEntity.noContent().build();
    }


}
