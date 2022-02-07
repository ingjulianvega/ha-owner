package ingjulianvega.ximic.haowner.services.feign;

import ingjulianvega.ximic.haowner.services.OwnerServiceImpl;
import ingjulianvega.ximic.haowner.web.model.response.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Profile("local-discovery")
@FeignClient(name = "HA-PERSON")
public interface PersonServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET,value = OwnerServiceImpl.PERSON_BY_ID_PATH)
    ResponseEntity<PersonDto> getById(@PathVariable UUID id);

}