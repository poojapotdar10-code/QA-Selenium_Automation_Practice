package POM_Testcases;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

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
import POM_Object_Repository.CartPage_POMClassFile;
import POM_Object_Repository.CheckoutPage_POMClassFile;
import POM_Object_Repository.ProductPage_POMClassFile;
import POM_Object_Repository.loginPage_POMClassFile;

public class TC00_E2E_Flow_POMusing {

	public static void main(String[] args) throws Throwable 
	{

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
						 
		//---to avoid hardcore data,we use POM..creatd in src/main/java-object repository
		//--using POM-- need to create object of POM class file and use object with getter method which we created in POM class file
									 loginPage_POMClassFile lp=new loginPage_POMClassFile(driver);
									 ProductPage_POMClassFile pp=new ProductPage_POMClassFile(driver);
									 CartPage_POMClassFile cp=new CartPage_POMClassFile(driver);
									 CheckoutPage_POMClassFile checkout=new CheckoutPage_POMClassFile(driver);
						 
		//---->find elements with locator--Login 	
						
							//driver.findElement(By.id("user-name")).sendKeys(un);
							lp.getUser().sendKeys(un);                     //LoginPOM getter method
							Thread.sleep(1200);   
							//driver.findElement(By.name("password")).sendKeys(pw);
							lp.getPwd().sendKeys(pw);                    //LoginPOM getter method
							Thread.sleep(1200);
							//driver.findElement(By.xpath("//input[@type='submit']")).click();
							lp.getLoginBtn().click();                           //LoginPOM getter method
							System.out.println("login POM-Username,Pwd,login btn click");
				
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
							//WebElement dd = driver.findElement(By.className("product_sort_container"));
							WebElement dd=pp.getdrpdwn();                    //Product POM getter method
							Select s=new Select(dd);
							//s.selectByVisibleText("Price (low to high)");
							
							s.selectByVisibleText(dropdown);
							Thread.sleep(2000);
							
			//--->Scroll to elemet
							//WebElement ele = driver.findElement(By.xpath("//div[text()='"+productname1+"']"));  //Sauce Labs Onesie
							WebElement ele=pp.getProdname1();                 //Product POM getter method
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].scrollIntoView(true);", ele);
							//ele.click();
		                   Thread.sleep(2000);
							//driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
							 pp.getAddCartBtn().click();                                 //Product POM getter method
							 System.out.println("Added add-to-cart-sauce-labs-onesie");
							Thread.sleep(2000);
							
