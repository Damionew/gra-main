package com.damionew.controller.chatroom;

import static com.damionew.utils.WebSocketUtil.LIVING_SESSIONS_CACHE;
import static com.damionew.utils.WebSocketUtil.sendMessage;
import static com.damionew.utils.WebSocketUtil.sendMessageAll;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@ServerEndpoint("/chatroom/{username}")	
public class ChatRoomServerEndPoint {
	private static final Logger log = LoggerFactory.getLogger(ChatRoomServerEndPoint.class);
	
	@OnOpen
	public void openSession(@PathParam("username") String username,Session session) {
		LIVING_SESSIONS_CACHE.put(username,session);
		String message = "欢迎用户["+username+"]进入聊天室";
		log.info(message);
		sendMessageAll(message);
	}
	
	@OnMessage
	public void onMessage(@PathParam("username") String username,String message) {
		log.info(message);
		sendMessageAll("用户["+username+"]: "+message);
	}
	
	@OnClose
	public void onClose(@PathParam("username") String username,Session session) {
		//当前的Session移除
		LIVING_SESSIONS_CACHE.remove(username);
		//通知其他用户该用户已下线
		sendMessageAll("用户["+username+"]已下线");
		try {
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@OnError
	public void onError(Session session,Throwable throwable) {
		try {
			session.close();
		} catch (Exception e) {
			throwable.printStackTrace();
		}
	}
	
	@GetMapping("/chatroom/{sender}/to/{receiver}")
	public void onMessage(@PathParam("sender") String sender,@PathVariable("receiver") String receiver,String message) {
		sendMessage(LIVING_SESSIONS_CACHE.get(receiver), "["+message+"]->["+receiver+"]: "+message);
	}
}
