package ingjulianvega.ximic.haowner.web.model.response;

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
public class PersonDto implements Serializable {
    static final long serialVersionUID = -6490482264970539957L;

    @Null
    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private String documentNumber;

}

