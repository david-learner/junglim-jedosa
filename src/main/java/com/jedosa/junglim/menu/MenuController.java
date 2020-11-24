package com.jedosa.junglim.menu;

import com.jedosa.junglim.exception.NoOnePageMenuException;
import com.jedosa.junglim.menu.domain.OnePageMenuDto;
import com.jedosa.junglim.menu.domain.OnePageMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;

@Controller
public class MenuController {

    private static final Logger log = LoggerFactory.getLogger(MenuController.class);

    private final MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    // 주문방법
    @GetMapping("menu/order-process")
    public String orderProcessForm(Model model) {
        OnePageMenu onePageMenu = menuRepository.findById(1L).orElseThrow(NoOnePageMenuException::new);
        model.addAttribute("onePageMenu", onePageMenu);
        return "menu/order-process";
    }

    @PatchMapping("menu/order-process")
    public String editOrderProcessContent(@ModelAttribute OnePageMenuDto dto, Model model) {
        OnePageMenu updatedOnePageMenu = menuRepository.save(dto.toPage());
        model.addAttribute("onePageMenu", updatedOnePageMenu);
        return "menu/order-process";
    }

    // 가격표
    @GetMapping("menu/price")
    public String priceForm(Model model) {
        OnePageMenu onePageMenu = menuRepository.findById(2L).orElseThrow(NoOnePageMenuException::new);
        model.addAttribute("onePageMenu", onePageMenu);
        return "menu/order-process";
    }

    @PatchMapping("menu/price")
    public String editPriceContent(@ModelAttribute OnePageMenuDto dto, Model model) {
        OnePageMenu updatedOnePageMenu = menuRepository.save(dto.toPage());
        model.addAttribute("onePageMenu", updatedOnePageMenu);
        return "menu/order-process";
    }

    // 회사소개
    @GetMapping("menu/company-introduce")
    public String companyIntroduceForm(Model model) {
        OnePageMenu onePageMenu = menuRepository.findById(3L).orElseThrow(NoOnePageMenuException::new);
        model.addAttribute("onePageMenu", onePageMenu);
        return "menu/order-process";
    }

    @PatchMapping("menu/company-introduce")
    public String editCompanyIntroduceContent(@ModelAttribute OnePageMenuDto dto, Model model) {
        OnePageMenu updatedOnePageMenu = menuRepository.save(dto.toPage());
        model.addAttribute("onePageMenu", updatedOnePageMenu);
        return "menu/order-process";
    }
}
