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
    private JTextField primeField, primeField2;
    private JButton bt1;
    private JMenuBar menuBar, helpBar;
    
    private JMenuItem menu, keyMenu, blockMenu,
    unblockMenu, encrMenu, decrMenu, exitMenu;
    
    private JMenuItem aboutMenu, helpMenu, help;
    
    private JPanel panel; //For Dropdown Menu
    private JPanel panel2; //Panel to display the output
    private JPanel fieldPanel; //Panel for 2 text fields
    private Container container;
    
    private String primeStr1, primeStr2;
    
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
        //container.add(Box.createRigidArea(new Dimension(500, 500)));
        //container.add(panel, BoxLayout.X_AXIS);
        //container.add(Box.createRigidArea(new Dimension(30, 30)));
        //container.add(fieldPanel, BoxLayout.Y_AXIS);
        //container.add(panel);
        //container.add(fieldPanel);
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
            else{
                primeHandler();
            }
        }
        
        if(str.equals("Create Key"))
            createKey();
        else if(str.equals("Block a File"))
            blockMenu();
        else if(str.equals("Unblock a File"))
            unblockMenu();
        else if(str.equals("Encrypt"))
            encryptMenu();
        else if(str.equals("Decrypt"))
            decryptMenu();
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
        primeStr1 = prime1;
        primeStr2 = prime2;
        
        //Check if the values are prime number
        if(primeCheck(prime1) && primeCheck(prime2))
        {
            //Set prime numbers to the user input
            primeStr1 = prime1;
            primeStr2 = prime2;
            
            //System.out.println("prime1 " + prime1);
            //System.out.println("prime2 " + prime2);
        }
        //Both numbers are not prime
        else if(!primeCheck(prime1) && !primeCheck(prime2))
        {
            JOptionPane.showMessageDialog(null, "The values are not prime numbers.\n" + "Please enter the values again.",
                                          "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        //First value is not prime
        else if(!primeCheck(prime1))
        {
            JOptionPane.showMessageDialog(null, "First value is not a prime number.\n" + "Please enter the value again.",
                                          "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        //Second value is not prime
        else if(!primeCheck(prime2))
        {
            JOptionPane.showMessageDialog(null, "Second value is not a prime number.\n" + "Please enter the value again.",
                                          "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
    }
    
    //Check if for prime number
    public static Boolean primeCheck(String p)
    {
        HugeUnsignedInteger hui = new HugeUnsignedInteger(p);
        HugeUnsignedInteger two = new HugeUnsignedInteger("2");
        
        //Divide the number by 2.
        String nStr = hui.division(two);
        HugeUnsignedInteger nHui = new HugeUnsignedInteger(nStr);
        
        Long nl = Long.parseLong(nStr);
        System.out.println("long nstr" + nl);
        
        int i = 0;
        for(i = 2; i <= Math.sqrt(nl); i+=2)
        {
            System.out.println("in for");
            String s = Integer.toString(i);
            System.out.println("s isi " + s);
            HugeUnsignedInteger x = new HugeUnsignedInteger(s);
            
            System.out.println("x is" + x.value);
            System.out.print("nhui   " +  nHui.value);
            //System.out.println("     temp " + x + "  " + i);
            
            String temp = hui.modulus(x);
            
            System.out.println("temp2 " + temp);
            
            if(Long.parseLong(temp) == 0)
            {
                System.out.println("in is temp = 0?");
                return false;
            }
            System.out.println("temp" + temp);
        }
        
        return true;
    }
    
    //Create random prime numbers
    public void generatePrime()
    {
        String[] arr = new String[20];
        //Read primeNumbers.rsc file
        try
        {
            int i = 0;
            Scanner sc = new Scanner(new File("primeNumbers.rsc.txt"));
            
            while(sc.hasNextLine())
            {
                String s = sc.nextLine();
                arr[i] = s;
                i++;
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }
        
        //Generate random number
        Random r = new Random();
        int temp = r.nextInt(20);
        int temp2 = r.nextInt(20);
        
        //assign prime number to the random index of the array.
        primeStr1 = arr[temp];
        primeStr2 = arr[temp2];
        
    }
    //Key object to display the key information.
    public void createKey()
    {
        //Set panel2 to invisible, to remove previous menu's output
        setVisible(false);
        container.remove(panel2);
        
        //Check if User entered prime number or not
        while(primeStr1 == null || primeStr2 == null)
        {
            generatePrime();
            generatePrime();
        }
        
        //Get name of public and private files.
        String publicFile = JOptionPane.showInputDialog("Enter file name for public key.");
        String privateFile = JOptionPane.showInputDialog("Enter file name for private key.");
        
        RsaAlgorithm key = new RsaAlgorithm(primeStr1, primeStr2, publicFile, privateFile);
        
        JLabel l = new JLabel("Public key " + publicFile + "was created successfully.\n");
        JLabel l2 = new JLabel("Private key " + privateFile + "was created successfully.\n");
        //l.setLocation(300, 300);
        l.setBounds(10, 10, 50, 30);
        panel2.add(l);
        panel2.add(l2);
        //panel2.setLocation(600, 600);
        //container.setLayout(null);
        //add panel to the container
        container = getContentPane();
        //container.add(panel2);
        //panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        container.add(Box.createRigidArea(new Dimension(500, 500)));
        container.add(panel2, BoxLayout.X_AXIS);
        //container.add(panel2);
        //container.setSize(200, 300);
        setVisible(true);
        setVisible(true);
    }
    
    //Display Blocked File's information
    public void blockMenu()
    {
        //Set panel2 to invisible, to remove previous menu's output
        setVisible(false);
        container.remove(panel2);
        
        //Get name of the block file.
        String messageFile = JOptionPane.showInputDialog("Enter file name of the message.");
        String blockFile = JOptionPane.showInputDialog("Enter file name to save the block.");
        String blockSize = JOptionPane.showInputDialog("Enter block size.");
        
        MessageBlocking blocking = new MessageBlocking(blockFile, Integer.parseInt(blockSize), messageFile);
        JLabel l = new JLabel("Block File created successfully.");
        panel2.add(l);
        
        //add panel to the container
        container = getContentPane();
        container.add(panel2, BorderLayout.CENTER);
        
        setVisible(true);
        setVisible(true);
    }
    
    //Display Unblocked File's information
    public void unblockMenu()
    {
        //Set panel2 to invisible, to remove previous menu's output
        setVisible(false);
        container.remove(panel2);
        
        //Get name of the block file.
        String unblockFile = JOptionPane.showInputDialog("Enter filename containg the block.");
        //String messageFile = JOptionPane.showInputDialog("Enter file name of the message.");
        
        MessageUnblocking unblocking = new MessageUnblocking(unblockFile);
        JLabel l = new JLabel("unblocking File successfully done.");
        panel2.add(l);
        
        //add panel to the container
        container = getContentPane();
        container.add(panel2, BorderLayout.CENTER);
        
        setVisible(true);
        setVisible(true);
    }
    
    //Display Unblocked File's information
    public void encryptMenu()
    {
        //Set panel2 to invisible, to remove previous menu's output
        setVisible(false);
        container.remove(panel2);
        
        //Get name of the block file.
        String encryptFile = JOptionPane.showInputDialog("Enter file name of private or public key");
        
        MessageUnblocking unblocking = new MessageUnblocking(encryptFile);		
        JLabel l = new JLabel("Encryption done successfully.");
        panel2.add(l);
        
        //add panel to the container
        container = getContentPane();
        container.add(panel2, BorderLayout.CENTER);
        
        setVisible(true);
        setVisible(true);
    }	
    
    //Display Unblocked File's information
    public void decryptMenu()
    {
        //Set panel2 to invisible, to remove previous menu's output
        setVisible(false);
        container.remove(panel2);
        
        //Get name of the file to decrypt from
        String decryptFile = JOptionPane.showInputDialog("Enter filename to decrpt from.");
        
        Decryption decrypt = new Decryption(decryptFile);		
        JLabel l = new JLabel("Decryption done successfully.");
        panel2.add(l);
        
        //add panel to the container
        container = getContentPane();
        container.add(panel2, BorderLayout.CENTER);
        
        setVisible(true);
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
