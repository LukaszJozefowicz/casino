package com.globallogic.casino.service;

import com.globallogic.casino.model.entity.Game;
import com.globallogic.casino.model.entity.Item;
import com.globallogic.casino.model.enums.ItemType;

public interface ItemService {
    Item getItemToSet(ItemType itemType);
    void saveItemsAfterAssignedToGame(Game game);
}
