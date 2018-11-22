package in.licious.dataplanning_API;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import in.licious.test.BaseTest;
import in.licious.util.ReadData;

import org.testng.AssertJUnit;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONObject;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class CreateProductionPlanAPI extends BaseTest{
	@Test
	public void productionPlanTest_TC06() throws Throwable
	{
		
	
	RequestSpecification request = RestAssured.given();
	 double PP=0;
	 ReadData rd=new ReadData();
		String excelFilePath="/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
	// String baseprodURLl="https://plan-es1.licious.app/_sql?sql=";
	String baseprodURLl="http://52.66.9.219:9200/_sql?sql=";
		JSONObject requestParams = new JSONObject();
		requestParams.put("from", rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 30)); 
		requestParams.put("to",rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 30)); 
		//requestParams.put("city", "1"); 
		// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");
		// Add a header stating the Request body is a JSON
		request.header("token", "e6e061838856bf47e1de730719fb2609");
		
		request.body(requestParams.toJSONString());
		
	//	P.P(T-1) = D.P(T)-Min[[M.I(T-2), C.S(T-2) - D.P (T-1)]]
		Response response = request.post(rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 2));
		//request.post()""
        Response   responseview=   request.post("http://planning-api.licious.in/production/viewplan");
		int statusCode = response.getStatusCode();
		
//		 response = request.get("/B_C2");
//		 
//		 ResponseBody body = response.getBody();
		 
		 // By using the ResponseBody.asString() method, we can convert the  body
		 // into the string representation.
		// System.out.println("Response Body is: " + body.asString());
		
		 
		ResponseBody data = response.getBody();
		
		AssertJUnit.assertEquals(statusCode, 200);
		// logger.log(LogStatus.PASS, "Test Case is Passed ");
		

		System.out.println(statusCode);
		
		System.out.println("****************");
		
