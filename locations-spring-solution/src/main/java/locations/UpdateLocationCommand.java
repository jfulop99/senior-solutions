package locations;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateLocationCommand {

    @NotBlank
    private String name;
    private double lat;
    private double lon;

}
