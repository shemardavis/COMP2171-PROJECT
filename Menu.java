import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.print.PrinterException;
import java.io.FileNotFoundException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;


public class Menu extends JFrame {

	private JPanel contentPane;
	
    private JButton stockList, salesReport, cashier, suppManagement;
    private int radioSelect = 0;
    private int isChecked = 0;
    private ButtonGroup buttonGroup;
    private JButton btnNewButton;
    private JLabel lblNewLabel;


	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 909, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0,1));
        

        JPanel radioPanel = new JPanel();

        RadioListener radioListener = new RadioListener();

        JPanel btnPanel = new JPanel();
        stockList = new JButton("View and Modify List");
        stockList.setFont(new Font("Tahoma", Font.PLAIN, 16));
        stockList.setBounds(127, 103, 186, 50);
        salesReport = new JButton("View and Export Report");
        salesReport.setFont(new Font("Tahoma", Font.PLAIN, 16));
        salesReport.setBounds(127, 222, 221, 50);
        cashier = new JButton("Conduct Sales Transaction");
        cashier.setFont(new Font("Tahoma", Font.PLAIN, 16));
        cashier.setBounds(503, 222, 264, 50);
        suppManagement = new JButton("View and Modify Suppliers");
        suppManagement.setFont(new Font("Tahoma", Font.PLAIN, 16));
        suppManagement.setBounds(546, 103, 221, 50);
        ButtonListener buttonListener = new ButtonListener();
        stockList.addActionListener(buttonListener);
        salesReport.addActionListener(buttonListener);
        cashier.addActionListener(buttonListener);
        suppManagement.addActionListener(buttonListener);
        btnPanel.setLayout(null);

        btnPanel.add(stockList);
        btnPanel.add(salesReport);
        btnPanel.add(cashier);
        btnPanel.add(suppManagement);
        btnPanel.setPreferredSize(new Dimension(349,40));

        getContentPane().add(inputPanel);
        getContentPane().add(radioPanel);
        getContentPane().add(btnPanel);
        
        lblNewLabel = new JLabel("What would you like to do?");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(328, 10, 248, 59);
        btnPanel.add(lblNewLabel);

       
        setPreferredSize(new Dimension(400, 350));
	}
	
	//Button actions
	private class ButtonListener implements ActionListener
    {
    
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == stockList) {
            	StockList st = new StockList();
            	st.setVisible(true);
            	dispose();
            }
            if(event.getSource() == salesReport) {
            	ReportFrame rf = new ReportFrame();
            	rf.setVisible(true);
            	dispose();
            }
           if(event.getSource() == suppManagement) {
            	SupplierManagement sm = new SupplierManagement();
            	sm.setVisible(true);
            	dispose();
            }
            if(event.getSource() == cashier) {
            	Cashier c = new Cashier();
            	c.setVisible(true);
            	dispose();
            }
               
        }
    }
	private class RadioListener implements ActionListener
    {
        
        public void actionPerformed(ActionEvent event)
        {
            radioSelect = 1;
        }
    }

	//Ensures position is selected by user
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

