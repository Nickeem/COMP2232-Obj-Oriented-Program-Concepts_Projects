package assignment1_nickeem;
/*DONE BY NICKEEM (⊙ˍ⊙)*/

public class Restaurant {
    private int numAmbroxians;
    private int numScoraxians;
    private int numZoraxians;
    private Zoraxian[] zoraxTableZoneA; // Used for seating Zoraxians in Zone A
    private Scoraxian[] scoraxTableZoneA; //  Used for seating Scoraxians in Zone A
    private Diner[][] tablesZoneB; // used for seating in Zone B

    Restaurant()
    {
        numZoraxians = 10;
        numScoraxians = 10;
        // Create arrays
        zoraxTableZoneA = new Zoraxian[numZoraxians]; // 10 seats
        scoraxTableZoneA = new Scoraxian[numScoraxians]; // 10 seats
    }

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
    }

    public boolean fillSeat(int seatPos, Diner diner)
    {
        seatPos--;
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
    }

    public boolean clearSeat(int seatPos, char species)
    {
        seatPos--;
        // seat position passed will be chosen from natural natural numbers
        // hence decrement

        if (seatPos > maxSeatsBetweenTables() || seatPos < 0)
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
    }

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
    }


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
    }
    
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
    }

    public int maxSeatsBetweenTables(){ // return maximum seats between tables in Zone A
        return zoraxTableZoneA.length >= scoraxTableZoneA.length
                ? zoraxTableZoneA.length
                : scoraxTableZoneA.length;
    }

    public boolean isZoraxianAtSeat(int seatNum)
    {
        seatNum--;
        // seat position passed will be chosen from natural natural numbers

        if (seatNum > zoraxTableZoneA.length)
            return false;
        return zoraxTableZoneA[seatNum] != null;
    }

    public boolean isScoraxianAtSeat(int seatNum)
    { // NOT USED
        seatNum--;
        // seat position passed will be chosen from natural natural numbers
        if (seatNum > scoraxTableZoneA.length)
            return false;
        return scoraxTableZoneA[seatNum] != null;
    }

    public boolean zoraxianEnergyLevelLow(int seatNum)
    {
        seatNum--; // seat position passed will be chosen from natural natural numbers
        return zoraxTableZoneA[seatNum].getEnergyLevel() < 3;
    }

    /**
     * @return Check to see if Scoraxian is opposite or opposite to the right of Zoraxian
     * */
    public boolean scoraxianNearby(int seatNum)
    {
        // seat position passed will be chosen from natural natural numbers
        seatNum--; // number is decremented account for array position
        for(int seat = seatNum; seat < scoraxTableZoneA.length; seat++)
        {
            if ( scoraxTableZoneA[seat] != null)
            {
                scoraxTableZoneA[seat].setEnergyLevel( // Scoraxian consumes Zoraxian and increases its energy
                        scoraxTableZoneA[seat].getEnergyLevel()
                                +
                        scoraxTableZoneA[seat].getEnergyLevel()
                );
                return true;
            }
        }
        return false;
    }

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
    }

    public void decreaseZoneAEnergy() {
        for (int i=0; i < maxSeatsBetweenTables(); i++)
        {
            try { // case where seat is empty
                scoraxTableZoneA[i].setEnergyLevel(scoraxTableZoneA[i].getEnergyLevel()-1);
            }
            catch (Exception e)
            {
                
            }
            try{ // case where seat is empty
                zoraxTableZoneA[i].setEnergyLevel(zoraxTableZoneA[i].getEnergyLevel()-1);
            }
            catch (Exception e)
            {

            }
        }
    }

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
    }

    public boolean AmbroxianWandersIn()
    {
        ReturnRandom returnRandom = new ReturnRandom();
        if (returnRandom.betweenZeroAnd(5) == 4) // 20% chance
        {
            int lowestScorax = lowestAtTable('s'); // Scoraxian with lowest energy level
            int lowestZorax = lowestAtTable('z'); // Zoraxian with lowest energy level

            // Lucky diner with lowest energy will consume Ambroxian
            if (scoraxTableZoneA[lowestScorax].getEnergyLevel() < zoraxTableZoneA[lowestZorax].getEnergyLevel())
            {
                scoraxTableZoneA[lowestScorax].setEnergyLevel(scoraxTableZoneA[lowestScorax].getEnergyLevel()+2);
            }
            else
            {
                zoraxTableZoneA[lowestZorax].setEnergyLevel(zoraxTableZoneA[lowestZorax].getEnergyLevel()+2);
            }
            return true; // Ambroxian was consumed
        }
        return false; // ambroxian was not consumed
    }
}

/*public void setUpZoneA()
    {
        for (int seat = 0; seat < 10; seat++) // 8 seats to be filled
        {
            ReturnRandom returnRandom = new ReturnRandom();

            if (returnRandom.betweenZeroAnd(2) == 1) // 1 occupies seat
            { // occupy seat at Zorax table
                zoraxTableZoneA[seat] = new Zoraxian(Integer.toString(seat));
            }

            if (returnRandom.betweenZeroAnd(2) == 1) // 1 occupies seat
            { // occupy seat at Scorax table
                scoraxTableZoneA[seat] = new Scoraxian(Integer.toString(seat));
                // scoraxian is set to random seat
            }

        }
    }*/
