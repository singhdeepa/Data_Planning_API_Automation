package in.licious.dataplanning_API;

import org.json.simple.JSONObject;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import in.licious.util.ReadData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class DispatchPlan {

	
	@Test
	public void createDispatchPlan()
	{
		ReadData rd=new ReadData();
		String excelFilePath="/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
		String baseprodURLl="http://52.66.9.219:9200/_sql?sql=";
       // String baseprodURLl="https://plan-es1.licious.app/_sql?sql=";
		RequestSpecification request = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("ck", "CK_001"); 
		requestParams.put("date", "2018-11-21");  
		//requestParams.put("city", "1"); 
		// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");
		// Add a header stating the Request body is a JSON
		request.header("token", "e6e061838856bf47e1de730719fb2609");
		
		request.body(requestParams.toJSONString());
		
		Response response = request.post("https://planning-api.licious.in/production/dispatchplan");

		int statusCode = response.getStatusCode();
		AssertJUnit.assertEquals(statusCode, 200);
		System.out.println(statusCode);
		//logger.log(LogStatus.PASS, "Brining Plan Response  is verified successfully");
		String data = response.getContentType();
		ResponseBody data1 = response.getBody();
		
		//String datta=response.body().asString();
		//System.out.println(datta);
		//System.out.println(data1.asString());
		
		System.out.println("********************************************");
		
        RequestSpecification request1 = RestAssured.given();
		
		JSONObject requestParams1 = new JSONObject();
		requestParams1.put("ck", "CK_001"); 
		requestParams1.put("actual", "333"); 
		requestParams1.put("date", "2018-11-21");  
		//requestParams.put("city", "1"); 
		// Add a header stating the Request body is a JSON
		request1.header("Content-Type", "application/json");
		// Add a header stating the Request body is a JSON
		request1.header("token", "e6e061838856bf47e1de730719fb2609");
		
		request1.body(requestParams1.toJSONString());
		
		Response response1 = request.post("https://planning-api.licious.in/production/dispatch/actual");

		int statusCode1 = response.getStatusCode();
		AssertJUnit.assertEquals(statusCode1, 200);
		System.out.println(statusCode1);
		//logger.log(LogStatus.PASS, "Brining Plan Response  is verified successfully");
	//	String data1 = response.getContentType();
		ResponseBody data2 = response.getBody();
		
		//System.out.println(data2.asString());
		
		
		
	}
}
