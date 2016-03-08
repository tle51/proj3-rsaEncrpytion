/** Tan Le (tle51) & Janki Patel (jpate53)
  * CS 342 - Project 3: RSA Encryption/Decryption
  * Gui Class - Sets up Gui
  */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Gui extends JFrame implements ActionListener{
	private JMenuBar menuBar;
	private JMenuItem menu;
	private JMenuItem keyMenu;
	private JMenuItem blockMenu;
	private JMenuItem unblockMenu;
	private JMenuItem encrMenu;
	private JMenuItem decrMenu;
	
	private JPanel panel;
	private Container container;
	
	public Gui()
	{
		panel = new JPanel();
		menuBar = new JMenuBar();
		
		menu = new JMenu("RSA");
		menuBar.add(menu);
		
		keyMenu = new JMenuItem("Create Key");
		menu.add(keyMenu);
		keyMenu.addActionListener(this);
		
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
		
		panel.add(menuBar);
		container = getContentPane();
		container.add(panel, BorderLayout.WEST);

		setSize(500, 500);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == "keyMenu")
			System.out.println("keypressed");
		
		if(e.getSource().equals("keyMenu"))
		System.out.println("inactionperformed");
	}

}
