package dk.diku.TRA.Project.Dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LocationDto {
    private String name;
    private double longitude;
    private double latitude;
}
