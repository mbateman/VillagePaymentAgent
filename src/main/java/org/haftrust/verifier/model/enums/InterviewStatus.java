package org.haftrust.verifier.model.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haftrust.verifier.model.StaticData;

public enum InterviewStatus implements KeyValueEnum {

    AWAITING("awaiting arrangement", "Awaiting Arrangement"), 
    ARRANGED("arranged", "Arranged"),
    COMPLETED("completed", "Completed"),
    CANCELLED("cancelled", "Cancelled");

    public final String key;
    public final String value;

    private InterviewStatus(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private static final Map<String, InterviewStatus> keyMap = new HashMap<>();
    private static final Map<String, InterviewStatus> valueMap = new HashMap<>();
    static {
        for (InterviewStatus status : InterviewStatus.values()) {
            keyMap.put(status.key, status);
            valueMap.put(status.value, status);
        }
    }

    public static InterviewStatus valueOfKey(String key) {
        return keyMap.get(key);
    }

    public static InterviewStatus valueOfValue(String value) {
        return valueMap.get(value);
    }
    
    public static List<StaticData> staticDataValues() {
        List<StaticData> sdList = new ArrayList<>();
        for (InterviewStatus val : values()) {
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
