package resturant_pack;

public class Scoraxian extends Diner
{
    private final int max_energy_level = 20; // Scoraxian maximum energy level
    Scoraxian(String name)
    {
        this.setName(name);
        this.setSpecies("scoraxian");
        this.setSpeciesCode('s');
        ReturnRandom returnRandom = new ReturnRandom();
        this.setEnergyLevel(returnRandom.betweenTwoNumbers(5,11)); // 5 to 11(not inclusive)
        setMax_energy_level(20);
    }

    public void eatFood(String foodType, int nutrition) throws GreedyGutsException, AllergyException
    {
        System.out.println(foodType);
        if (foodType.equals("scoron") || foodType.equals("ambron")) {
            this.setEnergyLevel(getEnergyLevel()+nutrition);
        }
        else if (foodType == "elixon")
        {
            this.setEnergyLevel(Math.round(getEnergyLevel() * 0.75f)); // decrease energy by 25%
        }
        else if (foodType == "zoron")
        {
            this.setDeathBy("death by allergic reaction");
            throw new AllergyException();

        }

        if (DeathByGreed())
        {
            throw new GreedyGutsException();
        }
    } // eatFood
} // Scoraxian
