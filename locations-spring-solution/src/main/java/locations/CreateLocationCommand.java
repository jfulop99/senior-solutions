package locations;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLocationCommand {

    @Schema(description = "Name of the location", example = "Budapest")
    @NotBlank(message = "Name cannot be blank")
    private String name;

//    @Min(value = -90, message = "Latitude cannot be smaller than -90")
//    @Max(value = 90, message = "Latitude cannot be larger than 90")
    @Coordinate
    private double lat;

//    @Min(value = -180, message = "Longitude cannot be smaller than -180")
//    @Max(value = 180, message = "Longitude cannot be larger than 180")
    @Coordinate(type = Type.LON)
    private double lon;

}
