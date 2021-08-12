package epam.com.springBoot.util;

import epam.com.springBoot.dto.activity.ActivityAdminDTO;
import epam.com.springBoot.model.Activity;
import epam.com.springBoot.model.Status;
import epam.com.springBoot.model.TypeOfActivity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ActivityDataUtil {
    public static final Long ID = 30000L;
    public static final String NAME = "NAME";
    public static final String DESCRIPTION_ENG = "DESCRIPTION_ENG";
    public static final String DESCRIPTION_RU = "DESCRIPTION_RU";
    public static final TypeOfActivity TYPE_OF_ACTIVITY = TypeOfActivity.EVENT;
    public static final Status STATUS = Status.ON_CHECK;
    public static final Timestamp START_TIME = Timestamp.valueOf("2018-12-12 01:02:03.123456789");
    public static final Timestamp END_TIME = Timestamp.valueOf("2019-12-12 01:02:03.123456789");

    public static Activity createActivity() {
        return new Activity()
                .setId(ID)
                .setStatus(STATUS)
                .setTypeOfActivity(TYPE_OF_ACTIVITY)
                .setName(NAME)
                .setDescriptionEng(DESCRIPTION_ENG)
                .setDescriptionRus(DESCRIPTION_RU)
                .setStartTime(START_TIME)
                .setEndTime(END_TIME);
    }

    public static ActivityAdminDTO createActivityDto() {
        return new ActivityAdminDTO()
                .setId(ID)
                .setTypeOfActivity(String.valueOf(TYPE_OF_ACTIVITY))
                .setName(NAME)
                .setDescriptionEng(DESCRIPTION_ENG)
                .setDescriptionRus(DESCRIPTION_RU)
                .setStartTime(START_TIME)
                .setEndTime(END_TIME);
    }
}
