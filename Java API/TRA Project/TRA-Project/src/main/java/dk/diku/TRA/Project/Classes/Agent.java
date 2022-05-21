package dk.diku.TRA.Project.Classes;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.io.Serializable;

@Table(name = "AGENT")
@Getter
@Setter
@Entity
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class Agent implements Serializable{

    @Id
    @GeneratedValue(generator = "uuid2") @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "AGENT_ID", unique = true, nullable = false, length = 36)
    private String uuid;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "EMAIL", unique = true, nullable = false, length = 100)
    private String email;

    @Column (name = "PASSWORD", nullable=false, length = 100)
    private String password;
}
