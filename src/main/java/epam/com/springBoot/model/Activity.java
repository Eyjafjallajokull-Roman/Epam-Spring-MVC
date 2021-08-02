package epam.com.springBoot.model;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Timestamp startTime;
    private Timestamp endTime;
    private String descriptionEng;
    private String descriptionRus;
    @Enumerated(EnumType.STRING)
    private TypeOfActivity typeOfActivity;
    @Enumerated(EnumType.STRING)
    private Status status;
    private int createdByUserID;
    private int oldActivityId;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "users_activity", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id"))
    private List<User> users;

    public Activity setId(Long id) {
        this.id = id;
        return this;
    }

    public Activity setName(String name) {
        this.name = name;
        return this;
    }

    public Activity setStartTime(Timestamp startTime) {
        this.startTime = startTime;
        return this;
    }

    public Activity setEndTime(Timestamp endTime) {
        this.endTime = endTime;
        return this;
    }

    public Activity setDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
        return this;
    }

    public Activity setDescriptionRus(String descriptionRus) {
        this.descriptionRus = descriptionRus;
        return this;
    }

    public Activity setTypeOfActivity(TypeOfActivity typeOfActivity) {
        this.typeOfActivity = typeOfActivity;
        return this;
    }

    public Activity setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Activity setCreatedByUserID(int createdByUserID) {
        this.createdByUserID = createdByUserID;
        return this;
    }

    public Activity setOldActivityId(int oldActivityId) {
        this.oldActivityId = oldActivityId;
        return this;
    }

    public Activity setUsers(List<User> users) {
        this.users = users;
        return this;
    }
}
