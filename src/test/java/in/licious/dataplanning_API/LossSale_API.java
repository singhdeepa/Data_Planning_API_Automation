package in.licious.dataplanning_API;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class LossSale_API {
	@Test
	public void dashboardLossSaleTest_TC12() throws Throwable
	{
		//RestAssured.baseURI="https://planning-api.licious.in/forecast/services/systemforecast";
		RequestSpecification request = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("from", "2018-10-03"); 
		requestParams.put("to", "2018-10-03");
		
		// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");
		// Add a header stating the Request body is a JSON
		request.header("token", "e6e061838856bf47e1de730719fb2609");
		
		request.body(requestParams.toJSONString());
		
		Response response = request.post("http://13.126.207.17:8080/forecast/services/lossSale");

		int statusCode = response.getStatusCode();
		AssertJUnit.assertEquals(statusCode, 200);
		String data = response.getContentType();
		ResponseBody data1 = response.getBody();

		System.out.println(statusCode);
		System.out.println(data);
		System.out.println(data1.asString());
		
		String ssL="select sum(loss_sale_today) from demand-plan where city_id='1' and product_id='pr_5785b9065d7e1' and date='2018-10-24' and version ='3'";
		String ssdp="select sum(final_forecast) from demand-plan where city_id='1' and product_id='pr_5785b9065d7e1' and date='2018-10-24' and version ='3'";
		String sURLdp = "http://52.66.9.219:9200/_sql?sql="+URLEncoder.encode(ssdp); //just a string
		String sURLL = "http://52.66.9.219:9200/_sql?sql="+URLEncoder.encode(ssL); //just a string
		
		//System.out.println(wastage);
//	String	sURL1 = URLEncoder.encode(sURL, "UTF-8");
	    // Connect to the URL using java's native library
	    URL url = new URL(sURLdp);
	    URLConnection requestdp = url.openConnection();
	    requestdp.connect();

	    // Convert to a JSON object to print data
	    JsonParser jpdp = new JsonParser(); //from gson
	    JsonElement rootdp = jpdp.parse(new InputStreamReader((InputStream) requestdp.getContent())); //Convert the input stream to a json element
	    String rootobjdp = rootdp.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
	    
	    //May be an array, may be an object. 
	    System.out.println(rootobjdp);
	  // String value = rootobj.get("value").getAsString(); //just grab the zipcode
	    //System.out.println(value);
	    
	    
	    URL url1 = new URL(sURLL);
	    URLConnection requestl = url1.openConnection();
	    requestl.connect();

	    // Convert to a JSON object to print data
	    JsonParser jpL = new JsonParser(); //from gson
	    JsonElement rootl = jpL.parse(new InputStreamReader((InputStream) requestl.getContent())); //Convert the input stream to a json element
	    String rootobjl = rootl.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(loss_sale_today)").getAsJsonObject().get("value").getAsString();
	    
	    //May be an array, may be an object. 
	    System.out.println(rootobjl);
	    
	    System.out.println("***********************");
	   // int dplan = Integer.parseInt(rootobj);
	  double dplan=  Double.parseDouble(rootobjdp);
	  double loss=  Double.parseDouble(rootobjl);
	   // int dplan = new Integer(rootobj).intValue();
		//int wastage = Integer.parseInt(rootobj1);			
		System.out.println(dplan);
		System.out.println(loss);
		double lossSalepercentage=(loss)/dplan*100;
		System.out.println(lossSalepercentage+" Loss Sale percentage");

		
		
	}
}
