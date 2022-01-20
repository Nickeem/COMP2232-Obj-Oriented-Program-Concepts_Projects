package assignment1_nickeem;
/*DONE BY NICKEEM (⊙ˍ⊙)*/
import java.util.Scanner;

public class RTSDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Roxia Inc management system");
        Restaurant restaurant = new Restaurant();
        System.out.print("Please enter your name: ");
        Trainee trainee = new Trainee(scanner.next());

        System.out.println("Name: "+trainee.getTraineeName());
        System.out.println("Service Points: "+trainee.getServicePoints());
        System.out.println("Diner's Fed: "+trainee.getNumFed());
        System.out.println("Number of Deaths: "+trainee.getNumDeaths());
        System.out.println();

        restaurant.setUpZoneA();



        for (int round = 1; round <= 7; round++)
        { // 7 rounds
            restaurant.displayTables();

            // TWO DINERS SHOW UP

            if (round > 1)
            {
                ReturnRandom returnRandom = new ReturnRandom();
                if (restaurant.AmbroxianWandersIn()){ // ambroxian walks in and is consumed
                    System.out.println();
                    System.out.println("Ambroxian wandered into Zone A and was consumed by a diner");
                    System.out.println();
                }

                Diner randDiner1 = returnRandom.betweenZeroAnd(2) == 0 ?
                        new Zoraxian("zoraxian")
                        :
                        new Scoraxian("scoraxian");
                Diner randDiner2 = returnRandom.betweenZeroAnd(2) == 1 ?
                        new Zoraxian("zoraxian")
                        :
                        new Scoraxian("scoraxian");
                Diner[] randDiners = {randDiner1, randDiner2};
                // 2 random diners are generated (either Scoraxian and/or Zoraxian) and placed into variables
                // Diners are then placed in seats or turned away
                for (int i =0;i <2; i++)
                {
                    boolean seatWasFilled = false;
                    if (restaurant.tableIsFull(randDiners[i].getSpeciesCode()))
                    {
                        // user turns awy diner
                        System.out.println("Table is full, turn away "+randDiners[i].getSpecies() +"(yes): ");
                        String answer = scanner.next();
                    }
                    else
                    {
                        System.out.print("Select a seat for "+randDiners[i].getSpecies()+": ");
                        seatWasFilled = restaurant.fillSeat(scanner.nextInt(), randDiners[i]);
                        if (seatWasFilled)
                        {
                            System.out.println("Seat was filled successfully\n");
                        }
                        else
                        {
                            System.out.println("Failed to fill seat\n");
                        }
                    }
                }

            }



            System.out.print("Enter a selection value\nFill a seat(1) | Clear a seat(2) | Display Table(3) | Exit Demo(4) | Do nothing( >= 5): ");
            int selection = scanner.nextInt();

            if (selection == 1)
            {
                System.out.print("Type of seat to fill Scoraxian(1) or Zoraxian(2): ");
                int diner = scanner.nextInt();
                System.out.print("Enter seat to fill: ");
                int seatNum = scanner.nextInt();
                boolean wasFilled = false;

                // Seat is cleared based on (Scoraxian or Zoraxian) adn seat number
                if (diner == 1)
                {
                    wasFilled = restaurant.fillSeat(seatNum, new Scoraxian(Integer.toString(seatNum)));
                }
                else if (diner == 2)
                {
                     wasFilled = restaurant.fillSeat(seatNum, new Zoraxian(Integer.toString(seatNum)));

                }
                if (wasFilled)
                {
                    System.out.println("Seat was filled");
                }
                else
                {
                    System.out.println("Failed to fill seat");
                }
                System.out.println();
            }
            else if (selection == 2)
            {
                System.out.print("Do yo want to clear a Scoraxian(1) or Zoraxian(2): ");
                int diner = scanner.nextInt();
                System.out.print("Enter seat to clear: ");
                int seatNum = scanner.nextInt();
                boolean wasCleared = false;

                // Seat is cleared based on (Scoraxian or Zoraxian) adn seat number
                if (diner == 1)
                {
                    wasCleared = restaurant.clearSeat(seatNum, 's');
                }
                else if (diner == 2)
                {
                    wasCleared = restaurant.clearSeat(seatNum, 'z');

                }
                if (wasCleared)
                {
                    System.out.println("Seat was cleared");
                }
                else
                {
                    System.out.println("Failed to clear seat");
                }
                System.out.println();
            }
            else if(selection == 3)
            {
                restaurant.displayTables();
            }
            else if (selection == 4)
             {
                 System.exit(0);
             }

            // Any Zoraxian weak will be consumed by nearby Scoraxian
            for(int seat=1; seat <= restaurant.maxSeatsBetweenTables(); seat++)
            {
                if (restaurant.isZoraxianAtSeat(seat)) { // seat is occupied
                    if (restaurant.zoraxianEnergyLevelLow(seat)) // zoraxian level is below 3
                    {
                        if (restaurant.scoraxianNearby(seat)) // opposite or to the right of opposite
                        {
                            restaurant.clearSeat(seat, 'z'); // zoraxian is  consumed / cleared
                            trainee.setServicePoints(trainee.getServicePoints() - 3); // service points deducted

                            trainee.setNumDeaths(trainee.getNumDeaths()+1);
                            // user is notified
                            System.out.println("Poor Service. Zoraxian at seat"+seat+" was consumed" +
                                    " by Scoraxian due to insufficient energy");
                        }
                    }
                }
            }


            // END OF ROUND DEDUCT 1 LEVEL From each diner
            if (round % 2 == 0)
            {
                restaurant.decreaseZoneAEnergy();
            }
        }

        // END OF LAST ROUND
        // Print Trainee details
        System.out.println();
        System.out.println("Name: "+trainee.getTraineeName());
        System.out.println("Service Points: "+trainee.getServicePoints());
        System.out.println("Diner's Fed: "+trainee.getNumFed());
        System.out.println("Number of Deaths: "+trainee.getNumDeaths());


    }
}
