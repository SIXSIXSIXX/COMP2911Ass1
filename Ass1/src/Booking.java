import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class Booking {
	private int RequestID;
	private Date sDate;
	private Date eDate;
	
	
	public Booking(String ID,String starthour,String startmonth,String startday,String endhour,String endmonth,String endday) throws ParseException{
		this.RequestID = Integer.parseInt(ID); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
		 sDate = sdf.parse("2017-"+MonToint(startmonth)+"-"+startday+"-"+starthour);
		 eDate = sdf.parse("2017-"+MonToint(endmonth)+"-"+endday+"-"+endhour);
	        
	}
	
//	public void s(){
//		System.out.println(sDate.getMonth());
//	}
//	
	public int getRequestID() {
		return RequestID;
	}

	public void setRequestID(int requestID) {
		RequestID = requestID;
	}

	public Date getsDate() {
		return sDate;
	}

	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}

	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	/**
	 * 
	 * @param date
	 * @return true if current start date is after the "other date"
	 */
	public boolean isAfter(Date date){
		return this.sDate.after(date);
	}
	/**
	 * 
	 * @param date
	 * @return true if current start date is before the "other date"
	 */
	public boolean isBefore(Date date){
		return this.eDate.before(date);
	}
	
	/**
	 * 
	 * @param Month
	 * @return the Integer stand for the month(i.e JAN=1)
	 * @throws ParseException
	 */
	public int MonToint(String Month) throws ParseException{
		Date date = new SimpleDateFormat("MMMM").parse(Month);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return (cal.get(Calendar.MONTH)+1);
	}
	
	/**
	 * 
	 * @param Month
	 * @return the month name (i.e. 3 = MAR)
	 */
	public String IntToMon(int Month){
		return new DateFormatSymbols().getShortMonths()[Month-1];
	}
}