
public class SeanceInscrit {

	private String Date_et_heure_actuelles;
	private String Date_à_laquelle_du_service_qui_sera_fourni;
	private long Numéro_du_professionnel;
	private long Numéro_du_membre;
	private int Code_du_service;
	
	public String getDate_et_heure_actuelles() {
		return Date_et_heure_actuelles;
	}

	public void setDate_et_heure_actuelles(String date_et_heure_actuelles) {
		Date_et_heure_actuelles = date_et_heure_actuelles;
	}

	public String getDate_à_laquelle_du_service_qui_sera_fourni() {
		return Date_à_laquelle_du_service_qui_sera_fourni;
	}

	public void setDate_à_laquelle_du_service_qui_sera_fourni(
			String date_à_laquelle_du_service_qui_sera_fourni) {
		Date_à_laquelle_du_service_qui_sera_fourni = date_à_laquelle_du_service_qui_sera_fourni;
	}

	public long getNuméro_du_professionnel() {
		return Numéro_du_professionnel;
	}

	public void setNuméro_du_professionnel(long numéro_du_professionnel) {
		Numéro_du_professionnel = numéro_du_professionnel;
	}

	public long getNuméro_du_membre() {
		return Numéro_du_membre;
	}

	public void setNuméro_du_membre(long numéro_du_membre) {
		Numéro_du_membre = numéro_du_membre;
	}

	public int getCode_du_service() {
		return Code_du_service;
	}

	public void setCode_du_service(int code_du_service) {
		Code_du_service = code_du_service;
	}

	public SeanceInscrit(String s,String d,long num,long numMembre,int code){
		this.Date_et_heure_actuelles=s;
		this.Date_à_laquelle_du_service_qui_sera_fourni=d;
		this.Numéro_du_professionnel=num;
		this.Numéro_du_membre=numMembre;
		this.Code_du_service=code;
		
	}
	
}
