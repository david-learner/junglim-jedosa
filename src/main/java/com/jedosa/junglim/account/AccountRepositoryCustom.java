package com.jedosa.junglim.account;

import com.jedosa.junglim.account.domain.Account;
import com.jedosa.junglim.admin.AccountSearchCondition;
import com.jedosa.junglim.article.domain.Article;
import com.jedosa.junglim.article.repository.ArticleSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepositoryCustom {
    Page<Account> search(AccountSearchCondition condition, Pageable pageable);
}
