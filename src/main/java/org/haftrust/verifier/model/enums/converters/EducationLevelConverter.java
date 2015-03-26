package org.haftrust.verifier.model.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.haftrust.verifier.model.enums.EducationLevel;

@Converter(autoApply = true)
public class EducationLevelConverter implements AttributeConverter<EducationLevel, String> {

    @Override
    public String convertToDatabaseColumn(EducationLevel kv) {
        if (kv == null) return null;
        return kv.getValue();
    }

    @Override
    public EducationLevel convertToEntityAttribute(String dbValue) {
        if (dbValue == null) return null;
        return EducationLevel.valueOfValue(dbValue);
    }

}
