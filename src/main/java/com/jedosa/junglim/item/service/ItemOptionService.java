package com.jedosa.junglim.item.service;

import com.jedosa.junglim.item.domain.option.BindingType;
import com.jedosa.junglim.item.domain.option.CoatingType;
import com.jedosa.junglim.item.domain.option.DesignType;
import com.jedosa.junglim.item.domain.option.FlyleafColorType;
import com.jedosa.junglim.item.domain.repository.BindingTypeRepository;
import com.jedosa.junglim.item.domain.repository.CoatingTypeRepository;
import com.jedosa.junglim.item.domain.repository.DesignTypeRepository;
import com.jedosa.junglim.item.domain.repository.FlyleafColorTypeRepository;
import com.jedosa.junglim.item.dto.CoatingTypeDto;
import com.jedosa.junglim.item.dto.DesignTypeDto;
import com.jedosa.junglim.item.dto.FlyleafColorTypeDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemOptionService {

    private final BindingTypeRepository bindingTypeRepository;
    private final CoatingTypeRepository coatingTypeRepository;
    private final DesignTypeRepository designTypeRepository;
    private final FlyleafColorTypeRepository flyleafColorTypeRepository;

    public ItemOptionService(BindingTypeRepository bindingTypeRepository,
                             CoatingTypeRepository coatingTypeRepository,
                             DesignTypeRepository designTypeRepository,
                             FlyleafColorTypeRepository flyleafColorTypeRepository) {
        this.bindingTypeRepository = bindingTypeRepository;
        this.coatingTypeRepository = coatingTypeRepository;
        this.designTypeRepository = designTypeRepository;
        this.flyleafColorTypeRepository = flyleafColorTypeRepository;
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

    public CoatingType saveCoatingType(CoatingType type) {
        return coatingTypeRepository.save(type);
    }

    public void deleteCoatingType(Long coatingTypeId) {
        coatingTypeRepository.deleteById(coatingTypeId);
    }

    public List<CoatingTypeDto> getAllCoatingTypes() {
        return coatingTypeRepository.findAll().stream().map(CoatingTypeDto::new).collect(Collectors.toList());
    }

    public DesignType saveDesignType(DesignType type) {
        return designTypeRepository.save(type);
    }

    public void deleteDesignType(Long designTypeId) {
        designTypeRepository.deleteById(designTypeId);
    }

    public List<DesignTypeDto> getAllDesignTypes() {
        return designTypeRepository.findAll().stream().map(DesignTypeDto::new).collect(Collectors.toList());
    }

    public FlyleafColorType saveFlyleafColorType(FlyleafColorType type) {
        return flyleafColorTypeRepository.save(type);
    }

    public void deleteFlyleafColorType(Long FlyleafTypeId) {
        flyleafColorTypeRepository.deleteById(FlyleafTypeId);
    }

    public List<FlyleafColorTypeDto> getAllFlyleafColorTypes() {
        return flyleafColorTypeRepository.findAll().stream().map(FlyleafColorTypeDto::new).collect(Collectors.toList());
    }
}
