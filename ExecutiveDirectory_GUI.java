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
 * This Class is GUI for the executive menu. This is the menu the business owner, CEO, CFO, COO will see. 
 * This displays a variety of optioins for executives to view. The options are to see a list of all the employees
 * organized for first name, search for a specific employee, remove an employee, see the annual expense of all the
 * employees, and the option to create a new user. 
 * 
 */
public class ExecutiveDirectory_GUI implements ActionListener
{
    private JFrame frame;
    private JPanel panel;
    
    private User user;
    /**
     * This constructor creates the display of different options for the executive user to press. 
     */
    public ExecutiveDirectory_GUI(User currentUser)
    {
        user = currentUser;
        
        frame = new JFrame("Menu Directory");
        panel = new JPanel();
        
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        
        panel.setLayout(null);
        
        JLabel displayUser = new JLabel("Current User: " + user.getFirstName());
        displayUser.setBounds(5,5,185,25);
        panel.add(displayUser);
        
        JButton employeeDirect = new JButton("Employee Directory");
        employeeDirect.setBounds(50, 50, 185, 25);
        employeeDirect.addActionListener(this);
        panel.add(employeeDirect);
        
        JButton searchForEmployee = new JButton("Search For An Employee");
        searchForEmployee.setBounds(50, 80, 185, 25);
        searchForEmployee.addActionListener(this);
        panel.add(searchForEmployee);
        
        JButton calculateBudget = new JButton("Calculate Expenses");
        calculateBudget.setBounds(50,110,185,25);
        calculateBudget.addActionListener(this);
        panel.add(calculateBudget);
        
        JButton resetEmployees = new JButton("Remove Employee");
        resetEmployees.setBounds(50, 140, 185, 25);
        resetEmployees.addActionListener(this);
        panel.add(resetEmployees);
        
        JButton addNewUser = new JButton("Add New User");
        addNewUser.setBounds(50,170,185,25);
        addNewUser.addActionListener(this);
        panel.add(addNewUser);
        
        JButton logOut = new JButton("Log Out");
        logOut.setBounds(245,10,90,25);
        logOut.addActionListener(this);
        panel.add(logOut);
        
        JButton settings = new JButton("Settings");
        settings.setBounds(245,50,90,25);
        settings.addActionListener(this);
        panel.add(settings);
        
        frame.setVisible(true);
    }
    /**
     * Depending on which button the user clicks on, they view an entire list of employees, search for an employee,
     * calculate the annual expense of all employees, remove an employee, add a new user, log out, or go to settings,
     * and change their username, password, or view their personal salary. 
     */
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == "Employee Directory")
        {
           DirectoryList_GUI goToListOfEmployees = new DirectoryList_GUI(user);
           frame.setVisible(false);
        }
        else if(e.getActionCommand() == "Search For An Employee")
        {
            SearchForAnEmployee_GUI goToSearchAnEmployee = new SearchForAnEmployee_GUI(user);
            frame.setVisible(false);
        }
        else if(e.getActionCommand() == "Calculate Expenses")
        {
            CalculateBudget_GUI budget = new CalculateBudget_GUI(user);
            frame.setVisible(false);
        }
        else if(e.getActionCommand() == "Remove Employee")
        {
            RemoveEmployee_GUI remove = new RemoveEmployee_GUI(user);
            frame.setVisible(false);
        }
        else if(e.getActionCommand() == "Add New User")
        {
            NewUser_GUI addAnotherNewUser = new NewUser_GUI();
            frame.setVisible(false);
        }
        else if(e.getActionCommand() == "Log Out")
        {
            MainMenu_GUI backToStart = new MainMenu_GUI();
            frame.setVisible(false);
        }
        else if(e.getActionCommand() == "Settings")
        {
            EmployeeDirectory_GUI goToSettings = new EmployeeDirectory_GUI(user);
            frame.setVisible(false);
        }
    }
}
