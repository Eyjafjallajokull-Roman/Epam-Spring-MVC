package epam.com.springBoot.controller.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import epam.com.springBoot.dto.activity.ActivityAdminDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor

public class ActivityModel extends RepresentationModel<ActivityModel> {

    @JsonUnwrapped
    private ActivityAdminDTO activityAdminDTO;

}
