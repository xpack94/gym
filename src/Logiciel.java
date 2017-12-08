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


/**
 * 
 * 
 *cette classe represente la classe qui contient toutes les fonctionalites que l'agent peut faire a travers 
 *son ordinateur 
 *elle fait appele a differentes classe comme GestionDeMembre et GestionDeProfessionnel et GestionDeService
 *pour faire les taches necessaire 
 * 
 *@param ctrDonne qui correspond a la base de donne ou toutes les informations se trouvent 
 *@version 3
 *
 *
 *
 */

public class Logiciel extends TimerTask {

	private CentreDeDonnes ctrDonne;
	
	public Logiciel(CentreDeDonnes c){
		this.ctrDonne=c;
	}
	
	
	public long ajouterMembre(String typeDuMembre){
		/**
		 * methode qui permet de faire l'ajout d'un nouveau membre 
		 * fait appele a la mehode creeMembre de la classe GestionDeMembre @see java.GYM.GestionDeMembre
		 * @param typeDuMembre de type String qui correspond a soit membre regulier soit professionnel
		 * @return long
		 */
			return new GestionDeMembre().creerUnMembre(typeDuMembre, this.ctrDonne);
		}
	
	
	//la methode qui permet de supprimer un membre du centre de donné
	public void supprimerMembre(long numeroUnique){
		/**
		 * methode qui permet de faire la suppression d'un membre de la base de donné 
		 * fait appele a supprimerMembre de la classe GestionDeMembre @see java.GYM.GestionDeMembre
		 * @papam numeroUnique de type long qui correspond a l'id du membre 
		 * @return rien 
		 */
		
	 new GestionDeMembre().supprimerMembre(numeroUnique, this.ctrDonne);
	}
	
	public void mettreAjourMembre(long num){
			/**
			 * permet de mettre a jour les informations d'un membre 
			 * fait appele a la methode mettreAJourMembre de la classe GestionDeMebre @see java.GYM.GestionDeMembre
			 * @param num de type long qui correspond au numeroUnique du membre 
			 * @return rien
			 */
		
			new GestionDeMembre().mettreAjourMembre(num, this.ctrDonne);
		}
	
	
		//la methode qui verifie si un memebre existe
		protected boolean verificationMembre(long numeroUnique){
			/**
			 * verifie si un membre est deja memebre dans le gym 
			 * fait appele a verificationMembre de la classe GestionDeMembre @see java.GYM.GestionDeMembre
			 * @param numeroUnique qui correspond a l'id du membre qu'on veut verifier 
			 * @return boolean
			 * 
			 */
			
		boolean b= new GestionDeMembre().verificationMembre(numeroUnique, this.ctrDonne);
		return b;
		}
		//methode qui retourne l'etat d'un memebre
		//soit actif ou suspendu
		private Membre chercherMembre(long numeroUnique){
			/**
			 * cherche un membre dans la base de donnée 
			 * @param numeroUnqie du membre qu'on veut trouver 
			 * @return un objet de type membre 
			 * 
			 */
			Membre m=this.ctrDonne.membres.get(numeroUnique);
			return m;
		}
		
		//la methode qui permet a un professionnel de donner un service 
		protected int DonnerService(long numeroUnique){
			/**
			 * methode qui permet au professionnel de donner un service 
			 * fait appele a la classe GestionDeService @see java.GYM.GestionDeService
			 * @param numeroUnique qui correspond a l'id du professionnel qui veut donne le service 
			 * @return un entier qui correspond au numero a 3 chiffre du service
			 */
			
			return new GestionDeService().DonnerService(numeroUnique, this.ctrDonne);
		}
		
		
		//la methode qui permet de supprimer une service 
		protected void removeService(int codeDuService){
			/**
			 * permet de faire la suppression d'un service 
			 * fait appele a la methode removeService de la classe GestionDeService @see java.GYM.GestionDeService
			 * @param codeDiService qui represente le numero a 3 chiffres du service a supprimer 
			 * @return rien 
			 */
			new GestionDeService().removeService(codeDuService, this.ctrDonne);
		}
		public void metterAjourService(int n){
			new GestionDeService().metterAjourService(n, this.ctrDonne);
		}
		
		//la methode qui permet au professionnel de consulter ses seance
		protected void  consulterSeance(int codeDuService){
			/**
			 * permet a un professionnel de consulter les inscritions a ses services 
			 * @param codeDuService de type int qui represente le code du service a consulter 
			 * @return rien
			 */
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
			/**
			 * permet a un membre de s'inscrire a un service existant 
			 * fait appele a la methode sinscrirAService de la classe GestionDeService @see java.GYM.GestionDeService
			 * @param numeroUnique du membre qui veut s'inscrir de type entier 
			 * @return long qui represente le numero du service dan lequele le membre c'est inscrit
			 */
			return new GestionDeService().sinscrirAService(numeroUnique, this.ctrDonne);
		}
		//cette methode permet de verifier si un membre n'est pas deja inscrit a un service 
		//pour eviter l'inscription une 2eme fois
	
