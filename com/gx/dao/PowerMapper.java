package com.gx.dao;

import com.gx.po.Power;

public interface PowerMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table power
	 * @mbggenerated  Mon Nov 06 14:52:45 CST 2017
	 */
	int deleteByPrimaryKey(Integer powerid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table power
	 * @mbggenerated  Mon Nov 06 14:52:45 CST 2017
	 */
	int insert(Power record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table power
	 * @mbggenerated  Mon Nov 06 14:52:45 CST 2017
	 */
	int insertSelective(Power record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table power
	 * @mbggenerated  Mon Nov 06 14:52:45 CST 2017
	 */
	Power selectByPrimaryKey(Integer powerid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table power
	 * @mbggenerated  Mon Nov 06 14:52:45 CST 2017
	 */
	int updateByPrimaryKeySelective(Power record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table power
	 * @mbggenerated  Mon Nov 06 14:52:45 CST 2017
	 */
	int updateByPrimaryKey(Power record);
}