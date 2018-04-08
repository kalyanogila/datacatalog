package com.amazon.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.amazon.base.BaseTest;
import com.amazon.pages.BookDetailsPage;
import com.amazon.pages.HomePage;
import com.amazon.pages.PaperBackDetailsTab;

/***
 * PaperBackDetailsTest implements test cases related to Paperback edition of book like
 * paperback book cost range low to high, New book purchase cost, rental cost and used book cost based on book condition
 * @author  Kalyan Reddy Ogila
 *
 */
public class PaperBackDetailsTest extends BaseTest {

	
	HomePage homePage;
	BookDetailsPage bookDetailsPage;
	PaperBackDetailsTab paperback;
	JSONObject json;
	JSONObject paperbackjson;
	Logger log = Logger.getLogger(PaperBackDetailsTest.class);

	public PaperBackDetailsTest() {
		super();
	}


	@BeforeClass
	public void setUp() {
		initialization();
		
		homePage = new HomePage();
		 JSONParser parser = new JSONParser();
		 try {
			 json =(JSONObject) parser.parse(new FileReader("testdata/CatalogBook.json"));
			 paperbackjson= (JSONObject)  json.get("paperbackdetails");
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
		homePage.searchText("search-alias=stripbooks", "dataCatalog");
		bookDetailsPage=homePage.clickOnBook("Catalog It!: A Guide to Cataloging School Library Materials, 3rd Edition");
		paperback= bookDetailsPage.clickOnPaperback();
		
	}
	
	@Test
	public void verifypaperbackCostRange()
	{
		log.info("-----Start of test case verifypaperbackCostRange in PaperBackDetailsTest-----");
			Assert.assertEquals( paperback.getpaperbackCostRange(),paperbackjson.get("paperbackCostRange").toString());
			log.info("-----End of test case verifypaperbackCostRange in PaperBackDetailsTest-----");
	}
	
	@Test
	public void verifyNewBookCostStartingRange()
	{
		log.info("-----Start of test case verifyNewBookCostStartingRange in PaperBackDetailsTest-----");
		Assert.assertEquals( paperback.getNewBookCostStartingRange(),paperbackjson.get("newBookCostStartingRange").toString());
		log.info("-----End of test case verifyNewBookCostStartingRange in PaperBackDetailsTest-----");
	}
	
	@Test
	public void verifyUsedBookCostStartingRange()
	{
		log.info("-----Start of test case verifyUsedBookCostStartingRange in PaperBackDetailsTest-----");
		Assert.assertEquals( paperback.getUsedBookCostStartingRange(),paperbackjson.get("usedBookCostStartingRange").toString());
		log.info("-----End of test case verifyUsedBookCostStartingRange in PaperBackDetailsTest-----");
	}
	
	@Test
	public void verifyRentalCostStartingRange()
	{
		log.info("-----Start of test case verifyRentalCostStartingRange in PaperBackDetailsTest-----");
		Assert.assertEquals( paperback.getRentalCostStartingRange(),paperbackjson.get("rentalBookCostStartingRange").toString());
		log.info("-----End of test case verifyRentalCostStartingRange in PaperBackDetailsTest-----");
	}
	
	@Test
	public void verifynewBookBuyingChoices()
	{
		log.info("-----Start of test case verifynewBookBuyingChoices in PaperBackDetailsTest-----");
		Assert.assertEquals( paperback.newBookBuyingChoices().trim(),paperbackjson.get("newBookBuyingChoices").toString());
		log.info("-----End of test case verifynewBookBuyingChoices in PaperBackDetailsTest-----");
	}
	
	
	@Test
	public void verifyusedBookBuyingChoices()
	{
		log.info("-----Start of test case verifyusedBookBuyingChoices in PaperBackDetailsTest-----");
		Assert.assertEquals( paperback.usedBookBuyingChoices().trim(),paperbackjson.get("usedBookBuyingChoices").toString());
		log.info("-----End of test case verifyusedBookBuyingChoices in PaperBackDetailsTest-----");
	}
	
	@Test
	public void verifyrentalBookBuyingChoices()
	{
		log.info("-----Start of test case verifyrentalBookBuyingChoices in PaperBackDetailsTest-----");
		Assert.assertEquals( paperback.rentalBookBuyingChoices().trim(),paperbackjson.get("rentalBookBuyingChoices").toString());
		log.info("-----End of test case verifyrentalBookBuyingChoices in PaperBackDetailsTest-----");
	}
	
	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}
	
	
}
