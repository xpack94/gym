import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;



public class Main {

	/**
	 * @author abdesselam
	 * cette classe et le main du programme , elle contient que des methode permet l'interaction avec l'utilisateur 
	 * @version 3
	 * 
	 */
	
	public void choixPris(Main m,Logiciel a,int choix){
		/**
		 * permet de gerer le choix qui a eté pris par l'utilisateur 
		 * @param m qui represente la classe main elle meme 
		 * @param a qui represente la classe Logiciel ou il y'a toutes les fonctionalités
		 * @choix correspondant au choix que l'utilisateur a prix
		 * @version 3
		 * @return void
		 */
		
		Scanner sc =new Scanner(System.in);
		switch (choix){
		case 1:
			   new MenuAgent().faireChoix(m,a);
			   break;
		

		case 2:
			System.out.println("entrer votre email");
			String email =sc.next();
			Membre member=a.verifierEmail(email);
			if(member!=null) {
				System.out.println("connecté");
				new MenuMembreRegulier(m,a).afficherMenu(member);
			}else{
				System.out.println("l'email est invalide");
			}
			break;
		case 3:
			System.out.println("entrer votre email");
			String email1=sc.next();
			Professionnel mem=a.verifierEmailDeProfessionnel(email1);
			if(mem!=null){
				System.out.println("connecté");
				new MenuDeProfessionnel(m,a).afficherMenu(mem);
			}else{
				System.out.println("l'email est invalide");
			}
			break;
		case 4:
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
	
		/**
		 * methode qui permet d'afficher les differants choix possible pour un itilisateur 
		 * @param m qui represente la classe Main 
		 * @param a qui represente la classe Logicel 
		 * @version 3 
		 * @return void
		 */
		System.out.println("Bienvenu a GYM");
		System.out.println("etes-vous!?");
		System.out.println("1:Agent");
		System.out.println("2:Membre regulier");
		System.out.println("3:professionnel");
		System.out.println("4:quitter");
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
