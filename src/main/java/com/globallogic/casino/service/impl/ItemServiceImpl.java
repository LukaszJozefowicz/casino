package com.globallogic.casino.service.impl;

import com.globallogic.casino.exception.EntityNotFoundException;
import com.globallogic.casino.model.entity.h2.Game;
import com.globallogic.casino.model.entity.h2.Item;
import com.globallogic.casino.model.enums.ItemCondition;
import com.globallogic.casino.model.enums.ItemType;
import com.globallogic.casino.repository.ItemRepository;
import com.globallogic.casino.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.globallogic.casino.exception.EntityNotFoundException.ITEM_TO_SET_NOT_FOUND_MSG;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item getItemToSet(ItemType itemType) {
        Item itemToFind = findByTypeAndCondition(itemType, ItemCondition.EXCELLENT)
                .orElseGet(
                        () -> findByTypeAndCondition(itemType, ItemCondition.GOOD)
                        .orElseThrow(() -> new EntityNotFoundException(ITEM_TO_SET_NOT_FOUND_MSG, itemType))
                );
        itemToFind.setIsAssigned(true);
        itemRepository.save(itemToFind);
        return itemToFind;
    }

    @Override
    public void saveItemsAfterAssignedToGame(Game game) {
        game.getNecessaryItems().forEach(item -> {
            item.setAssignedToGame(game);
            itemRepository.save(item);
        });
    }

    private Optional<Item> findByTypeAndCondition(ItemType itemType, ItemCondition itemCondition) {
        return itemRepository.findFirstByItemTypeAndItemConditionAndIsAssigned(
                itemType, itemCondition, false);
    }
}
