package com.crm.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;

import org.testng.Assert;


import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;


public class LoginPageTest extends TestBase {
	static LoginPage loginPage;
	static HomePage homePage;
	

	public LoginPageTest() {
		super();
		}

	@BeforeMethod
	public void setUp() throws IOException {
		initialization();
		loginPage = new LoginPage();
	}

	@Test(priority=1)
	public void loginPageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "CRMPRO - CRM software for customer relationship management, sales, and support.", "not match");
	}
	
	@Test(priority=2)
	public void crmLogoImageTest() {
		Assert.assertTrue(loginPage.validateCRMImage());
	}
	
	@Test(priority=3)
	public void loginTest() {
		homePage =loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
	}


	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
