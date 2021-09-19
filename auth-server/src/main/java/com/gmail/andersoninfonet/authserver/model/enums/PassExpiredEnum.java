package com.gmail.andersoninfonet.authserver.model.enums;

import java.util.Arrays;

public enum PassExpiredEnum {
    
    Y('Y'),
    N('N');

    private Character isPassExpired;

    /**
     * @param isPassExpired
     */
    private PassExpiredEnum(final Character isPassExpired) {
        this.isPassExpired = isPassExpired;
    }

    public Character getIsPassExpired() {
        return this.isPassExpired;
    }

    public static PassExpiredEnum getFromValue(final Character value) {
        return Arrays.stream(PassExpiredEnum.values()).filter(v -> v.getIsPassExpired().equals(value)).findFirst().orElse(null);
    }
}
