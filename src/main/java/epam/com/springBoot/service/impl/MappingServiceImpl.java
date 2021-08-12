package epam.com.springBoot.service.impl;

import epam.com.springBoot.dto.activity.ActivityAdminDTO;
import epam.com.springBoot.dto.user.UserDTO;
import epam.com.springBoot.model.Activity;
import epam.com.springBoot.model.TypeOfActivity;
import epam.com.springBoot.model.User;
import epam.com.springBoot.service.MappingService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;

@Service
public class MappingServiceImpl implements MappingService {

    public User getUserData(UserDTO dto, User user) {
        String surname = dto.getSurname();
        if (Objects.nonNull(surname)) {
            user.setSurname(surname);
        }
        String name = dto.getName();
        if (Objects.nonNull(name)) {
            user.setName(name);
        }
        String password = dto.getPassword();
        if (Objects.nonNull(password)) {
            user.setPassword(password);
        }
        return user;
    }

    @Override
    public Activity getActivityData(ActivityAdminDTO activityAdminDTO, Activity activity) {
        String name = activityAdminDTO.getName();
        if (Objects.nonNull(name))
            activity.setName(name);
        String descriptionEng = activityAdminDTO.getDescriptionEng();
        if (Objects.nonNull(descriptionEng))
            activity.setDescriptionEng(descriptionEng);
        String descriptionRus = activityAdminDTO.getDescriptionRus();
        if (Objects.nonNull(descriptionRus))
            activity.setDescriptionRus(descriptionRus);
        String typeOfActivity = activityAdminDTO.getTypeOfActivity();
        if (Objects.nonNull(typeOfActivity))
            activity.setTypeOfActivity(TypeOfActivity.valueOf(typeOfActivity));
        Timestamp startTime = activityAdminDTO.getStartTime();
        if (Objects.nonNull(startTime))
            activity.setStartTime(startTime);
        Timestamp endTime = activityAdminDTO.getEndTime();
        if (Objects.nonNull(endTime))
            activity.setEndTime(activityAdminDTO.getEndTime());

        return activity;
    }
}
