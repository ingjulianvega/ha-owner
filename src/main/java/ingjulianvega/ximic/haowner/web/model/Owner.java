package ingjulianvega.ximic.haowner.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Owner {

  @NotBlank
  private String personId = null;
  @NotBlank
  private String petId = null;

}

