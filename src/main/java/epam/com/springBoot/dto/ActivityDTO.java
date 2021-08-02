package epam.com.springBoot.dto;

import epam.com.springBoot.dto.group.OnCreate;
import epam.com.springBoot.model.TypeOfActivity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
public class ActivityDTO {
    @NotBlank(message = "name shouldn`t be empty", groups = OnCreate.class)
    private String name;
    @NotBlank(message = "'startTime' shouldn`t be empty", groups = OnCreate.class)
    private Timestamp startTime;
    @NotBlank(message = "name shouldn`t be empty", groups = OnCreate.class)
    private Timestamp endTime;

    private String typeOfActivity;
    private String descriptionEng;
    private String descriptionRus;
}
