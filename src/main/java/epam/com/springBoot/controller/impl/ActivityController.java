package epam.com.springBoot.controller.impl;

import epam.com.springBoot.dto.ActivityDTO;
import epam.com.springBoot.model.Activity;
import epam.com.springBoot.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Activity save(@RequestBody ActivityDTO dto) {
        return activityService.save(dto);
    }

}