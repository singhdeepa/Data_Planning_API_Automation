package in.licious.dataplanning_API;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
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

public class Demo {
	ExtentReports extent;
	 ExtentTest logger;
	 Helper helper=new Helper();
		
	 @BeforeTest
	 public void beforeTest()
	 {
		 extent = new ExtentReports ("/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/API_HTML_Report/" +helper.getCurrentDateTime()+".html", true);
		 //extent.addSystemInfo("Environment","Environment Name")
		 extent
		                .addSystemInfo("Host Name", "QA TEAM")
		                .addSystemInfo("Environment", "Automation Testing")
		                .addSystemInfo("User Name", "Deepa singh");
		                //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
		                //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
		                extent.loadConfig(new File("/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/extent-config.xml"));
		 
		                
	 }
	 @Test(priority=1)
		public  void createDemandPlanTest_TC01() {
			logger = extent.startTest("Execution Started for Initial Demand Plan  "); 
			ReadData rd=new ReadData();
			String excelFilePath="/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
			//"/Users/deepa/git/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
	//RestAssured.baseURI="https://planning-api.licious.in/forecast/services/systemforecast";
	RequestSpecification request = RestAssured.given();

	JSONObject requestParams = new JSONObject();
	//requestParams.put("week", rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 4)); 
	//requestParams.put("year", rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 5));
	//requestParams.put("city_id", rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 3));

	requestParams.put("week",rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 4) );
	logger.log(LogStatus.PASS, "Week for the Demand Plan With Buffers  is "+rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 4));
	requestParams.put("year", rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 5));
	logger.log(LogStatus.PASS, "YEAR for the Demand Plan With Buffers  is "+rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 5));
	requestParams.put("city_id", rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 3));
	logger.log(LogStatus.PASS, "City ID for the Demand Plan With Buffers  is "+rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 3));

	// Add a header stating the Request body is a JSON
	request.header("Content-Type", "application/json");
	// Add a header stating the Request body is a JSON
	request.header("token", "e6e061838856bf47e1de730719fb2609");

	request.body(requestParams.toJSONString());

	Response response = request.post(rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 2));

	int statusCode = response.getStatusCode();
	AssertJUnit.assertEquals(statusCode, 200);
	logger.log(LogStatus.PASS, "Demand Plan  Response  is verified successfully");
	String data = response.getContentType();
	ResponseBody data1 = response.getBody();

	System.out.println(statusCode);
	System.out.println(data);
	System.out.println(data1.asString());	
	
	extent.endTest(logger);
	}
	 @AfterTest
		public void aftermethod()
		{
		 extent.endTest(logger);
			extent.flush();
			extent.close();
		}
	// @Test(priority=2)
		public void updateDemandPlanWithBuffersTest_TC02()
		{
			logger = extent.startTest("Execution Started for Demand Plan With Buffers "); 
			AssertJUnit.assertTrue(true); 
		//RequestSpecification request = RestAssured.given();
			ReadData rd=new ReadData();
			JSONObject requestParams = new JSONObject();
			JSONArray authArray = new JSONArray();
			JSONObject authparam = new JSONObject();
			String excelFilePath="/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
			

			
			//rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 2);
			requestParams.put("week",rd.readDataFromExcel(excelFilePath, "Dataplanning", 2, 4) );
			logger.log(LogStatus.PASS, "Week for the Demand Plan With Buffers  is "+rd.readDataFromExcel(excelFilePath, "Dataplanning", 2, 4));
			requestParams.put("year", rd.readDataFromExcel(excelFilePath, "Dataplanning", 2, 5));
			logger.log(LogStatus.PASS, "YEAR for the Demand Plan With Buffers  is "+rd.readDataFromExcel(excelFilePath, "Dataplanning", 2, 5));
			requestParams.put("city_id", rd.readDataFromExcel(excelFilePath, "Dataplanning", 2, 3));
			logger.log(LogStatus.PASS, "City ID for the Demand Plan With Buffers  is "+rd.readDataFromExcel(excelFilePath, "Dataplanning", 2, 3));
			
			
			
			 authparam.put("date", rd.readDataFromExcel(excelFilePath, "Dataplanning", 2, 30));
			 logger.log(LogStatus.PASS, "Date for the Demand Plan With Buffers  is "+rd.readDataFromExcel(excelFilePath, "Dataplanning", 2, 30));
			 authparam.put("product_id", rd.readDataFromExcel(excelFilePath, "Dataplanning", 2, 6));
			 logger.log(LogStatus.PASS, "Product ID for the Demand Plan With Buffers  is "+rd.readDataFromExcel(excelFilePath, "Dataplanning", 2, 6));
			 authparam.put("hub_id", rd.readDataFromExcel(excelFilePath, "Dataplanning", 2, 8));
			 logger.log(LogStatus.PASS, "Hub ID for the Demand Plan With Buffers  is "+rd.readDataFromExcel(excelFilePath, "Dataplanning", 2, 8));
			 authparam.put("percent_buffer", rd.readDataFromExcelint(excelFilePath, "Dataplanning", 2, 9));
			 logger.log(LogStatus.PASS, "Percentage Buffers for the Demand Plan With Buffers  is "+rd.readDataFromExcelint(excelFilePath, "Dataplanning", 2, 9));
			 authparam.put("unit_buffer",rd.readDataFromExcelint(excelFilePath, "Dataplanning", 2, 10));
			 logger.log(LogStatus.PASS, "Unit Buffers for the Demand Plan With Buffers  is "+rd.readDataFromExcelint(excelFilePath, "Dataplanning", 2, 10));
			 authparam.put("final_value", rd.readDataFromExcel(excelFilePath, "Dataplanning", 2, 11));
			 authArray.add(authparam);

			 requestParams.put("data", authArray);
		    System.out.println( requestParams.put("data", authArray));
			RequestSpecification request = RestAssured.given();
			
			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");
			// Add a header stating the Request body is a JSON
			request.header("token", "e6e061838856bf47e1de730719fb2609");
			
			request.body(requestParams.toJSONString());
				
			Response response = request.post(rd.readDataFromExcel(excelFilePath, "Dataplanning", 2, 2));
			//http://13.126.207.17/forecast/services/demandplan/create

			int statusCode = response.getStatusCode();
			AssertJUnit.assertEquals(statusCode, 200);
			logger.log(LogStatus.PASS, "Demand Plan With Buffers  Response  is verified successfully");
			String data = response.getContentType();
			ResponseBody data1 = response.getBody();

			String datta=response.body().asString();
			System.out.println(datta);
			System.out.println(statusCode);
			System.out.println(data);
			System.out.println(data1.asString());
			
			extent.endTest(logger);
			 
		}
	 @Test(priority=3)
		public void productionPlanTest_TC06() throws Throwable
		{
			
			logger = extent.startTest("Execution Started for production Plan "); 
			Assert.assertTrue(true); 
			ReadData rd=new ReadData();
			String excelFilePath="/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
		   // String baseprodURLl="https://plan-es1.licious.app/_sql?sql=";
		    String baseprodURLl="http://52.66.9.219:9200/_sql?sql=";
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
				//"select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date=";
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
		//  extent.endTest(logger);
		  
		  String numberAsString = Double.toString(ppT_1);
		  System.out.println(numberAsString);
		
		
		
		System.out.println("***********************************************************************************************************");
		
		
				//"/Users/deepa/git/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
		//RestAssured.baseURI="https://planning-api.licious.in/forecast/services/systemforecast";
		
		
		RequestSpecification request1 = RestAssured.given();
		
		 
		//String baseprodURLl="http://52.66.9.219:9200/_sql?sql=";
		
			//requestParams.put("city", "1"); 
			// Add a header stating the Request body is a JSON
	
			JSONObject requestParams1 = new JSONObject();
			request1.body(requestParams1.toJSONString());
			
		requestParams1.put("week",rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 4) ); 
		requestParams1.put("year",rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 5) );
		requestParams1.put("ck", rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 18));
		requestParams1.put("date",rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 7));
		requestParams1.put("type",rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 33));
		
		
		// Add a header stating the Request body is a JSON
		request1.header("Content-Type", "application/json");
		// Add a header stating the Request body is a JSON
		request1.header("token", "e6e061838856bf47e1de730719fb2609");
		
		request1.body(requestParams1.toJSONString());
		
		Response response1 = request1.post("http://planning-api.licious.in/production/viewplan");

		int statusCode1 = response1.getStatusCode();
		AssertJUnit.assertEquals(statusCode1, 200);

		System.out.println(response1.getContentType());
		
		//System.out.println(response1.getBody().asString());	
		String dateString1=" "+dateString+" ";
		String prdFresh=rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 6);
				//"pr_5785b9065d7e1";
		System.out.println(dateString);
		
		if(response1.getBody().asString().contains(prdFresh))
		{
			if(response1.getBody().asString().contains(result1))
			{
			if(response1.getBody().asString().contains(numberAsString))
			{
			System.out.println("Production Plan pass for "+prdFresh+" "+result1+" "+numberAsString);
			}
			}
		}else
		{
			System.out.println("Fail");
		}
		extent.endTest(logger);
		}
	 @Test(priority=4)
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

			 System.out.println(dateString);
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
		  String numberAsString="";
		  
		 if(closingstock>value)
		 {
			 value1=closingstock-value;
			 tp=dplan-value1;
			// System.out.println(tp);
			 if(dplan>tp)
			 {
				 tp1=tp;
				 logger.log(LogStatus.PASS, "Thawing Plan for the Cold Cuts Products in Thawing Plan is ="+tp1);
				  numberAsString = Double.toString(tp1);
				 System.out.println(numberAsString);
				 System.out.println(tp1+" Thawing Plan"); 
			 }
			 else
			 {
				 tp1=0;
				 logger.log(LogStatus.PASS, "Thawing Plan for the Cold Cuts Products in Thawing Plan is ="+tp1);
				  numberAsString = Double.toString(tp1);
				 System.out.println(numberAsString);
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
				  numberAsString = Double.toString(tp);
				 System.out.println(numberAsString);
				 System.out.println(tp+" Thawing Plan"); 
			 }
			 else
			 {
				 tp1=0;
				 logger.log(LogStatus.PASS, "Thawing Plan for the Cold Cuts Products in Thawing Plan is ="+tp1);
				  numberAsString = Double.toString(tp1);
				 System.out.println(numberAsString);
				 System.out.println(tp1+" Thawing Plan");
			 }
			 //System.out.println(tp1+" Thawing Plan");
		 }
		 
		
		
		
		System.out.println("***********************************************************************************************************");
		
		
				//"/Users/deepa/git/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
		//RestAssured.baseURI="https://planning-api.licious.in/forecast/services/systemforecast";
		
		
		RequestSpecification request1 = RestAssured.given();
		
		 
		//String baseprodURLl="http://52.66.9.219:9200/_sql?sql=";
		
			//requestParams.put("city", "1"); 
			// Add a header stating the Request body is a JSON
	
			JSONObject requestParams1 = new JSONObject();
			request1.body(requestParams1.toJSONString());
			
			requestParams1.put("week",rd.readDataFromExcel(excelFilePath, "Dataplanning", 7, 4) ); 
			requestParams1.put("year",rd.readDataFromExcel(excelFilePath, "Dataplanning", 7, 5) );
			requestParams1.put("ck", rd.readDataFromExcel(excelFilePath, "Dataplanning", 7, 18));
			requestParams1.put("date",rd.readDataFromExcel(excelFilePath, "Dataplanning", 7, 7));
			requestParams1.put("type",rd.readDataFromExcel(excelFilePath, "Dataplanning", 7, 33));
		
		
		
		// Add a header stating the Request body is a JSON
		request1.header("Content-Type", "application/json");
		// Add a header stating the Request body is a JSON
		request1.header("token", "e6e061838856bf47e1de730719fb2609");
		
		request1.body(requestParams1.toJSONString());
		
		Response response1 = request1.post("http://planning-api.licious.in/production/viewplan");

		int statusCode1 = response1.getStatusCode();
		AssertJUnit.assertEquals(statusCode1, 200);

		System.out.println(response1.getContentType());
		
		//System.out.println(response1.getBody().asString());	
		//String dateString1=" "+dateString+" ";
		String prdColdCuts=rd.readDataFromExcel(excelFilePath, "Dataplanning", 7, 6);
				//"pr_57235b81a66f9";
		System.out.println(dateString);
		
		if(response1.getBody().asString().contains(prdColdCuts))
		{
			
		
			if(response1.getBody().asString().contains(result2))
			{
				
			if(response1.getBody().asString().contains(numberAsString))
			{
				System.out.println("Thaing Plan pass for "+prdColdCuts+" "+result2+" "+numberAsString);
			}else			
			{
				System.out.println("Fail");
			}
		}else
		{
			System.out.println("Fail");
		}
		}else
		{
			System.out.println("Fail");
		}
		
		 
		 
 
		 extent.endTest(logger);
		}
	 @Test(priority=5)
		public void generatebriningPlanTest_TC05() throws Throwable
		{
			logger = extent.startTest("Execution Started for Brining Plan "); 
			AssertJUnit.assertTrue(true); 
			ReadData rd=new ReadData();
			String excelFilePath="/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
			String baseprodURLl="http://52.66.9.219:9200/_sql?sql=";
	       // String baseprodURLl="https://plan-es1.licious.app/_sql?sql=";
			RequestSpecification request = RestAssured.given();
			
			
			JSONObject requestParams = new JSONObject();
			requestParams.put("from", rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 30)); 
			requestParams.put("to",rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 30)); 
			//requestParams.put("city", "1"); 
			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");
			// Add a header stating the Request body is a JSON
			request.header("token", "e6e061838856bf47e1de730719fb2609");
			
			request.body(requestParams.toJSONString());
			
			Response response = request.post(rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 2));

			int statusCode = response.getStatusCode();
			AssertJUnit.assertEquals(statusCode, 200);
			logger.log(LogStatus.PASS, "Brining Plan Response  is verified successfully");
			String data = response.getContentType();
			ResponseBody data1 = response.getBody();
			String datta=response.body().asString();
			System.out.println(datta);
			System.out.println(data1.asString());
	//	
