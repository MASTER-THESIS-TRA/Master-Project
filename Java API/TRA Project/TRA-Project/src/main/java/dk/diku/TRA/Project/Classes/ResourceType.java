package dk.diku.TRA.Project.Classes;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table(name = "RESOURCE_TYPE")
@Getter
@Setter
@Entity
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class ResourceType {
    @Id
    @GeneratedValue(generator = "uuid2") @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "RESOURCE_TYPE_ID", unique = true, nullable = false, length =36)
    private String id;

    @Column(name = "Name", unique = true, nullable = false)
    private String name;

    @Column(name = "Weight", nullable = false)
    private int weight;
}
