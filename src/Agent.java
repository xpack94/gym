import java.util.HashMap;
import java.util.Scanner;


public class Agent {

	
	
	
  
	
	private CentreDeDonnes ctrDonne;
	public Agent(CentreDeDonnes c){
		this.ctrDonne=c;
	}
	
	public long creeMembre(){
		    this.ctrDonne.setId(this.ctrDonne.getId()+1);
		    long id =this.ctrDonne.getId();
		    this.ctrDonne.membres.put( id, new Membre(id,"actif"));
			return id;
		}
		//la methode qui verifie si un memebre existe
		protected boolean verificationMembre(long numeroUnique){
			Membre m=this.ctrDonne.membres.get(numeroUnique);
			if(m!=null){
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
		public void visite(long numeroUnique){
			Membre m=this.chercherMembre(numeroUnique);
			if(m!=null && m.getEtat().equals("actif")){
				System.out.println("Validé");
			}else{
				//soit le membre est suspendu soit il n'existe pas
				if(m.getEtat().equals("suspendu")){
					System.out.println("membre suspendu");
				}else{
					System.out.println("numero invalide");
				}
				
			}
		}
		
		//la methode qui permet a une professionnel de donner un service 
		protected int DonnerService(long numeroUnique){
			if(!this.verificationMembre(numeroUnique)){
				//le professionnel n'est pas inscrit 
				long number=this.creeMembre();
			}
			//cree le service avec les informations necessaire 
			this.ctrDonne.setServiceId(this.ctrDonne.getServiceId()+1);
			int serviceId= this.ctrDonne.getServiceId();
			Service s=new Service("10/5/2017","10/6/2017","20h","samedi",30,numeroUnique,serviceId);
			this.ctrDonne.services.put( s.getCodeDuService(), s);
			return serviceId;
		}
		//la methode qui permet au professionnel de consulter ses seance
		protected Service consulterSeance(int codeDuService){
			Service s=this.ctrDonne.services.get(codeDuService);
			return s;
			
		}
		public long sinscrirAService(long numeroUnique){
			if(this.verificationMembre(numeroUnique)){
				
			
			//s'inscrir au dernier service disponible 
			int g=1000001;
			Service s=this.ctrDonne.services.get(g);
			System.out.println("les services disponible pour aujourd'hui sont : ");
			while(s!=null){
				//afficher tout les service dispobible pour aujourd'hui
				if(s.getDateDebutService().equals("10/5/2017")){
					System.out.println("service numero "+s.getCodeDuService()+" avec le professionnel " +s.getNumeroDuProfessionnel());
				}
				s=this.ctrDonne.services.get(++g);
			}
			Scanner sc =new Scanner(System.in);
			System.out.println("entrer le numero du service choisit");
			int num=sc.nextInt();
			Service service=this.ctrDonne.services.get(num);
			SeanceInscrit seance=new SeanceInscrit("10/5/2017", service.getDateDebutService()
					, service.getNumeroDuProfessionnel(),numeroUnique , service.getCodeDuService());
			
			//ajouter les information relatif a cette senace dans le ArrayList du centre de donnée
			this.ctrDonne.inscrit.add(seance);			
			
			System.out.println("vous etes maintenant inscrit");
			return service.getCodeDuService();
			}
			
			return 0000000;
		}
	   //la methode qui permet de confirmer une presence a une seance donnée
		public void confirmationPresence(int codeService,long numeroUnique){
			//l'agent cherche le service avec le code du service
			Service s=this.ctrDonne.services.get(codeService);
			
			//verifier si l'inscription du memebre au service est valide
			if(verifierInscription(codeService,numeroUnique)){
				System.out.println("validé");
			}else{
				//le memebre n'est pas inscrit au service
				System.out.println("vous n'etes pas inscrit");
			}

			if(s!=null){
				//le code du service est valide 
			
			}else{
				System.out.println("le code du service n'est pas valide");
			}
			
		}
		//la methode qui verifie si un memebre est inscrit a une seance 
		private boolean verifierInscription(int codeService,long numeroUnique){
			
			for(int i=0;i<this.ctrDonne.inscrit.size();i++){
				if(this.ctrDonne.inscrit.get(i).getCode_du_service()==codeService && 
						this.ctrDonne.inscrit.get(i).getNuméro_du_membre()==numeroUnique){
					return true;
				}
			}
			return false;
		}
		
		public static void main(String [] Args){
			
			Agent a =new Agent(new CentreDeDonnes());
			
			//cree un memebre
			a.creeMembre();
			//cree un professionnel
			a.creeMembre();
			//verifier le membre 1
			a.verificationMembre(1);
			//le professionel donne une service
			a.DonnerService(2);
			//le professionnel consulte la seance
			//a.consulterSeance(2);
			
			a.sinscrirAService(1);
			//a.visite(1);
			a.confirmationPresence(1000001, 1);
			
		}
		
}
