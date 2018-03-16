/********************************************
 * Copyright (c) , shixun.online
 *
 * All rights reserved
 *
*********************************************/
package online.shixun.demo.robot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import online.shixun.demo.robot.dto.User;

/**
 * <p> 用户存储 </p>
 *
 */
@Mapper
public interface UserDao {
	
	/**
	 * <p>查询所有用户</p>
	 * @return
	 */
	@Select("SELECT * FROM sys_user u")
	List<User> getUser();
	
	/**
	 * <p>查询所有用户</p>
	 * @return
	 */
	@Select({"SELECT * FROM sys_user u WHERE u.id = #{id}"})
	User getUserById(@Param("id") Long id);

	/**
	 * <p>根据用户名称搜索用户</p>
	 */
	@Select({"SELECT * FROM sys_user u WHERE u.name like concat('%',#{name},'%')"})
	List<User> getUserByName(@Param("name") String name);
}
