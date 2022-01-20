package resturant_pack;
/*DONE BY NICKEEM (⊙ˍ⊙)*/
import java.util.Random;

public class ReturnRandom
{
    /**
     * @return number between 0 and max(not inclusive)
     */
    public int betweenZeroAnd(int max)
    {
        Random randNum = new Random();
        return randNum.nextInt(max);
    } // betweenZeroAnd
    /**
    * @return number between min and max(not inclusive)
     */
    public int betweenTwoNumbers(int min, int max)
    {
        Random randNum = new Random();
        return randNum.nextInt(max-min) +min;
    } // betweenTwoNumbers

    /**
     *
     * @param positions array of items
     * @return random element from array
     */
    public int elementFromArray(int[] positions)
    {
        Random randNum = new Random();
        return positions[randNum.nextInt(positions.length)];
    } // elementFromArray
} // ReturnRandom
