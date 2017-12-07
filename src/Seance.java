
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
