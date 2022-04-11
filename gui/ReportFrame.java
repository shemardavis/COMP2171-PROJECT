package gui;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.EventQueue;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class ReportFrame{
    JFrame f;
    JTable j;
    private JTextField textField;
    private JButton btnNewButton;
    private JTextField textField_1;
    private final Action action = new SwingAction();
    private JButton Search;

	/** this was done to test the functionalilty outside of the entire system.
	 * Launch the application.
	 */ 
    //delete this when combining with project//
/* 	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportFrame frame = new ReportFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} */

	/**
	 * Create the frame.
	 */
	public  ReportFrame(){
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
    
    textField = new JTextField();
    sp.setColumnHeaderView(textField);
    textField.setColumns(10);
    //button created to export report as an xml file
    btnNewButton = new JButton("Export to Excel");
    btnNewButton.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
        //callling the excel function with the table as a parameter
    		excel(j);
        //Confirmation message
    		JOptionPane.showMessageDialog(f,"Report Exported!");
    	}
    });
    btnNewButton.setBounds(299, 346, 180, 21);
    f.getContentPane().add(btnNewButton);
    //Title
    JLabel lblNewLabel = new JLabel("Inventory Report");
    lblNewLabel.setFont(new Font("Segoe UI Variable", Font.BOLD, 15));
    lblNewLabel.setBounds(261, 10, 154, 31);
    f.getContentPane().add(lblNewLabel);
    //textfield where search inputs will be made
    textField_1 = new JTextField();
    
    
    textField_1.setBounds(464, 65, 96, 19);
    f.getContentPane().add(textField_1);
    textField_1.setColumns(10);
        
    //reading in the text file.
    File text = new File("Counter.txt");
    Scanner scnr = null;
   try {
       scnr = new Scanner(text);
   } catch (FileNotFoundException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
   }
   String date = scnr.nextLine();//attaching the dates of product purchases
   JLabel lblNewLabel_1 = new JLabel(date);
   lblNewLabel_1.setBounds(57, 71, 163, 13);
    f.getContentPane().add(lblNewLabel_1);
    //using rowfilter to enable the search function
    //checking the input in textfields against data in the table and returning row matches.
    Search = new JButton("Search");
    Search.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		String text = textField_1.getText();
            if (text.length() == 0) {
              sorter.setRowFilter(null);
            } else {
              sorter.setRowFilter(RowFilter.regexFilter(text));
            }
    	}
    	
    });
    Search.setBounds(570, 63, 85, 21);
    f.getContentPane().add(Search);
    // Frame Size
    f.setSize(793, 414);
    // Frame Visible = true
    f.setVisible(true);
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
    //Reading each line of the file into rows of the table.
   int i = 0;
    for(String row : inputList) {
         String [] line = row.split("\\s");
         input[i][0] = line[2];
         input[i][1] = line[7];
         i++;
     }
   return input;
}


public void setVisible(boolean b) {
	// TODO Auto-generated method stub
	b = true;
	
}
//To export as an excel file//
private void excel(JTable table){
    try
    {
      TableModel m = table.getModel();
      FileWriter fw = new FileWriter(new File("report.xls"));
      for(int i = 0; i < m.getColumnCount(); i++){
        fw.write(m.getColumnName(i) + "\t");
      }
      fw.write("\n");
      for(int i=0; i < m.getRowCount(); i++) {
        for(int j=0; j < m.getColumnCount(); j++) {
          fw.write(m.getValueAt(i,j).toString()+"\t");
        }
        fw.write("\n");
      }
      fw.close();
    }
    catch(IOException e){ System.out.println(e); }
  }
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
