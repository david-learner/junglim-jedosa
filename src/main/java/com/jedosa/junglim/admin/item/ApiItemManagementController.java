package com.jedosa.junglim.admin.item;

import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.infrastructure.annotation.Admin;
import com.jedosa.junglim.item.domain.option.BindingType;
import com.jedosa.junglim.item.dto.BindingTypeDto;
import com.jedosa.junglim.item.service.ItemOptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiItemManagementController {

    private final ItemOptionService itemOptionService;

    public ApiItemManagementController(ItemOptionService itemOptionService) {
        this.itemOptionService = itemOptionService;
    }

    @PostMapping("/api/admin/items/{itemId}/binding/bindingtypes/save")
    public BindingType saveBindingType(@RequestBody BindingTypeDto type,
                                       @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.saveBindingType(type.toBindingType());
    }

    @PutMapping("/api/admin/items/{itemId}/binding/bindingtypes/{bindingTypeId}")
    public BindingType updateBindingType(@RequestBody BindingTypeDto type,
                                         @Admin SessionAccountDto sessionAccountDto) {
        return itemOptionService.saveBindingType(type.toBindingType());
    }

    @DeleteMapping("/api/admin/items/{itemId}/binding/bindingtypes/{bindingTypeId}")
    public ResponseEntity<Void> deleteBindingType(@PathVariable Long bindingTypeId, @Admin SessionAccountDto sessionAccountDto) {
        itemOptionService.deleteBindingType(bindingTypeId);
        return ResponseEntity.ok().build();
    }
}
