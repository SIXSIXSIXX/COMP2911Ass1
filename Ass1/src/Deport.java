import java.util.ArrayList;

public class Deport {
	private String name;
	private ArrayList<Campervan> campervan = new ArrayList<Campervan>();
	public Deport(String name){
		this.name  = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Campervan> getCampervan() {
		return campervan;
	}
	
	public void setCampervan(ArrayList<Campervan> campervan) {
		this.campervan = campervan;
	}
	
	/**
	 * 
	 * @param name
	 * @param type
	 * Add cam to the deport
	 */
	public void addCampervan(String name,String type){
		Campervan cam = new Campervan(this.name,name,type);
		campervan.add(cam);
	}
	
}
