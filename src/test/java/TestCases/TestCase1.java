package TestCases;

import org.testng.annotations.Test;

import Pages.GetOTP;
import Pages.SearchPage;
import base.TestBase;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase1 extends TestBase  {
	
	GetOTP t;
	SearchPage search;
	
	public TestCase1()
	{
		super();
	}
	
	@BeforeTest
	public static void setUp()
	{
		Initialization();
	}
	
	@Test(priority=1)
	public void test1() throws InterruptedException
	{
		AdminLogin();
		t=new GetOTP();
		search=new SearchPage();
		t.gettingOt();
	}
	
	@Test(priority=2)
	public void MoveNewSearch() throws InterruptedException
	{
		Thread.sleep(2000);
		search.MovetoNewSearch();
	}
	

}
