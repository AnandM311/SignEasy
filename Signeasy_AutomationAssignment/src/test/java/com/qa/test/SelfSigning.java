package com.qa.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.qa.base.TestBase;

public class SelfSigning extends TestBase {

	TestBase testbase = new TestBase();
	String username = testbase.prop.getProperty("Email");
	String password = testbase.prop.getProperty("Password");
	static SelfSigning obj = new SelfSigning();

	@BeforeMethod
	public void setUp() {
		testbase.initialization();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id='auto_password_login']")).sendKeys(password);
		driver.findElement(By.xpath("//div//button[@id='auto_login']")).click();
	}

	// Self signing using signature
	@Test(priority = 1)
	public static void selfSigningUsingSignature() throws InterruptedException {

		obj.signDropDownClick();

		Thread.sleep(9000);
		driver.findElement(By.xpath("//div//nav//ul//li//a//span[@class='icon-signature']")).click();
		Actions action = new Actions(driver);
		Thread.sleep(4000);
		action.dragAndDropBy(driver.findElement(By.xpath("//div//nav//ul//li//a//span[@class='icon-signature']")), 10,
				15).release().build().perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='thumbnailView']/div/div/nav/ul/li[3]/a")).click();
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div/div[1]/div[1]/div/div/button[2]")).click();
		String tableName = driver.findElement(By.xpath(
				"//table[@class='fullWidth draft-table']/tbody/tr[3]/td[4]/div[@class='doc-type draft-tag text-center']"))
				.getText();
		System.out.println(tableName);
		Assert.assertEquals(tableName, "DRAFT");
		obj.closeBrowser();
	}

	// Self signing using date
	@Test(priority = 2)
	public static void selfSigningUsingDate() throws InterruptedException {

		obj.signDropDownClick();
		Thread.sleep(9000);
		driver.findElement(By.xpath("//*[@id='thumbnailView']/div/div/nav/ul/li[3]/a")).click();
		Actions action = new Actions(driver);
		Thread.sleep(3000);
		action.dragAndDropBy(driver.findElement(By.xpath("//*[@id='thumbnailView']/div/div/nav/ul/li[3]/a")), 03, 15)
				.release().build().perform();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div/div[1]/div[1]/div/div/button[2]")).click();
		String tableName = driver.findElement(By.xpath(
				"//table[@class='fullWidth draft-table']/tbody/tr[3]/td[4]/div[@class='doc-type draft-tag text-center']"))
				.getText();
		System.out.println(tableName);
		Assert.assertEquals(tableName, "DRAFT");
		obj.closeBrowser();
	}

	public void signDropDownClick() throws InterruptedException {
		driver.findElement(
				By.xpath("//div[@class='sidebar ng-scope']//aside//ul[2]//li[2]/a[@href='#/documents/original']"))
				.click();
		List<WebElement> signList = driver.findElements(By.xpath("//div[@class='button-two']"));
		for (int i = 0; i < signList.size(); i++) {
			if (i == 0) {
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@class='button-two']")).click();
				driver.findElement(By.xpath("//*[@id='signDropdown']/div/a[1]")).click();
			}
		}
	}

	public void closeBrowser()

	{
		driver.quit();
	}

}
