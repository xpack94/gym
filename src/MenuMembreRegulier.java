import java.util.Scanner;


public class MenuMembreRegulier {
	Logiciel log;
	
	public MenuMembreRegulier(Logiciel l){
		this.log=l;
	}
	
	public void afficherMenu(Membre m){
		System.out.println("bienvenue a Gym "+ m.getNom());
		System.out.println("votre numero est :"+m.getNumeroUnique());
		System.out.println("veuillez choisir une option");
		System.out.println("1:s'inscrir a une séance ");
		System.out.println("2:mettre a jours les informations");
		Scanner sc=new Scanner(System.in);
		int option= Integer.parseInt(sc.next());
		this.choisirOption(m,option);
	}
	
	public void choisirOption(Membre m,int option){
		Scanner read=new Scanner(System.in);
		switch (option){
		case 1:
			log.sinscrirAService(m.getNumeroUnique());
			break;
		case 2:
			log.mettreAjourMembre(m.getNumeroUnique());
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
