package epam.com.springBoot.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String name;
    private String surname;
    private String password;
    private String confirmPassword;


}
