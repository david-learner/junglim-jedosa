package com.jedosa.junglim.item.service;

import com.jedosa.junglim.exception.NoFlyleafContentPriceException;
import com.jedosa.junglim.item.domain.option.*;
import com.jedosa.junglim.item.domain.repository.*;
import com.jedosa.junglim.item.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemOptionService {

    private final BindingTypeRepository bindingTypeRepository;
    private final CoatingTypeRepository coatingTypeRepository;
    private final DesignTypeRepository designTypeRepository;
    private final FlyleafColorTypeRepository flyleafColorTypeRepository;
    private final PaperTypeRepository paperTypeRepository;
    private final PaperSizeTypeRepository paperSizeTypeRepository;
    private final PaperPrintingTypeRepository paperPrintingTypeRepository;
    private final FlyleafContentPriceRepository flyleafContentPriceRepository;

    public ItemOptionService(BindingTypeRepository bindingTypeRepository,
                             CoatingTypeRepository coatingTypeRepository,
                             DesignTypeRepository designTypeRepository,
                             FlyleafColorTypeRepository flyleafColorTypeRepository,
                             PaperTypeRepository paperTypeRepository,
                             PaperSizeTypeRepository paperSizeTypeRepository,
                             PaperPrintingTypeRepository paperPrintingTypeRepository,
                             FlyleafContentPriceRepository flyleafContentPriceRepository) {
        this.bindingTypeRepository = bindingTypeRepository;
        this.coatingTypeRepository = coatingTypeRepository;
        this.designTypeRepository = designTypeRepository;
        this.flyleafColorTypeRepository = flyleafColorTypeRepository;
        this.paperTypeRepository = paperTypeRepository;
        this.paperSizeTypeRepository = paperSizeTypeRepository;
        this.paperPrintingTypeRepository = paperPrintingTypeRepository;
        this.flyleafContentPriceRepository = flyleafContentPriceRepository;
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

    public void deleteFlyleafColorType(Long flyleafTypeId) {
        flyleafColorTypeRepository.deleteById(flyleafTypeId);
    }

    public List<FlyleafColorTypeDto> getAllFlyleafColorTypes() {
        return flyleafColorTypeRepository.findAll().stream().map(FlyleafColorTypeDto::new).collect(Collectors.toList());
    }

    public PaperTypeDto savePaperType(PaperType type) {
        return new PaperTypeDto(paperTypeRepository.save(type));
    }

    public void deletePaperType(Long paperTypeId) {
        paperTypeRepository.deleteById(paperTypeId);
    }

    public List<PaperTypeDto> getAllPaperTypes() {
        return paperTypeRepository.findAll().stream().map(PaperTypeDto::new).collect(Collectors.toList());
    }

    public PaperSizeTypeDto savePaperSizeType(PaperSizeType type) {
        return new PaperSizeTypeDto(paperSizeTypeRepository.save(type));
    }

    public void deletePaperSizeType(Long paperSizeTypeId) {
        paperSizeTypeRepository.deleteById(paperSizeTypeId);
    }

    public List<PaperSizeTypeDto> getAllPaperSizeTypes() {
        return paperSizeTypeRepository.findAll().stream().map(PaperSizeTypeDto::new).collect(Collectors.toList());
    }

    public PaperPrintingTypeDto savePaperPrintingType(PaperPrintingType type) {
        return new PaperPrintingTypeDto(paperPrintingTypeRepository.save(type));
    }

    public void deletePaperPrintingType(Long paperPrintingTypeId) {
        paperPrintingTypeRepository.deleteById(paperPrintingTypeId);
    }

    public List<PaperPrintingTypeDto> getAllCoverPaperPrintingTypes() {
        return paperPrintingTypeRepository.findAll().stream().filter(type -> type.getCategory().equals("cover")).map(PaperPrintingTypeDto::new).collect(Collectors.toList());
    }

    public List<PaperPrintingTypeDto> getAllContentPaperPrintingTypes() {
        return paperPrintingTypeRepository.findAll().stream().filter(type -> type.getCategory().equals("content")).map(PaperPrintingTypeDto::new).collect(Collectors.toList());
    }

    public List<PaperPrintingTypeDto> getAllPaperPrintingTypes() {
        return paperPrintingTypeRepository.findAll().stream().map(PaperPrintingTypeDto::new).collect(Collectors.toList());
    }

    public FlyleafContentPriceDto saveFlyleafContentPrice(FlyleafContentPrice type) {
        return new FlyleafContentPriceDto(flyleafContentPriceRepository.save(type));
    }

    public FlyleafContentPriceDto getFlyleafContentPrice() {
        return new FlyleafContentPriceDto(flyleafContentPriceRepository.findById(1L).orElseThrow(NoFlyleafContentPriceException::new));
    }
}
