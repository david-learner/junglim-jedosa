package com.jedosa.junglim.admin.item;

import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.infrastructure.annotation.Admin;
import com.jedosa.junglim.item.domain.option.*;
import com.jedosa.junglim.item.dto.*;
import com.jedosa.junglim.item.service.ItemOptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiItemManagementController {

    private final ItemOptionService itemOptionService;

    public ApiItemManagementController(ItemOptionService itemOptionService) {
        this.itemOptionService = itemOptionService;
    }

    /**
     * 종이 가격 테이블을 제공한다
     */
    @GetMapping("/api/paper-prices")
    public List<PaperPrintingTypeDto> getAllPaperPrices() {
        return itemOptionService.getAllPaperPrintingTypes();
    }

    /**
     * 제본 유형을 저장한다
     */
    @PostMapping("/api/admin/items/{itemId}/binding/binding-types")
    public BindingType saveBindingType(@RequestBody BindingTypeDto type,
                                       @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.saveBindingType(type.toBindingType());
    }

    /**
     * 제본 유형을 수정한다
     */
    @PutMapping("/api/admin/items/{itemId}/binding/binding-types/{bindingTypeId}")
    public BindingType updateBindingType(@RequestBody BindingTypeDto type,
                                         @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.saveBindingType(type.toBindingType());
    }

    /**
     * 제본 유형을 삭제한다
     */
    @DeleteMapping("/api/admin/items/{itemId}/binding/binding-types/{bindingTypeId}")
    public ResponseEntity<Void> deleteBindingType(@PathVariable Long bindingTypeId, @Admin SessionAccountDto sessionAccountDto) {
        itemOptionService.deleteBindingType(bindingTypeId);
        return ResponseEntity.ok().build();
    }

    /**
     * 코팅 유형을 저장한다
     */
    @PostMapping("/api/admin/items/{itemId}/cover/coating-types")
    public CoatingType saveCoatingType(@RequestBody CoatingTypeDto type,
                                       @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.saveCoatingType((CoatingType) type.toDomain());
    }

    /**
     * 코팅 유형을 수정한다
     */
    @PutMapping("/api/admin/items/{itemId}/cover/coating-types/{coatingTypeId}")
    public CoatingType updateCoatingType(@RequestBody CoatingTypeDto type,
                                         @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.saveCoatingType((CoatingType) type.toDomain());
    }

    /**
     * 코팅유형을 삭제한다
     */
    @DeleteMapping("/api/admin/items/{itemId}/cover/coating-types/{coatingTypeId}")
    public ResponseEntity<Void> deleteCoatingType(@PathVariable Long coatingTypeId, @Admin SessionAccountDto sessionAccountDto) {
        itemOptionService.deleteCoatingType(coatingTypeId);
        return ResponseEntity.ok().build();
    }

    /**
     * 디자인 유형을 저장한다
     */
    @PostMapping("/api/admin/items/{itemId}/cover/design-types")
    public DesignType saveDesignType(@RequestBody DesignTypeDto type,
                                       @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.saveDesignType((DesignType) type.toDomain());
    }

    /**
     * 디자인 유형을 수정한다
     */
    @PutMapping("/api/admin/items/{itemId}/cover/design-types/{designTypeId}")
    public DesignType updateDesignType(@RequestBody DesignTypeDto type,
                                         @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.saveDesignType((DesignType) type.toDomain());
    }

    /**
     * 디자인 유형을 삭제한다
     */
    @DeleteMapping("/api/admin/items/{itemId}/cover/design-types/{designTypeId}")
    public ResponseEntity<Void> deleteDesignType(@PathVariable Long designTypeId, @Admin SessionAccountDto sessionAccountDto) {
        itemOptionService.deleteDesignType(designTypeId);
        return ResponseEntity.ok().build();
    }

    /**
     * 간지 유형을 저장한다
     */
    @PostMapping("/api/admin/items/{itemId}/flyleaf/flyleaf-color-types")
    public FlyleafColorType saveFlyleafType(@RequestBody FlyleafColorTypeDto type,
                                            @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.saveFlyleafColorType((FlyleafColorType) type.toDomain());
    }

    /**
     * 간지 유형을 수정한다
     */
    @PutMapping("/api/admin/items/{itemId}/flyleaf/flyleaf-color-types/{flyleafColorTypeId}")
    public FlyleafColorType updateFlyleafType(@RequestBody FlyleafColorTypeDto type,
                                              @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.saveFlyleafColorType((FlyleafColorType) type.toDomain());
    }

    /**
     * 간지 유형을 삭제한다
     */
    @DeleteMapping("/api/admin/items/{itemId}/flyleaf/flyleaf-color-types/{flyleafColorTypeId}")
    public ResponseEntity<Void> deleteFlyleafType(@PathVariable Long flyleafColorTypeId, @Admin SessionAccountDto sessionAccountDto) {
        itemOptionService.deleteFlyleafColorType(flyleafColorTypeId);
        return ResponseEntity.ok().build();
    }

    /**
     * 종이 유형을 저장한다
     */
    @PostMapping("/api/admin/items/{itemId}/paper/paper-types")
    public PaperTypeDto savePaperType(@RequestBody PaperTypeDto type,
                                            @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.savePaperType((PaperType) type.toDomain());
    }

    /**
     * 종이 유형을 수정한다
     */
    @PutMapping("/api/admin/items/{itemId}/paper/paper-types/{paperTypeId}")
    public PaperTypeDto updatePaperType(@RequestBody PaperTypeDto type,
                                              @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.savePaperType((PaperType) type.toDomain());
    }

    /**
     * 종이 유형을 삭제한다
     */
    @DeleteMapping("/api/admin/items/{itemId}/paper/paper-types/{paperTypeId}")
    public ResponseEntity<Void> deletePaperType(@PathVariable Long paperTypeId, @Admin SessionAccountDto sessionAccountDto) {
        itemOptionService.deletePaperType(paperTypeId);
        return ResponseEntity.ok().build();
    }

    /**
     * 종이 규격 유형을 저장한다
     */
    @PostMapping("/api/admin/items/{itemId}/paper/paper-size-types")
    public PaperSizeTypeDto savePaperSizeType(@RequestBody PaperSizeTypeDto type,
                                      @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.savePaperSizeType((PaperSizeType) type.toDomain());
    }

    /**
     * 종이 규격 유형을 수정한다
     */
    @PutMapping("/api/admin/items/{itemId}/paper/paper-size-types/{paperSizeTypeId}")
    public PaperSizeTypeDto updatePaperSizeType(@RequestBody PaperSizeTypeDto type,
                                        @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.savePaperSizeType((PaperSizeType) type.toDomain());
    }

    /**
     * 종이 규격 유형을 삭제한다
     */
    @DeleteMapping("/api/admin/items/{itemId}/paper/paper-size-types/{paperSizeTypeId}")
    public ResponseEntity<Void> deletePaperSizeType(@PathVariable Long paperSizeTypeId, @Admin SessionAccountDto sessionAccountDto) {
        itemOptionService.deletePaperSizeType(paperSizeTypeId);
        return ResponseEntity.ok().build();
    }

    /**
     * 표지 종이 유형을 저장한다
     */
    @PostMapping("/api/admin/items/{itemId}/cover/paper-types")
    public PaperPrintingTypeDto saveCoverPaperType(@RequestBody PaperPrintingTypeDto type,
                                              @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.savePaperPrintingType((PaperPrintingType) type.toDomain());
    }

    /**
     * 표지 종이 유형을 수정한다
     */
    @PutMapping("/api/admin/items/{itemId}/cover/paper-types/{paperPrintingTypeId}")
    public PaperPrintingTypeDto updateCoverPaperType(@RequestBody PaperPrintingTypeDto type,
                                                @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.savePaperPrintingType((PaperPrintingType) type.toDomain());
    }

    /**
     * 표지 종이 유형을 삭제한다
     */
    @DeleteMapping("/api/admin/items/{itemId}/cover/paper-types/{paperPrintingTypeId}")
    public ResponseEntity<Void> deleteCoverPaperType(@PathVariable Long paperPrintingTypeId, @Admin SessionAccountDto sessionAccountDto) {
        itemOptionService.deletePaperPrintingType(paperPrintingTypeId);
        return ResponseEntity.ok().build();
    }

    /**
     * 본문 종이 유형을 저장한다
     */
    @PostMapping("/api/admin/items/{itemId}/content/paper-types")
    public PaperPrintingTypeDto saveContentPaperType(@RequestBody PaperPrintingTypeDto type,
                                                   @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.savePaperPrintingType((PaperPrintingType) type.toDomain());
    }

    /**
     * 본문 종이 유형을 수정한다
     */
    @PutMapping("/api/admin/items/{itemId}/content/paper-types/{paperPrintingTypeId}")
    public PaperPrintingTypeDto updateContentPaperType(@RequestBody PaperPrintingTypeDto type,
                                                     @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.savePaperPrintingType((PaperPrintingType) type.toDomain());
    }

    /**
     * 본문 종이 유형을 삭제한다
     */
    @DeleteMapping("/api/admin/items/{itemId}/content/paper-types/{paperPrintingTypeId}")
    public ResponseEntity<Void> deleteContentPaperType(@PathVariable Long paperPrintingTypeId, @Admin SessionAccountDto sessionAccountDto) {
        itemOptionService.deletePaperPrintingType(paperPrintingTypeId);
        return ResponseEntity.ok().build();
    }

    /**
     * 간지 문구 출력 가격을 수정한다
     */
    @PutMapping("/api/admin/items/{itemId}/flyleaf/flyleaf-content/{id}")
    public FlyleafContentPriceDto updateFlyleafContentPrice(@RequestBody FlyleafContentPriceDto type,
                                                     @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.saveFlyleafContentPrice((FlyleafContentPrice) type.toDomain());
    }
}
