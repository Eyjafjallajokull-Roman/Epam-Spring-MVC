package epam.com.springBoot.service.impl;

import epam.com.springBoot.dto.ActivityDTO;
import epam.com.springBoot.exceptions.ActivityNotFoundException;
import epam.com.springBoot.exceptions.NoSuchUserException;
import epam.com.springBoot.model.Activity;
import epam.com.springBoot.repository.ActivityRepository;
import epam.com.springBoot.service.ActivityService;
import epam.com.springBoot.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private MappingService mappingService;


    @Override
    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    @Override
    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        if (!activityRepository.existsById(activityDTO.getId())) {
            throw new NoSuchUserException();
        }
        Activity activity = conversionService.convert(activityDTO, Activity.class);
        activity = activityRepository.save(activity);
        return conversionService.convert(activity, ActivityDTO.class);
    }

    @Override
    public ActivityDTO updateActivity(ActivityDTO activityDTO, Long id) {
        Activity activityToUpdate = activityRepository.findById(id).orElseThrow(ActivityNotFoundException::new);
        Activity activity = mappingService.getActivityData(activityDTO, activityToUpdate);
        activity = activityRepository.save(activity);
        return conversionService.convert(activity, ActivityDTO.class);
    }

    @Override
    public ActivityDTO getById(Long id) {
        Activity activity = activityRepository.findById(id).orElseThrow(ActivityNotFoundException::new);
        return conversionService.convert(activity, ActivityDTO.class);
    }


    @Override
    public void delete(Long id) {
        activityRepository.findById(id).orElseThrow(ActivityNotFoundException::new);
        activityRepository.deleteById(id);

    }

}
