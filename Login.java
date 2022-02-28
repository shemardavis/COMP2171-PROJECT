import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JPanel
{
    private JTextField uname;
    private JButton submit, clear;
    private static JLabel confirmMsg;
    private ButtonGroup buttonGroup;
    private JButton btnNewButton;
    JLabel passwordLabel = new JLabel("Password");
    JPasswordField passwordField = new JPasswordField();
    JCheckBox showPassword = new JCheckBox("Show Password");
    private JButton btnNewButton_1;

    
    //Creates frame
    public Login()
    {

        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(144, 41, 638, 176);
        
        uname = new JTextField(30);
        uname.setBounds(162, 21, 456, 38);
        JLabel uLabel = new JLabel("Username:");
        uLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        uLabel.setBounds(0, 18, 456, 38);
        JLabel pLabel = new JLabel("Password:");
        inputPanel.setLayout(null);

        inputPanel.add(uLabel);
        inputPanel.add(uname);
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        passwordLabel.setBounds(0, 83, 349, 24);
        inputPanel.add(passwordLabel);
        passwordField.setBounds(162, 78, 456, 40);
        inputPanel.add(passwordField);
        showPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
        showPassword.setBounds(162, 126, 349, 24);
        inputPanel.add(showPassword);
        inputPanel.setPreferredSize(new Dimension(349,120));

        

        JPanel radioPanel = new JPanel();
        radioPanel.setBounds(336, 251, 267, 31);


        JPanel btnPanel = new JPanel();
        btnPanel.setBounds(296, 324, 349, 40);
        submit = new JButton("Submit");
        clear = new JButton("Clear");
        ButtonListener buttonListener = new ButtonListener();
        submit.addActionListener(buttonListener);
        clear.addActionListener(buttonListener);
        showPassword.addActionListener(buttonListener);

        btnPanel.add(submit);
        btnPanel.add(clear);
        btnPanel.setPreferredSize(new Dimension(349,40));

        JPanel confirmPanel = new JPanel();
        confirmPanel.setBounds(296, 386, 349, 40);
        confirmMsg = new JLabel("<<MSG>>");
        confirmPanel.add(confirmMsg);
        confirmPanel.setPreferredSize(new Dimension(349,40));
        setLayout(null);
        
        btnNewButton_1 = new JButton("Next");
        btnNewButton_1.setBounds(423, 461, 85, 21);
        btnNewButton_1 = new JButton("Next");
        btnNewButton_1.addActionListener(buttonListener);
        JPanel next = new JPanel();
        next.setBounds(296, 448, 349, 40);
        next.add(btnNewButton_1);

        

        add(inputPanel);
        add(radioPanel);
        add(btnPanel);
        add(confirmPanel);
        add(next);

       
        setPreferredSize(new Dimension(926, 532));
        
        
        
        
        
    }
    public static boolean isValidPassword(String password)
    {
            boolean isValid = true;
            if (password.length() < 8)
            {
                    confirmMsg.setText("Password must be more than 8 characters in length.");
                    isValid = false;
            }
            String upperCaseChars = "(.*[A-Z].*)";
            if (!password.matches(upperCaseChars ))
            {
            	confirmMsg.setText("Password must have atleast one uppercase character");
                    isValid = false;
            }
            String lowerCaseChars = "(.*[a-z].*)";
            if (!password.matches(lowerCaseChars ))
            {
            	confirmMsg.setText("Password must have atleast one lowercase character");
                    isValid = false;
            }
            String numbers = "(.*[0-9].*)";
            if (!password.matches(numbers ))
            {
            	confirmMsg.setText("Password must have atleast one number");
                    isValid = false;
            }
            String specialChars = "(.*[@,#,$,%].*$)";
            if (!password.matches(specialChars ))
            {
            	confirmMsg.setText("Password must have atleast one special character among @#$%");
                    isValid = false;
            }
            return isValid; 
    }

    //Button actions
    private class ButtonListener implements ActionListener
    {
    
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == submit)
            {
            	String pwdText = passwordField.getText();
                if(uname.getText().length() > 0 && pwdText.length() > 0) //Ensures all fields are completed
                {
                    if (isValidPassword(pwdText)){ //Check password is valid
                    		confirmMsg.setText("New user succesfully added");
                    }
                } else {
                    confirmMsg.setText("Fields missing");
                }
            } if (event.getSource() == clear) {
                uname.setText("");
                //pwd.setText("");
                passwordField.setText("");
                buttonGroup.clearSelection();
                confirmMsg.setText("<<MSG>>>");
            }
            if (event.getSource() == showPassword) {
                if (showPassword.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                }


            }
            if (event.getSource() == btnNewButton_1){
            	String pwdText = passwordField.getText();
                if(uname.getText().length() > 0 && pwdText.length() > 0 && isValidPassword(pwdText))
                {
                	Menu m = new Menu();
                	m.setVisible(true);
                } else {
                    confirmMsg.setText("Fields missing");
                }
           }
            
        }
    }
   
}
