package com.globallogic.casino.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ItemCondition {
    EXCELLENT,
    GOOD,
    POOR;

    public ItemCondition getOneLowerCondition() {
        if (this.equals(EXCELLENT)) {
            return GOOD;
        }
        return POOR;
    }
}
