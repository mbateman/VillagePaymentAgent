package org.haftrust.verifier.model.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haftrust.verifier.model.StaticData;

public enum PersonTitle {

    MALE("mr", "Mr"), 
    MRS("mrs", "Mrs"),
    MISS("miss", "Miss");

    public final String key;
    public final String value;

    private PersonTitle(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private static final Map<String, PersonTitle> keyMap = new HashMap<>();
    private static final Map<String, PersonTitle> valueMap = new HashMap<>();
    static {
        for (PersonTitle title : PersonTitle.values()) {
            keyMap.put(title.key, title);
            valueMap.put(title.value, title);
        }
    }

    public static PersonTitle valueOfKey(String key) {
        return keyMap.get(key);
    }

    public static PersonTitle valueOfValue(String value) {
        return valueMap.get(value);
    }
    
    public static List<StaticData> staticDataValues() {
        List<StaticData> sdList = new ArrayList<>();
        for (PersonTitle val : values()) {
            sdList.add(new StaticData(val.key, val.value));
        }
        return sdList;
    }

}
