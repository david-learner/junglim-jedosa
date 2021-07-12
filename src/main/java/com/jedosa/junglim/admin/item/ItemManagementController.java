package com.jedosa.junglim.admin.item;

import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.infrastructure.annotation.Admin;
import com.jedosa.junglim.item.service.ItemOptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemManagementController {

    private final ItemOptionService itemOptionService;

    public ItemManagementController(ItemOptionService itemOptionService) {
        this.itemOptionService = itemOptionService;
    }

    @GetMapping("admin/items/{itemId}")
    public String itemManagement(@PathVariable Long itemId, Model model, @Admin SessionAccountDto sessionAccountDto) {

        if (itemId == 1L) {

            model.addAttribute("itemId", 1);
            model.addAttribute("bindingTypes", itemOptionService.getAllBindingTypes());
            model.addAttribute("coatingTypes", itemOptionService.getAllCoatingTypes());
            model.addAttribute("designTypes", itemOptionService.getAllDesignTypes());
            model.addAttribute("flyleafColorTypes", itemOptionService.getAllFlyleafColorTypes());
            model.addAttribute("paperTypes", itemOptionService.getAllPaperTypes());
            model.addAttribute("paperSizeTypes", itemOptionService.getAllPaperSizeTypes());
            model.addAttribute("coverPaperPrintingTypes", itemOptionService.getAllCoverPaperPrintingTypes());
            model.addAttribute("contentPaperPrintingTypes", itemOptionService.getAllContentPaperPrintingTypes());
            model.addAttribute("flyleafContentPrice", itemOptionService.getFlyleafContentPrice());
        }

        return "/admin/item/printing-and-binding";
    }
}