//			X1 = [C.S(T-3) - D.P(T-2)]+Dispatch Plan (T-2)
//			X2 = Min[X1,{H.I(T-3) +H.I(T-4) + Dispatch(T-2)}]
//			X3 = X2-D.P(T-1)
//			X4 = Min[X3,{H.I(T-3) + Dispatch(T-2)}]
//			B.P(T-2) = D.P(T) -X4
			//Date date =new Date();
			   		
				
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			String dateString=rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 30);
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
			String dateDPT2sam="'"+result2+"'";
			String dateDPT="'"+dateString+"'"+"and%20version%20=";
			System.out.println(dateDPT);
			String dateDPT1="'"+result1+"'"+"and%20version%20=";
			System.out.println(dateDPT1);
			String dateDPT2="'"+result2+"'"+"and%20version%20=";
			System.out.println(dateDPT2);
			String dateDispPT2="'"+result2+"'";
			System.out.println(dateDispPT2);
			String dateHI_3="'"+result3+"'";
			System.out.println(dateHI_3);
			String dateCS_3="'"+result3+"'";
			System.out.println(dateCS_3);
			String dateHI_4="'"+result4+"'";
			System.out.println(dateHI_4);
			
			String ssDPmaxV=rd.readDataFromExcel(excelFilePath, "Dataplanning", 5 , 31);
					//"select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date=";
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
			//String ssDP="select sum(final_forecast) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e'";
			String ssDP=rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 14);
			//String ssDPmaxV="select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date=";
			
			//String ssHIt="select sum(received_quantity) from operationdataplan where sku='pr_57235922d122e' and to in ('1','4','10') and source='11' and dispatch_datetime=";
			String ssHIt=rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 15);
			//String ssCS="select sum(stock_units) from closingstock where hub_id in ('1','4','10') and product_id='pr_57235922d122e' and osdate=";
			String ssCS=rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 16);
			 //String ssdispPlan="select sum(dispatch_plan_qty) from dispatch-plan where product_id='pr_57235922d122e' and cluster_id='B_C2' and dispatch_date=";
			  String ssdispPlan=rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 17);
			  String versioN=rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 29);
			  String data2=dateDPT+versiondp;
			 String sURLdp =baseprodURLl+URLEncoder.encode(ssDP)+dateDPT+versiondp;
			// String sURLdp =baseprodURLl+URLEncoder.encode(ssDP)+dateDPT+"and version="+version; 
			// String sURLdp =baseprodURLl+URLEncoder.encode(ssDP);
			 System.out.println(sURLdp);
			// String sURLdpVersion =baseprodURLl+URLEncoder.encode(ssDPmaxV)+dateDPT; //just a string
			 String sURLdp1 = baseprodURLl+URLEncoder.encode(ssDP)+dateDPT1+versiondp1;
			 String sURLdp2 = baseprodURLl+URLEncoder.encode(ssDP)+dateDPT2+versiondp2; //just a string
			
			
			 URL urldp = new URL(sURLdp);
			 URLConnection requestdp = urldp.openConnection();
			 requestdp.connect();
			

			 URL urldp1 = new URL(sURLdp1);
			    URLConnection requestdp1 = urldp1.openConnection();
			    requestdp1.connect();
			    
			    URL urldp2 = new URL(sURLdp2);
			    URLConnection requestdp2 = urldp2.openConnection();
			    requestdp2.connect();

			    
			    JsonParser jpdp = new JsonParser(); //from gson
			    JsonElement rootdp = jpdp.parse(new InputStreamReader((InputStream) requestdp.getContent())); 
			    String rootobjdp = rootdp.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
			    
			    
			    JsonParser jpdp1 = new JsonParser(); //from gson
			    JsonElement rootdp1 = jpdp1.parse(new InputStreamReader((InputStream) requestdp1.getContent())); //Convert the input stream to a json element
			    String rootobjdp1 = rootdp1.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
			    
			    JsonParser jpdp2 = new JsonParser(); //from gson
			    JsonElement rootdp2 = jpdp2.parse(new InputStreamReader((InputStream) requestdp2.getContent())); //Convert the input stream to a json element
			    String rootobjdp2 = rootdp2.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
			    
			   
				    
				    String sURLHIt_3 = baseprodURLl+URLEncoder.encode(ssHIt)+dateHI_3; //just a string
					
					URL urlhIt_3 = new URL(sURLHIt_3);
				    URLConnection requesthIt_3 = urlhIt_3.openConnection();
				    requesthIt_3.connect();

				    // Convert to a JSON object to print data
				    JsonParser jphIt_3 = new JsonParser(); //from gson
				    JsonElement roothIt_3 = jphIt_3.parse(new InputStreamReader((InputStream) requesthIt_3.getContent())); //Convert the input stream to a json element
				    String rootobjhIt_3 = roothIt_3.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(received_quantity)").getAsJsonObject().get("value").getAsString();
				    
	           String sURLHIt_4 = baseprodURLl+URLEncoder.encode(ssHIt)+dateHI_4; //just a string
					
					URL urlhIt_4 = new URL(sURLHIt_4);
				    URLConnection requesthIt_4 = urlhIt_4.openConnection();
				    requesthIt_4.connect();

				    // Convert to a JSON object to print data
				    JsonParser jphIt_4 = new JsonParser(); //from gson
				    JsonElement roothIt_4 = jphIt_4.parse(new InputStreamReader((InputStream) requesthIt_4.getContent())); //Convert the input stream to a json element
				    String rootobjhIt_4 = roothIt_4.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(received_quantity)").getAsJsonObject().get("value").getAsString();
				    
				    //May be an array, may be an object. 
				   
				    
			String sURLCS =baseprodURLl+URLEncoder.encode(ssCS)+dateCS_3; //just a string
			
		    // Connect to the URL using java's native library
		    URL urlCS = new URL(sURLCS);
		    URLConnection requestCS = urlCS.openConnection();
		    requestCS.connect();

		    // Convert to a JSON object to print data
		    JsonParser jpCS = new JsonParser(); //from gson
		    JsonElement rootCS = jpCS.parse(new InputStreamReader((InputStream) requestCS.getContent())); //Convert the input stream to a json element
		    String rootobjCS = rootCS.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(stock_units)").getAsJsonObject().get("value").getAsString();
		    
		    
	        String sURLDispPlan = baseprodURLl+URLEncoder.encode(ssdispPlan)+dateDispPT2; //just a string
			
		    // Connect to the URL using java's native library
		    URL urlDispP = new URL(sURLDispPlan);
		    URLConnection requestDispP = urlDispP.openConnection();
		    requestDispP.connect();

		    // Convert to a JSON object to print data
		    JsonParser jpDispP = new JsonParser(); //from gson
		    JsonElement rootDispP = jpDispP.parse(new InputStreamReader((InputStream) requestDispP.getContent())); //Convert the input stream to a json element
		    String rootobjDispP = rootDispP.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(dispatch_plan_qty)").getAsJsonObject().get("value").getAsString();
		   
		    
		    
		    //May be an array, may be an object. 
		    
