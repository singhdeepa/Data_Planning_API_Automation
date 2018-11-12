package in.licious.dataplanning_UI;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import in.licious.util.Helper;
import io.github.bonigarcia.wdm.ChromeDriverManager;

public class Validate_DataPlanning_Component extends Helper {

	@Test
	public void validateSubTitleTest()  throws Exception
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
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div/div[1]")).click();
		Thread.sleep(1000);
		String expdashboard="DASHBOARD";
	 String actdashboard=driver.findElement(By.xpath("//*[@id=\"modal-root\"]/div/div/div[1]/p")).getText();
	 WebElement dashboard=driver.findElement(By.xpath("//*[@id=\"modal-root\"]/div/div/div[1]/p"));
	 if(expdashboard.equals(actdashboard))
	 {
		 Helper.highlightElement(driver,dashboard);
		System.out.println("Dashboard is verified"); 
	 }else
	 {
		 System.out.println("Dashboard is Not verified");  
	 }
	 Thread.sleep(1000);
	 String expdemandPlan="DEMAND PLAN";
	 String actdemandPlan=driver.findElement(By.xpath("//*[@id=\"modal-root\"]/div/div/div[2]/p")).getText();
	 WebElement demandPlan=driver.findElement(By.xpath("//*[@id=\"modal-root\"]/div/div/div[2]/p"));
	 if(expdemandPlan.equals(actdemandPlan))
	 {
		 Helper.highlightElement(driver,demandPlan);
		System.out.println("Demand Plan is verified"); 
	 }else
	 {
		 System.out.println("Demand Plan is Not verified");  
	 }
	 Thread.sleep(1000);
	 String expfinal="FINAL";
	 WebElement final1=driver.findElement(By.xpath("//*[@id=\"modal-root\"]/div/div/div[3]/p"));
	 String actfinal=driver.findElement(By.xpath("//*[@id=\"modal-root\"]/div/div/div[3]/p")).getText();
	 if(expfinal.equals(actfinal))
	 {
		 Helper.highlightElement(driver,final1);
		System.out.println("FINAL  is verified"); 
	 }else
	 {
		 System.out.println("FINAL is Not verified");  
	 }
	 Thread.sleep(1000);
	 String expHubInventortView="HUB INVENTORY VIEW";
	 WebElement hubInventryView=driver.findElement(By.xpath("//*[@id=\"modal-root\"]/div/div/div[4]/p"));
	 String actHubInventortView=driver.findElement(By.xpath("//*[@id=\"modal-root\"]/div/div/div[4]/p")).getText();
	 if(expHubInventortView.equals(actHubInventortView))
	 {
		 Helper.highlightElement(driver,hubInventryView);
		System.out.println("HUB INVENTORY VIEW is verified"); 
	 }else
	 {
		 System.out.println("HUB INVENTORY VIEW is Not verified");  
	 }
	 Thread.sleep(1000);
	 String expProduction="PRODUCTION";
	 WebElement production=driver.findElement(By.xpath("//*[@id=\"modal-root\"]/div/div/div[5]/p"));
	String actProduction=driver.findElement(By.xpath("//*[@id=\"modal-root\"]/div/div/div[5]/p")).getText();
	if(expProduction.equals(actProduction))
	 {
		Helper.highlightElement(driver,production);
		System.out.println("PRODUCTION is verified"); 
	 }else
	 {
		 System.out.println("PRODUCTION is Not verified");  
	 }
	Thread.sleep(1000);
	String expDispatch="DISPATCH";
			String actDispatch=driver.findElement(By.xpath("//*[@id=\"modal-root\"]/div/div/div[6]/p")).getText();
			WebElement dispatch=driver.findElement(By.xpath("//*[@id=\"modal-root\"]/div/div/div[6]/p"));
			if(expDispatch.equals(actDispatch))
			 {
				Helper.highlightElement(driver,dispatch);
				System.out.println("DISPATCH is verified"); 
			 }else
			 {
				 System.out.println("DISPATCH is Not verified");  
			 }
			Thread.sleep(1000);
			String expProcrument="PROCUREMENT";
			String actProcrument=driver.findElement(By.xpath("//*[@id=\"modal-root\"]/div/div/div[7]/p")).getText();
			WebElement procrument=driver.findElement(By.xpath("//*[@id=\"modal-root\"]/div/div/div[7]/p"));
			if(expProcrument.equals(actProcrument))
			 {
				Helper.highlightElement(driver,procrument);
				System.out.println("PROCUREMENT is verified"); 
			 }else
			 {
				 System.out.println("PROCUREMENT is Not verified");  
			 }
			Thread.sleep(1000);
			WebElement logout=driver.findElement(By.xpath("//div[text()='Log out']"));
			Helper.highlightElement(driver,logout);
			driver.findElement(By.xpath("//div[text()='Log out']")).click();
	}
	
}
