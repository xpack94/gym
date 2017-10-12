
public class Membre {

	private long numeroUnique;
	private String etat;
	
	
	public  Membre(long numeroUnique,String etat){
		this.numeroUnique=numeroUnique;
		this.etat=etat;
	}


	public long getNumeroUnique() {
		return numeroUnique;
	}


	public void setNumeroUnique(long numeroUnique) {
		this.numeroUnique = numeroUnique;
	}


	public String getEtat() {
		return etat;
	}


	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	
	
	
}