RequestSpecification request1 = RestAssured.given();
		
		JSONObject requestParams1 = new JSONObject();
		requestParams1.put("week","47" ); 
		requestParams1.put("year","2018" );
		requestParams1.put("ck", "CK_001");
		requestParams1.put("date","2018-11-20" );
		requestParams1.put("type","production" );
		
		// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");
		// Add a header stating the Request body is a JSON
		request.header("token", "e6e061838856bf47e1de730719fb2609");
		
		request.body(requestParams1.toJSONString());
		
		Response response1 = request.post("http://planning-api.licious.in/production/viewplan");

		int statusCode1 = response.getStatusCode();
		AssertJUnit.assertEquals(statusCode1, 200);
		String data1 = response.getContentType();
		ResponseBody data2 = response.getBody();

		System.out.println(statusCode);
		System.out.println(data1);
		String value1=data2.asString();
		System.out.println(data2.asString());	
		
		
		
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();
        String todate = dateFormat.format(date);
         System.out.println(todate);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date todate1 = cal.getTime();    
        String fromdate = dateFormat.format(todate1);
		System.out.println(fromdate);
		cal.add(Calendar.DATE, -1);
        Date fromdate1 = cal.getTime();    
        String fromdate2 = dateFormat.format(fromdate1);
		System.out.println(fromdate2);
		cal.add(Calendar.DATE, -1);
        Date fromdate3 = cal.getTime();    
        String fromdate4 = dateFormat.format(fromdate3);
		System.out.println(fromdate4);
		cal.add(Calendar.DATE, -1);
        Date fromdate5 = cal.getTime();    
        String fromdate6 = dateFormat.format(fromdate5);
		System.out.println(fromdate6);
		
		
	String dateDPT="'"+todate+"'";
	//etest.log(LogStatus.PASS,"Demand Plan fot T is "+dateDPT);
	System.out.println(dateDPT);
	String dateDPT1="'"+fromdate+"'";
	//etest.log(LogStatus.PASS,"Demand Plan fot T-1 is "+dateDPT1);
	System.out.println(dateDPT1);
	String dateDPT2="'"+fromdate2+"'";
	//etest.log(LogStatus.PASS,"Demand Plan fot T-2 is "+dateDPT2);
	System.out.println(dateDPT2);
	
	String dateHI_2="'"+fromdate2+"'";
	//etest.log(LogStatus.PASS,"Hub Inward  for T-2 is "+dateHI_2);
	System.out.println(dateHI_2);
	String dateCS_2="'"+fromdate2+"'";
	//etest.log(LogStatus.PASS,"Closing Stock  for T-2 is "+dateCS_2);
	System.out.println(dateCS_2);
	
	
	String ssDPmaxV=rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 31);
			//"select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_5785b9065d7e1' and date=";
	String sURLdpVersion =baseprodURLl+URLEncoder.encode(ssDPmaxV)+dateDPT;
	System.out.println(sURLdpVersion);
	 URL urldpMaxVer = new URL(sURLdpVersion);
	 
	 URLConnection requestdpmaxVar = urldpMaxVer.openConnection();
	 requestdpmaxVar.connect();
	 
	   JsonParser jpdpmaxVersion = new JsonParser(); //from gson
	    JsonElement rootdpmaxVer = jpdpmaxVersion.parse(new InputStreamReader((InputStream) requestdpmaxVar.getContent())); //Convert the input stream to a json element
	    String rootobjdpmaxVersion = rootdpmaxVer.getAsJsonObject().get("aggregations").getAsJsonObject().get("MAX(version)").getAsJsonObject().get("value").getAsString();
	    System.out.println(rootobjdpmaxVersion);
	    double dpmaxVersiond=  Double.parseDouble(rootobjdpmaxVersion);
	    int dpmaxVersion=(int)dpmaxVersiond;
	    String version="'"+dpmaxVersion+"'";
	    //etest.log(LogStatus.PASS,"Maximum version of Demand Plan is "+version);
	    System.out.println(version);
	
	String ssDP=rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 14);
	//String ssDPmaxV="select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date=";
	
	
	String ssHIt=rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 15);
	
	String ssCS=rd.readDataFromExcel(excelFilePath, "Dataplanning", 6, 16);
	
	 String sURLdp =baseprodURLl+URLEncoder.encode(ssDP)+dateDPT;
	// String sURLdp =baseprodURLl+URLEncoder.encode(ssDP)+dateDPT+"and version="+version; 
	// String sURLdp =baseprodURLl+URLEncoder.encode(ssDP);
	 System.out.println(sURLdp);
	// String sURLdpVersion =baseprodURLl+URLEncoder.encode(ssDPmaxV)+dateDPT; //just a string
	 String sURLdp1 = baseprodURLl+URLEncoder.encode(ssDP)+dateDPT1;
	
	
	
		 URL urldp = new URL(sURLdp);
		    URLConnection requestdp = urldp.openConnection();
		    requestdp.connect();

		    // Convert to a JSON object to print data
		    JsonParser jpdp = new JsonParser(); //from gson
		    JsonElement rootdp = jpdp.parse(new InputStreamReader((InputStream) requestdp.getContent())); //Convert the input stream to a json element
		    String rootobjdp = rootdp.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
		    
		    //May be an array, may be an object. 
		    System.out.println(rootobjdp+"demand Plan");
		    
		//String sURLdp1 = baseprodURLl+URLEncoder.encode(ssDP); //just a string
		
		 URL urldp1 = new URL(sURLdp1);
		    URLConnection requestdp1 = urldp1.openConnection();
		    requestdp1.connect();

		    // Convert to a JSON object to print data
		    JsonParser jpdp1 = new JsonParser(); //from gson
		    JsonElement rootdp1 = jpdp1.parse(new InputStreamReader((InputStream) requestdp1.getContent())); //Convert the input stream to a json element
		    String rootobjdp1 = rootdp1.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
		    
		    //May be an array, may be an object. 
		    System.out.println(rootobjdp1+"Demand Plan T-1");
		    

		String sURLHI = baseprodURLl+URLEncoder.encode(ssHIt)+dateHI_2; //just a string
		URL urlHI = new URL(sURLHI);
	    URLConnection requestHI = urlHI.openConnection();
	    requestHI.connect();

	    // Convert to a JSON object to print data
	    JsonParser jpMI = new JsonParser(); //from gson
	    JsonElement rootHI = jpMI.parse(new InputStreamReader((InputStream) requestHI.getContent())); //Convert the input stream to a json element
	    String rootobjHI = rootHI.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(received_quantity)").getAsJsonObject().get("value").getAsString();
	    
		
		String sURLCS = baseprodURLl+URLEncoder.encode(ssCS)+dateCS_2; //just a string
		
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
	 
	    
	   
	  double dplan=  Double.parseDouble(rootobjdp);
	  double dplan1=  Double.parseDouble(rootobjdp1);
	  double closingstock=  Double.parseDouble(rootobjCS);

	  double hubInward=Double.parseDouble(rootobjHI);
	  System.out.println("*************************");
	  System.out.println(dplan+"Demand Plan");
	  System.out.println(dplan1+"Demand Plan t-1");
	  System.out.println(closingstock+"Closing stock t-2");
	  System.out.println(hubInward+"Hub inward t-2");
	  double value=closingstock-dplan1;
	  double pp_1=0;
	  double ppT_1=0;
	  if(value>0)
	  {
		if(hubInward<value)  
		{
			ppT_1=dplan-hubInward;
			if(ppT_1>0)
			{
				// etest.log(LogStatus.PASS,"Production Plan for T Demand Plan is "+ppT_1);
				System.out.println("Production Plan "+ppT_1);
			}else
			{
				// etest.log(LogStatus.PASS,"Production Plan for T Demand Plan is "+0);
				System.out.println("Production Plan "+0);
			}
		}
			else
			{
				ppT_1=dplan-value;	
				if(ppT_1>0)
				{
					// etest.log(LogStatus.PASS,"Production Plan for T Demand Plan is "+ppT_1);
					System.out.println("Production Plan "+ppT_1);
				}else
				{
					 //etest.log(LogStatus.PASS,"Production Plan for T Demand Plan is "+0);
					System.out.println("Production Plan "+0);
				}
			}
		
	  }else
	  {
		  ppT_1=dplan-hubInward; 
		  if(ppT_1>0)
			{
			  //etest.log(LogStatus.PASS,"Production Plan for T Demand Plan is "+ppT_1);
				System.out.println(ppT_1+"Production Plan ");
			}else
			{
				// etest.log(LogStatus.PASS,"Production Plan for T Demand Plan is "+0);
				System.out.println("Production Plan "+0);
			}
	  }
	  String numberAsString = String.valueOf(ppT_1);
	if(numberAsString.contains(value1))	
	{
		System.out.println("pass");
	}
	else
	{
		System.out.println("fail");
	}
	//extent.flush();
	//extent.close();
	}
}
