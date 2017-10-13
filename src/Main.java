import java.util.Scanner;



public class Main {

	/**
	 * @param args
	 */
	
	public void choixPris(Main m,Agent a,int choix){
		Scanner sc =new Scanner(System.in);
		switch (choix){
		case 1:
			System.out.println(" vous etes maintenant inscrit votre numero est: "+a.creeMembre());
			break;
		case 2:
			System.out.println("veuillez entrer votre numero");
			long numero = sc.nextLong();
			a.visite(numero);
			break;
		case 3:
			System.out.println("veuillez entrer votre numero");
			long num = sc.nextLong();
			a.sinscrirAService(num);
			break;
		case 4:
			long id=0;
			System.out.println("etes vous inscrit? oui/Non");
			String reponse =sc.nextLine();
			if((reponse.equals("Non")) ||( reponse.equals("n")) || (reponse.equals("non"))){
			 //le membre n'est pas inscrit
			 id=a.creeMembre();
			 System.out.println("vous etes maintenant inscrit votre numero est :"+id);
			}else{
				System.out.println("veuillez entrer votre numero");
				id=sc.nextLong();
			}
			int codeS=a.DonnerService(id);
			System.out.println("le service a ete cree avec le code "+codeS);
			break;
		case 5:
			System.out.println("veuillez entrer le code de la seance a 7 chiffres");
			int codeSeance=sc.nextInt();
			System.out.println("veuillez entrer votre numero");
			long numeroUnique=sc.nextLong();
			a.confirmationPresence(codeSeance, numeroUnique);
			break;
		case 6:
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
	
	public void faireChoix(Main m, Agent a){
		System.out.println("Bienvenu a GYM");
		System.out.println("que voulez-vous faire?");
		System.out.println("1:inscripton");
		System.out.println("2:visite");
		System.out.println("3:s'inscrire a une séance");
		System.out.println("4:donner service");
		System.out.println("5:confirmer presence");
		System.out.println("6:quitter");
		Scanner sc =new Scanner(System.in);
		int choix=sc.nextInt();
		m.choixPris(m,a, choix);
	}
	
	public static void main(String[] args) {
		Main m =new Main();
		Agent a =new Agent(new CentreDeDonnes());
		m.faireChoix(m, a);
		
		
		
		

	}

}
