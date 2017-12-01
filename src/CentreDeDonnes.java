import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;




public class CentreDeDonnes {
   
	//un hashmap contenant tout les membres qui son inscrit 
	public HashMap<Long ,Membre> membres;
	public HashMap<Integer ,Service> services;
	public HashMap<Long, Professionnel> professionnels;
	
    private long id=100000000;
	private int serviceId=100;
	
	public  CentreDeDonnes(){
		membres=new HashMap<Long, Membre>();
		services=new HashMap<Integer, Service>();
		professionnels=new HashMap<Long, Professionnel>();
	}
	
	public HashMap<Long, Membre> getMembres() {
		return membres;
	}

	public void setMembres(HashMap<Long, Membre> membres) {
		this.membres = membres;
	}

	public HashMap<Integer, Service> getServices() {
		return services;
	}

	public void setServices(HashMap<Integer, Service> services) {
		this.services = services;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int l) {
		this.serviceId = l;
	}

	public static void main(String [] Args){
		//new CentreDeDonnes().creeMembre();
		
	}
}
