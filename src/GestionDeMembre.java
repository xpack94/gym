import java.util.Scanner;


public class GestionDeMembre {
	long id;
	public long creerUnMembre(String typeDuMembre,CentreDeDonnes ctrDonne){
		Scanner sc=new Scanner(System.in);
			System.out.println("entrer votre nom:");
		    String nom=sc.next();
		    System.out.println("entrer votre email");
		    String email=sc.next();
		    System.out.println("entrer votre adresse");
		    String adresse=sc.nextLine();
		    sc.nextLine();
		    System.out.println("entrer votre ville ");
		    String ville =sc.next();
		    System.out.println("entrer votre province ");
		    String province =sc.next();
		    System.out.println("entrer votre code postal");
		    String codePostal=sc.nextLine();
		    sc.nextLine();
		    String status="actif";
		    
		    if(new Logiciel(ctrDonne).verifierEmail(email)!=null){
		    	System.out.println("inscription echoué");
		    	System.out.println("l'email existe deja ");
		    	return id;
		    }
		    
		    if(typeDuMembre.equals("membreRegulier")){
		    	//inscrir un membre
		    	 System.out.println("payer les frais d'adhesions? oui/non");
				    String reponse =sc.next();
				    //affecter au status actif si le membre decide de payer maintenant
				    if(reponse.equals("non") || reponse.equals("n")){
				    	return id;
				    }else{
				    	
				    	 id=ctrDonne.getId();
				    	 ctrDonne.setId(ctrDonne.getId()+1);
				    	 ctrDonne.membres.put( id, new MembreRegulier(id,nom,email,status,adresse,ville,codePostal,province));
				    }
				   
		    }else{
		    	 
		    	 id=ctrDonne.getId();
		    	 ctrDonne.setId(ctrDonne.getId()+1);
		    	//inscrir un professionnel
		    	ctrDonne.professionnels.put(id, new Professionnel(id, nom, email,adresse,ville,codePostal,province));
		    }
			return id;
		    
	}
	
	public void supprimerMembre(long numeroUnique,CentreDeDonnes ctrDonne){
		
		ctrDonne.membres.remove(numeroUnique);
		Professionnel p=ctrDonne.professionnels.get(numeroUnique);
		//supprimer les service donné par le professionnel
		try{
			for(int i=0;i<p.getNombreDeServiceDonné();i++){
				ctrDonne.services.remove(p.getCodeDesServiceDonnés(i));
			}
		}catch(Exception e){
			
		}
		
		
		ctrDonne.professionnels.remove(numeroUnique);
		
	}
	
	public void mettreAjourMembre(long num,CentreDeDonnes ctrDonne){
		   Membre m=ctrDonne.membres.get(num);
		    if(m==null){
		    	System.out.println("le numero est invalide");
		    	return;
		    }
			
			System.out.println("1:mettre a jour l'email");
			System.out.println("2:payer les frais mensuels");
			System.out.println("3:mettre a jour la ville");
			System.out.println("4:mettre a jour l'adresse");
			System.out.println("5:mettre a jour le code postal");
			System.out.println("6:mettre a jour la province");
			
			
			Scanner sc=new Scanner(System.in);
			String rep=sc.next();
		
			switch(Integer.parseInt(rep)){
				
			case 1:
				System.out.println("entrer votre nouvelle adresse email");
				String email=sc.next();
				m.setEmail(email);
				System.out.println("l'email a eté mis a jour");
				break;
			case 2:
				//le membre choisi de payer les frais mensuels
				if((((MembreRegulier)m).getEtat()).equals("suspendu")){
					((MembreRegulier)m).setEtat("actif");
				}else{
					System.out.println("vos frais son deja payés");
				}
				break;
				
			case 3:
				System.out.println("veuillez entrer votre ville ");
				String ville =sc.next();
				m.setVille(ville);
				System.out.println("la ville a éte mise a jour ");
				break;
			case 4:
				System.out.println("veuillez entrer votre adresse");
				String adresse =sc.nextLine();
				sc.nextLine();
				m.setAdresse(adresse);
				System.out.println("l'adresse a été mise a jour");
				break;
				
			case 5:
				System.out.println("veuillez entrer le code postale ");
				String codePostal =sc.nextLine();
				sc.nextLine();
				m.setCodePostal(codePostal);
				System.out.println("le code postale a eté mise a jour");
				break;
			case 6:
				System.out.println("veuillez entrer votre province ");
				String province = sc.next();
				m.setProvince(province);
				System.out.println("la province a été mise a jour");
				break;
			}
	}
	
	public boolean verificationMembre(long numeroUnique,CentreDeDonnes ctrDonne){
		Membre m=ctrDonne.membres.get(numeroUnique);
		Membre p =ctrDonne.professionnels.get(numeroUnique);
		if(m!=null || p!=null){
			return true;
		}
		return false;
		
	}

	
	
}
