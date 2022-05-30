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
public class CreditDto implements Serializable {
    @Id
    @Column(name = "AGENT_ID", nullable = false,unique = true,length=36)
    private String agentId;

    @Column(name = "RESOURCE", columnDefinition = "TEXT",nullable = false)
    private String resource;
}
