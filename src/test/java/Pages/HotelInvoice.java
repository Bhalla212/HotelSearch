package Pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.TestBase;

public class HotelInvoice extends TestBase{
	
	@FindBy(xpath="//*[@class='register-head text-center bold font-size-155']")
	WebElement pageheading;

	@FindBy(xpath="//div[contains(text(),'TBOH Confirmation No:')]")
	WebElement ConfirmationNo;
	
	@FindBy(xpath="//*[@class=\"gtaCityListTable row_cell mpsrl\"]/table/tbody/tr[2]/td")
	WebElement HotelName;
	
	@FindBy(xpath="//a[contains(text(),'Email Invoice')]")
	WebElement EmailInvoice;
	
	@FindBy(xpath="//a[contains(text(),'Download Invoice')]")
	WebElement DownloadInvoicelink;
	
	@FindBy(xpath="//b[contains(text(),'Tax Invoice')]")
	WebElement TaxInvoice;
	
	@FindBy(xpath="//a[contains(text(),'Back')]")
	WebElement BacktoHotelBooking;
	
	
	public HotelInvoice() {
		PageFactory.initElements(driver, this);
	}
	
	
	
	public String HotelInvoicePage() {
		String heading = pageheading.getText().trim();
		return heading;
	}
	
	
	public ArrayList<String> GetInvoiceDetails() {
		String ABC = ConfirmationNo.getText().trim();
		String a[] = ABC.split(":");
		String ConfNumber = a[1].trim();
		String Hname= HotelName.getText().trim();
		ArrayList<String> list=new ArrayList<String>();
		list.add(ConfNumber);
		list.add(Hname);
		return list;
	
	}
	
	
	public String EmailInvoice() throws InterruptedException {
		EmailInvoice.click();
		Thread.sleep(4000);
		//driver.findElement(By.xpath("//button[@onclick='SendMail()'and@xpath=1]")).click();
		driver.findElement(By.xpath("//body/div[@id='myhide']/div[@id='contener']/div[@id='menu']/div[5]/div[1]/form[5]/div[3]/div[1]/div[1]/span[1]/div[1]/div[1]/div[2]/button[1]")).click();
		String Message=driver.findElement(By.xpath("//*[@class=\"tbosuccess\"]")).getText();
		return Message;
	}
	
	
	public void DownloadInvoice() throws InterruptedException {
		DownloadInvoicelink.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@onclick='downloadInvoice()'and@value='Download PDF']")).click();
		Thread.sleep(9000);
		
	}
	
	public TaxInvoice GotoTaxInvoice() throws InterruptedException {
		TaxInvoice.click();
		Thread.sleep(3000);
		return new TaxInvoice();
	}
	
	
	public HotelBooking GoBackToHotelBooking() {
		BacktoHotelBooking.click();
		return new HotelBooking();
	}
	
	
	public String RaiseDispute() throws InterruptedException {
		String Message = null;
		if(IsElementPresent("//a[contains(text(),'Raise Dispute')]")) {
		driver.findElement(By.xpath("//a[contains(text(),'Raise Dispute')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("staffRemarksTxt")).sendKeys("Test Dispute flow");
		Select drpAssignTo = new Select(driver.findElement(By.id("AssignDisputeSelectId")));
		drpAssignTo.selectByIndex(1);
		Select drpreason = new Select(driver.findElement(By.id("SelectReasonSelectId")));
		drpreason.selectByIndex(1);
		driver.findElement(By.xpath("//input[@type='button'and@value='Update']")).click();
		Thread.sleep(2000);
		Message = driver.findElement(By.xpath("//div[@id='ErrorDiv']")).getText().trim();
		}else {
			Message = "Dispute has already been raised";
		}
		return Message;
	}
	
}
