package org.haftrust.verifier.model.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.haftrust.verifier.model.enums.Gender;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender kv) {
        if (kv == null) return null;
        return kv.getValue();
    }

    @Override
    public Gender convertToEntityAttribute(String dbValue) {
        if (dbValue == null) return null;
        return Gender.valueOfValue(dbValue);
    }

}
