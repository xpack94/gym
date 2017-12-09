
/**
 * 
 * @author abdesselam
 * 
 * definit le type Presence qui est utilisé lors de la presence d'un membre a une seance 
 * @version 1 
 * @param DateEtHeuresActueles qui est la date et l'heure a lesquels le membre a assister a la seance
 * @param numeroDuProfessionnel qui represente le numero du professionnel qui a donné la seance
 * @param numeroDuMembre qui represente le numero du membre qui a assisté 
 * @param codeDeLaSeance qui represente la seance en question 
 * @param cmmentaire un commentaire sur la presence
 * 
 *
 */


public class Presence {

	
	
	String DateEtHeuresActueles;
	long numeroDuProfessionnel;
	long numeroDuMembre;
	int codeDeLaSeance;
	String commentaire;
	
	
	public Presence(String date,long num,long numeroMembre,int codeDeSeance,String comment){
		this.DateEtHeuresActueles=date;
		this.numeroDuMembre=numeroMembre;
		this.numeroDuProfessionnel=num;
		this.codeDeLaSeance=codeDeSeance;
		this.commentaire=commentaire;
	}


	public String getDateEtHeuresActueles() {
		return DateEtHeuresActueles;
	}


	public void setDateEtHeuresActueles(String dateEtHeuresActueles) {
		DateEtHeuresActueles = dateEtHeuresActueles;
	}


	public long getNumeroDuProfessionnel() {
		return numeroDuProfessionnel;
	}


	public void setNumeroDuProfessionnel(long numeroDuProfessionnel) {
		this.numeroDuProfessionnel = numeroDuProfessionnel;
	}


	public long getNumeroDuMembre() {
		return numeroDuMembre;
	}


	public void setNumeroDuMembre(long numeroDuMembre) {
		this.numeroDuMembre = numeroDuMembre;
	}


	public int getCodeDeLaSeance() {
		return codeDeLaSeance;
	}


	public void setCodeDeLaSeance(int codeDeLaSeance) {
		this.codeDeLaSeance = codeDeLaSeance;
	}


	public String getCommentaire() {
		return commentaire;
	}


	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	
}
