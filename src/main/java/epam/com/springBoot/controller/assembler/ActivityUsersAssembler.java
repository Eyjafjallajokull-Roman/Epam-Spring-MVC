package epam.com.springBoot.controller.assembler;

import epam.com.springBoot.controller.AdminController;
import epam.com.springBoot.controller.model.ActivityAdminUsersModel;
import epam.com.springBoot.dto.activity.ActivityAdminUsersDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ActivityUsersAssembler extends RepresentationModelAssemblerSupport<ActivityAdminUsersDTO, ActivityAdminUsersModel> {
    public static final String GET_REL = "get_activity";

    public ActivityUsersAssembler() {
        super(AdminController.class, ActivityAdminUsersModel.class);
    }

    @Override
    public ActivityAdminUsersModel toModel(ActivityAdminUsersDTO entity) {
        ActivityAdminUsersModel activityModel = new ActivityAdminUsersModel(entity);
        Link get = linkTo(methodOn(AdminController.class).findActivity(entity.getId())).withRel(GET_REL);
        activityModel.add(get);
        return activityModel;
    }
}
