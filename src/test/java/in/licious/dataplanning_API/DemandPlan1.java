package in.licious.dataplanning_API;

import org.json.simple.JSONObject;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class DemandPlan1 {
	@Test()
	public  void createDemandPlanTest_TC01() {
		//ReadData rd=new ReadData();
		//String excelFilePath="/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
		//"/Users/deepa/git/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
//RestAssured.baseURI="https://planning-api.licious.in/forecast/services/systemforecast";
RequestSpecification request = RestAssured.given();



Response response = request.post("http://planning-api.licious.in/forecast/services/demandplan/initialcreate");


JSONObject requestParams = new JSONObject();
requestParams.put("city_id", "1");
requestParams.put("week", "46"); 
requestParams.put("year", "2018");


// Add a header stating the Request body is a JSON
request.header("content-type", "application/json");
// Add a header stating the Request body is a JSON
request.header("token", "e6e061838856bf47e1de730719fb2609");

request.body(requestParams.toJSONString());

int statusCode = response.getStatusCode();
AssertJUnit.assertEquals(statusCode, 200);
String data = response.getContentType();
ResponseBody data1 = response.getBody();

System.out.println(statusCode);
System.out.println(data);
System.out.println(data1.asString());	
	}
}
