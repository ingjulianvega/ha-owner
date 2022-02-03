package ingjulianvega.ximic.haowner.bootstrap;


import ingjulianvega.ximic.haowner.domain.OwnerEntity;
import ingjulianvega.ximic.haowner.domain.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class OwnerLoader implements CommandLineRunner {

    private final OwnerRepository ownerRepository;

    @Override
    public void run(String... args) throws Exception {
        if (ownerRepository.count() == 0) {
            loadMaritalStatusObjects();
        }
    }

    private void loadMaritalStatusObjects() {
        ownerRepository.saveAll(Arrays.asList(
                OwnerEntity.builder().personId("02438b2e-df71-4427-8ed2-6ff475fd66ab").petId("134d2db6-e9fe-4e59-99b2-75a868ab9a9b").build(),
                OwnerEntity.builder().personId("01e3e1e0-d4e3-4379-89c1-4c6bd7a686aa").petId("1a8c65e4-d259-4350-95e8-2653441c36f2").build(),
                OwnerEntity.builder().personId("01652555-24d8-4b8b-b7ec-211a0e5780ed").petId("97b193c5-42ce-4f0e-b22b-a8ffea29ad60").build(),
                OwnerEntity.builder().personId("01652555-24d8-4b8b-b7ec-211a0e5780ed").petId("df99f218-88ab-4275-842c-d8b320314e5d").build(),
                OwnerEntity.builder().personId("01652555-24d8-4b8b-b7ec-211a0e5780ed").petId("78d24bc7-c594-427d-b896-f072a4a69c13").build(),
                OwnerEntity.builder().personId("01e3e1e0-d4e3-4379-89c1-4c6bd7a686aa").petId("8ab9712b-0686-46c4-90aa-84c156c41adc").build(),
                OwnerEntity.builder().personId("02438b2e-df71-4427-8ed2-6ff475fd66ab").petId("c588c06e-1127-4ffb-89cd-7fc679f29017").build()
        ));
    }
}
