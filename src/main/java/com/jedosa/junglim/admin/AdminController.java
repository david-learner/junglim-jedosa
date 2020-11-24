package com.jedosa.junglim.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("admin")
    public String adminMain() {
        return "admin/admin-index";
    }

    @GetMapping("admin/manager/menu")
    public String managerMenu() {
        return "admin/menu-management";
    }
}
