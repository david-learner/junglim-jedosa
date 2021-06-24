package com.jedosa.junglim.item.service;

import com.jedosa.junglim.item.domain.option.BindingType;
import com.jedosa.junglim.item.domain.repository.BindingTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemOptionService {

    private final BindingTypeRepository bindingTypeRepository;

    public ItemOptionService(BindingTypeRepository bindingTypeRepository) {
        this.bindingTypeRepository = bindingTypeRepository;
    }

    public BindingType saveBindingType(BindingType type) {
        return bindingTypeRepository.save(type);
    }

    public void deleteBindingType(Long bindingTypeId) {
        bindingTypeRepository.deleteById(bindingTypeId);
    }

    public List<BindingType> getAllBindingTypes() {
        return bindingTypeRepository.findAll();
    }
}
