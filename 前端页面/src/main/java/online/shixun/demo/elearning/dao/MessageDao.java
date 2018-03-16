/********************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
*********************************************/
package online.shixun.demo.elearning.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import online.shixun.demo.elearning.dto.Message;

@Mapper
public interface MessageDao {
	
	/**
	 * <p>保存发送和接收到的消息</p>
	 * @return
	 */
	@Insert("INSERT INTO message(id,sendContent,replyContent,userid,createTime) values(#{message.id},#{message.sendContent},#{message.replyContent},#{message.user.id},#{message.createTime}) ")
	void saveMessage(@Param("message") Message message);
	
	/**
	 * <p>显示用户聊天记录</p>
	 * @param userid 用户id
	 * @param start 从0开始
	 * @param num 多少条
	 */
	@Select("SELECT m.id AS id,m.sendContent AS sendContent,m.replyContent AS replyContent,m.createTime AS createTime, u.id AS 'user.id', u.headPortrait AS 'user.headPortrait', u.backgroundImage AS 'user.backgroundImage' FROM message m , sys_user u WHERE m.userid = u.id AND m.userid = #{userid} ORDER BY m.createTime DESC limit #{start},#{num} ")
	List<Message> getMessage(@Param("userid") Long userid, @Param("start") int start , @Param("num") int num);
}
