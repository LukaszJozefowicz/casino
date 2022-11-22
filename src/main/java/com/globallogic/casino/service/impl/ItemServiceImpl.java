package com.globallogic.casino.service.impl;

import com.globallogic.casino.model.entity.Game;
import com.globallogic.casino.model.entity.Item;
import com.globallogic.casino.model.enums.ItemCondition;
import com.globallogic.casino.model.enums.ItemType;
import com.globallogic.casino.repository.ItemRepository;
import com.globallogic.casino.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Item getItemToSet(ItemType itemType) {
        Item itemFound = itemRepository.findFirstByItemTypeAndItemConditionAndIsAssigned(itemType, ItemCondition.EXCELLENT, false);
        itemFound.setIsAssigned(true);
        itemRepository.save(itemFound);
        return itemFound;
//        return modelMapper.map(itemFound, ItemDto.class);
    }

    @Override
    public void saveItemsAfterAssignedToGame(Game game) {
        game.getNecessaryItems().forEach(item -> {
            item.setAssignedToGame(game);
            itemRepository.save(item);
        });
    }
}
