package epam.com.springBoot.service.impl;

import epam.com.springBoot.dto.ActivityDTO;
import epam.com.springBoot.model.Activity;
import epam.com.springBoot.repository.ActivityRepository;
import epam.com.springBoot.service.ActivityService;
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


    @Override
    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    @Override
    public Activity findById(Long id) {
        return activityRepository.getById(id);
    }

    @Override
    public Activity save(ActivityDTO activityDTO) {
        Activity convert = conversionService.convert(activityDTO, Activity.class);
        return activityRepository.save(convert);
    }

    @Override
    public void delete(Long id) {
        activityRepository.deleteById(id);

    }

}
