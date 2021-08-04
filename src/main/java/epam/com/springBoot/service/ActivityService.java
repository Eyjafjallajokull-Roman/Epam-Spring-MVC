package epam.com.springBoot.service;

import epam.com.springBoot.dto.ActivityDTO;
import epam.com.springBoot.model.Activity;

import java.util.List;

public interface ActivityService {
    List<Activity> findAll();

    ActivityDTO createActivity(ActivityDTO activityDTO);

    ActivityDTO updateActivity(ActivityDTO activityDTO, Long id);

    ActivityDTO getById(Long id);

    void delete(Long id);
}
