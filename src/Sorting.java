import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * 
 * @author abdesselam 
 * classe qui permet de faire le trie des dates 
 * @version 1 
 * 
 *
 */

public class Sorting {
	
	
	public ArrayList<Presence> sort(ArrayList<Presence> arr){
		/**
		 * methode qui fait le trie d'un tableau contenant des dates 
		 * @param arr contenant des classes de type Presence qui contient eux meme des date qui permettent de faire 
		 * le trie 
		 * @return ArrayList<Presence> qui est un arrayList trié 
		 */
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		GestionDeService ges=new GestionDeService();
		int min=0;
		 for(int i = 0;i<arr.size();i++)
		    {
		        //Assume first element is min
		        min = i;
		        for(int j = i + 1;j<arr.size();j++)
		        {
		            try {
						if(ges.compareDatesByCompareTo(df, df.parse(arr.get(j).getDateEtHeuresActueles()), df.parse(arr.get(min).getDateEtHeuresActueles()))==-1) { min = j;}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		        
		        Presence temp = arr.get(i);
		        arr.set(i,arr.get(min));
		        arr.set(min,temp) ;
		
		      
		    }
		 
		 return arr;
	}
	
	
}
