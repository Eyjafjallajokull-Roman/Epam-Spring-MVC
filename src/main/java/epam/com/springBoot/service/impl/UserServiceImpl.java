package epam.com.springBoot.service.impl;

import epam.com.springBoot.dto.UserDTO;
import epam.com.springBoot.exceptions.NoSuchUserException;
import epam.com.springBoot.exceptions.UserNotFoundException;
import epam.com.springBoot.model.User;
import epam.com.springBoot.repository.UserRepository;
import epam.com.springBoot.service.MappingService;
import epam.com.springBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private MappingService mappingService;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (!userRepository.existsByEmail(userDTO.getEmail())) {
            throw new NoSuchUserException();
        }
        User user = conversionService.convert(userDTO, User.class);
        user = userRepository.save(user);
        return conversionService.convert(user, UserDTO.class);
    }


    @Override
    public UserDTO getById(Long id) {
        return conversionService
                .convert(userRepository.findById(id).orElseThrow(UserNotFoundException::new), UserDTO.class);
    }

    @Override
    public UserDTO update(UserDTO dto, String email) {
        User userToUpdate = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        User user = mappingService.getUserData(dto, userToUpdate);
        userRepository.save(user);
        return conversionService.convert(user, UserDTO.class);
    }

    @Override
    public UserDTO getByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return conversionService.convert(user, UserDTO.class);
    }


    @Override
    public void deleteByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }
}
