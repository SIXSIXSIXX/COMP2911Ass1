import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class BookingSystem {
	private ArrayList<Deport> deports = new ArrayList<Deport>();
	public BookingSystem(ArrayList<Deport> allDeports){
		this.deports = allDeports;
	}
	/**
	 * 
	 * @param command
	 * @param words
	 * @throws ParseException
	 * handlde the booking and changing request
	 */
	public void bookingorchanging(String command,String[] words) throws ParseException{
		ArrayList<Campervan> validDeports = new ArrayList<Campervan>();
		Booking booking = new Booking(words[1],words[2],words[3],words[4],words[5],words[6],words[7]);
		//words.length >10 2 types are required
		if(words.length>10&&!words[10].matches("#.*")){
		ArrayList<Campervan> type1= validCheck(booking,Integer.parseInt(words[8]),words[9]);
		ArrayList<Campervan> type2= (validCheck(booking,Integer.parseInt(words[10]),words[11]));
		//if size>0 the request can be fulfilled
			if(type1.size()>0&&type2.size()>0) {
				validDeports.addAll(type1);
				validDeports.addAll(type2);
				//handle the booking request
				if(command.equals("Request")){
					for(Campervan cam:validDeports){				
						cam.setBookList(booking);
					}
				this.printRequest(command, deports,validDeports, words);
					
				}
				//handle the change request
				if(command.equals("Change")){
					for(Deport d:deports){
						for(Campervan cam:d.getCampervan()){
							for(int i=0;i<cam.getBookList().size();i++){
								if(cam.getBookList().get(i).getRequestID()==Integer.parseInt(words[1])){
									cam.getBookList().remove(i);
									break;
								}
							}
						}
					}
					for(Campervan cam:validDeports){				
						cam.setBookList(booking);
					}
					this.printRequest(command, deports,validDeports, words);
				}
			}
			//request can not be fulfilled size==0
			else {
				if(command.equals("Request"))System.out.println("Booking rejected");
				if(command.equals("Change"))System.out.println("Change rejected");
			}
		}
		//words.length<10 One type is required
		else{
			ArrayList<Campervan> type1= validCheck(booking,Integer.parseInt(words[8]),words[9]);
			if(type1.size()>0) {
				validDeports.addAll(type1);
				//handle the booking request
				if(command.equals("Request")){
					//add the booking into cam bookinglist
					for(Campervan cam:validDeports){				
						cam.setBookList(booking);
					}
					this.printRequest(command, deports,validDeports, words);
				}
				//handle the change request
				if(command.equals("Change")){
					for(Deport d:deports){
						for(Campervan cam:d.getCampervan()){
							for(int i=0;i<cam.getBookList().size();i++){
								if(cam.getBookList().get(i).getRequestID()==Integer.parseInt(words[1])){
									cam.getBookList().remove(i);
									break;
								}
							}
						}
					}
					//add the booking into the cam bookinglist
					for(Campervan cam:validDeports){				
						cam.setBookList(booking);
					}
					this.printRequest(command, deports,validDeports, words);
				}
			}
			//size==0
			else{
				if(command.equals("Request"))System.out.println("Booking rejected");
				if(command.equals("Change"))System.out.println("Change rejected");
			}
		}
	}
	
	/**
	 * 
	 * @param words
	 * handle the cancel request, if the ID does not exist, cancel rejected
	 */
	public void cancel(String[] words){
		boolean flag =false;
		for(Deport d:deports){
			for(Campervan cam:d.getCampervan()){
				for(int i=0;i<cam.getBookList().size();i++){
					if(cam.getBookList().get(i).getRequestID()==Integer.parseInt(words[1])){
						flag= true;
						cam.getBookList().remove(i);
						break;
					}
				}
			}
		}
		if(flag)System.out.println("Cancel "+words[1]);
		else System.out.println("Cancel rejected");
	}
	
	
	/**
	 * 
	 * @param book
	 * @param num1
	 * @param type1
	 * check the request whether can be fulfilled. After the loop if the num1==0, the request is valid
	 * @return
	 */
	public ArrayList<Campervan> validCheck(Booking book,int num1,String type1){
		ArrayList<Campervan> valid = new ArrayList<Campervan>();
		mainloop:
		for(Deport d:deports){
			for(Campervan cam:d.getCampervan()){
				if(type1.equals(cam.getType())){
					boolean flag = true;
					for(Booking b:cam.getBookList()){
						//skip the same ID booking
						if(b.getRequestID()==book.getRequestID()) {
							continue;
						}
						if((b.isAfter(book.getsDate())&&b.isAfter(book.geteDate()))||(b.isBefore(book.getsDate())&&b.isBefore(book.geteDate()))){
							continue;
						}
						else{
							flag = false;
							break;
						}
					}
					if(flag){
						valid.add(cam);
						num1--;
						if(num1==0) break mainloop;
					}
				}
			}
		}
		//request can not be fulfilled return zero size ArrayList
		if(num1!=0) {
			return new ArrayList<Campervan>();
		}
		return valid;
		
	}
	
	/**
	 * 
	 * @param command
	 * @param deports
	 * @param list
	 * @param words
	 * Print the booking and changing request result
	 */
	public void printRequest(String command,ArrayList<Deport> deports,ArrayList<Campervan> list,String[] words){
		ArrayList<Campervan> validDeports= new ArrayList<Campervan>();
		//sort the order of the campervans
		for(Deport d:deports){
			for(Campervan cam:d.getCampervan()){
				for(Campervan car:list){
					if(cam.getName().equals(car.getName()))validDeports.add(cam);
				}
			}
		}
		String belong = validDeports.get(0).getBelong();
		if(command.equals("Request"))command = "Booking";
		System.out.print(command+" "+words[1]+" "+belong+" "+validDeports.get(0).getName());
		if(validDeports.size()==1)System.out.println();
		for(int i =1;i<validDeports.size();i++){						
			if(belong==validDeports.get(i).getBelong()){
				System.out.print(", ");
			}
			else {
				belong = validDeports.get(i).getBelong();
				System.out.print("; "+validDeports.get(i).getBelong()+" ");
			}
			if(i==validDeports.size()-1)System.out.println(validDeports.get(i).getName());
			else System.out.print(validDeports.get(i).getName());
		}
	}
	
	/**
	 * handle the print request(i.e Print CBD)
	 * @param words
	 */
	public void printDeport(String[] words){
		for(Deport d:deports){
			if(d.getName().equals(words[1])){
				for(Campervan cam:d.getCampervan()){
					cam.sortDate();
					for(Booking b:cam.getBookList()){
						Date s = b.getsDate();
						Date e = b.geteDate();
						String shour =""+s.getHours();
						String ehour =""+e.getHours();
						String sdate =""+s.getDate();
						String edate =""+e.getDate();
						if(s.getHours()<10) shour="0"+shour;
						if(e.getHours()<10) ehour="0"+ehour;
						if(s.getDate()<10) sdate ="0"+sdate;
						if(e.getDate()<10) edate = "0"+edate;
						System.out.println(cam.getBelong()+" "+cam.getName()+" "+shour+":00 "+b.IntToMon(s.getMonth()+1)+" "+sdate+" "+ehour+":00 "+b.IntToMon(e.getMonth()+1)+" "+edate);
					}
				}
			}
		}
	}
}
