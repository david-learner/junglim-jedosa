package com.jedosa.junglim.menu;

import com.jedosa.junglim.menu.domain.OnePageMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<OnePageMenu, Long> {

}
