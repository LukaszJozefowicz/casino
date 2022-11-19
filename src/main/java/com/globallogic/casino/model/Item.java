package com.globallogic.casino.model;

import com.globallogic.casino.model.enums.ItemCondition;
import com.globallogic.casino.model.enums.ItemType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Item {
    @Id
    private final UUID itemNo = UUID.randomUUID();
    private ItemType itemType;
    private ItemCondition itemCondition;
}
