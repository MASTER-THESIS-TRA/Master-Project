package dk.diku.TRA.Project.Classes;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "EVENT")
@Getter
@Setter
@Entity
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(generator = "uuid2") @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "EVENT_ID", unique = true, nullable = false, length =36)
    private String id;

    @Column(name = "EVENT_TYPE", nullable = false)
    private String eventType;

    @Column(name = "AGENT_ID", nullable = false, length = 36)
    private String agentId;

    @Column(name = "TIME", nullable = false)
    private LocalDateTime time;

    @Column(name = "BODY", length = 5000)
    private String body;
}
