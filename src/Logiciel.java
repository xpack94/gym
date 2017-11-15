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
			Scanner sc= new Scanner(System.in);
		    long id =0;

		    System.out.println("entrer votre nom:");
		    String nom=sc.next();
		    System.out.println("entrer votre email");
		    String email=sc.next();
		    String status="actif";
		    if(typeDuMembre.equals("membreRegulier")){
		    	//inscrir un membre
		    	 System.out.println("payer les frais d'adhesions? oui/non");
				    String reponse =sc.next();
				    //affecter au status actif si le membre decide de payer maintenant
				    if(reponse.equals("non") || reponse.equals("n")){
				    	return id;
				    }else{
				    	 this.ctrDonne.setId(this.ctrDonne.getId()+1);
				    	 id=this.ctrDonne.getId();
				    	 this.ctrDonne.membres.put( id, new MembreRegulier(id,nom,email,status));
				    }
				   
		    }else{
		    	 this.ctrDonne.setId(this.ctrDonne.getId()+1);
		    	 id=this.ctrDonne.getId();
		    	//inscrir un professionnel
		    	this.ctrDonne.professionnels.put(id, new Professionnel(id, nom, email));
		    }
		    
			return id;
		}
	
	
	//la methode qui permet de supprimer un membre du centre de donné
	public void supprimerMembre(long numeroUnique){
		this.ctrDonne.membres.remove(numeroUnique);
		Professionnel p=this.ctrDonne.professionnels.get(numeroUnique);
		//supprimer les service donné par le professionnel
		try{
			for(int i=0;i<p.getNombreDeServiceDonné();i++){
				this.ctrDonne.services.remove(p.getCodeDesServiceDonnés(i));
			}
		}catch(Exception e){
			
		}
		
		
		this.ctrDonne.professionnels.remove(numeroUnique);
		
		
		
	}
	
	
	public void mettreAjourMembre(long num){
	    Membre m=this.ctrDonne.membres.get(num);
	    if(m==null){
	    	System.out.println("le numero est invalide");
	    	return;
	    }
		
		System.out.println("1:mettre a jour l'email");
		System.out.println("2:payer les frais mensuels");
		Scanner sc=new Scanner(System.in);
		String rep=sc.next();
		if(rep.equals("1")){
			System.out.println("entrer votre nouvelle adresse email");
			String email=sc.next();
			m.setEmail(email);
			
		}else{
			//le membre choisi de payer les frais mensuels
			if((((MembreRegulier)m).getEtat()).equals("suspendu")){
				((MembreRegulier)m).setEtat("actif");
			}
		}
		
		}
	
	
		//la methode qui verifie si un memebre existe
		protected boolean verificationMembre(long numeroUnique){
			Membre m=this.ctrDonne.membres.get(numeroUnique);
			Membre p =this.ctrDonne.professionnels.get(numeroUnique);
			if(m!=null || p!=null){
				return true;
			}
			return false;
			
		}
		//methode qui retourne l'etat d'un memebre
		//soit actif ou suspendu
		private Membre chercherMembre(long numeroUnique){
			Membre m=this.ctrDonne.membres.get(numeroUnique);
			return m;
		}
		
	
		
		//la methode qui permet a un professionnel de donner un service 
		protected int DonnerService(long numeroUnique){
			if(!this.verificationMembre(numeroUnique)){
				//le professionnel n'est pas inscrit 
				//long number=this.ajouterMembre("professionnel");
				System.out.println("le numero n'est pas correct");
			}
			//cree le service avec les informations necessaire 
			this.ctrDonne.setServiceId(this.ctrDonne.getServiceId()+1);
			int serviceId= this.ctrDonne.getServiceId();
			String dateEtHeuresAct= new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar.getInstance().getTime());
			//String dateDebutService=new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
			Scanner sc=new Scanner(System.in);
			System.out.println("entrer la date du debut du service sous forme dd-MM-yyyy");
			String dateDebutService=sc.next();
			System.out.println("entrer la date fin du service sous forme dd-MM-yyyy");
			String dateFinService=sc.next();
			System.out.println("entrer l'heure du service sous forme HH:MM");
			String heureDuService=sc.next();
			System.out.println("entrer la reccurence hebdomadaire du service");
			sc.nextLine();
			String reccurenceHebdo=sc.nextLine();
			System.out.println("entrer les frais du service (ne depasse pas 100.00$)");
			double frais =sc.nextDouble();
			System.out.println("entrer un commentaire sur le service (au plus 100 caractaires)");
			String commentaire=sc.next();
			
			if(!this.isValidFormat(dateDebutService)){
				System.out.println("le format de date debut du service n'est pas valide");
				return 0;
			}
			if(!this.isValidFormat(dateFinService)){
				System.out.println("le format de date fin du service n'est pas valide");
				return 0;
			}
			if(!this.isValidHourFormat(heureDuService)){
				System.out.println("l'heure du service entrée n'est pas valide");
				return 0;
			}
			if(frais >100){
				System.out.println("le frais du service ne doivent pas depasser 100$ ");
				return 0;
			}
			if(commentaire.length()>100){
				System.out.println("le commentaire a plus que 100 caractaire");
				return 0;
			}
			
			
			//faire la comparaison des dates entrées par le professionnel
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			
			try {
				
				if(this.compareDatesByCompareTo(df, df.parse(dateEtHeuresAct.substring(0,10)), df.parse(dateDebutService)) ==1){
					System.out.println("la date du debut de service est deja passé et donc elle est invalid");
					return 0;
				}
				if(this.compareDatesByCompareTo(df, df.parse(dateEtHeuresAct.substring(0,10)), df.parse(dateFinService)) ==1){
					System.out.println("la date fin du service est deja passé et donc elle est invalid");
					return 0;
				}
				if(this.compareDatesByCompareTo(df, df.parse(dateDebutService), df.parse(dateFinService)) ==1){
					System.out.println("la date fin du service ne peut pas etre avant la date debut du service");
					return 0;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("erreur");
				e.printStackTrace();
			}
			
			
			Service s =new Service(dateEtHeuresAct,dateDebutService,dateFinService,heureDuService,reccurenceHebdo,30,numeroUnique,serviceId,frais,commentaire);
			this.ctrDonne.services.put( s.getCodeDuService(), s);
			//ajouter le service creé dans l'array codeDesServiceDonnés de la class professionnel
			Professionnel p =this.ctrDonne.professionnels.get(numeroUnique);
			ArrayList <Integer> list=p.getCodeDesServiceDonnés();
			list.add(serviceId);
			p.setCodeDesServiceDonnés(list);
			p.setNombreDeServiceDonné(p.getNombreDeServiceDonné()+1);
			
			return serviceId;
		}
		
		
		public  int compareDatesByCompareTo(DateFormat df, java.util.Date oldDate, java.util.Date newDate) {
	        
	        if (oldDate.compareTo(newDate) == 0) {  
	            return 0;
	        }
	        if (oldDate.compareTo(newDate) < 0) {
	          
	            return -1;
	        }
	        
	        return 1;
	    }


	   //methode qui verifie si l'heure du service est valide
		private static boolean isValidHourFormat(String value){
			if(value.matches("([0-9]{2}):([0-9]{2})")){
				return true;
			}
			return false;
		}
	
		
		//methode qui verifie si la date entrée par l'utilisateur correspond au format voulu
		private static boolean isValidFormat( String value) {
			 boolean checkFormat;

			 if (value.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")){
				 return true;
			 }
			   
			 
			 return false;
		    }
		
		
		//la methode qui permet de supprimer une service 
		protected void removeService(int codeDuService){
		Service s=this.ctrDonne.services.get(codeDuService);
		if(s!=null){
			this.ctrDonne.services.remove(codeDuService);
			System.out.println("le service a bien eté supprimé ");
		}else{
			System.out.println("le service n'existe pas ");
		}
		
		
		}
		public void metterAjourService(int n){
			Service s=this.ctrDonne.services.get(n);
			if(s==null){
				System.out.println("le code du service est invalide");
				return;
			}
			System.out.println("que voulez-vous mettre a jour?");
			System.out.println("1:date fin du service");
			System.out.println("2:heure du service");
			System.out.println("3:recurrence hebdomadaire du service");
			System.out.println("4:frais du service");
			Scanner sc=new Scanner(System.in);
			
			
			int rep=Integer.parseInt(sc.next());
			switch (rep){
			case 1:
				System.out.println("veuillez entrer la nouvelle date de fin du service sous forme dd-MM-yyyy");
				String dateFin=sc.next();
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				if(!isValidFormat(dateFin)   ){
					System.out.println("la date que vous avez entrée ne correspond pas a la bonne forme");
					
				}else{
					
					String dateEtHeuresAct= new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar.getInstance().getTime());
					
						try {
							if(this.compareDatesByCompareTo(df, df.parse(s.getDateDebutService().substring(0,10)), df.parse(dateFin)) ==1
									|| this.compareDatesByCompareTo(df, df.parse(dateEtHeuresAct.substring(0,10)), df.parse(dateFin)) ==1){
								System.out.println("la nouvelle date n'est pas correcte");
							}else{
								s.setDateFinService(dateFin);
								System.out.println("la nouvelle date a eté mise a jour");
							}
							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				}
				break;
			case 2:
				System.out.println("veuillez entrer le nouveau heure du service sous forme HH:MM");
				String heure=sc.next();
				if(!isValidHourFormat(heure)){
					System.out.println("l'heure entrée ne correspond pas a la bonne forme");
					
				}else{
					s.setHeureDuService(heure);
					System.out.println("l'heure du service a eté mis a jour");
				}
				break;
			case 3:
				System.out.println("veuillez entrer la nouvelle reccurence hebdomadaire du service");
				String reccurence=sc.nextLine();
				s.setRecurrenceHebdo(reccurence);
				System.out.println("la reccurence hebdomadaire du service a eté mise a jour");
				
				break;
			
			case 4:
				System.out.println("veuillez-entrer la nouvelle somme des frais du service (mois que 100$)");
				String frais=sc.next();
				if(Double.parseDouble(frais) >100){
					System.out.println("les frais ne doivent pas depasser 100$");
				}else{
					s.setFraisDuService(Double.parseDouble(frais));
					System.out.println("les frais ont eté mis a jour");
				}
			default:
				break;
			}
			
		}
		
		//la methode qui permet au professionnel de consulter ses seance
		protected Seance [] consulterSeance(int codeDuService){
			Service s=this.ctrDonne.services.get(codeDuService);
			return s.getSeances();
			
		}
		private int afficherToutLesServices(){
			int g=1000001;
			int nombreDeServiceDispo=0;
			Service s=this.ctrDonne.services.get(g);
			System.out.println("les services disponible pour aujourd'hui sont : ");
			while(s!=null){
				//afficher tout les service dispobible pour aujourd'hui
				if(s.getDateDebutService().equals(new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()))){
					nombreDeServiceDispo++;
					System.out.println("service numero "+s.getCodeDuService()+" avec le professionnel " +s.getNumeroDuProfessionnel());
				}
				s=this.ctrDonne.services.get(++g);
			}
			return nombreDeServiceDispo;
		}
		
		public long sinscrirAService(long numeroUnique){
			if(this.verificationMembre(numeroUnique)){
				
			
			//s'inscrir au dernier service disponible 
		    int nombreDeServiceDispo=this.afficherToutLesServices();
		    if(nombreDeServiceDispo==0){
		    	System.out.println("aucun service n'est disponible pour aujourdh'ui");
		    	return 0;
		    }
			Scanner sc =new Scanner(System.in);
			System.out.println("entrer le numero du service choisit");
			int num=sc.nextInt();
			Service service=this.ctrDonne.services.get(num);
			if(service==null){
				System.out.println("le numero du service entrée est invalide");
				return 0;
			}
			//tester si la capacité maximale n'est pas atteinte
			while(service.getIndex()==31){
				System.out.println("il y'a plus de place disponible pour s'inscrir au service");
				System.out.println("essayer un autre service!");
				this.afficherToutLesServices();
				System.out.println("entrer le numero du service choisit");
				int number=sc.nextInt();
				service=this.ctrDonne.services.get(number);
				
			}
			//verifier si le membre n'est pas suspendu
			if( ( (MembreRegulier)this.ctrDonne.membres.get(numeroUnique)).getEtat().equals("suspendu")){
				System.out.println("vous ne pouvez pas vous inscrir car vous etes suspendu");
				
			}else if(!verifierSiDansService(service,numeroUnique)){
				String dateEtHeuresAct= new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar.getInstance().getTime());
				Seance seance=new Seance(dateEtHeuresAct, service.getDateDebutService()
						, service.getNumeroDuProfessionnel(),numeroUnique , service.getCodeDuService(),"");
				
				//ajouter les information relatif a cette senace dans le ArrayList du centre de donnée
				service.setSeances(seance);		
				
				System.out.println("vous etes maintenant inscrit");
				return service.getCodeDuService();
			}else{
				System.out.println("vous etes deja inscrit dans ce service ");
			}
			
			}else{
				System.out.println("le numero entrée est invalide");
			}
			
			return 0000000;
		}
		//cette methode permet de verifier si un membre n'est pas deja inscrit a un service 
		//pour eviter l'inscription une 2eme fois
		private boolean verifierSiDansService(Service s,long num){
			for(int i=0;i<s.getIndex();i++){
				if(s.getSeances(i).getNumero_du_membre()==num){
					return true;
				}
			}
			return false;
		}
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
							if(this.compareDatesByCompareTo(df,df.parse(dateEtHeuresAct), df.parse(s.getDateFinService()))==-1){
								Professionnel p=this.ctrDonne.professionnels.get(s.getNumeroDuProfessionnel());
								writer.println("nom du professionnel : "+p.getNom());
								writer.println("numero du professionnel : "+p.getNumeroUnique());
								writer.println("montant a transferé : "+"100" +"$");
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
	    					
	    					if(this.compareDatesByCompareTo(df, df.parse(s.getSeances(i).getDate_et_heure_actuelles().substring(0,10)), df.parse(date))>=1){
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
