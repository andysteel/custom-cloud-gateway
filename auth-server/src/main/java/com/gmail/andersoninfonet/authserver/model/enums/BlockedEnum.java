package com.gmail.andersoninfonet.authserver.model.enums;

import java.util.Arrays;

public enum BlockedEnum {
    
    YES('Y'),
    NO('N');

    private Character isBlocked;

    /**
     * @param isBlocked
     */
    private BlockedEnum(Character isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Character getIsBlocked() {
        return this.isBlocked;
    }

    public BlockedEnum getFromValue(Character value) {
        return Arrays.stream(BlockedEnum.values()).filter(v -> v.getIsBlocked().equals(value)).findFirst().orElse(null);
    }
}
