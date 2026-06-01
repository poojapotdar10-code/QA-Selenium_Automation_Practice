package TestCases;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import Generic_Utility.Excel_Utility;
import Generic_Utility.Java_Utility;
import Generic_Utility.PropertiesFile_Utility;
import Generic_Utility.WebDriver_Utility;

public class TC00_E2E_Flow 
{
	public static void main(String[] args) throws Throwable 
	{
	//--->access file and read data from file
					//FileInputStream fis=new FileInputStream("./src/test/resources/CommonData.properties");
					//Properties p=new Properties();
					//p.load(fis);
	//-->using Generic Utility
	//----->access Generic Utitilty file with creating object of thier
			Excel_Utility exl=new Excel_Utility();
			PropertiesFile_Utility propfile= new PropertiesFile_Utility();
			WebDriver_Utility wbdrvr =new WebDriver_Utility();
			Java_Utility javaUtil=new Java_Utility();
					
	//---fetch key and value from file and store in local varibale for further  use--using generic utility obejct
				
					String browser =propfile.toReadDataFromPropertiesFile("Browser");
					String url=propfile.toReadDataFromPropertiesFile("url");
					 String un = propfile.toReadDataFromPropertiesFile("username");
					 String pw = propfile.toReadDataFromPropertiesFile("password");
					 System.out.println("Generic file executed");
					 
	//--->start write actual script
					 WebDriver driver=null;
	//--->browser launch
					 if(browser.equals("Edge"))
					 {
						 driver=new EdgeDriver();
					 }
					 else  if(browser.equals("Chrome"))
					 {
						 driver=new ChromeDriver();
					 }
					 else  if(browser.equals("FireFox"))
					 {
						 driver=new FirefoxDriver();
					 }
					 else
					 {
						 driver=new EdgeDriver();
					 }
					 
					 driver.manage().window().maximize();
					 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
					 driver.get(url);
					 
	//---->find elements with locator--Login 	
					
						driver.findElement(By.id("user-name")).sendKeys(un);
						Thread.sleep(1200);   
						driver.findElement(By.name("password")).sendKeys(pw);
						Thread.sleep(1200);
						driver.findElement(By.xpath("//input[@type='submit']")).click();
			
	//---->Product details using Generic utility-->Excel utility object and method -->so line 89 to 97 code no need.
						String dropdown=exl.toReaddataFromExcel("Product", 4, 2);
						String productname1= exl.toReaddataFromExcel("Product", 4, 3);
						String productname2= exl.toReaddataFromExcel("Product", 5, 3);
						String dropdown1=exl.toReaddataFromExcel("Product", 6, 2);
						String productname3= exl.toReaddataFromExcel("Product", 6, 3);
						
						
	//--->Access excel 
						//FileInputStream fis2=new FileInputStream("./src/test/resources/testdata.xlsx");
						//Workbook wb1 = WorkbookFactory.create(fis2);
						//.out.println("File read complete");
	  //--->Access excel and fetch product name
						//Sheet s1=wb1.getSheet("Product");
						//Row r1=s1.getRow(5);
						//String dropdown = r1.getCell(2).getStringCellValue();
						//String productname1 = r1.getCell(3).getStringCellValue();
						System.out.println("Dropdown value  - "+dropdown);
						System.out.println("Product Name value  - "+productname1);
						System.out.println("Product Name value  - "+productname2);
						System.out.println("Dropdown value  - "+dropdown1);
						System.out.println("Product Name value  - "+productname3);
						
	 //---Below code iis hardcode
						Thread.sleep(2000);
						WebElement dd = driver.findElement(By.className("product_sort_container"));
						Select s=new Select(dd);
						//s.selectByVisibleText("Price (low to high)");
						
						s.selectByVisibleText(dropdown);
						Thread.sleep(2000);
						
		//--->Scroll to elemet
						WebElement ele = driver.findElement(By.xpath("//div[text()='"+productname1+"']"));  //Sauce Labs Onesie
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].scrollIntoView(true);", ele);
						//ele.click();
	                   Thread.sleep(2000);
						driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
						 System.out.println("Added add-to-cart-sauce-labs-onesie");
						Thread.sleep(2000);
						