							//WebElement ele2 = driver.findElement(By.xpath("//div[text()='"+productname2+"']"));  //Sauce Labs Backpack
							WebElement ele2 =pp.getProdname2();                //Product POM getter method
							JavascriptExecutor js1 = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].scrollIntoView(true);", ele2);
							//ele2.click();
							 Thread.sleep(2000);
						    //driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
						     pp.getAddCartBtn().click();                                      //Product POM getter method
						     System.out.println("Added Sauce Labs Backpack");
							 Thread.sleep(2000);
						
							//WebElement ele3 = driver.findElement(By.xpath("//div[text()='"+productname3+"']"));  //Sauce Labs Bolt T-Shirt
							WebElement ele3 = pp.getProdname3();                  //Product POM getter method
							JavascriptExecutor js2 = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].scrollIntoView(true);", ele3);
							//ele3.click();
							 Thread.sleep(2000);
						   // driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
						    pp.getAddCartBtn().click();                                         //Product POM getter method
						     System.out.println("Added Sauce Labs Bolt T-Shirt");
							 Thread.sleep(2000);
							 
							System.out.println("Product POM-3 product--Sauce Labs Onesie,Sauce Labs Backpack,Sauce Labs Bolt T-Shirt");

		//---> Navigate to cart
							//driver.findElement(By.className("shopping_cart_link")).click();
							pp.getCartIcon().click();                                    //Product POM getter method
							Thread.sleep(2000);
							
		//--Validate the added product in cart is similar to which we added from prevoius page
							//String[] productIncart =null;
							//String productIncart =driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
							String  productIncart =cp.getInventoryItemname().getText();                   //Cart POM getter method
							
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
							//chk.click(driver.findElement(By.id("first-name"))).sendKeys("Pooja").perform(); 
							chk.click(checkout.getFirstname()).sendKeys("Pooja").perform();                               //Checkout POM getter method
							Thread.sleep(2000);
							Actions chk1=new Actions(driver);
							//chk1.click(driver.findElement(By.id("last-name"))).sendKeys("Dhaval").perform(); //enter data  by using action class
							chk1.click(checkout.getLastname()).sendKeys("Dhaval").perform();                         //Checkout POM getter method
							Thread.sleep(2000);
							Actions chk3=new Actions(driver);
							//WebElement zpcd=driver.findElement(By.id("postal-code"));  //hover element by using action class
							WebElement zpcd = checkout.getPostalCode();                 //Checkout POM getter method
							chk3.moveToElement(zpcd).perform();  
							Thread.sleep(2000);
		
							System.out.println("checkout---checkout  POM");

		//-->Cancel 
							//driver.findElement(By.id("cancel")).click();
							checkout.getCancel().click();                                               //Checkout POM getter method
							Thread.sleep(2000);
							System.out.println("cancel---checkout  POM");
							
							System.out.println(productIncart + " ,"+productname1);
							
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
							//driver.findElement(By.id("checkout")).click();
							cp.getCheckout().click();
							Thread.sleep(2000);
							System.out.println("checkout---checkout  POM");
				
						 //driver.findElement(By.id("first-name")).sendKeys("Pooja");
							checkout.getFirstname().sendKeys("Pooja");                   //Checkout POM getter method
				           Thread.sleep(2000);
				           System.out.println("firstname---checkout  POM");
				
				          //driver.findElement(By.id("last-name")).sendKeys("Dhaval");
				           checkout.getLastname().sendKeys("Dhaval");                   //Checkout POM getter method
				           Thread.sleep(2500);
				           System.out.println("lastname---checkout  POM");
				
		//--->Again enter the zipcode on checkout page with Robot class
				         //-->Enter checkout details with Robot class-keystroke
							Robot enterDetails = new Robot();
							//driver.findElement(By.id("postal-code")).click();              
							checkout.getPostalCode().click();                                        //Checkout POM getter method
							enterDetails.keyPress(KeyEvent.VK_1);
							enterDetails.keyRelease(KeyEvent.VK_1);
							enterDetails.keyPress(KeyEvent.VK_3);
							enterDetails.keyRelease(KeyEvent.VK_3);
							enterDetails.keyPress(KeyEvent.VK_5);
							enterDetails.keyRelease(KeyEvent.VK_5);
							Thread.sleep(2000);
							System.out.println("postal---checkout  POM");

		//-->Cancel 
							//driver.findElement(By.id("cancel")).click();
							checkout.getCancel().click();                                              //Checkout POM getter method
							Thread.sleep(2000);
							System.out.println("cancel---checkout  POM");
							
		//--->Continue shopping
							//driver.findElement(By.id("continue-shopping")).click();
							cp.getContinueBtn().click();                                            //Cart POM getter method
							Thread.sleep(2000);
							System.out.println("continue shopping---cart  POM");
							
		//---> logout from sauce demo
							//driver.findElement(By.id("react-burger-menu-btn")).click();
							cp.getBurgerIcon().click();                                                    //Cart POM getter method
							Thread.sleep(1200); 
							//driver.findElement(By.id("logout_sidebar_link")).click();
							cp.getLogout().click();                                                               //Cart POM getter method
							System.out.println("burger icon and logout---cart  POM");
							
							Thread.sleep(2000);
		//---> Closing browser
							driver.close();
						    System.out.println("PASS_TC00_E2E_using POM and External resource(Properties and Excel)");
	}

}
