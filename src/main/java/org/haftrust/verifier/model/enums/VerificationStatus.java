package org.haftrust.verifier.model.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haftrust.verifier.model.StaticData;

public enum VerificationStatus implements KeyValueEnum {

    VERIFIED("verified", "Verified"), 
    UNVERIFIED("unverified", "Unverified"),
    AWAITING("awaiting verification", "Awaiting Verification");

    public final String key;
    public final String value;

    private VerificationStatus(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private static final Map<String, VerificationStatus> keyMap = new HashMap<>();
    private static final Map<String, VerificationStatus> valueMap = new HashMap<>();
    static {
        for (VerificationStatus status : VerificationStatus.values()) {
            keyMap.put(status.key, status);
            valueMap.put(status.value, status);
        }
    }

    public static VerificationStatus valueOfKey(String key) {
        return keyMap.get(key);
    }

    public static VerificationStatus valueOfValue(String value) {
        return valueMap.get(value);
    }
    
    public static List<StaticData> staticDataValues() {
        List<StaticData> sdList = new ArrayList<>();
        for (VerificationStatus val : values()) {
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
