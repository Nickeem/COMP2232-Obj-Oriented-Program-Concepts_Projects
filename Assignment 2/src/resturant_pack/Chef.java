package resturant_pack;

public class Chef
{
    private String[] dish_types; // dish names
    private int[] dish_values; // 'nutrition' values of dishes
    public final static int number_of_dishes = 6; // number of dishes chef serves
    Chef()
    {
        //number_of_dishes = 6;
        dish_types = new String[number_of_dishes];
        dish_values = new int[number_of_dishes];
        makeCourse();
    }
    public void makeCourse()
    {
        dish_types[0] = "ambron";
        dish_types[1] = "scoron";
        dish_types[2] = "zoron";
        ReturnRandom returnRandom = new ReturnRandom();
        for (int i = 0; i < number_of_dishes; i++)
        {
            if (i > 2) {
                //ReturnRandom returnRandom_generator = new ReturnRandom();
                int random_value = returnRandom.betweenZeroAnd(4);
                if (random_value == 0) {
                    dish_types[i] = "ambron";
                }
                else if (random_value == 1)
                {
                    dish_types[i] = "scoron";
                }
                else if (random_value == 2)
                {
                    dish_types[i] = "zoron";
                }
                else // random_value is 3
                {
                    dish_types[i] = "elixon";
                }
            }
            //ReturnRandom returnRandom = new ReturnRandom();
            dish_values[i] = returnRandom.betweenTwoNumbers(2,6);
        }
    } // makeCourse

    public String dishTypeAt(int plate_num)
    {
        return dish_types[plate_num];
    } // dishTypeAt

    public int dishValueAt(int plate_num)
    {
        return dish_values[plate_num];
    } // dishValueAt

    public String[] getDishTypes (){
        return dish_types;
    } // getDishTypes
    public int[] getDishValues()
    {
        return dish_values;
    } // getDishValues
}
