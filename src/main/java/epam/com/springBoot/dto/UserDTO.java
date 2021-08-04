package epam.com.springBoot.dto;

import epam.com.springBoot.dto.group.OnCreate;
import epam.com.springBoot.dto.group.OnUpdate;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class UserDTO {

    @NotBlank(message = "'email' shouldn't be empty", groups = OnCreate.class)
    @Email(message = "Please check if your email is correct",groups = OnCreate.class)
    private String email;

    @NotBlank(message = "name shouldn`t be empty", groups = OnCreate.class)
    private String name;

    @NotBlank(message = "'surname' shouldn't be empty", groups = OnCreate.class)
    private String surname;

    @NotNull(message = "'password' should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "'password' shouldn't be empty", groups = OnCreate.class)
    private String password;

    @NotNull(message = "'repeatPassword' should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "'repeatPassword' shouldn't be empty!", groups = OnCreate.class)
    private String confirmPassword;

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public UserDTO setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
