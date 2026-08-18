package com.codewithmosh.store.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
@Data
@Getter
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
//    @JsonIgnore

    private String description;
    private BigDecimal price;
    private Byte categoryId;

}
