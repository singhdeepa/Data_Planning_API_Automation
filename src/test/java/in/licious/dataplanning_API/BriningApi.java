package in.licious.dataplanning_API;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import org.json.simple.JSONObject;


import org.testng.Assert;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import in.licious.util.Helper;
import in.licious.util.ReadData;
import in.licious.util.Utilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class BriningApi  {
	/**
	 * @throws Throwable
	 */
	
	ExtentReports extent;
	 ExtentTest logger;
	 Helper helper=new Helper();
		
	 @BeforeTest
	 public void beforeTest()
	 {
		 extent = new ExtentReports ("/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/API_HTML_Report/" +helper.getCurrentDateTime()+".html", false);
		 //extent.addSystemInfo("Environment","Environment Name")
		 extent
		                .addSystemInfo("Host Name", "QA TEAM")
		                .addSystemInfo("Environment", "Automation Testing")
		                .addSystemInfo("User Name", "Deepa singh");
		                //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
		                //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
		                extent.loadConfig(new File("/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/extent-config.xml"));
		             //   ExtentReports extent = new ExtentReports("/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/API_HTML_Report/"+helper.getCurrentDateTime()+".html",false); 
		                
	 }
	@Test
	public void generatebriningPlanTest_TC05() throws Throwable
	{
		logger = extent.startTest("Execution Started for Brining Plan "); 
		AssertJUnit.assertTrue(true); 
		ReadData rd=new ReadData();
		String excelFilePath="/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
		String baseprodURLl="http://52.66.9.219:9200/_sql?sql=";
       // String baseprodURLl="https://plan-es1.licious.app/_sql?sql=";
		RequestSpecification request = RestAssured.given();
		
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("from", rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 30)); 
		requestParams.put("to",rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 30)); 
		//requestParams.put("city", "1"); 
		// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");
		// Add a header stating the Request body is a JSON
		request.header("token", "e6e061838856bf47e1de730719fb2609");
		
		request.body(requestParams.toJSONString());
		
		Response response = request.post(rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 2));

		int statusCode = response.getStatusCode();
		AssertJUnit.assertEquals(statusCode, 200);
		logger.log(LogStatus.PASS, "Brining Plan Response  is verified successfully");
		String data = response.getContentType();
		ResponseBody data1 = response.getBody();
		String datta=response.body().asString();
		System.out.println(datta);
		System.out.println(data1.asString());
