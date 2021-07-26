package com.jedosa.junglim.item;

import com.jedosa.junglim.item.service.ItemOptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

    ItemOptionService itemOptionService;

    public ItemController(ItemOptionService itemOptionService) {
        this.itemOptionService = itemOptionService;
    }

    /**
     * '인쇄 및 제본' 페이지
     */
    @GetMapping("/item/printing-and-binding")
    public String normalPrintingAndBinding(Model model) {

        model.addAttribute("bindingTypes", itemOptionService.getAllBindingTypes());
        model.addAttribute("coatingTypes", itemOptionService.getAllCoatingTypes());
        model.addAttribute("designTypes", itemOptionService.getAllDesignTypes());
        model.addAttribute("flyleafColorTypes", itemOptionService.getAllFlyleafColorTypes());
        model.addAttribute("paperTypes", itemOptionService.getAllPaperTypes());
        model.addAttribute("paperSizeTypes", itemOptionService.getAllPaperSizeTypes());
        model.addAttribute("flyleafContentPrice", itemOptionService.getFlyleafContentPrice());
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
