package dk.diku.TRA.Project.Classes;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "LOCATION")
@Getter
@Setter
@Entity
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class GeoCoordinate implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2") @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "LOCATION_ID", unique = true, nullable = false, length =36)
    public String id;

    @Column(name = "NAME", nullable = false)
    public String name;
    @Column(name = "LONGITUDE", nullable = false)
    public double longitude;
    @Column(name = "LATITUDE", nullable = false)
    public double latitude;

    @Override
    public String toString(){
        return "("+longitude + ", " + latitude + ")";
    }

}
