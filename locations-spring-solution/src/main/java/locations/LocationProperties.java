package locations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ConfigurationProperties(prefix = "location")
@Validated
public class LocationProperties {

    @NotNull(message = "uppercase must be true or false")
    private Boolean uppercase;

}
