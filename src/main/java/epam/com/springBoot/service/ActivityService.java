package epam.com.springBoot.service;

import epam.com.springBoot.controller.model.ActivityModel;
import epam.com.springBoot.dto.activity.ActivityAdminDTO;
import epam.com.springBoot.model.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

public interface ActivityService {
    PagedModel<ActivityModel> findAllActivities(Pageable pageable);

    ActivityAdminDTO createActivity(ActivityAdminDTO activityAdminDTO);

    ActivityAdminDTO updateActivity(ActivityAdminDTO activityAdminDTO, Long id);

    ActivityAdminDTO getById(Long id);

    PagedModel<ActivityModel> findActivitiesByTypeOfActivityAndStatus(String typeOfActivity, Status status, Pageable pageable);

    PagedModel<ActivityModel> findActivitiesByCreatedByUserEmailOrUserId(String email, String typeOfActivity,
                                                                         String status, Pageable pageable);

    PagedModel<ActivityModel> findActivitiesByCreatedByUserIdAndStatus(String email, Status status, Pageable pageable);

    void delete(Long id);

    void setOnDelete(Long id);

    void acceptActivity(Long activityId);

    void declineActivity(Long activityId);
}
