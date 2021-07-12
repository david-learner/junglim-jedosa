package com.jedosa.junglim.item.domain.repository;

import com.jedosa.junglim.item.domain.option.FlyleafContentPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlyleafContentPriceRepository extends JpaRepository<FlyleafContentPrice, Long> {
}