	   //la methode qui permet de confirmer une presence a une seance donnée
		public void confirmationPresence(long numeroUnique){
			/**
			 * methode qui permet de faire la confirmation de Presence d'un membre 
			 * cette methode est executé par un professionnel 
			 * fait appele a la methode confirmationPresence de la classe GestionDeService @see java.GYM.GestionDeService
			 * @param numeroUnique qui correspond a l'id du professionnel 
			 * @return rien
			 */
			
			new GestionDeService().confirmationPresence(ctrDonne, numeroUnique);
			
		}
		//la methode qui verifie si un memebre est inscrit a une seance 
		private boolean verifierInscription(Service s,long numeroUnique){
			/**
			 * verifie si un membre est inscit a une seance , elle est appeler pour prevenir 
			 * les double inscription du meme membre a la meme seance 
			 * @param s de type Service 
			 * @param numeroUnique de type long 
			 * @return boolean 
			 */
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
			/**
			 * methode qui cree un fichier contenant les informations sur les professionnel a payé cette semaine 
			 * ainsi que le montant qui leurs ai attribué cette methode est executé automatiquement par le programme 
			 * chaque vendredi a 00:00 h
			 * @exception fileNoteFound 
			 * @exception parseException 
			 * @exception UnsupportedEncodingException
			 * @return rien
			 * 
			 */
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
			
			/**
			 * methode qui permet de generer un rapport contenant toutes les informations sur les professionnel
			 * a payé cette semaine , elle est executé par le gerant du gym 
			 * @exception FileNotFoundException
			 * @exception IOEXception 
			 * @return void
			 */
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
		
		public void listDesService(long numeroUnique){
			/**
			 * methode qui permet de crerr un fichier contenant la liste de tout les service 
			 * pour lesquels un certains membre est inscirt , ce fichier aura comme nom 
			 * le nom du membre en question ainsi que la date du jour 
			 * cette methode fait appele a la methode listeDesService de la classe 
			 * GestionDeMmbre @see java.GYM.GestionDeMembre 
			 * @param numeroUnique du membre pour les quelle on afficher la liste des service 
			 * @return void
			 */
			
			new GestionDeMembre().listDesService(ctrDonne, numeroUnique);
		}
		
		
		public void avisDePaiment(long numeroUnique){
			/**
			 * methode qui cree un fichier contenant toutes les informations sur un professionnel ainsi 
			 * que tout ses service et le paiment qui lui ai attribué 
			 * cette methode fait appele a la methode avisDePaiment de la classe GestionDeProfessionne
			 * @see java.GYM.GestionDeProfessionne
			 * @param numeroUnique 
			 * @return void
			 */
			new GestionDeProfessionnel().avisDePaiment(ctrDonne, numeroUnique);
		}
		
		
		//cette methode check les membres qui se sont inscrit au service cette semaine la 
		//et renvoie le frais totaux due au professionnel
		private Double verifierMembrePourCetteSemaine(Service s,String date){
			/**
			 * methode qui check les memebres qui sont inscrit au service cette semaine 
			 * et renvoie les frais totaux qui sont due au professionnel
			 * 
			 * @exception ParseException
			 * @param s de type service
			 * @param date de type String 
			 * @return Double representant le montant total
			 */
			
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Double fraisTotal=0.0;
	    	for(int i=0;i<s.getIndex();i++){
	    		//comparer la date dans laquelle le membre c'est inscrit a la seance avec la date actuelle - 7 jours 
	    		//pour savoir si le membre s'est inscrit au service cette semaine
	    			try {
	    				if(s.getIndex()>0){
	    					
	    					if(new GestionDeService().compareDatesByCompareTo(df, df.parse(s.getSeances(i).getDate_et_heure_actuelles().substring(0,10)), df.parse(date))>=1){
								fraisTotal+=50;
							}
	    				}
	    				
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			
	    		
	    	}
	    	return fraisTotal;
	    }
		
		//la methode qui verifie si l'email du membre et valide l'hors de la connection
		//la methode retourne le membre si trouvé
		public Membre verifierEmail(String email){
			/**
			 * methode qui verifie si un email (compte facebook ) existe dans le centre de donneés 
			 * @return un objet de type membre qui correspond au membre trouvé 
			 */
			
			long n=100000000;
			int counter=0;
			Membre p=ctrDonne.membres.get(n);
			while(counter<=ctrDonne.membres.size()-1){
				
				if(p!=null){
					counter++;
					if(p.getEmail().equals(email)){
						return p;
					}
				}else{
					p=ctrDonne.membres.get(++n);
				}
						
				
				
				}
		
			return null;
			
		}
		public Professionnel verifierEmailDeProfessionnel(String email){
			/**
			 * methode qui verifie l'email d'un professionne(compte facebook ) et check si 
			 * l'emal existe dans le centre de données 
			 * @return un objet de type professionnel
			 * 
			 */
			
			long n=100000000;
			int counter=0;
			Professionnel p=ctrDonne.professionnels.get(n);
			while(counter<=ctrDonne.professionnels.size()-1){
				if(p!=null ) {
					counter++;
					if(p.getEmail().equals(email)){
						return p;
					}
				}else{
					p=ctrDonne.professionnels.get(++n);
				}
				
				}
				
			
			
			return null;
			
		}


		@Override
		public void run() {
			/**
			 * permet de creer un thread qui est executé chaque fin de semaine a 00:00 h 
			 * pour generer le rapport qui contient toutes les information sur les professionnel a payé cette semaine
			 *@exception Exception
			 * @return void
			 */
			try{
				this.procedureComptable();
				
			}catch(Exception ex){
				System.out.println("error running thread " + ex.getMessage());
			}
			
		}
		//la methode qui cree un timer pour creer le fichier tef chaque vendredi a minuit
		 public void runTask(){
			 	/**
			 	 * methide qui cree un timer pour creer le fichier tef chque vendredi a minuit
			 	 * @return void
			 	 */
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
