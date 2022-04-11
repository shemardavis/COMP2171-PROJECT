package inventory;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Font;

public class ModifyList extends JFrame {

	private JPanel contentPane;
	
		
    private JButton add, delete, edit;
    private JLabel msg;
    private int radioSelect = 0;
    private int isChecked = 0;
    private ButtonGroup buttonGroup;
    private JButton btnNewButton;
    private JButton btnNewButton_1;

	

	/**
	 * Create the frame.
	 */
	public ModifyList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 920, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
        ButtonListener buttonListener = new ButtonListener();
        contentPane.setLayout(null);
        
        btnNewButton_1 = new JButton("Back");
        btnNewButton_1.setFont(new Font("Segoe UI Variable", Font.PLAIN, 15));
        btnNewButton_1.setBounds(808, 388, 73, 52);
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		StockList st = new StockList();
            	st.setVisible(true);
        	}
        });
        contentPane.add(btnNewButton_1);
        add = new JButton("Add item");
        add.setFont(new Font("Segoe UI Variable", Font.PLAIN, 16));
        add.setBounds(73, 139, 111, 52);
        contentPane.add(add);
        delete = new JButton("Delete item");
        delete.setFont(new Font("Segoe UI Variable", Font.PLAIN, 16));
        delete.setBounds(394, 146, 142, 39);
        contentPane.add(delete);
        edit = new JButton("Edit item");
        edit.setFont(new Font("Segoe UI Variable", Font.PLAIN, 16));
        edit.setBounds(728, 146, 111, 39);
        contentPane.add(edit);
        
        JLabel lblNewLabel = new JLabel("Select Option");
        lblNewLabel.setFont(new Font("Segoe UI Variable", Font.PLAIN, 17));
        lblNewLabel.setBounds(424, 36, 111, 40);
        contentPane.add(lblNewLabel);
        edit.addActionListener(buttonListener);
        delete.addActionListener(buttonListener);
        add.addActionListener(buttonListener);

       
        setPreferredSize(new Dimension(400, 350));
	}
	
	
//Button actions
private class ButtonListener implements ActionListener
{

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == add)
        {
        	AddItem at = new AddItem();
    		at.setVisible(true);
    		dispose();
        }else {
        	if(event.getSource() == delete) {
        		Delete d = new Delete();
        		d.setVisible(true);
        		dispose();
        	}else {
        		if(event.getSource() == edit) {
        			Edit e = new Edit();
        			e.setVisible(true);
        			dispose();
        		}
        	}
        }
      }
    }
 }


