package resturant_pack;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.IOException;

public class Trainee {
    private String traineeName; // name of trainee
    private int servicePoints; // service points earned
    private int numDeaths; // number of total deaths
    private int numFed; // diners fed
    // new attributes
    private int dishesServed; // number of dishes served
    private int dishesReturned; // number of dishes returned
    private int curiousAmbroxians; // number of curious ambroxians
    private int lostFromWaiting; // diners lost from waiting too long
    private final ArrayList<String> incidentReport; // incidents from playing the simulation

    Trainee(String name)
    {
        traineeName = name;
        servicePoints = 0;
        numDeaths = 0;
        numFed = 0;

        dishesServed = 0;
        dishesReturned = 0;
        curiousAmbroxians = 0;
        lostFromWaiting = 0;
        incidentReport = new ArrayList<String>();
    }

    public void setTraineeName(String traineeName)
    {
        this.traineeName = traineeName;
    } // setTraineeName

    public void setServicePoints(int servicePoints)
    {
        this.servicePoints = servicePoints;
    } // setServicePoints

    public void setNumDeaths(int numDeaths)
    {
        this.numDeaths = numDeaths;
    } // setNumDeaths

    public void setNumFed(int numFed)
    {
        this.numFed = numFed;
    } // setNumFed

    public void setDishesServed(int dishesServed)
    {
        this.dishesServed = dishesServed;
    } // setDishesServed

    public void setDishesReturned(int dishesReturned)
    {
        this.dishesReturned = dishesReturned;
    } // setDishesReturned

    public void setCuriousAmbroxians(int curiousAmbroxians)
    {
        this.curiousAmbroxians = curiousAmbroxians;
    } // setCuriousAmbroxians

    public String getTraineeName()
    {
        return traineeName;
    } // getTraineeName

    public int getServicePoints()
    {
        return servicePoints;
    } // getServicePoints

    public int getNumDeaths()
    {
        return numDeaths;
    } // getNumDeaths

    public int getNumFed()
    {
        return numFed;
    } // getNumFed

    public int getDishesServed()
    {
        return dishesServed;
    } // getDishesServed

    public int getDishesReturned()
    {
        return dishesReturned;
    } // getDishesReturned

    public int getCuriousAmbroxians()
    {
        return curiousAmbroxians;
    } // getCuriousAmbroxians

    public void increaseServicePoints(int increment)
    {
        servicePoints += increment;
    } // increaseServicePoints

    public void deductServicePoints(int decrement)
    {
        servicePoints -= decrement;
    } // deductServicePoints

    public void increaseLostFromWaiting()
    {
        lostFromWaiting++;
    } // increaseLostFromWaiting

    public void increaseDishesServed()
    {
        dishesServed++;
    } // increaseDishesServed

    public void increaseDishesReturned()
    {
        dishesReturned++;
    } // increaseDishesReturned

    public void increaseCuriousAmbroxian()
    {
        curiousAmbroxians++;
    } // increaseCuriousAmbroxian

    public void increaseDeaths() {
        numDeaths++;
    } // increaseDeaths

    public void addToIncidentReport(String incident)
    {
        incidentReport.add(incident);
    } // addToIncidentReport

    public void generateTraineeReport()
    {
        LocalDate date = LocalDate.now();
        String filename = traineeName + "_"+date+".txt";
        String[] statDetails = {"Date", "Name", "Service Points", "Dishes Served",
                                "Dishes Returned", "Diners fully fed",
                                "Diners suffering from death from Poor Service",
                                "Ambroxians who met an untimely demis through 'death by curiosity",
                                "Customers lost from waiting too long"
                                };
        String[] stats = {date.toString(), traineeName, String.valueOf(servicePoints), String.valueOf(dishesServed),
                        String.valueOf(dishesReturned), String.valueOf(numFed),
                        String.valueOf(numDeaths),
                        String.valueOf(curiousAmbroxians),
                        String.valueOf(lostFromWaiting)};
        try {
            File outfile = new File(filename);
            FileWriter fileWriter = new FileWriter(outfile);

            for (int i = 0; i < stats.length; i++)
            {
                fileWriter.write(statDetails[i] + ": "+ stats[i]);
                fileWriter.write("\n");
            }
            fileWriter.write("Incidents\n");
            for (int i = 0; i < incidentReport.size(); i++)
            {
                fileWriter.write(incidentReport.get(i));
                fileWriter.write("\n");
            }

            fileWriter.flush();
            fileWriter.close();

        }
        catch (Exception e)
        {

        }

    } // generateTraineeReport
}
