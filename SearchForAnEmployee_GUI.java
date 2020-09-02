import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * @author (Brian Kieffer)
 * @version (8/28/2020)
 * 
 * In this class the executive user can search for an employee and see all their info. The GUI will display the 
 * employees first and last name, username, password, salary and role. The purpose of this class is in case an 
 * employee forgot their username or password they could go to their manager and be reminded of what their access
 * info is. 
 * 
 */
public class SearchForAnEmployee_GUI implements ActionListener
{
    private JFrame frame;
    private JPanel panel;
    
    private JTextField nameInput;
    
    private JLabel displayInfo;
    
    private EmployeeList searchThroughList = new EmployeeList();
    
    private User user;
    /** 
     * The constructor will create GUI display. 
     */
    public SearchForAnEmployee_GUI(User currentUser)
    {
        user = currentUser;
        
        frame = new JFrame("Employee Search");
        panel = new JPanel();
        
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        
        panel.setLayout(null);
        
        JLabel enterName = new JLabel("Please enter a name");
        enterName.setBounds(25,50,165,25);
        panel.add(enterName);
        
        nameInput = new JTextField();
        nameInput.setBounds(150,50,165,25);
        panel.add(nameInput);
        
        JButton search = new JButton("Search");
        search.setBounds(25,80,100,25);
        search.addActionListener(this);
        panel.add(search);
        
        JButton backToMenu = new JButton("Back to Main Menu");
        backToMenu.setBounds(135,80,180,25);
        backToMenu.addActionListener(this);
        panel.add(backToMenu);
        
        displayInfo = new JLabel("");
        displayInfo.setBounds(25,110,250,100);
        panel.add(displayInfo);
        
        frame.setVisible(true);
    }
    /**
     * Once the user presses the search button the program will go into the binary search tree and see if the
     * employee is in the list. If so, it will describe the info explained above. If the employee is not in 
     * list, then it will display an erro message. 
     */
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == "Search")
        {
            if(searchThroughList.searchForAnEmployee(nameInput.getText()) == false)
            {
                displayInfo.setText("This employee is not in the directory.");
            }
            else
            {
                User employee = searchThroughList.giveEmployeeInfo(nameInput.getText());
                
                String role = employee.getRole();
                String fullName = employee.getFirstName() + " " + employee.getLastName();
                String userName = employee.getUserName();
                String pass = employee.getPassword();
                int sal = employee.getSalary();
               
                displayInfo.setText("<html>" + "Role: " + role + 
                "<br>" + "Salary: $" + sal + "<br>" + "Full Name: " + fullName + 
                "<br>" + "Username: " + userName + "<br>" + 
                "Password: " + pass + "<html>");
            }
        }
        else
        {
            ExecutiveDirectory_GUI backToMenuOfOptions = new ExecutiveDirectory_GUI(user);
            frame.setVisible(false);
        }
    }
}

