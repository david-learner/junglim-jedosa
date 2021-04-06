package com.jedosa.junglim.account;

import com.jedosa.junglim.account.domain.Account;
import com.jedosa.junglim.account.domain.QAccount;
import com.jedosa.junglim.admin.AccountSearchCondition;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AccountRepositoryImpl implements AccountRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public AccountRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<Account> search(AccountSearchCondition condition, Pageable pageable) {
        QueryResults<Account> results = jpaQueryFactory
                .selectFrom(QAccount.account)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(QAccount.account.id.desc())
                .fetchResults();
        List<Account> accounts = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(accounts, pageable, total);
    }
}
