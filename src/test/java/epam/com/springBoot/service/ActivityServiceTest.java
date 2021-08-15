package epam.com.springBoot.service;

import epam.com.springBoot.dto.activity.ActivityAdminDTO;
import epam.com.springBoot.exceptions.ActivityNotFoundException;
import epam.com.springBoot.model.Activity;
import epam.com.springBoot.repository.ActivityRepository;
import epam.com.springBoot.service.impl.ActivityServiceImpl;
import epam.com.springBoot.service.impl.MappingServiceImpl;
import epam.com.springBoot.util.ActivityDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.Optional;

import static epam.com.springBoot.util.ActivityDataUtil.ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ActivityServiceTest {

    @InjectMocks
    ActivityServiceImpl activityService;

    @Mock
    ActivityRepository activityRepository;

    @Mock
    ConversionService conversionService;

    @Spy
    private static MappingService mappingService = new MappingServiceImpl();

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
        //todo property for status
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




}
