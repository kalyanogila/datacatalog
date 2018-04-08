package com.amazon.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.amazon.base.BaseTest;

/***
 * HomePage class implements search criteria by scope on Amazon.com Home page
 * @author Kalyan Reddy Ogila
 *
 */
public class HomePage extends BaseTest {

	@FindBy(id="searchDropdownBox")
	WebElement searchScope;
	
	@FindBy(id="twotabsearchtextbox")
	WebElement searchKeyword;
	
	@FindBy(xpath="//input[@value='Go']")
	WebElement search;
	
	Logger log;
	
	/***
	 * Initializing HomePage Object
	 */
		public HomePage() {
			PageFactory.initElements(driver, this);
			log= Logger.getLogger(HomePage.class);
		}
	
	
		/***
		 * This method is used to select scope dropdown on Amazon.com
		 * @param name
		 */
		public void selectSearchScope(String name){
			
				try {
					selectValue(searchScope, name);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}		
		
		/***
		 * This method is used to search text in particular search scope on Amazon.com
		 * @param scope parameter is used for selecting scope ex:Books
		 * @param text parameter is used for text search ex: data Catalog
		 */
		public void searchText(String scope,String text)
		{
			
			try {
				selectSearchScope(scope);
			
				type(searchKeyword,text);
			
				clickOnElement(search);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
		}
		
		/***
		 * This method is to click the first book link based on search criteria of data catalog in Books scope
		 * @param name parameter is the book name to be clicked
		 * @return Book Details Page which contains book information
		 */
		public BookDetailsPage clickOnBook(String name)
		{
			try {
				clickLink(name);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			finally
			{
				return new BookDetailsPage();
			}
			
			
		}
	
		
		
}
