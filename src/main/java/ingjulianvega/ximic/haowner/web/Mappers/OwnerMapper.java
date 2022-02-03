package ingjulianvega.ximic.haowner.web.Mappers;


import ingjulianvega.ximic.haowner.domain.OwnerEntity;
import ingjulianvega.ximic.haowner.web.model.OwnerDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(uses = DateMapper.class)
public interface OwnerMapper {
    OwnerDto personEntityToPersonDto(OwnerEntity ownerEntity);

    OwnerEntity personDtoToPersonEntity(OwnerDto OwnerDto);

    ArrayList<OwnerDto> personEntityListToPersonDtoList(List<OwnerEntity> ownerEntityList);
}

