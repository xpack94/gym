import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Sorting {

	 public ArrayList<Presence> array;
	    public int length;
	 
	    public void sort(ArrayList<Presence> inputArr) {
	         
	        if (inputArr == null || inputArr.size() == 0) {
	            return;
	        }
	        this.array = inputArr;
	        length = inputArr.size();
	        quickSort(0, length - 1);
	    }
	    
	    
	    public void quickSort(int lowerIndex, int higherIndex) {
	    	DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	        int i = lowerIndex;
	        int j = higherIndex;
	        // calculate pivot number, I am taking pivot as middle index number
	        System.out.println(lowerIndex+(higherIndex-lowerIndex)/2);
	        Presence pivot =array.get(lowerIndex+(higherIndex-lowerIndex)/2);
	        
	        // Divide into two arrays
	        while (i <= j) {
	            /**
	             * In each iteration, we will identify a number from left side which 
	             * is greater then the pivot value, and also we will identify a number 
	             * from right side which is less then the pivot value. Once the search 
	             * is done, then we exchange both numbers.
	             */
	            try {
					while (new GestionDeService().compareDatesByCompareTo(df, df.parse( array.get(i).getDateEtHeuresActueles()), df.parse( pivot.getDateEtHeuresActueles()))==-1) {
					    i++;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            try {
					while (new GestionDeService().compareDatesByCompareTo(df, df.parse( array.get(j).getDateEtHeuresActueles()), df.parse( pivot.getDateEtHeuresActueles()))==1 ) {
					    j--;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            if (i <= j) {
	                exchangeNumbers(i, j);
	                //move index to next position on both sides
	                i++;
	                j--;
	            }
	        }
	        // call quickSort() method recursively
	        if (lowerIndex < j)
	            quickSort(lowerIndex, j);
	        if (i < higherIndex)
	            quickSort(i, higherIndex);
	    }
	 
	    private void exchangeNumbers(int i, int j) {
	        Presence temp = array.get(i);
	        array.add(i, array.get(j)) ;
	        array.add(j, temp) ;
	    }
	     
	    
}
