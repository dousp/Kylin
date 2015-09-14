package com.dou.kylin.sys.dao;

import com.dou.kylin.common.annotation.MyBatisRepository;
import com.dou.kylin.common.page.Pagination;
import com.dou.kylin.sys.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@MyBatisRepository
public interface RoleDao {
	/**
	 * 保存
	 *
	 */
	public Integer saveRole(Role entity) throws Exception;

	/**
	 * 修改
	 *
	 */
	public Integer updateRoleById(Role entity) throws Exception;

	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Role selectRoleById(Object id) throws Exception;

	List<Role> listPage(@Param("role") Role role,
						@Param("pagination") Pagination pagination);

	/**
	 * 根据角色id删除该角色所有权限
	 * 
	 * @param id
	 * @return
	 */
	public Integer delPermBYId(Long id);


	/**
	 * 给角色设置权限
	 * 
	 * @param rid
	 * @param id
	 * @return
	 */
	public Integer bathSavePerm(@Param("rids") Long[] rid, @Param("id") Long id);

	public Integer delRoleBYId(Long id);

	public Integer bathSaveRole(@Param("rids") Long[] rid, @Param("id") Long id);

	public List<Role> findByAccountId(Long id);

}
