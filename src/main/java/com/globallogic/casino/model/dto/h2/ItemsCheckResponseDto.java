package com.globallogic.casino.model.dto.h2;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ItemsCheckResponseDto {
    private String message;
    private List<ItemDto> items;
}
