import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * @author (Brian Kieffer)
 * @version (8/19/2020)
 * 
 * This is the first class of many for my Human Resources Program.
 * With this program a CEO or Business Owner can create a list of their employees. Each employee can have a sign in
 * and can adjust their username, password, and view their salary. As the owner or CEO you have a couple more 
 * features such as seeing an ordered list of all the employees, search for a specific employee, if an executive
 * fires an employee they can use the program and remove them off the list. They also have the ability to create 
 * new users within the main menu and at the end of the year can view the total expenses of all the employees 
 * salaries. 
 * 
 */
public class MainMenu_GUI implements ActionListener
{
    private JFrame frame;
    private JPanel panel;
    /**
     * This constructor creates the Starting display of options of Logging in or creating a new user.
     */
    public MainMenu_GUI()
    {
        frame = new JFrame("Main Menu");
        panel = new JPanel();
        
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        
        panel.setLayout(null);
        
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 50, 165, 25);
        loginButton.addActionListener(this);
        panel.add(loginButton);
        
        JButton newUserButton = new JButton("New User");
        newUserButton.setBounds(100, 100, 165, 25);
        newUserButton.addActionListener(this);
        panel.add(newUserButton);
        
        frame.setVisible(true);
    }
    /**
     * Depending on what button the user presses, a button will guide them to log in or begin the process of 
     * creating a new user.
     */
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == "Login")
        {
           Login_GUI goingToLoginGUI = new Login_GUI();
           
           frame.setVisible(false);
        }
        else if(e.getActionCommand() == "New User")
        {
            NewUser_GUI setUpNewUser = new NewUser_GUI();
            
            frame.setVisible(false);
        }
    }
    public static void main(String [] args)
    {
        MainMenu_GUI start = new MainMenu_GUI();
    }
}
