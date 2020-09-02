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
 * In this class an executive can remove an employee from the employer list in the case of the employee being 
 * fired. Once an employee is removed he is erased from the list, and if it was a mistake of the person being 
 * erased, then the user would have to be recreated.
 * 
 */
public class RemoveEmployee_GUI implements ActionListener
{
    private JFrame frame;
    private JPanel panel;
    
    private JTextField nameInput;
    
    private JLabel displayInfo;
    
    EmployeeList eraseEmployee = new EmployeeList();
    
    private User user;
    /**
     * The constructor creates a window to remove an employee.
     */
    public RemoveEmployee_GUI(User currentUser)
    {
        user = currentUser;
        
        frame = new JFrame("Remove Employee");
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
        
        JButton search = new JButton("Remove");
        search.setBounds(25,80,100,25);
        search.addActionListener(this);
        panel.add(search);
        
        JButton backToMenu = new JButton("Back to Main Menu");
        backToMenu.setBounds(135,80,180,25);
        backToMenu.addActionListener(this);
        panel.add(backToMenu);
        
        displayInfo = new JLabel("");
        displayInfo.setBounds(25,110,250,50);
        panel.add(displayInfo);
        
        frame.setVisible(true);
    }
    /**
     * Once the remove button is pressed it uses the removingEmployee method.
     */
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == "Remove")
        {
            if(eraseEmployee.searchForAnEmployee(nameInput.getText()) == false)
            {
                displayInfo.setText("This employee is not in the directory.");
            }
            else
            {
                removingEmployee(nameInput.getText());
                displayInfo.setText("Done");
            }
        }
        else
        {
            ExecutiveDirectory_GUI backToMenuOfOptions = new ExecutiveDirectory_GUI(user);
            frame.setVisible(false);
        }
    }
    /**
     * This method will go into the employee list and will find the specific employee and erase them.
     */
    public void removingEmployee(String name)
    {
        eraseEmployee.delete(name);
    }
}

