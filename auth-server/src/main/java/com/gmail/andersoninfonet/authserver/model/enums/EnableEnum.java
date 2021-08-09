package com.gmail.andersoninfonet.authserver.model.enums;

import java.util.Arrays;

public enum EnableEnum {
    
    YES('Y'),
    NO('N');

    private Character isEnable;

    /**
     * @param isEnable
     */
    private EnableEnum(Character isEnable) {
        this.isEnable = isEnable;
    }

    public Character getIsEnable() {
        return this.isEnable;
    }

    public EnableEnum getFromValue(Character value) {
        return Arrays.stream(EnableEnum.values()).filter(v -> v.getIsEnable().equals(value)).findFirst().orElse(null);
    }
}
