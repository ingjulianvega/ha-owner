package ingjulianvega.ximic.haowner.web.Mappers;

import ingjulianvega.ximic.haowner.web.model.OwnerDto;
import ingjulianvega.ximic.haowner.web.model.OwnerExtendedDto;
import ingjulianvega.ximic.haowner.web.model.response.PersonDto;
import ingjulianvega.ximic.haowner.web.model.response.PetDto;
import org.springframework.stereotype.Component;

@Component
public class OwnerExtendedMapper {
    public OwnerExtendedDto toOwnerExtendedDto(OwnerDto ownerDto, PersonDto persondto, PetDto petDto) {
    return OwnerExtendedDto.builder()
        .id(ownerDto.getId())
        .person(
            PersonDto.builder()
                .id(persondto.getId())
                .documentNumber(persondto.getDocumentNumber())
                .name(persondto.getName())
                .build())
        .pet(
            PetDto.builder()
                .id(petDto.getId())
                .type(petDto.getType())
                .name(petDto.getName())
                .build())
        .build();
    }
}
