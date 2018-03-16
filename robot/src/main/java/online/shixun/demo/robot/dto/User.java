/********************************************
 * Copyright (c) , shixun.online
 *
 * All rights reserved
 *
*********************************************/
package online.shixun.demo.robot.dto;

/**
 * <p> 聊天用户类 </p>
 *
 */
public class User {
	
	private Long id;
	
	// 用户名称
	private String name;
	
	// 用户状态
	private String status;
	
	// 签名
	private String autograph;
	
	// 头像地址
	private String headPortrait;
	
	// 聊天背景
	private String backgroundImage;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAutograph() {
		return autograph;
	}
	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}
	
	
	public String getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	public String getBackgroundImage() {
		return backgroundImage;
	}
	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = backgroundImage;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", status=" + status + ", autograph=" + autograph
				+ ", headPortrait=" + headPortrait + ", backgroundImage=" + backgroundImage + "]";
	}
	

	
	
	

}
