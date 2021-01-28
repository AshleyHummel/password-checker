package io.github.AshleyHummel.passwordchecker;

/*
 * Ashley Hummel
 * DATE: 1/27/21
 * Description: Create a username and valid password, then enter correct login details.
 */

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class PasswordChecker 
{
	public static void main(String[] args) 
	{
		//set JOptionPane dimensions
		UIManager.put("OptionPane.minimumSize",new Dimension(400, 200)); 
		
		boolean success = false;
		
		//create and add panel components
		JTextField user = new JTextField();
		user.setPreferredSize(new Dimension(200, 10));
		JPasswordField pass = new JPasswordField();
		user.setPreferredSize(new Dimension(200, 10));
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 0));
		
		panel.add(new JLabel("Username:"));
		panel.add(user);
		
		panel.add(new JLabel("Password:"));
		panel.add(pass);
		
		//present instructions to create a valid username and password
		JOptionPane.showMessageDialog(null, "Create a valid username and password."
				+ "\nValid passwords have:"
				+ "\n\t-At least 7 characters."
				+ "\n\t-At least 1 non-letter."
				+ "\n\t-No spaces.", "Instructions", JOptionPane.PLAIN_MESSAGE, null);
		
		//JOptionPane to create login credentials
		int option = JOptionPane.showConfirmDialog(null, panel, "Create Account", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
		
		String userInput = user.getText();
        String passInput = pass.getText();
        
		if (option == JOptionPane.OK_OPTION) {
            boolean space = false;
            boolean nonletter = false;
            
            //check validity of created password
            for(int i = 0; i < passInput.length(); i++) {
            	if(Character.isWhitespace(passInput.charAt(i))) {
            		space = true;
            	}
            	if(!Character.isLetter(passInput.charAt(i))) {
            		nonletter = true;
            	}
            }
			
            //if created password is less than 7 characters, has a space, or does not contain a non-letter --> INVALID
            if (passInput.length() < 7) {
				System.out.println("Invalid password! Please use more than 7 characters.\n");
			}
            else if (space) {
				System.out.println("Invalid password! Do not include a space.\n");
			}
            else if (!nonletter) {
				System.out.println("Invalid password! Please use at least 1 non-letter.\n");
			}
            
            else {
            	int attempts = 3;
            	JLabel attemptsMessage = new JLabel();
            	panel.add(attemptsMessage);
            	
            	do {
            		user.setText(""); //clear username and password fields for each attempt
                	pass.setText("");
                	
                	//JOptionPane to enter login credentials
                	JOptionPane.showConfirmDialog(null, panel, "LOGIN", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
                	
                	String userLogin = user.getText();
                    String passLogin = pass.getText();
                    
                    //check if entered username/password equals previous inputted credentials
                    if(userLogin.equals(userInput) && passLogin.equals(passInput)) {
                    	success = true;
                    	System.out.println("Login successful!");
                    }
                    
                    if(attempts > 1) {
                    	attemptsMessage.setText("Unknown login credentials. " + attempts + " attempts left!");
                    }
                    else {
                    	attemptsMessage.setText("Unknown login credentials. " + attempts + " attempt left!"); //"attempts" is changed to "attempt" when there's 1 attempt left
                    }
                	attempts--; //subtract number of attempts after each try
            	}
            	while(!success && attempts >= 0);
            }
            
            if(!success) System.out.println("Login failed.");
		}
		else System.out.println("Login cancelled.");	

	}

}


