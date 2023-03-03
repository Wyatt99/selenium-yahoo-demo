package com.yahoo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class YahooTests {
	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeMethod(alwaysRun = true)
	private void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test(priority = 1)
	public void newsTitleTest() {
		// open url
		driver.get("http://www.yahoo.com/");

		// find news header and click it
		WebElement newsHeader = driver.findElement(By.id("root_2"));
		newsHeader.click();

		// Get the news title
		String expectedTitle = "Yahoo News - Latest News & Headlines";
		String actualTitle = driver.getTitle();

		// Assert Title
		Assert.assertEquals(expectedTitle, actualTitle, "Actual title does not match expected");
	}

	@Test(priority = 2)
	public void politicsTitleTest() {
		// open url
		driver.get("https://news.yahoo.com/");

		// find politics header and click it
		WebElement politicsHeader = driver.findElement(By.id("root_2"));
		politicsHeader.click();

		// Get the politics title
		String expectedTitle = "Politics Latest News and Headlines | Yahoo News - Latest News & Headlines";
		String actualTitle = driver.getTitle();

		// Assert Title
		Assert.assertEquals(expectedTitle, actualTitle, "Actual title does not match expected");
	}

	@Test(priority = 3)
	public void financeTitleTest() {
		// open url
		driver.get("http://www.yahoo.com/");

		// find finance header and click it
		WebElement financeHeader = driver.findElement(By.id("root_3"));
		financeHeader.click();

		// Get the finance title
		String expectedTitle = "Yahoo Finance - Stock Market Live, Quotes, Business & Finance News";
		String actualTitle = driver.getTitle();

		// Assert Title
		Assert.assertEquals(expectedTitle, actualTitle, "Actual title does not match expected");
	}

	@Test(priority = 4)
	public void successfulLoginTest() {
		// open url
		driver.get("http://www.yahoo.com/");

		// click log in button
		WebElement loginButton = driver.findElement(By.id("ybarAccountProfile"));
		loginButton.click();

		// enter email
		WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable((By.id("login-username"))));
		emailField.sendKeys("qarox@yahoo.com");

		// click next button
		WebElement nextButton = driver.findElement(By.id("login-signin"));
		nextButton.click();

		// enter password
		WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable((By.id("login-passwd"))));
		passwordField.sendKeys("Test.123");

		// click next button nextButton.click()
		nextButton = driver.findElement(By.id("login-signin"));
		nextButton.click();

		// hover over profile picture
		WebElement profilePicture = driver.findElement(By.id("ybarAccountProfile"));
		Actions hover = new Actions(driver);
		hover.moveToElement(profilePicture).perform();

		// click on add or manage accounts
		WebElement manageAccounts = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//html[@id='atomic']//div[@id='ybarAccountMenuBody']//span[.='Add or manage accounts']")));
		manageAccounts.click();

		// assert username
		WebElement usernameField = driver.findElement(By.className("name"));
		String expectedUsername = "A Tester";
		String actualUsername = usernameField.getText();
		Assert.assertEquals(actualUsername, expectedUsername, "Actual does not match expected");

	}

	@Test(priority = 5)
	public void inccorectPasswordTest() {
		// open url
		driver.get("http://www.yahoo.com/");

		// click log in button
		WebElement loginButton = driver.findElement(By.id("ybarAccountProfile"));
		loginButton.click();

		// enter email
		WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable((By.id("login-username"))));
		emailField.sendKeys("qarox@yahoo.com");

		// click next button
		WebElement nextButton = driver.findElement(By.id("login-signin"));
		nextButton.click();

		// enter password
		WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable((By.id("login-passwd"))));
		passwordField.sendKeys("incorrect");

		// click next button nextButton.click()
		nextButton = driver.findElement(By.id("login-signin"));
		nextButton.click();
		
		//Verify Error message
		WebElement errorElement = wait.until(ExpectedConditions.elementToBeClickable((By.className("error-msg"))));
		String expectedMessage = "Invalid password. Please try again";
		String actualMessage = errorElement.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message does not match expected.");
	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		// close browser
		driver.quit();
	}
}
