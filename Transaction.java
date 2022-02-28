import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

//import StockList.ButtonListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Transaction extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTextField quantity;
	private JTextField amount;
	private JButton enter, clear, done;
	private JLabel confirmMsg;
	private JLabel lblNewLabel_2;
	private JTextField textField;
	public static double transactionalTotal = 0;
	
	 private final static String DIALOG_TITLE = "Inventory Alert";
	 private final static int DIALOG_ICON = JOptionPane.WARNING_MESSAGE;
	 private JButton btnBack;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Transaction() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 906, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ButtonListener buttonListener = new ButtonListener();
		
		JLabel lblNewLabel = new JLabel("Sales Transaction");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(357, 10, 188, 79);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Quantity:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(129, 175, 96, 37);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Product ID:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(129, 104, 96, 37);
		contentPane.add(lblNewLabel_1_1);
		
		id = new JTextField();
		id.setBounds(288, 99, 309, 47);
		contentPane.add(id);
		id.setColumns(10);
		
		quantity = new JTextField();
		quantity.setColumns(10);
		quantity.setBounds(288, 165, 309, 47);
		contentPane.add(quantity);
		
		enter = new JButton("Enter");
		enter.setFont(new Font("Tahoma", Font.PLAIN, 16));
		enter.setBounds(740, 99, 96, 29);
		enter.addActionListener(buttonListener);
		contentPane.add(enter);
		
		clear = new JButton("Clear");
		clear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		clear.setBounds(740, 151, 96, 29);
		clear.addActionListener(buttonListener);
		contentPane.add(clear);
		
		done = new JButton("Done");
		done.setFont(new Font("Tahoma", Font.PLAIN, 16));
		done.setBounds(740, 202, 96, 29);
		done.addActionListener(buttonListener);
		contentPane.add(done);
		
		JPanel confirmPanel = new JPanel();
        confirmPanel.setBounds(272, 308, 349, 40);
        confirmMsg = new JLabel("<<MSG>>");
        confirmPanel.add(confirmMsg);
        confirmPanel.setPreferredSize(new Dimension(349,40));
        contentPane.add(confirmPanel);
        
        lblNewLabel_2 = new JLabel("Amount collected:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblNewLabel_2.setBounds(129, 232, 141, 37);
        contentPane.add(lblNewLabel_2);
        
        
        amount = new JTextField();
		amount.setColumns(10);
		amount.setBounds(288, 230, 309, 47);
		contentPane.add(amount);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu m = new Menu();
				m.setVisible(true);
				dispose();
			}
			
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack.setBounds(740, 261, 96, 29);
		contentPane.add(btnBack);
	}

	
	//Total
	public static double Total (String quantity, String price){
	  double total;
	  int q = Integer.parseInt(quantity);
	  int p = Integer.parseInt(price);
	  total = q * p;
	  return (total);
	}
	
	
		//Discount
	public static double Discount(double total){
	  double disc, calcdisc, discount;
	  disc = 0.05;
	  calcdisc = total * disc;
	  discount = total - calcdisc;
	  return (discount);
	}
				
	//Tax
	public static double Tax(double total){
	  double gct = 0.15;
	  double tax = total * gct;
	  double ttl = total + tax;
	  return (ttl);
	}
		
				
	//Change to be given to customer
	public static double Change(double amtGiven, double amtPaid){
	  double change;
	  change = amtGiven - amtPaid;
	  return (change);
	}

    public static void createTransLog(ArrayList<String[]> temp, double sum, String amtGiven, double change){
      Random random = new Random();
      Integer ID = random.nextInt(999 - 100);
      Object[] h = temp.toArray();
      String[] holder = (String[]) Arrays.copyOf(h, h.length + 3);
      holder[holder.length - 3] = Double.toString(sum);
      holder[holder.length - 2] = amtGiven;
      holder[holder.length - 1] = Double.toString(change);
      String[] trans = new String[holder.length+1];
      int j = 0;
      for(int i = 0; i < holder.length+1; i++) {
        if(i == 0) {
          trans[i] = ID.toString();
        } else{
          trans[i] = holder[j];
          j++;
        }
      }
      UserLogin.transLog.add(trans);
    }
	
	private class ButtonListener implements ActionListener
    {
		double sum = 0;
		String name, quan;
		ArrayList<String[]> bought = new ArrayList<String[]>();
		DecimalFormat df = new DecimalFormat("0.00");
    
        public void actionPerformed(ActionEvent event)
        {
        	boolean inList = false;
        	if(event.getSource() == enter){
        		name = id.getText();
        		for (String[] i: UserLogin.products) {
        			if (i[0].equals(name)) {
        				inList = true;
        			}
        		}
        		quan = quantity.getText();
        		if ((Integer.parseInt(quan) > 0) && (inList == true)) {
	        		System.out.println("ID " + name + "Quan " + quan);
	        		String[] item = {name, quan};
	        		bought.add(item);
	        		confirmMsg.setText("Item registered as bought");
	        		id.setText("");
	            	quantity.setText("");
        		}else {
        			confirmMsg.setText("Invalid entry.");
        			id.setText("");
	            	quantity.setText("");
        		}
            }
            if(event.getSource() == clear) {
            	id.setText("");
            	quantity.setText("");
            	confirmMsg.setText("<<MSG>>");
            }
            if(event.getSource() == done) {
            	for (String[] s : bought){
                    for (String i[] : UserLogin.products){
                      if (i[0].equals(s[0])){
                        double ttl = Total(s[1], i[2]);//gets price from Stock List
                        if (Integer.parseInt(s[1]) >= 3){
                          ttl = Discount(ttl);
                        }
                        sum = sum + Tax(ttl);
                        Integer newQuan = Integer.parseInt(i[1]) - Integer.parseInt(s[1]);
                        if (newQuan <= 2) {
                    		  JOptionPane.showMessageDialog(null, "This product has reached the set minimum quantity!", DIALOG_TITLE, DIALOG_ICON);
                    	  }
                      }
                    }
                    //count each product type
                    boolean found = false;
                    Integer tempCounter;
                    for (String[] a : UserLogin.transLog){
                      if (a[0].equals(s[0])){
                        found = true;
                        tempCounter = (Integer.parseInt(a[1]))+(Integer.parseInt(s[1]));
                        a[1] = tempCounter.toString();
                      }
                    }
                    //if item not already in list
                    if (found == false){
                      String[] item = {s[0], s[1]};
                      UserLogin.transLog.add(item);
                    }
            }
            	if (Integer.parseInt(amount.getText()) > 0) {
            		double change = Change(Double.parseDouble(amount.getText()), sum);
            		System.out.println("amount " + amount.getText());
            		confirmMsg.setText("Total cost: " + df.format(sum) + "      Change: " + df.format(change));
            	
            		createTransLog(bought, sum, amount.getText(), change);
            		transactionalTotal = transactionalTotal + sum;
            	}else {
            		confirmMsg.setText("Invalid amount");
            	}
         }
      }
    }
}


            
            
            
            
            
    
