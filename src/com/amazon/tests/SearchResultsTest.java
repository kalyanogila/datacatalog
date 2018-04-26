package com.amazon.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.amazon.base.BaseTest;
import com.amazon.pages.BookDetailsPage;
import com.amazon.pages.HomePage;

/**
 * SearchResultsTest implements test cases related to book details like
 * 10-digit ISBN,13-digit ISDB, boot title, book author, book edition,
 * customer ratings and percentage based on start ratings
 * @author Kalyan Reddy Ogila
 *
 */
public class SearchResultsTest extends BaseTest {

	
	HomePage homePage;
	BookDetailsPage bookDetailsPage;
	JSONObject json;
	JSONObject reviewsjson;
	Logger log;
	

	public SearchResultsTest() {
		super();
	}


	@BeforeClass
	public void setUp() {
		initialization();

		
		log=Logger.getLogger(SearchResultsTest.class);
		
		
		homePage = new HomePage();
		 JSONParser parser = new JSONParser();
		 try {
			 json =(JSONObject) parser.parse(new FileReader("testdata/CatalogBook.json"));
			 reviewsjson=(JSONObject)  json.get("customerRating");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
	}
	
	@BeforeMethod
	public void searchBooks() {
		homePage.searchText("search-alias=stripbooks", "data Catalog");
		bookDetailsPage=homePage.clickOnBook("Catalog It!: A Guide to Cataloging School Library Materials, 3rd Edition");
	}
	
	@Test
	public void verifybooktitle()
	{
		
			log.info("-----Start of test case verifybooktitle in SearchResultsTest-----");
			Assert.assertEquals( bookDetailsPage.getbookTitle(),json.get("title").toString());
			log.info("-----End of test case verifybooktitle in SearchResultsTest-----");
		
	}
	
	@Test
	public void verifybookauthor()
	{
		log.info("-----Start of test case verifybookauthor in SearchResultsTest-----");
		Assert.assertEquals( bookDetailsPage.getbookAuthor(),json.get("author").toString());
		log.info("-----End of test case verifybookauthor in SearchResultsTest-----");
		
		
	}
	
	@Test
	public void verify13digitISBN()
	{
		log.info("-----Start of test case verify13digitISBN in SearchResultsTest-----");
			Assert.assertEquals(bookDetailsPage.getISBN13code(),json.get("ISBN13").toString());
			log.info("-----End of test case verify13digitISBN in SearchResultsTest-----");
	}
	
	@Test
	public void verify10digitISBN()
	{
		log.info("-----Start of test case verify10digitISBN in SearchResultsTest-----");
			Assert.assertEquals(bookDetailsPage.getISBN10code(),json.get("ISBN10").toString());
			log.info("-----End of test case verify10digitISBN in SearchResultsTest-----");
		
	}
	
	@Test
	public void verifyBookEdition()
	{
		log.info("-----Start of test case verifyBookEdition in SearchResultsTest-----");
			Assert.assertEquals(bookDetailsPage.getbookEdition(),json.get("edition").toString());
			log.info("-----End of test case verifyBookEdition in SearchResultsTest-----");
		
	}
	
	@Test
	public void verify5starRating()
	{
		log.info("-----Start of test case verify5starRating in SearchResultsTest-----");
		HashMap<String,String> customerrating=bookDetailsPage.getCustomerRating();
		Assert.assertEquals(customerrating.get("5 star"),reviewsjson.get("5 star").toString());
		log.info("-----End of test case verify5starRating in SearchResultsTest-----");
		
	}
	
	@Test
	public void verify4starRating()
	{
		log.info("-----Start of test case verify4starRating in SearchResultsTest-----");
		HashMap<String,String> customerrating=bookDetailsPage.getCustomerRating();
		Assert.assertEquals(customerrating.get("4 star"),reviewsjson.get("4 star").toString());
		log.info("-----End of test case verify4starRating in SearchResultsTest-----");
		
	}
	
	@Test
	public void verify3starRating()
	{
		log.info("-----Start of test case verify3starRating in SearchResultsTest-----");
		HashMap<String,String> customerrating=bookDetailsPage.getCustomerRating();
		Assert.assertEquals(customerrating.get("3 star"),reviewsjson.get("3 star").toString());
		log.info("-----End of test case verify3starRating in SearchResultsTest-----");
		
	}
	
	@Test
	public void verify2starRating()
	{
		log.info("-----Start of test case verify2starRating in SearchResultsTest-----");
		HashMap<String,String> customerrating=bookDetailsPage.getCustomerRating();
		Assert.assertEquals(customerrating.get("2 star"),reviewsjson.get("2 star").toString());
		log.info("-----End of test case verify2starRating in SearchResultsTest-----");
		
	}
	
	@Test
	public void verify1starRating()
	{
		log.info("-----Start of test case verify1starRating in SearchResultsTest-----");
		HashMap<String,String> customerrating=bookDetailsPage.getCustomerRating();
		Assert.assertEquals(customerrating.get("1 star"),reviewsjson.get("1 star").toString());
		log.info("-----End of test case verify1starRating in SearchResultsTest-----");
		
	}
	
	@Test
	public void verifytotalRating()
	{
		log.info("-----Start of test case verifytotalRating in SearchResultsTest-----");
		Assert.assertEquals(bookDetailsPage.gettotalRating(),reviewsjson.get("totalRating").toString());
		log.info("-----End of test case verifytotalRating in SearchResultsTest-----");
		
	}
	
	@Test
	public void verifytotalCustomerReviewsCount()
	{
		log.info("-----Start of test case verifytotalCustomerReviewsCount in SearchResultsTest-----");
		Assert.assertEquals(bookDetailsPage.gettotalCustomerReviewCount(),reviewsjson.get("totalCustomerReviews").toString());
		log.info("-----End of test case verifytotalCustomerReviewsCount in SearchResultsTest-----");
		
	}
	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}
	
}
