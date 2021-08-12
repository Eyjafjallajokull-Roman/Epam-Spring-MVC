package epam.com.springBoot.controller.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import epam.com.springBoot.dto.activity.ActivityAdminUsersDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ActivityAdminUsersModel extends RepresentationModel<ActivityAdminUsersModel> {

    @JsonUnwrapped
    ActivityAdminUsersDTO activityAdminUsersDTO;
}
