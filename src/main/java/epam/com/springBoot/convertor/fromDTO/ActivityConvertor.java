package epam.com.springBoot.convertor.fromDTO;

import epam.com.springBoot.dto.activity.ActivityAdminDTO;
import epam.com.springBoot.model.Activity;
import epam.com.springBoot.model.TypeOfActivity;
import org.springframework.core.convert.converter.Converter;

public class ActivityConvertor implements Converter<ActivityAdminDTO, Activity> {
    @Override
    public Activity convert(ActivityAdminDTO dto) {
        return new Activity()
                .setDescriptionEng(dto.getDescriptionEng())
                .setDescriptionRus(dto.getDescriptionRus())
                .setEndTime(dto.getEndTime())
                .setStartTime(dto.getStartTime())
                .setName(dto.getName())
                .setTypeOfActivity(TypeOfActivity.valueOf(dto.getTypeOfActivity()))
                .setCreatedByUserId(dto.getCreatedByUserId());
    }
}
