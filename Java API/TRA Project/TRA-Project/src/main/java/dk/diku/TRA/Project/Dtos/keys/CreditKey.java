package dk.diku.TRA.Project.Dtos.keys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditKey implements Serializable {
    String agentId;
    String resourceType;
}

