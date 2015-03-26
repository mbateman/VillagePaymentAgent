package org.haftrust.verifier.model.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.haftrust.verifier.model.enums.EmploymentStatus;

@Converter(autoApply = true)
public class EmploymentStatusConverter implements AttributeConverter<EmploymentStatus, String> {

    @Override
    public String convertToDatabaseColumn(EmploymentStatus kv) {
        if (kv == null) return null;
        return kv.getValue();
    }

    @Override
    public EmploymentStatus convertToEntityAttribute(String dbValue) {
        if (dbValue == null) return null;
        return EmploymentStatus.valueOfValue(dbValue);
    }

}
