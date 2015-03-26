package org.haftrust.verifier.model.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haftrust.verifier.model.StaticData;

public enum Gender implements KeyValueEnum {

    MALE("m", "Male"), 
    FEMALE("f", "Female");

    public final String key;
    public final String value;

    private Gender(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private static final Map<String, Gender> keyMap = new HashMap<>();
    private static final Map<String, Gender> valueMap = new HashMap<>();
    static {
        for (Gender gender : Gender.values()) {
            keyMap.put(gender.key, gender);
            valueMap.put(gender.value, gender);
        }
    }

    public static Gender valueOfKey(String key) {
        return keyMap.get(key);
    }

    public static Gender valueOfValue(String value) {
        return valueMap.get(value);
    }
    
    public static List<StaticData> staticDataValues() {
        List<StaticData> sdList = new ArrayList<>();
        for (Gender val : values()) {
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
