package com.globallogic.casino.model.entity.h2;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.globallogic.casino.model.enums.ItemCondition;
import com.globallogic.casino.model.enums.ItemType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ITEMS")
public class Item {
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    @Enumerated(EnumType.STRING)
    private ItemType itemType;
    @Enumerated(EnumType.STRING)
    private ItemCondition itemCondition;
    private Boolean isAssigned;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name="game_id")
    private Game assignedToGame;
}
