package epam.com.springBoot.service;

import epam.com.springBoot.dto.activity.ActivityAdminDTO;
import epam.com.springBoot.dto.user.UserDTO;
import epam.com.springBoot.model.Activity;
import epam.com.springBoot.model.User;

public interface MappingService {

    User getUserData(UserDTO userDTO, User user);

    Activity getActivityData(ActivityAdminDTO activityAdminDTO, Activity activity);

}
