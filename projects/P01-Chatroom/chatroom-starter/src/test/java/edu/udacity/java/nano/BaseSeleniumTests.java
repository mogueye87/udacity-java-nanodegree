package edu.udacity.java.nano;

import java.io.File;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class BaseSeleniumTests {
	private static final String CHROMEDRIVER_EXE = "chromedriver.exe";
	private WebDriver webDriver;

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	private String findFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		URL url = classLoader.getResource(CHROMEDRIVER_EXE);
		return url.getFile();
	}

	@Before
	public void setUp() {
		String driverFile = findFile();
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File(driverFile))
				.build();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
		options.addArguments("--headless");
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("start-maximized"); // open Browser in maximized mode
		options.addArguments("disable-infobars"); // disabling infobars
		options.addArguments("--disable-extensions"); // disabling extensions
		options.addArguments("--disable-gpu"); // applicable to windows os only
		options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		options.merge(capabilities);
		this.webDriver = new ChromeDriver(service, options);
	}

	@After
	public void tearDown() {
		if (this.webDriver != null) {
			this.webDriver.close();
		}
	}
}