package com.jedosa.junglim.item.domain.repository;

import com.jedosa.junglim.item.domain.option.BindingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BindingTypeRepository extends JpaRepository<BindingType, Long> {

}
