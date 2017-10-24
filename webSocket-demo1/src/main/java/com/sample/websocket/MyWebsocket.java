/*****************************************************************************
 * Copyright (c) 2015, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.sample.websocket;

import java.io.IOException;
import java.net.URI;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.ClientEndpointConfig;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.Extension;
import javax.websocket.Session;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;

@WebServlet("/web/MyWebsocket")
public class MyWebsocket extends HttpServlet implements ServerContainer{
	private static final long serialVersionUID = 1L;
    
    public MyWebsocket() throws DeploymentException {
    	super();
    	
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//加入endpoint
			this.addEndpoint(com.sample.websocket.ChatEndpoint.class);
			
		} catch (DeploymentException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/chat.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public Session connectToServer(Object arg0, URI arg1) throws DeploymentException, IOException {
		return null;
	}

	@Override
	public Session connectToServer(Class<?> arg0, URI arg1) throws DeploymentException, IOException {
		return null;
	}

	@Override
	public Session connectToServer(Endpoint arg0, ClientEndpointConfig arg1, URI arg2)
			throws DeploymentException, IOException {
		return null;
	}

	@Override
	public Session connectToServer(Class<? extends Endpoint> arg0, ClientEndpointConfig arg1, URI arg2)
			throws DeploymentException, IOException {
		return null;
	}

	@Override
	public long getDefaultAsyncSendTimeout() {
		return 0;
	}

	@Override
	public int getDefaultMaxBinaryMessageBufferSize() {
		return 0;
	}

	@Override
	public long getDefaultMaxSessionIdleTimeout() {
		return 0;
	}

	@Override
	public int getDefaultMaxTextMessageBufferSize() {
		return 0;
	}

	@Override
	public Set<Extension> getInstalledExtensions() {
		return null;
	}

	@Override
	public void setAsyncSendTimeout(long arg0) {
		
	}

	@Override
	public void setDefaultMaxBinaryMessageBufferSize(int arg0) {
		
	}

	@Override
	public void setDefaultMaxSessionIdleTimeout(long arg0) {
		
	}

	@Override
	public void setDefaultMaxTextMessageBufferSize(int arg0) {
		
	}

	@Override
	public void addEndpoint(Class<?> obj) throws DeploymentException {
		
	}

	@Override
	public void addEndpoint(ServerEndpointConfig config) throws DeploymentException {
		
	}

}
