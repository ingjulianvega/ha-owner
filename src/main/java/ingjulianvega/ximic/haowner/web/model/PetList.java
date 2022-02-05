package ingjulianvega.ximic.haowner.web.model;

import ingjulianvega.ximic.haowner.web.model.response.PetDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetList implements Serializable {

  static final long serialVersionUID = 7561423550901684695L;

  public ArrayList<PetDto> petList;

}

