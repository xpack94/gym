import java.util.Scanner;


/**
 * 
 * @author abdesselam
 * classe qui represente un gui du professionnel 
 * @param log qui represente la classe Logicel ou il y'a toutes les fonctionnalités
 * @param m qi represente la classe Main ou toutes la base de donné a éte initialisé
 * @version 1 
 *
 */

public class MenuDeProfessionnel {

	Logiciel log;
	Main main;
	public MenuDeProfessionnel(Main m,Logiciel log){
		this.log=log;
		this.main=m;
	}
	
	public void afficherMenu(Membre m){
		/**
		 * methode qui affiche le menu qui contient toutes les actions qu'un professionnel peut faire avec son application 
		 * @param m de type membre qui represente le professionnel en question
		 * @version 1
		 * @return void
		 */
		
		System.out.println("bienvenue a Gym "+m.getNom());
		System.out.println("votre numero est "+m.getNumeroUnique());
		System.out.println("veuillez choisir une option ");
		System.out.println("1:consulter les inscriptions au séances");
		System.out.println("2:modification des informations");
		System.out.println("3:confirmation de presence ");
		System.out.println("4:avis de paiment");
		System.out.println("5:se deconnecter ");
		Scanner sc=new Scanner(System.in);
		this.choisirOption(m, Integer.parseInt(sc.next()));
	}
	public void choisirOption(Membre m,int option){
		/**
		 * methode qui permet de gerrer le choix du professionnel et faire appele au bonne methode qui 
		 * repondent a son choix 
		 * @param m qui represente le professionnel lui meme 
		 * @param option qui represente le numero de l'option que le professionnel a choisit de faire 
		 * @version 1 
		 * @return void
		 */
		Scanner read=new Scanner(System.in);
		switch(option){
		case 1:
			System.out.println("entrer le code du service");
			log.consulterSeance(Integer.parseInt(read.next()));
			break;
		case 3:
			log.confirmationPresence(m.getNumeroUnique());
			break;
		case 4:
			log.avisDePaiment(m.getNumeroUnique());
			break;
		case 5:
			main.faireChoix(this.main, log);
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
		this.afficherMenu(m);
		
		
	}
	
}