						WebElement ele2 = driver.findElement(By.xpath("//div[text()='"+productname2+"']"));  //Sauce Labs Backpack
						JavascriptExecutor js1 = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].scrollIntoView(true);", ele2);
						//ele2.click();
						 Thread.sleep(2000);
					     driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
					     System.out.println("Added Sauce Labs Backpack");
						 Thread.sleep(2000);
					
						WebElement ele3 = driver.findElement(By.xpath("//div[text()='"+productname3+"']"));  //Sauce Labs Bolt T-Shirt
						JavascriptExecutor js2 = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].scrollIntoView(true);", ele3);
						//ele3.click();
						 Thread.sleep(2000);
					    driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
					     System.out.println("Added Sauce Labs Bolt T-Shirt");
						 Thread.sleep(2000);
					
		             //---> Add product to cart
		            //driver.findElement(By.xpath("//div[text()='Sauce Labs Onesie']")).click();
		            //driver.findElement(By.id("add-to-cart")).click();

	//---> Navigate to cart
						driver.findElement(By.className("shopping_cart_link")).click();
						Thread.sleep(2000);
						
	//--Validate the added product in cart is similar to which we added from prevoius page
						//String[] productIncart =null;
						String productIncart =driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
						
						for(int i=4;i<=6;i++) 
						{
							if(productIncart.equals(exl.toReaddataFromExcel("Product", i, 3))) 
							{
								System.out.println("Correct item added");
							}
						}
						Thread.sleep(2000);
	//-->Checkout
						driver.findElement(By.id("checkout")).click();
						Thread.sleep(2000);
						
     //-->Enter checkout details with actiona class
						Actions chk=new Actions(driver);
						chk.click(driver.findElement(By.id("first-name"))).sendKeys("Pooja").perform(); 
						Thread.sleep(2000);
						Actions chk1=new Actions(driver);
						chk1.click(driver.findElement(By.id("last-name"))).sendKeys("Dhaval").perform(); //enter data  by using action class
						Thread.sleep(2000);
						Actions chk3=new Actions(driver);
						WebElement zpcd=driver.findElement(By.id("postal-code"));  //hover element by using action class
						chk3.moveToElement(zpcd).perform();
						Thread.sleep(2000);
	//-->Enter checkout details with Robot class-keystroke
						System.out.println("Enter checkout details");

	//-->Cancel 
						driver.findElement(By.id("cancel")).click();
						Thread.sleep(2000);
						
						if (productIncart.equals(productname1))  //Sauce Labs Onesie
						{
							Thread.sleep(2000);
							driver.findElement(By.id("remove-sauce-labs-onesie")).click();
						}
						else
						{
						System.out.println("No item to remove");
						}
						Thread.sleep(2000);
	//-->Checkout
						driver.findElement(By.id("checkout")).click();
						Thread.sleep(2000);
			
						driver.findElement(By.id("first-name")).sendKeys("Pooja");
			           Thread.sleep(2000);
			
			          driver.findElement(By.id("last-name")).sendKeys("Dhaval");
			           Thread.sleep(2500);
			
	//--->Again enter the zipcode on checkout page with Robot class
						Robot enterDetails = new Robot();
						driver.findElement(By.id("postal-code")).click();
						enterDetails.keyPress(KeyEvent.VK_1);
						enterDetails.keyRelease(KeyEvent.VK_1);
						enterDetails.keyPress(KeyEvent.VK_3);
						enterDetails.keyRelease(KeyEvent.VK_3);
						enterDetails.keyPress(KeyEvent.VK_5);
						enterDetails.keyRelease(KeyEvent.VK_5);
						Thread.sleep(2000);

	//-->Cancel 
						driver.findElement(By.id("cancel")).click();
						Thread.sleep(2000);
						
	//--->Continue shopping
						driver.findElement(By.id("continue-shopping")).click();
						Thread.sleep(2000);
						
	//---> logout from sauce demo
						driver.findElement(By.id("react-burger-menu-btn")).click();
						Thread.sleep(1200);
						driver.findElement(By.id("logout_sidebar_link")).click();
						
						Thread.sleep(2000);
	//---> Closing browser
						driver.close();
					    System.out.println("All done");
						System.out.println("Robot class and Action class used");

	}
}
