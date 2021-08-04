package epam.com.springBoot.service;

import epam.com.springBoot.dto.ActivityDTO;
import epam.com.springBoot.dto.UserDTO;
import epam.com.springBoot.model.Activity;
import epam.com.springBoot.model.User;

public interface MappingService {

    User getUserData(UserDTO userDTO, User user);

    Activity getActivityData(ActivityDTO activityDTO, Activity activity);

}
