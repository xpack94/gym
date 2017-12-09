import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;


/**
 * 
 * @author Abdesselam
 * represente la base de donneé du Gym ou toutes les informations des membre , professionnel, service sont stocké 
 * @param membres de type hashmap qui conttient tout les membres qui sont inscrit dans le gym
 * @param services un hashmap contenant tout les service donné par des professionnel avec tout les information necessaire
 * @param professionnels un hashmap contenant tout les professionnel du Gym
 * @param id qui represente un code unique a 9 chiffres ui va etre attribuér au membre 
 * @param serviceId qui represente le code a 3 chiffres qui va etre attribuér a un service  
 * 
 * 
 * 
 * @version 3 
 * 
 *
 */

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

	
}
