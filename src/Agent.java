import java.util.HashMap;


public class Agent {

	
	
	
  
	
	private CentreDeDonnes ctrDonne;
	public Agent(CentreDeDonnes c){
		this.ctrDonne=c;
	}
	
	protected long creeMembre(){
		    this.ctrDonne.setId(this.ctrDonne.getId()+1);
		    long id =this.ctrDonne.getId();
		    this.ctrDonne.membres.put((int) id, new Membre(id,"actif"));
			return id;
		}
		//la methode qui verifie si un memebre existe 
		protected boolean verificationMembre(long numeroUnique){
			Membre m=ctrDonne.membres.get(numeroUnique);
			if(m!=null && m.getEtat().equals("actif")){
				System.out.println("Validé");
			}else{
				//soit le membre est suspendu soit il n'existe pas
				if(m.getEtat().equals("suspendu")){
					System.out.println("membre suspendu");
				}else{
					System.out.println("numero invalide");
				}
				return false;
			}
			return true;
			
		}
		//la methode qui permet a une professionnel de donner un service 
		protected void DonnerService(long numeroUnique){
			if(!this.verificationMembre(numeroUnique)){
				//le professionnel n'est pas inscrit 
				long number=this.creeMembre();
			}
			//cree le service avec les informations necessaire 
			this.ctrDonne.setServiceId(this.ctrDonne.getServiceId()+1);
			int serviceId= this.ctrDonne.getServiceId();
			Service s=new Service("10/5/2017","10/6/2017","20h","samedi",30,numeroUnique,serviceId);
			this.ctrDonne.services.put((int) s.getCodeDuService(), s);
		}
		//la methode qui permet au professionnel de consulter ses seance
		protected Service consulterSeance(int codeDuService){
			Service s=this.ctrDonne.services.get(codeDuService);
			return s;
			
		}
}
