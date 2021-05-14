package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import base.TestBase;

public class SearchPage extends TestBase {
	
	@FindBy(xpath = "//*[@id='searchForm']/div[3]/div[4]/a/u")
	WebElement NewSearch;
	
	@FindBy(xpath="//body[1]/div[1]/header[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]")
	WebElement GTB; // Web element to open "Go To Booking pop up" 
	
	@FindBy(id="headerTBOHConfNo")
	WebElement GTBConfNo; // Web element to enter confirmation Number in  "Go To Booking pop up" 
	
	@FindBy(id="btnFind")
	WebElement GTBOpen; // Web element to click "Go" in "Go To Booking pop up" 
	
	
	public SearchPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void MovetoNewSearch() throws InterruptedException
	{
		driver.findElement(By.xpath("//a[@onclick='SearchForm.ClosePasswordNotification();']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='searchForm']/div[3]/div[4]/a/u")).click();
		//NewSearch.clear();
	}
	
	public HotelBooking OpenSpecificBooking() {
		GTB.click();
		//GTBConfNo.sendKeys(Config.getProperty("ConfirmationNo"));
		GTBConfNo.sendKeys(excel.getCellData("Sheet3", "TBOHConfNo", 2));
		GTBOpen.click();
		return new HotelBooking();
	}


}

