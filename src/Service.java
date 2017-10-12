


public class Service {
  private  String dateDebutService;
  private  String dateFinService;
  private  String heureDuService;
  private  String recurrenceHebdo;
  private int capaciteMaximale;
  private long numeroDuProfessionnel;
  private long codeDuService;



public  Service(String dateDebutService,String dateFinService,String heureDuService,String recurrenceHebdo,
		  int capaciteMaximale,long numeroDuProfessionnel,long codeDuService){
	  
	  this.dateDebutService=dateDebutService;
	  this.dateFinService=dateFinService;
	  this.heureDuService=heureDuService;
	  this.recurrenceHebdo=recurrenceHebdo;
	  this.capaciteMaximale=capaciteMaximale;
	  this.numeroDuProfessionnel=numeroDuProfessionnel;
	  this.codeDuService=codeDuService;
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



public long getCodeDuService() {
	return codeDuService;
}



public void setCodeDuService(long codeDuService) {
	this.codeDuService = codeDuService;
}
	
}
