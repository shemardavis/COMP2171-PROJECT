import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;


public class Delete extends JFrame {

	private JPanel contentPane;
	private JTextField pID;
	private JLabel msg;
	private JButton enter, clear;
	private JButton btnNewButton;
	private JTable table;
	private JScrollPane scrollPane;
    DefaultTableModel model;



	/**
	 * Create the frame.
	 */
	public Delete() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 901, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel messagePanel = new JPanel();
        msg = new JLabel("Enter product ID for item you wish to delete.");
        msg.setFont(new Font("Tahoma", Font.PLAIN, 20));
        messagePanel.add(msg);
        messagePanel.setPreferredSize(new Dimension(349,40));
        
		JPanel inputPanel = new JPanel();
        pID = new JTextField(30);
        pID.setBounds(146, 43, 443, 43);
        inputPanel.setLayout(null);
        JLabel idLabel = new JLabel("Product ID:");
        idLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        idLabel.setBounds(10, 30, 190, 63);

        inputPanel.add(idLabel);
        inputPanel.add(pID);
        inputPanel.setPreferredSize(new Dimension(349,120));
        

        JPanel btnPanel = new JPanel();
        ButtonListener buttonListener = new ButtonListener();
         btnPanel.setPreferredSize(new Dimension(349,40));

        
        getContentPane().add(messagePanel, BorderLayout.NORTH);
        getContentPane().add(inputPanel);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 127, 857, 279);
        inputPanel.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        model = new DefaultTableModel();
        Object[] column = {"Product ID", "Product Quantity", "Product Price"};
        model.setColumnIdentifiers(column);
        table.setModel(model);
        scrollPane.setViewportView(table);
        
        updateTable();
        
        getContentPane().add(messagePanel, BorderLayout.NORTH);
        getContentPane().add(inputPanel);
        enter = new JButton("Enter");
        enter.setFont(new Font("Tahoma", Font.PLAIN, 15));
        enter.setBounds(768, 10, 82, 27);
        inputPanel.add(enter);
        clear = new JButton("Clear");
        clear.setBounds(768, 49, 82, 27);
        inputPanel.add(clear);
        clear.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        btnNewButton = new JButton("Back");
        btnNewButton.setBounds(768, 86, 82, 27);
        inputPanel.add(btnNewButton);
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ModifyList m = new ModifyList();
        		m.setVisible(true);
        	}
        });
        clear.addActionListener(buttonListener);
        enter.addActionListener(buttonListener);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);
        
	}
	
	
	//Button actions
	private class ButtonListener implements ActionListener
	{

	    public void actionPerformed(ActionEvent event)
	    {
	        if(event.getSource() == enter)
	        {
	        	if(pID.getText().length() >0) { //Ensures ID is entered
	        		for (String i[] : UserLogin.products){
	        			if (i[0].equals(pID.getText())){
	        				int index = UserLogin.products.indexOf(i); //Finds product location in list
	        		        UserLogin.products.remove(index);
	        		        msg.setText("Item deleted");
	        		        model.setRowCount(0);
	        		        updateTable();
	        		      }
	        		    }
	        	}else {
	        		msg.setText("Enter product ID");
	        	}
	        }else {
	        	pID.setText("");
	        }
	        
	      }
	    }
	
	//Displays new list
		Object[] row = new Object[0];
		public void updateTable(){
	    	for (String[] i: UserLogin.products) {
	        	row = i;
	            model.addRow(row);
	    	}
	    	return;
    }
}
	

  
