package com.globallogic.casino.repository;

import com.globallogic.casino.model.entity.Item;
import com.globallogic.casino.model.enums.ItemCondition;
import com.globallogic.casino.model.enums.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findFirstByItemTypeAndItemConditionAndIsAssigned(ItemType itemType, ItemCondition itemCondition, Boolean isAssigned);
}