//			X1 = [C.S(T-3) - D.P(T-2)]+Dispatch Plan (T-2)
//			X2 = Min[X1,{H.I(T-3) +H.I(T-4) + Dispatch(T-2)}]
//			X3 = X2-D.P(T-1)
//			X4 = Min[X3,{H.I(T-3) + Dispatch(T-2)}]
//			B.P(T-2) = D.P(T) -X4
		 
		  double dplan=  Double.parseDouble(rootobjdp);
		 // int dpmaxVersion=  Integer.parseInt(rootobjdpmaxVersion);
		  System.out.println(dpmaxVersion+" max version");
		  double dplan1=  Double.parseDouble(rootobjdp1);
		  double dplan2=  Double.parseDouble(rootobjdp2);
		  double closingstock=  Double.parseDouble(rootobjCS);
		  double DispatchPlan=  Double.parseDouble(rootobjDispP);
		  double hubInwardt_3=  Double.parseDouble(rootobjhIt_3);
		  double hubInwardt_4=  Double.parseDouble(rootobjhIt_4);
		
		  System.out.println("*************************");
		  System.out.println(dplan+" Demand Plan");
		  logger.log(LogStatus.PASS, "Demand Plan T for the Marinades Products in Brining Plan is ="+dplan);
		  logger.log(LogStatus.PASS, "Demand Plan T-1 for the Marinades Products in Brining Plan is"+dplan1);
		  logger.log(LogStatus.PASS, "Demand Plan T-2 for the Marinades Products in Brining Plan is"+dplan2);
		  System.out.println(dplan1+" Demand Plan -1");
		  System.out.println(dplan2+" Demand Plan -2");
		  logger.log(LogStatus.PASS, "Closing Stock  T-3 for the Marinades Products in Brining Plan is"+closingstock);
		  System.out.println(closingstock+" closing stock t-3");
		  logger.log(LogStatus.PASS, "Dispatch Plan  T-3 for the Marinades Products in Brining Plan is"+DispatchPlan);
		  System.out.println(DispatchPlan+ " Dispatch Plan t-2");
		  logger.log(LogStatus.PASS, "Hub Inward  T-4 for the Marinades Products in Brining Plan is"+hubInwardt_4);
		  logger.log(LogStatus.PASS, "Hub Inward  T-3 for the Marinades Products in Brining Plan is"+hubInwardt_3);
		  System.out.println(hubInwardt_4 +"Hub Inwart t-4");
		  System.out.println(hubInwardt_3+"hub Inward t-3");
		  System.out.println("*************************");
		
			 double x1_1=closingstock-dplan2;
			 System.out.println(x1_1);
			 double x1=x1_1+DispatchPlan;
			 System.out.println(x1+ " x1");
			 double x2_2=hubInwardt_3+hubInwardt_4+DispatchPlan;
			 System.out.println(x2_2+" x2_2");
			 double x2;
			 double x3,x4;
			 double x4_4=hubInwardt_3+DispatchPlan;
			 System.out.println(x4_4+" x4_4");
			 double b_pt_2;
			 
			 if(x1<x2_2)
			 {
				 if(x1<0)
					{
						x1=0;
					}
			  x2=x1;
			  x3=x2-dplan1;
			  if(x3<x4_4)
			  {
				  if(x3<0)
					{
						x3=0;
					}
				  x4=x3;
				  b_pt_2=dplan-x4;
				  if(b_pt_2>0)
				  {
					  logger.log(LogStatus.PASS, "Brining Plan for the Marinades Products in Brining Plan is ="+b_pt_2);
				  System.out.println(b_pt_2+" Brining plan");
				  }else
				  {
					  logger.log(LogStatus.PASS, "Brining Plan for the Marinades Products in Brining Plan is ="+0);
					  System.out.println(0+" Brining plan");  
			  }
			  }else
			  {
				 x4= x4_4;
				 b_pt_2=dplan-x4;
				 if(b_pt_2>0)
				  {
					 logger.log(LogStatus.PASS, "Brining Plan for the Marinades Products in Brining Plan is ="+b_pt_2);
				  System.out.println(b_pt_2+" Brining plan");
				  }else
				  {
					  logger.log(LogStatus.PASS, "Brining Plan for the Marinades Products in Brining Plan is ="+0);
					  System.out.println(0+" Brining plan");  
			  }
			  }
			 }else
			 {
				x2= x2_2;
				x3=x2-dplan1;
				
				if(x3<x4_4)
				  {
					if(x3<0)
					{
						x3=0;
					}
					  x4=x3;
					  b_pt_2=dplan-x4;
					  if(b_pt_2>0)
					  {
						  logger.log(LogStatus.PASS, "Brining Plan for the Marinades Products in Brining Plan is ="+b_pt_2);
					  System.out.println(b_pt_2+" Brining plan");
					  }else
					  {
						  logger.log(LogStatus.PASS, "Brining Plan for the Marinades Products in Brining Plan is ="+0);
						  System.out.println(0+" Brining plan");  
				  }
				  }else
				  {
					 x4= x4_4;
					 b_pt_2=dplan-x4;
					 if(b_pt_2>0)
					  {
						 logger.log(LogStatus.PASS, "Brining Plan for the Marinades Products in Brining Plan is ="+b_pt_2);
					  System.out.println(b_pt_2+" Brining plan");
					  }else
					  {
						  logger.log(LogStatus.PASS, "Brining Plan for the Marinades Products in Brining Plan is ="+0);
						  System.out.println(0+" Brining plan");  
				  }
				  }
			 }
				 String numberAsString = Double.toString(b_pt_2);
				  System.out.println(numberAsString);
			 
			 System.out.println("***********************************************************************************************************");
				
				
				//"/Users/deepa/git/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
		//RestAssured.baseURI="https://planning-api.licious.in/forecast/services/systemforecast";
		
		
		RequestSpecification request1 = RestAssured.given();
		
		 
		//String baseprodURLl="http://52.66.9.219:9200/_sql?sql=";
		
			//requestParams.put("city", "1"); 
			// Add a header stating the Request body is a JSON
	
			JSONObject requestParams1 = new JSONObject();
			request1.body(requestParams1.toJSONString());
			
			requestParams1.put("week",rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 4) ); 
			requestParams1.put("year",rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 5) );
			requestParams1.put("ck", rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 18));
			requestParams1.put("date",rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 7));
			requestParams1.put("type",rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 33));
		
		
		// Add a header stating the Request body is a JSON
		request1.header("Content-Type", "application/json");
		// Add a header stating the Request body is a JSON
		request1.header("token", "e6e061838856bf47e1de730719fb2609");
		
		request1.body(requestParams1.toJSONString());
		
		Response response1 = request1.post("http://planning-api.licious.in/production/viewplan");

		int statusCode1 = response1.getStatusCode();
		AssertJUnit.assertEquals(statusCode1, 200);

		System.out.println(response1.getContentType());
		
		//System.out.println(response1.getBody().asString());	
		//String dateString1=" "+dateString+" ";
		String prdMarinades=rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 6);
				//"pr_57235922d122e";
		
		System.out.println(dateString);
		
		if(response1.getBody().asString().contains(prdMarinades))
		{
			
		
			if(response1.getBody().asString().contains(result2))
			{
				
			if(response1.getBody().asString().contains(numberAsString))
			{
				System.out.println("Brining Plan pass for "+prdMarinades+" "+result2+" "+numberAsString);
			}else			
			{
				System.out.println("Fail");
			}
		}else
		{
			System.out.println("Fail");
		}
		}else
		{
			System.out.println("Fail");
		}
		
		 
			 
			 extent.endTest(logger);
		}


}
