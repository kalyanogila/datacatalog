package com.amazon.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.base.BaseTest;

/***
 * KindleDetailsTab class is used to get all information related to Kindle edition of book including 
 * cost,pages,settings and supported devices
 * @author Kalyan Reddy Ogila
 *
 */
public class KindleDetailsTab extends BaseTest{

	@FindBy(xpath="//span[@class='a-list-item a-size-base' and contains(text(),'Length')]/span")
	WebElement kindleLength;
	
	
	@FindBy(xpath="//span[@class='a-list-item a-size-base' and contains(text(),'Page Flip')]/span")
	WebElement pageFlipEnabled;
	
	@FindBy(xpath="//span[@class='a-list-item a-size-base' and contains(text(),'Enhanced Typesetting')]/span")
	WebElement enhancedTypesetting;
	
	@FindBy(xpath="//a[contains(text(),'See all supported devices')]")
	WebElement supportedPopup;
		
	@FindBy(xpath="//ul[@class='a-unordered-list a-nostyle a-vertical']/li")
	List<WebElement> supportedDevices;
	
	@FindBy(xpath="//span[@class='a-size-large mediaTab_title' and contains(text(),'Kindle')]/following::span[1]")
	WebElement kindleCost;
	Logger log;
	
	/***
	 * Initializing Kindle Tab
	 */
	public KindleDetailsTab() {
				PageFactory.initElements(driver, this);
				log= Logger.getLogger(KindleDetailsTab.class);
			}
		
		/***
		 * This method is to check if PageFlip is Enabled or not	
		 * @return true if Enabled text is displayed on Kindle tab for Page Flip
		 */
		public boolean isPageFlipEnabled()
			{
				log.info("start of isPageFlipEnabled() in KindleDetailsTab");
				if(pageFlipEnabled.getText().equals("Enabled"))
				{	
					log.info("End of isPageFlipEnabled() in KindleDetailsTab and pageFlipEnabled is true");
					return true;
				}
				else
				{	log.info("End of isPageFlipEnabled() in KindleDetailsTab and pageFlipEnabled is false");
					return false;
					
				}
				
			}	
	
		/***
		 * This method is to check if Enhanced Type Setting is Enabled or not
		 * @return true if Enabled text is displayed on Kindle tab for Enhanced Type Setting
		 */
		public boolean isEnhancedTypesetting()
		{
			log.info("start of isEnhancedTypesetting() in KindleDetailsTab");
			if(enhancedTypesetting.getText().equals("Enabled"))
			{
				log.info("End of isEnhancedTypesetting() in KindleDetailsTab and isEnhancedTypesetting is true");
				return true;
			}
			else
			{
				log.info("End of isEnhancedTypesetting() in KindleDetailsTab and isEnhancedTypesetting is false");
				return false;
			}
			
		}	
		
		/***
		 * This method is to get Number of pages for Kindle
		 * @return Length in String format present on Kindle tab
		 */
		public String getKindleLength()
		{
			log.info("Number of Kindle Pages are:"+kindleLength.getText());
			return kindleLength.getText();
		}

		
		/***
		 * This method is to get cost of Kindle format of book
		 * @return cost as String format for Kindle
		 */
		public String getKindlecost()
		{
			log.info("Cosr of Kindle edition is:"+kindleCost.getText());
			return kindleCost.getText();
			
		}
		
		
		/***
		 * This method returns all Supported devices available for kindle
		 * @return List of Strings i.e. List of all supported devices
		 */
		public List<String> getSupportedDevices()
		{
			List<String> devices=new ArrayList();
			try {
				clickOnElement(supportedPopup);
				log.info("Supported Devices are");
				for(WebElement element:supportedDevices)
				{
					log.info(element.getText());
					devices.add(element.getText());
				}
				
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			finally
			{
				return devices;
			}
			
		}
	
		
	
}
