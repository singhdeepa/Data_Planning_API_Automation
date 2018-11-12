package in.licious.dataplanning;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.ParseException;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;

public class Compare_DB {
@Test
public void compareData() throws Throwable
{
    Json1 j=new Json1();
    SQL_DB s=new SQL_DB();
    SQL_DB2 s2=new SQL_DB2();
    JsonElement js=j.jsonData();
    String js1=js.toString();
    
    for (int i = 0; i < 31; i++) {

    	Date date = new Date();
        String DATE_FORMAT = "yyyy-mm-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
       
        Calendar cal = Calendar.getInstance();
	    cal.setTime ( date ); // convert your date to Calendar object
	    int daysToDecrement = -i;
		cal.add(Calendar.DATE, daysToDecrement);
		date = cal.getTime(); // again get back your date object
	String  datesql=sdf.format(date);
		System.out.println("Date is coming " + datesql);

	}
    

  
    double m=  Double.parseDouble(js1);
    System.out.println(js1);
    String db1=s.data_DB();
    String db2=s2.data_DB();
    System.out.println(db1);
    System.out.println(db2);
    double q1=  Double.parseDouble(db1);
    double q2=  Double.parseDouble(db2);
   double q=q1+q2;
    
    System.out.println(q);

    for(int i=0;i<=110;i++)
    {
    if(m==q)
    { 
        System.out.println("same"+js1+" "+q);
    }else
    {
        System.out.println("not same");
    }
    }
    /*for(int i=1;i<=5;i++)
    {
        if(j.jsonData().)
    }*/
}
}