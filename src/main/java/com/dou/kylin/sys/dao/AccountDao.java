package com.dou.kylin.sys.dao;


import com.dou.kylin.common.annotation.MyBatisRepository;
import com.dou.kylin.common.page.Pagination;
import com.dou.kylin.common.page.demo01.Page;
import com.dou.kylin.sys.entity.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@MyBatisRepository
public interface AccountDao {
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

	/**
	 * 根据用户名和用户类型查找用户
	 * 
	 * @param username
	 * @param
	 * @return
	 */
	Account selectByUsername(String username);

	/**
	 * 分页查找
	 * 
	 * @param account
	 * @param pagination
	 * @return
	 */
    List<Account> listPage(@Param("user") Account account,
                           @Param("pagination") Pagination pagination);

	/**
	 * 获取用户权限标识
	 * 
	 * @param username
	 * @param
	 * @return
	 */
	Set<String> findPermissions(String username);

    List<Account> findPage(Page page, Account account);
}
