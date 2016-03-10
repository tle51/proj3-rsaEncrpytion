/** Tan Le (tle51) & Janki Patel (jpate53)
 * CS 342 - Project 3: RSA Encryption/Decryption
 * Gui Class - Sets up Gui
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class Gui extends JFrame implements ActionListener{
    private JTextField primeField;
    private JTextField primeField2;
    private JButton bt1;
    private JMenuBar menuBar;
    private JMenuBar helpBar;
    
    private JMenuItem menu;
    private JMenuItem keyMenu;
    private JMenuItem blockMenu;
    private JMenuItem unblockMenu;
    private JMenuItem encrMenu;
    private JMenuItem decrMenu;
    private JMenuItem exitMenu;
    
    private JMenuItem aboutMenu;
    private JMenuItem helpMenu;
    private JMenuItem help;
    
    private JPanel panel; //For Dropdown Menu
    private JPanel panel2; //Panel to display the output
    private JPanel fieldPanel; //Panel for 2 text fields
    private Container container;
    
    private String primeStr1;
    private String primeStr2;
    
    public Gui()
    {
        panel = new JPanel(); //panel object
        panel2 = new JPanel();
        fieldPanel = new JPanel();
        bt1 = new JButton("Enter"); //Button to Submit the prime keys.
        menuBar = new JMenuBar(); //object for menu bar
        helpBar = new JMenuBar();
        primeField = new JTextField(20); //text field object to get prime number
        primeField2 = new JTextField(20); //text field object to get 2nd prime number
        
        //Set up text field to get prime numbers
        primeField.setText("Prime 1");
        primeField2.setText("Prime 2");
        //primeField.addActionListener(this);
        //primeField2.addActionListener(this);
        bt1.addActionListener(this);
        
        /* MenuBar RSA has the following Menu list:
         * "Create a Key", "Block a File", "Unblock a File", "Encrypt", "Decrypt", "Exit"
         */
        menu = new JMenu("RSA");
        menuBar.add(menu);
        
        // HelpBar creates a menu containing help and about section
        helpMenu = new JMenu("Help");
        helpBar.add(helpMenu);
        
        //Menu Items for RSA menu
        keyMenu = new JMenuItem("Create Key");
        menu.add(keyMenu); //add menu to the menu bar
        keyMenu.addActionListener(this); //set action listener for the menu item
        
        blockMenu = new JMenuItem("Block a File");
        menu.add(blockMenu);
        blockMenu.addActionListener(this);
        
        unblockMenu = new JMenuItem("Unblock a File");
        menu.add(unblockMenu);
        unblockMenu.addActionListener(this);
        
        encrMenu = new JMenuItem("Encrypt");
        menu.add(encrMenu);
        encrMenu.addActionListener(this);
        
        decrMenu = new JMenuItem("Decrypt");
        menu.add(decrMenu);
        decrMenu.addActionListener(this);
        
        exitMenu = new JMenuItem("Exit");
        menu.add(exitMenu);
        exitMenu.addActionListener(this);
        
        //Menu Items for Help Menu Bar
        aboutMenu = new JMenuItem("About");
        helpMenu.add(aboutMenu);
        aboutMenu.addActionListener(this);
        
        help = new JMenuItem("Help");
        helpMenu.add(help);
        help.addActionListener(this);
        
        //Add the text field to the panel
        fieldPanel.add(primeField);
        fieldPanel.add(primeField2);
        fieldPanel.add(bt1);
        
        //Add the menu to the panel
        panel.add(menuBar);
        panel.add(helpBar);
        
        //add panel to the container
        container = getContentPane();
        container.add(panel, BorderLayout.PAGE_START);
        container.add(fieldPanel, BorderLayout.CENTER);
        
        //Set screen size and make it visible
        setSize(600, 600);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String str = e.getActionCommand();
        
        //get the textbox input
        String prime1 = primeField.getText();
        String prime2 = primeField2.getText();
        
        //Enter button is clicked
        if(e.getActionCommand().equals("Enter"))
        {
            //Check that user has entered values for both prime numbers
            if(prime1.equals("Prime 1") || prime2.equals("Prime 2"))
            {
                JOptionPane.showMessageDialog(null, "You must enter values for both prime numbers.",
                                              "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            else
                primeHandler();
        }
        
        if(str.equals("Create Key"))
            createKey();
        else if(str.equals("Block a File"))
            blockMenu();//System.out.println("BlockMenu"); //TODO
        else if(str.equals("Unblock a File"))
            System.out.println("unblock Menu"); //TODO
        else if(str.equals("Encrypt"))
            System.out.println("Encrypt Menu"); //TODO
        else if(str.equals("Decrypt"))
            System.out.println("decrypt menu"); //TODO
        else if(str.equals("Exit"))
            System.exit(0);
        else if(str.equals("About"))
            about();
        else if(str.equals("Help"))
            help();
    }
    
    //Get the value of the two prime numbers
    public void primeHandler()
    {
        String prime1 = primeField.getText();
        String prime2 = primeField2.getText();
        
        //Set prime numbers to the user input
        primeStr1 = prime1;
        primeStr2 = prime2;
        
        System.out.println("prime1 " + prime1);
        System.out.println("prime2 " + prime2);
    }
    
    //Check if for prime number
    public Boolean primeCheck(String p)
    {
        HugeUnsignedInteger hui = new HugeUnsignedInteger(p);
        hui.toArray();
        
        return true;
    }
    
    //Create random prime numbers
    public void generatePrime()
    {
        try(BufferedReader read = new BufferedReader(new FileReader("prime.txt")))
        {
            StringBuilder sb = new StringBuilder();
            String line = read.readLine();
            
            while (line != null)
            {
                System.out.println(line);
                
                sb.append(line);
                sb.append(System.lineSeparator());
                line = read.readLine();
            }
            String everything = sb.toString();
            
            //System.out.println(everything);
            read.close();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    //Key object to display the key information.
    public void createKey()
    {
        //TODO
        //Set panel2 to invisible, to remove previous menu's output
        setVisible(false);
        container.remove(panel2);
        
        JLabel l = new JLabel("KEY CREATE");
        
        //Check if User entered prime number or not
        if(primeStr1 == null && primeStr2 == null)
        {
            generatePrime();
        }
        
        RsaAlgorithm key = new RsaAlgorithm(primeStr1, primeStr2);
        
        panel2.add(l);
        //add panel to the container
        container = getContentPane();
        container.add(panel2, BorderLayout.PAGE_END);
        l.setVisible(true);
        setVisible(true);
    }
    
    //Display Blocked File's information
    public void blockMenu()
    {
        //TODO
        //Set panel2 to invisible, to remove previous menu's output
        setVisible(false);
        container.remove(panel2);
        
        JLabel l = new JLabel("Block CREATE");
        panel2.add(l);
        
        //add panel to the container
        container = getContentPane();
        container.add(panel2, BorderLayout.CENTER);
        l.setVisible(true);
        setVisible(true);
    }
    //Message displaying information about the program.
    private void about()
    {
        JOptionPane.showMessageDialog(null, "This RSA Encryption/Descryption program is implemented for Software Design course (CS-342) Class at UIC.\n"
                                      +"The team members for this project are Tan Le (tle51) and Janki Patel (jpate53)\n", "About RSA Encryption/Decryption", JOptionPane.INFORMATION_MESSAGE);
    }
    
    //Help message for the program.
    private void help()
    {
        //TODO
        JOptionPane.showMessageDialog(null, "Program's functions are located under the RSA Menu.\n"
                                      + "TODO\n", "Help", JOptionPane.INFORMATION_MESSAGE);
    }
}//End of Class
