package dk.diku.TRA.Project.Dtos;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "RESOURCETYPE")
@Getter
@Setter
@Entity
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class ResourceTypeDto implements Serializable {

    @Id
    @Column(name = "RESOURCE_TYPE_ID", unique = true, nullable = false, length = 36)
    public Integer id;

    @Column(name = "NAME", nullable = false, length = 100)
    public String name;

    @Column(name = "WEIGHT", nullable = false, length = 100)
    public String weight;

}
