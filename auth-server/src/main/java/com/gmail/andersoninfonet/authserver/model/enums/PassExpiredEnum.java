package com.gmail.andersoninfonet.authserver.model.enums;

import java.util.Arrays;

public enum PassExpiredEnum {
    
    YES('Y'),
    NO('N');

    private Character isPassExpired;

    /**
     * @param isPassExpired
     */
    private PassExpiredEnum(Character isPassExpired) {
        this.isPassExpired = isPassExpired;
    }

    public Character getIsPassExpired() {
        return this.isPassExpired;
    }

    public PassExpiredEnum getFromValue(Character value) {
        return Arrays.stream(PassExpiredEnum.values()).filter(v -> v.getIsPassExpired().equals(value)).findFirst().orElse(null);
    }
}
