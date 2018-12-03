package in.licious.dataplanning_API;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import in.licious.util.Helper;
import in.licious.util.ReadData;

import org.testng.AssertJUnit;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONObject;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class CreateProductionPlanAPISample {
	
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
	public void productionPlanTest_TC06() throws Throwable
	{
		
		logger = extent.startTest("Execution Started for production Plan "); 
		Assert.assertTrue(true); 
		ReadData rd=new ReadData();
		String excelFilePath="/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
		String baseprodURLl="http://52.66.9.219:9200/_sql?sql=";
	    //String baseprodURLl="https://plan-es1.licious.app/_sql?sql=";
		
	RequestSpecification request = RestAssured.given();
	 double PP=0;
	 
	//String baseprodURLl="http://52.66.9.219:9200/_sql?sql=";
		JSONObject requestParams = new JSONObject();
		requestParams.put("from", rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 12)); 
		requestParams.put("to",rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 13)); 
		//requestParams.put("city", "1"); 
		// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");
		// Add a header stating the Request body is a JSON
		request.header("token", "e6e061838856bf47e1de730719fb2609");
		
		request.body(requestParams.toJSONString());
		
	//	P.P(T-1) = D.P(T)-Min[[M.I(T-2), C.S(T-2) - D.P (T-1)]]
		Response response = request.post(rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 2));

		int statusCode = response.getStatusCode();
		AssertJUnit.assertEquals(statusCode, 200);
		String data = response.getContentType();
		ResponseBody data1 = response.getBody();

		System.out.println(statusCode);
		logger.log(LogStatus.PASS, "Production Plan Response  is verified successfully");
		
		System.out.println(data);
		System.out.println(data1.asString());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String dateString=rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 30);
		logger.log(LogStatus.PASS, "Demand Plan for the fresh Products in Production Plan is ="+dateString);
        // Get a Date object from the date string
        Date myDate1 = dateFormat.parse(dateString);
        Date  oneDayBefore1 = new Date(myDate1.getTime() - 1);
        String result1 = dateFormat.format(oneDayBefore1);
        System.out.println(result1);
        
        Date myDate2 = dateFormat.parse(result1);
        Date  oneDayBefore2 = new Date(myDate2.getTime() - 1);
        String result2 = dateFormat.format(oneDayBefore2);
        System.out.println(result2);
        
        Date myDate3 = dateFormat.parse(result2);
        Date  oneDayBefore3 = new Date(myDate3.getTime() - 1);
        String result3 = dateFormat.format(oneDayBefore3);
        System.out.println(result3);
		
        Date myDate4 = dateFormat.parse(result3);
        Date  oneDayBefore4 = new Date(myDate4.getTime() - 1);
        String result4 = dateFormat.format(oneDayBefore4);
        System.out.println(result4);
		System.out.println("***************************");
		
		String dateDPTsam="'"+dateString+"'";
		String dateDPT1sam="'"+result1+"'";
		
		String dateDPT="'"+dateString+"'"+"and%20version%20=";
		System.out.println(dateDPT);
		String dateDPT1="'"+result1+"'"+"and%20version%20=";
		System.out.println(dateDPT1);
		
	
	String dateHI_2="'"+result2+"'";
	System.out.println(dateHI_2);
	String dateCS_2="'"+result2+"'";
	System.out.println(dateCS_2);
	
	String ssDPmaxV=rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 31);
			//"select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_5785b9065d7e1' and date=";
	String sURLdpVersion =baseprodURLl+URLEncoder.encode(ssDPmaxV)+dateDPTsam;
	System.out.println(sURLdpVersion);
	 URL urldpMaxVer = new URL(sURLdpVersion);
	 
	 URLConnection requestdpmaxVar = urldpMaxVer.openConnection();
	 requestdpmaxVar.connect();
	 
	   JsonParser jpdpmaxVersion = new JsonParser(); //from gson
	    JsonElement rootdpmaxVer = jpdpmaxVersion.parse(new InputStreamReader((InputStream) requestdpmaxVar.getContent())); //Convert the input stream to a json element
	    String rootobjdpmaxVersion = rootdpmaxVer.getAsJsonObject().get("aggregations").getAsJsonObject().get("MAX(version)").getAsJsonObject().get("value").getAsString();
	    System.out.println(rootobjdpmaxVersion);
	    double dpmaxVersiond=  Double.parseDouble(rootobjdpmaxVersion);
	    int dpmaxVersion=(int)dpmaxVersiond;
	    String versiondp="'"+dpmaxVersion+"'";
	    System.out.println(versiondp);
	    
	    String sURLdp1Version =baseprodURLl+URLEncoder.encode(ssDPmaxV)+dateDPT1sam;
		System.out.println(sURLdp1Version);
		 URL urldp1MaxVer = new URL(sURLdp1Version);
		 
		 URLConnection requestdp1maxVar = urldp1MaxVer.openConnection();
		 requestdp1maxVar.connect();
		 
		   JsonParser jpdp1maxVersion = new JsonParser(); //from gson
		    JsonElement rootdp1maxVer = jpdp1maxVersion.parse(new InputStreamReader((InputStream) requestdp1maxVar.getContent())); //Convert the input stream to a json element
		    String rootobjdp1maxVersion = rootdp1maxVer.getAsJsonObject().get("aggregations").getAsJsonObject().get("MAX(version)").getAsJsonObject().get("value").getAsString();
		    System.out.println(rootobjdp1maxVersion);
		    double dp1maxVersiond=  Double.parseDouble(rootobjdp1maxVersion);
		    int dp1maxVersion=(int)dp1maxVersiond;
		    String versiondp1="'"+dp1maxVersion+"'";
		    System.out.println(versiondp1);
		    
		    
	String ssDP=rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 14);
	//String ssDPmaxV="select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date=";
	
	
	String ssHIt=rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 15);
	
	String ssCS=rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 16);
	
	 String sURLdp =baseprodURLl+URLEncoder.encode(ssDP)+dateDPT+versiondp;
	
	 System.out.println(sURLdp);
	// String sURLdpVersion =baseprodURLl+URLEncoder.encode(ssDPmaxV)+dateDPT; //just a string
	 String sURLdp1 = baseprodURLl+URLEncoder.encode(ssDP)+dateDPT1+versiondp1;
	
	
	
		 URL urldp = new URL(sURLdp);
		    URLConnection requestdp = urldp.openConnection();
		    requestdp.connect();

		    // Convert to a JSON object to print data
		    JsonParser jpdp = new JsonParser(); //from gson
		    JsonElement rootdp = jpdp.parse(new InputStreamReader((InputStream) requestdp.getContent())); //Convert the input stream to a json element
		    String rootobjdp = rootdp.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
		    
		    //May be an array, may be an object. 
		    System.out.println(rootobjdp+"demand Plan");
		    
		//String sURLdp1 = baseprodURLl+URLEncoder.encode(ssDP); //just a string
		
		 URL urldp1 = new URL(sURLdp1);
		    URLConnection requestdp1 = urldp1.openConnection();
		    requestdp1.connect();

		    // Convert to a JSON object to print data
		    JsonParser jpdp1 = new JsonParser(); //from gson
		    JsonElement rootdp1 = jpdp1.parse(new InputStreamReader((InputStream) requestdp1.getContent())); //Convert the input stream to a json element
		    String rootobjdp1 = rootdp1.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
		    
		    //May be an array, may be an object. 
		    System.out.println(rootobjdp1+"Demand Plan T-1");
		    

		String sURLHI = baseprodURLl+URLEncoder.encode(ssHIt)+dateHI_2; //just a string
		URL urlHI = new URL(sURLHI);
	    URLConnection requestHI = urlHI.openConnection();
	    requestHI.connect();

	    // Convert to a JSON object to print data
	    JsonParser jpMI = new JsonParser(); //from gson
	    JsonElement rootHI = jpMI.parse(new InputStreamReader((InputStream) requestHI.getContent())); //Convert the input stream to a json element
	    String rootobjHI = rootHI.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(received_quantity)").getAsJsonObject().get("value").getAsString();
	    
		
		String sURLCS = baseprodURLl+URLEncoder.encode(ssCS)+dateCS_2; //just a string
		
	    // Connect to the URL using java's native library
	    URL urlCS = new URL(sURLCS);
	    URLConnection requestCS = urlCS.openConnection();
	    requestCS.connect();

	    // Convert to a JSON object to print data
	    JsonParser jpCS = new JsonParser(); //from gson
	    JsonElement rootCS = jpCS.parse(new InputStreamReader((InputStream) requestCS.getContent())); //Convert the input stream to a json element
	    String rootobjCS = rootCS.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(stock_units)").getAsJsonObject().get("value").getAsString();
	    
	    //May be an array, may be an object. 
	    System.out.println(rootobjCS+"closing stock");
	 
	    
	   
	  double dplan=  Double.parseDouble(rootobjdp);
	  double dplan1=  Double.parseDouble(rootobjdp1);
	  double closingstock=  Double.parseDouble(rootobjCS);

	  double hubInward=Double.parseDouble(rootobjHI);
	//  P.P(T-1) = D.P(T)-Min[[M.I(T-2), C.S(T-2) - D.P (T-1)]]
	  System.out.println("*************************");
	  System.out.println(dplan+"Demand Plan");
	  System.out.println(dplan1+"Demand Plan t-1");
	  logger.log(LogStatus.PASS, "Demand Plan T-1 for the fresh Products in Production Plan is ="+dplan1);
	  System.out.println(closingstock+"Closing stock t-2");
	  logger.log(LogStatus.PASS, "Closing stock  T-2 for the fresh Products in Production Plan is ="+closingstock);
	  System.out.println(hubInward+"Hub inward t-2");
	  logger.log(LogStatus.PASS, "Hub Inward  T-2 for the fresh Products in Production Plan is ="+hubInward);
	  double value=closingstock-dplan1;
	  double pp_1=0;
	  double ppT_1=0;
	  if(value>0)
	  {
		if(hubInward<value)  
		{
			ppT_1=dplan-hubInward;
			if(ppT_1>0)
			{
				logger.log(LogStatus.PASS, "Production Plan for the fresh Products in Production Plan is ="+ppT_1);
				System.out.println("Production Plan "+ppT_1);
			}else
			{
				logger.log(LogStatus.PASS, "Production Plan for the fresh Products in Production Plan is ="+0);
				System.out.println("Production Plan "+0);
			}
		}
			else
			{
				ppT_1=dplan-value;	
				if(ppT_1>0)
				{
					logger.log(LogStatus.PASS, "Production Plan for the fresh Products in Production Plan is ="+ppT_1);
					System.out.println("Production Plan "+ppT_1);
				}else
				{
					logger.log(LogStatus.PASS, "Production Plan for the fresh Products in Production Plan is ="+0);
					System.out.println("Production Plan "+0);
				}
			}
		
	  }else
	  {
		  ppT_1=dplan-hubInward; 
		  if(ppT_1>0)
			{
			  logger.log(LogStatus.PASS, "Production Plan for the fresh Products in Production Plan is ="+ppT_1);
				System.out.println(ppT_1+"Production Plan ");
			}else
			{
				logger.log(LogStatus.PASS, "Production Plan for the fresh Products in Production Plan is ="+0);
				System.out.println("Production Plan "+0);
			}
	  }
	  extent.endTest(logger);
	}
	  @AfterTest
		public void aftermethod()
		{
			extent.flush();
			extent.close();
		}
	  
	
	
}
