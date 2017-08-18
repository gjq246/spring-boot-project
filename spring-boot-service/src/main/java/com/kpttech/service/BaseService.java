package com.kpttech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

public abstract class BaseService<T> {

	@Autowired
	private Mapper<T> mapper;

	public Mapper<T> getMapper() {
		return mapper;
	}

	/**
	 * 根据id查询数据
	 * 
	 * @param id
	 * @return
	 */
	public T queryById(Long id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	/**
	 * 查询所有数据。该方法一般不需要重载就够用
	 * 
	 * @return
	 */
	public List<T> queryAll(T record, String orderProperty, String orderDir) {
		PropertyMapService propertyMapService = new PropertyMapService(record.getClass());
		Example example = new Example(record.getClass());
		if (orderProperty != null && orderProperty != "" && orderDir != null && orderDir != "") {
			example.setOrderByClause(propertyMapService.getColumn(orderProperty) + " " + orderDir);
		}
		return this.mapper.selectByExample(example);
	}

	/**
	 * 根据条件查询一条数据
	 * 
	 * @param record
	 * @return
	 */
	public T queryOne(T record) {
		return this.mapper.selectOne(record);
	}

	/**
	 * 根据条件查询数据列表。该方法一般需要重载为具有模糊查询条件的方法
	 * 
	 * @param record
	 * @return
	 */
	public List<T> queryListByWhere(T record, String orderProperty, String orderDir) {
		return this.mapper.select(record);
	}

	/**
	 * 分页查询数据列表。该方法一般需要重载为具有模糊查询条件的方法
	 * 
	 * @param page
	 * @param rows
	 * @param record
	 * @return
	 */
	public PageInfo<T> queryPageListByWhere(Integer page, Integer rows, T record, String orderProperty,
			String orderDir) {
		// 设置分页条件
		PageHelper.startPage(page, rows);
		List<T> list = this.mapper.select(record);
		return new PageInfo<T>(list);
	}

	/**
	 * 新增数据
	 * 
	 * @param record
	 * @return
	 */
	public Integer save(T record) {
		// record.setCreateTime(new Date());
		// record.setUpdateTime(record.getCreateTime());
		return this.mapper.insert(record);
	}

	/**
	 * 有选择的保存，选择不为null的字段作为插入字段
	 * 
	 * @param record
	 * @return
	 */
	public Integer saveSelective(T record) {
		// record.setCreateTime(new Date());
		// record.setUpdateTime(record.getCreateTime());
		return this.mapper.insertSelective(record);
	}

	/**
	 * 更新数据
	 * 
	 * @param record
	 * @return 成功更新条数
	 */
	public Integer update(T record) {
		// record.setUpdateTime(new Date());
		return this.mapper.updateByPrimaryKey(record);
	}

	/**
	 * 有选择的更新，选择不为null的字段作为插入字段
	 * 
	 * @param record
	 * @return 成功更新条数
	 */
	public Integer updateSelective(T record) {
		// record.setUpdateTime(new Date());
		return this.mapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 根据id删除数据
	 * 
	 * @param id
	 * @return
	 */
	public Integer deleteById(Long id) {
		return this.mapper.deleteByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 * 
	 * @param clazz
	 * @param property
	 * @param values
	 * @return
	 */
	public Integer deleteByIds(Class<T> clazz, String property, List<Object> values) {
		Example example = new Example(clazz);
		example.createCriteria().andIn(property, values);
		return this.mapper.deleteByExample(example);
	}

	/**
	 * 根据条件删除数据
	 * 
	 * @param record
	 * @return
	 */
	public Integer deleteByWhere(T record) {
		return this.mapper.delete(record);
	}

}
