package epam.com.springBoot.service.impl;

import epam.com.springBoot.dto.UserDTO;
import epam.com.springBoot.model.User;
import epam.com.springBoot.service.MappingService;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public class MappingServiceImpl implements MappingService {

    public User getData(UserDTO dto, User user) {
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
}
