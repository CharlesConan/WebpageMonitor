import java.io.*;
import java.net.*;
import java.util.Calendar;

public class Monitor {
	
	//public static String targetURL = "https://shop.exclucitylife.com/products/nike-air-foamposite-pro";
	public static String targetURL; 
	public static String output; 
	
	private static String timeTo2String(int timeInt){
		if (timeInt<10){
			return "0"+Integer.toString(timeInt);
		}
		return Integer.toString(timeInt);	
	}
	
	private static String timeTo3String(int timeInt){
		if (timeInt<10){
			return "00"+Integer.toString(timeInt);
		}else if (timeInt<100){
			return "0"+Integer.toString(timeInt);
		}
		return Integer.toString(timeInt);	
	}
	
	public void setTargetURL(String text){
		targetURL = text;
	}
	
	public static void main(String[] args) {
	    URL url;
	    InputStream is = null;
	    BufferedReader br;
	    String line;
	    Integer lineNumber = 0;
	    
	    long millis=System.currentTimeMillis();
	    Calendar c=Calendar.getInstance();
	    c.setTimeInMillis(millis);
	    int years=c.get(Calendar.YEAR);
	    int months=c.get(Calendar.MONTH)+1;
	    int days=c.get(Calendar.DATE);
	    int hours=c.get(Calendar.HOUR_OF_DAY);
	    int minutes=c.get(Calendar.MINUTE);
	    int seconds=c.get(Calendar.SECOND);
	    int milliseconds=c.get(Calendar.MILLISECOND);
	    String timeAsString = Integer.toString(years)+timeTo2String(months)+timeTo2String(days)+timeTo2String(hours)+timeTo2String(minutes)+timeTo2String(seconds)+timeTo3String(milliseconds);
	    try {
	        url = new URL(targetURL);
	        is = url.openStream();  // throws an IOException
	        br = new BufferedReader(new InputStreamReader(is));

	        try{
	            PrintWriter writer = new PrintWriter(timeAsString+".txt", "UTF-8");
	            StringWriter strOut = new StringWriter();
	            
		        while ((line = br.readLine()) != null) {
		            //System.out.println(line);
		        	
		            writer.println(line);
		            strOut.write(lineNumber.toString()+":    "+line);
		            strOut.write("\n");
		            lineNumber++;
		        }
	            writer.close();
	            output = strOut.toString();
	            //System.out.print(output);
	        } catch (IOException e) {
	           // do something
	        }
	        

	    } catch (MalformedURLException mue) {
	         mue.printStackTrace();
	    } catch (IOException ioe) {
	         ioe.printStackTrace();
	    } finally {
	        try {
	            if (is != null) is.close();
	        } catch (IOException ioe) {
	            // nothing to see here
	        }
	    }
	}

	
}
