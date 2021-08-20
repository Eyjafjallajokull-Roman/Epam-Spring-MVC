package epam.com.springBoot.dto.activity;


import com.fasterxml.jackson.annotation.JsonInclude;
import epam.com.springBoot.validator.annotation.TimeConstraint;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;

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
public class ActivityAdminDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    @NotNull(message = "name shouldn`t be empty")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Timestamp startTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Timestamp endTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank(message = "type of activity shouldn`t be empty")
    private String typeOfActivity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long createdByUserId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String descriptionEng;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String descriptionRus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityAdminDTO that = (ActivityAdminDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createdByUserId, that.createdByUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endTime, createdByUserId);
    }

    public ActivityAdminDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public ActivityAdminDTO setName(String name) {
        this.name = name;
        return this;
    }

    public ActivityAdminDTO setStartTime(Timestamp startTime) {
        this.startTime = startTime;
        return this;
    }

    public ActivityAdminDTO setEndTime(Timestamp endTime) {
        this.endTime = endTime;
        return this;
    }

    public ActivityAdminDTO setTypeOfActivity(String typeOfActivity) {
        this.typeOfActivity = typeOfActivity;
        return this;
    }

    public ActivityAdminDTO setDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
        return this;
    }

    public ActivityAdminDTO setDescriptionRus(String descriptionRus) {
        this.descriptionRus = descriptionRus;
        return this;
    }

    public ActivityAdminDTO setCreatedByUserId(Long createdByUserId) {
        this.createdByUserId = createdByUserId;
        return this;
    }

    public ActivityAdminDTO setStatus(String status) {
        this.status = status;
        return this;
    }
}
