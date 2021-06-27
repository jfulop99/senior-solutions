package locations;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLocationCommand {

    @Schema(description = "Name of the location", example = "Budapest")
    private String name;
    private double lat;
    private double lon;

}
