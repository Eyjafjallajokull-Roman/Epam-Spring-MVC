package epam.com.springBoot.dto.activity;

import epam.com.springBoot.dto.group.OnCreate;
import epam.com.springBoot.dto.user.UserDTO;
import epam.com.springBoot.validator.annotation.TimeConstraint;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


@TimeConstraint.List({
        @TimeConstraint(
                endTime = "endTime",
                startTime = "startTime",
                typeOfActivity = "typeOfActivity",
                message = "Incorrect data, check the time you enter " +
                        "(The start time must be greater than your current time and the end time must be greater than the start time)."
        )
})
@Data
public class ActivityAdminUsersDTO {
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
    private List<UserDTO> userSet;

    public ActivityAdminUsersDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public ActivityAdminUsersDTO setName(String name) {
        this.name = name;
        return this;
    }

    public ActivityAdminUsersDTO setStartTime(Timestamp startTime) {
        this.startTime = startTime;
        return this;
    }

    public ActivityAdminUsersDTO setEndTime(Timestamp endTime) {
        this.endTime = endTime;
        return this;
    }

    public ActivityAdminUsersDTO setTypeOfActivity(String typeOfActivity) {
        this.typeOfActivity = typeOfActivity;
        return this;
    }

    public ActivityAdminUsersDTO setDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
        return this;
    }

    public ActivityAdminUsersDTO setDescriptionRus(String descriptionRus) {
        this.descriptionRus = descriptionRus;
        return this;
    }

    public ActivityAdminUsersDTO setCreatedByUserId(Long createdByUserId) {
        this.createdByUserId = createdByUserId;
        return this;
    }

    public ActivityAdminUsersDTO setStatus(String status) {
        this.status = status;
        return this;
    }

    public ActivityAdminUsersDTO setUserList(List<UserDTO> userSet) {
        this.userSet = userSet;
        return this;
    }
}
