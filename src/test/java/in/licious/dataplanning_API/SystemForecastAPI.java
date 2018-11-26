package in.licious.dataplanning_API;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.AssertJUnit;

import in.licious.util.Helper;
import in.licious.util.ReadData;
import in.licious.util.Utilities;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class SystemForecastAPI  {
	ExtentReports extent;
	 ExtentTest logger;
	 Helper helper=new Helper();
		
	 @BeforeTest
	 public void beforeTest()
	 {
		 extent = new ExtentReports ("/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/API_HTML_Report/" +helper.getCurrentDateTime()+".html", false);
		 //extent.addSystemInfo("Environment","Environment Name")
		 extent
		                .addSystemInfo("Host Name", "QA TEAM")
		                .addSystemInfo("Environment", "Automation Testing")
		                .addSystemInfo("User Name", "Deepa singh");
		                //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
		                //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
		                extent.loadConfig(new File("/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/extent-config.xml"));
		 
		                
	 }
	@Test
	public void createSystemForecastTest_TC03() throws Throwable
	{
		ReadData rd=new ReadData();
		logger = extent.startTest("Execution started SystemForecast "); 
//		Assert.assertTrue(true);
//		
		String excelFilePath="/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
//				//"/Users/deepa/git/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
//		//RestAssured.baseURI="https://planning-api.licious.in/forecast/services/systemforecast";
//		RequestSpecification request = RestAssured.given();
//		
//		JSONObject requestParams = new JSONObject();
//		requestParams.put("from", rd.readDataFromExcel(excelFilePath, "Dataplanning", 3, 12)); 
//		requestParams.put("to", rd.readDataFromExcel(excelFilePath, "Dataplanning", 3, 13));
//		
//		// Add a header stating the Request body is a JSON
//		request.header("Content-Type", "application/json");
//		// Add a header stating the Request body is a JSON
//		request.header("token", "e6e061838856bf47e1de730719fb2609");
//		
//		request.body(requestParams.toJSONString());
//		
//		Response response = request.post(rd.readDataFromExcel(excelFilePath, "Dataplanning", 3, 2));
//
//		int statusCode = response.getStatusCode();
//		AssertJUnit.assertEquals(statusCode, 200);
//		String data = response.getContentType();
//		ResponseBody data1 = response.getBody();
//
//		System.out.println(statusCode);
//		System.out.println(data);
//		System.out.println(response.body().asString());	
//		logger.log(LogStatus.PASS, "System Forecast day level Response is verified successfully");
//		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String dateString=rd.readDataFromExcel(excelFilePath, "Dataplanning", 3, 30);
         
		Date date = dateFormat.parse(dateString);
		
		
		
//		Calendar cal1 = Calendar.getInstance();
//	    cal1.setTime(date);
//	    cal1.add(Calendar.DAY_OF_MONTH, -7);
//	    
//	    Date todateee = cal1.getTime();    
//        String fromdate = dateFormat.format(todateee);
//        fromdate="'"+fromdate+"'";
//        System.out.println(fromdate);
	    
	    
       // dateString="'"+dateString+"'";
       // System.out.println(dateString);
        
       
        System.out.println("*****************************");
        
        
        
        
         Calendar cal = Calendar.getInstance();
         cal.setTime(date);
         Date todate = cal.getTime(); 
         String todatee = dateFormat.format(todate);
         todatee="'"+todatee+"'";
         System.out.println(todatee);
         
         cal.add(Calendar.DAY_OF_MONTH, -7);
         Date todate1 = cal.getTime();  
        
         String fromdate1 = dateFormat.format(todate1);
         fromdate1="'"+fromdate1+"'";
         System.out.println(fromdate1);
         
         cal.add(Calendar.DAY_OF_MONTH, -7);
         Date todate2 = cal.getTime();    
         String fromdate2 = dateFormat.format(todate2);
         fromdate2="'"+fromdate2+"'";
         System.out.println(fromdate2);
                 
         cal.add(Calendar.DAY_OF_MONTH, -7);
         Date todate3 = cal.getTime();    
         String fromdate3 = dateFormat.format(todate3);
         fromdate3="'"+fromdate3+"'";
         System.out.println(fromdate3);
         
         cal.add(Calendar.DAY_OF_MONTH, -7);
         Date todate4 = cal.getTime();    
         String fromdate4 = dateFormat.format(todate4);
         fromdate4="'"+fromdate4+"'";
         System.out.println(fromdate4);
	
         String pastfourDates="("+fromdate1+","+fromdate2+","+fromdate3+","+fromdate4+")";
         String baseprodURLl="http://52.66.9.219:9200/_sql?sql=";
         // String baseprodURLl="https://plan-es1.licious.app/_sql?sql=";
         String ssSF=rd.readDataFromExcel(excelFilePath, "Dataplanning", 3, 34);
        		 //"select sum(total) from system-forecast where date in ('2018-11-23') and hub_id in ('4') and product_id='pr_5785b9065d7e1'";
         String   sURLSF=  baseprodURLl+URLEncoder.encode(ssSF)+todatee;
         System.out.println(sURLSF);
         URL urlSF = new URL(sURLSF);
		 URLConnection requestsf = urlSF.openConnection();
		 requestsf.connect();

		    
		    JsonParser jpsf = new JsonParser(); //from gson
		    JsonElement rootsf = jpsf.parse(new InputStreamReader((InputStream) requestsf	.getContent())); 
		    String rootobjsf = rootsf.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(total)").getAsJsonObject().get("value").getAsString();
		    
         System.out.println(rootobjsf+"system forecast");
         
         String ssSlotagg=rd.readDataFromExcel(excelFilePath, "Dataplanning", 3, 35);
        		 //"select sum(quantity) from slot-aggregation-data where date in( '2018-11-16','2018-11-09','2018-11-02','2018-10-26') and hub_id in ('4') and product_id='pr_5785b9065d7e1'";
         String   sURLSlotagg=  baseprodURLl+URLEncoder.encode(ssSlotagg)+pastfourDates;
         System.out.println(sURLSlotagg);
         URL urlSlotagg = new URL(sURLSlotagg);
		 URLConnection requestSlotagg = urlSlotagg.openConnection();
		 requestSlotagg.connect();

		    
		    JsonParser jpSlotAgg = new JsonParser(); //from gson
		    JsonElement rootSlotAgg = jpSlotAgg.parse(new InputStreamReader((InputStream) requestSlotagg.getContent())); 
		    String rootobjSlotAgg = rootSlotAgg.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(quantity)").getAsJsonObject().get("value").getAsString();
		    
         System.out.println(rootobjSlotAgg+"slot aggregation day level");
         
         
         String ssLosstagg=rd.readDataFromExcel(excelFilePath, "Dataplanning", 3, 36);
        		 //"select sum(loss_sale_qty) from slot-aggregation-data where date in( '2018-11-16','2018-11-09','2018-11-02','2018-10-26') and hub_id in ('4') and product_id='pr_5785b9065d7e1'";
         String   sURLLossagg=  baseprodURLl+URLEncoder.encode(ssLosstagg)+pastfourDates;
         System.out.println(sURLLossagg);
         URL urlLossagg = new URL(sURLLossagg);
		 URLConnection requestLossagg = urlLossagg.openConnection();
		 requestLossagg.connect();

		    
		    JsonParser jpLossAgg = new JsonParser(); //from gson
		    JsonElement rootLossAgg = jpLossAgg.parse(new InputStreamReader((InputStream) requestLossagg.getContent())); 
		    String rootobjLossAgg = rootLossAgg.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(loss_sale_qty)").getAsJsonObject().get("value").getAsString();
		    
         System.out.println(rootobjLossAgg+"loss Aggregation");
         
         double sysForecast=  Double.parseDouble(rootobjsf);
   	     double slotAgg=  Double.parseDouble(rootobjSlotAgg);
   	     double lossAgg=  Double.parseDouble(rootobjLossAgg);
         
         double agg=(slotAgg+lossAgg)/4;
         System.out.println(agg+ "Aggregation");
         System.out.println(sysForecast+"system forecast");
		 extent.endTest(logger);
		
		
		
		
		
	}
	@AfterTest
	public void aftermethod()
	{
		extent.flush();
		extent.close();
	}
}
