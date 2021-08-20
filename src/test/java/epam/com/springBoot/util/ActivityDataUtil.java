package epam.com.springBoot.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import epam.com.springBoot.dto.activity.ActivityAdminDTO;
import epam.com.springBoot.dto.activity.ActivityAdminUsersDTO;
import epam.com.springBoot.dto.user.UserDTO;
import epam.com.springBoot.model.Activity;
import epam.com.springBoot.model.Status;
import epam.com.springBoot.model.TypeOfActivity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ActivityDataUtil {
    public static final Long ID = 3000L;
    public static final String NAME = "NAME";
    public static final String DESCRIPTION_ENG = "DESCRIPTION_ENG";
    public static final String DESCRIPTION_RU = "DESCRIPTION_RU";
    public static final String TYPE_OF_ACTIVITY = TypeOfActivity.EVENT.name();
    public static final Status STATUS = Status.ON_CHECK;
    public static final Timestamp START_TIME = Timestamp.valueOf("2022-12-12 01:02:03.123456789");
    public static final Timestamp END_TIME = Timestamp.valueOf("2023-12-12 01:02:03.123456789");
    public static final Long CREATED_ID = 3000l;

    public static Activity createActivity() {
        return new Activity()
                .setId(ID)
                .setStatus(STATUS)
                .setTypeOfActivity(TypeOfActivity.EVENT)
                .setName(NAME)
                .setDescriptionEng(DESCRIPTION_ENG)
                .setDescriptionRus(DESCRIPTION_RU)
                .setStartTime(START_TIME)
                .setEndTime(END_TIME)
                .setCreatedByUserId(CREATED_ID);
    }

    public static ActivityAdminDTO createActivityDto() {
        return new ActivityAdminDTO()
                .setId(ID)
                .setTypeOfActivity(TYPE_OF_ACTIVITY)
                .setName(NAME)
                .setDescriptionEng(DESCRIPTION_ENG)
                .setDescriptionRus(DESCRIPTION_RU)
                .setStartTime(START_TIME)
                .setEndTime(END_TIME)
                .setCreatedByUserId(CREATED_ID);
    }

    public static ActivityAdminUsersDTO createActivityUsersDto() {
        return new ActivityAdminUsersDTO()
                .setId(ID)
                .setTypeOfActivity(TYPE_OF_ACTIVITY)
                .setName(NAME)
                .setDescriptionEng(DESCRIPTION_ENG)
                .setDescriptionRus(DESCRIPTION_RU)
                .setStartTime(START_TIME)
                .setEndTime(END_TIME)
                .setCreatedByUserId(CREATED_ID);
    }

    public static String jsonMapper(ActivityAdminDTO dto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(dto);
    }
}
