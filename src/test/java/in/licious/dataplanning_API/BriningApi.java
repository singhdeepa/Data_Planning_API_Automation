package in.licious.dataplanning_API;

import org.testng.annotations.Test;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

public class BriningApi {
	@Test
	public void generatebriningPlanTest_TC07() throws Throwable
	{
		String baseprodURLl="https://plan-es1.licious.app/_sql?sql=";

//		RequestSpecification request = RestAssured.given();
//		
//		
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
//		Response response = request.post("http://13.126.207.17/production/services/brining/generate");
//
//		int statusCode = response.getStatusCode();
//		Assert.assertEquals(statusCode, 200);
//		String data = response.getContentType();
//		ResponseBody data1 = response.getBody();
//
//		System.out.println(statusCode);
//		System.out.println(data);
//	
//		X1 = [C.S(T-3) - D.P(T-2)]+Dispatch Plan (T-2)
//		X2 = Min[X1,{H.I(T-3) +H.I(T-4) + Dispatch(T-2)}]
//		X3 = X2-D.P(T-1)
//		X4 = Min[X3,{H.I(T-3) + Dispatch(T-2)}]
//		B.P(T-2) = D.P(T) -X4
		String datedp="'2018-11-05'";
		String datedp1="'2018-11-04'";
		String datedp2="'2018-11-03'";
		String ssDP="select sum(final_forecast) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date=";
		//String ssDP="select sum(final_forecast) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date='2018-11-05'";
		String ssDP1="select sum(final_forecast) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date='2018-11-04'";
		String ssDP2="select sum(final_forecast) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date='2018-11-03'";
//		
		String ssHIt_4="select sum(received_quantity) from operationdataplan where sku='pr_57235922d122e' and to in ('1','4','10') and source='11' and dispatch_datetime='2018-11-02' order by dispatch_datetime desc";
		String ssHIt_3="select sum(received_quantity) from operationdataplan where sku='pr_57235922d122e' and to in ('1','4','10') and source='11' and dispatch_datetime='2018-11-01' order by dispatch_datetime desc";
		String ssCS="select sum(stock_units) from closingstock where osdate='2018-11-02' and hub_id in ('1','4','10') and product_id='pr_57235922d122e'";
		 String ssdispPlan="select sum(dispatch_plan_qty) from dispatch-plan where product_id='pr_57235922d122e' and cluster_id='B_C2' and dispatch_date='2018-11-03'";
		
		 String sURLdp =baseprodURLl+URLEncoder.encode(ssDP)+datedp; //just a string
		 String sURLdp1 = baseprodURLl+URLEncoder.encode(ssDP)+datedp1;
		 String sURLdp2 = baseprodURLl+URLEncoder.encode(ssDP)+datedp2; //just a string
		//String sURLdp =baseprodURLl+URLEncoder.encode(ssDP); //just a string
		
		 URL urldp = new URL(sURLdp);
		 URLConnection requestdp = urldp.openConnection();
		 requestdp.connect();
		 

		 URL urldp1 = new URL(sURLdp1);
		    URLConnection requestdp1 = urldp1.openConnection();
		    requestdp1.connect();
		    
		    URL urldp2 = new URL(sURLdp2);
		    URLConnection requestdp2 = urldp2.openConnection();
		    requestdp2.connect();

		    // Convert to a JSON object to print data
		    JsonParser jpdp = new JsonParser(); //from gson
		    JsonElement rootdp = jpdp.parse(new InputStreamReader((InputStream) requestdp.getContent())); //Convert the input stream to a json element
		    String rootobjdp = rootdp.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
		    
		    JsonParser jpdp1 = new JsonParser(); //from gson
		    JsonElement rootdp1 = jpdp1.parse(new InputStreamReader((InputStream) requestdp1.getContent())); //Convert the input stream to a json element
		    String rootobjdp1 = rootdp1.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
		    
		    JsonParser jpdp2 = new JsonParser(); //from gson
		    JsonElement rootdp2 = jpdp2.parse(new InputStreamReader((InputStream) requestdp2.getContent())); //Convert the input stream to a json element
		    String rootobjdp2 = rootdp2.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
		    
		   
			    
			    String sURLHIt_3 = baseprodURLl+URLEncoder.encode(ssHIt_3); //just a string
				
				URL urlhIt_3 = new URL(sURLHIt_3);
			    URLConnection requesthIt_3 = urlhIt_3.openConnection();
			    requesthIt_3.connect();

			    // Convert to a JSON object to print data
			    JsonParser jphIt_3 = new JsonParser(); //from gson
			    JsonElement roothIt_3 = jphIt_3.parse(new InputStreamReader((InputStream) requesthIt_3.getContent())); //Convert the input stream to a json element
			    String rootobjhIt_3 = roothIt_3.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(received_quantity)").getAsJsonObject().get("value").getAsString();
			    
           String sURLHIt_4 = baseprodURLl+URLEncoder.encode(ssHIt_4); //just a string
				
				URL urlhIt_4 = new URL(sURLHIt_4);
			    URLConnection requesthIt_4 = urlhIt_4.openConnection();
			    requesthIt_4.connect();

			    // Convert to a JSON object to print data
			    JsonParser jphIt_4 = new JsonParser(); //from gson
			    JsonElement roothIt_4 = jphIt_4.parse(new InputStreamReader((InputStream) requesthIt_4.getContent())); //Convert the input stream to a json element
			    String rootobjhIt_4 = roothIt_4.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(received_quantity)").getAsJsonObject().get("value").getAsString();
			    
			    //May be an array, may be an object. 
			   
			    
		String sURLCS =baseprodURLl+URLEncoder.encode(ssCS); //just a string
		
	    // Connect to the URL using java's native library
	    URL urlCS = new URL(sURLCS);
	    URLConnection requestCS = urlCS.openConnection();
	    requestCS.connect();

	    // Convert to a JSON object to print data
	    JsonParser jpCS = new JsonParser(); //from gson
	    JsonElement rootCS = jpCS.parse(new InputStreamReader((InputStream) requestCS.getContent())); //Convert the input stream to a json element
	    String rootobjCS = rootCS.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(stock_units)").getAsJsonObject().get("value").getAsString();
	    
	    
        String sURLDispPlan = baseprodURLl+URLEncoder.encode(ssdispPlan); //just a string
		
	    // Connect to the URL using java's native library
	    URL urlDispP = new URL(sURLDispPlan);
	    URLConnection requestDispP = urlDispP.openConnection();
	    requestDispP.connect();

	    // Convert to a JSON object to print data
	    JsonParser jpDispP = new JsonParser(); //from gson
	    JsonElement rootDispP = jpDispP.parse(new InputStreamReader((InputStream) requestDispP.getContent())); //Convert the input stream to a json element
	    String rootobjDispP = rootDispP.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(dispatch_plan_qty)").getAsJsonObject().get("value").getAsString();
	    
	    //May be an array, may be an object. 
	    
//		X1 = [C.S(T-3) - D.P(T-2)]+Dispatch Plan (T-2)
//		X2 = Min[X1,{H.I(T-3) +H.I(T-4) + Dispatch(T-2)}]
//		X3 = X2-D.P(T-1)
//		X4 = Min[X3,{H.I(T-3) + Dispatch(T-2)}]
//		B.P(T-2) = D.P(T) -X4
	 
	  double dplan=  Double.parseDouble(rootobjdp);
	  double dplan1=  Double.parseDouble(rootobjdp1);
	  double dplan2=  Double.parseDouble(rootobjdp2);
	  double closingstock=  Double.parseDouble(rootobjCS);
	  double DispatchPlan=  Double.parseDouble(rootobjDispP);
	  double hubInwardt_3=  Double.parseDouble(rootobjhIt_3);
	  double hubInwardt_4=  Double.parseDouble(rootobjhIt_4);
	//  double mainInwardt_4=  Double.parseDouble(rootobjMIt_4);
	//  double normalInwardt_4=  Double.parseDouble(rootobjNIt_4);
	 // double hubInwardt_4=mainInwardt_4+normalInwardt_4;
	  System.out.println(hubInwardt_4+"Hub Inward t-4");
	  System.out.println("*************************");
	  System.out.println(dplan+" Demand Plan");
	  System.out.println(dplan1+" Demand Plan -1");
	  System.out.println(dplan2+" Demand Plan -2");
	  System.out.println(closingstock+" closing stock");
	  System.out.println(DispatchPlan+ " Dispatch Plan");
	  System.out.println(hubInwardt_4 +"Hub Inwart t-4");
	  System.out.println(hubInwardt_3+"hub Inward t-3");
	  System.out.println("*************************");
	//  System.out.println(hubInwardt_3+"Hub Inwart t-3");
	//  double mainInwardt_3=  Double.parseDouble(rootobjMIt_3);
	//  double normalInwardt_3=  Double.parseDouble(rootobjNIt_3);
		// double hubInwardt_3=mainInwardt_3+normalInwardt_3;
		
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
		  x2=x1;
		  x3=x2-dplan1;
		  if(x3<x4_4)
		  {
			  x4=x3;
			  b_pt_2=dplan-x4;
			 // System.out.println(b_pt_2);
		  }else
		  {
			 x4= x4_4;
			 b_pt_2=dplan-x4;
			 //System.out.println(b_pt_2);
		  }
		 }else
		 {
			x2= x2_2;
			x3=x2-dplan1;
			if(x3<x4_4)
			  {
				  x4=x3;
				  b_pt_2=dplan-x4;
				  //System.out.println(b_pt_2);
			  }else
			  {
				 x4= x4_4;
				 b_pt_2=dplan-x4;
				// System.out.println(b_pt_2);
			  }
		 }
	  
	  System.out.println(b_pt_2+"Brining plan");
	 
	  
	 
		
		
		
	}
}
