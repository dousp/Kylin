package com.dou.kylin.sys.service;

import com.dou.kylin.common.page.Pagination;
import com.dou.kylin.common.page.demo01.Page;
import com.dou.kylin.sys.entity.Account;

import java.util.List;
import java.util.Set;

/**
 * 
 * @author <Auto generate>
 * @version 2015-04-15 13:44:42
 */
public interface AccountService {
	/**
	 * 保存
	 *
	 */
	public Integer saveAccount(Account entity) throws Exception;

	/**
	 * 修改
	 *
	 */
	public Integer updateAccountById(Account entity) throws Exception;

	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Account selectAccountById(Long id) throws Exception;

	Account selectByUsername(String username);

    List<Account> listPage(Account account, Pagination pagination);

	/**
	 * 获取用户权限标识
	 * 
	 * @param username
	 * @return
	 */
	Set<String> findPermissions(String username);

    Page<Account> findPage(Page<Account> page ,Account account);
}
