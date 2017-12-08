import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Sorting {
	
	public ArrayList<Presence> sort(ArrayList<Presence> arr){
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
