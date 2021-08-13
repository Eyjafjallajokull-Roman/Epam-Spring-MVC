package epam.com.springBoot.convertor.fromDTO;

import epam.com.springBoot.dto.user.UserActivitiesDTO;
import epam.com.springBoot.model.Activity;
import epam.com.springBoot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

public class UserActivityConvertor implements Converter<UserActivitiesDTO, User> {
//    @Autowired
//    ConversionService conversionService;

    @Override
    public User convert(UserActivitiesDTO dto) {
        return new User()
                .setName(dto.getName())
                .setEmail(dto.getEmail())
                .setSurname(dto.getSurname())
                .setPassword(dto.getPassword());
//                .setActivities(dto.getActivities()
//                        .stream()
//                        .map(activityAdminDTO -> conversionService.convert(activityAdminDTO, Activity.class))
//                        .collect(Collectors.toList()));
    }
}
