package epam.com.springBoot.model;


import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class Activity {
    private int id;
    private String name;
    private Timestamp startTime;
    private Timestamp endTime;
    private String descriptionEng;
    private String descriptionRus;
    private TypeOfActivity typeOfActivity;
    private Status status;
    private int createdByUserID;
    private int oldActivityId;
    private Set<Integer> usersId;
}
