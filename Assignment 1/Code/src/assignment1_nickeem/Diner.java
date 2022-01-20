package assignment1_nickeem;

public class Diner {
    // Attributes
    private String name; // name of diner
    private char speciesCode; // speciesCode used to display the diner’s species on the screen
    private String species; //  holds the species of the dine
    private int energyLevel; // diner’s current energy level

    Diner()
    {
        species = "D";
        speciesCode = 'D';
        name = "??";
    }

    // mutators

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSpeciesCode(char speciesCode)
    {
        this.speciesCode = speciesCode;
    }

    public void setSpecies(String species)
    {
        this.species = species;
    }

    public void setEnergyLevel(int energyLevel)
    {
        this.energyLevel = energyLevel;
    }

    // Accessors

    public String getName()
    {
        return name;
    }

    public char getSpeciesCode()
    {
        return speciesCode;
    }

    public String getSpecies()
    {
        return species;
    }

    public int getEnergyLevel()
    {
        return energyLevel;
    }
}
