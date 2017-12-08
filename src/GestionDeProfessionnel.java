import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class GestionDeProfessionnel {

	public void avisDePaiment(CentreDeDonnes ctrDonne,long numeroUnique){
		PrintWriter writer;
		
		try {
			
			
			Professionnel p=ctrDonne.professionnels.get(numeroUnique);
			String dateEtHeuresAct= new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
			writer = new PrintWriter(""+p.getNom()+""+dateEtHeuresAct, "UTF-8");
			writer.println("nom du professionnel "+p.getNom());
			writer.println("numero du professionnel "+p.getNumeroUnique());
			writer.println("adresse du professionnel "+p.getAdresse());
			writer.println("ville du professionnel "+p.getVille());
			writer.println("province du professionnel "+p.getProvince());
			writer.println("code postal du professionnel "+p.getCodePostal());
			ArrayList<Integer> codeDesServiceDonnes =p.getCodeDesServiceDonnés();
	
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			GestionDeService ges= new GestionDeService();
			//puisque on checrche les service fournit cette semaines 
			//donc on decremente la date de 7 jours
			
			for(int i=0;i<codeDesServiceDonnes.size();i++){
				Service s=ctrDonne.services.get(codeDesServiceDonnes.get(i));
				ArrayList<Integer> codeDesSeance = s.getCodeDesSeance();
				
				for(int j=0;j<codeDesSeance.size();j++){
					
					
		    		
					if(s!=null){
						if(ges.compareDatesByCompareTo(df, df.parse(s.getDateDebutService()), df.parse(dateEtHeuresAct))<1 ){
						writer.println("date du service "+s.getDateDebutService());
						writer.println("date et heures recu par l'ordinateur "+s.getDateEtHeuresActuelles());
						writer.println("nom du membre "+s.getNom());
						writer.println("numero du membre "+p.getNumeroUnique());
						writer.println("numero de la seance "+codeDesSeance.get(j));
						//la methode de paiment n'est pas mentioné dans l'enoncé 
						//donc on a choisit une montant de 30 dollar par seance
						writer.println("montant a payer "+30+"$");
					
							
						
					}
					
				}
				
				}
			}
			
		writer.println("------------------------------------");
		writer.close();
		
		}
		  catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	
	
	}
}
