package epam.com.springBoot.service.impl;

import epam.com.springBoot.dto.UserDTO;
import epam.com.springBoot.exceptions.UserNotFoundException;
import epam.com.springBoot.model.User;
import epam.com.springBoot.repository.UserRepository;
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

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(UserDTO userDTO) {
        User user = conversionService.convert(userDTO, User.class);
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User update(UserDTO dto) {
        User userToUpdate = userRepository.getByEmail(dto.getEmail());
        userToUpdate.setPassword(dto.getPassword());
        userToUpdate.setSurname(dto.getSurname());
        userToUpdate.setName(dto.getName());
        return userRepository.save(userToUpdate);
    }


    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
