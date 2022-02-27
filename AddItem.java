import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;


public class AddItem extends JFrame {
	
	private JPanel contentPane;

	private JTextField pName, pQuan, pPrice;
    private JButton enter, clear;
    private JLabel msg;
    private int radioSelect = 0;
    private int isChecked = 0;
    private ButtonGroup buttonGroup;
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JTable table_1;
    DefaultTableModel model;
    
   

	/**
	 * Create the frame.
	 */
	public AddItem() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 917, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	
		
        JPanel inputPanel = new JPanel();
        //inputPanel.setLayout(new GridLayout(0,1));
        pName = new JTextField(30);
        pName.setBounds(163, 3, 457, 34);
        pQuan = new JTextField(30);
        pQuan.setBounds(163, 43, 457, 34);
        JLabel nLabel = new JLabel("Product name:");
        nLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nLabel.setBounds(12, 0, 122, 34);
        JLabel qLabel = new JLabel("Product quantity:");
        qLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        qLabel.setBounds(12, 44, 122, 26);
        inputPanel.setLayout(null);

        inputPanel.add(nLabel);
        inputPanel.add(pName);
        inputPanel.add(qLabel);
        inputPanel.add(pQuan);
        inputPanel.setPreferredSize(new Dimension(349,120));
		
     

        RadioListener radioListener = new RadioListener();

       
       JPanel btnPanel = new JPanel();
        ButtonListener buttonListener = new ButtonListener();
        btnPanel.setPreferredSize(new Dimension(300,40));

        JPanel messagePanel = new JPanel();
        msg = new JLabel("<<MSG>>");
        messagePanel.add(msg);
        messagePanel.setPreferredSize(new Dimension(349,40));
        
        
     

        getContentPane().add(inputPanel, BorderLayout.NORTH);
        enter = new JButton("Enter");
        enter.setBounds(771, 17, 95, 21);
        inputPanel.add(enter);
        clear = new JButton("Clear");
        clear.setBounds(771, 56, 95, 21);
        inputPanel.add(clear);
        
        btnNewButton_1 = new JButton("Back");
        btnNewButton_1.setBounds(771, 93, 95, 21);
        inputPanel.add(btnNewButton_1);
        pPrice = new JTextField(30);
        pPrice.setBounds(163, 87, 457, 33);
        inputPanel.add(pPrice);
        JLabel pLabel = new JLabel("Product price:");
        pLabel.setBounds(12, 92, 143, 30);
        inputPanel.add(pLabel);
        pLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		StockList st = new StockList();
        		st.setVisible(true);
        	}
        });
        clear.addActionListener(buttonListener);
        enter.addActionListener(buttonListener);
        getContentPane().add(btnPanel);
        btnPanel.setLayout(null);
        
        
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 54, 500, 100);
        getContentPane().add(scrollPane);
        
        table_1 = new JTable();
        model = new DefaultTableModel();
        Object[] column = {"Product ID", "Product Quantity", "Product Price"};
        Object[] row = new Object[0];
        updateTable();
        
        model.setColumnIdentifiers(column);
        table_1.setModel(model);
        scrollPane.setViewportView(table_1);
        getContentPane().add(messagePanel, BorderLayout.SOUTH);
       
        setPreferredSize(new Dimension(400, 350));
}
	
	//Button actions
	private class ButtonListener implements ActionListener
    {
		Object[] row = new Object[0];
    
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == enter)
            {
                if(pName.getText().length() > 0 && pQuan.getText().length() > 0 && pPrice.getText().length() > 0)
                { //Ensures all fields are completed
                	for (String i[] : UserLogin.products){
                      String[] namePart = i[0].split("(?<=\\D)(?=\\d)");
                      if (namePart[0].equals(pName.getText())){ //Ensures products are not duplicated
                    	  msg.setText("Product name already exists");
                    	  pName.setText("");
                      }
                    }   
                    Random random = new Random();
                    String ID = pName.getText() + random.nextInt(999 - 100); //Creates ID containing name and 3 random integers
                    
                    Integer quantity =  Integer.parseInt(pQuan.getText());
                    if (quantity <= 0){
                      if (quantity <= 0){ //Quantity must be greater than 0
                    	  msg.setText("Invalid quantity. Enter an integer greater than 0");
                    	  pQuan.setText("");
                      }
                    }
                   
                    Integer price =  Integer.parseInt(pPrice.getText());
                    if (price <= 0){
                      if (price <= 0){ //Quantity must be greater than 0
                        msg.setText("Invalid price. Enter an integer greater than 0");
                        pPrice.setText("");
                      }
                    }                                      
                    if (pName.getText().length() > 0 && pQuan.getText().length() > 0 && pPrice.getText().length() > 0) {
                    	String[] item = {ID, quantity.toString(), price.toString()};
                        UserLogin.products.add(item); //Adds item to stock list
               		 	msg.setText("New product succesfully added");
                        row = item;
                        model.addRow(row);
                    }
                                      
                 }else {
                	 msg.setText("Fields missing");
                }
            } if(event.getSource() == clear) {
                pName.setText("");
                pQuan.setText("");
                pPrice.setText("");
                buttonGroup.clearSelection();
                radioSelect = 0;
                msg.setText("<<MSG>>>");
            }
        }
    }
	//Prints stock list in frame
	public void updateTable(){
		Object[] row = new Object[0];
    	for (String[] i : UserLogin.products) {
        	row = i;
        	model.addRow(row);
        }
    	return;
    }
	private class RadioListener implements ActionListener
    {
        
        public void actionPerformed(ActionEvent event)
        {
            radioSelect = 1;
        }
    }

    private class AcceptListener implements ItemListener
    {

        public void itemStateChanged(ItemEvent event)
        {
            if(isChecked == 0)
                isChecked = 1;
            else
                isChecked = 0;
        }
    }
}
