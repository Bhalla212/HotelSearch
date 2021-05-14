package Pages;

import java.io.File;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class HotelBooking extends TestBase{
	
	@FindBy(id="btnInvoice")
	WebElement HotelInvoicebtn;
	
	@FindBy(id="downloadInvoice")
	WebElement DownloadInvoice;
	
	@FindBy(xpath="//*[@class=\"colm3 btnright mwidth text-align-right\"]")
	WebElement HotelConfNo;
	
	@FindBy(xpath="//*[@class='hotel_name']")
	WebElement Hname;
	
	@FindBy(xpath="//*[@id='voucherStatus']")
	WebElement status;
	
	
	
	public HotelBooking() {
		PageFactory.initElements(driver, this);
	} 
	
	public String GetHotelConfirmationNumber() throws InterruptedException {
		Thread.sleep(2000);
		String ABC[] = HotelConfNo.getText().split(":");
		return ABC[1];
	}
	
	public String GetHotelName() {
		String Name = Hname.getText().trim();
		String t[] = Name.split(",");
		return t[0];
	}
	
	
	public ArrayList<String> GetBookingDetails(){
		String confNo[] = HotelConfNo.getText().trim().split(":");
		String Name[] = Hname.getText().trim().split(",");
		ArrayList<String> BookingDetails = new ArrayList<String>();
		BookingDetails.add(confNo[1]); // Hotel Confirmation Number
		BookingDetails.add(Name[0].trim());   // Hotel Name 
		return BookingDetails;
	}
	
	
	public String GetBookingStatus() {
		String BookingStatus = status.getText();
		return BookingStatus;
	}
	
	public HotelInvoice OpenHotelInvoice() {
		
		HotelInvoicebtn.click();
		return new HotelInvoice();
	}
	
	public void DownloadInvoice() throws InterruptedException {
		DownloadInvoice.click();
		driver.findElement(By.xpath("//input[@onclick='downloadInvoice()']")).click();
		Thread.sleep(9000);
	}
	
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		  File dir = new File(downloadPath);
		  File[] dirContents = dir.listFiles();
		  for (int i = 0; i < dirContents.length; i++) {
		      if (dirContents[i].getName().contains(fileName)) {
		          // File has been found, it can now be deleted:
		          dirContents[i].delete();
		          return true;
		      }
		          }
		      return false;
		  } 
	
	
	

}
