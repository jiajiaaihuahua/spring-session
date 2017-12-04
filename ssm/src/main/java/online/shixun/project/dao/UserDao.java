package online.shixun.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import online.shixun.project.dto.UserDto;

/**
 * 用户管理持久层
 * @author mrliu
 *
 */
public interface UserDao {
	/**
	 * 查询所有用户对象
	 */
	public List<UserDto> getUser(int age);
	
	/**
	 * 多参数传递
	 */
	public List<UserDto> getUserById(@Param(value = "id") long id,@Param(value = "age") int age,@Param(value = "name") String name);
	/**
	 * 更新用户
	 */
	public void updateUser(UserDto user);
}
