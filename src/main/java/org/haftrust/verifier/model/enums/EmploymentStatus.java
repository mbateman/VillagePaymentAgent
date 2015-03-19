package org.haftrust.verifier.model.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haftrust.verifier.model.StaticData;

public enum EmploymentStatus implements KeyValueEnum {

    PRE_REGISTERED("preregistered", "Preregistered"), 
    REGISTERED("registered", "Registered"),
    VERIFIED("verified", "Verified"),
    EMPLOYED("employed", "Employed"),
    FAILED("failed", "Failed");

    public final String key;
    public final String value;

    private EmploymentStatus(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private static final Map<String, EmploymentStatus> keyMap = new HashMap<>();
    private static final Map<String, EmploymentStatus> valueMap = new HashMap<>();
    static {
        for (EmploymentStatus status : EmploymentStatus.values()) {
            keyMap.put(status.key, status);
            valueMap.put(status.value, status);
        }
    }

    public static EmploymentStatus valueOfKey(String key) {
        return keyMap.get(key);
    }

    public static EmploymentStatus valueOfValue(String value) {
        return valueMap.get(value);
    }
    
    public String toString() {
        return this.value;
    }
    
    public static List<StaticData> staticDataValues() {
        List<StaticData> sdList = new ArrayList<>();
        for (EmploymentStatus val : values()) {
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
