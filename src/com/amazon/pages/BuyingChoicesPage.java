package com.amazon.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.base.BaseTest;
import com.amazon.tests.OffersDetailsTest;

/***
 * BuyingChoicesPage implements logic for different offers from sellers as per condition of book
 * @author Kalyan Reddy Ogila
 *
 */
public class BuyingChoicesPage extends BaseTest {

	
	@FindBy(name="olpCheckbox_new")
	WebElement newBook;
	
	@FindBy(name="olpCheckbox_rental")
	WebElement rentalBook;
	
	@FindBy(name="olpCheckbox_used")
	WebElement usedBook;
	
	@FindBy(name="olpCheckbox_usedLikeNew")
	WebElement usedLikeNewBook;
	
	@FindBy(name="olpCheckbox_usedVeryGood")
	WebElement usedVerygoodBook;
	
	@FindBy(name="olpCheckbox_usedGood")
	WebElement usedgoodBook;
	
	@FindBy(name="olpCheckbox_usedAcceptable")
	WebElement usedAcceptableBook;
	
	Logger log;
	/***
	 * Initializing Buying Choices Page
	 */
	public BuyingChoicesPage() {
					PageFactory.initElements(driver, this);
					log= Logger.getLogger(BuyingChoicesPage.class);
	}
				
	
	/***
	 * This method is used to return Page which has details of all offers available from different Sellers
	 * available for PaperBack edition
	 * @param condition parameter is type of search criteria currently based on book condition like based on rental books or
	 * new books or used books conditions like good or very good or acceptable, etc
	 * @return returns Offer Details available from different sellers like cost,shipping charges
	 */
	public OfferListingDetailsPage filterByBookCondition(String condition)
	{
		try {
			if(condition.equals("rental"))
				clickOnElement(rentalBook);
			else if(condition.equals("used"))
				clickOnElement(usedBook);
			else if(condition.equals("usedLikenew"))
				clickOnElement(usedLikeNewBook);
			else if(condition.equals("usedverygood"))
				clickOnElement(usedVerygoodBook);
			else if(condition.equals("usedgood"))
				clickOnElement(usedgoodBook);
			else if(condition.equals("usedacceptable"))
				clickOnElement(usedAcceptableBook);
			else
				clickOnElement(newBook);
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		finally
		{
		return new OfferListingDetailsPage();
		}
	}
	
		
}
