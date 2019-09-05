package edu.udacity.java.nano.chat.model;

import com.alibaba.fastjson.JSON;

/**
 * WebSocket message model
 */
public class Message {
	private String username;
	private String msg;
	private MessageType type;
	private int onlineCount;

	public Message() {
	}

	public Message(String username, String msg, MessageType type, int onlineCount) {
		this.username = username;
		this.msg = msg;
		this.type = type;
		this.onlineCount = onlineCount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public int getOnlineCount() {
		return onlineCount;
	}

	public void setOnlineCount(int onlineCount) {
		this.onlineCount = onlineCount;
	}
	
	public String getMessage(String username, String msg, MessageType type, int onlineCount) {
		return JSON.toJSONString(new Message(username, msg, type, onlineCount));
	}
}
