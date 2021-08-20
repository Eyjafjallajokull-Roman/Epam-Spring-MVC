package epam.com.springBoot.service;

import epam.com.springBoot.controller.assembler.ActivityAssembler;
import epam.com.springBoot.controller.assembler.ActivityUsersAssembler;
import epam.com.springBoot.controller.model.ActivityAdminUsersModel;
import epam.com.springBoot.controller.model.ActivityModel;
import epam.com.springBoot.dto.activity.ActivityAdminDTO;
import epam.com.springBoot.dto.activity.ActivityAdminUsersDTO;
import epam.com.springBoot.exceptions.ActivityNotFoundException;
import epam.com.springBoot.model.Activity;
import epam.com.springBoot.repository.ActivityRepository;
import epam.com.springBoot.repository.UserRepository;
import epam.com.springBoot.service.impl.ActivityServiceImpl;
import epam.com.springBoot.service.impl.MappingServiceImpl;
import epam.com.springBoot.util.ActivityDataUtil;
import epam.com.springBoot.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static epam.com.springBoot.util.ActivityDataUtil.ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class ActivityServiceTest {

    @InjectMocks
    ActivityServiceImpl activityService;

    @Mock
    ActivityRepository activityRepository;

    @Mock
    ConversionService conversionService;

    @Mock
    UserRepository userRepository;

    @Spy
    private static MappingService mappingService = new MappingServiceImpl();

    @Mock
    private PagedResourcesAssembler<ActivityAdminUsersDTO> activityUsersAssemblerPaged;

    @Mock
    private ActivityUsersAssembler activityUsersAssembler;

    @Mock
    private PagedModel<ActivityAdminUsersModel> activityAdminUsersDTOPagedModel;

    @Mock
    private PagedModel<ActivityModel> activityAdminDTOPagedModel;
    @Mock
    ActivityAssembler activityAssembler;
    @Mock
    PagedResourcesAssembler<ActivityAdminDTO> activityAdminDTOPagedResourcesAssembler;


    @Test
    public void getByIdTest() {
        Activity activity = ActivityDataUtil.createActivity();
        ActivityAdminDTO activityAdminDTO = ActivityDataUtil.createActivityDto();
        when(activityRepository.findById(activity.getId())).thenReturn(Optional.of(activity));
        when(conversionService.convert(activity, ActivityAdminDTO.class)).thenReturn(activityAdminDTO);

        ActivityAdminDTO activityAdminDTO1 = activityService.getById(activity.getId());

        assertThat(activityAdminDTO1, allOf(
                hasProperty("name", equalTo(activity.getName())),
                hasProperty("descriptionEng", equalTo(activity.getDescriptionEng())),
                hasProperty("id", equalTo(activity.getId()))
        ));
    }

    @Test
    void getByIdActivityNotFoundTest() {
        when(activityRepository.findById(ID)).thenReturn(Optional.empty());
        assertThrows(ActivityNotFoundException.class, () -> activityService.getById(ID));
    }

    @Test
    void createActivityTest() {
        Activity activity = ActivityDataUtil.createActivity();
        ActivityAdminDTO activityAdminDTO = ActivityDataUtil.createActivityDto();
        when(activityRepository.save(any())).thenReturn(activity);
        when(conversionService.convert(activityAdminDTO, Activity.class)).thenReturn(activity);
        when(conversionService.convert(activity, ActivityAdminDTO.class)).thenReturn(activityAdminDTO);

        ActivityAdminDTO result = activityService.createActivity(activityAdminDTO);

        assertThat(result, allOf(
                hasProperty("name", equalTo(activity.getName())),
                hasProperty("descriptionEng", equalTo(activity.getDescriptionEng())),
                hasProperty("id", equalTo(activity.getId()))));
    }

    @Test
    void updateActivityTest() {
        Activity activity = ActivityDataUtil.createActivity();
        ActivityAdminDTO activityAdminDTO = ActivityDataUtil.createActivityDto();
        when(conversionService.convert(activity, ActivityAdminDTO.class)).thenReturn(activityAdminDTO);
        when(activityRepository.findById(activity.getId())).thenReturn(Optional.of(activity));
        ActivityAdminDTO result = activityService.updateActivity(activityAdminDTO, activityAdminDTO.getId());
        verify(activityRepository, times(1)).save(activity);

        assertThat(result, allOf(
                hasProperty("name", equalTo(activity.getName())),
                hasProperty("descriptionEng", equalTo(activity.getDescriptionEng())),
                hasProperty("id", equalTo(activity.getId()))));

    }

    @Test
    void updateActivityActivityNotFoundTest() {
        ActivityAdminDTO activityAdminDTO = ActivityDataUtil.createActivityDto();
        when(activityRepository.findById(ID)).thenReturn(Optional.empty());
        assertThrows(ActivityNotFoundException.class, () -> activityService.updateActivity(activityAdminDTO, ID));
    }


    @Test
    void deleteActivityTest() {
        Activity activity = ActivityDataUtil.createActivity();
        when(activityRepository.findById(activity.getId())).thenReturn(Optional.of(activity));

        activityService.delete(ID);
        verify(activityRepository, times(1)).deleteById(activity.getId());

    }


    @Test
    void setOnDeleteTest() {
        Activity activity = ActivityDataUtil.createActivity();
        when(activityRepository.findById(ID)).thenReturn(Optional.of(activity));
        activityService.setOnDelete(ID);
        verify(activityRepository, times(1)).save(activity);

    }

    @Test
    void acceptActivityTest() {
        Activity activity = ActivityDataUtil.createActivity();
        when(activityRepository.getById(ID)).thenReturn(activity);
        activityService.acceptActivity(ID);
        verify(activityRepository, times(1)).save(activity);
    }

    @Test
    void declineActivityTest() {
        Activity activity = ActivityDataUtil.createActivity();
        when(activityRepository.getById(ID)).thenReturn(activity);
        activityService.declineActivity(ID);
        verify(activityRepository, times(1)).save(activity);

    }

    @Test
    void findAllActivitiesTest() {
        Pageable pageable = PageRequest.of(0, 1);
        List<Activity> activities = new ArrayList<>();
        List<ActivityAdminUsersDTO> dtos = new ArrayList<>();

        ActivityAdminUsersDTO activitiesDto = ActivityDataUtil.createActivityUsersDto();
        Activity activity = ActivityDataUtil.createActivity();
        activities.add(activity);
        dtos.add(activitiesDto);

        Page<ActivityAdminUsersDTO> dtoPage = new PageImpl<>(dtos);
        Page<Activity> activityPage = new PageImpl<>(activities, pageable, 1);
        PagedModel<ActivityAdminUsersDTO> pagedModel = PagedModel.empty();

        when(activityRepository.findAll(pageable)).thenReturn(activityPage);
        when(conversionService.convert(activity, ActivityAdminUsersDTO.class)).thenReturn(activitiesDto);
        when(activityUsersAssemblerPaged.toModel(any(), eq(activityUsersAssembler))).thenReturn(activityAdminUsersDTOPagedModel);

        activityService.findAllActivities(pageable);

        verify(activityRepository, times(1)).findAll(pageable);
        verify(conversionService, times(1)).convert(activity, ActivityAdminUsersDTO.class);
        verify(activityUsersAssemblerPaged, times(1)).toModel(any(), eq(activityUsersAssembler));
    }

    @Test
    void findActivitiesByCreatedByUserIdAndStatusTest() {
        Pageable pageable = PageRequest.of(0, 1);
        List<Activity> activities = new ArrayList<>();
        List<ActivityAdminDTO> dtos = new ArrayList<>();

        ActivityAdminDTO activitiesDto = ActivityDataUtil.createActivityDto();
        Activity activity = ActivityDataUtil.createActivity();
        activities.add(activity);
        dtos.add(activitiesDto);

        Page<ActivityAdminDTO> dtoPage = new PageImpl<>(dtos);
        Page<Activity> activityPage = new PageImpl<>(activities, pageable, 1);
        PagedModel<ActivityAdminUsersDTO> pagedModel = PagedModel.empty();

        when(userRepository.findByEmail(TestDataUtil.TEST_EMAIL)).thenReturn(Optional.of(TestDataUtil.createUser()));
        when(activityRepository.findActivitiesByCreatedByUserIdAndStatus(TestDataUtil.ID, activity.getStatus(), pageable))
                .thenReturn(activityPage);
        when(conversionService.convert(activity, ActivityAdminDTO.class)).thenReturn(activitiesDto);
        when(activityAdminDTOPagedResourcesAssembler.toModel(any(), eq(activityAssembler))).thenReturn(activityAdminDTOPagedModel);

        activityService.findActivitiesByCreatedByUserIdAndStatus(TestDataUtil.TEST_EMAIL, activity.getStatus(), pageable);

        verify(activityRepository, times(1)).findActivitiesByCreatedByUserIdAndStatus(TestDataUtil.ID, activity.getStatus(), pageable);
        verify(conversionService, times(1)).convert(activity, ActivityAdminDTO.class);
        verify(activityAdminDTOPagedResourcesAssembler, times(1)).toModel(any(), eq(activityAssembler));
    }


    @Test
    void findActivitiesByCreatedByUserIdAndStatusAUTest() {
        Pageable pageable = PageRequest.of(0, 1);
        List<Activity> activities = new ArrayList<>();
        List<ActivityAdminUsersDTO> dtos = new ArrayList<>();

        ActivityAdminUsersDTO activitiesDto = ActivityDataUtil.createActivityUsersDto();
        Activity activity = ActivityDataUtil.createActivity();
        activities.add(activity);
        dtos.add(activitiesDto);

        Page<ActivityAdminUsersDTO> dtoPage = new PageImpl<>(dtos);
        Page<Activity> activityPage = new PageImpl<>(activities, pageable, 1);
        PagedModel<ActivityAdminDTO> pagedModel = PagedModel.empty();

        when(userRepository.findByEmail(TestDataUtil.TEST_EMAIL)).thenReturn(Optional.of(TestDataUtil.createUser()));
        when(activityRepository.findActivitiesByCreatedByUserIdAndStatus(TestDataUtil.ID, activity.getStatus(), pageable))
                .thenReturn(activityPage);
        when(conversionService.convert(activity, ActivityAdminUsersDTO.class)).thenReturn(activitiesDto);
        when(activityUsersAssemblerPaged.toModel(any(), eq(activityUsersAssembler))).thenReturn(activityAdminUsersDTOPagedModel);

        activityService.findActivitiesByCreatedByUserIdAndStatusAU(TestDataUtil.TEST_EMAIL, activity.getStatus(), pageable);

        verify(activityRepository, times(1)).findActivitiesByCreatedByUserIdAndStatus(TestDataUtil.ID, activity.getStatus(), pageable);
        verify(conversionService, times(1)).convert(activity, ActivityAdminUsersDTO.class);
        verify(activityUsersAssemblerPaged, times(1)).toModel(any(), eq(activityUsersAssembler));
    }


}
