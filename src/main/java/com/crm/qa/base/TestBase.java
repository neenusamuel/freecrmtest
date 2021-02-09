package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;


public class TestBase {
	 public static WebDriver driver;
	 public static  Properties prop;
	 public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	 
	 
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("C:\\Users\\neenu\\eclipse-workspace\\FreeCRMTest1\\src\\main\\java\\com\\crm"
					+ "\\qa\\config\\config.properties");
			prop.load(fis);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}			
			
		}
	public static void initialization() throws IOException {
		
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\neenu\\eclipse-workspace\\"
					+ "SeleniumSessionJan2021\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
//			driver = new HtmlUnitDriver();
		}
		else if(browserName.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\neenu\\Desktop\\browserdrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else {
			Throwable th = new Throwable();
			th.initCause(null);
		}
		
		e_driver = new EventFiringWebDriver(driver);
//		Now create object of EventListenerHandler to register it with EventFiringWebDriver
		try {
			eventListener = new WebEventListener();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		e_driver.register(eventListener);
		driver = e_driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.Page_load_Timeout,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.Implicit_Wait, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
		
	}


