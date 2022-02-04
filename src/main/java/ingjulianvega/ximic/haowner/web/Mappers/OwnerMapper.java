package ingjulianvega.ximic.haowner.web.Mappers;


import ingjulianvega.ximic.haowner.domain.OwnerEntity;
import ingjulianvega.ximic.haowner.web.model.OwnerDto;
import ingjulianvega.ximic.haowner.web.model.OwnerExtendedDto;
import ingjulianvega.ximic.haowner.web.model.response.PersonDto;
import ingjulianvega.ximic.haowner.web.model.response.PetDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(uses = {DateMapper.class})
public interface OwnerMapper {
    OwnerDto ownerEntityToOwnerDto(OwnerEntity ownerEntity);

    OwnerEntity ownerDtoToOwnerEntity(OwnerDto ownerDto);

    ArrayList<OwnerDto> ownerEntityListToOwnerDtoList(List<OwnerEntity> ownerEntityList);

}

