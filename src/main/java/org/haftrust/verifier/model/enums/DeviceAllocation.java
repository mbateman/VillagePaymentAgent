package org.haftrust.verifier.model.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haftrust.verifier.model.StaticData;

public enum DeviceAllocation implements KeyValueEnum {

    YES("yes", "Yes"), 
    NO("no", "No");

    public final String key;
    public final String value;

    private DeviceAllocation(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private static final Map<String, DeviceAllocation> keyMap = new HashMap<>();
    private static final Map<String, DeviceAllocation> valueMap = new HashMap<>();
    static {
        for (DeviceAllocation yesno : DeviceAllocation.values()) {
            keyMap.put(yesno.key, yesno);
            valueMap.put(yesno.value, yesno);
        }
    }

    public static DeviceAllocation valueOfKey(String key) {
        return keyMap.get(key);
    }

    public static DeviceAllocation valueOfValue(String value) {
        return valueMap.get(value);
    }
    
    public static List<StaticData> staticDataValues() {
        List<StaticData> sdList = new ArrayList<>();
        for (DeviceAllocation val : values()) {
            sdList.add(new StaticData(val.key, val.value));
        }
        return sdList;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

}
