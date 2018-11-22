package in.licious.dataplanning_API;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import in.licious.util.Helper;
import in.licious.util.ReadData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class ThawingApi {
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
	public void generateThawingPlanTest_TC07() throws Throwable
	{
		logger = extent.startTest("Execution Started for Thawing Plan "); 
		Assert.assertTrue(true); 
		double tp1;
		double tp;
		ReadData rd=new ReadData();
		String excelFilePath="/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
		String baseprodURLl="http://52.66.9.219:9200/_sql?sql=";
       // String baseprodURLl="https://plan-es1.licious.app/_sql?sql=";
		RequestSpecification request = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("from", rd.readDataFromExcel(excelFilePath, "Dataplanning", 7, 30)); 
		requestParams.put("to",rd.readDataFromExcel(excelFilePath, "Dataplanning", 7, 30)); 
		requestParams.put("city", "1"); 
		// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");
		// Add a header stating the Request body is a JSON
		request.header("token", "e6e061838856bf47e1de730719fb2609");
		
		request.body(requestParams.toJSONString());
		
		Response response = request.post(rd.readDataFromExcel(excelFilePath, "Dataplanning", 7, 2));

		int statusCode = response.getStatusCode();
		AssertJUnit.assertEquals(statusCode, 200);
		logger.log(LogStatus.PASS, "Thawing Plan Response  is verified successfully");
		String data = response.getContentType();
		ResponseBody data1 = response.getBody();

		System.out.println(statusCode);
		System.out.println(data);
		System.out.println(data1.asString());
		
		//TP(T-2)=MAX[FF(T)-MAX{CS(T-3)-[FF(T-2)+FF(T-1)}]
		String dateString=rd.readDataFromExcel(excelFilePath, "Dataplanning", 7, 30);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		
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
			String dateDPT2sam="'"+result2+"'";
			String dateDPT="'"+dateString+"'"+"and%20version%20=";
			System.out.println(dateDPT);
			String dateDPT1="'"+result1+"'"+"and%20version%20=";
			System.out.println(dateDPT1);
			String dateDPT2="'"+result2+"'"+"and%20version%20=";
			
		
		String dateCS_3="'"+result3+"'";
		System.out.println(dateCS_3+"closing stock t-3");
		
	

		String ssDPmaxV=rd.readDataFromExcel(excelFilePath, "Dataplanning", 7, 31);
				//rd.readDataFromExcel(excelFilePath, "Dataplanning", 7, 31);
				//"select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235b81a66f9' and date=";
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
			    
			    String sURLdp2Version =baseprodURLl+URLEncoder.encode(ssDPmaxV)+dateDPT2sam;
				System.out.println(sURLdp2Version);
				 URL urldp2MaxVer = new URL(sURLdp2Version);
				 
				 URLConnection requestdp2maxVar = urldp2MaxVer.openConnection();
				 requestdp2maxVar.connect();
				 
				   JsonParser jpdp2maxVersion = new JsonParser(); //from gson
				    JsonElement rootdp2maxVer = jpdp2maxVersion.parse(new InputStreamReader((InputStream) requestdp2maxVar.getContent())); //Convert the input stream to a json element
				    String rootobjdp2maxVersion = rootdp2maxVer.getAsJsonObject().get("aggregations").getAsJsonObject().get("MAX(version)").getAsJsonObject().get("value").getAsString();
				    System.out.println(rootobjdp2maxVersion);
				    double dp2maxVersiond=  Double.parseDouble(rootobjdp2maxVersion);
				    int dp2maxVersion=(int)dp2maxVersiond;
				    String versiondp2="'"+dp2maxVersion+"'";
				    System.out.println(versiondp2);
	
	String ssDP=rd.readDataFromExcel(excelFilePath, "Dataplanning", 7, 14);
	//String ssDPmaxV="select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date=";
	
	
	
	
	String ssCS=rd.readDataFromExcel(excelFilePath, "Dataplanning", 7, 16);
	
	 String sURLdp =baseprodURLl+URLEncoder.encode(ssDP)+dateDPT+versiondp;
	// String sURLdp =baseprodURLl+URLEncoder.encode(ssDP)+dateDPT+"and version="+version; 
	// String sURLdp =baseprodURLl+URLEncoder.encode(ssDP);
	 System.out.println(sURLdp);	
	
		
		 URL urldp = new URL(sURLdp);
		    URLConnection requestdp = urldp.openConnection();
		    requestdp.connect();

		    // Convert to a JSON object to print data
		    JsonParser jpdp = new JsonParser(); //from gson
		    JsonElement rootdp = jpdp.parse(new InputStreamReader((InputStream) requestdp.getContent())); //Convert the input stream to a json element
		    String rootobjdp = rootdp.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
		    
		    //May be an array, may be an object. 
		    System.out.println(rootobjdp+"demand Plan");
		    
		String sURLdp1 = baseprodURLl+URLEncoder.encode(ssDP)+dateDPT1+versiondp1; //just a string
		
		 URL urldp1 = new URL(sURLdp1);
		    URLConnection requestdp1 = urldp1.openConnection();
		    requestdp1.connect();

		    // Convert to a JSON object to print data
		    JsonParser jpdp1 = new JsonParser(); //from gson
		    JsonElement rootdp1 = jpdp1.parse(new InputStreamReader((InputStream) requestdp1.getContent())); //Convert the input stream to a json element
		    String rootobjdp1 = rootdp1.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
		    
		    //May be an array, may be an object. 
		    System.out.println(rootobjdp1+"Demand Plan T-1");
		    
		    String sURLdp2 = baseprodURLl+URLEncoder.encode(ssDP)+dateDPT2+versiondp2; //just a string
			
			 URL urldp2 = new URL(sURLdp2);
			    URLConnection requestdp2 = urldp2.openConnection();
			    requestdp2.connect();

			    // Convert to a JSON object to print data
			    JsonParser jpdp2 = new JsonParser(); //from gson
			    JsonElement rootdp2 = jpdp2.parse(new InputStreamReader((InputStream) requestdp2.getContent())); //Convert the input stream to a json element
			    String rootobjdp2 = rootdp2.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
			    
			    //May be an array, may be an object. 
			    System.out.println(rootobjdp2+"Demand Plan T-2");
		    

		
		String sURLCS = baseprodURLl+URLEncoder.encode(ssCS)+dateCS_3; //just a string
		
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
	  // String value = rootobj.get("value").getAsString(); //just grab the zipcode
	    //System.out.println(value);
	    
	    
	  //TP(T-2)=MAX[FF(T)-MAX{CS(T-3)-[FF(T-2)+FF(T-1)}]
	  double dplan=  Double.parseDouble(rootobjdp);
	  double dplan1=  Double.parseDouble(rootobjdp1);
	  double dplan2=  Double.parseDouble(rootobjdp2);
	  double closingstock=  Double.parseDouble(rootobjCS);
	 
	  System.out.println("*************************");
	  System.out.println(dplan+"Demand Plan");
	  logger.log(LogStatus.PASS, "Demand Plan T for the Colds Cuts  Products in Thawing Plan is ="+dplan);
	  logger.log(LogStatus.PASS, "Demand Plan T-1 for the Colds Cuts  Products in Thawing Plan is ="+dplan1);
	  logger.log(LogStatus.PASS, "Demand Plan T-2 for the Colds Cuts  Products in Thawing Plan is ="+dplan2);
	  logger.log(LogStatus.PASS, "Closing Stock  T-3 for Colds Cuts  Products in Thawing Plan is "+closingstock);
	  System.out.println(dplan1+"Demand Plan -1");
	  System.out.println(dplan2+"Demand Plan -2");
	  System.out.println(closingstock+"Closing stock t-3");
	  double value=dplan2+dplan1;
	  double value1=0;
	  
	  
	 if(closingstock>value)
	 {
		 value1=closingstock-value;
		 tp=dplan-value1;
		// System.out.println(tp);
		 if(dplan>tp)
		 {
			 tp1=tp;
			 logger.log(LogStatus.PASS, "Thawing Plan for the Cold Cuts Products in Thawing Plan is ="+tp1);
			 System.out.println(tp1+" Thawing Plan"); 
		 }
		 else
		 {
			 tp1=0;
			 logger.log(LogStatus.PASS, "Thawing Plan for the Cold Cuts Products in Thawing Plan is ="+tp1);
			 System.out.println(tp1+" Thawing Plan"); 
		 }
	 }
	 else
	 {
		 value1=0;
		 tp =dplan-value1;
		 if(tp>0)
		 {
			// tp1=dplan;
			 logger.log(LogStatus.PASS, "Thawing Plan for the Cold Cuts Products in Thawing Plan is ="+tp);
			 System.out.println(tp+" Thawing Plan"); 
		 }
		 else
		 {
			 tp1=0;
			 logger.log(LogStatus.PASS, "Thawing Plan for the Cold Cuts Products in Thawing Plan is ="+tp1);
			 System.out.println(tp1+" Thawing Plan");
		 }
		 //System.out.println(tp1+" Thawing Plan");
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
