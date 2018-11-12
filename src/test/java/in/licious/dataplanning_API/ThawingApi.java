package in.licious.dataplanning_API;

import org.testng.annotations.Test;
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

public class ThawingApi {
	@Test
	public void generateThawingPlanTest_TC06() throws Throwable
	{
		double tp1;
		double tp;
		String baseprodURLl="https://plan-es1.licious.app/_sql?sql=";
//	    RequestSpecification request = RestAssured.given();
//		double tp1;
//		double tp;
//		JSONObject requestParams = new JSONObject();
//		requestParams.put("from", "2018-11-01"); 
//		requestParams.put("to","2018-11-01"); 
//		//requestParams.put("city", "1"); 
//		// Add a header stating the Request body is a JSON
//		request.header("Content-Type", "application/json");
//		// Add a header stating the Request body is a JSON
//		request.header("token", "e6e061838856bf47e1de730719fb2609");
//		
//		request.body(requestParams.toJSONString());
//		
//		Response response = request.post("http://13.126.207.17/production/services/thawing/generate");
//
//		int statusCode = response.getStatusCode();
//		Assert.assertEquals(statusCode, 200);
//		String data = response.getContentType();
//		ResponseBody data1 = response.getBody();
//
//		System.out.println(statusCode);
//		System.out.println(data);
//		System.out.println(data1.asString());
//		
		//TP(T-2)=MAX[FF(T)-MAX{CS(T-3)-[FF(T-2)+FF(T-1)}]
		
		String ssDP="select sum(final_forecast) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235b81a66f9' and date='2018-11-05'";
		String ssDP1="select sum(final_forecast) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235b81a66f9' and date='2018-11-04'";
		String ssDP2="select sum(final_forecast) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235b81a66f9' and date='2018-11-03'";
//		String ssMI="select sum(received_quantity) from operationdataplan where sku='pr_5785b9065d7e1' and to in ('1','4','10') and source=11 and received_datetime in ('2018-10-29')";
//		String ssNI="select sum(received_quantity) from operationdataplan where sku='pr_5785b9065d7e1' and to in ('1','4','10') and received_datetime='2018-10-29' and dispatch_id is null";
		String ssCS="select sum(stock_units) from closingstock where osdate='2018-11-02' and hub_id in ('1','4','10') and product_id='pr_57235b81a66f9'";
		
		
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
		    
		    String sURLdp2 = baseprodURLl+URLEncoder.encode(ssDP2); //just a string
			
			 URL urldp2 = new URL(sURLdp2);
			    URLConnection requestdp2 = urldp2.openConnection();
			    requestdp2.connect();

			    // Convert to a JSON object to print data
			    JsonParser jpdp2 = new JsonParser(); //from gson
			    JsonElement rootdp2 = jpdp2.parse(new InputStreamReader((InputStream) requestdp2.getContent())); //Convert the input stream to a json element
			    String rootobjdp2 = rootdp2.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
			    
			    //May be an array, may be an object. 
			    System.out.println(rootobjdp2+"Demand Plan T-2");
		    
//		String sURLMI = "http://52.66.9.219:9200/_sql?sql="+URLEncoder.encode(ssMI); //just a string
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
//		String sURLNI = "http://52.66.9.219:9200/_sql?sql="+URLEncoder.encode(ssNI); //just a string
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
	    
	    
	  //TP(T-2)=MAX[FF(T)-MAX{CS(T-3)-[FF(T-2)+FF(T-1)}]
	  double dplan=  Double.parseDouble(rootobjdp);
	  double dplan1=  Double.parseDouble(rootobjdp1);
	  double dplan2=  Double.parseDouble(rootobjdp2);
	  double closingstock=  Double.parseDouble(rootobjCS);
	  //double mainInward=  Double.parseDouble(rootobjMI);
	//  double normalInward=  Double.parseDouble(rootobjNI);
		// double hubInward=mainInward+normalInward;
	  System.out.println("*************************");
	  System.out.println(dplan+"Demand Plan");
	  System.out.println(dplan1+"Demand Plan -1");
	  System.out.println(dplan2+"Demand Plan -2");
	  System.out.println(closingstock);
	  double value=dplan2+dplan1;
	  double value1=0;
	  //System.out.println(hubInward);
	  
	 if(closingstock>value)
	 {
		 value1=closingstock-value;
		 tp=dplan-value1;
		// System.out.println(tp);
		 if(dplan>tp)
		 {
			 tp1=tp;
			 System.out.println(tp1+" Thawing Plan"); 
		 }
		 else
		 {
			 tp1=0;
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
			 System.out.println(tp+" Thawing Plan"); 
		 }
		 else
		 {
			 tp1=0;
			 System.out.println(tp1+" Thawing Plan");
		 }
		 //System.out.println(tp1+" Thawing Plan");
	 }
	}
}
