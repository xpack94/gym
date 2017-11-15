import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;



public class Main {

	/**
	 * @param args
	 */
	
	public void choixPris(Main m,Logiciel a,int choix){
		Scanner sc =new Scanner(System.in);
		switch (choix){
		case 1:
			    System.out.println("choisiser une des options");
			    System.out.println("etes-vous?");
			    System.out.println("1: membre regulier");
			    System.out.println("2: un professionnel");
			    String choix1=sc.next();
			    if(choix1.equals("1")){
			    	long n=a.ajouterMembre("membreRegulier");
			    	if(n==0){
			    		System.out.println("l'inscription est annulé car les frais d'adhesion ne sont pas payés");
			    	}else{
			    		System.out.println(" vous etes maintenant inscrit votre numero est: "+n);
			    	}
			    	
			    }else{
			    	System.out.println(" vous etes maintenant inscrit votre numero est: "+a.ajouterMembre("professionnel"));
			    }
			    
			
			break;
		case 2:
			
			System.out.println("veuillez entrer votre numero");
			long num = sc.nextLong();
			a.sinscrirAService(num);
			
			break;
		case 3:
			long id=0;
			System.out.println("etes vous inscrit? oui/Non");
			String reponse =sc.nextLine();
			if((reponse.equals("Non")) ||( reponse.equals("n")) || (reponse.equals("non"))){
			 //le membre n'est pas inscrit
			 id=a.ajouterMembre("professionnel");
			 System.out.println("vous etes maintenant inscrit votre numero est :"+id);
			}else{
				System.out.println("veuillez entrer votre numero");
				id=sc.nextLong();
			}
			int codeS=a.DonnerService(id);
			if(codeS==0){
				System.out.println("le service n'a pas eté crée");
			}else{
				System.out.println("le service a ete cree avec le code "+codeS);
			}
			
			
			break;
		case 4:
			System.out.println("entrer le code du service que vous voulez consulter");
			int code=sc.nextInt();
			a.consulterSeance(code);
			break;
		case 5:
			System.out.println("veuillez entrer le code de la seance a 7 chiffres");
			int codeSeance=sc.nextInt();
			System.out.println("veuillez entrer votre numero");
			long numeroUnique=sc.nextLong();
			a.confirmationPresence(codeSeance, numeroUnique);
			break;
		case 6:
			System.out.println("entrer votre numero ");
			long numero=sc.nextLong();
			a.supprimerMembre(numero);
			System.out.println("le membre est supprimé");
			break;
		case 7:
			System.out.println("entrer votre numero");
			long newNum=sc.nextLong();
			a.mettreAjourMembre(newNum);
			break;
		case 8:
			System.out.println("entrer le code du service");
			a.supprimerMembre(sc.nextInt());
			break;
		case 9:
			System.out.println("entrer le code du service");
			a.metterAjourService(sc.nextInt());
			break;
		case 10:
			a.procedureComptable();
			break;
			
		case 11:
			a.genererRapport();
			break;
			
		case 12:
			System.exit(0);
			
		}
		try        
		{
		    Thread.sleep(4000);
		} 
		catch(InterruptedException ex) 
		{
		    Thread.currentThread().interrupt();
		}
		faireChoix(m, a);
		
	}
	
	public void faireChoix(Main m, Logiciel a){
		
		
		
		

		
		System.out.println("Bienvenu a GYM");
		System.out.println("que voulez-vous faire?");
		System.out.println("1:inscripton");
		System.out.println("2:s'inscrire a une séance");
		System.out.println("3:donner service");
		System.out.println("4:consulter inscription");
		System.out.println("5:confirmer presence");
		System.out.println("6:supprimer membre");
		System.out.println("7:mettre a jour membre");
		System.out.println("8:supprimer service ");
		System.out.println("9:mettre a jour service");
		System.out.println("10:procedure comptable");
		System.out.println("11:generer le rapport");
		System.out.println("12:quitter");
		Scanner sc =new Scanner(System.in);
		int choix=sc.nextInt();
		m.choixPris(m,a, choix);
	}
	
	public static void main(String[] args) {
		
		
		Main m =new Main();
		Logiciel a =new Logiciel(new CentreDeDonnes());
		a.runTask();
		m.faireChoix(m, a);
		
		
		
		
		

	}

}
