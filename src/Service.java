import java.util.ArrayList;
import java.util.HashMap;




public class Service {
  private MembreInscrit [] seances;
  private HashMap<Integer, Seance> SeancesList;
  private ArrayList<Integer> codeDesSeance;
  private String dateEtHeuresActuelles;
  private  String dateDebutService;
  private  String dateFinService;
  private  String heureDuService;
  private  String recurrenceHebdo;
  private String nom;
  private int capaciteMaximale;
  private long numeroDuProfessionnel;
  private int codeDuService;
  private String commentaire;
  //l'index sert a savoir a quelle case du array seances on insert une nouvelle seance
  private int index=0;
//initialiser le nombre de seance a 0 
  private int nombreDeSeance=0;
  //initialise le numero de la seance a 10 (nombre arbitraire)
  private int numeroDeSeance=10;
  
  
  
  public int getNombreDeSeance() {
	return nombreDeSeance;
}
public void setNombreDeSeance(int nombreDeSeance) {
	this.nombreDeSeance = nombreDeSeance;
}






public HashMap<Integer, Seance> getSeancesList() {
	return SeancesList;
}
public void setSeancesList(Integer n,Seance s) {
	SeancesList.put(n, s);
}
public MembreInscrit getSeances(int n) {
	return this.seances[n];
}
public MembreInscrit [] getSeances(){
	return this.seances;
}



public void setSeances(MembreInscrit seances) {
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



public  Service(String dateEtHeuresActuelles,String dateDebutService,String dateFinService,String heureDuService,String nom,String recurrenceHebdo,
		  int capaciteMaximale,long numeroDuProfessionnel,int codeDuService,String commentaire){
	  this.dateEtHeuresActuelles=dateEtHeuresActuelles;
	  this.dateDebutService=dateDebutService;
	  this.dateFinService=dateFinService;
	  this.heureDuService=heureDuService;
	  this.nom=nom;
	  this.recurrenceHebdo=recurrenceHebdo;
	  this.capaciteMaximale=capaciteMaximale;
	  this.numeroDuProfessionnel=numeroDuProfessionnel;
	  this.codeDuService=codeDuService;
	  this.commentaire=commentaire;
	  this.seances=new MembreInscrit[30];
	  this.SeancesList=new HashMap<Integer,Seance>();
	  this.codeDesSeance=new ArrayList<Integer>();
  }




public int getNumeroDeSeance() {
	return numeroDeSeance;
}
public void setNumeroDeSeance(int numeroDeSeance) {
	this.numeroDeSeance = numeroDeSeance;
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
public ArrayList<Integer> getCodeDesSeance() {
	return codeDesSeance;
}
public void setCodeDesSeance(ArrayList<Integer> codeDesSeance) {
	this.codeDesSeance = codeDesSeance;
}
public void setCodeDesSeance( int code) {
	this.codeDesSeance.add(code);
}
public void setSeancesList(HashMap<Integer, Seance> seancesList) {
	SeancesList = seancesList;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}

	
}
