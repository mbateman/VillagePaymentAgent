package org.haftrust.verifier.model.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.haftrust.verifier.model.enums.EmployeeType;

@Converter(autoApply = true)
public class EmployeeTypeConverter implements AttributeConverter<EmployeeType, String> {

    @Override
    public String convertToDatabaseColumn(EmployeeType kv) {
        if (kv == null) return null;
        return kv.getValue();
    }

    @Override
    public EmployeeType convertToEntityAttribute(String dbValue) {
        if (dbValue == null) return null;
        return EmployeeType.valueOfValue(dbValue);
    }

}
