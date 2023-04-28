package cz.kct.data.mapper;

import cz.kct.data.dto.TimeSheetDto;
import cz.kct.data.entity.TimeSheetEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component

public class ExcelMapper {
    private final ModelMapper modelMapper;

    public TimeSheetEntity mapToEntity(TimeSheetDto dto) {
        return modelMapper.map(dto, TimeSheetEntity.class).toBuilder().build();
    }

    public TimeSheetDto mapToDto(TimeSheetEntity entity) {
        return modelMapper.map(entity, TimeSheetDto.class).toBuilder().build();
    }
}