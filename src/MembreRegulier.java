
public class MembreRegulier extends Membre{

	private String etat;
	
	public MembreRegulier(long numeroUnique, String nom,
			String email,String etat) {
		super(numeroUnique, nom, email);
		this.etat=etat;
		
	}
	
	

	public String getEtat() {
		return etat;
	}



	public void setEtat(String etat) {
		this.etat = etat;
	}





}
