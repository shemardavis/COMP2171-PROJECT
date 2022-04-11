package checkout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import gui.Menu;
import gui.UserLogin;

//import StockList.ButtonListener;

public class Cashier extends JFrame {

	private JPanel contentPane;
	JFrame f;
	JTable j;

    private JButton trans, transLog, btnNewButton_2;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Cashier() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 872, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ButtonListener buttonListener = new ButtonListener();
		
		JLabel lblNewLabel = new JLabel("Point-of-Sale");
		lblNewLabel.setBounds(359, 10, 114, 42);
		lblNewLabel.setFont(new Font("Segoe UI Variable", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select appropriate option:");
		lblNewLabel_1.setFont(new Font("Segoe UI Variable", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(37, 58, 291, 59);
		contentPane.add(lblNewLabel_1);
		
		trans = new JButton("Sales transaction");
		trans.setFont(new Font("Segoe UI Variable", Font.PLAIN, 16));
		trans.setBounds(121, 149, 185, 67);
		trans.addActionListener(buttonListener);
		contentPane.add(trans);
		
		transLog = new JButton("Transaction log");
		transLog.setFont(new Font("Segoe UI Variable", Font.PLAIN, 16));
		transLog.setBounds(543, 153, 197, 59);
		transLog.addActionListener(buttonListener);
		contentPane.add(transLog);
		
		btnNewButton_2 = new JButton("Back");
		btnNewButton_2.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		btnNewButton_2.setBounds(768, 381, 80, 42);
		btnNewButton_2.addActionListener(buttonListener);
		contentPane.add(btnNewButton_2);
		
	}
	
	private String[][] readFile() {
		   File text = new File("Counter.txt");
		    Scanner scnr = null;
		   try {
		       scnr = new Scanner(text);
		   } catch (FileNotFoundException e) {
		       // TODO Auto-generated catch block
		       e.printStackTrace();
		   }
		  
		   List<String> inputList = new ArrayList<String>();
		   scnr.nextLine();
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
		         input[i][1] = line[7];
		         i++;
		     }
		   return input;
		}
	
	private class ButtonListener implements ActionListener
    {
    
        public void actionPerformed(ActionEvent event)
        {
        	if(event.getSource() == btnNewButton_2){
        		Menu m = new Menu();
        		m.setVisible(true);
        		dispose();
            }
        	if(event.getSource() == trans){
        		Transaction t = new Transaction();
        		t.setVisible(true);
        		dispose();
            }
        	if(event.getSource() == transLog) {
        		dispose();
        		 File file = new File("Counter.txt");
        		 try {
					file.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // if file already exists will do nothing 
        		 try {
        			 BufferedWriter out = new BufferedWriter( new FileWriter("Counter.txt", true));
        		     for (String i[] : UserLogin.transLog) {
        		    	 out.write("Product ID: " + i[0]);
        	             out.write(" | Total quantity sold: " + i[1]);
        		         out.write("\n");
        		     }
        		     
        		     out.close();
        		 }
        		 // Catch block to handle the exceptions
        		 catch (IOException e) {
        			 // Display message when exception occurs
        		     System.out.println("exception occoured" + e);
        		 }
        		f = new JFrame();
        	    f.setTitle("Products");

        	    // Data to be displayed in the JTable
        	    String[][] data = readFile();

        	    // Column Names
        	    String[] columnNames = { "ID", "Quantity"};
        	    f.getContentPane().setLayout(null);

        	    // Initializing the JTable
        	    j = new JTable(data, columnNames);
        	    j.setBounds(30, 40, 200, 300);
        	    final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(j.getModel());
        	    j.setRowSorter(sorter);

        	    // adding it to JScrollPane
        	    JScrollPane sp = new JScrollPane(j);
        	    sp.setBounds(57, 94, 651, 249);
        	    f.getContentPane().add(sp);
        	    
        	    
        	    JLabel lblNewLabel = new JLabel("Transaction Log");
        	    lblNewLabel.setFont(new Font("Segoe UI Variable", Font.BOLD, 15));
        	    lblNewLabel.setBounds(261, 10, 154, 31);
        	    f.getContentPane().add(lblNewLabel);
        	    
        	  ;
        	    
        	    
        	    
        	    File text = new File("Counter.txt");
        	    Scanner scnr = null;
        	   try {
        	       scnr = new Scanner(text);
        	   } catch (FileNotFoundException e) {
        	       // TODO Auto-generated catch block
        	       e.printStackTrace();
        	   }
        	   String date = scnr.nextLine();
        	   JLabel lblNewLabel_1 = new JLabel(date);
        	   lblNewLabel_1.setBounds(57, 71, 163, 13);
        	    f.getContentPane().add(lblNewLabel_1);
        	    
        	    
        	    // Frame Size
        	    f.setSize(793, 414);
        	    // Frame Visible = true
        	    f.setVisible(true);
	           	    
                  
            }
          }
        }
}
