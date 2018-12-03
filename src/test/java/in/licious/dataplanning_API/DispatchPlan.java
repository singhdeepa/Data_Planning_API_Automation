package in.licious.dataplanning_API;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.simple.JSONObject;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.relevantcodes.extentreports.LogStatus;

import in.licious.util.ReadData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class DispatchPlan {

	ReadData rd=new ReadData();
	String excelFilePath="/Users/deepa/eclipse-workspace/Data_Planning_API_Automation/ExcelData/DataPlanning.xlsx";
	double productionPlan_Cluster_B_C2=0;
    double productionPlan_Cluster_B_C1=0;
    double productionPlan_Cluster_B_C3=0;
    double productionPlan_Cluster_B_C4=0;
    double productionPlan_Cluster1=0;
    double productionPlan_Cluster2=0;
    double  disp_Plan_Hub=0;
    double  disp_Plan_HubH=0;
    double  disp_Plan_HubH2=0;
    double  disp_Plan_HubH1=0;
   
    double  disp_Plan_B_C2=0;
    double  disp_Plan_B_C1=0;
    double  disp_Plan_B_C3=0;
    double  disp_Plan_B_C4=0;
    double  disp_Plan_Hub1=0;
    double  disp_Plan_Hub4=0;
    double  disp_Plan_Hub10=0;
    double  disp_Plan_Hub3=0;
    double  disp_Plan_Hub5=0;
    double  disp_Plan_Hub15=0;
    double  disp_Plan_Hub2=0;
    double  disp_Plan_Hub7=0;
    double  disp_Plan_Hub12=0;
    double  disp_Plan_Hub6=0;
    double  disp_Plan_Hub8=0;
    double  disp_Plan_Hub46=0;
    double  disp_Plan_Hub16=0;
    double  disp_Plan_Hub17=0;
    double  disp_Plan_Hub49=0;
    double  disp_Plan_Hub50=0;
    double  disp_Plan_Hub18=0;
    double  disp_Plan_Hub24=0;
    double  disp_Plan_Hub25=0;
    double ScheduleOrder=0;
    double  ScheduleOrder_hubB= 0;
    double  ScheduleOrder_hub1B= 0;
    double  ScheduleOrder_hub2B= 0;
    double ScheduleOrder_Bangalore=0;
    String rootobjScheduleOrder_hubH="";
    double  ScheduleOrder_hubHyd = 0;
    double dispatchQtyBang=0;
    double dispatchQtyHyd=0;
    double totalProductionPlan=0;
    double totalProductionBang=0;
    double totalProductionHyd=0;
    double dispatchPlanBng=0;
    double dispatchPlanHyd=0;
    double totalDispatchPlan=0;
    String array[]= {};
    String sURLScheduleOrder_hubH ="";
    int hubIDH=0;
    double dplan_hub_h=  0;
    double dplan_hub_17=  0;
    double dplan_hub_49=  0;
    double dplan_hub_50=  0;
    double dplan_hub_16=  0;
    double dplan_hub_18=  0;
    double dplan_hub_24=  0;
    double dplan_hub_25=  0;
    String baseprodURLl="http://52.66.9.219:9200/_sql?sql=";
	@Test(dataProvider="getData")
//	public void createDispatchPlanBangalore(String hubid, String hubid1,String hubid2,String cluster_ID) throws Throwable
//	{
    public void createDispatchPlanBangalore(String hubid, String hubid1,String hubid2,String cluster_ID) throws Throwable
	{			
		
       // String baseprodURLl="https://plan-es1.licious.app/_sql?sql=";
		RequestSpecification request = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("ck", "CK_001"); 
		requestParams.put("date", rd.readDataFromExcel(excelFilePath, "Dataplanning", 8, 30));  
		//requestParams.put("city", "1"); 
		// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");
		// Add a header stating the Request body is a JSON
		request.header("token", "e6e061838856bf47e1de730719fb2609");
		
		request.body(requestParams.toJSONString());
		
		Response response = request.post("https://planning-api.licious.in/production/dispatchplan");

		int statusCode = response.getStatusCode();
		AssertJUnit.assertEquals(statusCode, 200);
		System.out.println(statusCode);
		//logger.log(LogStatus.PASS, "Brining Plan Response  is verified successfully");
		String data = response.getContentType();
		ResponseBody data1 = response.getBody();
		
		System.out.println("********************************************");
		
		String dateString=rd.readDataFromExcel(excelFilePath, "Dataplanning", 8, 30);
		
		String dateDPTsam="'"+dateString+"'";
		
		String dateDPT="'"+dateString+"'"+"and%20version%20=";
		//System.out.println(dateDPT);
		
		String ssDPmaxV=rd.readDataFromExcel(excelFilePath, "Dataplanning", 8, 31);
		//"select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_5785b9065d7e1' and date=";
        String sURLdpVersion =baseprodURLl+URLEncoder.encode(ssDPmaxV)+dateDPTsam;
       // System.out.println(sURLdpVersion);
        URL urldpMaxVer = new URL(sURLdpVersion);
 
        URLConnection requestdpmaxVar = urldpMaxVer.openConnection();
        requestdpmaxVar.connect();
 
       JsonParser jpdpmaxVersion = new JsonParser(); //from gson
       JsonElement rootdpmaxVer = jpdpmaxVersion.parse(new InputStreamReader((InputStream) requestdpmaxVar.getContent())); //Convert the input stream to a json element
       String rootobjdpmaxVersion = rootdpmaxVer.getAsJsonObject().get("aggregations").getAsJsonObject().get("MAX(version)").getAsJsonObject().get("value").getAsString();
      // System.out.println(rootobjdpmaxVersion);
       double dpmaxVersiond=  Double.parseDouble(rootobjdpmaxVersion);
       int dpmaxVersion=(int)dpmaxVersiond;
       String version="'"+dpmaxVersion+"'";
       //etest.log(LogStatus.PASS,"Maximum version of Demand Plan is "+version);
     //  System.out.println(version);

       String ssDP=rd.readDataFromExcel(excelFilePath, "Dataplanning", 8, 14);
       //String ssDPmaxV="select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date=";
       String sURLdp =baseprodURLl+URLEncoder.encode(ssDP)+"'"+hubid+"'"+"and%20date="+dateDPT+version;
       String sURLdp1 =baseprodURLl+URLEncoder.encode(ssDP)+"'"+hubid1+"'"+"and%20date="+dateDPT+version;
       String sURLdp2 =baseprodURLl+URLEncoder.encode(ssDP)+"'"+hubid2+"'"+"and%20date="+dateDPT+version;
       // String sURLdp =baseprodURLl+URLEncoder.encode(ssDP)+dateDPT+"and version="+version; 
       // String sURLdp =baseprodURLl+URLEncoder.encode(ssDP);
     //  System.out.println(sURLdp);
      // System.out.println(sURLdp1);
       
       URL urldp = new URL(sURLdp);
	    URLConnection requestdp = urldp.openConnection();
	    requestdp.connect();

	    // Convert to a JSON object to print data
	    JsonParser jpdp = new JsonParser(); //from gson
	    JsonElement rootdp = jpdp.parse(new InputStreamReader((InputStream) requestdp.getContent())); //Convert the input stream to a json element
	    String rootobjdp = rootdp.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
	    
	    URL urldp1 = new URL(sURLdp1);
	    URLConnection requestdp1 = urldp1.openConnection();
	    requestdp1.connect();

	    // Convert to a JSON object to print data
	    JsonParser jpdp1 = new JsonParser(); //from gson
	    JsonElement rootdp1 = jpdp1.parse(new InputStreamReader((InputStream) requestdp1.getContent())); //Convert the input stream to a json element
	    String rootobjdp1 = rootdp1.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
	    
	    URL urldp2 = new URL(sURLdp2);
	    URLConnection requestdp2 = urldp2.openConnection();
	    requestdp2.connect();

	    // Convert to a JSON object to print data
	    JsonParser jpdp2 = new JsonParser(); //from gson
	    JsonElement rootdp2 = jpdp2.parse(new InputStreamReader((InputStream) requestdp2.getContent())); //Convert the input stream to a json element
	    String rootobjdp2 = rootdp2.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
	   
//	    //May be an array, may be an object. 
//	    System.out.println(rootobjdp+ "demand Plan of hub ID is "+hubid);
//	    System.out.println(rootobjdp1+"demand Plan of hub ID is "+hubid1);
//	    System.out.println(rootobjdp2+"demand Plan of hub ID is "+hubid2);
	   
	    String ssDP_Cluster=rd.readDataFromExcel(excelFilePath, "Dataplanning", 8, 38);
	       //String ssDPmaxV="select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date=";
	       String sURLdp_Cluster =baseprodURLl+URLEncoder.encode(ssDP_Cluster)+"'"+cluster_ID+"'"+"and%20date="+dateDPT+version;
	       // String sURLdp =baseprodURLl+URLEncoder.encode(ssDP)+dateDPT+"and version="+version; 
	       // String sURLdp =baseprodURLl+URLEncoder.encode(ssDP);
	      // System.out.println(sURLdp_Cluster);
	       
	       URL urldp_Cluster = new URL(sURLdp_Cluster);
		    URLConnection requestdp_Cluster = urldp_Cluster.openConnection();
		    requestdp_Cluster.connect();

		    // Convert to a JSON object to print data
		    JsonParser jpdp_Cluster = new JsonParser(); //from gson
		    JsonElement rootdp_Cluster = jpdp_Cluster.parse(new InputStreamReader((InputStream) requestdp_Cluster.getContent())); //Convert the input stream to a json element
		    String rootobjdp_Cluster = rootdp_Cluster.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
		    
		    //May be an array, may be an object. 
		    //System.out.println(rootobjdp_Cluster+"demand Plan Cluster Level "+cluster_ID);
		    
		    String ssPP_Cluster=rd.readDataFromExcel(excelFilePath, "Dataplanning", 8, 39);
		       //String ssDPmaxV="select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date=";
		       String sURLPP_Cluster =baseprodURLl+URLEncoder.encode(ssPP_Cluster)+"'"+cluster_ID+"'"+"and%20production_date="+dateDPTsam;
		       
		       
		       URL urlPP_Cluster = new URL(sURLPP_Cluster);
			    URLConnection requestPP_Cluster = urlPP_Cluster.openConnection();
			    requestPP_Cluster.connect();

			    // Convert to a JSON object to print data
			    JsonParser jpPP_Cluster = new JsonParser(); //from gson
			    JsonElement rootPP_Cluster = jpPP_Cluster.parse(new InputStreamReader((InputStream) requestPP_Cluster.getContent())); //Convert the input stream to a json element
			    String rootobjPP_Cluster = rootPP_Cluster.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(quantity_to_produce)").getAsJsonObject().get("value").getAsString();
			    
			    //May be an array, may be an object. 
			   // System.out.println(rootobjPP_Cluster+"Production Plan Cluster Level "+cluster_ID);
	           // String cluster=cluster_ID;
			    int hubID=  Integer.parseInt(hubid);
			    int hubID1=  Integer.parseInt(hubid1);
			    int hubID2=  Integer.parseInt(hubid2);
			    double dplan_hub=  Double.parseDouble(rootobjdp);
			  //  double dplan_ClusterValue=  Double.parseDouble(clusterValue);
			    double dplan_hub1=  Double.parseDouble(rootobjdp1);
			    double dplan_hub2=  Double.parseDouble(rootobjdp2);
			    
			   // System.out.println(dplan_hub+" "+dplan_hub1+" "+dplan_hub2);
			    double dPlan_Cluster=Double.parseDouble(rootobjdp_Cluster);
			    double productionPlan_Cluster=Double.parseDouble(rootobjPP_Cluster);
			    
			    if(hubID==1&&hubID1==4&&hubID2==10&&cluster_ID.equals("B_C2"))
			    {
			    	if(hubID==1)
			    	{
			    		  disp_Plan_Hub1=(dplan_hub/dPlan_Cluster)*productionPlan_Cluster; 
						    System.out.println(disp_Plan_Hub1+ " dispatch Plan for B_C2 "+dplan_hub+"HUB ID= "+hubID+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
			    	}else
			    	{
			    		System.out.println("Fail");
			    	}
			    		if(hubID1==4)
			    	{
			    		  disp_Plan_Hub4=(dplan_hub1/dPlan_Cluster)*productionPlan_Cluster; 
						    System.out.println(disp_Plan_Hub4+ " dispatch Plan for B_C2 "+dplan_hub1+"HUB ID=  "+hubID1+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
			    	}else 
			    	{
			    		System.out.println("Fail");
			    	}
			    		if(hubID2==10)
			    	{
			    		  disp_Plan_Hub10=(dplan_hub2/dPlan_Cluster)*productionPlan_Cluster; 
						    System.out.println(disp_Plan_Hub10+ " dispatch Plan for B_C2 "+dplan_hub2+"HUB ID=  "+hubID2+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
			    	}else
			    	{
			    		System.out.println("Fail");
			    	}
			    		disp_Plan_B_C2=disp_Plan_Hub1+disp_Plan_Hub4+disp_Plan_Hub10;
			    		System.out.println("B_C2 DISP"+disp_Plan_B_C2);
			    		 productionPlan_Cluster_B_C2=productionPlan_Cluster;
			    		 System.out.println(productionPlan_Cluster_B_C2+"B_C2 productionPlan_Cluster ");
			    	
			    }
			 
			    
			    if (hubID==2&&hubID1==7&&hubID2==12&&cluster_ID.equals("B_C1"))
			    {
			    	
			    	if(hubID==2)
			    	{
			    		  disp_Plan_Hub2=(dplan_hub/dPlan_Cluster)*productionPlan_Cluster; 
						    System.out.println(disp_Plan_Hub2+ " dispatch Plan for B_C1 "+dplan_hub+"HUB ID=  "+hubID+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
			    	}else
			    	{
			    		System.out.println("Fail");
			    	}
			    	if(hubID1==7)
			    	{
			    		  disp_Plan_Hub7=(dplan_hub1/dPlan_Cluster)*productionPlan_Cluster; 
						    System.out.println(disp_Plan_Hub7+ " dispatch Plan for B_C1 "+dplan_hub1+" HUB ID= "+hubID1+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
			    	}else 
			    	{
			    		System.out.println("Fail");
			    	}
			    		if(hubID2==12)
			    	{
			    		  disp_Plan_Hub12=(dplan_hub2/dPlan_Cluster)*productionPlan_Cluster; 
						    System.out.println(disp_Plan_Hub12+ " dispatch Plan for B_C1 "+dplan_hub2+" HUB ID= "+hubID2+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
			    	}else
			    	{
			    		System.out.println("Fail");
			    	}
			    		disp_Plan_B_C1=disp_Plan_Hub2+disp_Plan_Hub7+disp_Plan_Hub12;
			    		System.out.println("B_C1 DISP"+disp_Plan_B_C1);
			    		  productionPlan_Cluster_B_C1=productionPlan_Cluster;
			    		  System.out.println(productionPlan_Cluster_B_C1+"productionPlan_Cluster B_C1");
				    		//System.out.println(productionPlan_Cluster_B_C1+"productionPlan_Cluster_B_C1");
						    
			    }
			  
			    	if(hubID==3&&hubID1==5&&hubID2==15&&cluster_ID.equals("B_C4"))
			    {
			    	if(hubID==3)
			    	{
			    		  disp_Plan_Hub3=(dplan_hub/dPlan_Cluster)*productionPlan_Cluster; 
						    System.out.println(disp_Plan_Hub3+ " dispatch Plan for B_C4 "+dplan_hub+" HUB ID= "+hubID+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
			    	}else
			    	{
			    		System.out.println("Fail");
			    	}
			    		
			    		if(hubID1==5)
			    	{
			    		  disp_Plan_Hub5=(dplan_hub1/dPlan_Cluster)*productionPlan_Cluster; 
						    System.out.println(disp_Plan_Hub5+ " dispatch Plan for B_C4 "+dplan_hub1+" HUB ID= "+hubID1+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
			    	}else
			    	{
			    		System.out.println("Fail");
			    	}
			    		if(hubID2==15)
			    	{
			    		  disp_Plan_Hub15=(dplan_hub2/dPlan_Cluster)*productionPlan_Cluster; 
						    System.out.println(disp_Plan_Hub15+ " dispatch Plan for B_C4 "+dplan_hub2+" HUB ID= "+hubID2+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
			    	}
			    		else
			    		{
			    			System.out.println("Fail");
			    		}
			    		disp_Plan_B_C4=disp_Plan_Hub3+disp_Plan_Hub5+disp_Plan_Hub15;
			    		System.out.println("B_C4 DISP"+disp_Plan_B_C4);
			    		 productionPlan_Cluster_B_C4=productionPlan_Cluster;
			    		 System.out.println(productionPlan_Cluster_B_C4+"productionPlan_Cluster B_C4");
			    		
			    } 
			    	//System.out.println(productionPlan_Cluster_B_C1+productionPlan_Cluster_B_C2+productionPlan_Cluster_B_C3+productionPlan_Cluster_B_C4);   
			    	
			    	if(hubID==6&&hubID1==8&&hubID2==46&&cluster_ID.equals("B_C3"))
			    {
			    	
			    	if(hubID==6)
			    	{
			    		  disp_Plan_Hub6=(dplan_hub/dPlan_Cluster)*productionPlan_Cluster; 
						    System.out.println(disp_Plan_Hub6+ " dispatch Plan for B_C3 "+dplan_hub+" HUB ID= "+hubID+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
			    	}else
			    	{
			    		System.out.println("Fail");
			    	}
			    		if(hubID1==8)
			    	{
			    		  disp_Plan_Hub8=(dplan_hub1/dPlan_Cluster)*productionPlan_Cluster; 
						    System.out.println(disp_Plan_Hub8+ " dispatch Plan for B_C3 "+dplan_hub1+" HUB ID= "+hubID1+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);
			    	}
						    else
						    {
						    	System.out.println("Fail");
						    }
			    	if(hubID2==46)
			    	{
			    		  disp_Plan_Hub46=(dplan_hub2/dPlan_Cluster)*productionPlan_Cluster; 
						    System.out.println(disp_Plan_Hub46+ " dispatch Plan for B_C3 "+dplan_hub2+"HUB ID=  "+hubID2+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
			    	}else
			    	{
			    		System.out.println("Fail");
			    	}
			    	disp_Plan_B_C3=disp_Plan_Hub6+disp_Plan_Hub8+disp_Plan_Hub46;
			    	System.out.println("B_C3 DISP"+disp_Plan_B_C3);
			    	 productionPlan_Cluster_B_C3=productionPlan_Cluster;
		    		System.out.println(productionPlan_Cluster_B_C3+"productionPlan_Cluster_B_C3");
			    	
			    }
			    	//dispatchPlanBng=disp_Plan_B_C3+disp_Plan_B_C2+disp_Plan_B_C1+disp_Plan_B_C4;
			    	//System.out.println(dispatchPlanBng+"banglore dispatch plan");
			    	
//			    System.out.println("******************************************************");
//			    if(hubID==1&&hubID1==4&&hubID2==10&&cluster_ID.equals("B_C2"))
//			    {
//			    	if(hubID==1)
//			    	{
//			    		  disp_Plan_Hub=(dplan_hub/dPlan_Cluster)*productionPlan_Cluster; 
//						    System.out.println(disp_Plan_Hub+ " dispatch Plan for B_C2 "+dplan_hub+"HUB ID= "+hubID+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
//			    	}else
//			    	{
//			    		System.out.println("Fail");
//			    	}
//			    		if(hubID1==4)
//			    	{
//			    		  disp_Plan_Hub=(dplan_hub1/dPlan_Cluster)*productionPlan_Cluster; 
//						    System.out.println(disp_Plan_Hub+ " dispatch Plan for B_C2 "+dplan_hub1+"HUB ID=  "+hubID1+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
//			    	}else 
//			    	{
//			    		System.out.println("Fail");
//			    	}
//			    		if(hubID2==10)
//			    	{
//			    		  disp_Plan_Hub=(dplan_hub2/dPlan_Cluster)*productionPlan_Cluster; 
//						    System.out.println(disp_Plan_Hub+ " dispatch Plan for B_C2 "+dplan_hub2+"HUB ID=  "+hubID2+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
//			    	}else
//			    	{
//			    		System.out.println("Fail");
//			    	}
//			    		disp_Plan_Hub+=disp_Plan_Hub;
//			    		 productionPlan_Cluster_B_C2=productionPlan_Cluster;
//			    		 
//			    	//	System.out.println(productionPlan_Cluster_B_C2+"productionPlan_Cluster_B_C2");	
//			    }
//			    disp_Plan_Hub+=disp_Plan_Hub;
//			  //  System.out.println(productionPlan_Cluster_B_C1+productionPlan_Cluster_B_C2+productionPlan_Cluster_B_C3+productionPlan_Cluster_B_C4);   
//			    
//			    if (hubID==2&&hubID1==7&&hubID2==12&&cluster_ID.equals("B_C1"))
//			    {
//			    	
//			    	if(hubID==2)
//			    	{
//			    		  disp_Plan_Hub=(dplan_hub1/dPlan_Cluster)*productionPlan_Cluster; 
//						    System.out.println(disp_Plan_Hub+ " dispatch Plan for B_C1 "+dplan_hub+"HUB ID=  "+hubID+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
//			    	}else
//			    	{
//			    		System.out.println("Fail");
//			    	}
//			    	if(hubID1==7)
//			    	{
//			    		  disp_Plan_Hub=(dplan_hub1/dPlan_Cluster)*productionPlan_Cluster; 
//						    System.out.println(disp_Plan_Hub+ " dispatch Plan for B_C1 "+dplan_hub1+" HUB ID= "+hubID1+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
//			    	}else 
//			    	{
//			    		System.out.println("Fail");
//			    	}
//			    		if(hubID2==12)
//			    	{
//			    		  disp_Plan_Hub=(dplan_hub2/dPlan_Cluster)*productionPlan_Cluster; 
//						    System.out.println(disp_Plan_Hub+ " dispatch Plan for B_C1 "+dplan_hub2+" HUB ID= "+hubID2+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
//			    	}else
//			    	{
//			    		System.out.println("Fail");
//			    	}
//			    		  productionPlan_Cluster_B_C1=productionPlan_Cluster;
//				    		//System.out.println(productionPlan_Cluster_B_C1+"productionPlan_Cluster_B_C1");
//			    		  disp_Plan_Hub+=disp_Plan_Hub;	    
//			    }
//			   // System.out.println(productionPlan_Cluster_B_C1+productionPlan_Cluster_B_C2+productionPlan_Cluster_B_C3+productionPlan_Cluster_B_C4);   
//			    	if(hubID==3&&hubID1==5&&hubID2==15&&cluster_ID.equals("B_C4"))
//			    {
//			    	if(hubID==3)
//			    	{
//			    		  disp_Plan_Hub=(dplan_hub/dPlan_Cluster)*productionPlan_Cluster; 
//						    System.out.println(disp_Plan_Hub+ " dispatch Plan for B_C4 "+dplan_hub+" HUB ID= "+hubID+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
//			    	}else
//			    	{
//			    		System.out.println("Fail");
//			    	}
//			    		
//			    		if(hubID1==5)
//			    	{
//			    		  disp_Plan_Hub=(dplan_hub1/dPlan_Cluster)*productionPlan_Cluster; 
//						    System.out.println(disp_Plan_Hub+ " dispatch Plan for B_C4 "+dplan_hub1+" HUB ID= "+hubID1+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
//			    	}else
//			    	{
//			    		System.out.println("Fail");
//			    	}
//			    		if(hubID2==15)
//			    	{
//			    		  disp_Plan_Hub=(dplan_hub2/dPlan_Cluster)*productionPlan_Cluster; 
//						    System.out.println(disp_Plan_Hub+ " dispatch Plan for B_C4 "+dplan_hub2+" HUB ID= "+hubID2+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
//			    	}
//			    		else
//			    		{
//			    			System.out.println("Fail");
//			    		}
//			    		 productionPlan_Cluster_B_C4=productionPlan_Cluster;
//			    		 disp_Plan_Hub+=disp_Plan_Hub;
//			    		//System.out.println(productionPlan_Cluster_B_C4+"productionPlan_Cluster_B_C4");
//			    } 
//			    	//System.out.println(productionPlan_Cluster_B_C1+productionPlan_Cluster_B_C2+productionPlan_Cluster_B_C3+productionPlan_Cluster_B_C4);   
//			    	
//			    	if(hubID==6&&hubID1==8&&hubID2==46&&cluster_ID.equals("B_C3"))
//			    {
//			    	//double disp_Plan_Hub=(dplan_hub/dPlan_Cluster)*productionPlan_Cluster;
//			    	 //System.out.println(disp_Plan_Hub+ " dispatch Plan for B_C3 "+cluster_ID);
//			    	if(hubID==6)
//			    	{
//			    		  disp_Plan_Hub=(dplan_hub/dPlan_Cluster)*productionPlan_Cluster; 
//						    System.out.println(disp_Plan_Hub+ " dispatch Plan for B_C3 "+dplan_hub+" HUB ID= "+hubID+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
//			    	}else
//			    	{
//			    		System.out.println("Fail");
//			    	}
//			    		if(hubID1==8)
//			    	{
//			    		  disp_Plan_Hub=(dplan_hub1/dPlan_Cluster)*productionPlan_Cluster; 
//						    System.out.println(disp_Plan_Hub+ " dispatch Plan for B_C3 "+dplan_hub1+" HUB ID= "+hubID1+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);
//			    	}
//						    else
//						    {
//						    	System.out.println("Fail");
//						    }
//			    	if(hubID2==46)
//			    	{
//			    		  disp_Plan_Hub=(dplan_hub2/dPlan_Cluster)*productionPlan_Cluster; 
//						    System.out.println(disp_Plan_Hub+ " dispatch Plan for B_C3 "+dplan_hub2+"HUB ID=  "+hubID2+" "+dPlan_Cluster+" "+"Production cluster="+productionPlan_Cluster);	
//			    	}else
//			    	{
//			    		System.out.println("Fail");
//			    	}
//			    	 productionPlan_Cluster_B_C3=productionPlan_Cluster;
//		    		//System.out.println(productionPlan_Cluster_B_C3+"productionPlan_Cluster_B_C3");
//			    	 disp_Plan_Hub+=disp_Plan_Hub;
//			    }
//			    	//disp_Plan_Hub+=disp_Plan_Hub;
//			    	System.out.println(disp_Plan_Hub+"disp bang");
			    System.out.println("***********************************************************");
			    
			    	// System.out.println(productionPlan_Cluster_B_C1+productionPlan_Cluster_B_C2+productionPlan_Cluster_B_C3+productionPlan_Cluster_B_C4);
			    //totalProductionBang=productionPlan_Cluster_B_C1+productionPlan_Cluster_B_C2+productionPlan_Cluster_B_C3+productionPlan_Cluster_B_C4;
			    	//System.out.println(totalProductionBang);   
			    
			    	//System.out.println("Bangalore Cluster value="+productionPlan_Cluster_B_C3+productionPlan_Cluster_B_C2+productionPlan_Cluster_B_C1+productionPlan_Cluster_B_C4);
			   // System.out.println("*********************************");
			 
			    
			 System.out.println("hydrabad");   
			    
			    
			    String ssDPHmaxV=rd.readDataFromExcel(excelFilePath, "Dataplanning", 9, 31);
				//"select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_5785b9065d7e1' and date=";
		        String sURLdpHVersion =baseprodURLl+URLEncoder.encode(ssDPHmaxV)+dateDPTsam;
		       // System.out.println(sURLdpHVersion);
		        URL urldpHMaxVer = new URL(sURLdpHVersion);
		 
		        URLConnection requestdpHmaxVar = urldpHMaxVer.openConnection();
		        requestdpHmaxVar.connect();
		 
		       JsonParser jpdpHmaxVersion = new JsonParser(); //from gson
		       JsonElement rootdpHmaxVer = jpdpHmaxVersion.parse(new InputStreamReader((InputStream) requestdpHmaxVar.getContent())); //Convert the input stream to a json element
		       String rootobjdpHmaxVersion = rootdpHmaxVer.getAsJsonObject().get("aggregations").getAsJsonObject().get("MAX(version)").getAsJsonObject().get("value").getAsString();
		      // System.out.println(rootobjdpHmaxVersion);
		       double dpHmaxVersiond=  Double.parseDouble(rootobjdpHmaxVersion);
		       int dpHmaxVersion=(int)dpHmaxVersiond;
		       String versionH="'"+dpHmaxVersion+"'";
		       //etest.log(LogStatus.PASS,"Maximum version of Demand Plan is "+version);
		     //  System.out.println(version);

		      
		       String sURLdpH =" ";
		       String ssDPH=rd.readDataFromExcel(excelFilePath, "Dataplanning", 9, 14);
		       String array[]= {"17","49","50","16","18","24","25"};
		       String cluster1="H_C1";
		        String cluster2="H_C2";
		        
				  
			    double dPlan_Cluster1=0;
			    double dPlan_Cluster2=0;
			    
        
		       for (int i = 0; i < array.length; i++)
		       {
					//System.out.println(array[i]);
					hubIDH=  Integer.parseInt(array[i]);
					   sURLdpH =baseprodURLl+URLEncoder.encode(ssDPH)+"'"+hubIDH+"'"+"and%20date="+dateDPT+versionH;
					 //  System.out.println(sURLdpH);
					   URL urldpH = new URL(sURLdpH);
					    URLConnection requestdpH = urldpH.openConnection();
					    requestdpH.connect();

					    // Convert to a JSON object to print data
					    JsonParser jpdpH = new JsonParser(); //from gson
					    JsonElement rootdpH = jpdpH.parse(new InputStreamReader((InputStream) requestdpH.getContent())); //Convert the input stream to a json element
					    String rootobjdpH = rootdpH.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
					    
					    
					    //May be an array, may be an object. 
					    //System.out.println(rootobjdpH+ "demand Plan of hub ID is "+hubIDH);
					   
			    
			    String ssDP_ClusterH=rd.readDataFromExcel(excelFilePath, "Dataplanning", 9, 38);
			       //String ssDPmaxV="select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date=";
			       String sURLdp_Cluster1 =baseprodURLl+URLEncoder.encode(ssDP_ClusterH)+"'"+cluster1+"'"+"and%20date="+dateDPT+versionH;
			       String sURLdp_Cluster2 =baseprodURLl+URLEncoder.encode(ssDP_ClusterH)+"'"+cluster2+"'"+"and%20date="+dateDPT+versionH;
			       
			      // System.out.println(sURLdp_Cluster1);
			      // System.out.println(sURLdp_Cluster2);
			       URL urldp_Cluster1 = new URL(sURLdp_Cluster1);
				    URLConnection requestdp_Cluster1 = urldp_Cluster1.openConnection();
				    requestdp_Cluster1.connect();

				    // Convert to a JSON object to print data
				    JsonParser jpdp_Cluster1 = new JsonParser(); //from gson
				    JsonElement rootdp_Cluster1 = jpdp_Cluster1.parse(new InputStreamReader((InputStream) requestdp_Cluster1.getContent())); //Convert the input stream to a json element
				    String rootobjdp_Cluster1 = rootdp_Cluster1.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
				 //   System.out.println(rootobjdp_Cluster1);
				    //May be an array, may be an object. 
				    //System.out.println(rootobjdp_Cluster+"demand Plan Cluster Level "+cluster_ID);
				    
				    URL urldp_Cluster2 = new URL(sURLdp_Cluster2);
				    URLConnection requestdp_Cluster2 = urldp_Cluster2.openConnection();
				    requestdp_Cluster2.connect();

				    // Convert to a JSON object to print data
				    JsonParser jpdp_Cluster2 = new JsonParser(); //from gson
				    JsonElement rootdp_Cluster2 = jpdp_Cluster2.parse(new InputStreamReader((InputStream) requestdp_Cluster2.getContent())); //Convert the input stream to a json element
				    String rootobjdp_Cluster2 = rootdp_Cluster2.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
				   // System.out.println(rootobjdp_Cluster2);
				    
				    
				    
				    String ssPP_ClusterH=rd.readDataFromExcel(excelFilePath, "Dataplanning", 9, 39);
				       //String ssDPmaxV="select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date=";
				       String sURLPP_Cluster1 =baseprodURLl+URLEncoder.encode(ssPP_ClusterH)+"'"+cluster1+"'"+"and%20production_date="+dateDPTsam;
				       String sURLPP_Cluster2 =baseprodURLl+URLEncoder.encode(ssPP_ClusterH)+"'"+cluster2+"'"+"and%20production_date="+dateDPTsam;
				       // String sURLdp =baseprodURLl+URLEncoder.encode(ssDP)+dateDPT+"and version="+version; 
				       // String sURLdp =baseprodURLl+URLEncoder.encode(ssDP);
				      // System.out.println(sURLPP_Cluster);
				       
				       URL urlPP_Cluster1 = new URL(sURLPP_Cluster1);
					    URLConnection requestPP_Cluster1 = urlPP_Cluster1.openConnection();
					    requestPP_Cluster1.connect();

					    // Convert to a JSON object to print data
					    JsonParser jpPP_Cluster1 = new JsonParser(); //from gson
					    JsonElement rootPP_Cluster1 = jpPP_Cluster1.parse(new InputStreamReader((InputStream) requestPP_Cluster1.getContent())); //Convert the input stream to a json element
					    String rootobjPP_Cluster1 = rootPP_Cluster1.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(quantity_to_produce)").getAsJsonObject().get("value").getAsString();
					    //System.out.println(rootobjPP_Cluster1);
					    
					    URL urlPP_Cluster2 = new URL(sURLPP_Cluster2);
					    URLConnection requestPP_Cluster2 = urlPP_Cluster2.openConnection();
					    requestPP_Cluster2.connect();

					    // Convert to a JSON object to print data
					    JsonParser jpPP_Cluster2 = new JsonParser(); //from gson
					    JsonElement rootPP_Cluster2 = jpPP_Cluster2.parse(new InputStreamReader((InputStream) requestPP_Cluster2.getContent())); //Convert the input stream to a json element
					    String rootobjPP_Cluster2 = rootPP_Cluster2.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(quantity_to_produce)").getAsJsonObject().get("value").getAsString();
					    
					   // System.out.println(rootobjPP_Cluster2);
					    //May be an array, may be an object. 
					   // System.out.println(rootobjPP_Cluster+"Production Plan Cluster Level "+cluster_ID);
			            
					    dplan_hub_h=  Double.parseDouble(rootobjdpH);
					  
					     dPlan_Cluster1=Double.parseDouble(rootobjdp_Cluster1);
					     dPlan_Cluster2=Double.parseDouble(rootobjdp_Cluster2);
					     productionPlan_Cluster1=Double.parseDouble(rootobjPP_Cluster1);
					     productionPlan_Cluster2=Double.parseDouble(rootobjPP_Cluster2);
					     
					     if(hubIDH==17||hubIDH==49||hubIDH==50||hubIDH==16||cluster1.equals("H_C1"))
						    {
						    	if(hubIDH==17)
						    	{
						    		disp_Plan_Hub17=(dplan_hub_h/dPlan_Cluster1)*productionPlan_Cluster1; 
									    System.out.println(disp_Plan_Hub17+ " dispatch Plan for H_C1 "+dplan_hub_h+"HUB ID=  "+hubIDH+" "+dPlan_Cluster1+" "+"Production cluster"+productionPlan_Cluster1);	
						    	}
						    		if(hubIDH==49)
						    	{
						    			disp_Plan_Hub49=(dplan_hub_h/dPlan_Cluster1)*productionPlan_Cluster1; 
									    System.out.println(disp_Plan_Hub49+ " dispatch Plan for H_C1 "+dplan_hub_h+"HUB ID=  "+hubIDH+" "+dPlan_Cluster1+" "+"Production cluster"+productionPlan_Cluster1);	
						    	}
						    		if(hubIDH==50)
						    	{
						    			disp_Plan_Hub50=(dplan_hub_h/dPlan_Cluster1)*productionPlan_Cluster1; 
									    System.out.println(disp_Plan_Hub50+ " dispatch Plan for H_C1 "+dplan_hub_h+"HUB ID=  "+hubIDH+" "+dPlan_Cluster1+" "+"Production cluster"+productionPlan_Cluster1);	
						    	}
						    		if(hubIDH==16)
							    	{
						    			disp_Plan_Hub16=(dplan_hub_h/dPlan_Cluster1)*productionPlan_Cluster1; 
										    System.out.println(disp_Plan_Hub16+ " dispatch Plan for H_C1 "+dplan_hub_h+"HUB ID=  "+hubIDH+" "+dPlan_Cluster1+" "+"Production cluster"+productionPlan_Cluster1);	
							    	}
						    		 disp_Plan_HubH1=disp_Plan_Hub17+disp_Plan_Hub49+disp_Plan_Hub50+disp_Plan_Hub16; 
						    }
						    //double disp_Plan_Hub=(dplan_hub/dPlan_Cluster)*productionPlan_Cluster; 
						   // System.out.println(disp_Plan_Hub+ " dispatch Plan for B_C2 "+cluster_ID);
						
						    if (hubIDH==18||hubIDH==24||hubIDH==25||cluster2.equals("H_C2"))
						    {
						    	
						    	if(hubIDH==18)
						    	{
						    		disp_Plan_Hub18=(dplan_hub_h/dPlan_Cluster2)*productionPlan_Cluster2; 
									    System.out.println(disp_Plan_Hub18+ " dispatch Plan for H_C2 "+dplan_hub_h+" HUB ID= "+hubIDH+" "+dPlan_Cluster2+" "+"Production cluster"+productionPlan_Cluster2);	
						    	}
						    	if(hubIDH==24)
						    	{
						    		disp_Plan_Hub24=(dplan_hub_h/dPlan_Cluster2)*productionPlan_Cluster2; 
									    System.out.println(disp_Plan_Hub24+ " dispatch Plan for H_C2 "+dplan_hub_h+"HUB ID=  "+hubIDH+" "+dPlan_Cluster2+" "+"Production cluster"+productionPlan_Cluster2);	
						    	}
						    		if(hubIDH==25)
						    	{
						    			disp_Plan_Hub25=(dplan_hub_h/dPlan_Cluster2)*productionPlan_Cluster2; 
									    System.out.println(disp_Plan_Hub25+ " dispatch Plan for H_C2 "+dplan_hub_h+"HUB ID=  "+hubIDH+" "+dPlan_Cluster2+" "+"Production cluster"+productionPlan_Cluster2);	
						    	}	    	
						    		 disp_Plan_HubH2=disp_Plan_Hub18+disp_Plan_Hub24+disp_Plan_Hub25; 
			                }
						   // disp_Plan_HubH+=disp_Plan_HubH;
						   // System.out.println(disp_Plan_HubH+" Total Hydrabad disp Plan");
		       }  
		       System.out.println("hydrabad");        
		             
					     totalProductionPlan=productionPlan_Cluster_B_C1+productionPlan_Cluster_B_C2+productionPlan_Cluster_B_C3+productionPlan_Cluster_B_C4+productionPlan_Cluster1+productionPlan_Cluster2;
					    System.out.println(totalProductionPlan+"Total Production Plan");
					    disp_Plan_HubH=disp_Plan_HubH1+disp_Plan_HubH2;
					    System.out.println(disp_Plan_HubH+"dispatchPlanHyd");
					     dispatchPlanBng=disp_Plan_B_C4+disp_Plan_B_C1+disp_Plan_B_C2+disp_Plan_B_C3;
					    System.out.println(dispatchPlanBng+"dispatchPlanBng");
					     totalDispatchPlan=disp_Plan_HubH+dispatchPlanBng;
					    System.out.println(totalDispatchPlan+"Total Dispatch Plan");
					    
			    RequestSpecification request1 = RestAssured.given();
				
				JSONObject requestParams1 = new JSONObject();
				requestParams1.put("ck", "CK_001"); 
				requestParams1.put("actual", totalProductionPlan); 
				requestParams1.put("date", rd.readDataFromExcel(excelFilePath, "Dataplanning", 8, 30));  
				//requestParams.put("city", "1"); 
				// Add a header stating the Request body is a JSON
				request1.header("Content-Type", "application/json");
				// Add a header stating the Request body is a JSON
				request1.header("token", "e6e061838856bf47e1de730719fb2609");
				
				request1.body(requestParams1.toJSONString());
				
				Response response1 = request.post("https://planning-api.licious.in/production/dispatch/actual");

				int statusCode1 = response.getStatusCode();
				AssertJUnit.assertEquals(statusCode1, 200);
				System.out.println(statusCode1);
				//logger.log(LogStatus.PASS, "Brining Plan Response  is verified successfully");
			//	String data1 = response.getContentType();
				ResponseBody data2 = response.getBody();
			   
				String ssScheduleOrder_hub=rd.readDataFromExcel(excelFilePath, "Dataplanning", 8, 40);
			       //String ssDPmaxV="select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date=";
			       String sURLScheduleOrder_hub =baseprodURLl+URLEncoder.encode(ssScheduleOrder_hub)+"'"+hubid+"'"+"and%20sheduled="+dateDPTsam+"%20and%20created_at<>%20"+dateDPTsam;
			       String sURLScheduleOrder_hub1 =baseprodURLl+URLEncoder.encode(ssScheduleOrder_hub)+"'"+hubid1+"'"+"and%20sheduled="+dateDPTsam+"%20and%20created_at<>%20"+dateDPTsam;
			       String sURLScheduleOrder_hub2 =baseprodURLl+URLEncoder.encode(ssScheduleOrder_hub)+"'"+hubid2+"'"+"and%20sheduled="+dateDPTsam+"%20and%20created_at<>%20"+dateDPTsam;
			       String sURLScheduleOrder_hubH =baseprodURLl+URLEncoder.encode(ssScheduleOrder_hub)+"'"+hubIDH+"'"+"and%20sheduled="+dateDPTsam+"%20and%20created_at<>%20"+dateDPTsam;
			       // String sURLdp =baseprodURLl+URLEncoder.encode(ssDP)+dateDPT+"and version="+version; 
			       // String sURLdp =baseprodURLl+URLEncoder.encode(ssDP);
			      // System.out.println(sURLScheduleOrder_hub);
			       
			       URL urlScheduleOrder_hub = new URL(sURLScheduleOrder_hub);
				    URLConnection requestScheduleOrder_hub = urlScheduleOrder_hub.openConnection();
				    requestScheduleOrder_hub.connect();

				    // Convert to a JSON object to print data
				    JsonParser jpScheduleOrder_hub = new JsonParser(); //from gson
				    JsonElement rootScheduleOrder_hub = jpScheduleOrder_hub.parse(new InputStreamReader((InputStream) requestScheduleOrder_hub.getContent())); //Convert the input stream to a json element
				    String rootobjScheduleOrder_hub= rootScheduleOrder_hub.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(order_item_quantity)").getAsJsonObject().get("value").getAsString();
				    
				    //May be an array, may be an object. 
				    System.out.println(rootobjScheduleOrder_hub+"Schedule order Hub Level "+hubid);
		            System.out.println("************************************");
		            
				       URL urlScheduleOrder_hub1 = new URL(sURLScheduleOrder_hub1);
					    URLConnection requestScheduleOrder_hub1 = urlScheduleOrder_hub1.openConnection();
					    requestScheduleOrder_hub.connect();

					    // Convert to a JSON object to print data
					    JsonParser jpScheduleOrder_hub1 = new JsonParser(); //from gson
					    JsonElement rootScheduleOrder_hub1 = jpScheduleOrder_hub1.parse(new InputStreamReader((InputStream) requestScheduleOrder_hub1.getContent())); //Convert the input stream to a json element
					    String rootobjScheduleOrder_hub1= rootScheduleOrder_hub1.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(order_item_quantity)").getAsJsonObject().get("value").getAsString();
					    //System.out.println(rootobjScheduleOrder_hub+"Schedule order Hub Level "+hubid);
					    //May be an array, may be an object. 
					    System.out.println(rootobjScheduleOrder_hub1+"Schedule order Hub Level Bangalore "+hubid1);
					    System.out.println("************************************");    	
					    URL urlScheduleOrder_hub2 = new URL(sURLScheduleOrder_hub2);
					    URLConnection requestScheduleOrder_hub2 = urlScheduleOrder_hub2.openConnection();
					    requestScheduleOrder_hub2.connect();

					    // Convert to a JSON object to print data
					    JsonParser jpScheduleOrder_hub2 = new JsonParser(); //from gson
					    JsonElement rootScheduleOrder_hub2 = jpScheduleOrder_hub2.parse(new InputStreamReader((InputStream) requestScheduleOrder_hub2.getContent())); //Convert the input stream to a json element
					    String rootobjScheduleOrder_hub2= rootScheduleOrder_hub2.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(order_item_quantity)").getAsJsonObject().get("value").getAsString();
					    System.out.println(rootobjScheduleOrder_hub2+"Schedule order Hub Level "+hubid2);
					      ScheduleOrder_hubB=    Double.parseDouble(rootobjScheduleOrder_hub);
					      ScheduleOrder_hub1B=    Double.parseDouble(rootobjScheduleOrder_hub1);
					      ScheduleOrder_hub2B=    Double.parseDouble(rootobjScheduleOrder_hub2);
					   // System.out.println(ScheduleOrder_hubB+ScheduleOrder_hub1B+ScheduleOrder_hub2B);
					    ScheduleOrder_Bangalore=ScheduleOrder_hubB+ScheduleOrder_hub1B+ScheduleOrder_hub2B;
					   System.out.println(ScheduleOrder_Bangalore+"Bangalore schedule order");
					    String rootobjScheduleOrder_hubH="";
					    double  ScheduleOrder_hubHyd = 0;
					    for (int i = 0; i < array.length; i++)
					       {
					    	hubIDH=  Integer.parseInt(array[i]);
					    	   sURLScheduleOrder_hubH =baseprodURLl+URLEncoder.encode(ssScheduleOrder_hub)+"'"+hubIDH+"'"+"and%20sheduled="+dateDPTsam+"%20and%20created_at<>%20"+dateDPTsam;
					    	 URL urlScheduleOrder_hubH = new URL(sURLScheduleOrder_hubH);
							    URLConnection requestScheduleOrder_hubH = urlScheduleOrder_hubH.openConnection();
							    requestScheduleOrder_hubH.connect();

							    // Convert to a JSON object to print data
							    JsonParser jpScheduleOrder_hubH = new JsonParser(); //from gson
							    JsonElement rootScheduleOrder_hubH = jpScheduleOrder_hubH.parse(new InputStreamReader((InputStream) requestScheduleOrder_hubH.getContent())); //Convert the input stream to a json element
							     rootobjScheduleOrder_hubH= rootScheduleOrder_hubH.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(order_item_quantity)").getAsJsonObject().get("value").getAsString();
							     ScheduleOrder_hubHyd +=    Double.parseDouble(rootobjScheduleOrder_hubH);
							    //System.out.println(rootobjScheduleOrder_hub+"Schedule order Hub Level "+hubid);
							    //May be an array, may be an object. 
							   // System.out.println(rootobjScheduleOrder_hubH+"Schedule order Hub Level "+hubIDH);
							     System.out.println(rootobjScheduleOrder_hubH+"Schedule order Hub Level hydrabad "+hubIDH);    
					       }
					    //System.out.println(ScheduleOrder_hubHyd); 
					  ScheduleOrder+=ScheduleOrder_hubHyd+ScheduleOrder_Bangalore;
					    System.out.println(ScheduleOrder+"Total");
//				
//					    requestParams1.put("ck", "CK_001"); 
//						requestParams1.put("actual", totalProductionPlan-100); 
//						requestParams1.put("date", rd.readDataFromExcel(excelFilePath, "Dataplanning", 8, 30));  
//						//requestParams.put("city", "1"); 
//						// Add a header stating the Request body is a JSON
//						request1.header("Content-Type", "application/json");
//						// Add a header stating the Request body is a JSON
//						request1.header("token", "e6e061838856bf47e1de730719fb2609");
//						
//						request1.body(requestParams1.toJSONString());
//						
//						Response response2 = request.post("https://planning-api.licious.in/production/dispatch/actual");
//
//						int statusCode2 = response.getStatusCode();
//						AssertJUnit.assertEquals(statusCode2, 200);
//					
//						 ScheduleOrder_hubB=    Double.parseDouble(rootobjScheduleOrder_hub);
//					      ScheduleOrder_hub1B=    Double.parseDouble(rootobjScheduleOrder_hub1);
//					      ScheduleOrder_hub2B=    Double.parseDouble(rootobjScheduleOrder_hub2);
//					   // System.out.println(ScheduleOrder_hubB+ScheduleOrder_hub1B+ScheduleOrder_hub2B);
//					    ScheduleOrder_Bangalore=ScheduleOrder_hubB+ScheduleOrder_hub1B+ScheduleOrder_hub2B;
//					   System.out.println(ScheduleOrder_Bangalore+"Bangalore schedule order");
//					    
//					    for (int i = 0; i < array.length; i++)
//					       {
//					    	hubIDH=  Integer.parseInt(array[i]);
//					    	   sURLScheduleOrder_hubH =baseprodURLl+URLEncoder.encode(ssScheduleOrder_hub)+"'"+hubIDH+"'"+"and%20sheduled="+dateDPTsam+"%20and%20created_at<>%20"+dateDPTsam;
//					    	 URL urlScheduleOrder_hubH = new URL(sURLScheduleOrder_hubH);
//							    URLConnection requestScheduleOrder_hubH = urlScheduleOrder_hubH.openConnection();
//							    requestScheduleOrder_hubH.connect();
//
//							    // Convert to a JSON object to print data
//							    JsonParser jpScheduleOrder_hubH = new JsonParser(); //from gson
//							    JsonElement rootScheduleOrder_hubH = jpScheduleOrder_hubH.parse(new InputStreamReader((InputStream) requestScheduleOrder_hubH.getContent())); //Convert the input stream to a json element
//							     rootobjScheduleOrder_hubH= rootScheduleOrder_hubH.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(order_item_quantity)").getAsJsonObject().get("value").getAsString();
//							     ScheduleOrder_hubHyd +=    Double.parseDouble(rootobjScheduleOrder_hubH);
//							    //System.out.println(rootobjScheduleOrder_hub+"Schedule order Hub Level "+hubid);
//							    //May be an array, may be an object. 
//							   // System.out.println(rootobjScheduleOrder_hubH+"Schedule order Hub Level "+hubIDH);
//							     System.out.println(rootobjScheduleOrder_hubH+"Schedule order Hub Level hydrabad "+hubIDH);    
//					       }
//					    //System.out.println(ScheduleOrder_hubHyd); 
//					  ScheduleOrder+=ScheduleOrder_hubHyd+ScheduleOrder_Bangalore;
//					    System.out.println(ScheduleOrder+"Total ScheduleOrder for less than production Plan");
//					    					    
						String ssDispatchPlanHYD=rd.readDataFromExcel(excelFilePath, "Dataplanning", 8, 42);
					    String sURLDispatchPlanHyd =baseprodURLl+URLEncoder.encode(ssDispatchPlanHYD)+dateDPTsam;
					       // String sURLdp =baseprodURLl+URLEncoder.encode(ssDP)+dateDPT+"and version="+version; 
					       // String sURLdp =baseprodURLl+URLEncoder.encode(ssDP);
					      // System.out.println(sURLScheduleOrder_hub);
					       
					       URL urlDispPlanHyd = new URL(sURLDispatchPlanHyd);
						    URLConnection requestDispatchPlanHyd = urlDispPlanHyd.openConnection();
						    requestDispatchPlanHyd.connect();

						    // Convert to a JSON object to print data
						    JsonParser jpDispatchPlanHyd = new JsonParser(); //from gson
						    JsonElement rooturlDispPlanHyd = jpDispatchPlanHyd.parse(new InputStreamReader((InputStream) requestDispatchPlanHyd.getContent())); //Convert the input stream to a json element
						    String rootobjurlDispPlanHyd= rooturlDispPlanHyd.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(dispatch_plan_qty)").getAsJsonObject().get("value").getAsString();
						    dispatchQtyHyd=Double.parseDouble(rootobjurlDispPlanHyd);
						    //May be an array, may be an object. 
						    System.out.println(rootobjurlDispPlanHyd+"Dispatch Plan for Hydrabad");	
						
						    String ssDispatchPlanBANG=rd.readDataFromExcel(excelFilePath, "Dataplanning", 8, 43);
						    String sURLDispatchPlanBANG =baseprodURLl+URLEncoder.encode(ssDispatchPlanBANG)+dateDPTsam;
						       // String sURLdp =baseprodURLl+URLEncoder.encode(ssDP)+dateDPT+"and version="+version; 
						       // String sURLdp =baseprodURLl+URLEncoder.encode(ssDP);
						      // System.out.println(sURLScheduleOrder_hub);
						       
						       URL urlDispPlanBANG = new URL(sURLDispatchPlanBANG);
							    URLConnection requestDispatchPlanBANG = urlDispPlanBANG.openConnection();
							    requestDispatchPlanBANG.connect();

							    // Convert to a JSON object to print data
							    JsonParser jpDispatchPlanBANG = new JsonParser(); //from gson
							    JsonElement rooturlDispPlanBANG = jpDispatchPlanBANG.parse(new InputStreamReader((InputStream) requestDispatchPlanBANG.getContent())); //Convert the input stream to a json element
							    String rootobjurlDispPlanBANG= rooturlDispPlanBANG.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(dispatch_plan_qty)").getAsJsonObject().get("value").getAsString();
							    
							    //May be an array, may be an object. 
							    System.out.println(rootobjurlDispPlanBANG+"Dispatch Plan for BANGALORE");	
							    
							 dispatchQtyBang=Double.parseDouble(rootobjurlDispPlanBANG);
							     
							 double  dispatchQtyBang95=(95*dispatchQtyBang)/100;
							 System.out.println(dispatchQtyBang95+"95 % of Dispatch qty for bangalore ");
							 double dispatch=dispatchQtyBang95+dispatchQtyHyd+ScheduleOrder;
							 System.out.println(dispatch+" Total dispatch equal to Production Plan");
					   
							 
	}
	
	
	@DataProvider
	public Object[][] getData()
	{
	//Rows - Number of times your test has to be repeated.
	//Columns - Number of parameters in test data.
	Object[][] data = new Object[4][4];

	// 1st row B_C2
	data[0][0] ="1";
	data[0][1] = "4";
	data[0][2] = "10";
	data[0][3] = "B_C2";
	// 2nd row B_C4
	data[1][0] ="3";
	data[1][1] = "5";
	data[1][2] = "15";
	data[1][3] = "B_C4";
	// 3rd row
	//B_C1
	data[2][0] ="2";
	data[2][1] = "7";
	data[2][2] = "12";
	data[2][3] = "B_C1";
	
	
	//B_C3
	data[3][0] ="6";
	data[3][1] = "8";
	data[3][2] = "46";
	data[3][3] = "B_C3";
	
	
	
	return data;
	}
	//@Test()
	public void createDispatchPlan_Hydrabad_City() throws Throwable
	{
String dateString=rd.readDataFromExcel(excelFilePath, "Dataplanning", 8, 30);
		
		String dateDPTsam="'"+dateString+"'";
		
		String dateDPT="'"+dateString+"'"+"and%20version%20=";
		//System.out.println(dateDPT);
		 String ssDPHmaxV=rd.readDataFromExcel(excelFilePath, "Dataplanning", 9, 31);
			//"select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_5785b9065d7e1' and date=";
	        String sURLdpHVersion =baseprodURLl+URLEncoder.encode(ssDPHmaxV)+dateDPTsam;
	       // System.out.println(sURLdpHVersion);
	        URL urldpHMaxVer = new URL(sURLdpHVersion);
	 
	        URLConnection requestdpHmaxVar = urldpHMaxVer.openConnection();
	        requestdpHmaxVar.connect();
	 
	       JsonParser jpdpHmaxVersion = new JsonParser(); //from gson
	       JsonElement rootdpHmaxVer = jpdpHmaxVersion.parse(new InputStreamReader((InputStream) requestdpHmaxVar.getContent())); //Convert the input stream to a json element
	       String rootobjdpHmaxVersion = rootdpHmaxVer.getAsJsonObject().get("aggregations").getAsJsonObject().get("MAX(version)").getAsJsonObject().get("value").getAsString();
	      // System.out.println(rootobjdpHmaxVersion);
	       double dpHmaxVersiond=  Double.parseDouble(rootobjdpHmaxVersion);
	       int dpHmaxVersion=(int)dpHmaxVersiond;
	       String versionH="'"+dpHmaxVersion+"'";
	       //etest.log(LogStatus.PASS,"Maximum version of Demand Plan is "+version);
	     //  System.out.println(version);

	      
	       String sURLdpH =" ";
	       String ssDPH=rd.readDataFromExcel(excelFilePath, "Dataplanning", 9, 14);
	       String array[]= {"17","49","50","16","18","24","25"};
	       String cluster1="H_C1";
	        String cluster2="H_C2";
	        
			  
		    double dPlan_Cluster1=0;
		    double dPlan_Cluster2=0;
		    
 
	       for (int i = 0; i < array.length; i++)
	       {
				//System.out.println(array[i]);
				hubIDH=  Integer.parseInt(array[i]);
				   sURLdpH =baseprodURLl+URLEncoder.encode(ssDPH)+"'"+hubIDH+"'"+"and%20date="+dateDPT+versionH;
				 //  System.out.println(sURLdpH);
				   URL urldpH = new URL(sURLdpH);
				    URLConnection requestdpH = urldpH.openConnection();
				    requestdpH.connect();

				    // Convert to a JSON object to print data
				    JsonParser jpdpH = new JsonParser(); //from gson
				    JsonElement rootdpH = jpdpH.parse(new InputStreamReader((InputStream) requestdpH.getContent())); //Convert the input stream to a json element
				    String rootobjdpH = rootdpH.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
				    
				    
				    //May be an array, may be an object. 
				    //System.out.println(rootobjdpH+ "demand Plan of hub ID is "+hubIDH);
				   
		    
		    String ssDP_ClusterH=rd.readDataFromExcel(excelFilePath, "Dataplanning", 9, 38);
		       //String ssDPmaxV="select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date=";
		       String sURLdp_Cluster1 =baseprodURLl+URLEncoder.encode(ssDP_ClusterH)+"'"+cluster1+"'"+"and%20date="+dateDPT+versionH;
		       String sURLdp_Cluster2 =baseprodURLl+URLEncoder.encode(ssDP_ClusterH)+"'"+cluster2+"'"+"and%20date="+dateDPT+versionH;
		       
		      // System.out.println(sURLdp_Cluster1);
		      // System.out.println(sURLdp_Cluster2);
		       URL urldp_Cluster1 = new URL(sURLdp_Cluster1);
			    URLConnection requestdp_Cluster1 = urldp_Cluster1.openConnection();
			    requestdp_Cluster1.connect();

			    // Convert to a JSON object to print data
			    JsonParser jpdp_Cluster1 = new JsonParser(); //from gson
			    JsonElement rootdp_Cluster1 = jpdp_Cluster1.parse(new InputStreamReader((InputStream) requestdp_Cluster1.getContent())); //Convert the input stream to a json element
			    String rootobjdp_Cluster1 = rootdp_Cluster1.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
			 //   System.out.println(rootobjdp_Cluster1);
			    //May be an array, may be an object. 
			    //System.out.println(rootobjdp_Cluster+"demand Plan Cluster Level "+cluster_ID);
			    
			    URL urldp_Cluster2 = new URL(sURLdp_Cluster2);
			    URLConnection requestdp_Cluster2 = urldp_Cluster2.openConnection();
			    requestdp_Cluster2.connect();

			    // Convert to a JSON object to print data
			    JsonParser jpdp_Cluster2 = new JsonParser(); //from gson
			    JsonElement rootdp_Cluster2 = jpdp_Cluster2.parse(new InputStreamReader((InputStream) requestdp_Cluster2.getContent())); //Convert the input stream to a json element
			    String rootobjdp_Cluster2 = rootdp_Cluster2.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(final_forecast)").getAsJsonObject().get("value").getAsString();
			   // System.out.println(rootobjdp_Cluster2);
			    
			    
			    
			    String ssPP_ClusterH=rd.readDataFromExcel(excelFilePath, "Dataplanning", 9, 39);
			       //String ssDPmaxV="select max(version) from demand-plan where city_id='1' and hub_id in ('1','4','10') and product_id='pr_57235922d122e' and date=";
			       String sURLPP_Cluster1 =baseprodURLl+URLEncoder.encode(ssPP_ClusterH)+"'"+cluster1+"'"+"and%20production_date="+dateDPTsam;
			       String sURLPP_Cluster2 =baseprodURLl+URLEncoder.encode(ssPP_ClusterH)+"'"+cluster2+"'"+"and%20production_date="+dateDPTsam;
			       // String sURLdp =baseprodURLl+URLEncoder.encode(ssDP)+dateDPT+"and version="+version; 
			       // String sURLdp =baseprodURLl+URLEncoder.encode(ssDP);
			      // System.out.println(sURLPP_Cluster);
			       
			       URL urlPP_Cluster1 = new URL(sURLPP_Cluster1);
				    URLConnection requestPP_Cluster1 = urlPP_Cluster1.openConnection();
				    requestPP_Cluster1.connect();

				    // Convert to a JSON object to print data
				    JsonParser jpPP_Cluster1 = new JsonParser(); //from gson
				    JsonElement rootPP_Cluster1 = jpPP_Cluster1.parse(new InputStreamReader((InputStream) requestPP_Cluster1.getContent())); //Convert the input stream to a json element
				    String rootobjPP_Cluster1 = rootPP_Cluster1.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(quantity_to_produce)").getAsJsonObject().get("value").getAsString();
				    //System.out.println(rootobjPP_Cluster1);
				    
				    URL urlPP_Cluster2 = new URL(sURLPP_Cluster2);
				    URLConnection requestPP_Cluster2 = urlPP_Cluster2.openConnection();
				    requestPP_Cluster2.connect();

				    // Convert to a JSON object to print data
				    JsonParser jpPP_Cluster2 = new JsonParser(); //from gson
				    JsonElement rootPP_Cluster2 = jpPP_Cluster2.parse(new InputStreamReader((InputStream) requestPP_Cluster2.getContent())); //Convert the input stream to a json element
				    String rootobjPP_Cluster2 = rootPP_Cluster2.getAsJsonObject().get("aggregations").getAsJsonObject().get("SUM(quantity_to_produce)").getAsJsonObject().get("value").getAsString();
				    
				   // System.out.println(rootobjPP_Cluster2);
				    //May be an array, may be an object. 
				   // System.out.println(rootobjPP_Cluster+"Production Plan Cluster Level "+cluster_ID);
		            
				    dplan_hub_h=  Double.parseDouble(rootobjdpH);
				  
				     dPlan_Cluster1=Double.parseDouble(rootobjdp_Cluster1);
				     dPlan_Cluster2=Double.parseDouble(rootobjdp_Cluster2);
				     productionPlan_Cluster1=Double.parseDouble(rootobjPP_Cluster1);
				     productionPlan_Cluster2=Double.parseDouble(rootobjPP_Cluster2);
				     
				     if(hubIDH==17||hubIDH==49||hubIDH==50||hubIDH==16||cluster1.equals("H_C1"))
					    {
					    	if(hubIDH==17)
					    	{
					    		disp_Plan_Hub17=(dplan_hub_h/dPlan_Cluster1)*productionPlan_Cluster1; 
								    System.out.println(disp_Plan_Hub17+ " dispatch Plan for H_C1 "+dplan_hub_h+"HUB ID=  "+hubIDH+" "+dPlan_Cluster1+" "+"Production cluster"+productionPlan_Cluster1);	
					    	}
					    		if(hubIDH==49)
					    	{
					    			disp_Plan_Hub49=(dplan_hub_h/dPlan_Cluster1)*productionPlan_Cluster1; 
								    System.out.println(disp_Plan_Hub49+ " dispatch Plan for H_C1 "+dplan_hub_h+"HUB ID=  "+hubIDH+" "+dPlan_Cluster1+" "+"Production cluster"+productionPlan_Cluster1);	
					    	}
					    		if(hubIDH==50)
					    	{
					    			disp_Plan_Hub50=(dplan_hub_h/dPlan_Cluster1)*productionPlan_Cluster1; 
								    System.out.println(disp_Plan_Hub50+ " dispatch Plan for H_C1 "+dplan_hub_h+"HUB ID=  "+hubIDH+" "+dPlan_Cluster1+" "+"Production cluster"+productionPlan_Cluster1);	
					    	}
					    		if(hubIDH==16)
						    	{
					    			disp_Plan_Hub16=(dplan_hub_h/dPlan_Cluster1)*productionPlan_Cluster1; 
									    System.out.println(disp_Plan_Hub16+ " dispatch Plan for H_C1 "+dplan_hub_h+"HUB ID=  "+hubIDH+" "+dPlan_Cluster1+" "+"Production cluster"+productionPlan_Cluster1);	
						    	}
					    		 disp_Plan_HubH1=disp_Plan_Hub17+disp_Plan_Hub49+disp_Plan_Hub50+disp_Plan_Hub16; 
					    }
					    //double disp_Plan_Hub=(dplan_hub/dPlan_Cluster)*productionPlan_Cluster; 
					   // System.out.println(disp_Plan_Hub+ " dispatch Plan for B_C2 "+cluster_ID);
					
					    if (hubIDH==18||hubIDH==24||hubIDH==25||cluster2.equals("H_C2"))
					    {
					    	
					    	if(hubIDH==18)
					    	{
					    		disp_Plan_Hub18=(dplan_hub_h/dPlan_Cluster2)*productionPlan_Cluster2; 
								    System.out.println(disp_Plan_Hub18+ " dispatch Plan for H_C2 "+dplan_hub_h+" HUB ID= "+hubIDH+" "+dPlan_Cluster2+" "+"Production cluster"+productionPlan_Cluster2);	
					    	}
					    	if(hubIDH==24)
					    	{
					    		disp_Plan_Hub24=(dplan_hub_h/dPlan_Cluster2)*productionPlan_Cluster2; 
								    System.out.println(disp_Plan_Hub24+ " dispatch Plan for H_C2 "+dplan_hub_h+"HUB ID=  "+hubIDH+" "+dPlan_Cluster2+" "+"Production cluster"+productionPlan_Cluster2);	
					    	}
					    		if(hubIDH==25)
					    	{
					    			disp_Plan_Hub25=(dplan_hub_h/dPlan_Cluster2)*productionPlan_Cluster2; 
								    System.out.println(disp_Plan_Hub25+ " dispatch Plan for H_C2 "+dplan_hub_h+"HUB ID=  "+hubIDH+" "+dPlan_Cluster2+" "+"Production cluster"+productionPlan_Cluster2);	
					    	}	    	
					    		 disp_Plan_HubH2=disp_Plan_Hub18+disp_Plan_Hub24+disp_Plan_Hub25; 
		                }
			    	
			    }
       }
	
	}
//	@DataProvider
//	public Object[][] getData()
//	{
//	//Rows - Number of times your test has to be repeated.
//	//Columns - Number of parameters in test data.
//	
//
//	for (int i = 1; i <= 5; i++) {
//		for (int j = 1; j <= 21; j++) {
//		
//			String value=	rd.readDataFromExcel(excelFilePath, "Dataplanning", i, 41);
//			
//			System.out.println(value);
//		}
//		
//	}
//	
//	return data;
//	}

