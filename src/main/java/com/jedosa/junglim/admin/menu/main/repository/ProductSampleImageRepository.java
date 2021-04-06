package com.jedosa.junglim.admin.menu.main.repository;

import com.jedosa.junglim.admin.menu.main.domain.ProductSampleImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductSampleImageRepository extends JpaRepository<ProductSampleImage, Long> {
    Optional<ProductSampleImage> findBySequence(Long sequence);
}
