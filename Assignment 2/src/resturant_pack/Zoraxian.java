package resturant_pack;

public class Zoraxian extends Diner
{
    //private final int max_energy_level = 15; // Zoraxian maximum energy level
    Zoraxian(String name)
    {
        this.setName(name);
        this.setSpecies("zoraxian");
        this.setSpeciesCode('z');
        ReturnRandom returnRandom = new ReturnRandom();
        this.setEnergyLevel(returnRandom.betweenTwoNumbers(4,8)); // 4 to 8(not inclusive)
        this.setMax_energy_level(15);
    }

    public void eatFood(String foodType, int nutrition) throws GreedyGutsException
    {
        if (foodType == "zoron" || foodType == "ambron") {
            this.setEnergyLevel(getEnergyLevel()+nutrition);
        }
        else if (foodType == "elixon")
        {
            this.setEnergyLevel(Math.round(getEnergyLevel() * 1.25f)); // increase energy by 25%
        }
        else if (foodType == "scoron")
        {
            this.setEnergyLevel(Math.round(nutrition/2) + getEnergyLevel()); // can only take in half energy from scoron
        }

        if (DeathByGreed()) // Zoraxian consumed too much
        {
            throw new GreedyGutsException();
        }
    } // eatFood

} // Zoraxian
