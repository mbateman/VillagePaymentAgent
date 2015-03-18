package org.haftrust.verifier.model.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haftrust.verifier.model.StaticData;

public enum EducationLevel {

    LEVEL_1("level 1", "Level 1"), 
    LEVEL_2("level 2", "Level 2");

    public final String key;
    public final String value;

    private EducationLevel(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private static final Map<String, EducationLevel> keyMap = new HashMap<>();
    private static final Map<String, EducationLevel> valueMap = new HashMap<>();
    static {
        for (EducationLevel level : EducationLevel.values()) {
            keyMap.put(level.key, level);
            valueMap.put(level.value, level);
        }
    }

    public static EducationLevel valueOfKey(String key) {
        return keyMap.get(key);
    }

    public static EducationLevel valueOfValue(String value) {
        return valueMap.get(value);
    }
    
    public static List<StaticData> staticDataValues() {
        List<StaticData> sdList = new ArrayList<>();
        for (EducationLevel val : values()) {
            sdList.add(new StaticData(val.key, val.value));
        }
        return sdList;
    }

}
