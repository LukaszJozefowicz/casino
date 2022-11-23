package com.globallogic.casino.service;

import com.globallogic.casino.model.entity.h2.Game;
import com.globallogic.casino.model.entity.h2.Item;
import com.globallogic.casino.model.enums.ItemType;

public interface ItemService {
    Item getItemToSet(ItemType itemType);
    void saveItemsAfterAssignedToGame(Game game);
}
