package dk.diku.TRA.Project.Dtos;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResourceTypeDto {
    public String name;
    public int weight;
}
