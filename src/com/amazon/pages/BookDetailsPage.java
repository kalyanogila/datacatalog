package com.amazon.pages;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.base.BaseTest;

/***
 * BookDetailsPage implements logic to get all book details
 * @author Kalyan Reddy Ogila
 *
 */
public class BookDetailsPage extends BaseTest{

	
	@FindBy(id="productTitle")
	WebElement bookTitle;
	
	@FindBy(xpath="//a[@class='a-link-normal contributorNameID']")
	WebElement author;
	
	@FindBy(id="bookEdition")
	WebElement edition;
	
	@FindBy(xpath="//span[contains(text(),'ISBN-13')]/following::span[1]")
	WebElement barCode13;
	
	@FindBy(xpath="//span[contains(text(),'ISBN-10')]/following::span[1]")
	WebElement barCode10;
	
	@FindBy(xpath="//table[@id='histogramTable']/tbody")
	WebElement customerReview;
	
	@FindBy(xpath="//span[@data-hook='total-review-count']")
	WebElement totalCustomerReviewCount;
	
	@FindBy(xpath="//span[@data-hook='rating-out-of-text']")
	WebElement totalRating;
	
	@FindBy(xpath="//li[@id='mediaTab_heading_1']")
	WebElement paperBackTab;
	
	@FindBy(xpath="//li[@id='mediaTab_heading_0']")
	WebElement kindleTab;
	
	@FindBy(xpath="//table[@id='histogramTable']/tbody/tr/td[1]")
	List<WebElement> rating;
	
	@FindBy(xpath="//table[@id='histogramTable']/tbody/tr/td[3]")
	List<WebElement> ratingPercentage;
	
	Logger log;
	
	HashMap<String,String> customerRating;
	
	
	/***
	 * Initializing BookDetailsPage
	 */
			public BookDetailsPage() {
				PageFactory.initElements(driver, this);
				customerRating=new HashMap<String,String>();
				log= Logger.getLogger(BookDetailsPage.class);
			}
	
	/***
	 * This method is used to get Book title
	 * @return book title as String
	 */
	public String getbookTitle()
	{
		
		return bookTitle.getText();
		
	}
	
	/***
	 * This method return book Author
	 * @return String
	 */
	public String getbookAuthor()
	{
		
		return author.getText();
		
	}
	
	/***
	 * This method returns book edition as String
	 * @return String
	 */
	public String getbookEdition()
	{
		
		return edition.getText();
		
	}
	
	/***
	 * This method returns 13 digit ISBN code as String
	 * @return String
	 */
	public String getISBN13code()
	{
		
		return barCode13.getText();
		
	}
	
	/***
	 * This method returns 10 digit ISBN code as String
	 * @return String
	 */
	public String getISBN10code()
	{
		
		return barCode10.getText();
		
	}
	
	
	/***
	 * This method returns Customer Ratings like star rating percentge 
	 * @return HashMap with start as key and percentage as value
	 */
	public HashMap getCustomerRating()
	{
		int rows=rowCount(customerReview);
		
		for(int i=0;i<rows;i++)
		{
			customerRating.put(rating.get(i).getText(),ratingPercentage.get(i).getText());
			System.out.println(rating.get(i).getText()+"...."+ratingPercentage.get(i).getText());
		}
		
		
		return customerRating;
	}
	
	/***
	 * This method returns number of customer reviews given for the book
	 * @return
	 */
	public String gettotalCustomerReviewCount()
	{
		return totalCustomerReviewCount.getText();
	}
	
	/***This method returns total rating out of 5 stars
	 * 
	 * @return
	 */
	public String gettotalRating()
	{
		return totalRating.getText();
	}
	
	/***
	 * This method returns all details present in Kindle Tab
	 * @return KindleDetailsTab page
	 */
	public KindleDetailsTab clickOnKindle()
	{
		try {
			clickOnElement(kindleTab);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		finally
		{
			return new KindleDetailsTab();
		}
		
	}
	
	/***
	 * This method returns all details present in Paperback Tab
	 * @return PaperBackDetailsTab page
	 */
	public PaperBackDetailsTab clickOnPaperback()
	{
		try {
			clickOnElement(paperBackTab);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		finally {
			return new PaperBackDetailsTab();
		}
		
	}
}
