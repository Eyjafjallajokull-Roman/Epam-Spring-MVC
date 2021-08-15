package epam.com.springBoot.convertor.toDTO;

import epam.com.springBoot.dto.user.UserActivitiesDTO;
import epam.com.springBoot.model.User;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;


public class UserActivityDTOConvertor implements Converter<User, UserActivitiesDTO> {

    ActivityDTOConvertor activityConvertor = new ActivityDTOConvertor();

    @Override
    public UserActivitiesDTO convert(User user) {
        return new UserActivitiesDTO()
                .setName(user.getName())
                .setEmail(user.getEmail())
                .setSurname(user.getSurname())
                .setPassword(user.getPassword())
                .setActivities(user.getActivities().stream().map(activity -> activityConvertor.convert(activity)).collect(Collectors.toList()));

    }
}
