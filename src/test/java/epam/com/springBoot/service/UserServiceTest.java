package epam.com.springBoot.service;

import epam.com.springBoot.dto.user.UserDTO;
import epam.com.springBoot.exceptions.UserAlreadyExist;
import epam.com.springBoot.exceptions.UserNotFoundException;
import epam.com.springBoot.model.User;
import epam.com.springBoot.repository.UserRepository;
import epam.com.springBoot.service.impl.MappingServiceImpl;
import epam.com.springBoot.service.impl.UserServiceImpl;
import epam.com.springBoot.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.Optional;

import static epam.com.springBoot.util.TestDataUtil.TEST_EMAIL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ConversionService conversionService;

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
}
