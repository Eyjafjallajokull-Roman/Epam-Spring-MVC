package epam.com.springBoot.controller.assembler;

import epam.com.springBoot.controller.AdminController;
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

    public UserActivitiesAssembler() {
        super(AdminController.class, UserActivitiesModel.class);
    }

    @Override
    public UserActivitiesModel toModel(UserActivitiesDTO entity) {
        UserActivitiesModel userModel = new UserActivitiesModel(entity);
        Link get = linkTo(methodOn(AdminController.class).findUser(entity.getEmail())).withRel(GET_REL);
        userModel.add(get);
        return userModel;

    }
}
