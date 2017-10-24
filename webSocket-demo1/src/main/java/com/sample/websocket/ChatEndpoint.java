/*****************************************************************************
 * Copyright (c) 2015, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.sample.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSONObject;

@ServerEndpoint(value = "/chat", configurator = GetHttpSessionConfigurator.class)
public class ChatEndpoint {
	private Session session = null;
	private static final List<Session> list = new ArrayList<Session>();
	private static final List<String> userList = new ArrayList<String>();
	private static Object object = new Object();
	// 登录用户名称
	private String username;

	/**
	 * 发送消息
	 * 
	 * @param message
	 * @throws IOException
	 */
	@OnMessage
	public void hello(String message) throws IOException {
		System.out.println("Received : " + message);
		JSONObject json = new JSONObject();
		json.put("prefix", username);
		json.put("data", message);
		json.put("flag", "returnMessage");
		broadMessages(json.toJSONString());
	}

	/**
	 * 连接建立
	 * 
	 * @param session
	 * @param config
	 */
	@OnOpen
	public void myOnOpen(Session session, EndpointConfig config) {
		HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		username = (String) httpSession.getAttribute("user");
		// 加入用户列表
		userList.add(username);
		this.session = session;
		list.add(session);
		// 增加提示信息
		JSONObject json = new JSONObject();
		json.put("message", username + "加入群聊！！！");
		// 推送所有用户信息
		json.put("user", userList);
		json.put("flag", "initUser");
		broadMessages(json.toJSONString());
	}

	/**
	 * 连接
	 * 
	 * @param reason
	 */
	@OnClose
	public void myOnClose(CloseReason reason) {
		list.remove(session);
		// 移出用户列表
		userList.remove(username);
		// 增加提示信息
		// 增加提示信息
		JSONObject json = new JSONObject();
		json.put("message", username + "退出群聊！！！");
		// 推送所有用户信息
		json.put("user", userList);
		json.put("flag", "exit");
		broadMessages(json.toJSONString());
		System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase());
	}

	/**
	 * 广播消息
	 * 
	 * @throws IOException
	 */
	public void broadMessages(String message) {
		for (Session s : list) {
			try {
				// 加把锁
				synchronized (object) {
					s.getBasicRemote().sendText(message);
					// s.getAsyncRemote().sendText(message);
				}
			} catch (Exception e) {
				list.remove(s);
				try {
					s.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

}