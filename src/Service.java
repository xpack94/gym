import java.util.HashMap;




public class Service {
  private Seance [] seances;
  private String dateEtHeuresActuelles;
  private  String dateDebutService;
  private  String dateFinService;
  private  String heureDuService;
  private  String recurrenceHebdo;
  private int capaciteMaximale;
  private long numeroDuProfessionnel;
  private int codeDuService;
  private String commentaire;
  //l'index sert a savoir a quelle case du array seances on insert une nouvelle seance
  private int index=0;


public Seance getSeances(int n) {
	return seances[n];
}



public void setSeances(Seance seances) {
	this.seances[this.index++]=seances;
}



public String getDateEtHeuresActuelles() {
	return dateEtHeuresActuelles;
}



public void setDateEtHeuresActuelles(String dateEtHeuresActuelles) {
	this.dateEtHeuresActuelles = dateEtHeuresActuelles;
}



public String getCommentaire() {
	return commentaire;
}



public void setCommentaire(String commentaire) {
	this.commentaire = commentaire;
}



public int getIndex() {
	return index;
}



public void setIndex(int index) {
	this.index = index;
}



public  Service(String dateEtHeuresActuelles,String dateDebutService,String dateFinService,String heureDuService,String recurrenceHebdo,
		  int capaciteMaximale,long numeroDuProfessionnel,int codeDuService,String commentaire){
	  this.dateEtHeuresActuelles=dateEtHeuresActuelles;
	  this.dateDebutService=dateDebutService;
	  this.dateFinService=dateFinService;
	  this.heureDuService=heureDuService;
	  this.recurrenceHebdo=recurrenceHebdo;
	  this.capaciteMaximale=capaciteMaximale;
	  this.numeroDuProfessionnel=numeroDuProfessionnel;
	  this.codeDuService=codeDuService;
	  this.commentaire=commentaire;
	  this.seances=new Seance[30];
  }



public String getDateDebutService() {
	return dateDebutService;
}



public void setDateDebutService(String dateDebutService) {
	this.dateDebutService = dateDebutService;
}



public String getDateFinService() {
	return dateFinService;
}



public void setDateFinService(String dateFinService) {
	this.dateFinService = dateFinService;
}



public String getHeureDuService() {
	return heureDuService;
}



public void setHeureDuService(String heureDuService) {
	this.heureDuService = heureDuService;
}



public String getRecurrenceHebdo() {
	return recurrenceHebdo;
}



public void setRecurrenceHebdo(String recurrenceHebdo) {
	this.recurrenceHebdo = recurrenceHebdo;
}



public int getCapaciteMaximale() {
	return capaciteMaximale;
}



public void setCapaciteMaximale(int capaciteMaximale) {
	this.capaciteMaximale = capaciteMaximale;
}



public long getNumeroDuProfessionnel() {
	return numeroDuProfessionnel;
}



public void setNumeroDuProfessionnel(long numeroDuProfessionnel) {
	this.numeroDuProfessionnel = numeroDuProfessionnel;
}



public int getCodeDuService() {
	return codeDuService;
}



public void setCodeDuService(int codeDuService) {
	this.codeDuService = codeDuService;
}
	
}
