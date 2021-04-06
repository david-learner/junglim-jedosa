package com.jedosa.junglim.admin.menu;

import com.jedosa.junglim.admin.menu.main.domain.ProductSampleImage;
import com.jedosa.junglim.admin.menu.main.dto.ProductSampleImageDto;
import com.jedosa.junglim.admin.menu.main.service.ProductSampleImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MenuManagementController {

    private final ProductSampleImageService productSampleImageService;

    public MenuManagementController(ProductSampleImageService productSampleImageService) {
        this.productSampleImageService = productSampleImageService;
    }

    @GetMapping("admin/menus")
    public String menuManagement() {
        return "admin/menu/menu-management";
    }

    /**
     * 관리자 - 메뉴관리 - 메인화면
     */
    @GetMapping("admin/menus/main")
    public String mainManagement(Model model) {
        List<ProductSampleImage> images = productSampleImageService.getImages();
        model.addAttribute("productSampleImages", images);
        return "admin/menu/index-management";
    }

    /**
     * 관리자 - 메뉴관리 - 메인화면 - 상품 샘플 이미지 슬라이드 저장
     */
    @PostMapping("admin/menus/main/product-sample-images")
    public ResponseEntity<Void> saveMainProductSampleImages(@ModelAttribute ProductSampleImageDto productSampleImageDto) {
        productSampleImageService.update(productSampleImageDto);
        return ResponseEntity.ok().build();
    }

    /**
     * 관리자 - 메뉴관리 - 메인화면 - 상품 샘플 이미지 슬라이드 삭제
     */
    @DeleteMapping("/admin/menus/main/product-sample-images/{id}")
    public ResponseEntity<Void> deleteMainProductSampleImages(@PathVariable Long id) {
        productSampleImageService.deletePath(id);
        return ResponseEntity.ok().build();
    }
}
