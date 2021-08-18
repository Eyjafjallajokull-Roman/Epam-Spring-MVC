package epam.com.springBoot.service;

import epam.com.springBoot.controller.model.ActivityAdminUsersModel;
import epam.com.springBoot.controller.model.ActivityModel;
import epam.com.springBoot.dto.activity.ActivityAdminDTO;
import epam.com.springBoot.dto.activity.ActivityAdminUsersDTO;
import epam.com.springBoot.model.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

public interface ActivityService {
    PagedModel<ActivityAdminUsersModel> findAllActivities(Pageable pageable);

    ActivityAdminDTO createActivity(ActivityAdminDTO activityAdminDTO);

    ActivityAdminDTO updateActivity(ActivityAdminDTO activityAdminDTO, Long id);

    ActivityAdminDTO getById(Long id);

    PagedModel<ActivityAdminUsersModel> findActivitiesByTypeOfActivityAndStatus(String typeOfActivity, Status status, Pageable pageable);

    PagedModel<ActivityAdminUsersModel> findActivitiesByCreatedByUserEmailOrUserId(String email, String typeOfActivity,
                                                                         String status, Pageable pageable);

    PagedModel<ActivityAdminUsersModel> findActivitiesByCreatedByUserIdAndStatusAU(String email, Status status, Pageable pageable);

    PagedModel<ActivityModel> findActivitiesByCreatedByUserIdAndStatus(String email, Status status, Pageable pageable);

    ActivityAdminUsersDTO getByIdAU(Long id);

    void setEndTimeForTimeTracker(Long activityId);

    void delete(Long id);

    void setOnDelete(Long id);

    void acceptActivity(Long activityId);

    void declineActivity(Long activityId);
}
