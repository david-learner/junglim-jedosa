package com.jedosa.junglim.item;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

    /**
     * '인쇄 및 제본' 페이지
     */
    @GetMapping("/item/printing-and-binding")
    public String normalPrintingAndBinding() {
        return "item/normal-printing-and-binding";
    }

    /**
     * 'CAD 및 대형출력' 페이지
     */
    @GetMapping("/item/cad-and-big-printing")
    public String cadAndBigPrinting() {
        return "item/cad-and-big-printing";
    }
}
