package epam.com.springBoot.convertor.toDTO;

import epam.com.springBoot.dto.user.UserActivitiesDTO;
import epam.com.springBoot.model.User;
import org.springframework.core.convert.converter.Converter;

public class UserActivityDTOConvertor implements Converter<User, UserActivitiesDTO> {

//    @Autowired
//    ConversionService conversionService;

    @Override
    public UserActivitiesDTO convert(User user) {
        return new UserActivitiesDTO()
                .setName(user.getName())
                .setEmail(user.getEmail())
                .setSurname(user.getSurname())
                .setPassword(user.getPassword());
//                .setListActivities(user.getActivities()
//                        .stream()
//                        .map(activity -> conversionService.convert(activity, ActivityAdminDTO.class))
//                        .collect(Collectors.toList()));
    }
}
