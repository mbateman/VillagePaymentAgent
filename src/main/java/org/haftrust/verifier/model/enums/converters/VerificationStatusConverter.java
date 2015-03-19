package org.haftrust.verifier.model.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.haftrust.verifier.model.enums.VerificationStatus;

@Converter(autoApply = true)
public class VerificationStatusConverter implements AttributeConverter<VerificationStatus, String> {

    @Override
    public String convertToDatabaseColumn(VerificationStatus kv) {
        if (kv == null) return null;
        return kv.getValue();
    }

    @Override
    public VerificationStatus convertToEntityAttribute(String dbValue) {
        if (dbValue == null) return null;
        return VerificationStatus.valueOfValue(dbValue);
    }

}
