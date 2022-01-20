package assignment1_nickeem;

public class Trainee {
    private String traineeName;
    private int servicePoints;
    private int numDeaths;
    private int numFed;

    Trainee(String name)
    {
        traineeName = name;
        servicePoints = 0;
        numDeaths = 0;
        numFed = 0;
    }

    public void setTraineeName(String traineeName) {
        this.traineeName = traineeName;
    }

    public void setServicePoints(int servicePoints) {
        this.servicePoints = servicePoints;
    }

    public void setNumDeaths(int numDeaths) {
        this.numDeaths = numDeaths;
    }

    public void setNumFed(int numFed) {
        this.numFed = numFed;
    }

    public String getTraineeName() {
        return traineeName;
    }

    public int getServicePoints() {
        return servicePoints;
    }

    public int getNumDeaths() {
        return numDeaths;
    }

    public int getNumFed() {
        return numFed;
    }

}
