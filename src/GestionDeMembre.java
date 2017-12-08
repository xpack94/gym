import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


public class GestionDeMembre {
	long id;
	public long creerUnMembre(String typeDuMembre,CentreDeDonnes ctrDonne){
		Scanner sc=new Scanner(System.in);
			System.out.println("entrer votre nom (25 lettres):");
		    String nom=sc.next();
		    System.out.println("entrer votre email");
		    String email=sc.next();
		    System.out.println("entrer votre adresse (25 caractaires)");
		    String adresse=sc.nextLine();
		    sc.nextLine();
		    System.out.println("entrer votre ville  (14 caractaires)");
		    String ville =sc.next();
		    System.out.println("entrer votre province (2 lettres) ");
		    String province =sc.next();
		    System.out.println("entrer votre code postal (6 caractaires) ");
		    String codePostal=sc.nextLine();
		    sc.nextLine();
		    String status="actif";
		    
		    if(new Logiciel(ctrDonne).verifierEmail(email)!=null){
		    	System.out.println("inscription echoué");
		    	System.out.println("l'email existe deja ");
		    	return id;
		    }
		    
		   if(!this.conttienLettres(nom, 25)){
			   System.out.println("le nom depasse les 25 lettres");
			   System.out.println("l'inscription a echoué");
			   return id;
		   }
		   if(adresse.length()>25){
			   System.out.println("l'adresse contient plus que 25 caractaires");
			   System.out.println("l'inscription a echoué");
			   return id;
		   }
		   if(ville.length()>14){
			   System.out.println("la ville contient plus que 14 caractaires");
			   System.out.println("l'inscription a echoué");
			   return id;
		   }
		    if(!conttienLettres(province, 2)){
		    	System.out.println("la province a plus que 2 lettres");
		    	System.out.println("l'inscription a echoué");
		    	return id;
		    }
		    if(codePostal.length()>6){
		    	System.out.println("le code postal contient plus que 6 caractaires");
		    	System.out.println("l'inscripton a echoué ");
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
	//calcule le nombre de lettre dans une chaine de caractaires
	public boolean conttienLettres(String word,int nbrLettres){
		int comp=0;
		for(int i=0;i<word.length();i++){
			if(Character.isLetter(word.charAt(i))){
				comp++;
			}
		}
		if(comp>nbrLettres){
			return false;
		}
		return true;
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
	
	public void listDesService(CentreDeDonnes ctrDonne,long numeroUnique){
		
		PrintWriter writer;
		try {
			
			
			Membre m=ctrDonne.membres.get(numeroUnique);
			String dateEtHeuresAct= new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
			writer = new PrintWriter(""+m.getNom()+""+dateEtHeuresAct, "UTF-8");
			if(m!=null){
				writer.println("nom "+m.getNom());
				writer.println("numero unique "+m.getNumeroUnique());
				writer.println("adresse "+m.getAdresse());
				writer.println("ville "+m.getVille());
				writer.println("province "+m.getProvince());
				writer.println("code postal "+m.getCodePostal());
				m.setPresence( new Sorting().sort(m.getPresence()));
				ArrayList<Presence> serviceFournit=m.getPresence();
				for(int i=0;i<serviceFournit.size();i++){
					writer.println("date du service "+serviceFournit.get(i).getDateEtHeuresActueles());
					Professionnel pro=ctrDonne.professionnels.get(serviceFournit.get(i).getNumeroDuProfessionnel());
					if(pro!=null){
						writer.println("Nom du professionnel "+pro.getNom());
					}
						//trouver le nom du service 
					int codeDuService=Integer.parseInt(String.valueOf(serviceFournit.get(i).getCodeDeLaSeance()).substring(0, 3));
					Service s=ctrDonne.services.get(codeDuService);
					if(s!=null){
					writer.println("nom du service "+s.getNom());
					}
						
					
				}
			}
			writer.println("---------------------------------");
			writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
		
		
	
		
	}
	
	
}

	
	

