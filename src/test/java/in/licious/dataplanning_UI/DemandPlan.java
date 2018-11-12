package in.licious.dataplanning_UI;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import in.licious.util.Helper;
import io.github.bonigarcia.wdm.ChromeDriverManager;



public class DemandPlan {
@Test
public void createDemandPlantest() throws Exception
{
	WebDriver driver;
	ChromeDriverManager.getInstance().setup();
	driver = new ChromeDriver();
	driver.get("https://dev-planning.licious.in");
	Thread.sleep(1000);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.findElement(By.xpath("(//input[@class='Login__formControl___MVaJ0 form-control'])[1]")).sendKeys("demo@licious.in");
	Thread.sleep(1000);
	driver.findElement(By.xpath("(//input[@class='Login__formControl___MVaJ0 form-control'])[2]")).sendKeys("demo@9999");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//button[text()='Sign in']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//a[contains(text(),'System Forecast_list')]")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//li[text()='Tue']")).click();
	Thread.sleep(1000);
//	WebElement element=driver.findElement(By.xpath("(//select[contains(@class,'sc')])[3]"));
//	element.click();
//	Select sel=new Select(element);
//	sel.selectByValue("42");
	//Thread.sleep(1000);
	WebElement createplan=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/div/div/div[2]/div[1]/div[1]/button"));
	WebElement updatebtn=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/div/div/div[2]/div[1]/div[1]/button"));
	try {
		
		Helper.safeJavaScriptClick(createplan,driver);
	}catch(Exception e)
	{
		
		Helper.safeJavaScriptClick(updatebtn,driver);
	}
	
	//Helper.safeJavaScriptClick(updatebtn,driver);
	//Robot robot=new Robot();
	Thread.sleep(500);		
	WebElement searchProduct=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/div/div/div[2]/div[2]/div[1]/div[2]/input"));
	searchProduct.sendKeys("7e1");
	Thread.sleep(5000);	
	//driver.findElement(By.xpath("//li[text()='Tue']")).click();
	driver.findElement(By.xpath("//*[@id=\"table-system-forecast\"]/tbody/tr[1]/td[5]")).sendKeys("10");
	Thread.sleep(2000);	
WebElement percentageBuffer=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/div/div/div[2]/div[1]/div[1]/p"));
Helper.safeJavaScriptClick(percentageBuffer,driver);
	Thread.sleep(1000);
	//robot.keyPress(KeyEvent.VK_ENTER);
	//driver.findElement(By.xpath("//button[@class='sc-dfVpRl fRJQJR']")).click();
	//driver.findElement(By.xpath("(//td[contains(@class,'sc')])[8]")).sendKeys("10");
	//Thread.sleep(500);
	//driver.findElement(By.xpath("//p[text()='Percent Buffer']")).click();
	//robot.keyPress(KeyEvent.VK_ENTER);
	//Thread.sleep(500);
	//driver.findElement(By.xpath("//button[@class='sc-dfVpRl fRJQJR']")).click();
	//driver.findElement(By.xpath("(//td[contains(@class,'sc')])[13]")).sendKeys("10");
	//robot.keyPress(KeyEvent.VK_ENTER);
	//Thread.sleep(500);
	WebElement nextbutton=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/div/div/div[2]/div[4]/button"));
	Helper.safeJavaScriptClick(nextbutton,driver);
	Thread.sleep(1000);
	WebElement unitBuffer=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/div/div/div[2]/div[1]/div[1]"));
	Helper.safeJavaScriptClick(unitBuffer,driver);
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/div/div/div[2]/div[2]/div[1]/div[2]/input")).sendKeys("7e1");
	Thread.sleep(1000);
	WebElement Nxtbtn=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/div/div/div[2]/div[4]/button"));

	Helper.safeJavaScriptClick(Nxtbtn,driver);
	//driver.findElement(By.xpath("//button[text()='NEXT']")).click();
	Thread.sleep(1000);
	
	WebElement submitbutton=driver.findElement(By.xpath("//button[text()='SUBMIT']"));
	Helper.safeJavaScriptClick(submitbutton,driver);
	Thread.sleep(1000);
	
	WebElement approve=driver.findElement(By.xpath("//button[text()='APPROVE']"));
	Helper.safeJavaScriptClick(approve,driver);
	Thread.sleep(2000);
	driver.close();
	driver.quit();
	
}
}
