package org.haftrust.verifier.model.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.haftrust.verifier.model.enums.PersonTitle;

@Converter(autoApply = true)
public class PersonTitleConverter implements AttributeConverter<PersonTitle, String> {

    @Override
    public String convertToDatabaseColumn(PersonTitle kv) {
        if (kv == null) return null;
        return kv.getValue();
    }

    @Override
    public PersonTitle convertToEntityAttribute(String dbValue) {
        if (dbValue == null) return null;
        return PersonTitle.valueOfValue(dbValue);
    }

}
