package ingjulianvega.ximic.haowner.services;


import ingjulianvega.ximic.haowner.configuration.ErrorCodeMessages;
import ingjulianvega.ximic.haowner.domain.OwnerEntity;
import ingjulianvega.ximic.haowner.domain.repositories.OwnerRepository;
import ingjulianvega.ximic.haowner.exception.OwnerException;
import ingjulianvega.ximic.haowner.web.Mappers.OwnerMapper;
import ingjulianvega.ximic.haowner.web.model.Owner;
import ingjulianvega.ximic.haowner.web.model.OwnerDto;
import ingjulianvega.ximic.haowner.web.model.OwnerList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;


    @Cacheable(cacheNames = "ownerListCache")
    @Override
    public OwnerList get() {
        log.debug("get()...");
        return OwnerList
                .builder()
                .ownerList(ownerMapper.personEntityListToPersonDtoList(ownerRepository.findAllByOrderByPersonId()))
                .build();
    }

    @Cacheable(cacheNames = "ownerCache")
    @Override
    public OwnerDto getById(UUID id) {
        log.debug("getById()...");
        return ownerMapper.personEntityToPersonDto(
                ownerRepository.findById(id).orElseThrow(() -> OwnerException
                        .builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .apiCode(ErrorCodeMessages.OWNER_NOT_FOUND_API_CODE)
                        .error(ErrorCodeMessages.OWNER_NOT_FOUND_ERROR)
                        .message(ErrorCodeMessages.OWNER_NOT_FOUND_MESSAGE)
                        .solution(ErrorCodeMessages.OWNER_NOT_FOUND_SOLUTION)
                        .build()));
    }

    @Override
    public void create(Owner owner) {
        log.debug("create()...");
        ownerMapper.personEntityToPersonDto(
                ownerRepository.save(
                        ownerMapper.personDtoToPersonEntity(
                                OwnerDto
                                        .builder()
                                        .personId(owner.getPersonId())
                                        .petId(owner.getPetId()).
                                        build())));
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
        ownerEntity.setPersonId(owner.getPersonId());
        ownerEntity.setPetId(owner.getPetId());

        ownerRepository.save(ownerEntity);
    }

    @Override
    public void deleteById(UUID id) {
        log.debug("deleteById...");
        ownerRepository.deleteById(id);
    }
}