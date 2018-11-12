package in.licious.dataplanning_API;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.testng.AssertJUnit;


import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.simple.JSONObject;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class CreateProductionPlanAPI {
	@Test
	public void productionPlanTest_TC05() throws Throwable
	{
		
//	RequestSpecification request = RestAssured.given();
	 double PP=0;
	 String baseprodURLl="https://plan-es1.licious.app/_sql?sql=";
//		JSONObject requestParams = new JSONObject();
//		requestParams.put("from", "2018-11-03"); 
//		requestParams.put("to","2018-11-03"); 
//		//requestParams.put("city", "1"); 
//		// Add a header stating the Request body is a JSON
//		request.header("Content-Type", "application/json");
//		// Add a header stating the Request body is a JSON
//		request.header("token", "e6e061838856bf47e1de730719fb2609");
//		
//		request.body(requestParams.toJSONString());
//		
//	//	P.P(T-1) = D.P(T)-Min[[M.I(T-2), C.S(T-2) - D.P (T-1)]]
//		Response response = request.post("https://planning-api.licious.in/production/services/generate");
//
//		int statusCode = response.getStatusCode();
//		AssertJUnit.assertEquals(statusCode, 200);
//		String data = response.getContentType();
//		ResponseBody data1 = response.getBody();
//
//		System.out.println(statusCode);
//		System.out.println(data);
//		System.out.println(data1.asString());
		
		
		String ssDP="select sum(final_forecast) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_5785b9065d7e1' and date='2018-11-06'";
		String ssDP1="select sum(final_forecast) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_5785b9065d7e1' and date='2018-11-05'";
		//String ssMI="select sum(received_quantity) from operationdataplan where sku='pr_5785b9065d7e1' and to in ('1','4','10') and received_datetime='2018-11-02' and dispatch_id is Not null";
		//String ssNI="select sum(received_quantity) from operationdataplan where sku='pr_5785b9065d7e1' and to in ('1','4','10') and received_datetime='2018-11-02' and dispatch_id is null";
		String ssHI="select sum(received_quantity) from operationdataplan where sku='pr_5785b9065d7e1' and to in ('1','4','10') and source='11' and dispatch_datetime='2018-11-04' order by dispatch_datetime desc";
		String ssCS="select sum(stock_units) from closingstock where osdate='2018-11-04' and hub_id in ('1','4','10') and product_id='pr_5785b9065d7e1'";
		
		
		String sURLdp = baseprodURLl+URLEncoder.encode(ssDP); //just a string
		
		 URL urldp = new URL(sURLdp);
		    URLConnection requestdp = urldp.openConnection();
		    requestdp.connect();

		    // Convert to a JSON object to print data
		    JsonParser jpdp = new JsonParser(); //from gson
		    JsonElement rootdp = jpdp.parse(new InputStreamReader((InputStream) requestdp.getContent())); //Convert the input stream to a json element
		    String rootobjdp = rootdp.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
		    
		    //May be an array, may be an object. 
		    System.out.println(rootobjdp+"demand Plan");
		    
		String sURLdp1 = baseprodURLl+URLEncoder.encode(ssDP1); //just a string
		
		 URL urldp1 = new URL(sURLdp1);
		    URLConnection requestdp1 = urldp1.openConnection();
		    requestdp1.connect();

		    // Convert to a JSON object to print data
		    JsonParser jpdp1 = new JsonParser(); //from gson
		    JsonElement rootdp1 = jpdp1.parse(new InputStreamReader((InputStream) requestdp1.getContent())); //Convert the input stream to a json element
		    String rootobjdp1 = rootdp1.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
		    
		    //May be an array, may be an object. 
		    System.out.println(rootobjdp1+"Demand Plan T-1");
		    
//		String sURLMI = "https://plan-es1.licious.app/_sql?sql="+URLEncoder.encode(ssMI); //just a string
//		URL urlMI = new URL(sURLMI);
//	    URLConnection requestMI = urlMI.openConnection();
//	    requestMI.connect();
//
//	    // Convert to a JSON object to print data
//	    JsonParser jpMI = new JsonParser(); //from gson
//	    JsonElement rootMI = jpMI.parse(new InputStreamReader((InputStream) requestMI.getContent())); //Convert the input stream to a json element
//	    String rootobjMI = rootMI.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(received_quantity)").getAsJsonObject().get("value").getAsString();
//	    
//	    //May be an array, may be an object. 
//	    //System.out.println(rootobjMI+"MainInward");
//	    
//		String sURLNI = "https://plan-es1.licious.app/_sql?sql="+URLEncoder.encode(ssNI); //just a string
//		
//		URL urlNI = new URL(sURLNI);
//	    URLConnection requestNI = urlNI.openConnection();
//	    requestNI.connect();
//
//	    // Convert to a JSON object to print data
//	    JsonParser jpNI = new JsonParser(); //from gson
//	    JsonElement rootNI = jpNI.parse(new InputStreamReader((InputStream) requestNI.getContent())); //Convert the input stream to a json element
//	    String rootobjNI = rootNI.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(received_quantity)").getAsJsonObject().get("value").getAsString();
//	    
//	    //May be an array, may be an object. 
//	    //System.out.println(rootobjNI);
//	    
//		String rootobjHI=rootobjMI+rootobjNI;
//		
//		System.out.println(rootobjMI+"Main Inward");
//		System.out.println(rootobjNI+"Normal Inward");
//		
		String sURLHI = baseprodURLl+URLEncoder.encode(ssHI); //just a string
		URL urlHI = new URL(sURLHI);
	    URLConnection requestHI = urlHI.openConnection();
	    requestHI.connect();

	    // Convert to a JSON object to print data
	    JsonParser jpMI = new JsonParser(); //from gson
	    JsonElement rootHI = jpMI.parse(new InputStreamReader((InputStream) requestHI.getContent())); //Convert the input stream to a json element
	    String rootobjHI = rootHI.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(received_quantity)").getAsJsonObject().get("value").getAsString();
	    
		
		String sURLCS = baseprodURLl+URLEncoder.encode(ssCS); //just a string
		
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
	    
	    
	   
	  double dplan=  Double.parseDouble(rootobjdp);
	  double dplan1=  Double.parseDouble(rootobjdp1);
	  double closingstock=  Double.parseDouble(rootobjCS);
//	  double mainInward=  Double.parseDouble(rootobjMI);
//	  double normalInward=  Double.parseDouble(rootobjNI);
//		 double hubInward=mainInward+normalInward;
	  double hubInward=Double.parseDouble(rootobjHI);
	  System.out.println("*************************");
	  System.out.println(dplan);
	  System.out.println(dplan1);
	  System.out.println(closingstock);
	  System.out.println(hubInward);
	  double value=closingstock-dplan1;
	 if(hubInward<value)
	 {
		 PP=dplan-hubInward;
		 System.out.println(PP+" production Plan");
	 }
	 else
	 {
		 PP =dplan-value;
		 System.out.println(PP+" production Plan");
	 }
	  // PP= dplan "-" [closingstock-dplan1];
//		P.P(T-1) = D.P(T)-Min[[M.I(T-2), C.S(T-2) - D.P (T-1)]]
	   // int dplan = new Integer(rootobj).intValue();
		//int wastage = Integer.parseInt(rootobj1);			
		
		

	}
}
