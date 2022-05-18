package dk.diku.TRA.Project.Dtos;

import dk.diku.TRA.Project.Dtos.keys.CreditKey;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Table(name = "CREDIT")
@Getter
@Setter
@Entity
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CreditKey.class)
public class CreditDto implements Serializable {
    @Id
    @Column(name = "AGENT_ID", nullable = false,unique = true,length=36)
    private String agentId;

    @Id
    @Column(name = "RESOURCE_TYPE")
    private String resourceType;

    @Column(name = "AMOUNT", nullable = false)
    private int amount;
}
