
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
