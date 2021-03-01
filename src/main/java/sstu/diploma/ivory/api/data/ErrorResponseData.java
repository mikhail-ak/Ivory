package sstu.diploma.ivory.api.data;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Value
@NotNull
public class ErrorResponseData {

    ZonedDateTime time;

    String message;

    String description;
}
