package org.haftrust.verifier.model.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.haftrust.verifier.model.enums.InterviewStatus;

@Converter(autoApply = true)
public class InterviewStatusConverter implements AttributeConverter<InterviewStatus, String> {

    @Override
    public String convertToDatabaseColumn(InterviewStatus kv) {
        if (kv == null) return null;
        return kv.getValue();
    }

    @Override
    public InterviewStatus convertToEntityAttribute(String dbValue) {
        if (dbValue == null) return null;
        return InterviewStatus.valueOfValue(dbValue);
    }

}
