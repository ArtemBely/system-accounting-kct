package cz.kct.data.mapper;

import cz.kct.data.dto.ExcelDto;
import cz.kct.data.entity.ExcelEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component

public class ExcelMapper {
    private final ModelMapper modelMapper;
    public ExcelEntity mapToEntity(ExcelDto dto){
        return modelMapper.map(dto, ExcelEntity.class).toBuilder().build();
    }
    public ExcelDto mapToDto(ExcelEntity entity){
        return modelMapper.map(entity, ExcelDto.class).toBuilder().build();
    }
}