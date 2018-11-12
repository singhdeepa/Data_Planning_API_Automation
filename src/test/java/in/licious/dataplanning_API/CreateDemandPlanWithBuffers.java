package in.licious.dataplanning_API;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import in.licious.util.ReadData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class CreateDemandPlanWithBuffers {
	@Test
	public void updateDemandPlanWithBuffersTest_TC02()
	{
	//RequestSpecification request = RestAssured.given();
		ReadData rd=new ReadData();
		JSONObject requestParams = new JSONObject();
		JSONArray authArray = new JSONArray();
		JSONObject authparam = new JSONObject();
		String excelFilePath="/Users/deepa/eclipse-workspace/websiteautomation/ExcelData/DataPlanning.xlsx";
		
//		requestParams.put("week","44" );
//		requestParams.put("year", "2018");
//		requestParams.put("city_id", "1");
//		
//		
//		
//		 authparam.put("date", "2018-11-30");
//		 authparam.put("product_id", "pr_5785b9065d7e1");
//		 authparam.put("hub_id","4");
//		 authparam.put("percent_buffer","0");
//		 authparam.put("unit_buffer","0");
//		 authparam.put("final_value", "100");
		
		
		
		
		
		//rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 2);
		requestParams.put("week",rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 2) );
		requestParams.put("year", rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 3));
		requestParams.put("city_id", rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 1));
		
		
		
		 authparam.put("date", rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 5));
		 authparam.put("product_id", rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 4));
		 authparam.put("hub_id", rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 6));
		 authparam.put("percent_buffer", rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 7));
		 authparam.put("unit_buffer",rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 8));
		 authparam.put("final_value", rd.readDataFromExcel(excelFilePath, "Dataplanning", 1, 9));
		 authArray.add(authparam);

		 requestParams.put("data", authArray);

//		Map<String, Object> map = new HashMap<>();
//		map.put("week", "44");
//		map.put("year", "2018");
//		map.put("city_id", new Integer(1));
//		map.put("data", Arrays.asList(new HashMap<String, Object>() {{
//		    put("date", "2018-11-30");
//		    put("product_id", "pr_5785b9065d7e1");
//		    put("hub_id", "4");
//		    put("percent_buffer", new Integer(0));
//		    put("unit_buffer",new Integer(0));
//		    put("final_value", new Integer(100));
//		}}
//		));
		
		RequestSpecification request = RestAssured.given();
		
		// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");
		// Add a header stating the Request body is a JSON
		request.header("token", "e6e061838856bf47e1de730719fb2609");
		
		request.body(requestParams.toJSONString());
		
		Response response = request.post("https://planning-api.licious.in/forecast/services/demandplan/create");
		//http://13.126.207.17/forecast/services/demandplan/create

		int statusCode = response.getStatusCode();
		AssertJUnit.assertEquals(statusCode, 200);
		String data = response.getContentType();
		ResponseBody data1 = response.getBody();

		System.out.println(statusCode);
		System.out.println(data);
		System.out.println(data1.asString());
		
	}
}
