package in.licious.dataplanning_API;

import org.json.simple.JSONObject;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import in.licious.util.ReadData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class ViewProductionPlan {
	@Test
	public void viewProductionPlanTest()
	{
		ReadData rd=new ReadData();
		String excelFilePath="/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
				//"/Users/deepa/git/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
		//RestAssured.baseURI="https://planning-api.licious.in/forecast/services/systemforecast";
		RequestSpecification request = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("week","45" ); 
		requestParams.put("year","2018" );
		requestParams.put("ck", "CK_001");
		requestParams.put("date","2018-11-05" );
		requestParams.put("type","production" );
		
		// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");
		// Add a header stating the Request body is a JSON
		request.header("token", "e6e061838856bf47e1de730719fb2609");
		
		request.body(requestParams.toJSONString());
		
		Response response = request.post("http://planning-api.licious.in/production/viewplan");

		int statusCode = response.getStatusCode();
		AssertJUnit.assertEquals(statusCode, 200);
		String data = response.getContentType();
		ResponseBody data1 = response.getBody();

		System.out.println(statusCode);
		System.out.println(data);
		System.out.println(data1.asString());	
	}
}
