package dk.diku.TRA.Project.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransformDto {
    List<ResourceDto> input;
    List<ResourceDto> output;
    String name;
}
