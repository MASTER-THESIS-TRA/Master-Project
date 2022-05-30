package dk.diku.TRA.Project.Dtos.PersistStateless;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GiveResourceDto implements Serializable {
    String email;
    String resourceType;
    int amount;
}
