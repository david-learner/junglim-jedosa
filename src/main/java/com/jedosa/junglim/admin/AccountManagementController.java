package com.jedosa.junglim.admin;

import com.jedosa.junglim.account.AccountService;
import com.jedosa.junglim.account.domain.AccountsDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountManagementController {

    private final AccountService accountService;

    public AccountManagementController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("admin/accounts")
    public String accountManagementHome(
            @RequestParam(required = false, defaultValue = "0", name = "page") Integer currentPage,
            @RequestParam(required = false, defaultValue = "", name = "search") String search, Model model) {
        AccountSearchCondition condition = new AccountSearchCondition();
        condition.setPage(currentPage);
        condition.setSearch(search);
        // 회원들을 조건에 맞게 다 불러와야 한다
        AccountsDto accounts = accountService.getAccounts(condition);
        model.addAttribute("accounts", accounts.getAccountDtos());
        model.addAttribute("pagination", accounts.getPagination());

        return "admin/account-management";
    }
}
