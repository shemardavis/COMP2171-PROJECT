import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;


public class Edit extends JFrame {

	private JPanel contentPane;
	
	private JTextField pID, pName, pQuan, pPrice;
    private JButton enter, clear;
    private JLabel msg;
    private ButtonGroup buttonGroup;
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JButton btnNewButton_2;
    private JTable table;
    DefaultTableModel model;
   


	/**
	 * Create the frame.
	 */
	public Edit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel inputPanel = new JPanel();
        
		pID = new JTextField(30);
		pID.setBounds(169, 4, 715, 49);
		pName = new JTextField(30);
		pName.setBounds(169, 61, 715, 49);
        pQuan = new JTextField(30);
        pQuan.setBounds(169, 120, 715, 49);
        pPrice = new JTextField(30);
        pPrice.setBounds(169, 179, 715, 49);
        JLabel iLabel = new JLabel("Product ID:");
        iLabel.setBounds(0, 2, 884, 49);
        iLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        JLabel nLabel = new JLabel("Product name:");
        nLabel.setBounds(0, 61, 884, 49);
        nLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        JLabel qLabel = new JLabel("Product quantity:");
        qLabel.setBounds(0, 120, 884, 49);
        qLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        JLabel pLabel = new JLabel("Product price:");
        pLabel.setBounds(0, 179, 884, 49);
        pLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inputPanel.setLayout(null);

        inputPanel.add(iLabel);
        inputPanel.add(pID);
        inputPanel.add(nLabel);
        inputPanel.add(pName);
        inputPanel.add(qLabel);
        inputPanel.add(pQuan);
        inputPanel.add(pLabel);
        inputPanel.add(pPrice);
        inputPanel.setPreferredSize(new Dimension(349,120));
		
		JPanel btnPanel = new JPanel();
        enter = new JButton("Enter");
        enter.setFont(new Font("Tahoma", Font.PLAIN, 15));
        clear = new JButton("Clear");
        clear.setFont(new Font("Tahoma", Font.PLAIN, 15));
        ButtonListener buttonListener = new ButtonListener();
        enter.addActionListener(buttonListener);
        clear.addActionListener(buttonListener);

        btnPanel.add(enter);
        btnPanel.add(clear);
        btnPanel.setPreferredSize(new Dimension(349,40));
        
        JPanel messagePanel = new JPanel();
        msg = new JLabel("Enter product ID for item you wish to edit and new name/quantity/price in the relevant field(s)");
        msg.setFont(new Font("Tahoma", Font.PLAIN, 17));
        messagePanel.add(msg);
        messagePanel.setPreferredSize(new Dimension(349,40));

     

        getContentPane().add(messagePanel, BorderLayout.NORTH);
        getContentPane().add(inputPanel);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(0, 245, 884, 151);
        inputPanel.add(scrollPane_1);
        
        table = new JTable();
        model = new DefaultTableModel();
        Object[] column = {"Product ID", "Product Quantity", "Product Price"};
        model.setColumnIdentifiers(column);
        table.setModel(model);
        scrollPane_1.setViewportView(table);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);
        updateTable();
            
             
        btnNewButton_2 = new JButton("Back");
        btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ModifyList m = new ModifyList();
        		m.setVisible(true);
        	}
        });
        btnPanel.add(btnNewButton_2);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(21, 28, 379, 55);
        btnPanel.add(scrollPane);
       
        setPreferredSize(new Dimension(400, 350));
	}
	
	//Button actions
	private class ButtonListener implements ActionListener
	{
		Random random = new Random();
	    public void actionPerformed(ActionEvent event)
	    {
	    	boolean inList = false;
	        if(event.getSource() == enter) {
	        	if(pID.getText().length() > 0) { //Ensures ID is entered
	        		if (pName.getText().length() > 0 || pQuan.getText().length() > 0 || pPrice.getText().length() > 0) { //Ensures at least one product characteristic is entered
	        			for (String i[] : UserLogin.products){
	        		         if (i[0].equals(pID.getText())){
	        		           inList = true; //Ensures product exists
	        		           break;
	        		         }
	        		    }
	        			if (inList == false) {
	        				msg.setText("Poduct ID does not exist");
	        		        pID.setText("");
	        			    pName.setText("");
	        			    pQuan.setText("");
	        			    pPrice.setText("");
	        			    msg.setText("");
	        			}
	        			if (pName.getText().length() > 0) {
	        				for (String i[] : UserLogin.products){
		        	            String[] namePart = i[0].split("(?<=\\D)(?=\\d)");
		        	            if (namePart[0].equals(pName.getText())){
		        	            	msg.setText("Product name already exists");
		        	            	pName.setText("");
		        	            }
	        	            }
	        			}
	        				if (pName.getText().length() > 0) { //Changes name of product at index containing given ID
	        					for (String i[] : UserLogin.products){
	        						if (i[0].equals(pName.getText())){
	        							i[0] = pName.getText() + random.nextInt(999 - 100);
	        							break;
	        						}
	        					}
	        	             }
	        				if (pQuan.getText().length() > 0) {
	        					if (Integer.parseInt(pQuan.getText()) <= 0){ //Integers must be greater than 0
	        						msg.setText("Invalid quantity. Enter an integer greater than 0");
	        		                pQuan.setText("");
	        		               }
	        		        }
	        				
	        				if (pQuan.getText().length() > 0) {//Changes quantity of product at index containing given ID
	        					for (String i[] : UserLogin.products){
	        						if (i[0].equals(pID.getText())){
	        							i[1] = pQuan.getText();
	        							break;
	        		               }
	        		             }
	        				}
	        				if (pPrice.getText().length() > 0) {
	        					if (Integer.parseInt(pPrice.getText()) <= 0){ //Integers must be greater than 0
	        						msg.setText("Invalid price. Enter an integer greater than 0");
	        		                pPrice.setText("");
	        		             }
	        		         }
	        				if (pPrice.getText().length() > 0) {
	        					for (String i[] : UserLogin.products){
	        						if (i[0].equals(pID.getText())){ //Changes price of product at index containing given ID
	        							i[2] = pPrice.getText();
	        							break;
	        		                 }
	        		             }
	        				}
	        				msg.setText("Product updated");	    
	        				model.setRowCount(0);
	        				updateTable();
	        		}else {
	        			msg.setText("Field missing");
	        		}
	        }else {
	        	msg.setText("Product ID missing");
	        }
	      }
	        pID.setText("");
	        pName.setText("");
	        pQuan.setText("");
	        pPrice.setText("");
	        msg.setText("");
	    }
	}
	
	//Displays new Stock List
	Object[] row = new Object[0];
	public void updateTable(){
    	for (String[] i: UserLogin.products) {
        	row = i;
            model.addRow(row);
    	}
    	return;
    }
}

