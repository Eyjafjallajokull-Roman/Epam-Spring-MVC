package epam.com.springBoot.controller.assembler;

import epam.com.springBoot.controller.UserController;
import epam.com.springBoot.controller.model.UserActivitiesModel;
import epam.com.springBoot.dto.user.UserActivitiesDTO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UserActivitiesAssembler extends RepresentationModelAssemblerSupport<UserActivitiesDTO, UserActivitiesModel> {
    public UserActivitiesAssembler() {
        super(UserController.class, UserActivitiesModel.class);
    }

    @Override
    public UserActivitiesModel toModel(UserActivitiesDTO entity) {
        return null;
    }
}
