import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;




public class CentreDeDonnes {
   
	//un hashmap contenant tout les membres qui son inscrit 
	public HashMap<Integer ,Membre> membres;
	public HashMap<Integer ,Service> services;
	
    private long id=0;
	private int serviceId=1000000;
	
	public void CentreDeDonnes(){
		membres=new HashMap<Integer, Membre>();
		services=new HashMap<Integer, Service>();
	}
	
	public HashMap<Integer, Membre> getMembres() {
		return membres;
	}

	public void setMembres(HashMap<Integer, Membre> membres) {
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
