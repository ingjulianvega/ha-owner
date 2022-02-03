package ingjulianvega.ximic.haowner.web.model;

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
public class OwnerDto implements Serializable {
    static final long serialVersionUID = 3288070695886353002L;

    @Null
    private UUID id;
    @NotBlank
    private String personId;
    @NotBlank
    private String petId;

}

