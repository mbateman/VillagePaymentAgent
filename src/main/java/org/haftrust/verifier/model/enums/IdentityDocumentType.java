package org.haftrust.verifier.model.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haftrust.verifier.model.StaticData;

public enum IdentityDocumentType implements KeyValueEnum {

    PASSPORT("Passport", "Passport"), 
    IDENTITY_CARD("identity card", "Identity Card"),
    DRIVING_LICENSE("driving license", "Driving License");

    public final String key;
    public final String value;

    private IdentityDocumentType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private static final Map<String, IdentityDocumentType> keyMap = new HashMap<>();
    private static final Map<String, IdentityDocumentType> valueMap = new HashMap<>();
    static {
        for (IdentityDocumentType type : IdentityDocumentType.values()) {
            keyMap.put(type.key, type);
            valueMap.put(type.value, type);
        }
    }

    public static IdentityDocumentType valueOfKey(String key) {
        return keyMap.get(key);
    }

    public static IdentityDocumentType valueOfValue(String value) {
        return valueMap.get(value);
    }
    
    public static List<StaticData> staticDataValues() {
        List<StaticData> sdList = new ArrayList<>();
        for (IdentityDocumentType val : values()) {
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
