package assignment1_nickeem;

public class Zoraxian extends Diner{
    Zoraxian(String name)
    {
        this.setName(name);
        this.setSpecies("zoraxian");
        this.setSpeciesCode('z');
        ReturnRandom returnRandom = new ReturnRandom();
        this.setEnergyLevel(returnRandom.betweenTwoNumbers(4,8)); // 4 to 8(not inclusive)
    }

}
