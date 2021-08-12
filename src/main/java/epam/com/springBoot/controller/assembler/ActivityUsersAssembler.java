package epam.com.springBoot.controller.assembler;

import epam.com.springBoot.controller.ActivityController;
import epam.com.springBoot.controller.model.ActivityAdminUsersModel;
import epam.com.springBoot.dto.activity.ActivityAdminUsersDTO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ActivityUsersAssembler extends RepresentationModelAssemblerSupport<ActivityAdminUsersDTO, ActivityAdminUsersModel> {

    public ActivityUsersAssembler() {
        super(ActivityController.class, ActivityAdminUsersModel.class);
    }

    @Override
    public ActivityAdminUsersModel toModel(ActivityAdminUsersDTO entity) {
        return null;
    }
}
