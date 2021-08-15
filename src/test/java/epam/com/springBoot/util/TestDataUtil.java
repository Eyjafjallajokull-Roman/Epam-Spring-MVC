package epam.com.springBoot.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import epam.com.springBoot.dto.user.UserDTO;
import epam.com.springBoot.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataUtil {
    public static final Long ID = 3000L;
    public static final String NAME = "NAME";
    public static final String SURNAME = "SURNAME";
    public static final String TEST_EMAIL = "email@email.com";
    private static final String PASSWORD = "password";


    public static User createUser() {
        return new User()
                .setId(ID)
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
                .setPassword(PASSWORD)
                .setConfirmPassword(PASSWORD);
    }

    public static String jsonMapper(UserDTO userDTO) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(userDTO);
    }

}
