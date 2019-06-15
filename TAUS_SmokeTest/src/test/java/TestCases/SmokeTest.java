package TestCases;

	import java.util.Iterator;
	import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


	public class SmokeTest {
		public WebDriver driver;

		@BeforeClass
		public void HP_LoginTAUS() {
			System.setProperty("webdriver.chrome.driver", "E:\\Selenium-Java\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println("Go to TAUS website");
			driver.get("https://www.theaustralian.com.au/");
			// WebElement
			// element=driver.findElement(By.xpath("//*[@id='header']//div[@class='header__user']//a[@class='header__user-login']"));
			WebDriverWait wait = new WebDriverWait(driver, 10);
			//wait until 'login' button is displayed
			WebElement header_login = wait.until(ExpectedConditions.visibilityOfElementLocated(
					(By.xpath("//*[@id='header']//div[@class='header__user']//a[@class='header__user-login']"))));
			// Thread.sleep(2000);
			// element.click();
			System.out.println("click on login button");
					header_login.click();

			WebDriverWait hp_wait = new WebDriverWait(driver, 10);
			WebElement loginpage_email = hp_wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@id='r-login-container']//input[@type='email']")));
			// Thread.sleep(2000);
			System.out.println("Auth0 login page URL is:\t" + driver.getCurrentUrl());
			System.out.println("Enter username, password and click on submit button\n");

			loginpage_email.sendKeys("ramya.balusulapalem@gmail.com");
			WebElement loginpage_password = driver
					.findElement(By.xpath("//div[@id='r-login-container']//input[@type='password']"));
			loginpage_password.sendKeys("News@123");
			WebElement loginpage_submit = driver
					.findElement(By.xpath("//div[@id='r-login-container']//button[@type='submit']"));
			loginpage_submit.click();
			// Thread.sleep(2000);
			WebDriverWait hpRedirect_wait = new WebDriverWait(driver, 10);
			WebElement loggedinUser = hpRedirect_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id='header']//div[@class='header__user-status header__user-logged-in']//a[@class='header__user-name']//span")));

			System.out.println("Logged in username is:\t" + loggedinUser.getText());
		}

		@Test(priority = 1)
		public void HP_TristateIssue() {
			// Logged in promo editorial- learn more is verified:
			WebElement Loggedin_Promo = driver.findElement(By.xpath("//a[@class='promo-block__learnmore']"));
			if (Loggedin_Promo.isDisplayed()) {
				System.out.println("\nNo tristate issue: Loggedin promo is displayed on RHC");
			}
			String check_auth0User = driver.findElement(By.tagName("body")).getAttribute("class");
			if (check_auth0User.contains(check_auth0User)) {
			
				System.out.println("\nLogged in Auth 0 user is identified by this attribute value containing 'user-auth-subscriber'\nFull value is:\t" + check_auth0User);
			}
		}

		@Test(priority=2)
		public void Username_Dropdown()
		{
			WebElement header_Dropdown=driver.findElement(By.xpath("//a[@class='header__user-name']"));
			header_Dropdown.click();
			WebElement User_WelcomeMsg=driver.findElement(By.xpath("//p[@class='header__account-name']"));
			System.out.println("\nUser welcome msg is:\t"+User_WelcomeMsg.getText());
			
			List<WebElement>  Menu_MyAccount=driver.findElements(By.xpath("//ul[@id='menu-my-account']"));
			 Iterator<WebElement> itr = Menu_MyAccount.iterator();
			 while(itr.hasNext()) {
			 WebElement menu = itr.next();
			 System.out.println("Header username menu list items are:\n"+menu.getText());
			 }
		}
		
		@Test(priority = 3)
		public void HP_TodaysPaper() throws InterruptedException {
			// Click on "Todays Paper" on home page below navigation bar
			WebElement TodaysPaper = driver.findElement(By.xpath("//a[contains(text(),\"Today's Paper\")]"));
			TodaysPaper.click();
			driver.navigate().refresh();
			System.out.println("DPE page_'Read' button page URL is" + driver.getCurrentUrl());
			// wait for the page to open and "read" button displayed
			WebDriverWait PaperRead_Button_Wait = new WebDriverWait(driver, 10);
			WebElement Click_Read_Button = PaperRead_Button_Wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Read')]")));
			// click on "Read" button
			Click_Read_Button.click();
			System.out.println("DPE page URL is:\t" + driver.getCurrentUrl());
			WebDriverWait FrontCoverWait = new WebDriverWait(driver, 10);
			WebElement FrontCoverElement = FrontCoverWait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='sections_container']")));
			System.out.println("Text displayed is: " + FrontCoverElement.getText());
			// Capture and display all the sections in Front Cover container
			// List<WebElement>
			// Sections_Container=driver.findElements(By.xpath("//div[@class='sections_container']//div[@class=contains(text(),'sectionListContainer')]//ul[@class='sections_list']"));
			// Iterator<WebElement> itr = Sections_Container.iterator();
			// while(itr.hasNext()) {
			// WebElement row = itr.next();
			// System.out.println(row.getText());
			System.out.println("1st Default selected article page is:\t" + driver
					.findElement(By.xpath("//div[@class='articles_container']//div[@class='selected_article']")).getText());
			WebDriverWait Next_pageWait = new WebDriverWait(driver, 10);
			// Next_pageWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='replicaView_thumbnailViewer'][class=contains(text(),'closed')]")));

			WebElement Next_pageElement1 = Next_pageWait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//div[@id='content']//div[@id='next-page']"))));
			// WebElement
			// NextPage_Button=driver.findElement(By.xpath("//div[@id='content']//div[@id='next-page']"));
			// NextPage_Button.click();
			Next_pageElement1.click();
			// click on next button
			// System.out.println("Click on next button");
			// Actions actions = new Actions(driver);
			//
			// actions.moveToElement(Next_pageElement).click().perform();
			// next article
			Thread.sleep(2000);
			WebDriverWait Next_articleWait1 = new WebDriverWait(driver, 10);
			WebElement NextArticleElement1 = Next_articleWait1.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='articles_container']//div[@class='selected_article']")));
			System.out.println("2nd Next selected article page is:\t" + NextArticleElement1.getText());
			// Next article
			WebElement Next_pageElement2 = Next_pageWait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//div[@id='content']//div[@id='next-page']"))));
			Next_pageElement2.click();
			Thread.sleep(2000);
			WebDriverWait Next_articleWait2 = new WebDriverWait(driver, 10);
			WebElement NextArticleElement2 = Next_articleWait2.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='articles_container']//div[@class='selected_article']")));
			System.out.println("3rd Next selected article page is:\t" + NextArticleElement2.getText());
			// Next article
			WebElement Next_pageElement3 = Next_pageWait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//div[@id='content']//div[@id='next-page']"))));
			Next_pageElement3.click();
			Thread.sleep(2000);

			WebDriverWait Next_articleWait3 = new WebDriverWait(driver, 10);
			WebElement NextArticleElement3 = Next_articleWait3.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='articles_container']//div[@class='selected_article']")));
			System.out.println("4th Next selected article page is:\t" + NextArticleElement3.getText());
			System.out.println("end of the tests");
		}
		

		@AfterClass
		public void afterClass() {
			driver.quit();
		}

	}

