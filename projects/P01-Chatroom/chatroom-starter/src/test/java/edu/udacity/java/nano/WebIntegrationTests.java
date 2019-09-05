package edu.udacity.java.nano;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebIntegrationTests extends BaseSeleniumTests {
	private static final Logger LOG = LoggerFactory.getLogger(WebSocketChatApplicationTests.class);
	@LocalServerPort
	private int port;

	@Test
	public void login() {
		this.getWebDriver().get(createURLWithPort("/"));
		WebElement inputElement = this.getWebDriver().findElement(By.id("username"));
		WebElement submitElement = this.getWebDriver().findElement(By.className("submit"));
		inputElement.sendKeys("username");
		System.out.println("inputElement: " + inputElement.getText());
		submitElement.click();
		Assert.assertNotNull(inputElement);

		// getting the title of the open page
		String title = this.getWebDriver().getTitle();
		String expectedTitle = "Chat Room Login";
		if (title.equalsIgnoreCase(expectedTitle)) {
			String messageType = "ENTER";
			LOG.info("The user username enter the chat room : {}", messageType);
		}
	}

	@Test
	public void chat() throws Exception {
		this.getWebDriver().get(createURLWithPort("/index/username"));
		WebElement message = this.getWebDriver().findElement(By.id("msg"));
		String sendedMessage = "Hi there";
		message.click();
		message.sendKeys(sendedMessage);
		message.sendKeys(Keys.ENTER);
		Assert.assertNotNull(message);
	}

	@Test
	public void leave() throws Exception {
		this.getWebDriver().get(createURLWithPort("/index/username"));
		WebElement logout = this.getWebDriver().findElement(By.id("logout"));
		logout.click();
		String url = createURLWithPort("/");
		String expectedUrl = this.getWebDriver().getCurrentUrl();
		if (url.equals(expectedUrl)) {
			String messageType = "LEAVE";
			LOG.info("The user username has left the chat room : {}", messageType);
		}
	}

	/**
	 * 
	 * @param uri
	 * @return
	 */
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
