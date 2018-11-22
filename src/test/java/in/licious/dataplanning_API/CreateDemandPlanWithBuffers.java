package in.licious.dataplanning_API;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.testng.Assert;
import org.testng.annotations.Test;

import in.licious.util.Helper;
import in.licious.util.ReadData;
import in.licious.util.Utilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class CreateDemandPlanWithBuffers  {
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
	  @AfterTest
		public void aftermethod()
		{
		 
			extent.flush();
			extent.close();
		}
}
