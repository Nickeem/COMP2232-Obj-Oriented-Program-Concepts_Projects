package assignment1_nickeem;
/*DONE BY NICKEEM (⊙ˍ⊙)*/
import java.util.Arrays;

/**
 * @defintion Class returns or prints strings formatted by a separator
 */
public class ReturnFormatted {
    /**
     * @return String Sequence separated by |
     */
    public String sequenceFromOneTo(int max)
    {
        // inclusive max
        int[] temp_array = new int[max];
        for (int i=0; i < max; i++)
        {
            temp_array[i] = i+1;
        }
        return Arrays.toString(temp_array)
                .replace(",", " |")
                .replace("[", "")
                .replace("]", ""); // replace unnecessary characters
    }


    /**
     * @return Diner's energy values separated by |
     */
    public void EnergyValues(Diner[] diner) { // return formatted Energy values from
        String temp = "";
        for (int i = 0; i < diner.length; i++) {
            // if seat occupied energy value will be displayed
            // if seat is empty a space is will be displayed
            // Separated by |
            if (i == 0 ) { // first value
                temp = (diner[i] != null ? Integer.toString(diner[i].getEnergyLevel())
                                :
                                " ")
                ;
            } else {
                temp += " | " + (diner[i] != null ? diner[i].getEnergyLevel()
                                :
                                " ");
            }
        }
        System.out.println(temp.replace("10 ", "10"));// replaces number to only take up 2 places
        System.out.println();
    }

}
