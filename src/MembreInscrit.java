import java.sql.Date;


public class MembreInscrit {
	
	/**
	 * @author abdesselam
	 * classe qui definie l'inscription d'un membre a une seance et contient toutes les informations de cette inscription 
	 * @version 3
	 *  
	 */
	
	private String Date_et_heure_actuelles;
	private String Date_à_laquelle_du_service_qui_sera_fourni;
	private long numéro_du_professionnel;
	private long numero_du_membre;
	private int codeDuService;
	private String commentaire;
	
	public MembreInscrit(String dateAct,String dateAfournir,long numeroDuProfessionnel,long numeroDuMembre,int codeDuService,String commentaire){
		this.Date_et_heure_actuelles=dateAct;
		this.Date_à_laquelle_du_service_qui_sera_fourni=dateAfournir;
		this.numéro_du_professionnel=numeroDuProfessionnel;
		this.numero_du_membre=numeroDuMembre;
		this.codeDuService=codeDuService;
		this.commentaire=commentaire;
	}

	public int getCodeDuService() {
		return codeDuService;
	}

	public void setCodeDuService(int codeDuService) {
		this.codeDuService = codeDuService;
	}

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
		return numéro_du_professionnel;
	}

	public void setNuméro_du_professionnel(long numéro_du_professionnel) {
		this.numéro_du_professionnel = numéro_du_professionnel;
	}

	public long getNumero_du_membre() {
		return numero_du_membre;
	}

	public void setNumero_du_membre(long numero_du_membre) {
		this.numero_du_membre = numero_du_membre;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	
}
