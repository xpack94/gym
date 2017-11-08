
public class Membre {

	private long numeroUnique;
	private String nom;
	private int age ;
	private String email;
	
	
	public  Membre(long numeroUnique, String nom,String email){
		this.numeroUnique=numeroUnique;
		this.nom=nom;
		this.email=email;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public long getNumeroUnique() {
		return numeroUnique;
	}


	public void setNumeroUnique(long numeroUnique) {
		this.numeroUnique = numeroUnique;
	}





	
	
	
	
}
