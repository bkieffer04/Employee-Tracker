import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * @author (Brian Kieffer)
 * @version (8/28/2020)
 * 
 * In this class it is the second of three options for employees to view in their menu. This is identical to the 
 * change username class, but it now allows the employee to change their password. 
 * 
 */
public class ChangePassword_GUI implements ActionListener
{
    private JFrame frame;
    private JPanel panel;
    
    private JTextField currentInput;
    private JPasswordField newPasswordInput, confirmInput;
    
    private JLabel displayInfo;
    
    private EmployeeList searchThroughList = new EmployeeList();
    
    private User user;
    public ChangePassword_GUI(User currentUser)
    {
        user = currentUser;
        
        frame = new JFrame("Change Password");
        panel = new JPanel();
        
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        
        panel.setLayout(null);
        
        JLabel currentPassword = new JLabel("What is your current Password?");
        currentPassword.setBounds(15,50,185,25);
        panel.add(currentPassword);
        
        JLabel newPassword = new JLabel("What is your new Password?");
        newPassword.setBounds(15,75,185,25);
        panel.add(newPassword);
        
        JLabel confirmPassword = new JLabel("Confirm new Password:");
        confirmPassword.setBounds(15,100,165,25);
        panel.add(confirmPassword);
        
        currentInput = new JTextField();
        currentInput.setBounds(210,50,125,25);
        panel.add(currentInput);
        
        newPasswordInput = new JPasswordField();
        newPasswordInput.setBounds(210,75,125,25);
        panel.add(newPasswordInput);
        
        confirmInput = new JPasswordField();
        confirmInput.setBounds(210,100,125,25);
        panel.add(confirmInput);
        
        JButton search = new JButton("Done");
        search.setBounds(15,125,100,25);
        search.addActionListener(this);
        panel.add(search);
        
        JButton backToMenu = new JButton("Back to Main Menu");
        backToMenu.setBounds(135,125,180,25);
        backToMenu.addActionListener(this);
        panel.add(backToMenu);
        
        displayInfo = new JLabel("");
        displayInfo.setBounds(25,140,250,100);
        panel.add(displayInfo);
        
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == "Done")
        {
            if((searchForPassword(currentInput.getText()) == null) || 
            (newPasswordInput.getText().equals(confirmInput.getText()) == false)) 
            {
                displayInfo.setText("<html>" + "We could not find the User with this password." + "<br>" + 
                "Or the new Password and the confirmed Password do not match." + "<html>");
            }
            else
            {
                searchForPassword(currentInput.getText()).changePassword(confirmInput.getText());
                
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
    public User searchForPassword(String password)
    {
        User [] searchList = searchThroughList.sendEmployeeList();
        for(int i = 0; i < searchList.length; i++)
        {
            if(password.equals(searchList[i].getPassword()))
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

