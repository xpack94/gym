
public class MembreRegulier extends Membre{

	/**
	 * @author abdesselam
	 * definit le membre regulier qui lui meme herite de membre avec un attribut en plus qui est etat 
	 * @version 2
	 */
	
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
