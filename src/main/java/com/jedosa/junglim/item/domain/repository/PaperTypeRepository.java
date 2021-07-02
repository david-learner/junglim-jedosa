package com.jedosa.junglim.item.domain.repository;

import com.jedosa.junglim.item.domain.option.PaperType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperTypeRepository extends JpaRepository<PaperType, Long> {
}
