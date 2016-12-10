import java.io.*;
import java.net.*;
import java.util.Calendar;
import java.util.regex.Pattern;

public class Monitor {
	
	//public static String targetURL = "https://shop.exclucitylife.com/products/nike-air-foamposite-pro";
	private String targetURL; 
	private String searchContent;
	private String searchNumberString;
	private long searchNumber;
	private String output; 
	
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
	
	public void setSearchContent(String text){
		searchContent = text;
	}
	
	public void setSearchNumberString(String text){
		searchNumberString = text;
		searchNumber = Long.parseLong(text);
	}
	
//	public String convertSearchNumber(String text){
//	long temp = Long.parseLong(text);
//	return Long.toString(temp);
//	}
	
//	public String convertURL(String text){
//		long temp = Long.parseLong(text)+64;
//		return Long.toString(temp)+":1";
//	}
	
	public String getOutput(){
		return output;
	}
	
	public void readSourceCode(String[] args) {
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
	    	//System.out.println("1");
	        url = new URL(targetURL+Long.toString(searchNumber)+":1");
	        is = url.openStream();  // throws an IOException
	        br = new BufferedReader(new InputStreamReader(is));
	        //System.out.println("2");
	        
//	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//	        Long dateTime = connection.getLastModified();
//	        //System.out.println(Long.toString(dateTime));

	        try{
	            PrintWriter writer = new PrintWriter(timeAsString+".txt", "UTF-8");
	            StringWriter strOut = new StringWriter();
	            
		        while ((line = br.readLine()) != null) {
		            //System.out.println(searchContent);
		        	if (Pattern.compile(Pattern.quote("try again"), Pattern.CASE_INSENSITIVE).matcher(line).find()){
		        		System.out.println("OOPS");
		        	}else if (Pattern.compile(Pattern.quote(searchContent), Pattern.CASE_INSENSITIVE).matcher(line).find()){
		        		strOut.write(lineNumber.toString()+":    "+line);
			            strOut.write("\n");
			            writer.println(line);
			            //System.out.print("3");
		        	}else{
		        		//System.out.print(searchContent);
		        	}
		            //writer.println(line);		            
		            lineNumber++;
		            
		        }
		        strOut.write(Long.toString(searchNumber));
	            strOut.write("\n");
		        writer.println(Long.toString(searchNumber));
		        searchNumber+=640;
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
	    	if (ioe.getMessage().toLowerCase().contains("response")){
	    		System.out.println(ioe.getMessage());
	    	}else{
	    		searchNumber+=640;
	    	}
	         
	    } finally {
	        try {
	            if (is != null) is.close();
	        } catch (IOException ioe) {
	            // nothing to see here
	        }
	    }
	    //System.out.println(targetURL+searchNumber+":1");
	}

	
}
