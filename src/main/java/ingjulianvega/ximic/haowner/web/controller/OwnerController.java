package ingjulianvega.ximic.haowner.web.controller;

import ingjulianvega.ximic.haowner.services.OwnerService;
import ingjulianvega.ximic.haowner.web.model.Owner;
import ingjulianvega.ximic.haowner.web.model.OwnerExtendedDto;
import ingjulianvega.ximic.haowner.web.model.OwnerList;
import ingjulianvega.ximic.haowner.web.model.PetList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OwnerController implements OwnerI {
    private final OwnerService ownerService;

    @Override
    public ResponseEntity<OwnerList> get(Boolean usingCache) {
        return new ResponseEntity<>(ownerService.get(usingCache), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OwnerExtendedDto> getById(@NotNull UUID id) {
        return new ResponseEntity<>(ownerService.getById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PetList> getPetsByPersonId(@NotNull UUID personId) {
        return new ResponseEntity<>(ownerService.getPetsByPersonId(personId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> create(@NotNull @Valid Owner owner) {
        ownerService.create(owner);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> updateById(@NotNull UUID id, @NotNull @Valid Owner owner) {
        ownerService.updateById(id, owner);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteById(@NotNull UUID id) {
        ownerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}