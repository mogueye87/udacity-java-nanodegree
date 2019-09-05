package edu.udacity.java.nano;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
public class WebSocketChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketChatApplication.class, args);
	}

	/**
	 * Login Page
	 */
	@GetMapping("/")
	public ModelAndView login() {
		return new ModelAndView("/login");
	}
	/**
	 * Chatroom Page
	 */
	@GetMapping("/index/{username}")
	public ModelAndView index(@PathVariable("username") String username, HttpServletRequest request) throws UnknownHostException {
		ModelAndView model = new ModelAndView();
		model.addObject("username", username);
		String url = "ws://localhost:" + request.getServerPort() + request.getContextPath() + "/chat/"+username;
		model.addObject("url", url);
		model.setViewName("chat");
		return model;
	}
}
