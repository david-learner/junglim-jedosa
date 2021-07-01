package com.jedosa.junglim.item.domain.repository;

import com.jedosa.junglim.item.domain.option.FlyleafColorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlyleafColorTypeRepository extends JpaRepository<FlyleafColorType, Long> {
}
