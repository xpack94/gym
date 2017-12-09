import java.util.Scanner;

/**
 * 
 * @author abdesselam
 * classe qui represente le gui des actions qu'un membre regulier peut faire 
 * @version 1 
 * @param log qui represente la classe logiciel 
 * @param main qui represente la classe Main ou la base de donné a ete initialisé 
 *
 */

public class MenuMembreRegulier {
	Logiciel log;
	Main main;
	
	public MenuMembreRegulier(Main m,Logiciel l){
		this.log=l;
		this.main=m;
	}
	
	public void afficherMenu(Membre m){
		/**
		 * methode qui affiche le menu contenant toutes les actions que le membre peut faire 
		 * @param m qui represente le memebre lui meme 
		 * @version 1 
		 * @return void 
		 */
		
		System.out.println("bienvenue a Gym "+ m.getNom());
		System.out.println("votre numero est :"+m.getNumeroUnique());
		System.out.println("veuillez choisir une option");
		System.out.println("1:s'inscrir a une séance ");
		System.out.println("2:mettre a jours les informations");
		System.out.println("3:consulter la listes des incriptions ");
		System.out.println("4:se deconnecter");
		Scanner sc=new Scanner(System.in);
		int option= Integer.parseInt(sc.next());
		this.choisirOption(m,option);
	}
	
	public void choisirOption(Membre m,int option){
		/**
		 * methode qui gere le choix du memebre 
		 * @version 1 
		 * @param m qui represente le memebre lui meme 
		 * @param option qui represente le numero de l'option choisit 
		 * @return void
		 */
		
		Scanner read=new Scanner(System.in);
		switch (option){
		case 1:
			log.sinscrirAService(m.getNumeroUnique());
			break;
		case 2:
			log.mettreAjourMembre(m.getNumeroUnique());
			break;
		
		case 3:
			log.listDesService(m.getNumeroUnique());
		
		case 4:
			this.main.faireChoix(this.main, log);		
			break;
		}
		
		try        
		{
		    Thread.sleep(4000);
		} 
		catch(InterruptedException ex) 
		{
		    Thread.currentThread().interrupt();
		}
		afficherMenu(m);
		
		
	}
	
	
}
