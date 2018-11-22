package in.licious.dataplanning_API;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import in.licious.util.ReadData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class LossSale_API {
	@Test
	public void dashboardLossSaleTest_TC12() throws Throwable
	{
		//String baseprodURLl="http://52.66.9.219:9200/_sql?sql=";
        String baseprodURLl="https://plan-es1.licious.app/_sql?sql=";
		
		ReadData rd=new ReadData();
		String excelFilePath="/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
		//RestAssured.baseURI="https://planning-api.licious.in/forecast/services/systemforecast";
		RequestSpecification request = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("from", rd.readDataFromExcel(excelFilePath, "Dataplanning", 12, 12)); 
		requestParams.put("to", rd.readDataFromExcel(excelFilePath, "Dataplanning", 12, 13));
		
		// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");
		// Add a header stating the Request body is a JSON
		request.header("token", "e6e061838856bf47e1de730719fb2609");
		
		request.body(requestParams.toJSONString());
		
		Response response = request.post(rd.readDataFromExcel(excelFilePath, "Dataplanning", 12, 2));

		int statusCode = response.getStatusCode();
		AssertJUnit.assertEquals(statusCode, 200);
		String data = response.getContentType();
		ResponseBody data1 = response.getBody();

		System.out.println(statusCode);
		System.out.println(data);
		System.out.println(data1.asString());
		System.out.println("*****************************************");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String dateString=rd.readDataFromExcel(excelFilePath, "Dataplanning", 12, 30);
        // Get a Date object from the date string
        Date myDate1 = dateFormat.parse(dateString);
        
		String dateDPTsam="'"+dateString+"'";
		String dateDPT="'"+dateString+"'"+"and%20version%20=";
		
		
		
		
		
		
		
		//select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date=
		String ssDPmaxV=rd.readDataFromExcel(excelFilePath, "Dataplanning", 12, 31);
		String sURLdpVersion =baseprodURLl+URLEncoder.encode(ssDPmaxV)+dateDPTsam;
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
		    String versiondp="'"+dpmaxVersion+"'";
		    System.out.println(versiondp);
		
		    String ssDP=rd.readDataFromExcel(excelFilePath, "Dataplanning", 12, 14);
		    String ssLS=rd.readDataFromExcel(excelFilePath, "Dataplanning", 12, 32);
		String ssL="select sum(loss_sale_today) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e'and date=";
				//"select sum(loss_sale_today) from demand-plan where city_id='1' and product_id='pr_5785b9065d7e1' and date='2018-10-24' and version ='3'";
		String ssdp="select sum(final_forecast) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e'and date=";
		//"select sum(final_forecast) from demand-plan where city_id='1' and product_id='pr_5785b9065d7e1' and date='2018-10-24' and version ='3'";
		String sURLdp =baseprodURLl+URLEncoder.encode(ssDP)+dateDPT+versiondp; //just a string
		String sURLL = baseprodURLl+URLEncoder.encode(ssLS)+dateDPT+versiondp; //just a string
		
		System.out.println(sURLdp);
		System.out.println(sURLL);
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
