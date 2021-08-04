package epam.com.springBoot.controller.assembler;

import epam.com.springBoot.controller.ActivityController;
import epam.com.springBoot.controller.model.ActivityModel;
import epam.com.springBoot.dto.ActivityDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ActivityAssembler extends RepresentationModelAssemblerSupport<ActivityDTO, ActivityModel> {

    public static final String GET_REL = "get_activity";
    public static final String CREATE_ACTIVITY = "create_activity";
    public static final String UPDATE_ACTIVITY = "update_activity";
    public static final String DELETE_ACTIVITY = "delete_activity";

    public ActivityAssembler() {
        super(ActivityController.class, ActivityModel.class);
    }

    @Override
    public ActivityModel toModel(ActivityDTO entity) {
        ActivityModel activityModel = new ActivityModel(entity);
        Link get = linkTo(methodOn(ActivityController.class).getActivity(entity.getId())).withRel(GET_REL);
        Link create = linkTo(methodOn(ActivityController.class).createActivity(entity)).withRel(CREATE_ACTIVITY);
        Link update = linkTo(methodOn(ActivityController.class).updateActivity(entity, entity.getId())).withRel(UPDATE_ACTIVITY);
        Link delete = linkTo(methodOn(ActivityController.class).deleteActivity(entity.getId())).withRel(DELETE_ACTIVITY);
        activityModel.add(get, create, update, delete);
        return activityModel;
    }
}
