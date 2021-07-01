package com.jedosa.junglim.admin.item;

import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.infrastructure.annotation.Admin;
import com.jedosa.junglim.item.domain.option.BindingType;
import com.jedosa.junglim.item.domain.option.CoatingType;
import com.jedosa.junglim.item.domain.option.DesignType;
import com.jedosa.junglim.item.domain.option.FlyleafColorType;
import com.jedosa.junglim.item.dto.BindingTypeDto;
import com.jedosa.junglim.item.dto.CoatingTypeDto;
import com.jedosa.junglim.item.dto.DesignTypeDto;
import com.jedosa.junglim.item.dto.FlyleafColorTypeDto;
import com.jedosa.junglim.item.service.ItemOptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiItemManagementController {

    private final ItemOptionService itemOptionService;

    public ApiItemManagementController(ItemOptionService itemOptionService) {
        this.itemOptionService = itemOptionService;
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
    @PostMapping("/api/admin/items/{itemId}/paper/flyleaf-color-types")
    public FlyleafColorType saveFlyleafType(@RequestBody FlyleafColorTypeDto type,
                                            @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.saveFlyleafColorType((FlyleafColorType) type.toDomain());
    }

    /**
     * 간지 유형을 수정한다
     */
    @PutMapping("/api/admin/items/{itemId}/paper/flyleaf-color-types/{flyleafColorTypeId}")
    public FlyleafColorType updateFlyleafType(@RequestBody FlyleafColorTypeDto type,
                                              @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.saveFlyleafColorType((FlyleafColorType) type.toDomain());
    }

    /**
     * 간지 유형을 삭제한다
     */
    @DeleteMapping("/api/admin/items/{itemId}/paper/flyleaf-color-types/{flyleafColorTypeId}")
    public ResponseEntity<Void> deleteFlyleafType(@PathVariable Long flyleafColorTypeId, @Admin SessionAccountDto sessionAccountDto) {
        itemOptionService.deleteFlyleafColorType(flyleafColorTypeId);
        return ResponseEntity.ok().build();
    }
}
