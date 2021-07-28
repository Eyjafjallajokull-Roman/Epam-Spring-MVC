package epam.com.springBoot.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ActivityDTO {
    private String name;
    private Timestamp startTime;
    private Timestamp endTime;
    private String descriptionEng;
    private String descriptionRus;
}
