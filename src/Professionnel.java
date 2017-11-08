
public class Professionnel extends Membre{

	private int []codeDesServiceDonnés;
	private int nombreDeServiceDonné;
	
	
	public Professionnel(long numeroUnique, String nom, int age, String email,int [] codesDesServiceDonnés,int nombreDeServiceDonné) {
		super(numeroUnique, nom, email);
		this.codeDesServiceDonnés=codesDesServiceDonnés;
		this.nombreDeServiceDonné=nombreDeServiceDonné;
		
	}
	
	
	public int getNombreDeServiceDonné() {
		return nombreDeServiceDonné;
	}

	public void setNombreDeServiceDonné(int nombreDeServiceDonné) {
		this.nombreDeServiceDonné = nombreDeServiceDonné;
	}



	
	
}
