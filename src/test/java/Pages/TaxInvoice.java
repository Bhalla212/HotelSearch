package Pages;

import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class TaxInvoice extends TestBase{
	
	@FindBy(xpath="//div[@class='register-head text-center bold font-size-155']")
	WebElement PageHeading;
	
	@FindBy(xpath="//*[@class='gtaCityListTable row_cell mpsrl']/table/tbody/tr[2]/td[2]")
	WebElement HotelName;
	
	@FindBy(xpath="//a[contains(text(),'Back')]")
	WebElement BacktoHotelInvoice;
	
	public TaxInvoice() {
		PageFactory.initElements(driver, this);
	}
	
	public String VerifyTaxInvoice() {
		String Heading = PageHeading.getText().trim();
		return Heading;
	}
	
	
	public ArrayList<String> TaxDetails() {
		String HName = HotelName.getText().trim();
		ArrayList<String> TaxDetaillist=new ArrayList<String>();
		TaxDetaillist.add(HName);
		return TaxDetaillist;
	}
	
	public HotelInvoice BackToHotelInvoice() {
		BacktoHotelInvoice.click();
		return new HotelInvoice();
	}

}
