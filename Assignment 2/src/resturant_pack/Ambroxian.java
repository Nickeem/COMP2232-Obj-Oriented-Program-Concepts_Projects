package resturant_pack;

public class Ambroxian extends Diner
{
    private boolean isBerserk; // holds current status

    Ambroxian(String name)
    {
        this.setSpecies("ambroxian");
        this.setSpeciesCode('a');
        this.setName(name);
        this.setEnergyLevel(0);
        this.isBerserk = false;
    }

    // mutator
    public void setBerserk(boolean berserk)
    {
        isBerserk = berserk;
    }
    //accessor
    public boolean isBerserk()
    {
        return isBerserk;
    }
}
