package org.haftrust.verifier.model.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.haftrust.verifier.model.enums.DeviceAllocation;

@Converter(autoApply = true)
public class DeviceAllocationConverter implements AttributeConverter<DeviceAllocation, String> {

    @Override
    public String convertToDatabaseColumn(DeviceAllocation kv) {
        if (kv == null) return null;
        return kv.getValue();
    }

    @Override
    public DeviceAllocation convertToEntityAttribute(String dbValue) {
        if (dbValue == null) return null;
        return DeviceAllocation.valueOfValue(dbValue);
    }

}
