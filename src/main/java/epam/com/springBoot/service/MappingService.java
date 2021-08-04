package epam.com.springBoot.service;

import epam.com.springBoot.dto.UserDTO;
import epam.com.springBoot.model.User;

public interface MappingService {

    User getData(UserDTO dto, User user);

}
