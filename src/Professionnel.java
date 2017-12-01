import java.util.ArrayList;


public class Professionnel extends Membre{

	private ArrayList<Integer> codeDesServiceDonnés;
	private int nombreDeServiceDonné;
	
	public Professionnel (long numeroUnique, String nom, String email,String adresse ,String ville,String codePostal,String province){
		super(numeroUnique, nom, email, adresse, ville, codePostal, province);
		this.codeDesServiceDonnés = new ArrayList<Integer>();
		this.nombreDeServiceDonné=0;
	}
	
	public Professionnel(long numeroUnique, String nom, String email,String adresse ,String ville,String codePostal,String province,ArrayList<Integer> codesDesServiceDonnés,int nombreDeServiceDonné) {
		super(numeroUnique, nom, email, adresse, ville, codePostal, province);
		this.codeDesServiceDonnés=codesDesServiceDonnés;
		this.nombreDeServiceDonné=nombreDeServiceDonné;
		
	}
	
	
	public ArrayList<Integer> getCodeDesServiceDonnés() {
		return codeDesServiceDonnés;
	}

	public void setCodeDesServiceDonnés(ArrayList<Integer> codeDesServiceDonnés) {
		this.codeDesServiceDonnés = codeDesServiceDonnés;
	}
	public int getCodeDesServiceDonnés(int i) {
		return codeDesServiceDonnés.get(i);
	}

	public int getNombreDeServiceDonné() {
		return nombreDeServiceDonné;
	}

	public void setNombreDeServiceDonné(int nombreDeServiceDonné) {
		this.nombreDeServiceDonné = nombreDeServiceDonné;
	}

  

	
	
}
