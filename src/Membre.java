import java.util.ArrayList;


public class Membre {
	
	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public String getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	private long numeroUnique;
	private String nom;
	private int age ;
	private String email;
	private String adresse;
	private String ville;
	private String codePostal;
	private String province;
	private ArrayList<Integer> codeDesSeancesInscirit;
	private ArrayList<Presence> presence;
	
	public  Membre(long numeroUnique,String nom,String email,String adresse,String ville,String codePostal,String province){
		this.numeroUnique=numeroUnique;
		this.nom=nom;
		this.email=email;
		this.adresse=adresse;
		this.ville=ville;
		this.codePostal=codePostal;
		this.province=province;
		this.codeDesSeancesInscirit=new ArrayList<Integer>();
		this.presence=new ArrayList<Presence>();
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


	public ArrayList<Integer> getCodeDesSeancesInscirit() {
		return codeDesSeancesInscirit;
	}


	public void setCodeDesSeancesInscirit(ArrayList<Integer> codeDesSeancesInscirit) {
		this.codeDesSeancesInscirit = codeDesSeancesInscirit;
	}

	public void setCodeDesSeancesInscirit(int code) {
		this.codeDesSeancesInscirit.add(code);
	}


	


	public ArrayList<Presence> getPresence() {
		return presence;
	}


	public void setPresence(ArrayList<Presence> presence) {
		this.presence = presence;
	}
	
	public void setPresence(Presence p) {
	
		this.presence.add(p);
	}
	public void setPresence(Presence p,int index) {
		
		this.presence.add(index,p);
	}


	
	
	
	
}
