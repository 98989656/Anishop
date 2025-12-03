package com.shop.anishop.Item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {


    private Long id;

    @Size(min = 3, max = 50, message = " 상품명은 3자리에서 50자리 사이여야 합니다.")
    @NotEmpty
    private String itemName;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    @NotEmpty(message = "상품 설명은 필수 입력 값입니다.")
    private String itemDetail;




}
