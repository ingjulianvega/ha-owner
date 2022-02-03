package ingjulianvega.ximic.haowner.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Owner")
@Entity
public class OwnerEntity extends BaseEntity{

    @Column
    private String personId;
    @Column
    private String petId;
}
