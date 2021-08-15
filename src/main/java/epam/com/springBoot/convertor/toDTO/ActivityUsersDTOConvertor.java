package epam.com.springBoot.convertor.toDTO;

import epam.com.springBoot.dto.activity.ActivityAdminUsersDTO;
import epam.com.springBoot.model.Activity;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class ActivityUsersDTOConvertor implements Converter<Activity, ActivityAdminUsersDTO> {

    UserDTOConvertor userConvertor = new UserDTOConvertor();

    @Override
    public ActivityAdminUsersDTO convert(Activity activity) {
        return new ActivityAdminUsersDTO()
                .setId(activity.getId())
                .setName(activity.getName())
                .setEndTime(activity.getEndTime())
                .setStartTime(activity.getStartTime())
                .setTypeOfActivity(activity.getTypeOfActivity().name())
                .setDescriptionRus(activity.getDescriptionRus())
                .setDescriptionEng(activity.getDescriptionEng())
                .setCreatedByUserId(activity.getCreatedByUserId())
                .setUsers(activity.getUsers().stream().map(user -> userConvertor.convert(user))
                        .collect(Collectors.toList()));

    }
}
