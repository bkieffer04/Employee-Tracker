import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * @author (Brian Kieffer)
 * @version (08/19/2020)
 * 
 * In this class it is the menu all employees will have access to. It is a window of simple options like change 
 * username, change password, and view their own personal salary. This is also the window executives will see
 * if they press the settings button in the ExecutiveDirectory_GUI.
 * 
 */
public class EmployeeDirectory_GUI implements ActionListener
{
    private JFrame frame;
    private JPanel panel;
    
    private User user;
    /**
     * The constructor creates the window of options for the employee to choose. 
     */
    public EmployeeDirectory_GUI(User currentUser)
    {
        user = currentUser;
        
        frame = new JFrame("Settings");
        panel = new JPanel();
        
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        
        panel.setLayout(null);
        
        JLabel displayUser = new JLabel("Current User: " + user.getFirstName());
        displayUser.setBounds(5,5,185,25);
        panel.add(displayUser);
        
        JButton employeeDirect = new JButton("Change Username");
        employeeDirect.setBounds(50, 50, 185, 25);
        employeeDirect.addActionListener(this);
        panel.add(employeeDirect);
        
        JButton searchForEmployee = new JButton("Change Password");
        searchForEmployee.setBounds(50, 80, 185, 25);
        searchForEmployee.addActionListener(this);
        panel.add(searchForEmployee);
        
        JButton calculateBudget = new JButton("View Salary");
        calculateBudget.setBounds(50,110,185,25);
        calculateBudget.addActionListener(this);
        panel.add(calculateBudget);
        
        JButton logOut = new JButton("Log Out");
        logOut.setBounds(245,10,90,25);
        logOut.addActionListener(this);
        panel.add(logOut);
        
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == "Change Username")
        {
           ChangeUserName_GUI goToListOfEmployees = new ChangeUserName_GUI(user);
           frame.setVisible(false);
        }
        else if(e.getActionCommand() == "Change Password")
        {
            ChangePassword_GUI changePassword = new ChangePassword_GUI(user);
            frame.setVisible(false);
        }
        else if(e.getActionCommand() == "View Salary")
        {
            ViewSalary_GUI userSalary = new ViewSalary_GUI(user);
            frame.setVisible(false);
        }
        else if(e.getActionCommand() == "Log Out")
        {
            MainMenu_GUI backToStart = new MainMenu_GUI();
            frame.setVisible(false);
        }
    }
}
