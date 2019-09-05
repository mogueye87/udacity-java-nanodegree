package edu.udacity.java.nano;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { WebSocketChatApplicationTests.class })
public class WebSocketChatApplicationTests {
	private static final Logger LOG = LoggerFactory.getLogger(WebSocketChatApplicationTests.class);
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void verifiesHomePageLoads() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.model().hasNoErrors())
				.andExpect(MockMvcResultMatchers.view().name("/login"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void verifiesChatPageLoads() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/index/username"))
				.andExpect(MockMvcResultMatchers.model().hasNoErrors())
				.andExpect(MockMvcResultMatchers.model().attributeExists("username"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("url"))
				.andExpect(MockMvcResultMatchers.view().name("chat")).andExpect(MockMvcResultMatchers.status().isOk());
	}

//	@Test
//	public void login() throws Exception {
//		System.setProperty("webdriver.chrome.driver", "C:/Users/mogue/Desktop/java projects/chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.get("http://localhost:8080/");
//		Thread.sleep(2000);
//		WebElement username = driver.findElement(By.cssSelector("input#username"));
//		WebElement loginBtn = driver.findElement(By.id("loginBtn"));
//		username.click();
//		username.clear();
//		username.sendKeys("username");
//		System.out.println("username: " + username.getText());
//        loginBtn.click();
//
//		// getting the title of the open page
//		String title = driver.getTitle();
//		String expectedTitle = "Chat Room Login";
//		if (title.equalsIgnoreCase(expectedTitle)) {
//			String messageType = "ENTER";
//			LOG.info("The user username enter the chat room : {}", messageType);
//		}
//
//		Thread.sleep(2000);
//
//		// Handling user chatting in the chatroom
//		WebElement msg;
//		msg = driver.findElement(By.id("msg"));
//		msg.sendKeys("Hi there");
//		msg.sendKeys(Keys.ENTER);
//		String expectedMessage;
//		String message = "Hi there";
//		expectedMessage = driver.findElement(By.className("message-content")).getText();
//		expectedMessage = expectedMessage.substring(expectedMessage.indexOf(":") + 1, expectedMessage.length()).trim();
//		if (message.equals(expectedMessage)) {
//			String messageType = "CHAT";
//			LOG.info("The user username has started chatting in the chat room : {}", messageType);
//		}
//
//		Thread.sleep(2000);
//		// Handling user leaving the chatroom
//		WebElement logout;
//		logout = driver.findElement(By.id("logout"));
//		logout.click();
//		String url = "http://localhost:8080/";
//		String expectedUrl = driver.getCurrentUrl();
//		if (url.equals(expectedUrl)) {
//			String messageType = "LEAVE";
//			LOG.info("The user username has left the chat room : {}", messageType);
//		}
//
//		driver.close();
//		driver.quit();
//
//	}

}
