package org.haftrust.verifier.model.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.haftrust.verifier.model.enums.IdentityDocumentType;

@Converter(autoApply = true)
public class IdentityDocumentTypeConverter implements AttributeConverter<IdentityDocumentType, String> {

    @Override
    public String convertToDatabaseColumn(IdentityDocumentType kv) {
        if (kv == null) return null;
        return kv.getValue();
    }

    @Override
    public IdentityDocumentType convertToEntityAttribute(String dbValue) {
        if (dbValue == null) return null;
        return IdentityDocumentType.valueOfValue(dbValue);
    }

}
