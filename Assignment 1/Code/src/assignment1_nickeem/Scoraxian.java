package assignment1_nickeem;

public class Scoraxian extends Diner{
    Scoraxian(String name)
    {
        this.setName(name);
        this.setSpecies("scoraxian");
        this.setSpeciesCode('s');
        ReturnRandom returnRandom = new ReturnRandom();
        this.setEnergyLevel(returnRandom.betweenTwoNumbers(5,11)); // 5 to 11(not inclusive)
    }
}
