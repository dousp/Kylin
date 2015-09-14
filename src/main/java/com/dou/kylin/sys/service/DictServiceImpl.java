package com.dou.kylin.sys.service;

import com.dou.kylin.common.page.Pagination;
import com.dou.kylin.sys.dao.DictDao;
import com.dou.kylin.sys.entity.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-31 15:46:09
 */
@Service("dictService")
public class DictServiceImpl implements DictService {
	@Autowired
	private DictDao dictDao;

	@Override
	public Integer saveDict(Dict entity) throws Exception {
		return dictDao.saveDict(entity);
	}

	@Override
	public Integer updateDictById(Dict entity) throws Exception {
		return dictDao.updateDictById(entity);
	}

	@Override
	public Dict selectDictById(Object id) throws Exception {
		return dictDao.selectDictById(id);
	}

	@Override
	public List<Dict> selectByColumn(String column) {
		return dictDao.selectByColumn(column);
	}

	@Override
	public List<Dict> listPage(Pagination pagination, Dict dict) {
		return dictDao.listPage(pagination,dict);
	}

}
