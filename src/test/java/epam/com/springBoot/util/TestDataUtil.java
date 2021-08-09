package epam.com.springBoot.util;

import epam.com.springBoot.dto.UserDTO;
import epam.com.springBoot.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;



@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataUtil {
    public static final String NAME = "NAME";
    public static final String SURNAME = "SURNAME";
    public static final String TEST_EMAIL = "email@email.com";
    private static final String PASSWORD = "password";


    public static User createUser() {
        return new User()
                .setPassword(PASSWORD)
                .setName(NAME)
                .setSurname(SURNAME)
                .setEmail(TEST_EMAIL);
    }

    public static UserDTO createUserDto() {
        return new UserDTO()
                .setName(NAME)
                .setEmail(TEST_EMAIL)
                .setSurname(SURNAME)
                .setPassword(PASSWORD);
    }

}
