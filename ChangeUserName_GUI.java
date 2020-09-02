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
 * This class is one of the main GUI in the employee menu. This class gives an employee the opportunity to change
 * their username if they are not happy with their initial username. Once the employee goes into this GUI 
 * they will have to input their original username, then create a new username and then confirm the new one. Once
 * submitted the program will find the user in the list and then change their username and be saved. 
 * 
 */
public class ChangeUserName_GUI implements ActionListener
{
    private JFrame frame;
    private JPanel panel;
    
    private JTextField currentNameInput, newNameInput, confirmInput;
    
    private JLabel displayInfo;
    
    private EmployeeList searchThroughList = new EmployeeList();
    
    private User user;
    /**
     * The constructor creates the Change Username window.
     */
    public ChangeUserName_GUI(User currentUser)
    {
        user = currentUser;
        
        frame = new JFrame("Change UserName");
        panel = new JPanel();
        
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        
        panel.setLayout(null);
        
        JLabel currentUserName = new JLabel("What is your current Username?");
        currentUserName.setBounds(15,50,185,25);
        panel.add(currentUserName);
        
        JLabel newUserName = new JLabel("What is your new Username?");
        newUserName.setBounds(15,75,185,25);
        panel.add(newUserName);
        
        JLabel confirmUserName = new JLabel("Confirm new Username:");
        confirmUserName.setBounds(15,100,165,25);
        panel.add(confirmUserName);
        
        currentNameInput = new JTextField();
        currentNameInput.setBounds(210,50,125,25);
        panel.add(currentNameInput);
        
        newNameInput = new JTextField();
        newNameInput.setBounds(210,75,125,25);
        panel.add(newNameInput);
        
        confirmInput = new JTextField();
        confirmInput.setBounds(210,100,125,25);
        panel.add(confirmInput);
        
        JButton search = new JButton("Done");
        search.setBounds(15,150,100,25);
        search.addActionListener(this);
        panel.add(search);
        
        JButton backToMenu = new JButton("Back to Main Menu");
        backToMenu.setBounds(135,150,180,25);
        backToMenu.addActionListener(this);
        panel.add(backToMenu);
        
        displayInfo = new JLabel("");
        displayInfo.setBounds(25,160,250,100);
        panel.add(displayInfo);
        
        frame.setVisible(true);
    }
    /**
     * Once the done button is pressed, the program will check to see if all fields are filled and both the 
     * new username field and confirm username field are identical. If they are not it will generate an error 
     * message. Otherwise, it will provoke the searchForUserName method, find the employee and then change the 
     * username. 
     */
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == "Done")
        {
            if((searchForUserName(currentNameInput.getText()) == null) || 
            (newNameInput.getText().equals(confirmInput.getText()) == false)) 
            {
                displayInfo.setText("<html>" + "We could not find the current User." + "<br>" + 
                "Or the new Username and the confirmed Username do not match." + "<html>");
            }
            else
            {
                searchForUserName(currentNameInput.getText()).changeUserName(confirmInput.getText());
                
                displayInfo.setText("Saved");
            }
        }
        else
        {
            searchThroughList.exportEmployeeList();
            
            if(user.getRole().equals("Employee"))
            {
                EmployeeDirectory_GUI backToSettings = new EmployeeDirectory_GUI(user);
                frame.setVisible(false);
            }
            else
            {
                ExecutiveDirectory_GUI backToExceMenu = new ExecutiveDirectory_GUI(user);
                frame.setVisible(false);
            }
        }
    }
    public User searchForUserName(String userName)
    {
        User [] searchList = searchThroughList.sendEmployeeList();
        for(int i = 0; i < searchList.length; i++)
        {
            if(userName.equals(searchList[i].getUserName()))
            {
                return searchList[i];
            }
            else
            {
                continue;
            }
        }
        return null;
    }
}

