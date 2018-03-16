/********************************************
 * Copyright (c) , shixun.online
 *
 * All rights reserved
 *
*********************************************/
package online.shixun.demo.robot.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>记录用户发送给机器人的消息以及回复的消息</p>
 *
 */
public class Message {
	private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Long id;
	private String sendContent;
	private String replyContent;
	private User user;
	private Date createTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSendContent() {
		return sendContent;
	}
	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public String getCreateTimeString() {
		return formatter.format(createTime);
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	

}
