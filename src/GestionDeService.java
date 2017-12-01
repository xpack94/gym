import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


public class GestionDeService {

	//la methode qui permet a un professionnel de donner un service 
			protected int DonnerService(long numeroUnique , CentreDeDonnes ctrDonne){
				if(!new GestionDeMembre().verificationMembre(numeroUnique, ctrDonne)){
					//le professionnel n'est pas inscrit 
					//long number=this.ajouterMembre("professionnel");
					System.out.println("le numero n'est pas correct");
				}
				//cree le service avec les informations necessaire 
				ctrDonne.setServiceId(ctrDonne.getServiceId()+1);
				int serviceId= ctrDonne.getServiceId();
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
				ctrDonne.services.put( s.getCodeDuService(), s);
				//ajouter le service creé dans l'array codeDesServiceDonnés de la class professionnel
				Professionnel p =ctrDonne.professionnels.get(numeroUnique);
				ArrayList <Integer> list=p.getCodeDesServiceDonnés();
				list.add(serviceId);
				p.setCodeDesServiceDonnés(list);
				p.setNombreDeServiceDonné(p.getNombreDeServiceDonné()+1);
				
				return serviceId;
			}
			
			
			
			protected void removeService(int codeDuService,CentreDeDonnes ctrDonne){
				Service s=ctrDonne.services.get(codeDuService);
				if(s!=null){
					ctrDonne.services.remove(codeDuService);
					System.out.println("le service a bien eté supprimé ");
				}else{
					System.out.println("le service n'existe pas ");
				}
				
				
				}
			
			
			public void metterAjourService(int n,CentreDeDonnes ctrDonne){
				Service s=ctrDonne.services.get(n);
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
			
			
			public long sinscrirAService(long numeroUnique,CentreDeDonnes ctrDonne){
				if(new GestionDeMembre(). verificationMembre(numeroUnique,ctrDonne)){
					
				
				//s'inscrir au dernier service disponible 
			    int nombreDeServiceDispo=this.afficherToutLesServices(ctrDonne);
			    if(nombreDeServiceDispo==0){
			    	System.out.println("aucun service n'est disponible pour aujourdh'ui");
			    	return 0;
			    }
				Scanner sc =new Scanner(System.in);
				System.out.println("entrer le numero du service choisit");
				int num=sc.nextInt();
				Service service=ctrDonne.services.get(num);
				if(service==null){
					System.out.println("le numero du service entrée est invalide");
					return 0;
				}
				//tester si la capacité maximale n'est pas atteinte
				while(service.getIndex()==31){
					System.out.println("il y'a plus de place disponible pour s'inscrir au service");
					System.out.println("essayer un autre service!");
					this.afficherToutLesServices(ctrDonne);
					System.out.println("entrer le numero du service choisit");
					int number=sc.nextInt();
					service=ctrDonne.services.get(number);
					
				}
				//verifier si le membre n'est pas suspendu
				if(service.getNumeroDuProfessionnel()==numeroUnique){
					System.out.println("vous ne pouvez pas vous inscrir a votre propre service");
				}else if(( (MembreRegulier)ctrDonne.membres.get(numeroUnique)).getEtat().equals("suspendu")){
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
			
			private int afficherToutLesServices(CentreDeDonnes ctrDonne){
				int g=101;
				int nombreDeServiceDispo=0;
				Service s=ctrDonne.services.get(g);
				System.out.println("les services disponible pour aujourd'hui sont : ");
				while(s!=null){
					//afficher tout les service dispobible pour aujourd'hui
					if(s.getDateDebutService().equals(new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()))){
						nombreDeServiceDispo++;
						System.out.println("service numero "+s.getCodeDuService()+" avec le professionnel " +s.getNumeroDuProfessionnel());
					}
					s=ctrDonne.services.get(++g);
				}
				return nombreDeServiceDispo;
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

			
			private boolean verifierSiDansService(Service s,long num){
				for(int i=0;i<s.getIndex();i++){
					if(s.getSeances(i).getNumero_du_membre()==num){
						return true;
					}
				}
				return false;
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
	
}
