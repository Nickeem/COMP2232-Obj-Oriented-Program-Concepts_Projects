package resturant_pack;
/*DONE BY NICKEEM (⊙ˍ⊙) and SEAN(。_。)*/

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class RoxiaTraining extends JFrame implements ActionListener
{

    //setLayout(new FlowLayout());
    private Restaurant restaurant ; // restaurant where everything takes place

//    JButton button = new JButton("OK"); // default button
    private JPanel panel_left; // Customers Waiting
    private JPanel panel_middle; // food waiting
    private JPanel panel_right; // User Summary
    private JPanel panel_bottom; // Zone A = Seating Area

    private JLabel[] CW_labels; // Customers Waiting(CW) labels
    private JButton[] CW_buttons; // Customers Waiting(CW) buttons

    private JLabel[] FW_labels; // Food Waiting(FW) labels
    private JButton[][] FW_buttons; // Food Waiting(FW) buttons

    private JLabel[] US_labels; // User Summary labels
    private JLabel[] US_values; // User Summary values

    private JButton[] SA_ScoraxTable_buttons; // seating area scoraxian buttons
    private JButton[] SA_ZoraxTable_buttons; // seating area zoraxian buttons
    private int num_of_seatButtons; // number of seat buttons f_or each seating area table

    private int round; // current round
    private final int num_of_rounds; // number of rounds the game will play
    private String diner_to_seat; // diner from tables index (numeric String)


    public static void main(String[] args)
    {
        RoxiaTraining fr = new RoxiaTraining();
        centerFrame(fr);
    }

    public RoxiaTraining()
    { //
        init();
        restaurant = new Restaurant();

        String name = JOptionPane.showInputDialog(new JFrame(), "What is your name");
        restaurant.setTraineeName(name);

        num_of_seatButtons = 15;
        num_of_rounds = 7;
        round = 1;

        diner_to_seat = "";
        restaurant.setUpZoneA();
        SA_ScoraxTable_buttons = new JButton[restaurant.NUM_OF_SCORAXIANS];
        SA_ZoraxTable_buttons = new JButton[restaurant.NUM_OF_ZORAXIANS];

        setPanelBorder();
        setupGrid();
        setupPanelContent();

        setSize(1000, 400);
        setTitle("Roxia Restaurants Training Program");
        //button_instance(button);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void init()
    { // initializer for setting swing objects
        panel_left = new JPanel(); // panel left in grid
        panel_middle = new JPanel(); // panel middle in grid
        panel_right = new JPanel(); // panel right in grid
        panel_bottom = new JPanel(); // panel bottom in grid
        CW_buttons = new JButton[3]; // Customer waiting(CW) buttons
        FW_labels = new JLabel[6]; // Customer waiting(CW) buttons
        FW_buttons = new JButton[6][2]; // Food Waiting(FW) buttons - 6 rows, 2 columns
        US_labels = new JLabel[7]; // User Summary(US) labels that will hold value labels
        US_values = new JLabel[7]; // User Summary(US) labels that will hold values
        CW_labels = new JLabel[3]; // Customer Waiting(CW) labels

    }

    public int getRound()
    {
        return round;
    }

    public void setRound(int round)
    {
        this.round = round;
    }

    public void setPanelBorder()
    {
        String [] panel_names = {"Customers Waiting", "Food Waiting", "User Summary", "Zone A - Seating Area"};
        JPanel []panels = {panel_left, panel_middle, panel_right, panel_bottom};
        Border blue_border = new LineBorder(Color.BLUE);
        for (int i = 0; i <4; i++){
            TitledBorder temp = BorderFactory.createTitledBorder(blue_border, panel_names[i]);
            temp.setTitleJustification(TitledBorder.CENTER);
            panels[i].setBorder(temp);
        }
    }

    public void setupGrid()
    {
        setLayout(new GridBagLayout()); // layout type
        setupGridBagConstraints();
        //setup jPanel layout
        panel_left.setLayout(new GridLayout(3,2));
        panel_middle.setLayout(new GridLayout(6,3));
        panel_right.setLayout(new GridLayout(8,2));
        panel_bottom.setLayout(new GridLayout(2,16)); //bottom panel will flow

        // heights
        //panel_left.setSize(50, 90);


    }

    public void setupGridBagConstraints()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // left panel
        gbc.ipady = 80;
        gbc.ipadx = 80;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(panel_left, gbc);

        // middle panel
        gbc.ipady = 2;
        gbc.ipadx = 60;
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(panel_middle, gbc);



        // right panel
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.ipady = 14;
        gbc.ipadx = 60;
        add(panel_right, gbc);

        gbc.ipady = 0;
        gbc.ipadx = 0;

        // bottom panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 4;
        add(panel_bottom, gbc);
    }

    public void setupPanelContent()
    {
        setupPanelLeft();
        setupPanelMiddle();
        setupPanelRight();
        setupPanelBottom();

        /*
        panel_middle.add(new JLabel("Ambron   (2)"));
        panel_middle.add(new JButton("Serve"));
        panel_middle.add(new JButton("Return"));*/


        /*panel_right.add(new JLabel("User Name"));
        JLabel test = new JLabel("Nickeem");
        panel_right.add(test);*/


    }

    public void setupPanelLeft()
    {
        //{, new JLabel("Empty Queue"), new JLabel("Curious Ambroxian?")};
        CW_labels[0] = new JLabel("Empty Queue");
        CW_labels[1] = new JLabel("Empty Queue");
        CW_labels[2] = new JLabel("Curious Ambroxian?");
        initializeCWButtons();
        ///disableCWButtons();
        for (int i = 0; i < CW_labels.length; i++){
            panel_left.add(CW_labels[i]);
            panel_left.add(CW_buttons[i]);
        }

    } // setupPanelLeft

    public void setupPanelMiddle()
    {
        initializeFWVariables ();
        updateFoodWaiting();
        for (int i = 0; i < FW_labels.length; i++) {
            panel_middle.add(FW_labels[i]);
            panel_middle.add(FW_buttons[i][0]);
            panel_middle.add(FW_buttons[i][1]);
        }
    } // setupPanelMiddle

    public void setupPanelRight()
    {
        initializeUSVariables();
        updateUSVariables();
        for (int i = 0; i < US_labels.length; i++) {
            panel_right.add(US_labels[i]);
            panel_right.add(US_values[i]);
        }
    } // setupPanelRight

    public void setupPanelBottom()
    {
        initializeSAVariables();
        JPanel row1 = new JPanel();
        JPanel row2 = new JPanel();
        row1.add(new JLabel("Scoraxian Table --> "));
        row2.add(new JLabel("Zoraxian Table --> "));
        for(int i = 0; i < SA_ZoraxTable_buttons.length; i++) {
            row1.add(SA_ScoraxTable_buttons[i]);
            row2.add(SA_ZoraxTable_buttons[i]);
        }
        panel_bottom.add(row1);
        panel_bottom.add(row2);
    } // setupPanelBottom

    private void initializeCWButtons()
    {
        for (int i = 0; i < CW_buttons.length; i++)
        {
            if (i == CW_buttons.length-1)
            {
                CW_buttons[i] = new JButton("No");
            }
            else
            {
                CW_buttons[i] = new JButton("Seat");
            }
            CW_buttons[i].setEnabled(false); // default on startup
            CW_buttons[i].addActionListener(this);
        }
    } // initializeCWButtons

    public void disableCWButtons()
    {
        for (int i = 0; i < CW_buttons.length; i++) {
            CW_buttons[i].setEnabled(false);
        }
    } // disableCWButtons

    public void initializeFWVariables()
    {
        for (int i = 0; i < FW_buttons.length; i++) {
            FW_labels[i] = new JLabel();
            FW_buttons[i][0] = new JButton("Serve");
            FW_buttons[i][1] = new JButton("Return");
            FW_buttons[i][0].addActionListener(this);
            FW_buttons[i][1].addActionListener(this);
        }
    } // initializeFWVariables

    public void updateFoodWaiting()
    {
        String[] dish_names = restaurant.getDishNames();
        int[] dish_values = restaurant.getDishValues();
        for (int i = 0; i < Chef.number_of_dishes; i++)
        {
            String food = dish_names[i];
            food = food.substring(0,1).toUpperCase() + food.substring(1);
            String label =  food + "   ("+dish_values[i]+")";
            FW_labels[i].setText(label);
            FW_buttons[i][0].setEnabled(true);
            FW_buttons[i][1].setEnabled(true);
        }
    } // updateFoodWaiting

    public void initializeUSVariables()
    {
        String[] titles = {"User Name", "Service Points", "Fully Fed", "Dishes Served", "Dishes Returned",
                "'Poor Service' Deaths:", "'Curious Ambroxian': "};
        for (int i = 0; i < titles.length; i++) {
            US_labels[i] = new JLabel(titles[i]);
            US_values[i] = new JLabel("0");
            US_values[i].setBorder( new LineBorder(new Color(184,207,229)));
        }
    } // initializeUSVariables

    public void updateUSVariables()
    {
        String[] values = restaurant.traineeStats();
        for (int i = 0; i < US_values.length; i++)
        {
            US_values[i].setText(values[i]);
        }
    } // updateUSVariables

    public void initializeSAVariables()
    {
        ArrayList<Integer> scoraxSeatsFilled = restaurant.scoraxTableFilledSeats(); // used only worry about filled seats
        ArrayList<Integer> zoraxSeatsFilled = restaurant.zoraxTableFilledSeats();
        for (int i = 0; i < SA_ZoraxTable_buttons.length; i++)
        {
            if (scoraxSeatsFilled.contains(i))
            {
                SA_ScoraxTable_buttons[i] = new JButton(String.valueOf(restaurant.scoraxianAtSeatEnergy(i)));

            }
            else
            {
                SA_ScoraxTable_buttons[i] = new JButton("0");
            }
            if (zoraxSeatsFilled.contains(i))
            {
                SA_ZoraxTable_buttons[i] = new JButton(String.valueOf(restaurant.zoraxianAtSeatEnergy(i)));

            }
            else
            {
                SA_ZoraxTable_buttons[i] = new JButton("0");
            }
            SA_ScoraxTable_buttons[i].addActionListener(this);
            SA_ZoraxTable_buttons[i].addActionListener(this);
        }
    } // initializeSAVariables

    public void updateSAVariables(String table)
    {
        if (table == "s") {
            for (int i = 0;i < SA_ScoraxTable_buttons.length; i++)
            {
                if (restaurant.isScoraxianAtSeat(i))
                {
                    SA_ScoraxTable_buttons[i].setText(String.valueOf(restaurant.scoraxianAtSeatEnergy(i)));
                }
                else
                {
                    SA_ScoraxTable_buttons[i].setText("0");
                }
            }
        } else if (table == "z") {
            for (int i = 0;i < SA_ZoraxTable_buttons.length; i++)
            {
                if (restaurant.isZoraxianAtSeat(i))
                {
                    SA_ZoraxTable_buttons[i].setText(String.valueOf(restaurant.zoraxianAtSeatEnergy(i)));
                }
                else
                {
                    SA_ZoraxTable_buttons[i].setText("0");
                }
            }
        }
    } // updateSAVariables


    public void disableDish(int index)
    {
        FW_buttons[index][0].setEnabled(false);
        FW_buttons[index][1].setEnabled(false);
    } //disableDish

    public boolean allDishesServed()
    {
        for (int i = 0; i < FW_buttons.length; i++)
        {
            if (FW_buttons[i][0].isEnabled() || FW_buttons[i][1].isEnabled())
            {
                return false;
            }
        }
        return true;
    } // allDishesServed

    public void startNewRound()
    {
        round += 1; // next round
        if (round > num_of_rounds)
        {
            restaurant.finalReport();
            System.exit(0);
            // end game
        }
        if (round%2==0) // even round
        {
            restaurant.decreaseZoneAEnergy();
        }
        restaurant.newCourse(); // create new course of meals for new round
        updateFoodWaiting(); // update labels for meals
        restaurant.queueWaits(); // decrease queue energy of waiting queue

        restaurant.addToQueue(); // chance new diners will enter queue
        disableCWButtons();
        updateQueue(); // update buttons if there is a queue
        if (restaurant.curiousAmbroxian())
        {
            CW_buttons[2].setEnabled(true);
            CW_buttons[2].setText("Yes. Click Here!");
        }
        else
        {
            CW_buttons[2].setEnabled(false);
            CW_buttons[2].setText("No");
        }
        restaurant.checkForWeakZoraxians();
        updateSAVariables("z"); // update seating area in a case a weak zoraxian
        updateSAVariables("s"); // was eaten

    } // startNewRound

    public void updateQueue()
    {
        String label;
        if (restaurant.sizeOfQueue() > 0)
        {
            CW_buttons[0].setEnabled(true);
            label = restaurant.dinerTypeInQueue(0) + "   ("+restaurant.dinerEnergyInQueue(0)+ ")";
            CW_labels[0].setText(label);
        }
        else
        {
            CW_buttons[0].setEnabled(false);
            CW_labels[0].setText("Empty Queue");
        }
        if (restaurant.sizeOfQueue() > 1)
        {
            CW_buttons[1].setEnabled(true);
            label = restaurant.dinerTypeInQueue(1) + "   ("+restaurant.dinerEnergyInQueue(1)+ ")";
            CW_labels[1].setText(label);
        }
        else
        {
            CW_buttons[1].setEnabled(false);
            CW_labels[1].setText("Empty Queue");
        }
    } // updateQueue

    public void processError(String type)
    { // display error type as message box
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Cannot "+ type +" diner here");
    } // processError

    public static void centerFrame(RoxiaTraining fr)
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int w = fr.getSize().width;
        int h = fr.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        fr.setLocation(x, y);
    } // centerFrame

    public static void maximiseFrame(RoxiaTraining fr)
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int w = fr.getSize().width;
        int h = fr.getSize().height;

        fr.setSize(w, h);
        fr.setLocation(0, 0);

        fr.setLocation(w, h);
    } // maximiseFrame


    public void actionPerformed(ActionEvent e)
    {
        if ( ((JButton) e.getSource()).getText() == "Serve")
        {// serve button is pressed
            diner_to_seat = "";

            for (int i = 0; i < FW_buttons.length; i++)
            { // find out which dish was selected
                if (FW_buttons[i][0] == e.getSource())
                { // store dish to be served
                    restaurant.setTrayDish(String.valueOf((i))); // send numeric String of dish index
                }
            }
        }
        else if ( ((JButton) e.getSource()).getText() == "Return")
        { // return button is presses
            diner_to_seat = ""; // reset variable
            restaurant.setTrayDish(""); // reset dish on tray whenever a dish is returned
            for (int i = 0; i < FW_buttons.length; i++)
            { // disable dish that is selected
                if (FW_buttons[i][1] == e.getSource())
                {
                    restaurant.DishReturned();
                    updateUSVariables();
                    disableDish(i);
                }
            }
            if (allDishesServed())
            {
                startNewRound();
            }
        }
        else if (((JButton) e.getSource()).getText() == "Seat")
        {  // seat button was presses
            restaurant.setTrayDish(""); // empty tray after seat button was pressed
            // store diner index in queue to be seated
            if(e.getSource() == CW_buttons[0])
            {
                diner_to_seat = "0";
            }
            else if (e.getSource() == CW_buttons[1])
            {
                diner_to_seat = "1";
            }
        }
        else if (((JButton)e.getSource()).getText().equals("Yes. Click Here!"))
        { // curious ambroxian button is presses and lowest diner gets lucky
            restaurant.AmbroxianWandersIn();
            // update tables because lowest diner will be fed
            updateSAVariables("s");
            updateSAVariables("z");
            updateUSVariables();
            CW_buttons[2].setEnabled(false);
            CW_buttons[2].setText("No");
        }
        else
        { // button from Seating Area is pressed
            //System.out.println("Zone A tables");
            if (!restaurant.trayIsEmpty())
            { // dish will be served
                if (((JButton) e.getSource()).getText().equals("0"))
                { // message will show hen user tries to feed empty seat
                    processError("feed");
                    return;
                }
                for (int seatNum = 0; seatNum < num_of_seatButtons; seatNum++ )
                { // find seat button presses
                    if (SA_ScoraxTable_buttons[seatNum] == e.getSource())
                    {
                        disableDish(Integer.parseInt(restaurant.getTrayIndex()));
                        restaurant.setTrayTable('s', String.valueOf(seatNum));
                        restaurant.serveDish();
                        updateUSVariables();
                        updateSAVariables("s");
                        restaurant.setTrayDish(""); // empty tray
                    }
                    else if (SA_ZoraxTable_buttons[seatNum] == e.getSource())
                    {
                        disableDish(Integer.parseInt(restaurant.getTrayIndex()));
                        restaurant.setTrayTable('z', String.valueOf(seatNum));
                        restaurant.serveDish();
                        updateUSVariables();
                        updateSAVariables("z");
                        restaurant.setTrayDish(""); // empty tray
                    }
                }
            }
            else if (!diner_to_seat.equals(""))
            { // attempt to seat diner from queue
                int index = Integer.parseInt(diner_to_seat); // diner index From Queue
                for (int seatNum = 0; seatNum < num_of_seatButtons; seatNum++)
                {
                    if (SA_ScoraxTable_buttons[seatNum] == e.getSource())
                    { // scorax seat is selected
                        if (!SA_ScoraxTable_buttons[seatNum].getText().equals("0") || restaurant.dinerTypeInQueue(index).equals("zoraxian"))
                        { // user attempts to seat in occupied seat or wrong table
                            processError("seat");
                            return;
                        }
                        int new_seat = Integer.parseInt(diner_to_seat);
                        CW_buttons[new_seat].setEnabled(false);
                        restaurant.seatWaiter(new_seat, seatNum);
                        updateQueue();
                        updateSAVariables("s");
                        diner_to_seat = "";

                    }
                    else if (SA_ZoraxTable_buttons[seatNum] == e.getSource())
                    { // zorax seat is selected
                        if (!SA_ZoraxTable_buttons[seatNum].getText().equals("0") || restaurant.dinerTypeInQueue(index).equals("scoraxian"))
                        { // user attempts to seat in occupied seat or wrong table
                            processError("seat");
                            return;
                        }
                        int new_seat = Integer.parseInt(diner_to_seat);
                        CW_buttons[new_seat].setEnabled(false);
                        restaurant.seatWaiter(new_seat, seatNum);
                        updateQueue();
                        updateSAVariables("z");
                        diner_to_seat = ""; // clear diner to seat
                    }
                }

            }


            if (allDishesServed())
            {
                startNewRound();
            }
        }

    } // actionPerformed
} // RoxiaTraining
