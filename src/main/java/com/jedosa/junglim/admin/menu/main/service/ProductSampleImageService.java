package com.jedosa.junglim.admin.menu.main.service;

import com.jedosa.junglim.admin.menu.main.domain.ProductSampleImage;
import com.jedosa.junglim.admin.menu.main.dto.ProductSampleImageDto;
import com.jedosa.junglim.admin.menu.main.repository.ProductSampleImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductSampleImageService {

    private final ProductSampleImageRepository productSampleImageRepository;

    public ProductSampleImageService(ProductSampleImageRepository productSampleImageRepository) {
        this.productSampleImageRepository = productSampleImageRepository;
    }


    public Long update(ProductSampleImageDto dto) {

        Optional<ProductSampleImage> maybeProductSampleImage =
                productSampleImageRepository.findBySequence(dto.getSequence());

        // update
        if (maybeProductSampleImage.isPresent()) {
            ProductSampleImage productSampleImage = maybeProductSampleImage.get();
            productSampleImage.update(dto);
            ProductSampleImage savedProductSampleImage = productSampleImageRepository.save(productSampleImage);
            return savedProductSampleImage.getSequence();
        }

        return productSampleImageRepository.save(new ProductSampleImage(dto.getSequence(), dto.getPath())).getSequence();
    }

    public void deletePath(Long id) {
        Optional<ProductSampleImage> maybeSequence = productSampleImageRepository.findBySequence(id);
        if(maybeSequence.isPresent()) {
            ProductSampleImage productSampleImage = maybeSequence.get();
            productSampleImage.deletePath();
            productSampleImageRepository.save(productSampleImage);
        }
    }

    public List<ProductSampleImage> getImages() {
        return productSampleImageRepository.findAllById(ProductSampleImage.SEQUENCES);
    }
}
