package epam.com.springBoot.service.impl;

import epam.com.springBoot.controller.assembler.UserActivitiesAssembler;
import epam.com.springBoot.controller.assembler.UserAssembler;
import epam.com.springBoot.controller.model.UserActivitiesModel;
import epam.com.springBoot.dto.user.UserActivitiesDTO;
import epam.com.springBoot.dto.user.UserDTO;
import epam.com.springBoot.exceptions.ActivityNotFoundException;
import epam.com.springBoot.exceptions.NoSuchUserException;
import epam.com.springBoot.exceptions.UserAlreadyExist;
import epam.com.springBoot.exceptions.UserNotFoundException;
import epam.com.springBoot.model.Role;
import epam.com.springBoot.model.User;
import epam.com.springBoot.repository.ActivityRepository;
import epam.com.springBoot.repository.UserRepository;
import epam.com.springBoot.service.MappingService;
import epam.com.springBoot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private UserAssembler userAssembler;
    @Autowired
    private UserActivitiesAssembler userActivitiesAssembler;
    @Autowired
    private PagedResourcesAssembler<UserActivitiesDTO> userActivitiesResourceAssembler;
    @Autowired
    private MappingService mappingService;
    @Autowired
    private PagedResourcesAssembler<UserDTO> pagedResourcesAssembler;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public PagedModel<UserActivitiesModel> findAll(Pageable pageable) {
        log.info("Find All - find all users in User Service");
        Page<User> pageResult = userRepository.findAll(pageable);
        pageResult = pageResult.map(user -> user.setActivities(activityRepository.findActivitiesByCreatedByUserId(user.getId())));
        Page<UserActivitiesDTO> map = pageResult.map(user -> conversionService.convert(user, UserActivitiesDTO.class));
        return userActivitiesResourceAssembler.toModel(map, userActivitiesAssembler);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        log.info("Create user in User Service");
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserAlreadyExist();
        }
        User user = conversionService.convert(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.CLIENT);
        user = userRepository.save(user);
        log.info("User was created successfully");
        return conversionService.convert(user, UserDTO.class);
    }


    @Override
    public UserDTO getById(Long id) {
        log.info("Get User by id: " + id);
        return conversionService
                .convert(userRepository.findById(id).orElseThrow(UserNotFoundException::new), UserDTO.class);
    }

    @Override
    public UserDTO update(UserDTO dto, String email) {
        log.info("Try to update user with email: " + email);
        User userToUpdate = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        User user = mappingService.getUserData(dto, userToUpdate);
        userRepository.save(user);
        log.info("User was successfully updated");
        return conversionService.convert(user, UserDTO.class);
    }

    @Override
    public UserDTO getByEmail(String email) {
        log.info("Get User by email: " + email);
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return conversionService.convert(user, UserDTO.class);
    }


    @Override
    public UserActivitiesDTO getByEmailUADTO(String email) {
        log.info("Get User by email: " + email);
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return conversionService.convert(user, UserActivitiesDTO.class);
    }

    @Override
    public PagedModel<UserActivitiesModel> findAllUsersByActivityId(Long id, Pageable pageable) {
        log.info("Find all Users by activity id " + id);
        Long activityId = activityRepository.findById(id).orElseThrow(ActivityNotFoundException::new).getId();
        Page<User> page = userRepository.findAllUsersByActivityId(activityId, pageable);
        page = page.map(user -> user.setActivities(activityRepository.findActivitiesByCreatedByUserId(user.getId())));
        Page<UserActivitiesDTO> map = page.map(user -> conversionService.convert(user, UserActivitiesDTO.class));
        return userActivitiesResourceAssembler.toModel(map, userActivitiesAssembler);
    }


    @Override
    public void deleteByEmail(String email) {
        log.info("Try to delete user by this email: " + email);
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        log.info("User was successfully deleted");
    }

    @Override
    public void addUserToActivity(String email, Long activityId) {
        Long userId = userRepository.findByEmail(email).orElseThrow(NoSuchUserException::new).getId();

        log.info("Try to add user with id:" + userId + " and activity id: " + activityId);
        if (activityRepository.existsById(activityId))
            userRepository.addUserToActivity(userId, activityId);
        log.info("User was successfully added to activity");
    }


    @Override
    public void deleteUserFromActivity(String email, Long activityId) {
        Long userId = userRepository.findByEmail(email).orElseThrow(NoSuchUserException::new).getId();

        log.info("Try to delete user with id:" + userId + " and activity id: " + activityId);
        if (activityRepository.existsById(activityId))
            userRepository.deleteUserFromActivity(userId, activityId);
        log.info("User was successfully deleted to activity");
    }


}
