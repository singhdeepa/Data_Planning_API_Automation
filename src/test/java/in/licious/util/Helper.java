package in.licious.util;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
	public static String SCREEN_SHOT_PATH;
	public static String dateAndTimeStamp(){
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yy-HH-mm-ss");
		 return sdf.format(new Date());
	}
	public static void customWait(long seconds){
		try{
			Thread.sleep(seconds*1000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	private static WebDriver explicitWait(WebDriver driver,long timeOutInSecond,ExpectedCondition<WebDriver> expectedCondition){
		WebDriverWait wait=new WebDriverWait(driver, timeOutInSecond);
		wait.pollingEvery(1, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		return wait.until(expectedCondition);
			}
	private static WebElement explicitWait(WebDriver driver,ExpectedCondition<WebElement> expectedCondition,long timeOutInSeconds){
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(1, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		return wait.until(expectedCondition);
	}
	public  void clickOnElement(WebDriver driver,WebElement element){
		explicitWait(driver, ExpectedConditions.elementToBeClickable(element), 10).click();
	}
	public  void clickOnElement(WebDriver driver,WebElement element,long timeOutInSecond){
		explicitWait(driver, ExpectedConditions.elementToBeClickable(element), timeOutInSecond).click();
	}
	public  void enterText(WebDriver driver,WebElement element,CharSequence text,long timeOutInSecond){
		
			if(explicitWait(driver, ExpectedConditions.elementToBeClickable(element), timeOutInSecond).isDisplayed()){
				element.clear();
				element.sendKeys(text);
			}
	}
	public  void enterText(WebDriver driver,WebElement element,CharSequence text){
		
		if(explicitWait(driver, ExpectedConditions.elementToBeClickable(element), 10).isDisplayed()){
			element.clear();
			element.sendKeys(text);
		}
}
	 public static void takeScreenShot(String sname){
		 try {
			Robot robot=new Robot();
			BufferedImage image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			SCREEN_SHOT_PATH=System.getProperty("user.dir")+"/ScreenShots/"+sname+"-"+dateAndTimeStamp()+".png";
			try {
				ImageIO.write(image, "png", new File(SCREEN_SHOT_PATH));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (AWTException e) {
			e.printStackTrace();
		}	
		 
	 }
	 public  WebDriver switchToFrame(WebDriver driver,WebElement element,long timeOutInSecond){
		 return  explicitWait(driver,timeOutInSecond, ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		 }
	 public  WebDriver switchBackFromFrame(WebDriver driver){
		return driver.switchTo().defaultContent();
	 }
	 /**switchToChildWindow() method works properly only when there is two window opened
	  */
	 public  void switchToChildWindow(WebDriver driver){
		 Iterator<String> itr = driver.getWindowHandles().iterator();
		 itr.next();
		 driver.switchTo().window(itr.next());
	 }
	 /**switchBackToParentWindow() method works properly only when there is two window opened
	  */
	 public  void switchBackToParentWindow(WebDriver driver){
		 Iterator<String> itr = driver.getWindowHandles().iterator();
		 driver.switchTo().window(itr.next());
	 }
	 public  void selectDropDown(WebDriver driver,WebElement element,String text){
		 Select select=new Select(explicitWait(driver, ExpectedConditions.elementToBeClickable(element), 10));
		 select.selectByVisibleText(text);
	 }
	 public  void selectDropDown(WebDriver driver,WebElement element,String text,long timeOutInSecond){
		 Select select=new Select(explicitWait(driver, ExpectedConditions.elementToBeClickable(element), timeOutInSecond));
		 select.selectByVisibleText(text);
	 }
	 public  void deSelectDropDown(WebDriver driver,WebElement element,String text){
		 Select select=new Select(explicitWait(driver, ExpectedConditions.elementToBeClickable(element), 10));
		 select.deselectByVisibleText(text);
	 }
	 public  void deSelectDropDown(WebDriver driver,WebElement element,String text,long timeOutInSecond){
		 Select select=new Select(explicitWait(driver, ExpectedConditions.elementToBeClickable(element), timeOutInSecond));
		 select.deselectByVisibleText(text);
	 }
	 public  void mouseHover(WebDriver driver,WebElement element){
		 Actions action=new Actions(driver);
		 action.moveToElement(element);
	 }
	 public  Alert switchToAlert(WebDriver driver){
		 WebDriverWait wait=new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.alertIsPresent());
	 }
	 public static void safeJavaScriptClick(WebElement element,WebDriver driver) throws Exception {
			try {
				if (element.isEnabled() && element.isDisplayed()) {
					System.out.println("Clicking on element with using java script click");

					((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				} else {
					System.out.println("Unable to click on element");
				}
			} catch (StaleElementReferenceException e) {
				System.out.println("Element is not attached to the page document "+ e.getStackTrace());
			} catch (NoSuchElementException e) {
				System.out.println("Element was not found in DOM "+ e.getStackTrace());
			} catch (Exception e) {
				System.out.println("Unable to click on element "+ e.getStackTrace());
			}
		}
 public void scrollBar(WebDriver driver,WebElement elementToFind)
	 {
		 while(true)
	        {
	            try
	            {	
	            	elementToFind.click();
	                break;
	            }
	            catch(Throwable t)
	            {
	            	
	                JavascriptExecutor js = (JavascriptExecutor) driver;
	                js.executeAsyncScript(" ", "Window.scrollBy(0,50)");
	            }
	        }
	 }
 public static void highlightElement(WebDriver driver,WebElement elementTohilightLight)
 {
	 JavascriptExecutor js = (JavascriptExecutor) driver;
     //use executeScript() method and pass the arguments 
     //Here i pass values based on css style. Yellow background color with solid red color border. 
js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", elementTohilightLight);
}
 public static String getCurrentDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		 
		 //get current date time with Date()
		 Date date = new Date();
		 
		 // Now format the date
		 String datenew= dateFormat.format(date);
		 
		 return datenew;
	}
 
}
