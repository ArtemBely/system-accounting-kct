package cz.kct.data.mapper;

import cz.kct.data.dto.DzcDto;
import cz.kct.data.entity.DzcEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DzcMapper {
    private final ModelMapper modelMapper;

    public DzcEntity mapToEntity(DzcDto dto) {
        return modelMapper.map(dto, DzcEntity.class).toBuilder().build();
    }

    public DzcDto mapToDto(DzcEntity entity) {
        return modelMapper.map(entity, DzcDto.class).toBuilder().build();
    }
}
