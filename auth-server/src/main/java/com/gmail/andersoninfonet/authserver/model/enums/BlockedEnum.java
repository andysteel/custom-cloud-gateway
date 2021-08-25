package com.gmail.andersoninfonet.authserver.model.enums;

import java.util.Arrays;

public enum BlockedEnum {
    
    Y('Y'),
    N('N');

    private Character isBlocked;

    /**
     * @param isBlocked
     */
    private BlockedEnum(final Character isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Character getIsBlocked() {
        return this.isBlocked;
    }

    public static BlockedEnum getFromValue(final Character value) {
        return Arrays.stream(BlockedEnum.values()).filter(v -> v.getIsBlocked().equals(value)).findFirst().orElse(null);
    }
}
