package epam.com.springBoot.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
public class ActivityDTO {

    private Long id;
    @NotBlank(message = "name shouldn`t be empty")
    private String name;
    @NotBlank(message = "'startTime' shouldn`t be empty")
    private Timestamp startTime;
    @NotBlank(message = "endTime shouldn`t be empty")
    private Timestamp endTime;
    @NotBlank(message = "type of activity shouldn`t be empty")
    private String status;
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
