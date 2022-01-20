package resturant_pack;
/*DONE BY NICKEEM (⊙ˍ⊙) and SEAN(。_。)*/

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Restaurant
{
    private int numAmbroxians;
    public final int NUM_OF_SCORAXIANS; // number of scoraxians
    public final int NUM_OF_ZORAXIANS; // number of zoraxians
    private Zoraxian[] zoraxTableZoneA; // Used for seating Zoraxians in Zone A
    private Scoraxian[] scoraxTableZoneA; //  Used for seating Scoraxians in Zone A
    private Diner[][] tablesZoneB; // used for seating in Zone B
    // assignment 2 added attributes
    private final int SEATS_AT_TABLE; // seats at diner tables in zone a
    Chef chef; // class that will prepare dishes
    Trainee trainee; // composition trainee
    private String[] tray; // array to hold dish type, table and diner type
    private ArrayList<Diner> queue; // queue of new diners. front of queue being 0

    Restaurant()
    { // initializing variables
        trainee = new Trainee("default name");
        queue = new ArrayList<Diner>();
        SEATS_AT_TABLE = 15;
        tray = new String[2];
        tray[0] = "";
        tray[1] = "";
        chef = new Chef();
        // old attributes
        NUM_OF_ZORAXIANS = 15;
        NUM_OF_SCORAXIANS = 15;
        // Create arrays
        zoraxTableZoneA = new Zoraxian[NUM_OF_ZORAXIANS]; // 10 seats
        scoraxTableZoneA = new Scoraxian[NUM_OF_SCORAXIANS]; // 10 seats
    } // Restaurant


    public void setUpZoneA()
    {
        int randPosition=0; // used for random available seat

        for (int seat = 0; seat < 8; seat++) // 8 seats to be filled
        {
            ReturnRandom returnRandom = new ReturnRandom();

            if (returnRandom.betweenZeroAnd(2) == 0) // returns 1 or 0
            { // occupy seat at Zorax table
                int[] zoraxAvailableSeats = zoraxTableAvailableSeats(); // available seats array e.g 0,2,7
                randPosition = returnRandom.elementFromArray(zoraxAvailableSeats); // get random available seat
                zoraxTableZoneA[randPosition] = new Zoraxian(Integer.toString(randPosition));
                // zoraxian is set to random seat
            }
            else
            { // occupy seat at Scorax table
                int[] scoraxAvailableSeats = scoraxTableAvailableSeats(); // available seats
                randPosition = returnRandom.elementFromArray(scoraxAvailableSeats); // get random available seat
                scoraxTableZoneA[randPosition] = new Scoraxian(Integer.toString(randPosition));
                // scoraxian is set to random seat
            }
        }
    } // setUpZoneA

    public boolean fillSeat(int seatPos, Diner diner)
    {
        //seatPos--;
        // seat position passed will be chosen from natural natural numbers
        // while array values start from 0. Hence decrement

        // case where seatPos doesn't exist
        if (seatPos > scoraxTableZoneA.length || seatPos > zoraxTableZoneA.length || seatPos < 0)
            return false;

        if (diner instanceof Scoraxian)
        {
            if (scoraxTableZoneA[seatPos] != null) // occupied seat
                return false; // no action was done
            scoraxTableZoneA[seatPos] = (Scoraxian) diner; // new diner occupies seat
            return true;
        }
        else if (diner instanceof Zoraxian)
        {
            if (zoraxTableZoneA[seatPos] != null) // occupied seat
                return false; // no action was done
            zoraxTableZoneA[seatPos] = (Zoraxian) diner; // new diner occupies seat
            return true;
        }
        return false; // default for unaccounted for case
    } // fillSeat

    public boolean clearSeat(int seatPos, char species)
    {
        //seatPos--;
        // seat position passed will be chosen from natural natural numbers
        // hence decrement

        if (seatPos > SEATS_AT_TABLE)
            return false; // seat doesn't exist

        if (species == 's')
        {
            if (scoraxTableZoneA[seatPos] == null)
                return false; // seat was already empty so no operation was done
            scoraxTableZoneA[seatPos] = null; // clear seat
            return true;
        }
        else if (species == 'z')
        {
            if (zoraxTableZoneA[seatPos] == null)
                return false; // seat was already empty so no operation was done
            zoraxTableZoneA[seatPos] = null; // clear seat
            return true;
        }
        return false; // default for shadow case
    } // clearSeat

    public void displayTables()
    {
        ReturnFormatted returnFormatted = new ReturnFormatted(); // class will be used to format output

        System.out.println("Scoraxian: ");
        System.out.print("Seat#:\t");
        System.out.print(returnFormatted.sequenceFromOneTo(scoraxTableZoneA.length)); // prints formatted sequence
        System.out.println();
        System.out.print("Energy:\t");
        returnFormatted.EnergyValues(scoraxTableZoneA); // prints formatted string with energy levels

        System.out.println("Zoraxian:");
        System.out.print("Seat#:\t");
        System.out.print(returnFormatted.sequenceFromOneTo(zoraxTableZoneA.length));
        System.out.println();
        System.out.print("Energy:\t");
        returnFormatted.EnergyValues(zoraxTableZoneA); // prints formatted string with energy levels
    } // displayTables

    /*public Scoraxian scoraxianAtSeat(int seatNum)
    {
        return scoraxTableZoneA[seatNum];
    }
    public Zoraxian zoraxianAtSeat(int seatNum)
    {
        return zoraxTableZoneA[seatNum];
    }*/

    public int scoraxianAtSeatEnergy(int seatNum)
    {
        return scoraxTableZoneA[seatNum].getEnergyLevel();
    } // scoraxianAtSeatEnergy

    public int zoraxianAtSeatEnergy(int seatNum)
    {
        return zoraxTableZoneA[seatNum].getEnergyLevel();
    } // zoraxianAtSeatEnergy

    /*Miscellaneous  Methods*/
    /**
     *
     * @return array of available seats at zoraxTableZoneA
     */
    public int[] zoraxTableAvailableSeats()
    {
        String temp = ""; //temp string to hold positions available
        int numAvailable = 0;

        for(int seat=0 ;seat < zoraxTableZoneA.length;seat++)
        {
            if (zoraxTableZoneA[seat] == null){
                temp += (seat + "-"); // separate positions by -
                numAvailable++; // count available seats
            }
        }


        String[] positions = temp.split("-"); // positions put in string array
        // create array with num of positions available as elements
        int[] availableSeats = new int[numAvailable]; // extra element after last - removed

        for (int i = 0;i<numAvailable;i++)
        {
            // sets available seats to each position available
            availableSeats[i] = Integer.parseInt(positions[i]);
        }
        return availableSeats; // available seats are return in an integer array
    } // zoraxTableAvailableSeats
    
    public int[] scoraxTableAvailableSeats()
    {
        String temp = ""; //temp string to hld positions
        int numAvailable = 0;

        for(int seat=0;seat<scoraxTableZoneA.length;seat++)
        {
            if (scoraxTableZoneA[seat] == null) {
                temp += (seat + "-"); // separate positions by -
                numAvailable++;
            }
        }


        String[] positions = temp.split("-");
        // create array with num of positions available as elements
        int[] availableSeats = new int[numAvailable];

        for (int i = 0;i<numAvailable;i++)
        {
            availableSeats[i] = Integer.parseInt(positions[i]);
        }
        return availableSeats;
    } // scoraxTableAvailableSeats
    
    public ArrayList<Integer> zoraxTableFilledSeats ()
    {
        ArrayList<Integer> filledSeats = new ArrayList<Integer>();
        for (int i = 0; i < zoraxTableZoneA.length; i++)
        {
            if (zoraxTableZoneA[i] != null)
            {
                filledSeats.add(i);
            }
        }
        return filledSeats;
    } // zoraxTableFilledSeats
    
    public ArrayList<Integer> scoraxTableFilledSeats ()
    {
        ArrayList<Integer> filledSeats = new ArrayList<Integer>();
        for (int i = 0; i < scoraxTableZoneA.length; i++)
        {
            if (scoraxTableZoneA[i] != null)
            {
                filledSeats.add(i);
            }
        }
        return filledSeats;
    } // scoraxTableFilledSeats

    public int maxSeatsBetweenTables()
    { // return maximum seats between tables in Zone A
        return zoraxTableZoneA.length >= scoraxTableZoneA.length
                ? zoraxTableZoneA.length
                : scoraxTableZoneA.length;
    } // maxSeatsBetweenTables

    public boolean isZoraxianAtSeat(int seatNum)
    {
        //seatNum;
        // seat position passed will be chosen from natural natural numbers

        if (seatNum > zoraxTableZoneA.length)
            return false;
        return zoraxTableZoneA[seatNum] != null;
    } // isZoraxianAtSeat

    public boolean isScoraxianAtSeat(int seatNum)
    {
        // NOT USED
        //seatNum--;
        // seat position passed will be chosen from natural natural numbers
        if (seatNum > scoraxTableZoneA.length)
            return false;
        return scoraxTableZoneA[seatNum] != null;
    } // isScoraxianAtSeat

    public boolean zoraxianEnergyLevelLow(int seatNum)
    {
        //seatNum--; // seat position passed will be chosen from natural natural numbers
        return zoraxTableZoneA[seatNum].getEnergyLevel() < 3;
    } // zoraxianEnergyLevelLow

    /**
     * @return Check to see if Scoraxian is opposite or opposite to the right of Zoraxian
     * */
    public String[] scoraxianNearby(int seatNum)
    {
        String[] scoraxian_nearby = new String[2]; // hold whether a scoraxian is nearby and its seat number
        scoraxian_nearby[0] = "false";
        for(int seat = seatNum; seat < scoraxTableZoneA.length; seat++)
        {
            if ( scoraxTableZoneA[seat] != null)
            {
                scoraxian_nearby[0] = "true";
                scoraxian_nearby[1] = String.valueOf(seat);
            }
        }
        return scoraxian_nearby;
    } // scoraxianNearby

    public boolean tableIsFull(char species)
    {
        if (species == 'z')
        {
            for(int seat = 1; seat <= zoraxTableZoneA.length; seat++)
            {
                if (!isZoraxianAtSeat(seat)) // if zoraxian seat is empty
                {
                    return false;
                }
            }
            return true;
        }
        else if (species == 's')
        {
            for(int seat = 1; seat <= scoraxTableZoneA.length; seat++)
            {
                if (!isScoraxianAtSeat(seat)) // if scoraxian seat is empty
                {
                    return false;
                }
            }
            return true;

        }
        return false;
    } // tableIsFull

    public void decreaseZoneAEnergy()
    {
        for (int seat=0; seat < maxSeatsBetweenTables(); seat++)
        {
            if (isScoraxianAtSeat(seat))
            {
                scoraxTableZoneA[seat].decreaseEnergy();
                if (scoraxTableZoneA[seat].getEnergyLevel() == 0)
                {
                    JFrame frame = new JFrame();
                    // add 1 for actual seat
                    String incident = "Scoraxian at seat "+ (seat+1) + "died from waiting too long";
                    JOptionPane.showMessageDialog(frame, "Poor Service:\n"+incident);
                    clearSeat(seat, 's');
                    trainee.deductServicePoints(3);
                    trainee.increaseDeaths();
                    trainee.increaseLostFromWaiting();
                    trainee.addToIncidentReport(incident);
                }
            }
            if (isZoraxianAtSeat(seat))
            {
                zoraxTableZoneA[seat].decreaseEnergy();
                if (zoraxTableZoneA[seat].getEnergyLevel() == 0){
                    JFrame frame = new JFrame();
                    // add 1 for actual seat
                    String incident = "Zoraxian at seat " + (seat+1) + " died from waiting too long";
                    JOptionPane.showMessageDialog(frame, "Poor Service:\n" + incident);
                    clearSeat(seat, 'z');
                    trainee.deductServicePoints(3);
                    trainee.increaseDeaths();
                    trainee.increaseLostFromWaiting();
                    trainee.addToIncidentReport(incident);
                }
            }

        }
    } // decreaseZoneAEnergy

    public int lowestAtTable(char species)
    {
        int lowestEnergy=10000; // value that energy will be lower than
        int lowestSeat=0;
        if (species == 's')
        {
            for (int seat = 0; seat < scoraxTableZoneA.length; seat++)
            {
                if (scoraxTableZoneA[seat] != null)
                {
                    if (scoraxTableZoneA[seat].getEnergyLevel() < lowestEnergy)
                    {
                        lowestEnergy = scoraxTableZoneA[seat].getEnergyLevel();
                        lowestSeat = seat;
                    }
                }
            }

        }
        else if (species == 'z')
        {
            for (int seat = 0; seat < zoraxTableZoneA.length; seat++)
            {
                if (zoraxTableZoneA[seat] != null)
                {
                    if (zoraxTableZoneA[seat].getEnergyLevel() < lowestEnergy)
                    {
                        lowestEnergy = zoraxTableZoneA[seat].getEnergyLevel();
                        lowestSeat = seat;
                    }


                }
            }

        }
        return lowestSeat;
    } // lowestAtTable

    public void AmbroxianWandersIn()
    {
        trainee.increaseCuriousAmbroxian(); // ambroxian that wanders in will be eaten by a diner
        int lowestScorax = lowestAtTable('s'); // Scoraxian with lowest energy level
        int lowestZorax = lowestAtTable('z'); // Zoraxian with lowest energy level
        JFrame frame = new JFrame();
        String incident = "Curious Ambroxian\n";
        // Lucky diner with lowest energy will consume Ambroxian
        // or if equal energy zoraxian gets ambroxian because they are more likely to die from low energy
        if (scoraxTableZoneA[lowestScorax].getEnergyLevel() < zoraxTableZoneA[lowestZorax].getEnergyLevel())
        { // add 1 for actual seat
            incident += "Scoraxian at seat "+(lowestScorax+1)+ " was the lucky diner that ate the Ambroxian";
            scoraxTableZoneA[lowestScorax].setEnergyLevel(scoraxTableZoneA[lowestScorax].getEnergyLevel()+2); // add 2 to energy
            JOptionPane.showMessageDialog(frame, incident);
        }
        else
        {
            incident += "Zoraxian at seat "+lowestZorax+1+ " was the lucky diner that ate the Ambroxian";
            zoraxTableZoneA[lowestZorax].setEnergyLevel(zoraxTableZoneA[lowestZorax].getEnergyLevel()+2); // add 2 to energy
            JOptionPane.showMessageDialog(frame, incident);
        }

    } // AmbroxianWandersIn

    // new methods
    public void setTraineeName(String name)
    {
        trainee.setTraineeName(name);
    } // setTraineeName

    public String[] traineeStats()
    {
        String[] stats = new String[7];
        stats[0] = trainee.getTraineeName();
        stats[1] = String.valueOf(trainee.getServicePoints());
        stats[2] = String.valueOf(trainee.getNumFed());
        stats[3] = String.valueOf(trainee.getDishesServed());
        stats[4] = String.valueOf(trainee.getDishesReturned());
        stats[5] = String.valueOf(trainee.getNumDeaths());
        stats[5] = String.valueOf(trainee.getNumDeaths());
        stats[6] = String.valueOf(trainee.getCuriousAmbroxians());

        return stats;

    } // traineeStats

    public void DishServed()
    {
        trainee.increaseDishesServed();
    } // DishServed

    public void DishReturned()
    {
        trainee.increaseDishesReturned();
    } // DishReturned

    public String getTrayIndex() {
        return tray[0];
    } // getTrayIndex

    public boolean trayIsEmpty()
    {
        return tray[0] == "";
    } // trayIsEmpty

    public void setTrayDish(String dish)
    { // dish is numerical String
        tray[0] = dish;
    } // setTrayDish

    public void setTrayTable(char table, String table_seat)
    { // s for scorax and z for zorax table and seat is a numerical value
        tray[1] = table + "-"+table_seat;
    } // setTrayTable

    public void serveDish()
    {
        if (tray[0] == "" || tray[1] == "") {
            System.out.println("Tray not filled properly");
            return;
        }

        trainee.increaseDishesServed();

        Frame f = new JFrame();
        int dish_index = Integer.parseInt(tray[0]);
        String table_type = tray[1].split("-")[0];
        int seat = Integer.parseInt(tray[1].split("-")[1]);

        String foodType = chef.dishTypeAt(dish_index);
        int nutrition = chef.dishValueAt(dish_index);

        boolean did_it_die; // did the Diner die from a condition (overfed, allergic...)
        if (table_type.toLowerCase().equals("s"))
        {
            try {
                scoraxTableZoneA[seat].eatFood(foodType, nutrition);
            }
            catch (GreedyGutsException e) {
                clearSeat(seat, 's');
                trainee.deductServicePoints(3);
                trainee.increaseDeaths();
                String incident = "Scoraxian at seat "+(seat+1) + " overate"; // add 1 for actual seat
                trainee.addToIncidentReport(incident);

                JOptionPane.showMessageDialog(f, "Poor Service:\n"+incident); // message
                //clearSeat();
            }
            catch (AllergyException e){
                //System.out.println("Allergic reaction");
                clearSeat(seat, 's');
                trainee.deductServicePoints(3);
                trainee.increaseDeaths();
                // add 1 for actual seat
                String incident = "Scoraxian at seat " +(seat+1)+" died from an Allergic reaction to zoron";
                JOptionPane.showMessageDialog(f, "Poor Service:\n"+incident); // message
                trainee.addToIncidentReport(incident);
            }
            if(isScoraxianAtSeat(seat))
            {
                if (scoraxTableZoneA[seat].isFullyFed())
                {
                    clearSeat(seat, 's');

                    trainee.increaseServicePoints(2);
                    // add 1 for actual seat
                    String message = "Good Service:\n"+"scoraxian at seat "+(seat+1)+" was fully fed";
                    JOptionPane.showMessageDialog(f, message);
                }
            }
        }
        else if (table_type.toLowerCase().equals("z"))
        {
            try {
                zoraxTableZoneA[seat].eatFood(foodType, nutrition);
            }
            catch (GreedyGutsException e)
            {
                clearSeat(seat, 'z');
                trainee.deductServicePoints(3);
                trainee.increaseDeaths();
                // add 1 for actual seat
                String incident = "Zoraxian at seat "+(seat+1)+ " overate";
                trainee.addToIncidentReport(incident);

                JOptionPane.showMessageDialog(f, "Poor Service:\n"+incident);
            }
            if(isZoraxianAtSeat(seat))
            {
                if (zoraxTableZoneA[seat].isFullyFed())
                {
                    clearSeat(seat, 'z');
                    trainee.increaseServicePoints(2);
                    // add 1 for actual seat
                    String message = "Good Service:\n"+"Zoraxian at seat "+(seat+1)+" was fully fed";
                    JOptionPane.showMessageDialog(f, message);
                }
            }
        }
        tray[0] = ""; // clear tray
        tray[1] = ""; // clear tray
    } // serveDish

    public String[] getDishNames() {
        return chef.getDishTypes();
    } // getDishNames

    public int[] getDishValues()
    {
        return chef.getDishValues();
    } // getDishValues


    public boolean curiousAmbroxian()
    { // chance of a curious ambroxian
        ReturnRandom returnRandom = new ReturnRandom();
        if (returnRandom.betweenZeroAnd(4)  == 3) // 25% chance
        {
            return true;
        }
        return false; // condition not met
    } // curiousAmbroxian

    public ArrayList<Integer> lowZoraxians()
    { // returns seat indexes of weak zoraxians
        ArrayList<Integer> weakZoraxians = new ArrayList<Integer>();
        for (int seat = 0; seat < NUM_OF_ZORAXIANS; seat++)
        {
            if (isZoraxianAtSeat(seat)){
                if (zoraxTableZoneA[seat].getEnergyLevel() < 3) {
                    weakZoraxians.add(seat);
                }
            }
        }
        return weakZoraxians;
    } // lowZoraxians

    public void checkForWeakZoraxians()
    {

        // needs addressing does Scoraxian gain eaten zoraxian's energy
        ArrayList<Integer> weakZoraxians = lowZoraxians();
        for (int i = 0; i < weakZoraxians.size(); i ++ )
        {
            int seatNum = weakZoraxians.get(i); // seat number of weak Zoraxian
            String[] scoraxian_nearby = scoraxianNearby(seatNum);
            if (scoraxian_nearby[0] == "true")
            {
                int scoraxSeatNum = Integer.parseInt(scoraxian_nearby[1]); // closet Scoraxian seat num
                //int zoraxEnergy = zoraxianAtSeatEnergy(seatNum);
                JFrame frame = new JFrame();

                String incident = "Scoraxian at seat "+scoraxSeatNum + " ate Zoraxian with low energy at seat "+seatNum;

                clearSeat(seatNum, 'z');
                trainee.deductServicePoints(3);
                trainee.addToIncidentReport(incident);
                JOptionPane.showMessageDialog(frame, "Poor Service:\n"+incident);
                /*
                try {
                    scoraxTableZoneA[scoraxSeatNum].eatFood("zoraxian", zoraxEnergy);
                }
                catch (GreedyGutsException e) {
                    clearSeat(scoraxSeatNum, 's');
                    trainee.decreaseServicePoints(3);
                    trainee.increaseDeaths();
                    String incident = "Scoraxian at seat "+scoraxSeatNum + "overate";
                    trainee.addToIncidentReport(incident);

                    JOptionPane.showMessageDialog(frame, "Poor Service:\n"+incident); // message
                    //clearSeat();
                }
                catch (AllergyException e)
                { // not possible case
                    System.out.println("Allergic reaction");
                    clearSeat(scoraxSeatNum, 's');
                    trainee.decreaseServicePoints(3);
                    trainee.increaseDeaths();
                    String incident = "Scoraxian at seat " +scoraxSeatNum+" died from an Allergic reaction to zoron";
                    JOptionPane.showMessageDialog(frame, "Poor Service:\n"+incident); // message
                    trainee.addToIncidentReport(incident);
                }
                finally {
                    String incident = "Scoraxian at seat "+ scoraxSeatNum + " ate weak Zoraxian at seat "+seatNum ;
                }*/
            }
        }
    } // checkForWeakZoraxians

    public void newCourse()
    {
        chef.makeCourse();
    } // newCourse

    public void dinersBecomeHungrier() // not used
    {
        for (int seat = 0; seat < scoraxTableZoneA.length; seat++)
        {
            if (isScoraxianAtSeat(seat)){
                scoraxTableZoneA[seat].decreaseEnergy();
            }
            if (isZoraxianAtSeat(seat)){
                zoraxTableZoneA[seat].decreaseEnergy();
            }
        }
    } // dinersBecomeHungrier

    public int sizeOfQueue()
    {
        return queue.size();
    } // sizeOfQueue

    public int dinerEnergyInQueue(int index)
    {
        return queue.get(index).getEnergyLevel();
    } // dinerEnergyInQueue

    public String dinerTypeInQueue(int index)
    {
        if (queue.get(index) instanceof Scoraxian)
        {
            return "scoraxian";
        }
        else if (queue.get(index) instanceof Zoraxian)
        {
            return "zoraxian";
        }
        return ""; // default
    } // dinerTypeInQueue

    public void addToQueue()
    {
        ReturnRandom returnRandom = new ReturnRandom();
        if (returnRandom.betweenZeroAnd(3) != 2) // 33% chance to continue
        {
            return; // 66% condition
        }
        int random_diners = 2; // num of random diners
        for (int i = 0; i < random_diners; i++)
        {
            if (returnRandom.betweenZeroAnd(2) == 1)
            {
                queue.add(new Scoraxian("scoraxian"));
            }
            else
            {
                queue.add(new Zoraxian("zoraxian"));
            }
        }
    } // addToQueue

    public void queueWaits()
    {
        ArrayList<Diner> indexes_toRemove = new ArrayList<Diner>(); // indexes to remove
        for(int i = 0; i < queue.size(); i++)
        {
            queue.get(i).decreaseEnergy();
            queue.get(i).increaseRoundsWaited();
            if (queue.get(i).getEnergyLevel() < 4)
            {
                indexes_toRemove.add(queue.get(i));
            }
        }
        //System.out.println(indexes_toRemove.toString());

        for (int i = 0; i < indexes_toRemove.size(); i++ )
        {
            dinerWaitedTooLong(indexes_toRemove.get(i));
            queue.remove(indexes_toRemove.get(i));
        }

    } // queueWaits

    public void dinerWaitedTooLong(Diner diner)
    {
        String customer_type = "";
        if (diner instanceof Scoraxian)
        {
            customer_type = "Scoraxian";
        }
        else if (diner instanceof Zoraxian)
        {
            customer_type = "Zoraxian";
        }
        String incident = customer_type + " has been waiting for "+diner.getRoundsWaited() + " rounds";
        trainee.addToIncidentReport(incident);
        trainee.deductServicePoints(2);
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Poor Service:\n"+incident);
    } // dinerWaitedTooLong

    public void seatWaiter(int index_in_queue, int seat)
    {
        fillSeat(seat, queue.get(index_in_queue));
        queue.remove(index_in_queue);
    } // seatWaiter

    public void finalReport()
    {
        trainee.generateTraineeReport();
    } // finalReport
} // Restaurant


