package epam.com.springBoot.convertor.fromDTO;

import epam.com.springBoot.dto.user.UserDTO;
import epam.com.springBoot.model.User;
import org.springframework.core.convert.converter.Converter;


public class UserConvertor implements Converter<UserDTO, User> {
    @Override
    public User convert(UserDTO dto) {
        return new User()
                .setName(dto.getName())
                .setEmail(dto.getEmail())
                .setSurname(dto.getSurname())
                .setPassword(dto.getPassword());
    }


}
