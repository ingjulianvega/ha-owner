package ingjulianvega.ximic.haowner.services;


import ingjulianvega.ximic.haowner.configuration.ErrorCodeMessages;
import ingjulianvega.ximic.haowner.domain.OwnerEntity;
import ingjulianvega.ximic.haowner.domain.repositories.OwnerRepository;
import ingjulianvega.ximic.haowner.exception.OwnerException;
import ingjulianvega.ximic.haowner.services.feign.PersonServiceFeignClient;
import ingjulianvega.ximic.haowner.services.feign.PetServiceFeignClient;
import ingjulianvega.ximic.haowner.web.Mappers.OwnerExtendedMapper;
import ingjulianvega.ximic.haowner.web.Mappers.OwnerMapper;
import ingjulianvega.ximic.haowner.web.model.Owner;
import ingjulianvega.ximic.haowner.web.model.OwnerDto;
import ingjulianvega.ximic.haowner.web.model.OwnerExtendedDto;
import ingjulianvega.ximic.haowner.web.model.OwnerExtendedList;
import ingjulianvega.ximic.haowner.web.model.OwnerList;
import ingjulianvega.ximic.haowner.web.model.PetList;
import ingjulianvega.ximic.haowner.web.model.response.PersonDto;
import ingjulianvega.ximic.haowner.web.model.response.PetDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class OwnerServiceImpl implements OwnerService {

    //***Feing constants***

    //Person
    public static final String PERSON_BY_ID_PATH = "/happy-animals/v1/person/{id}";
    //Pet
    public static final String PET_BY_ID_PATH = "/happy-animals/v1/pet/{id}";

    //Feign components
    private final PersonServiceFeignClient personServiceFeignClient;
    private final PetServiceFeignClient petServiceFeignClient;

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;
    private final OwnerExtendedMapper ownerExtendedMapper;

    @Cacheable(cacheNames = "ownerListCache")
    @Override
    public OwnerList get() {
        log.debug("get()...");
        return OwnerList
                .builder()
                .ownerList(ownerMapper.ownerEntityListToOwnerDtoList(ownerRepository.findAllByOrderByPersonId()))
                .build();
    }

    @Cacheable(cacheNames = "ownerCache")
    @Override
    public OwnerExtendedDto getById(UUID id) {
        log.debug("getById()...");
        OwnerDto ownerDto = ownerMapper.ownerEntityToOwnerDto(ownerRepository.findById(id).orElseThrow(() -> OwnerException
                .builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .apiCode(ErrorCodeMessages.OWNER_NOT_FOUND_API_CODE)
                .error(ErrorCodeMessages.OWNER_NOT_FOUND_ERROR)
                .message(ErrorCodeMessages.OWNER_NOT_FOUND_MESSAGE)
                .solution(ErrorCodeMessages.OWNER_NOT_FOUND_SOLUTION)
                .build()));

        //Consulta la persona
        ResponseEntity<PersonDto>  personDto = personServiceFeignClient.getById(ownerDto.getPersonId());
        ResponseEntity<PetDto> petDto = petServiceFeignClient.getById(ownerDto.getPetId());

        OwnerExtendedDto ownerExtendedDto = ownerExtendedMapper.toOwnerExtendedDto(ownerDto,personDto.getBody(),petDto.getBody());

        return ownerExtendedDto;
    }

    @Override
    public void create(Owner owner) {
        log.debug("create()...");
        ownerMapper.ownerEntityToOwnerDto(
                ownerRepository.save(
                        ownerMapper.ownerDtoToOwnerEntity(
                                OwnerDto
                                        .builder()
                                        //.personId(owner.getPersonId()) //TODO fix because of feign change
                                        //.petId(owner.getPetId())//TODO fix because of feign change
                                        .build())));
    }

    @Override
    public void updateById(UUID id, Owner owner) {
        log.debug("updateById...");
        OwnerEntity ownerEntity = ownerRepository.findById(id).orElseThrow(() -> OwnerException
                .builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .apiCode(ErrorCodeMessages.OWNER_NOT_FOUND_API_CODE)
                .error(ErrorCodeMessages.OWNER_NOT_FOUND_ERROR)
                .message(ErrorCodeMessages.OWNER_NOT_FOUND_MESSAGE)
                .solution(ErrorCodeMessages.OWNER_NOT_FOUND_SOLUTION)
                .build());
        //ownerEntity.setPersonId(owner.getPersonId()); //TODO fix because of feign change
        //ownerEntity.setPetId(owner.getPetId()); //TODO fix because of feign change

        ownerRepository.save(ownerEntity);
    }

    @Override
    public void deleteById(UUID id) {
        log.debug("deleteById...");
        ownerRepository.deleteById(id);
    }

    @Override
    public PetList getPetsByPersonId(UUID personId) {
        //Buscar los ids de las mascotas por personId
        List<OwnerEntity> ownerList = ownerRepository.findAllByPersonId(personId);
        //Buscar los datos de cada una de las mascotas
        ArrayList<PetDto> petArray = new ArrayList<>();
        ownerList.parallelStream().forEach(pet -> {
            ResponseEntity<PetDto> petDto = petServiceFeignClient.getById(pet.getPetId());
            petArray.add(petDto.getBody());
        });
        return PetList.builder().petList(petArray).build();
    }
}