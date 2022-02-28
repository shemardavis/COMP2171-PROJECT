import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import java.awt.Color;


public class StockList extends JFrame {

	private JPanel contentPane;
    private JButton add, modify, generate;
    private JButton btnNewButton;

	/**
	 * Create the frame.
	 */
	public StockList() {
		setBackground(Color.BLACK);
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 915, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0,1));
        
        JLabel message = new JLabel("Select appropriate option:");

        inputPanel.add(message);
        
        inputPanel.setPreferredSize(new Dimension(349,120));

        

        JPanel radioPanel = new JPanel();


        JPanel btnPanel = new JPanel();
        ButtonListener buttonListener = new ButtonListener();
        btnPanel.setPreferredSize(new Dimension(349,40));
        

        getContentPane().add(inputPanel);
        getContentPane().add(radioPanel);
        getContentPane().add(btnPanel);
                
                JLabel TitleLabel = new JLabel("STOCK LIST");
                TitleLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
                TitleLabel.setBounds(337, 10, 232, 77);
                add = new JButton("Add Item");
                add.setFont(new Font("Tahoma", Font.PLAIN, 17));
                add.setBounds(69, 206, 108, 42);
                add.setBackground(Color.YELLOW);
                add.addActionListener(buttonListener);
                btnPanel.setLayout(null);
                btnPanel.add(TitleLabel);
                
                        btnPanel.add(add);
                modify = new JButton("Modify Item");
                modify.setFont(new Font("Tahoma", Font.PLAIN, 17));
                modify.setBounds(376, 205, 129, 44);
                modify.setBackground(Color.YELLOW);
                modify.addActionListener(buttonListener);
                btnPanel.add(modify);
                generate = new JButton("Generate Stock List");
                generate.setFont(new Font("Tahoma", Font.PLAIN, 17));
                generate.setBounds(658, 206, 184, 42);
                generate.setBackground(Color.YELLOW);
                generate.addActionListener(buttonListener);
                btnPanel.add(generate);
                
                btnNewButton = new JButton("Back");
                btnNewButton.setBounds(760, 401, 108, 49);
                btnNewButton.setBackground(Color.PINK);
                btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
                btnNewButton.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		Menu m = new Menu();
                		m.setVisible(true);
                		dispose();
                	}
                });
                btnPanel.add(btnNewButton);
                
                JLabel lblNewLabel = new JLabel("What would you like to to do the Stock List?");
                lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
                lblNewLabel.setBounds(310, 75, 299, 68);
                btnPanel.add(lblNewLabel);

        
        setPreferredSize(new Dimension(926, 532));
        
        
    }
	
	//Reads Stock List file
	private String[][] readFile() {
        File text = new File("Stock List.txt");
         Scanner scnr = null;
        try {
            scnr = new Scanner(text);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        List<String> inputList = new ArrayList<String>();
        while(scnr.hasNextLine()){
            String eachLine = scnr.nextLine();
            //System.out.println(eachLine);
            inputList.add(eachLine);
        }
       
        String [][] input = new String[inputList.size()][4];
         //Reading each line of file using Scanner class
        int i = 0;
         for(String row : inputList) {
              String [] line = row.split("\\s");
              input[i][0] = line[2];
              input[i][1] = line[6];
              input[i][2] = line[10];
              input[i][3] = line[3];
              i++;
          }
        return input;
    }
	
	
	//Actions for buttons
    private class ButtonListener implements ActionListener
    {
    
        public void actionPerformed(ActionEvent event)
        {
        	if(event.getSource() == add){
        		AddItem at = new AddItem();
        		at.setVisible(true);
        		dispose();
            }else{
            	if(event.getSource() == modify) {
            		ModifyList ml = new ModifyList();
            		ml.setVisible(true);
            		dispose();
            }else {
            	if(event.getSource() == generate) {
            		dispose();
            		 File file = new File("Stock List.txt");
            		 try {
						file.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} // if file already exists will do nothing 
            		 try {
            			 BufferedWriter out = new BufferedWriter( new FileWriter("Stock List.txt", true));
            		     for (String i[] : UserLogin.products) {
            		    	 out.write("Product ID: " + i[0]);
            		    	 out.write(" | Product quantity: " + i[1]);
            		    	 out.write(" | Product price: $" + i[2]);
            		         System.out.println("");
            		         out.write("\n");
            		     }
            		     
            		     out.close();
            		 }
            		 // Catch block to handle the exceptions
            		 catch (IOException e) {
            			 // Display message when exception occurs
            		     System.out.println("exception occoured" + e);
            		 }
            		 JFrame f = new JFrame();
            	     f.setTitle("Products");

            	        // Data to be displayed in the JTable
            	        String[][] data = readFile();

            	        // Column Names
            	        String[] columnNames = { "ID", "Quantity", "Price ($)" };

            	        // Initializing the JTable
            	        JTable j = new JTable(data, columnNames);
            	        j.setBounds(30, 40, 200, 300);

            	        // adding it to JScrollPane
            	        JScrollPane sp = new JScrollPane(j);
            	        f.getContentPane().add(sp);
            	        // Frame Size
            	        f.setSize(500, 200);
            	        // Frame Visible = true
            	        f.setVisible(true);
            	}
            }
            }
          }
        }
    }











