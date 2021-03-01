package sstu.diploma.ivory.api.data;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@NotNull
public class CredentialsData {

    String metadata;

    String password;
}
