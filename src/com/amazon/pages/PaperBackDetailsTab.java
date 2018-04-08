package com.amazon.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.base.BaseTest;
import com.amazon.tests.OffersDetailsTest;

/***
 * PaperBackDetailsTab class is used to get all information related to PaperBack edition of book including 
 * cost range,starting cost based on new books,used books and rental books
 * @author Kalyan Reddy Ogila
 *
 */
public class PaperBackDetailsTab extends BaseTest{
	
	@FindBy(xpath="//span[@class='a-size-large mediaTab_title' and contains(text(),'Paperback')]/following::span[1]")
	WebElement paperbackCostRange;
	
	@FindBy(xpath="//span[@class='olp-padding-right']/a[contains(text(),'New')]")
	WebElement newBookBuyingChoice;
	
	@FindBy(xpath="//span[@class='olp-padding-right']/a[contains(text(),'Used')]")
	WebElement usedBookBuyingChoice;
	
	@FindBy(xpath="//span[@class='olp-padding-right']/a[contains(text(),'Rentals')]")
	WebElement rentalBookBuyingChoice;
	
	@FindBy(xpath="//span[@class='olp-padding-right']/a[contains(text(),'New')]/../span")
	WebElement newBookCostStartingRange;
	
	@FindBy(xpath="//span[@class='olp-padding-right']/a[contains(text(),'Used')]/../span")
	WebElement usedBookCostStartingRange;
	
	@FindBy(xpath="//span[@class='olp-padding-right']/a[contains(text(),'Rentals')]/../span")
	WebElement rentalBookCostStartingRange;
	
	@FindBy(xpath="//a[@title='See All Buying Options']")
	WebElement buyingOptions;
	
	Logger log;
	/***
	 * Initializing PaperBackDetailsTab
	 */
	public PaperBackDetailsTab() {
				PageFactory.initElements(driver, this);
				log= Logger.getLogger(PaperBackDetailsTab.class);
			}
	
	/***
	 *This method is used to get Cost Range of PaperBack Edition 	
	 * @return cost range as string
	 */
	public String getpaperbackCostRange()
	{
		return paperbackCostRange.getText();
	}
	
	/***
	 * This method is used to get Starting Range cost for New book PaperBack Edition 
	 * @return cost as string
	 */
	public String getNewBookCostStartingRange()
	{
		return newBookCostStartingRange.getText();
	}
	
	/***
	 * This method is used to get Starting Range cost for Used book PaperBack Edition 
	 * @return cost as string
	 */
	public String getUsedBookCostStartingRange()
	{
		return usedBookCostStartingRange.getText();
	}
	
	/***
	 * This method is used to get Starting Range cost for Rental book PaperBack Edition 
	 * @return cost as string
	 */
	public String getRentalCostStartingRange()
	{
		return rentalBookCostStartingRange.getText();
		
	}
	
	/***
	 * This method is used to get Number of Buying choices for New book PaperBack Edition 
	 * @return String
	 */
	public String newBookBuyingChoices()
	{
		String cost=newBookBuyingChoice.getText().split("New")[0];
		return cost;
	}
	
	/***
	 * This method is used to get Number of Buying choices for Used book PaperBack Edition 
	 * @return String
	 */
	public String usedBookBuyingChoices()
	{
		String cost=usedBookBuyingChoice.getText().split("Used")[0];
		return cost;
	}
	
	/***
	 * This method is used to get Number of Buying choices for Rental book PaperBack Edition 
	 * @return String
	 */
	public String rentalBookBuyingChoices()
	{
		String cost=rentalBookBuyingChoice.getText().split("Rentals")[0];
		return cost;
	}
	
	
	/***
	 * This method is used get page containing all seller information
	 * @return BuyingChoicesPage
	 */
	public BuyingChoicesPage clickBuyingChoices()
	{
		
		try {
			clickOnElement(buyingOptions);
			
		} catch (Exception e) {
			log.error(e.getMessage());			
		}
		finally
		{
			return new BuyingChoicesPage();
		}
			
	}
	
}
