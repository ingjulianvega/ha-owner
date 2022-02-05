package ingjulianvega.ximic.haowner.web.model;

import ingjulianvega.ximic.haowner.web.model.response.PersonDto;
import ingjulianvega.ximic.haowner.web.model.response.PetDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OwnerExtendedDto implements Serializable {

    static final long serialVersionUID = -4283475280502062886L;

    @Null
    private UUID id;
    @NotBlank
    private PersonDto person;
    @NotBlank
    private PetDto pet;
}
