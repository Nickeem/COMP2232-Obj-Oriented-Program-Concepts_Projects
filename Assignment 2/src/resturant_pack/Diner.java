package resturant_pack;

public class Diner
{
    // Attributes
    private String name; // name of diner
    private char speciesCode; // speciesCode used to display the diner’s species on the screen
    private String species; //  holds the species of the dine
    private int energyLevel; // diner’s current energy level
    private String deathBy; // variable that contains how the Diner died
    private int max_energy_level; // diners max energy level
    private int roundsWaited; // number of rounds diner has been waiting

    Diner()
    {
        roundsWaited = 0;
        max_energy_level = 20;
        species = "D";
        speciesCode = 'D';
        name = "??";
    }

    // mutators

    public void setName(String name)
    {
        this.name = name;
    } // setName

    public void setSpeciesCode(char speciesCode)
    {
        this.speciesCode = speciesCode;
    } // setSpeciesCode

    public void setSpecies(String species)
    {
        this.species = species;
    } // setSpecies

    public void setEnergyLevel(int energyLevel)
    {
        this.energyLevel = energyLevel;
    } // setEnergyLevel


    // Accessors

    public String getName()
    {
        return name;
    } // getName

    public char getSpeciesCode()
    {
        return speciesCode;
    } // getSpeciesCode

    public String getSpecies()
    {
        return species;
    } // getSpecies

    public int getEnergyLevel()
    {
        return energyLevel;
    } // getEnergyLevel

    //extra methods
    public void decreaseEnergy()
    {
        energyLevel--; // decrease energy by 1
    } // decreaseEnergy

    public void setDeathBy(String deathBy) {
        this.deathBy = deathBy;
    } // setDeathBy

    public String getDeathBy()
    {
        return deathBy;
    } // getDeathBy

    public void setMax_energy_level(int max_energy_level)
    {
        this.max_energy_level = max_energy_level;
    } // setMax_energy_level

    public int getMax_energy_level()
    {
        return max_energy_level;
    } // getMax_energy_level

    public void setRoundsWaited(int roundsWaited)
    {
        this.roundsWaited = roundsWaited;
    } // setRoundsWaited

    public int getRoundsWaited()
    {
        return roundsWaited;
    } // getRoundsWaited

    public void increaseRoundsWaited()
    {
        roundsWaited++; // increase by 1
    } // increaseRoundsWaited

    public Boolean DeathByGreed()
    {
        if (getEnergyLevel() > max_energy_level) // diner ate too much
        {
            deathBy = "death by greed";
            return true;
        }
        return false;
    } // DeathByGreed

    public boolean isFullyFed()
    {
        return max_energy_level == energyLevel;
    } // isFullyFed
} // Diner
