package dk.diku.TRA.Project.Dtos.PersistStateless;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "TRANSFORM")
@Getter
@Setter
@Entity
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class TransformDefinitions implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2") @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "TRANSFORM_ID", unique = true, nullable = false, length = 36)
    private String uuid;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "TRANSFORM", nullable = false,length = 1000)
    private String transform;

}
