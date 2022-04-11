package supplier;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import gui.Menu;
import gui.UserLogin;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.AbstractListModel;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class SupplierManagement extends JFrame {

	private JFrame frame;
	private JTextField supplierTF;
	private JTextField costTF;
	private JTable table;
	private JTextField productTF;
	private JComboBox<String> comboBox;
	private JTextField contactTF;
	private JTextField searchTF;
	private JPanel contentPane;

	

	/**
	 * Create the application.
	 */
	public SupplierManagement() {

	/**
	 * Initialize the contents of the frame.
	 */
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1132, 754);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		contentPane.setBounds(100, 100, 1127, 734);
		contentPane.setLayout(null);
		//contentPane.getContentPane().setLayout(null);
		
		JLabel TitleLabel = new JLabel("SUPPLIER MANAGEMENT");
		TitleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		TitleLabel.setBounds(357, 11, 386, 66);
		contentPane.add(TitleLabel);
		
		JLabel SupplierNameLabel = new JLabel("Supplier Name");
		SupplierNameLabel.setBounds(38, 168, 87, 14);
		contentPane.add(SupplierNameLabel);
		
		JLabel CostLabel = new JLabel("Cost for Supplier");
		CostLabel.setBounds(38, 211, 118, 14);
		contentPane.add(CostLabel);
		
		JLabel ProductTypeLabel = new JLabel("Products Supplied");
		ProductTypeLabel.setBounds(38, 262, 118, 14);
		contentPane.add(ProductTypeLabel);
		
		JLabel DeliverySpeedLabel = new JLabel("Delivery Speed");
		DeliverySpeedLabel.setBounds(38, 319, 87, 14);
		contentPane.add(DeliverySpeedLabel);
		
		supplierTF = new JTextField();
		supplierTF.setBounds(166, 165, 625, 20);
		contentPane.add(supplierTF);
		supplierTF.setColumns(10);
		
		costTF = new JTextField();
		costTF.setColumns(10);
		costTF.setBounds(166, 208, 86, 20);
		contentPane.add(costTF);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 423, 1091, 261);
		contentPane.add(scrollPane);
		
		
		DefaultTableModel myTableModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Supplier Name", "Cost for Supplier", "Products Supplied", "Delivery Speed", "Contact"
				}
			);
		TableRowSorter myTableRowSorter = new TableRowSorter(myTableModel);
		table = new JTable();
		table.setModel(myTableModel);
		table.setRowSorter(myTableRowSorter);
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(99);
		table.getColumnModel().getColumn(1).setPreferredWidth(105);
		table.getColumnModel().getColumn(2).setPreferredWidth(118);
		scrollPane.setViewportView(table);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Very Slow", "Slow", "Normal", "Fast", "Very Fast"}));
		comboBox.setBounds(166, 315, 117, 22);
		contentPane.add(comboBox);
		
		
		
		JButton SaveButton = new JButton("Save");
		SaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model;
				model = (DefaultTableModel) table.getModel();
				model.insertRow(model.getRowCount(), new Object [] {supplierTF.getText(),costTF.getText(),productTF.getText(), comboBox.getSelectedItem(), contactTF.getText()});
				String[] supplier = {supplierTF.getText(),costTF.getText(),productTF.getText(), (String) comboBox.getSelectedItem(), contactTF.getText()};
				UserLogin.suppliers.add(supplier);
				clear();
			}
		});
		SaveButton.setBounds(925, 87, 89, 36);
		contentPane.add(SaveButton);
		
		JButton RemoveButton = new JButton("Remove");
		RemoveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int SelectedRowIndex = table.getSelectedRow();
				model.removeRow(SelectedRowIndex);
				
				 
			}
		});
		RemoveButton.setBounds(925, 177, 89, 36);
		contentPane.add(RemoveButton);
		
		productTF = new JTextField();
		productTF.setColumns(10);
		productTF.setBounds(166, 259, 625, 20);
		contentPane.add(productTF);
		
		contactTF = new JTextField();
		contactTF.setColumns(10);
		contactTF.setBounds(166, 366, 625, 20);
		contentPane.add(contactTF);
		
		JLabel ContactLabel = new JLabel("Contact");
		ContactLabel.setBounds(38, 369, 87, 14);
		contentPane.add(ContactLabel);
		
		searchTF = new JTextField();
		searchTF.setColumns(10);
		searchTF.setBounds(197, 96, 625, 20);
		contentPane.add(searchTF);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchText = searchTF.getText();
				myTableRowSorter.setRowFilter(new MyRowFilter(searchText));
				clearSF();
			}
		});
		searchButton.setBounds(38, 95, 85, 21);
		contentPane.add(searchButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu m = new Menu();
				m.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(925, 259, 89, 36);
		contentPane.add(btnBack);
		
		JButton btnCreateFile = new JButton("Export");
		btnCreateFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supplList();
			}
		});
		btnCreateFile.setBounds(925, 335, 89, 36);
		contentPane.add(btnCreateFile);
		
		
	}
	private void clear() {
		supplierTF.setText(null);
		costTF.setText(null);
		productTF.setText(null);
		comboBox.setSelectedIndex(0);
		contactTF.setText(null);
	}
	
	private void clearSF() {
		searchTF.setText(null);
	}
	
	
	public void supplList() {
		File file = new File("Suppliers.txt");
		 try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // if file already exists will do nothing 
		 try {
			 BufferedWriter out = new BufferedWriter( new FileWriter("Suppliers.txt", true));
		     for (String i[] : UserLogin.suppliers) {
		    	 out.write("Supplier Name: " + i[0]);
		    	 out.write(" | Cost for Supplier: $" + i[1]);
		    	 out.write(" | Product supplied: " + i[2]);
		    	 out.write(" | Delivery speed: " + i[3]);
		    	 out.write(" | Supplier contact: " + i[4]);
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
		 
	}
}
	

