package epam.com.springBoot.controller.assembler;

import epam.com.springBoot.controller.ActivityController;
import epam.com.springBoot.controller.model.ActivityModel;
import epam.com.springBoot.dto.activity.ActivityAdminDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ActivityAssembler extends RepresentationModelAssemblerSupport<ActivityAdminDTO, ActivityModel> {

    public static final String GET_REL = "get_activity";
    public static final String CREATE_ACTIVITY = "create_activity";
    public static final String UPDATE_ACTIVITY = "update_activity";
    public static final String DELETE_ACTIVITY = "delete_activity";

    public ActivityAssembler() {
        super(ActivityController.class, ActivityModel.class);
    }

    @Override
    public ActivityModel toModel(ActivityAdminDTO entity) {
        ActivityModel activityModel = new ActivityModel(entity);
        Link get = linkTo(methodOn(ActivityController.class).getActivity(entity.getId())).withRel(GET_REL);
        Link create = linkTo(methodOn(ActivityController.class).createActivity(entity)).withRel(CREATE_ACTIVITY);
        Link update = linkTo(methodOn(ActivityController.class).updateActivity(entity, entity.getId())).withRel(UPDATE_ACTIVITY);
        Link setOnDelete = linkTo(methodOn(ActivityController.class).setOnDelete(entity.getId())).withRel(DELETE_ACTIVITY);
        activityModel.add(get, create, update, setOnDelete);
        return activityModel;
    }
}
