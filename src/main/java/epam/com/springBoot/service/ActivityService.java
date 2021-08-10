package epam.com.springBoot.service;

import epam.com.springBoot.controller.model.ActivityModel;
import epam.com.springBoot.dto.ActivityDTO;
import epam.com.springBoot.model.Activity;
import epam.com.springBoot.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.List;

public interface ActivityService {
    PagedModel<ActivityModel> findAllActivities(Pageable pageable);

    ActivityDTO createActivity(ActivityDTO activityDTO);

    ActivityDTO updateActivity(ActivityDTO activityDTO, Long id);

    ActivityDTO getById(Long id);

    PagedModel<ActivityModel> findActivitiesByTypeOfActivityAndStatus(String typeOfActivity, Status status, Pageable pageable);

    PagedModel<ActivityModel> findActivitiesByCreatedByUserEmailOrUserId(String email, String typeOfActivity,
                                                                         String status, Pageable pageable);

    PagedModel<ActivityModel> findActivitiesByCreatedByUserIdAndStatus(String email, Status status, Pageable pageable);

    void delete(Long id);

    void setOnDelete(Long id);

    void acceptActivity(Long activityId);

    void declineActivity(Long activityId);
}
