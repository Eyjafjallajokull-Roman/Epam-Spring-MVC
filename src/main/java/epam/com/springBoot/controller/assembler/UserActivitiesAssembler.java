package epam.com.springBoot.controller.assembler;

import epam.com.springBoot.controller.UserController;
import epam.com.springBoot.controller.model.UserActivitiesModel;
import epam.com.springBoot.dto.user.UserActivitiesDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class UserActivitiesAssembler extends RepresentationModelAssemblerSupport<UserActivitiesDTO, UserActivitiesModel> {

    public static final String GET_REL = "get_user";
    public static final String CREATE_USER = "create_user";
    public static final String UPDATE_USER = "update_user";
    public static final String DELETE_USER = "delete_user";


    public UserActivitiesAssembler() {
        super(UserController.class, UserActivitiesModel.class);
    }

    @Override
    public UserActivitiesModel toModel(UserActivitiesDTO entity) {
        UserActivitiesModel userModel = new UserActivitiesModel(entity);
        Link get = linkTo(methodOn(UserController.class).getUserByEmail(entity.getEmail())).withRel(GET_REL);
        userModel.add(get);
        return userModel;

    }
}
