package com.globallogic.casino.model.dto.h2;

import com.globallogic.casino.model.enums.ItemCondition;
import com.globallogic.casino.model.enums.ItemType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private Long itemId;
    private ItemType itemType;
    private ItemCondition itemCondition;
    private Boolean isAssigned;
}
