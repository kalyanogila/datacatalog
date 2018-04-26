package com.amazon.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.base.BaseTest;
import com.amazon.pages.BookDetailsPage;
import com.amazon.pages.BuyingChoicesPage;
import com.amazon.pages.HomePage;
import com.amazon.pages.OfferListingDetailsPage;
import com.amazon.pages.PaperBackDetailsTab;
import com.google.gson.JsonArray;

/***
 * OffersDetailsTest implements tests realted to different offers by sellers
 * based on condtion of book for paperback edition
 * @author  Kalyan Reddy Ogila
 *
 */
public class OffersDetailsTest extends BaseTest {

	HomePage homePage;
	BookDetailsPage bookDetailsPage;
	PaperBackDetailsTab paperback;
	BuyingChoicesPage buyingChoicePage;
	OfferListingDetailsPage offerListingPage;
	JSONObject json;
	JSONArray offersjson;
	Logger log;

	public OffersDetailsTest() {
		super();
	}


	@BeforeClass
	public void setUp() {
		initialization();
		log= Logger.getLogger(OffersDetailsTest.class);
		homePage = new HomePage();
		 JSONParser parser = new JSONParser();
		 try {
			 json =(JSONObject) parser.parse(new FileReader("testdata/CatalogBook.json"));
			
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
		
		PaperBackDetailsTab paperback= bookDetailsPage.clickOnPaperback();
		 buyingChoicePage= paperback.clickBuyingChoices();
		
	}
	
	public void validateSellerInfo(JSONArray offersjson,HashMap<String, List> offerdetails)
	{
		
		for(int i=0;i<offersjson.size();i++)
		{
			JSONObject seller=(JSONObject)  offersjson.get(i);
			if(offerdetails.containsKey(seller.get("seller").toString()))
			{
				Assert.assertEquals(offerdetails.get(seller.get("seller").toString()).get(0).toString(),seller.get("price").toString());
				Assert.assertEquals(offerdetails.get(seller.get("seller").toString()).get(1).toString(),seller.get("shipping").toString());
				Assert.assertEquals(offerdetails.get(seller.get("seller").toString()).get(2).toString(),seller.get("seller").toString());
				}
			}
		
	}
	
	 @Test
		public void verifyRentalBookSellerOffers()
		{
		 log.info("-----Start of test case verifyRentalBookSellerOffers in OffersDetailsTest-----");
			offerListingPage= buyingChoicePage.filterByBookCondition("rental");
			HashMap<String,List> offerdetails=offerListingPage.getNewBookOffers();
			offersjson=(JSONArray) json.get("rentalBookOffers");
			validateSellerInfo(offersjson, offerdetails);
			log.info("-----End of test case verifyRentalBookSellerOffers in OffersDetailsTest-----");		
		}
		
	
	 @Test
	public void verifyNewBookSellerOffers()
	{
		 log.info("-----Start of test case verifyNewBookSellerOffers in OffersDetailsTest-----");
		offerListingPage= buyingChoicePage.filterByBookCondition("newBook");
		HashMap<String,List> offerdetails=offerListingPage.getNewBookOffers();
		offersjson=(JSONArray) json.get("newBookOffers");
		validateSellerInfo(offersjson, offerdetails);
		log.info("-----End of test case verifyNewBookSellerOffers in OffersDetailsTest-----");	
	}
	
	
	 @Test
	public void verifyUsedLikeNewBookSellerOffers()
	{
		 log.info("-----Start of test case verifyUsedLikeNewBookSellerOffers in OffersDetailsTest-----");
		offerListingPage= buyingChoicePage.filterByBookCondition("usedLikenew");
		HashMap<String,List> offerdetails=offerListingPage.getNewBookOffers();
		offersjson=(JSONArray) json.get("usedLikeNewBookOffers");
		validateSellerInfo(offersjson, offerdetails);
		log.info("-----End of test case verifyUsedLikeNewBookSellerOffers in OffersDetailsTest-----");	
	}
	
	 @Test
	public void verifyUsedVeryGoodBookSellerOffers()
	{
		 log.info("-----Start of test case verifyUsedVeryGoodBookSellerOffers in OffersDetailsTest-----");
		offerListingPage= buyingChoicePage.filterByBookCondition("usedverygood");
		HashMap<String,List> offerdetails=offerListingPage.getNewBookOffers();
		offersjson=(JSONArray) json.get("usedVeryGoodBookOffers");
		validateSellerInfo(offersjson, offerdetails);
		log.info("-----End of test case verifyUsedVeryGoodBookSellerOffers in OffersDetailsTest-----");	
	}
	
	 @Test
	public void verifyUsedGoodBookSellerOffers()
	{ 
		 log.info("-----Start of test case verifyUsedGoodBookSellerOffers in OffersDetailsTest-----");
			offerListingPage= buyingChoicePage.filterByBookCondition("usedgood");
		HashMap<String,List> offerdetails=offerListingPage.getNewBookOffers();
		offersjson=(JSONArray) json.get("usedGoodBookOffers");
		validateSellerInfo(offersjson, offerdetails);
		log.info("-----End of test case verifyUsedGoodBookSellerOffers in OffersDetailsTest-----");	
	}
	
	
	@Test
	public void verifyUsedAcceptableBookSellerOffers()
	{
		log.info("-----Start of test case verifyUsedAcceptableBookSellerOffers in OffersDetailsTest-----");
		offerListingPage= buyingChoicePage.filterByBookCondition("usedacceptable");
		HashMap<String,List> offerdetails=offerListingPage.getNewBookOffers();
		offersjson=(JSONArray) json.get("usedAcceptableBookOffers");
		validateSellerInfo(offersjson, offerdetails);
		log.info("-----End of test case verifyUsedAcceptableBookSellerOffers in OffersDetailsTest-----");	
	}
	
	
	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}
	
	
	
}
