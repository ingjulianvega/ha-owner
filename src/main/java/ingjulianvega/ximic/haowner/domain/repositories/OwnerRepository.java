package ingjulianvega.ximic.haowner.domain.repositories;

import ingjulianvega.ximic.haowner.domain.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface OwnerRepository extends JpaRepository<OwnerEntity, UUID>, JpaSpecificationExecutor<OwnerEntity> {
    List<OwnerEntity> findAllByOrderByPersonId();
}
