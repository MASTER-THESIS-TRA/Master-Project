package dk.diku.TRA.Project.Dtos.keys;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class CreditKey implements Serializable {
    String agentId;
    String resourceType;
}

