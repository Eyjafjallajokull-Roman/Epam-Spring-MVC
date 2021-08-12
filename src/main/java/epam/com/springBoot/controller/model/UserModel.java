package epam.com.springBoot.controller.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import epam.com.springBoot.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class UserModel extends RepresentationModel<UserModel> {

    @JsonUnwrapped
    private UserDTO userDTO;
}
