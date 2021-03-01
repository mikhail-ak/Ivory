package sstu.diploma.ivory.api.converter;

import org.springframework.core.convert.converter.Converter;
import sstu.diploma.ivory.api.data.CredentialsData;
import sstu.diploma.ivory.storage.entity.CredentialsEntity;

public class ReverseCredentialsConverter implements Converter<CredentialsEntity, CredentialsData> {

    @Override
    public CredentialsData convert(CredentialsEntity entity) {
        return new CredentialsData(entity.getMetadata(), entity.getPassword());
    }
}
