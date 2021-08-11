package epam.com.springBoot.dto;


import epam.com.springBoot.dto.group.OnCreate;
import epam.com.springBoot.validator.annotation.TimeConstraint;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@TimeConstraint.List({
        @TimeConstraint(
                endTime = "endTime",
                startTime = "startTime",
                message = "Incorrect data, check the time you enter " +
                        "(The start time must be greater than your current time and the end time must be greater than the start time)."
        )
})
@Data
public class ActivityDTO {

    private Long id;
    @NotNull(message = "name shouldn`t be empty", groups = OnCreate.class)
    private String name;
    @NotNull(message = "startTime shouldn`t be empty", groups = OnCreate.class)
    private Timestamp startTime;
    @NotNull(message = "endTime shouldn`t be empty", groups = OnCreate.class)
    private Timestamp endTime;
    private String status;
    @NotBlank(message = "type of activity shouldn`t be empty")
    private String typeOfActivity;
    private Long createdByUserId;
    private String descriptionEng;
    private String descriptionRus;

    public ActivityDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public ActivityDTO setName(String name) {
        this.name = name;
        return this;
    }

    public ActivityDTO setStartTime(Timestamp startTime) {
        this.startTime = startTime;
        return this;
    }

    public ActivityDTO setEndTime(Timestamp endTime) {
        this.endTime = endTime;
        return this;
    }

    public ActivityDTO setTypeOfActivity(String typeOfActivity) {
        this.typeOfActivity = typeOfActivity;
        return this;
    }

    public ActivityDTO setDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
        return this;
    }

    public ActivityDTO setDescriptionRus(String descriptionRus) {
        this.descriptionRus = descriptionRus;
        return this;
    }

    public ActivityDTO setCreatedByUserId(Long createdByUserId) {
        this.createdByUserId = createdByUserId;
        return this;
    }
}
