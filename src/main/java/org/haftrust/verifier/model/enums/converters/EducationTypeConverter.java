package org.haftrust.verifier.model.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.haftrust.verifier.model.enums.EducationType;

@Converter(autoApply = true)
public class EducationTypeConverter implements AttributeConverter<EducationType, String> {

    @Override
    public String convertToDatabaseColumn(EducationType kv) {
        if (kv == null) return null;
        return kv.getValue();
    }

    @Override
    public EducationType convertToEntityAttribute(String dbValue) {
        if (dbValue == null) return null;
        return EducationType.valueOfValue(dbValue);
    }

}
