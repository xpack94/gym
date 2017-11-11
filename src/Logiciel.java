import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;


public class Logiciel {

	private CentreDeDonnes ctrDonne;
	
	public Logiciel(CentreDeDonnes c){
		this.ctrDonne=c;
	}
	
	
	public long ajouterMembre(String typeDuMembre){
			Scanner sc= new Scanner(System.in);
		    this.ctrDonne.setId(this.ctrDonne.getId()+1);
		    long id =this.ctrDonne.getId();
		    System.out.println("entrer votre nom:");
		    String nom=sc.next();
		    System.out.println("entrer votre email");
		    String email=sc.next();
		    String status="actif";
		    if(typeDuMembre.equals("membreRegulier")){
		    	//inscrir un membre
		    	 System.out.println("voulez-vous payer les frais maintenant? oui/nom");
				    String reponse =sc.next();
				    //affecter au status actif si le membre decide de payer maintenant
				    if(reponse.equals("non") || reponse.equals("n")){
				    	status ="suspendu";
				    }
				    this.ctrDonne.membres.put( id, new MembreRegulier(id,nom,email,status));
		    }else{
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
		for(int i=0;i<p.getNombreDeServiceDonné();i++){
			this.ctrDonne.services.remove(p.getCodeDesServiceDonnés(i));
		}
		
		this.ctrDonne.professionnels.remove(numeroUnique);
		
		
		
	}
	
	
	public void mettreAjourMembre(Membre m){
	
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
			String reccurenceHebdo=sc.next();
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
			
			Service s=new Service(dateEtHeuresAct,dateDebutService,"12/30/2017","20:15","samedi",30,numeroUnique,serviceId,"sans commentaire");
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
		
		//la methode qui permet au professionnel de consulter ses seance
		protected Seance [] consulterSeance(int codeDuService){
			Service s=this.ctrDonne.services.get(codeDuService);
			return s.getSeances();
			
		}
		private void afficherToutLesServices(){
			int g=1000001;
			Service s=this.ctrDonne.services.get(g);
			System.out.println("les services disponible pour aujourd'hui sont : ");
			while(s!=null){
				//afficher tout les service dispobible pour aujourd'hui
				if(s.getDateDebutService().equals(new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()))){
					System.out.println("service numero "+s.getCodeDuService()+" avec le professionnel " +s.getNumeroDuProfessionnel());
				}
				s=this.ctrDonne.services.get(++g);
			}
		}
		
		public long sinscrirAService(long numeroUnique){
			if(this.verificationMembre(numeroUnique)){
				
			
			//s'inscrir au dernier service disponible 
		    this.afficherToutLesServices();
			Scanner sc =new Scanner(System.in);
			System.out.println("entrer le numero du service choisit");
			int num=sc.nextInt();
			Service service=this.ctrDonne.services.get(num);
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
				
			}else{
				String dateEtHeuresAct= new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar.getInstance().getTime());
				Seance seance=new Seance(dateEtHeuresAct, service.getDateDebutService()
						, service.getNumeroDuProfessionnel(),numeroUnique , service.getCodeDuService(),"");
				
				//ajouter les information relatif a cette senace dans le ArrayList du centre de donnée
				service.setSeances(seance);		
				
				System.out.println("vous etes maintenant inscrit");
				return service.getCodeDuService();
			}
			
			}
			
			return 0000000;
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
		
		
}
