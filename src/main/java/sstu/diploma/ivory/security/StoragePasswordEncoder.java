package sstu.diploma.ivory.security;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StoragePasswordEncoder implements AttributeConverter<String, String> {



    @Override
    public String convertToDatabaseColumn(String s) {
        return null;
    }

    @Override
    public String convertToEntityAttribute(String s) {
        return null;
    }
}
