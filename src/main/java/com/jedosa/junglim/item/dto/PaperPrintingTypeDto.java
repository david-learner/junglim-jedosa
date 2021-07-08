package com.jedosa.junglim.item.dto;

import com.jedosa.junglim.item.domain.option.PaperPrintingType;
import com.jedosa.junglim.item.domain.option.PaperType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class PaperPrintingTypeDto implements Domainable{

    private Long id;
    private Long itemId;
    private String category;
    private String name;
    private String size;
    private String printingColor;
    private BigDecimal singleSidePrice;
    private BigDecimal doubleSidePrice;

    public PaperPrintingTypeDto(PaperPrintingType type) {
        this.id = type.getId();
        this.itemId = type.getItemId();
        this.category = type.getCategory();
        this.name = type.getName();
        this.size = type.getSize();
        this.printingColor = type.getPrintingColor();
        this.singleSidePrice = type.getSingleSidePrice();
        this.doubleSidePrice = type.getDoubleSidePrice();
    }

    @Override
    public Object toDomain() {
        return new PaperPrintingType(getId(),
                getItemId(),
                getCategory(),
                getName(),
                getSize(),
                getPrintingColor(),
                getSingleSidePrice(),
                getDoubleSidePrice());
    }
}
