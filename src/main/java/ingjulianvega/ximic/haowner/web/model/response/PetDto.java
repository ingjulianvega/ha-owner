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
public class PetDto implements Serializable {
    static final long serialVersionUID = 3288070695886353002L;

    @Null
    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private String type;

}

