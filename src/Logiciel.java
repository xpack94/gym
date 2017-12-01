import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class Logiciel extends TimerTask {

	private CentreDeDonnes ctrDonne;
	
	public Logiciel(CentreDeDonnes c){
		this.ctrDonne=c;
	}
	
	
	public long ajouterMembre(String typeDuMembre){
			return new GestionDeMembre().creerUnMembre(typeDuMembre, this.ctrDonne);
		}
	
	
	//la methode qui permet de supprimer un membre du centre de donné
	public void supprimerMembre(long numeroUnique){
	 new GestionDeMembre().supprimerMembre(numeroUnique, this.ctrDonne);
	}
	
	public void mettreAjourMembre(long num){
			new GestionDeMembre().mettreAjourMembre(num, this.ctrDonne);
		}
	
	
		//la methode qui verifie si un memebre existe
		protected boolean verificationMembre(long numeroUnique){
		boolean b= new GestionDeMembre().verificationMembre(numeroUnique, this.ctrDonne);
		return b;
		}
		//methode qui retourne l'etat d'un memebre
		//soit actif ou suspendu
		private Membre chercherMembre(long numeroUnique){
			Membre m=this.ctrDonne.membres.get(numeroUnique);
			return m;
		}
		
	
		
		//la methode qui permet a un professionnel de donner un service 
		protected int DonnerService(long numeroUnique){
			return new GestionDeService().DonnerService(numeroUnique, this.ctrDonne);
		}
		
		
	
		
		
		//la methode qui permet de supprimer une service 
		protected void removeService(int codeDuService){
			new GestionDeService().removeService(codeDuService, this.ctrDonne);
		}
		public void metterAjourService(int n){
			new GestionDeService().metterAjourService(n, this.ctrDonne);
		}
		
		//la methode qui permet au professionnel de consulter ses seance
		protected void  consulterSeance(int codeDuService){
			Service s=this.ctrDonne.services.get(codeDuService);
			if(s.getIndex()==0){
				System.out.println("aucun membre n'est inscrit a votre service");
			}else{
				System.out.println("les membre qui sont inscrit au service "+codeDuService+" sont :");
				for(int i=0;i<s.getIndex();i++){
					System.out.println("Membre numero "+s.getSeances(i).getNumero_du_membre());
				}
			}
			
			
			
		}
	
		
		public long sinscrirAService(long numeroUnique){
			return new GestionDeService().sinscrirAService(numeroUnique, this.ctrDonne);
		}
		//cette methode permet de verifier si un membre n'est pas deja inscrit a un service 
		//pour eviter l'inscription une 2eme fois
	
	   //la methode qui permet de confirmer une presence a une seance donnée
		public void confirmationPresence(int codeService,long numeroUnique){
			//l'agent cherche le service avec le code du service
			Service s=this.ctrDonne.services.get(codeService);
			
			//verifier si l'inscription du memebre au service est valide
			if(s!=null && verifierInscription(s,numeroUnique)){
				System.out.println("validé");
			}else if(s==null){
				//le memebre n'est pas inscrit au service
				System.out.println("le service n'existe pas ");
			}else{
				System.out.println("vous n'etes pas inscrit");
			}

			
		}
		//la methode qui verifie si un memebre est inscrit a une seance 
		private boolean verifierInscription(Service s,long numeroUnique){
			for(int i=0;i<s.getCapaciteMaximale();i++){
				if((s.getSeances(i)!=null) && s.getSeances(i).getNumero_du_membre()==numeroUnique){
					return true;
				}
			}
			return false;
			
		}
		
		//la methode qui genere le fichier tef avec les informations necessaire 
		//le fichier tef est generer dans le meme dossier que le projet 
		public void procedureComptable(){
			PrintWriter writer;
			try {
				writer = new PrintWriter("tef.txt", "UTF-8");
				//sauvegarder les numero unique des professionnel pour ne pas repeter le meme professionnel plusieur fois
				//si ce dernier donne plus qu'un service
				ArrayList<Long> numeroDesProfessionnels=new ArrayList<Long>();
				int id=1000001;
				Service s =this.ctrDonne.services.get(id);
				
				String dateEtHeuresAct= new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				while(s!=null){
					try {
						if(!numeroDesProfessionnels.contains(s.getNumeroDuProfessionnel())){
							//s'assurer que la date fin du service n'est pas passé 
							if(new GestionDeService().compareDatesByCompareTo(df,df.parse(dateEtHeuresAct), df.parse(s.getDateFinService()))==-1){
								Professionnel p=this.ctrDonne.professionnels.get(s.getNumeroDuProfessionnel());
								writer.println("nom du professionnel : "+p.getNom());
								writer.println("numero du professionnel : "+p.getNumeroUnique());
								Calendar calendar = Calendar.getInstance(); 
					    		calendar.add(Calendar.DAY_OF_MONTH, -7);
					    		String sevenDaysAgo=new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime());
				
								writer.println("montant a transferé : "+this.verifierMembrePourCetteSemaine(s, sevenDaysAgo) +"$");
								writer.println("-----------------------");
								numeroDesProfessionnels.add(s.getNumeroDuProfessionnel());
							}
						}
						
					
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					s=this.ctrDonne.services.get(++id);
				}
				
				numeroDesProfessionnels=null;
				writer.close();
				//appeler la methode genererRapport pour generer le rapport de synthese a partir du fichier tef
				this.genererRapport();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void genererRapport(){
			
			try(BufferedReader br = new BufferedReader(new FileReader("tef.txt"))) {
				ArrayList<Long> pro=new ArrayList<Long>();
			    StringBuilder sb = new StringBuilder();
			    String line = br.readLine();
			    double montantTotal=0;
			    int nombreDeProf=0,nombreTotalDeService=0;
			    while (line != null) {
			    	if(line.contains("numero du professionnel")){
			    		//ajouter tout les professionnel dans la list pro
			    		pro.add(Long.parseLong(line.substring(26)));
			    	}else if(line.contains("montant a transferé")){
			    		
			    		montantTotal+=Double.parseDouble(line.substring(22,line.length()-1));
			    	}
			    	
			        sb.append(line);
			        sb.append(System.lineSeparator());
			        line = br.readLine();
			    }
			   
			    String everything = sb.toString();
			    System.out.println("impression du rapport de synthese.......");
			    //afficher tout les professionnel avec le nombre total de leur dervices et frais 
			    for(int i=0;i<pro.size();i++){
			    	Professionnel p=this.ctrDonne.professionnels.get(pro.get(i));
			    	System.out.println("nom : "+p.getNom());
			    	System.out.println("numero : "+p.getNumeroUnique());
			    	System.out.println("nombre de services données :"+p.getNombreDeServiceDonné());
			    	//compter les frais totale du professionnel
			    	double fraisTotal=0;
			    	for(int j=0;j<p.getCodeDesServiceDonnés().size();j++){
			    		Service s= this.ctrDonne.services.get(p.getCodeDesServiceDonnés(j));
			    		//fare la date d'aujourdh'ui -7 jours pour trouver les membres qui ont rejoint le service
			    		//cette semaine la
			    		Calendar calendar = Calendar.getInstance(); // this would default to now
			    		calendar.add(Calendar.DAY_OF_MONTH, -7);
			    		String sevenDaysAgo=new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime());
			    		fraisTotal+= this.verifierMembrePourCetteSemaine(s, sevenDaysAgo);
			    		
			    	}
			    	System.out.println("les frais totale sont: "+fraisTotal+"$" );
			    	montantTotal+=fraisTotal;
			    	nombreDeProf++;
			    	nombreTotalDeService+=p.getCodeDesServiceDonnés().size();
			    	
			    	
			    	//le nombre totale de professionnel qui ont fournis de service cette semaine
			    	//le nombre totale de service 
			    	//le nombre totale des frais 
			    	
			    }
			    System.out.println("nombre de professionnel qui ont fournis de service cette semaine : "+nombreDeProf);
			    System.out.println("nombre totale de service : "+nombreTotalDeService);
			    System.out.println("nombre totale des frais : "+montantTotal+"$");
			    
			    
			    
			    
			    
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//cette methode check les membre qui se sont inscrit au service cette semaine la 
		//et renvoie le frais totale due au professionnel
		private Double verifierMembrePourCetteSemaine(Service s,String date){
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Double fraisTotal=0.0;
	    	for(int i=0;i<s.getIndex();i++){
	    		//comparer la date dans laquelle le membre c'est inscrit a la seance avec la date actuelle - 7 jours 
	    		//pour savoir si le membre s'est inscrit au service cette semaine
	    			try {
	    				if(s.getIndex()>0){
	    					
	    					if(new GestionDeService().compareDatesByCompareTo(df, df.parse(s.getSeances(i).getDate_et_heure_actuelles().substring(0,10)), df.parse(date))>=1){
								fraisTotal+=s.getFraisDuService();
							}
	    				}
	    				
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			
	    		
	    	}
	    	return fraisTotal;
	    }


		@Override
		public void run() {
			try{
				this.procedureComptable();
				
			}catch(Exception ex){
				System.out.println("error running thread " + ex.getMessage());
			}
			
		}
		//la methode qui cree un timer pour creer le fichier tef chaque vendredi a minuit
		 public void runTask(){

		        Calendar calendar = Calendar.getInstance();
		        calendar.set(
		           Calendar.DAY_OF_WEEK,
		           Calendar.FRIDAY
		        );
		        calendar.set(Calendar.HOUR_OF_DAY, 23);
		        calendar.set(Calendar.MINUTE, 59);
		        calendar.set(Calendar.SECOND, 59);
		        calendar.set(Calendar.MILLISECOND, 0);

		        System.out.println("firing now ...");

		        Timer time = new Timer(); // Instantiate Timer Object

		        //commencement du processus qui cree le fichier tef le vendredi a 23:59:59
		        //avec repetition chaque 168 heures donc chaque vendredi 
		        time.schedule(this, calendar.getTime(), TimeUnit.HOURS.toMillis(168));
		}
		
		
		
}
