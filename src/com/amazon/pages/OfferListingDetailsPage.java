package com.amazon.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.base.BaseTest;
import com.amazon.tests.PaperBackDetailsTest;

/***
 * OfferLisitngDetailsPage implements logic to get all offers from different Sellers
 * @author Kalyan Reddy Ogila
 *
 */
public class OfferListingDetailsPage extends BaseTest{

	
	@FindBy(xpath="//div[@class='a-row a-spacing-mini olpOffer']")
	List<WebElement> searchTable;
	Logger log;
	HashMap<String,List> bookOffers;
	
	/***
	 * Initializing Offer details page
	 */
	public OfferListingDetailsPage() {
		PageFactory.initElements(driver, this);
		bookOffers=new HashMap<String,List>();
		log= Logger.getLogger(OfferListingDetailsPage.class);
	}
	
	
	/***
	 * This method is used to get Book offers from different Sellers
	 * @return HashMap which contains seller as key and cost,shipping information as a List
	 */
	public HashMap<String,List> getNewBookOffers()
	{
		
		log.info("Start of getNewBookOffers()");
		for(int i=1;i<=searchTable.size();i++)
		{
			List<String> data=new ArrayList<>();
			data.add(driver.findElement(By.xpath("//div[@class='a-row a-spacing-mini olpOffer']["+i+"]//span[@class='a-size-large a-color-price olpOfferPrice a-text-bold']")).getText());
			String search=driver.findElement(By.xpath("//div[@class='a-row a-spacing-mini olpOffer']["+i+"]/div/child::p")).getText();
			if(search.contains("FREE"))
				data.add("FREE");
			else
			{
				data.add(search.split(" ")[1]);
			}
			try
			 {	
				 if(!driver.findElement(By.xpath("//div[@class='a-row a-spacing-mini olpOffer']["+i+"]/div[4]/descendant::a")).getText().isEmpty())
				 {
					 data.add(driver.findElement(By.xpath("//div[@class='a-row a-spacing-mini olpOffer']["+i+"]/div[4]/descendant::a")).getText());
				 }
				 else
					 data.add("amazon.com");
						
			 }
			 catch(Exception e) 
			 {
				 
				 data.add("amazon.com");
				
			 }
			
			
			if(i!=0 && i%10==0)
			{
				i=0;
				
				try {
					clickOnElement(driver.findElement(By.xpath("//ul[@class='a-pagination']/li/a[contains(text(),'Next')]")));
					
				} catch (Exception e) {
					log.error(e.getMessage());
				}
				
			}
			bookOffers.put(data.get(2),data);
			
			
		}
		log.info("End of getNewBookOffers()");
		return bookOffers;
		
	}
}
