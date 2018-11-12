package in.licious.dataplanning_UI;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import in.licious.util.Helper;
import io.github.bonigarcia.wdm.ChromeDriverManager;

public class FinalValidation {
	@Test
	public void finalValidationTest() throws Throwable
	{
	WebDriver driver;
	ChromeDriverManager.getInstance().setup();
	driver = new ChromeDriver();
	driver.get("https://dev-planning.licious.in");
	Thread.sleep(1000);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	WebElement usrname=driver.findElement(By.xpath("(//input[@class='Login__formControl___MVaJ0 form-control'])[1]"));
	Helper.highlightElement(driver,usrname);
	usrname.sendKeys("demo@licious.in");
	Thread.sleep(1000);
	WebElement pwd=driver.findElement(By.xpath("(//input[@class='Login__formControl___MVaJ0 form-control'])[2]"));
	Helper.highlightElement(driver,pwd);
	pwd.sendKeys("demo@9999");
	Thread.sleep(1000);
	WebElement sighIn=driver.findElement(By.xpath("//button[text()='Sign in']"));
	Helper.highlightElement(driver,sighIn);
	sighIn.click();
	//Thread.sleep(1000);
	//driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div/div[1]")).click();
	
	Thread.sleep(1000);
	driver.findElement(By.xpath("//a[contains(text(),'System Forecast_list')]")).click();
	Thread.sleep(1000);
	WebElement week= driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/div/div/div[2]/div[1]/div[2]/div[2]/div/select"));
	 
	Select sel1=new Select(week);
	sel1.selectByIndex(1);
	Thread.sleep(1000);
	driver.findElement(By.xpath("//li[text()='Tue']")).click();
	WebElement createplan=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/div/div/div[2]/div[1]/div[1]/button"));
	WebElement updatebtn=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/div/div/div[2]/div[1]/div[1]/button"));
	try {
		
		Helper.safeJavaScriptClick(createplan,driver);
	}catch(Exception e)
	{
		
		Helper.safeJavaScriptClick(updatebtn,driver);
	}
	driver.findElement(By.xpath("//*[@id=\"table-system-forecast\"]/tbody/tr[1]/td[5]")).sendKeys("10");
	Thread.sleep(2000);	
WebElement percentageBuffer=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/div/div/div[2]/div[1]/div[1]/p"));
Helper.safeJavaScriptClick(percentageBuffer,driver);
	Thread.sleep(1000);
	//Helper.safeJavaScriptClick(updatebtn,driver);
	//Robot robot=new Robot();
	Thread.sleep(500);		
	Thread.sleep(1000);
	 String expfinal="FINAL";
	 WebElement final1=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/div/div/div[1]/div[3]/p"));
	 String actfinal=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/div/div/div[1]/div[3]/p")).getText();
	 if(expfinal.equals(actfinal))
	 {
		 Helper.highlightElement(driver,final1);
		System.out.println("FINAL  is verified"); 
	 }else
	 {
		 System.out.println("FINAL is Not verified");  
	 }
	 Thread.sleep(1000);
	 driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/div/div/div[1]/div[3]/ul/li/a")).click();
	 
	
	 
	WebElement downloadBtn=driver.findElement(By.xpath("//button[text()='Download Sheet']"));
	downloadBtn.click();
	Thread.sleep(1000);
	WebElement category=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/div/div/div[2]/div[2]/div[1]/div[1]/select"));
	Select sel=new Select(category);
	sel.selectByIndex(0);
	Thread.sleep(1000);
	WebElement search=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/div/div/div[2]/div[2]/div[1]/div[2]/input"));
	search.sendKeys("7e1");
	Thread.sleep(1000);
	WebElement submitBtn=driver.findElement(By.xpath("//button[text()='SUBMIT']"));
	submitBtn.click();
	Thread.sleep(1000);
	WebElement approveBtn=driver.findElement(By.xpath("//button[text()='APPROVE']"));
	approveBtn.click();
	Thread.sleep(1000);
	 
	}
}
