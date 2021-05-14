package Pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.TestBase;

public class GetOTP extends TestBase {
	
	@FindBy(xpath = "//select[@id='select_Company']")
	WebElement companyDrop;
	
	@FindBy(id="UName")
	WebElement username;
	
	@FindBy(id="Password")
	WebElement password;
	
	@FindBy(xpath = "//a[@onclick='VarifyLogin()']/img")
	WebElement VerifyLoginButton;
	
	@FindBy(xpath = "//a[contains(text(),'Query Builder')]")
	WebElement clickOnQueryBuilder;
	
	@FindBy(xpath = "//select[@name='ctl00$ContentPlaceHolder1$ddl_select']")
	WebElement placeHolder;
	
	
	public GetOTP()
	  {
		  PageFactory.initElements(driver, this);
	  }
	
	public  void gettingOt() throws InterruptedException
	{
		driver.switchTo().newWindow(WindowType.TAB);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1)); // switches to new tab
		driver.get("http://awssa.tbomena.com");
		WebElement drop = companyDrop;
		Select select = new Select(drop);
		select.selectByIndex(2);
		username.sendKeys("geetar");
		password.sendKeys("12345");
		VerifyLoginButton.click();
		Thread.sleep(2000);
		clickOnQueryBuilder.click();
		WebElement drop1 = placeHolder;
		Select select1 = new Select(drop1);
		select1.selectByValue("TBOMena");
		WebElement textarea=driver.findElement(By.xpath("//textarea[@id='ctl00_ContentPlaceHolder1_txtQryForEdit']"));
		textarea.click();
		textarea.sendKeys("select top 10 * from otpverification Where memberId=68298 order by 1 desc");
		driver.findElement(By.id("btnShow")).click();
		String OTPValue=driver.findElement(By.xpath("//*[@id='div_Result']/table/tbody/tr[2]/td[5]")).getText();
		driver.switchTo().window(tabs.get(0));
		driver.findElement(By.id("otpValue")).sendKeys(OTPValue);
		driver.findElement(By.id("btnlogin")).click();
	}
	
	

}
