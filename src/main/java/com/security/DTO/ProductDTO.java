package com.security.DTO;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data  //getter, setter, toString 포함
public class ProductDTO {
    //제약 사항 메시지: 비어있을 수 없습니다.
    @NotEmpty(message = "상품명은 필수로 입력해야 합니다.")
    private String name; //상품명

    //0 이상이어야 합니다.
    @NotNull(message = "가격은 필수로 입력해야 합니다.") // - @NotNull → 값이 반드시 있어야 함
    @Min(value = 0, message = "가격은 0원일 수 없습니다.") // - @Min(value=0) → 값이 0 이상이어야 함, 초기값 0
    private int price;
}