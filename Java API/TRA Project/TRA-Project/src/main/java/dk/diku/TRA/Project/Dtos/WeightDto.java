package dk.diku.TRA.Project.Dtos;


import dk.diku.TRA.Project.Dtos.keys.OwnershipKey;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "WEIGHTS")
@Getter
@Setter
@Entity
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class WeightDto implements Serializable {
    @Id
    @Column(name = "TYPE", nullable = false,unique = true)
    private String resourceType;

    @Column(name = "WEIGHT", nullable = false)
    private int weight;
}
