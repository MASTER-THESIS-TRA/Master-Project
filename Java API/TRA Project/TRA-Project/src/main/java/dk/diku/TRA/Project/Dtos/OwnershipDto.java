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
public class OwnershipDto implements Serializable {
    @Id
    @Column(name = "AGENT_ID", nullable = false,unique = true,length=36)
    private String agentId;

    @Column(name = "RESOURCE", columnDefinition = "TEXT")
    private String resource;
}