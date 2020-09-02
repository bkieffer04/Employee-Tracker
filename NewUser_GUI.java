import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * @author (Brian Kieffer)
 * @version (08/19/2020)
 * 
 * This class consists of the NewUser GUI. This display window takes the user through the steps of creating a new
 * account. It asks questions like their Role, their salary, first and last name, their username, password and confirm
 * their password. Once created it will take them back to the start and now the user will be able to log in. Once 
 * all fields are completed and the passwords match, the user presses save and now the account will be stored in
 * the EmployeeList object while the user is using the program. Afterwards the accounts are stored in the XML file 
 * I created to store accounts.
 * 
 */
public class NewUser_GUI implements ActionListener
{
    private JFrame frame;
    private JPanel panel;
    
    private JLabel firstName, lastName, userName, password, confirmPassword, error, 
    role, salary;
    
    private JTextField firstNameText, lastNameText, userNameText, passwordText, 
    confirmPasswordText, roleText, salaryText;
    
    private EmployeeList additionalEmployee = new EmployeeList();
    /**
     * The constructor creates the window display to create a new account. 
     */
    public NewUser_GUI()
    {
        frame = new JFrame("New User");
        panel = new JPanel();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 400);
        frame.add(panel);
        
        role = new JLabel("Role");
        role.setBounds(10,5,80,25);
        panel.add(role);
        
        salary = new JLabel("Salary");
        salary.setBounds(10,35,80,25);
        panel.add(salary);
        
        firstName = new JLabel("First Name");
        firstName.setBounds(10,65,80,25);
        panel.add(firstName);
        
        lastName = new JLabel("Last Name");
        lastName.setBounds(10,95,80,25);
        panel.add(lastName);
        
        userName = new JLabel("User Name");
        userName.setBounds(10,125,80,25);
        panel.add(userName);
        
        password = new JLabel("Password");
        password.setBounds(10,155,80,25);
        panel.add(password);
        
        confirmPassword = new JLabel("Confirm Password");
        confirmPassword.setBounds(10,185,180,25);
        panel.add(confirmPassword);
        
        roleText = new JTextField();
        roleText.setBounds(150,5,180,25);
        panel.add(roleText);
        
        salaryText = new JTextField();
        salaryText.setBounds(150,35,180,25);
        panel.add(salaryText);
        
        firstNameText = new JTextField();
        firstNameText.setBounds(150,65,180,25);
        panel.add(firstNameText);
        
        lastNameText = new JTextField();
        lastNameText.setBounds(150,95,180,25);
        panel.add(lastNameText);
        
        userNameText = new JTextField();
        userNameText.setBounds(150,125,180,25);
        panel.add(userNameText);
        
        passwordText = new JPasswordField();
        passwordText.setBounds(150,155,180,25);
        panel.add(passwordText);
        
        confirmPasswordText = new JPasswordField();
        confirmPasswordText.setBounds(150,185,180,25);
        panel.add(confirmPasswordText);
        
        JButton save = new JButton("Save");
        save.setBounds(10,225,80,25);
        save.addActionListener(this);
        panel.add(save);
        
        error = new JLabel("");
        error.setBounds(10,250,300,25);
        panel.add(error);
        
        panel.setLayout(null);
        
        frame.setVisible(true);
    }
    /**
     * Once the user is done creating the account, the program will check each text field and if either a field 
     * is empty, or the passwords do not match the program will display an error message. If the program detects
     * that everything is filled in and passwords match it will create a new User object and then store the User
     * in the EmployeeList object. Once the user goes back to the main menu, the program will store all created 
     * accounts in an XML file.
     */
    public void actionPerformed(ActionEvent e)
    {
        String fullName = (fullName = firstNameText.getText() + " " + lastNameText.getText());
        if(roleText.getText().equals("") || salaryText.getText().equals("") || firstNameText.getText().equals("") 
        || lastNameText.getText().equals("") || userNameText.getText().equals("") 
        || passwordText.getText().equals("") ||
        confirmPasswordText.getText().equals(""))
        {
           error.setText("Unsuccesful, one or more fields are empty!");
        }
        else if(!passwordText.getText().equals(confirmPasswordText.getText()))
        {
            error.setText("Passwords do not match");
        }
        else if(additionalEmployee.searchForAnEmployee(fullName))
        {
            error.setText("User with the same name has already been created.");
        }
        else
        {
            error.setText("Saved");
            additionalEmployee.addEmployee(new User
            (firstNameText.getText(), lastNameText.getText(), userNameText.getText(),
            passwordText.getText(), roleText.getText(), Integer.parseInt(salaryText.getText())));
            
            additionalEmployee.exportEmployeeList();
            
            MainMenu_GUI backToStart = new MainMenu_GUI();
            frame.setVisible(false);
        }
   }
}
