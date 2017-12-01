
public class MembreRegulier extends Membre{

	private String etat;
	
	public MembreRegulier(long numeroUnique, String nom,
			String email,String etat,String adresse,String ville,String codePostal,String province) {
		super(numeroUnique, nom, email,adresse,ville,codePostal,province);
		this.etat=etat;
		
	}
	
	

	public String getEtat() {
		return etat;
	}



	public void setEtat(String etat) {
		this.etat = etat;
	}





}
