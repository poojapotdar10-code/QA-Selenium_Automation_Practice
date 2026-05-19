package Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonSearchIphone 
{
	public static void main(String[] args) throws InterruptedException 
	{
		//AssignQues: WATS to search for iphones mobile in http://Amazon.in App
				//using id locator
				
				WebDriver driver=new ChromeDriver();
				driver.get("https://www.amazon.co.jp/");
				
				driver.manage().window().maximize();
				
				By srch = By.id("twotabsearchtextbox");
				WebElement ele=driver.findElement(srch);
				
				ele.sendKeys("iphone 17 pro max");
				
				//SearchButton
				By bttn = By.id("nav-search-submit-button");
				WebElement ele1=driver.findElement(bttn);
				ele1.click();
				
				Thread.sleep(500);
				driver.close();
	}
}
