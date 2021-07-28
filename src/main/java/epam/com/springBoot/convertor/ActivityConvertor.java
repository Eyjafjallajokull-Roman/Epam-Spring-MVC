package epam.com.springBoot.convertor;

import epam.com.springBoot.dto.ActivityDTO;
import epam.com.springBoot.model.Activity;
import org.springframework.core.convert.converter.Converter;

public class ActivityConvertor implements Converter<ActivityDTO, Activity> {
    @Override
    public Activity convert(ActivityDTO dto) {
        return new Activity()
                .setDescriptionEng(dto.getDescriptionEng())
                .setDescriptionRus(dto.getDescriptionRus())
                .setEndTime(dto.getEndTime())
                .setStartTime(dto.getStartTime())
                .setName(dto.getName());
    }
}
