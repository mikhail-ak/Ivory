package sstu.diploma.ivory.api.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sstu.diploma.ivory.api.data.CredentialsData;
import sstu.diploma.ivory.storage.entity.CredentialsEntity;


import java.lang.annotation.Annotation;

@Component
public class CredentialsConverter implements Converter<CredentialsData, CredentialsEntity> {

    @Override
    public CredentialsEntity convert(CredentialsData data) {
        return new CredentialsEntity(null, data.getMetadata(), data.getPassword(), null, null);
    }
}
