
/**
 * @author Abdesselam
 * 
 * @param jour
 * represente quel jour la seance est donnée
 * @param codeDesSeance 
 * un entier qui contient le code de la seance a 7 chiffres
 * @param membreInscrit 
 * un array  de taille 30 de type membreInscrit qui contient tout les memebres qui sont inscrit a la seance 
 *@param index 
 *une entier utiliser pour garder tracke de combien de membre sont inscrit dans la seance jusqu'a present 
 *
 *les methode de cette classe sont tous des getters et des setters car les paramaitres sont privés 
 *
 *@return une classe de type Seance 
 *
 *
 */
public class Seance {

	
	
	
	private int codeDeSeance;
	private String jour;
	private MembreInscrit [] membreInscrit ;
	private int index;
	
	
	public Seance(int codeDeSeance, String jour){
		this.codeDeSeance =codeDeSeance;
		this.jour=jour;
		//car la seance peut avoir 30 membre au max
		this.membreInscrit=new MembreInscrit[30];
		index=0;
	}



	public int getIndex() {
		return index;
	}



	public void setIndex(int index) {
		this.index = index;
	}



	public int getCodeDeSeance() {
		return codeDeSeance;
	}



	public void setCodeDeSeance(int codeDeSeance) {
		this.codeDeSeance = codeDeSeance;
	}



	public String getJour() {
		return jour;
	}



	public void setJour(String jour) {
		this.jour = jour;
	}



	public MembreInscrit[] getMembreInscrit() {
		return membreInscrit;
	}



	public void setMembreInscrit(MembreInscrit[] membreInscrit) {
		this.membreInscrit = membreInscrit;
	}
	
	public void setMembreInscrit(MembreInscrit m) {
		this.membreInscrit[this.index++]=m;
	}
	
	
	
}
