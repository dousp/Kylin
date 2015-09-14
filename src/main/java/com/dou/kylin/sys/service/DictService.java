package com.dou.kylin.sys.service;


import com.dou.kylin.common.page.Pagination;
import com.dou.kylin.sys.entity.Dict;

import java.util.List;

public interface DictService {
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

	/**
	 * 根据对应表字段获取
	 * 
	 * @param column
	 * @return
	 */
	List<Dict> selectByColumn(String column);

	List<Dict> listPage(Pagination pagination, Dict dict);

}
