package com.dou.kylin.sys.dao;

import com.dou.kylin.common.annotation.MyBatisRepository;
import com.dou.kylin.common.page.Pagination;
import com.dou.kylin.sys.entity.Dict;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@MyBatisRepository
public interface DictDao {
	/**
	 * 保存
	 *
	 */
	public Integer saveDict(Dict entity) throws Exception;

	/**
	 * 修改
	 *
	 */
	public Integer updateDictById(Dict entity) throws Exception;

	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Dict selectDictById(Object id) throws Exception;

	List<Dict> selectByColumn(String column);

	List<Dict> listPage(@Param("pagination") Pagination pagination,
						@Param("dict") Dict dict);

}