//	
//		X1 = [C.S(T-3) - D.P(T-2)]+Dispatch Plan (T-2)
//		X2 = Min[X1,{H.I(T-3) +H.I(T-4) + Dispatch(T-2)}]
//		X3 = X2-D.P(T-1)
//		X4 = Min[X3,{H.I(T-3) + Dispatch(T-2)}]
//		B.P(T-2) = D.P(T) -X4
		//Date date =new Date();
		   		
			
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String dateString=rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 30);
        // Get a Date object from the date string
        Date myDate1 = dateFormat.parse(dateString);
        Date  oneDayBefore1 = new Date(myDate1.getTime() - 1);
        String result1 = dateFormat.format(oneDayBefore1);
        System.out.println(result1);
        
        Date myDate2 = dateFormat.parse(result1);
        Date  oneDayBefore2 = new Date(myDate2.getTime() - 1);
        String result2 = dateFormat.format(oneDayBefore2);
        System.out.println(result2);
        
        Date myDate3 = dateFormat.parse(result2);
        Date  oneDayBefore3 = new Date(myDate3.getTime() - 1);
        String result3 = dateFormat.format(oneDayBefore3);
        System.out.println(result3);
		
        Date myDate4 = dateFormat.parse(result3);
        Date  oneDayBefore4 = new Date(myDate4.getTime() - 1);
        String result4 = dateFormat.format(oneDayBefore4);
        System.out.println(result4);
		System.out.println("***************************");
		
		
		String dateDPTsam="'"+dateString+"'";
		String dateDPT1sam="'"+result1+"'";
		String dateDPT2sam="'"+result2+"'";
		String dateDPT="'"+dateString+"'"+"and%20version%20=";
		System.out.println(dateDPT);
		String dateDPT1="'"+result1+"'"+"and%20version%20=";
		System.out.println(dateDPT1);
		String dateDPT2="'"+result2+"'"+"and%20version%20=";
		System.out.println(dateDPT2);
		String dateDispPT2="'"+result2+"'";
		System.out.println(dateDispPT2);
		String dateHI_3="'"+result3+"'";
		System.out.println(dateHI_3);
		String dateCS_3="'"+result3+"'";
		System.out.println(dateCS_3);
		String dateHI_4="'"+result4+"'";
		System.out.println(dateHI_4);
		
		String ssDPmaxV=rd.readDataFromExcel(excelFilePath, "Dataplanning", 5 , 31);
				//"select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date=";
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
		    
		    String sURLdp1Version =baseprodURLl+URLEncoder.encode(ssDPmaxV)+dateDPT1sam;
			System.out.println(sURLdp1Version);
			 URL urldp1MaxVer = new URL(sURLdp1Version);
			 
			 URLConnection requestdp1maxVar = urldp1MaxVer.openConnection();
			 requestdp1maxVar.connect();
			 
			   JsonParser jpdp1maxVersion = new JsonParser(); //from gson
			    JsonElement rootdp1maxVer = jpdp1maxVersion.parse(new InputStreamReader((InputStream) requestdp1maxVar.getContent())); //Convert the input stream to a json element
			    String rootobjdp1maxVersion = rootdp1maxVer.getAsJsonObject().get("aggregations").getAsJsonObject().get("MAX(version)").getAsJsonObject().get("value").getAsString();
			    System.out.println(rootobjdp1maxVersion);
			    double dp1maxVersiond=  Double.parseDouble(rootobjdp1maxVersion);
			    int dp1maxVersion=(int)dp1maxVersiond;
			    String versiondp1="'"+dp1maxVersion+"'";
			    System.out.println(versiondp1);
			    
			    String sURLdp2Version =baseprodURLl+URLEncoder.encode(ssDPmaxV)+dateDPT2sam;
				System.out.println(sURLdp2Version);
				 URL urldp2MaxVer = new URL(sURLdp2Version);
				 
				 URLConnection requestdp2maxVar = urldp2MaxVer.openConnection();
				 requestdp2maxVar.connect();
				 
				   JsonParser jpdp2maxVersion = new JsonParser(); //from gson
				    JsonElement rootdp2maxVer = jpdp2maxVersion.parse(new InputStreamReader((InputStream) requestdp2maxVar.getContent())); //Convert the input stream to a json element
				    String rootobjdp2maxVersion = rootdp2maxVer.getAsJsonObject().get("aggregations").getAsJsonObject().get("MAX(version)").getAsJsonObject().get("value").getAsString();
				    System.out.println(rootobjdp2maxVersion);
				    double dp2maxVersiond=  Double.parseDouble(rootobjdp2maxVersion);
				    int dp2maxVersion=(int)dp2maxVersiond;
				    String versiondp2="'"+dp2maxVersion+"'";
				    System.out.println(versiondp2);
		//String ssDP="select sum(final_forecast) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e'";
		String ssDP=rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 14);
		//String ssDPmaxV="select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date=";
		
		//String ssHIt="select sum(received_quantity) from operationdataplan where sku='pr_57235922d122e' and to in ('1','4','10') and source='11' and dispatch_datetime=";
		String ssHIt=rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 15);
		//String ssCS="select sum(stock_units) from closingstock where hub_id in ('1','4','10') and product_id='pr_57235922d122e' and osdate=";
		String ssCS=rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 16);
		 //String ssdispPlan="select sum(dispatch_plan_qty) from dispatch-plan where product_id='pr_57235922d122e' and cluster_id='B_C2' and dispatch_date=";
		  String ssdispPlan=rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 17);
		  String versioN=rd.readDataFromExcel(excelFilePath, "Dataplanning", 5, 29);
		  String data2=dateDPT+versiondp;
		 String sURLdp =baseprodURLl+URLEncoder.encode(ssDP)+dateDPT+versiondp;
		// String sURLdp =baseprodURLl+URLEncoder.encode(ssDP)+dateDPT+"and version="+version; 
		// String sURLdp =baseprodURLl+URLEncoder.encode(ssDP);
		 System.out.println(sURLdp);
		// String sURLdpVersion =baseprodURLl+URLEncoder.encode(ssDPmaxV)+dateDPT; //just a string
		 String sURLdp1 = baseprodURLl+URLEncoder.encode(ssDP)+dateDPT1+versiondp1;
		 String sURLdp2 = baseprodURLl+URLEncoder.encode(ssDP)+dateDPT2+versiondp2; //just a string
		
		
		 URL urldp = new URL(sURLdp);
		 URLConnection requestdp = urldp.openConnection();
		 requestdp.connect();
		

		 URL urldp1 = new URL(sURLdp1);
		    URLConnection requestdp1 = urldp1.openConnection();
		    requestdp1.connect();
		    
		    URL urldp2 = new URL(sURLdp2);
		    URLConnection requestdp2 = urldp2.openConnection();
		    requestdp2.connect();

		    
		    JsonParser jpdp = new JsonParser(); //from gson
		    JsonElement rootdp = jpdp.parse(new InputStreamReader((InputStream) requestdp.getContent())); 
		    String rootobjdp = rootdp.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
		    
		    
		    JsonParser jpdp1 = new JsonParser(); //from gson
		    JsonElement rootdp1 = jpdp1.parse(new InputStreamReader((InputStream) requestdp1.getContent())); //Convert the input stream to a json element
		    String rootobjdp1 = rootdp1.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
		    
		    JsonParser jpdp2 = new JsonParser(); //from gson
		    JsonElement rootdp2 = jpdp2.parse(new InputStreamReader((InputStream) requestdp2.getContent())); //Convert the input stream to a json element
		    String rootobjdp2 = rootdp2.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
		    
		   
			    
			    String sURLHIt_3 = baseprodURLl+URLEncoder.encode(ssHIt)+dateHI_3; //just a string
				
				URL urlhIt_3 = new URL(sURLHIt_3);
			    URLConnection requesthIt_3 = urlhIt_3.openConnection();
			    requesthIt_3.connect();

			    // Convert to a JSON object to print data
			    JsonParser jphIt_3 = new JsonParser(); //from gson
			    JsonElement roothIt_3 = jphIt_3.parse(new InputStreamReader((InputStream) requesthIt_3.getContent())); //Convert the input stream to a json element
			    String rootobjhIt_3 = roothIt_3.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(received_quantity)").getAsJsonObject().get("value").getAsString();
			    
           String sURLHIt_4 = baseprodURLl+URLEncoder.encode(ssHIt)+dateHI_4; //just a string
				
				URL urlhIt_4 = new URL(sURLHIt_4);
			    URLConnection requesthIt_4 = urlhIt_4.openConnection();
			    requesthIt_4.connect();

			    // Convert to a JSON object to print data
			    JsonParser jphIt_4 = new JsonParser(); //from gson
			    JsonElement roothIt_4 = jphIt_4.parse(new InputStreamReader((InputStream) requesthIt_4.getContent())); //Convert the input stream to a json element
			    String rootobjhIt_4 = roothIt_4.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(received_quantity)").getAsJsonObject().get("value").getAsString();
			    
			    //May be an array, may be an object. 
			   
			    
		String sURLCS =baseprodURLl+URLEncoder.encode(ssCS)+dateCS_3; //just a string
		
	    // Connect to the URL using java's native library
	    URL urlCS = new URL(sURLCS);
	    URLConnection requestCS = urlCS.openConnection();
	    requestCS.connect();

	    // Convert to a JSON object to print data
	    JsonParser jpCS = new JsonParser(); //from gson
	    JsonElement rootCS = jpCS.parse(new InputStreamReader((InputStream) requestCS.getContent())); //Convert the input stream to a json element
	    String rootobjCS = rootCS.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(stock_units)").getAsJsonObject().get("value").getAsString();
	    
	    
        String sURLDispPlan = baseprodURLl+URLEncoder.encode(ssdispPlan)+dateDispPT2; //just a string
		
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
	 // int dpmaxVersion=  Integer.parseInt(rootobjdpmaxVersion);
	  System.out.println(dpmaxVersion+" max version");
	  double dplan1=  Double.parseDouble(rootobjdp1);
	  double dplan2=  Double.parseDouble(rootobjdp2);
	  double closingstock=  Double.parseDouble(rootobjCS);
	  double DispatchPlan=  Double.parseDouble(rootobjDispP);
	  double hubInwardt_3=  Double.parseDouble(rootobjhIt_3);
	  double hubInwardt_4=  Double.parseDouble(rootobjhIt_4);
	
	  System.out.println("*************************");
	  System.out.println(dplan+" Demand Plan");
	  logger.log(LogStatus.PASS, "Demand Plan T for the Marinades Products in Brining Plan is ="+dplan);
	  logger.log(LogStatus.PASS, "Demand Plan T-1 for the Marinades Products in Brining Plan is"+dplan1);
	  logger.log(LogStatus.PASS, "Demand Plan T-2 for the Marinades Products in Brining Plan is"+dplan2);
	  System.out.println(dplan1+" Demand Plan -1");
	  System.out.println(dplan2+" Demand Plan -2");
	  logger.log(LogStatus.PASS, "Closing Stock  T-3 for the Marinades Products in Brining Plan is"+closingstock);
	  System.out.println(closingstock+" closing stock t-3");
	  logger.log(LogStatus.PASS, "Dispatch Plan  T-3 for the Marinades Products in Brining Plan is"+DispatchPlan);
	  System.out.println(DispatchPlan+ " Dispatch Plan t-2");
	  logger.log(LogStatus.PASS, "Hub Inward  T-4 for the Marinades Products in Brining Plan is"+hubInwardt_4);
	  logger.log(LogStatus.PASS, "Hub Inward  T-3 for the Marinades Products in Brining Plan is"+hubInwardt_3);
	  System.out.println(hubInwardt_4 +"Hub Inwart t-4");
	  System.out.println(hubInwardt_3+"hub Inward t-3");
	  System.out.println("*************************");
	
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
			 if(x1<0)
				{
					x1=0;
				}
		  x2=x1;
		  x3=x2-dplan1;
		  if(x3<x4_4)
		  {
			  if(x3<0)
				{
					x3=0;
				}
			  x4=x3;
			  b_pt_2=dplan-x4;
			  if(b_pt_2>0)
			  {
				  logger.log(LogStatus.PASS, "Brining Plan for the Marinades Products in Brining Plan is ="+b_pt_2);
			  System.out.println(b_pt_2+" Brining plan");
			  }else
			  {
				  logger.log(LogStatus.PASS, "Brining Plan for the Marinades Products in Brining Plan is ="+0);
				  System.out.println(0+" Brining plan");  
		  }
		  }else
		  {
			 x4= x4_4;
			 b_pt_2=dplan-x4;
			 if(b_pt_2>0)
			  {
				 logger.log(LogStatus.PASS, "Brining Plan for the Marinades Products in Brining Plan is ="+b_pt_2);
			  System.out.println(b_pt_2+" Brining plan");
			  }else
			  {
				  logger.log(LogStatus.PASS, "Brining Plan for the Marinades Products in Brining Plan is ="+0);
				  System.out.println(0+" Brining plan");  
		  }
		  }
		 }else
		 {
			x2= x2_2;
			x3=x2-dplan1;
			
			if(x3<x4_4)
			  {
				if(x3<0)
				{
					x3=0;
				}
				  x4=x3;
				  b_pt_2=dplan-x4;
				  if(b_pt_2>0)
				  {
					  logger.log(LogStatus.PASS, "Brining Plan for the Marinades Products in Brining Plan is ="+b_pt_2);
				  System.out.println(b_pt_2+" Brining plan");
				  }else
				  {
					  logger.log(LogStatus.PASS, "Brining Plan for the Marinades Products in Brining Plan is ="+0);
					  System.out.println(0+" Brining plan");  
			  }
			  }else
			  {
				 x4= x4_4;
				 b_pt_2=dplan-x4;
				 if(b_pt_2>0)
				  {
					 logger.log(LogStatus.PASS, "Brining Plan for the Marinades Products in Brining Plan is ="+b_pt_2);
				  System.out.println(b_pt_2+" Brining plan");
				  }else
				  {
					  logger.log(LogStatus.PASS, "Brining Plan for the Marinades Products in Brining Plan is ="+0);
					  System.out.println(0+" Brining plan");  
			  }
			  }
		 }
		 extent.endTest(logger);
	 // System.out.println(b_pt_2+" Brining plan");
	 
//			X1 = [C.S(T-3) - D.P(T-2)]+Dispatch Plan (T-2)
//			X2 = Min[X1,{H.I(T-3) +H.I(T-4) + Dispatch(T-2)}]
//			X3 = X2-D.P(T-1)
//			X4 = Min[X3,{H.I(T-3) + Dispatch(T-2)}]
//			B.P(T-2) = D.P(T) -X4  
	 
		
		
		
		 
			}
	@AfterTest
				public void aftermethod()
				{
				  
					extent.flush();
					extent.close();
				}
}
