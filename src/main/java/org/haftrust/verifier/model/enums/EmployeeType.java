package org.haftrust.verifier.model.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haftrust.verifier.model.StaticData;

public enum EmployeeType {

    VILLAGE_PAYMENT_AGENT("vpa", "Village Payment Agent"), 
    VERIFIER("ver", "Verifier"),
    FARMER("far", "Farmer"),
    FIELD_OPERATIVE_MANAGER("fom", "Field Operative Manager");

    public final String key;
    public final String value;

    private EmployeeType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private static final Map<String, EmployeeType> keyMap = new HashMap<>();
    private static final Map<String, EmployeeType> valueMap = new HashMap<>();
    static {
        for (EmployeeType type : EmployeeType.values()) {
            keyMap.put(type.key, type);
            valueMap.put(type.value, type);
        }
    }

    public static EmployeeType valueOfKey(String key) {
        return keyMap.get(key);
    }

    public static EmployeeType valueOfValue(String value) {
        return valueMap.get(value);
    }
    
    public static List<StaticData> staticDataValues() {
        List<StaticData> sdList = new ArrayList<>();
        for (EmployeeType val : values()) {
            sdList.add(new StaticData(val.key, val.value));
        }
        return sdList;
    }

}
