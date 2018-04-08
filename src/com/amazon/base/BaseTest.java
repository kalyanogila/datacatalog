package com.amazon.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseTest {

	
	public static WebDriver driver;
	public static Properties prop;
	public static int PAGE_LOAD_TIMEOUT = 20;
	public static int IMPLICIT_WAIT = 20;
	public static long DefaultTimeOutInSeconds = 120; 
	
	
	public BaseTest(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("config/config.properties");
			prop.load(ip);
			PropertyConfigurator.configure(prop.getProperty("log4jConfPath"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void initialization(){
		String browserName = prop.getProperty("browser");
	
		if(browserName.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", "lib/geckodriver.exe");	
			driver = new FirefoxDriver(); 
		}	
		if(browserName.equals("chrome")){
				System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");	
				driver = new ChromeDriver(); 
		}
		
		
	
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		PAGE_LOAD_TIMEOUT=Integer.parseInt(prop.getProperty("pageloadtimeout"));
		PAGE_LOAD_TIMEOUT=Integer.parseInt(prop.getProperty("implicitwait"));
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}
	
	
	public static void clickOnElement(WebElement element) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOutInSeconds);
		
		 wait.until(ExpectedConditions.visibilityOf(element));
			
		if (element!=null) 
			element.click();
		else  
			throw new Exception ("Web element not found: " ); 
		}
	
	public static boolean isElementChecked(String identifier) throws Exception{
		String chkBoxPath = "//input[@name='"+identifier+"' or " +
				"@value='" + identifier + "' or " +
				"@id='"+ identifier + "']";		
		WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOutInSeconds);		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(chkBoxPath)));
		return driver.findElement(By.xpath(chkBoxPath)).isSelected();
	}
	
	public static boolean isElementEnabled(String identifier) throws Exception{
		String elementPath = "//input[@name='"+identifier+"' or " +
				"@value='" + identifier + "' or " +
				"@id='"+ identifier + "'] | //input[contains(@value,'"+identifier+"')]";		
		WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOutInSeconds);		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementPath)));
		return driver.findElement(By.xpath(elementPath)).isEnabled();
	}
	

	public static void clickLink(String identifier) throws Exception{
		String elementLocator = "//a[.='"+identifier+"']";
		WebElement element=driver.findElement(By.xpath(elementLocator));
		clickOnElement(element);
	}
	
	public static void selectValue(WebElement element,String value)throws Exception{
				
		Select option = new Select(element);
		option.selectByValue(value);
		
	}
	
	public static void type(WebElement element, String valueToWrite) throws Exception{
		WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOutInSeconds);		
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(valueToWrite);
	}
	
	public static int rowCount(WebElement table)
	{
		
		return table.findElements(By.tagName("tr")).size();
		
	}
	
	
	public WebElement getElement(String xpath)
	{
		
		WebElement element=driver.findElement(By.xpath(xpath));
		return element;
		
	}
	
	public List<WebElement> getElements(String xpath)
	{
		
		List<WebElement> elements=driver.findElements(By.xpath(xpath));
		return elements;
		
	}
	
}
