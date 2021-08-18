package epam.com.springBoot.service.impl;

import epam.com.springBoot.controller.assembler.ActivityAssembler;
import epam.com.springBoot.controller.assembler.ActivityUsersAssembler;
import epam.com.springBoot.controller.model.ActivityAdminUsersModel;
import epam.com.springBoot.controller.model.ActivityModel;
import epam.com.springBoot.dto.activity.ActivityAdminDTO;
import epam.com.springBoot.dto.activity.ActivityAdminUsersDTO;
import epam.com.springBoot.exceptions.ActivityNotFoundException;
import epam.com.springBoot.exceptions.UserNotFoundException;
import epam.com.springBoot.model.Activity;
import epam.com.springBoot.model.Status;
import epam.com.springBoot.model.TypeOfActivity;
import epam.com.springBoot.repository.ActivityRepository;
import epam.com.springBoot.repository.UserRepository;
import epam.com.springBoot.service.ActivityService;
import epam.com.springBoot.service.MappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;

@Slf4j
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private MappingService mappingService;

    @Autowired
    private ActivityAssembler activityAssembler;

    @Autowired
    private ActivityUsersAssembler activityUsersAssembler;

    @Autowired
    private PagedResourcesAssembler<ActivityAdminUsersDTO> activityUsersAssemblerPaged;

    @Autowired
    private PagedResourcesAssembler<ActivityAdminDTO> pagedResourcesAssembler;


    @Override
    public PagedModel<ActivityAdminUsersModel> findAllActivities(Pageable pageable) {
        Page<Activity> page = activityRepository.findAll(pageable);
        Page<ActivityAdminUsersDTO> activityDTOS = page.map(activity -> conversionService.convert(activity, ActivityAdminUsersDTO.class));
        return activityUsersAssemblerPaged.toModel(activityDTOS, activityUsersAssembler);
    }


    @Override
    public ActivityAdminDTO createActivity(ActivityAdminDTO activityAdminDTO) {
        log.info("Try to create Activity");
        Activity activity = conversionService.convert(activityAdminDTO, Activity.class);
        (activity).setStatus(Status.ON_CHECK);
        activity = activityRepository.save(activity);
        log.info("Activity was created");
        return conversionService.convert(activity, ActivityAdminDTO.class);
    }

    @Override
    public ActivityAdminDTO updateActivity(ActivityAdminDTO activityAdminDTO, Long id) {
        log.info("Try to update Activity by id: " + id);
        Activity activityToUpdate = activityRepository.findById(id).orElseThrow(ActivityNotFoundException::new);
        Activity activity = mappingService.getActivityData(activityAdminDTO, activityToUpdate);
        activity.setStatus(Status.ON_UPDATE);
        activityRepository.save(activity);
        log.info("Activity was updated");
        return conversionService.convert(activity, ActivityAdminDTO.class);
    }

    @Override
    public ActivityAdminDTO getById(Long id) {
        log.info("Try to find activity by id: " + id);
        Activity activity = activityRepository.findById(id).orElseThrow(ActivityNotFoundException::new);
        return conversionService.convert(activity, ActivityAdminDTO.class);

    }

    @Override
    public ActivityAdminUsersDTO getByIdAU(Long id) {
        log.info("Try to find activity by id: " + id);
        Activity activity = activityRepository.findById(id).orElseThrow(ActivityNotFoundException::new);
        return conversionService.convert(activity, ActivityAdminUsersDTO.class);

    }

    @Override
    public void setEndTimeForTimeTracker(Long activityId) {
        log.info("set End Time for Activity with TypeOFActivity - TimeTracker ");
        Activity activity = activityRepository.findById(activityId).orElseThrow(ActivityNotFoundException::new);
        activity.setEndTime(new Timestamp(System.currentTimeMillis()));
        activityRepository.save(activity);
        log.info("set End Time for Activity with TypeOFActivity - TimeTracker - success");
    }

    @Override
    public PagedModel<ActivityAdminUsersModel> findActivitiesByTypeOfActivityAndStatus(String typeOfActivity, Status status,
                                                                                       Pageable pageable) {
        log.info("Try to find activities by typeOfActivity and status");
        Page<Activity> page;
        if (Objects.isNull(typeOfActivity)) {
            log.info("Find activities by Status");
            page = activityRepository.findActivitiesByStatus(Status.ACCEPT, pageable);
        } else {
            log.info("Find activities by Status and typeOfActivity: " + typeOfActivity);
            page = activityRepository.findActivitiesByTypeOfActivityAndStatus(TypeOfActivity.valueOf(typeOfActivity), status, pageable);
        }

        Page<ActivityAdminUsersDTO> map = page.map(activity -> conversionService.convert(activity, ActivityAdminUsersDTO.class));
        return activityUsersAssemblerPaged.toModel(map, activityUsersAssembler);
    }


    @Override
    public PagedModel<ActivityAdminUsersModel> findActivitiesByCreatedByUserEmailOrUserId(String email, String typeOfActivity,
                                                                                          String status, Pageable pageable) {
        Page<Activity> page;
        log.info("Try to find activities by User email, typeOfActivity and status");
        Long userId = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new).getId();
        if (Objects.isNull(typeOfActivity)) {
            log.info("Find activities by Status and email: " + email);
            page = activityRepository.findActivitiesByCreatedByUserEmailOrUserId(userId, status, pageable);
        } else {
            log.info("Find activities by Status and email: " + email + " and typeOfActivity: " + typeOfActivity);
            page = activityRepository.findActivitiesByCreatedByUserIdOrUserIdAndTypeOfActivity(userId,
                    typeOfActivity, status, pageable);
        }
        Page<ActivityAdminUsersDTO> map = page.map(activity -> conversionService.convert(activity, ActivityAdminUsersDTO.class));
        return activityUsersAssemblerPaged.toModel(map, activityUsersAssembler);
    }

    @Override
    public PagedModel<ActivityAdminUsersModel> findActivitiesByCreatedByUserIdAndStatusAU(String email, Status status, Pageable pageable) {
        log.info("Try to find activities by created by User with this email" + email + "and status" + status);
        Long userId = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new).getId();
        Page<Activity> page = activityRepository.findActivitiesByCreatedByUserIdAndStatus(userId, status, pageable);
        Page<ActivityAdminUsersDTO> map = page.map(activity -> conversionService.convert(activity, ActivityAdminUsersDTO.class));
        return activityUsersAssemblerPaged.toModel(map, activityUsersAssembler);
    }


    @Override
    public PagedModel<ActivityModel> findActivitiesByCreatedByUserIdAndStatus(String email, Status status, Pageable pageable) {
        log.info("Try to find activities by created by User with this email" + email + "and status" + status);
        Long userId = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new).getId();
        Page<Activity> page = activityRepository.findActivitiesByCreatedByUserIdAndStatus(userId, status, pageable);
        Page<ActivityAdminDTO> map = page.map(activity -> conversionService.convert(activity, ActivityAdminDTO.class));
        return pagedResourcesAssembler.toModel(map, activityAssembler);
    }


    @Override
    public void delete(Long id) {
        log.info("Find activity by id: " + id);
        activityRepository.findById(id).orElseThrow(ActivityNotFoundException::new);
        activityRepository.deleteById(id);
        log.info("Activity was successfully deleted");
    }

    @Override
    public void setOnDelete(Long id) {
        log.info("User set activity to delete by this id: " + id);
        Activity activity = activityRepository.findById(id).orElseThrow(ActivityNotFoundException::new);
        activity.setStatus(Status.ON_DELETE);
        activityRepository.save(activity);
        log.info(" Activity successfully set on delete");
    }

    @Override
    public void acceptActivity(Long activityId) {
        Activity activity = activityRepository.getById(activityId).setStatus(Status.ACCEPT);
        activityRepository.save(activity);
    }

    @Override
    public void declineActivity(Long activityId) {
        Activity activity = activityRepository.getById(activityId).setStatus(Status.DECLINE);
        activityRepository.save(activity);
    }


}
