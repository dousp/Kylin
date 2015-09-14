package com.dou.kylin.sys.service;

import com.dou.kylin.common.page.Pagination;
import com.dou.kylin.common.page.demo01.Page;
import com.dou.kylin.sys.dao.AccountDao;
import com.dou.kylin.sys.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author <Auto generate>
 * @version 2015-04-15 13:44:42
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	@Override
	public Integer saveAccount(Account entity) throws Exception {
		return accountDao.saveAccount(entity);
	}

	@Override
	public Integer updateAccountById(Account entity) throws Exception {
		return accountDao.updateAccountById(entity);
	}

	@Override
	public Account selectAccountById(Long id) throws Exception {
		return accountDao.selectAccountById(id);
	}

	@Override
	public Account selectByUsername(String username) {
		return accountDao.selectByUsername(username);
	}

    @Override
    public List<Account> listPage(Account account, Pagination pagination) {
        return accountDao.listPage(account, pagination);
    }

	@Override
	public Set<String> findPermissions(String username) {
		return accountDao.findPermissions(username);
	}

    @Override
    public Page<Account> findPage(Page<Account> page, Account account) {

        page.setResults(accountDao.findPage(page, account));

        return page;
    }
}
