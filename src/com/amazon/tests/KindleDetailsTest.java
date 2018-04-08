package com.amazon.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
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
import com.amazon.pages.KindleDetailsTab;

/***
 * KindleDetailsTest implements test cases related verifying kindle edition of book features like
 * number of pages, pageflip enabled or not, cost, supported devices and any enhanced type settings enabled or not
 * @author  Kalyan Reddy Ogila
 *
 */
public class KindleDetailsTest  extends BaseTest{

	
	HomePage homePage;
	BookDetailsPage bookDetailsPage;
	KindleDetailsTab kindle;
	JSONObject json;
	JSONObject kindlejson;
	Logger log = Logger.getLogger(PaperBackDetailsTest.class);

	public KindleDetailsTest() {
		super();
	}


	@BeforeClass
	public void setUp() {
		initialization();
		
		homePage = new HomePage();
		 JSONParser parser = new JSONParser();
		 try {
			 json =(JSONObject) parser.parse(new FileReader("testdata/CatalogBook.json"));
			  kindlejson= (JSONObject)  json.get("kindledetails");
				
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
		kindle= bookDetailsPage.clickOnKindle();
		
	}
	
	@Test
	public void verifyKindleCost()
	{
		log.info("-----Start of test case verifyKindleCost in KindleDetailsTest-----");
		Assert.assertEquals( kindle.getKindlecost(),kindlejson.get("cost").toString());
		log.info("-----End of test case verifyKindleCost in KindleDetailsTest-----");	
	}
	
	@Test
	public void verifyKindlePages()
	{
		log.info("-----Start of test case verifyKindlePages in KindleDetailsTest-----");
		Assert.assertEquals( kindle.getKindleLength(),kindlejson.get("pages").toString());
		log.info("-----End of test case verifyKindlePages in KindleDetailsTest-----");	
	}
	
	@Test
	public void verifyEnhancedSetting()
	{
		log.info("-----Start of test case verifyEnhancedSetting in KindleDetailsTest-----");
		Assert.assertTrue(kindle.isEnhancedTypesetting());
		log.info("-----End of test case verifyEnhancedSetting in KindleDetailsTest-----");
	}
	
	@Test
	public void verifyPageFlipEnabled()
	{
		log.info("-----Start of test case verifyPageFlipEnabled in KindleDetailsTest-----");
			Assert.assertTrue(kindle.isPageFlipEnabled());
		log.info("-----End of test case verifyPageFlipEnabled in KindleDetailsTest-----");
	}
	
	
	@Test
	public void verifySupportedDevices()
	{
		boolean status=true;
		log.info("-----Start of test case verifySupportedDevices in KindleDetailsTest-----"); 
		JSONArray kindledevices=(JSONArray) kindlejson.get("supporteddevices");
		List<String> supporteddevices=	kindle.getSupportedDevices();
		for(int i=0;i<kindledevices.size();i++)
		{
			if(!supporteddevices.contains( kindledevices.get(i).toString()))
			{
				status=false;
				break;
			}
					
		}
		
		Assert.assertTrue(status,"Not alldevices mentioned in test data are supported devices");
		log.info("-----End of test case verifySupportedDevices in KindleDetailsTest-----");
	}
	
	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}
	
	
}
