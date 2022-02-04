package ingjulianvega.ximic.haowner.services.feign;
import ingjulianvega.ximic.haowner.services.OwnerServiceImpl;
import ingjulianvega.ximic.haowner.web.model.response.PersonDto;
import ingjulianvega.ximic.haowner.web.model.response.PetDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

//@FeignClient(name = "mssc-asu-administration-method")
@FeignClient(name = "PetServiceFeignClient", url = "http://localhost:8081")
public interface PetServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET,value = OwnerServiceImpl.PET_BY_ID_PATH)
    ResponseEntity<PetDto> getById(@PathVariable UUID id);
}
