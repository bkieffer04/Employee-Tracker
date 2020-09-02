import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
/**
 * @author (Brian Kieffer)
 * @version (08/19/2020)
 * 
 * The Login GUI asks for a username and password, if the program does not recognize the username or if the 
 * password does not match with the username it will display an error message to the user. If the user inputs the 
 * correct the program will go through a process of checking the User object and seeing what role the user has.
 * If it is an executive like a CEO, CFO, COO it will then display the executive menu. If it checks the user's role
 * and sees that they are an Employee then it will display the employee's menu of options.
 * 
 */
public class Login_GUI implements ActionListener
{
    private JFrame frame;
    private JPanel panel;
    
    private JLabel userName, password, error;
    
    private JTextField userNameText;
    private JPasswordField passwordText;
    
    private JButton login;
    
    private EmployeeList xmlList = new EmployeeList();
    /**
     * This GUI creates the log in display and asks for the user to input a username and password.
     */
    public Login_GUI()
    {
        frame = new JFrame("Login");
        panel = new JPanel();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 250);
        frame.add(panel);
        
        userName = new JLabel("Enter Username");
        userName.setBounds(50,50,180,25);
        panel.add(userName);
        
        password = new JLabel("Enter Password");
        password.setBounds(50,100,180,25);
        panel.add(password);
        
        userNameText = new JTextField();
        userNameText.setBounds(150,50,180,25);
        panel.add(userNameText);
        
        passwordText = new JPasswordField();
        passwordText.setBounds(150,100,180,25);
        panel.add(passwordText);
        
        login = new JButton("Login");
        login.setBounds(90,150,165,25);
        login.addActionListener(this);
        panel.add(login);
        
        error = new JLabel("");
        error.setBounds(50,175,300,25);
        panel.add(error);
        
        panel.setLayout(null);
        
        frame.setVisible(true);
    }
    /**
     * There are two different paths the program could go down when a user inputs information. If the username
     * or password do not match then an error message will pop up. If the inputs are correct it will then detect
     * which role you work and either take you down the path of the executive menu, or it will take you down the
     * employee menu path.
     */
    public void actionPerformed(ActionEvent e)
    {
        if(userNameText.getText().equals("") || passwordText.getText().equals(""))
        {
            error.setText("Unsucessful, one or more fields are empty!");
        }
        else
        {
            if(searchUserInfo(userNameText.getText(), passwordText.getText()))
            {
                User searchedEmployee = xmlList.giveEmployeeInfoByUserName(userNameText.getText());
                if(searchedEmployee.getRole().equals("Employee"))
                {
                    EmployeeDirectory_GUI employeeTerminal = new EmployeeDirectory_GUI(searchedEmployee);
                    frame.setVisible(false);
                }
                else
                {
                    ExecutiveDirectory_GUI execMenu = new ExecutiveDirectory_GUI(searchedEmployee);
                    frame.setVisible(false);
                }
            }
            else
            {
                error.setText("Unsucessful, username or password is incorrect.");
            }
        }
    }
    /**
     * This is my method to figure out if the password and username match a user in our list. The xmlList is an 
     * object of my employeeList class. The EmployeeList class consist of nodes so I needed to convert the node
     * data back into users to access their instance variables. 
     */
    public boolean searchUserInfo(String userName, String password)
    {
        User [] searchList = xmlList.sendEmployeeList();
        for(int i = 0; i < searchList.length; i++)
        {
            if((userName.equals(searchList[i].getUserName())) &&(
            password.equals(searchList[i].getPassword())))
            {
                return true;
            }
            else
            {
                continue;
            }
        }
        return false;
    }
}
