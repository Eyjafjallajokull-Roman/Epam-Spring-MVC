package epam.com.springBoot.service;

import epam.com.springBoot.dto.ActivityDTO;
import epam.com.springBoot.model.Activity;

import java.util.List;

public interface ActivityService {
    List<Activity> findAll();

    Activity findById(Long id);

    Activity save(ActivityDTO activityDTO);

    void delete(Long id);
}
