package ingjulianvega.ximic.haowner.services;

import ingjulianvega.ximic.haowner.web.model.Owner;
import ingjulianvega.ximic.haowner.web.model.OwnerDto;
import ingjulianvega.ximic.haowner.web.model.OwnerExtendedDto;
import ingjulianvega.ximic.haowner.web.model.OwnerList;

import java.util.UUID;

public interface OwnerService {
    OwnerList get();

    OwnerExtendedDto getById(UUID id);

    void create(Owner owner);

    void updateById(UUID id, Owner owner);

    void deleteById(UUID id);
}
