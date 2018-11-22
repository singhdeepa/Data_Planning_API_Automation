package in.licious.dataplanning_API;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.Test;

import in.licious.util.Helper;
import in.licious.util.ReadData;

import java.io.File;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class systemforecastSlotAPI {
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
		public  void systemforecastTest_TC04() {
	    	logger = extent.startTest("Execution started SystemForecast Slot Level "); 
	    	ReadData rd=new ReadData();
			String excelFilePath="/Users/deepa/git/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
			RestAssured.baseURI="https://planning-api.licious.in/forecast/services/systemforecast";
			RequestSpecification request = RestAssured.given();
			
			JSONObject requestParams = new JSONObject();
			requestParams.put("from", rd.readDataFromExcel(excelFilePath, "Dataplanning", 4, 12)); 
			requestParams.put("to", rd.readDataFromExcel(excelFilePath, "Dataplanning", 4, 13));
			
			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");
			// Add a header stating the Request body is a JSON
			request.header("token", "e6e061838856bf47e1de730719fb2609");
			
			request.body(requestParams.toJSONString());
			
			Response response = request.post(rd.readDataFromExcel(excelFilePath, "Dataplanning", 4, 2));

			int statusCode = response.getStatusCode();
			String data = response.getContentType();
			ResponseBody data1 = response.getBody();
			AssertJUnit.assertEquals(statusCode, 200);
			logger.log(LogStatus.PASS, "System Forecast Slot level Response is verified successfully");
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
