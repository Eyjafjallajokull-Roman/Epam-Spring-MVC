package epam.com.springBoot.controller.assembler;

import epam.com.springBoot.controller.UserController;
import epam.com.springBoot.controller.model.UserModel;
import epam.com.springBoot.dto.user.UserDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<UserDTO, UserModel> {

    public static final String GET_REL = "get_user";
    public static final String CREATE_USER = "create_user";
    public static final String UPDATE_USER = "update_user";
    public static final String DELETE_USER = "delete_user";

    public UserAssembler() {
        super(UserController.class, UserModel.class);
    }


    @Override
    public UserModel toModel(UserDTO entity) {
        UserModel userModel = new UserModel(entity);
        Link get = linkTo(methodOn(UserController.class).getUserByEmail(entity.getEmail())).withRel(GET_REL);
//        Link create = linkTo(methodOn(UserController.class).createUser(entity)).withRel(CREATE_USER);
        Link update = linkTo(methodOn(UserController.class).update(entity,entity.getEmail())).withRel(UPDATE_USER);
        Link delete = linkTo(methodOn(UserController.class).delete(entity.getEmail())).withRel(DELETE_USER);



        userModel.add(get, update, delete);
        return userModel;
    }
}
