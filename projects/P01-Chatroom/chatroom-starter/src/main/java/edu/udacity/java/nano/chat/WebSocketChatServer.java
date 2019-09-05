package edu.udacity.java.nano.chat;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import edu.udacity.java.nano.chat.model.Message;
import edu.udacity.java.nano.chat.model.MessageType;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session WebSocket Session
 */

@Component
@ServerEndpoint(value = "/chat/{username}")
public class WebSocketChatServer {

	/**
	 * All chat sessions.
	 */
	private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

	private static void sendMessageToAll(String msg) {
		// print the message on the console.
		System.out.println(msg);

		// send the message to all
		for (Session session : onlineSessions.values()) {
			try {
				session.getBasicRemote().sendText(msg);
			} catch (IOException e) {
				System.out.println("Error: cannot send message to " + session.getId());
			}
		}
	}

	/**
	 * Open connection, 1) add session, 2) add user.
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) {
		onlineSessions.put(username, session);
		Message message = new Message();
		message.setUsername(username);
		message.setOnlineCount(onlineSessions.size());
		message.setType(MessageType.ENTER);
		message.setMsg("Connected!");
		String json = JSON.toJSONString(message);
		sendMessageToAll(json);
	}

	/**
	 * Send message, 1) get username and session, 2) send message to all.
	 */
	@OnMessage
	public void onMessage(Session session, String jsonStr) {
		Message message = JSON.parseObject(jsonStr, Message.class);
		message.setUsername(message.getUsername());
		message.setOnlineCount(onlineSessions.size());
		message.setType(MessageType.CHAT);
		String json = JSON.toJSONString(message);
		sendMessageToAll(json);
	}

	/**
	 * Close connection, 1) remove session, 2) update user.
	 */
	@OnClose
	public void onClose(Session session, @PathParam("username") String username) {
		String onCloseMessage = onlineSessions.get(username) + " left the chat room";
		if (onlineSessions != null) {
			onlineSessions.remove(username);
			Message message = new Message();
			message.setOnlineCount(onlineSessions.size());
			message.setType(MessageType.LEAVE);
			sendMessageToAll(onCloseMessage);
		}
	}

	/**
	 * Print exception.
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("Error " + error.getMessage());
	}

}
