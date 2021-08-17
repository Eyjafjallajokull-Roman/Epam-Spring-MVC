package epam.com.springBoot.service;

import epam.com.springBoot.controller.assembler.UserActivitiesAssembler;
import epam.com.springBoot.dto.user.UserActivitiesDTO;
import epam.com.springBoot.dto.user.UserDTO;
import epam.com.springBoot.exceptions.NoSuchUserException;
import epam.com.springBoot.exceptions.UserAlreadyExist;
import epam.com.springBoot.exceptions.UserNotFoundException;
import epam.com.springBoot.model.Activity;
import epam.com.springBoot.model.User;
import epam.com.springBoot.repository.ActivityRepository;
import epam.com.springBoot.repository.UserRepository;
import epam.com.springBoot.service.impl.MappingServiceImpl;
import epam.com.springBoot.service.impl.UserServiceImpl;
import epam.com.springBoot.util.ActivityDataUtil;
import epam.com.springBoot.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.config.HateoasAwareSpringDataWebConfiguration;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static epam.com.springBoot.util.TestDataUtil.ID;
import static epam.com.springBoot.util.TestDataUtil.TEST_EMAIL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Import(HateoasAwareSpringDataWebConfiguration.class)
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private ConversionService conversionService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Spy
    private static MappingService mappingService = new MappingServiceImpl();


    @Test
    void getByEmail() {
        User user = TestDataUtil.createUser();
        Optional<User> optionalUser = Optional.of(user);
        UserDTO userDTO = TestDataUtil.createUserDto();
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(optionalUser);
        when(conversionService.convert(user, UserDTO.class)).thenReturn(userDTO);

        UserDTO result = userService.getByEmail(TEST_EMAIL);

        assertThat(result, allOf(
                hasProperty("email", equalTo(user.getEmail())),
                hasProperty("name", equalTo(user.getName())))
        );
    }

    @Test
    void getUserUserNotFoundTest() {
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getByEmail(TEST_EMAIL));
    }

    @Test
    public void createUserTest() {
        User testUser = TestDataUtil.createUser();
        UserDTO testUserDto = TestDataUtil.createUserDto();
        when(userRepository.save(any())).thenReturn(testUser);
        when(conversionService.convert(testUserDto, User.class)).thenReturn(testUser);
        when(conversionService.convert(testUser, UserDTO.class)).thenReturn(testUserDto);
        when(passwordEncoder.encode(testUserDto.getPassword())).thenReturn("encodePassword");

        UserDTO userDTO = userService.createUser(testUserDto);


        assertThat(userDTO, allOf(
                hasProperty("name", equalTo(testUser.getName())),
                hasProperty("surname", equalTo(testUser.getSurname())),
                hasProperty("email", equalTo(testUser.getEmail()))
        ));
    }

    @Test
    public void createUserAlreadyExistUserTest() {
        UserDTO userDTO = TestDataUtil.createUserDto();
        when(userRepository.existsByEmail(TEST_EMAIL)).thenReturn(true);

        assertThrows(UserAlreadyExist.class, () -> userService.createUser(userDTO));
    }

    @Test
    public void deleteByEmailTest() {
        User testUser = TestDataUtil.createUser();
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(testUser));

        userService.deleteByEmail(testUser.getEmail());
        verify(userRepository, times(1)).delete(testUser);
    }

    @Test
    void deleteByEmailUserNotFound() {
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.deleteByEmail(TEST_EMAIL));
    }

    @Test
    public void updateTest() {
        User user = TestDataUtil.createUser();
        UserDTO userDTO = TestDataUtil.createUserDto();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(conversionService.convert(user, UserDTO.class)).thenReturn(userDTO);


        UserDTO userDTO1 = userService.update(userDTO, user.getEmail());

        verify(userRepository, times(1)).save(user);

        assertThat(userDTO1, allOf(
                hasProperty("name", equalTo(user.getName())),
                hasProperty("surname", equalTo(user.getSurname())),
                hasProperty("email", equalTo(user.getEmail()))
        ));
    }

    @Test
    void updateUserNotFound() {
        UserDTO userDTO = TestDataUtil.createUserDto();
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.update(userDTO, userDTO.getEmail()));
    }

    @Test
    void addUserToActivityTest() {
        User user = TestDataUtil.createUser();
        Activity activity = ActivityDataUtil.createActivity();
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(user));
        when(activityRepository.existsById(ActivityDataUtil.ID)).thenReturn(true);

        userService.addUserToActivity(user.getEmail(), activity.getId());

        verify(userRepository, times(1)).addUserToActivity(ID, ActivityDataUtil.ID);

    }

    @Test
    void addUserToActivityNoSuchUserTest() {
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.empty());
        assertThrows(NoSuchUserException.class, () -> userService.addUserToActivity(TEST_EMAIL, ActivityDataUtil.ID));
    }

    @Test
    void deleteUserFromActivityTest() {
        User user = TestDataUtil.createUser();
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(user));
        when(activityRepository.existsById(ActivityDataUtil.ID)).thenReturn(true);
        userService.deleteUserFromActivity(TEST_EMAIL, ActivityDataUtil.ID);
        verify(userRepository, times(1)).deleteUserFromActivity(ID, ActivityDataUtil.ID);
    }

    @Test
    void deleteUserToActivityNoSuchUserTest() {
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.empty());
        assertThrows(NoSuchUserException.class, () -> userService.deleteUserFromActivity(TEST_EMAIL, ActivityDataUtil.ID));
    }

    @Test
    void findAllTest() {
        Pageable pageable = PageRequest.of(0, 1);
        List<User> userList = new ArrayList<>();
        List<UserActivitiesDTO> dtos = new ArrayList<>();
        UserActivitiesDTO userDTO = TestDataUtil.createUserActivitiesDto();
        User user = TestDataUtil.createUser();
        userList.add(user);
        dtos.add(userDTO);
        Page<UserActivitiesDTO> dtosPage = new PageImpl<>(dtos);
        Page<User> users = new PageImpl<>(userList, pageable, 1);
        PagedModel<UserActivitiesDTO> pagedModel = PagedModel.empty();

        when(userRepository.findAll(pageable)).thenReturn(users);
        when(conversionService.convert(user, UserActivitiesDTO.class)).thenReturn(userDTO);
        userService.findAll(pageable);
        verify(userRepository, times(1)).findAll(pageable);

    }


}
