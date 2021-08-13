package epam.com.springBoot.convertor.toDTO;

import epam.com.springBoot.dto.user.UserDTO;
import epam.com.springBoot.model.User;
import org.springframework.core.convert.converter.Converter;

public class UserDTOConvertor implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(User user) {
        return new UserDTO()
                .setName(user.getName())
                .setEmail(user.getEmail())
                .setSurname(user.getSurname());
    }


}
