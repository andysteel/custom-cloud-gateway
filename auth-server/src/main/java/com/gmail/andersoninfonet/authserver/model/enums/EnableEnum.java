package com.gmail.andersoninfonet.authserver.model.enums;

import java.util.Arrays;

public enum EnableEnum {
    
    Y('Y'),
    N('N');

    private Character isEnable;

    /**
     * @param isEnable
     */
    private EnableEnum(final Character isEnable) {
        this.isEnable = isEnable;
    }

    public Character getIsEnable() {
        return this.isEnable;
    }

    public static EnableEnum getFromValue(final Character value) {
        return Arrays.stream(EnableEnum.values()).filter(v -> v.getIsEnable().equals(value)).findFirst().orElse(null);
    }
}
