import java.util.ArrayList;

public class Campervan {
	private String belong;
	private String name;
	private String type;
	private ArrayList<Booking> bookList= new ArrayList<Booking>();
	public Campervan(String belong,String name,String type){
		this.setBelong(belong);
		this.name = name;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<Booking> getBookList() {
		return bookList;
	}
	public void setBookList(Booking booking) {
		this.bookList.add(booking);
	}
	public String getBelong() {
		return belong;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	
	/**
	 * Sort the booking date using bubble sort in order to print the result
	 */
	public void sortDate(){
		Booking temp = null;
		for(int i =0;i<this.getBookList().size();i++){
			for(int j=1;j<this.getBookList().size()-i;j++){
				if(this.getBookList().get(j-1).getsDate().after(this.getBookList().get(j).getsDate())){					
					temp = this.getBookList().get(j-1);
					this.getBookList().set(j-1, this.getBookList().get(j));
					this.getBookList().set(j, temp);
					
				}
			}
		}
	}
	/**
	 * 
	 * @param ID
	 * @return true and remove if the ID exit, otherwise false
	 */
	public boolean removeBookingList(int ID){
		for(int i=0;i<this.bookList.size();i++){
			if(bookList.get(i).getRequestID()==ID)
				bookList.remove(i);
				return true;
		}
		return false;
	}

}
