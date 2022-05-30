package dk.diku.TRA.Project.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DoTransformDto {
    private String sender;
    private String transform;
    private int amount;
}
