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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActivityServiceTest {

    @InjectMocks
    ActivityServiceImpl activityService;

    @Mock
    ActivityRepository activityRepository;

    @Mock
    ConversionService conversionService;

    @Spy
    MappingService mappingService = new MappingServiceImpl();

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
    public void createActivityTest() {
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


}
