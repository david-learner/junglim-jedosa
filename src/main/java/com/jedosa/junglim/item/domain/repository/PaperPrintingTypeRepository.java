package com.jedosa.junglim.item.domain.repository;

import com.jedosa.junglim.item.domain.option.PaperPrintingType;
import com.jedosa.junglim.item.domain.option.PaperSizeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperPrintingTypeRepository extends JpaRepository<PaperPrintingType, Long> {
}
