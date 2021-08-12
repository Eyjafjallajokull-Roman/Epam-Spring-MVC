package epam.com.springBoot.convertor;

import epam.com.springBoot.dto.activity.ActivityAdminDTO;
import epam.com.springBoot.model.Activity;
import org.springframework.core.convert.converter.Converter;

public class ActivityDTOConvertor implements Converter<Activity, ActivityAdminDTO> {
    @Override
    public ActivityAdminDTO convert(Activity activity) {
        return new ActivityAdminDTO()
                .setId(activity.getId())
                .setName(activity.getName())
                .setEndTime(activity.getEndTime())
                .setStartTime(activity.getStartTime())
                .setTypeOfActivity(activity.getTypeOfActivity().name())
                .setDescriptionRus(activity.getDescriptionRus())
                .setDescriptionEng(activity.getDescriptionEng())
                .setCreatedByUserId(activity.getCreatedByUserId());
    }
}
