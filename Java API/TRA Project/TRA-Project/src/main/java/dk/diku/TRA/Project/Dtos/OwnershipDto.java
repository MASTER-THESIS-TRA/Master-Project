package dk.diku.TRA.Project.Dtos;

import dk.diku.TRA.Project.Classes.Resource;
import dk.diku.TRA.Project.Dtos.keys.OwnershipKey;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "OWNERSHIPS")
@Getter
@Setter
@Entity
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(OwnershipKey.class)
public class OwnershipDto implements Serializable {
    @Id
    @Column(name = "AGENT_ID", nullable = false,unique = true,length=36)
    private String agentId;

    @Id
    @Column(name = "RESOURCE_TYPE")
    private String resourceType;

    @Column(name = "AMOUNT", nullable = false)
    private int amount;
}