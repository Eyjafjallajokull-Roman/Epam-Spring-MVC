package epam.com.springBoot.convertor.fromDTO;

import epam.com.springBoot.dto.activity.ActivityAdminUsersDTO;
import epam.com.springBoot.model.Activity;
import epam.com.springBoot.model.TypeOfActivity;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class ActivityUserConvertor implements Converter<ActivityAdminUsersDTO, Activity> {

    UserConvertor userConvertor = new UserConvertor();

    @Override
    public Activity convert(ActivityAdminUsersDTO dto) {
        return new Activity()
                .setDescriptionEng(dto.getDescriptionEng())
                .setDescriptionRus(dto.getDescriptionRus())
                .setEndTime(dto.getEndTime())
                .setStartTime(dto.getStartTime())
                .setName(dto.getName())
                .setTypeOfActivity(TypeOfActivity.valueOf(dto.getTypeOfActivity()))
                .setCreatedByUserId(dto.getCreatedByUserId())
                .setUsers(dto.getUsers().stream().map(userDTO -> userConvertor.convert(userDTO))
                        .collect(Collectors.toList()));
    }
}
