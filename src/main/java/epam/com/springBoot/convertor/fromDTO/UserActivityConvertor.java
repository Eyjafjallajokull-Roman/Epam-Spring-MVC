package epam.com.springBoot.convertor.fromDTO;

import epam.com.springBoot.dto.user.UserActivitiesDTO;
import epam.com.springBoot.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class UserActivityConvertor implements Converter<UserActivitiesDTO, User> {


    ActivityConvertor activityConvertor = new ActivityConvertor();

    @Override
    public User convert(UserActivitiesDTO dto) {
        return new User()
                .setName(dto.getName())
                .setEmail(dto.getEmail())
                .setSurname(dto.getSurname())
                .setPassword(dto.getPassword())
                .setActivities(dto.getActivities().stream().map(activityAdminDTO -> activityConvertor.convert(activityAdminDTO)).collect(Collectors.toList()));
    }
}
