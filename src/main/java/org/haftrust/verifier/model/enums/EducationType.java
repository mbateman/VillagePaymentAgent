package org.haftrust.verifier.model.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haftrust.verifier.model.StaticData;

public enum EducationType {

    TYPE_1("type 1", "Type 1"), 
    TYPE_2("type 2", "Type 2");

    public final String key;
    public final String value;

    private EducationType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private static final Map<String, EducationType> keyMap = new HashMap<>();
    private static final Map<String, EducationType> valueMap = new HashMap<>();
    static {
        for (EducationType type : EducationType.values()) {
            keyMap.put(type.key, type);
            valueMap.put(type.value, type);
        }
    }

    public static EducationType valueOfKey(String key) {
        return keyMap.get(key);
    }

    public static EducationType valueOfValue(String value) {
        return valueMap.get(value);
    }
    
    public static List<StaticData> staticDataValues() {
        List<StaticData> sdList = new ArrayList<>();
        for (EducationType val : values()) {
            sdList.add(new StaticData(val.key, val.value));
        }
        return sdList;
    }

}
