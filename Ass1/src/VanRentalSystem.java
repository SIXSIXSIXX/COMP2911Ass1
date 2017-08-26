import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class VanRentalSystem {

	public static void main(String[] args) throws ParseException {
		Scanner sc = null;
	      try
	      {
	    	  sc = new Scanner(new FileReader(args[0]));    
	      }
	      catch (FileNotFoundException e) {
	    	  System.out.println("No such file");
	      }
	      finally
	      {
	    	  String request;
	    	  String line;
	    	  ArrayList<Deport> allDeports = new ArrayList<Deport>();
	    	  BookingSystem bookingsys = new BookingSystem(allDeports);
	    	  while(sc.hasNextLine()&&sc.hasNext()){
	    		  request = sc.next();
	    		  line = sc.nextLine();
	    		  //System.out.println(line);
	    		  String[] words = line.split(" ");
	    		  //Deports to ArrayList
	    		  if(request.equals("Location")){
	    			  int flag =0;
	    			 
	    			  //System.out.println("a"+words[1]);
	    	    	  for(Deport d:allDeports){
	        			if(d.getName().equals(words[1])){
	        				d.addCampervan(words[2], words[3]);
	        				flag = 1;
	        				break;
	        			}			
	    	    	  }
	    	    	  if(flag==0) {
	    	    		  Deport lo = new Deport(words[1]);
	    	    		  lo.addCampervan(words[2], words[3]);
	    	    		  allDeports.add(lo);
	    	    	  }
	    		  }
	    		  
	    		if(request.equals("Request"))bookingsys.bookingorchanging(request,words);
	    		
	    		if(request.equals("Change")){
	    			boolean flag = false;
	    			//handle the request ID does not exist
	    			mainloop:for(Deport d:allDeports){
	    				for(Campervan cam:d.getCampervan()){
	    					for(Booking b:cam.getBookList()){
	    						if(b.getRequestID()==Integer.parseInt(words[1])){
	    							flag = true;
	    							break mainloop;
	    						}
	    					}
	    				}
	    			}
	    			if(flag)bookingsys.bookingorchanging(request,words);
	    			else System.out.println("Change rejected");
	    		}
	    		//cancel request
	    		if(request.equals("Cancel"))bookingsys.cancel(words);
	    		//print request
	    		if(request.equals("Print"))bookingsys.printDeport(words);
	    	  }
	      }
	}


}
