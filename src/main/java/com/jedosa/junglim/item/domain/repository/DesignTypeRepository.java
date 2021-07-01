package com.jedosa.junglim.item.domain.repository;

import com.jedosa.junglim.item.domain.option.CoatingType;
import com.jedosa.junglim.item.domain.option.DesignType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignTypeRepository extends JpaRepository<DesignType, Long> {
}
