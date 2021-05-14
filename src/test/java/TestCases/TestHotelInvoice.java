package TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.HotelBooking;
import Pages.HotelInvoice;
import Pages.SearchPage;
import Pages.TaxInvoice;
import base.TestBase;
import utilities.TestUtil;

import java.util.ArrayList;


public class TestHotelInvoice extends TestBase{
	
	SearchPage SP;
	HotelBooking HB;
	HotelInvoice HI;
	TaxInvoice TI;
	ArrayList<String> HotelBookingDetails;
	SoftAssert softassert = new SoftAssert();
	
	public TestHotelInvoice()
	{
		super();
	}
	
	@BeforeClass
	public static void setUp()
	{
		Initialization();
		AdminLogin();
	}
	
	@Test(priority=1)
	public void VerifyHotelBookingPage() {
		SP=new SearchPage();
		SP.OpenSpecificBooking();
		HB=new HotelBooking();
		HB.GetBookingStatus();
		Assert.assertNotNull(HB.GetBookingStatus(), "Booking Page is not Opened");
		HotelBookingDetails = HB.GetBookingDetails();
	}
	
	
	@Test(priority=2, enabled=true)
	public void VerifyDownloadHotelInvoice() throws InterruptedException {
		HB.DownloadInvoice();
		Thread.sleep(6000);
		String path = "C:\\Users\\aman\\Downloads";
		System.out.println(path);
		Assert.assertTrue(TestUtil.isFileDownloaded(path, "HotelInvoice"), "Hotel Invoice is not downloaded");
		Thread.sleep(3000);
	}
	
	
	
	@Test(priority=3, enabled=true)
	public void VerifyHotelInvoicePage() {
		
		HB.OpenHotelInvoice();
		HI = new HotelInvoice();
		HI.HotelInvoicePage();
		Assert.assertNotNull(HI.HotelInvoicePage(), "Invoice Page is not Opened");
		
	}
	
	
	@Test(priority=4, enabled=true)
	public void VerifyInvoiceDetails() {
		softassert.assertEquals(HI.GetInvoiceDetails().get(0), excel.getCellData("Sheet3", "TBOHConfNo", 2),"Confirmation Number did not match");
		softassert.assertEquals(HI.GetInvoiceDetails().get(1), HotelBookingDetails.get(1),"Hotel Name did not match");
		
	}
	
	@Test(priority=5, enabled=true)
	public void VerifyLinksOnInvoice() throws InterruptedException {
		String EmailMessage = HI.EmailInvoice();
		softassert.assertEquals(EmailMessage, "Email Sent Successfully","Mail has been sent successfully");
		HI.DownloadInvoice();
		String path = "C:\\Users\\aman\\Downloads";
		softassert.assertTrue(TestUtil.isFileDownloaded(path, "HotelInvoice"), "Hotel Invoice is not downloaded from Invoice page");
		if(IsElementPresent("//b[contains(text(),'Tax Invoice')]")) {
			HI.GotoTaxInvoice();
			Thread.sleep(2000);
			TI = new TaxInvoice();
			softassert.assertEquals(TI.VerifyTaxInvoice(), "Tax Invoice","Tax Invoice page is opened");
			softassert.assertEquals(TI.TaxDetails().get(0), HotelBookingDetails.get(1),"Hotel Name on Tax invoice is not matched");
			TI.BackToHotelInvoice();
			Thread.sleep(2000);
			softassert.assertNotNull(HI.HotelInvoicePage(), "Invoice Page is not Opened");
		}
		/*HI.GoBackToHotelBooking();
		Thread.sleep(2000);
		softassert.assertNotNull(HB.GetBookingStatus(), "Booking Page is not Opened");*/
	}
	
	
	@Test(priority=6, enabled=true)
	public void VerifyRaiseDispute() throws InterruptedException {
		//System.out.println(HI.RaiseDispute());
		softassert.assertEquals(HI.RaiseDispute(), "Invoice updated successfully.","Dispute has not been raised successfully");
		HI.GoBackToHotelBooking();
		Thread.sleep(2000);
		softassert.assertNotNull(HB.GetBookingStatus(), "Booking Page is not Opened");
	}
	
	
	@AfterClass
	public void teardown(){
		driver.close();
	}

}
