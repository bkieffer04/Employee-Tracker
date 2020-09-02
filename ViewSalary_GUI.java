import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * @author (Brian Kieffer)
 * @version (8/28/2020)
 * 
 * In this class it is a simple option for the employee to view their personal salary. 
 * 
 */
public class ViewSalary_GUI implements ActionListener
{
    private JFrame frame;
    private JPanel panel;
    
    private JLabel salary;
    
    private User user;
    public ViewSalary_GUI(User currentUser)
    {
        user = currentUser;
        
        frame = new JFrame("Change Password");
        panel = new JPanel();
        
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        
        panel.setLayout(null);
        
        JButton viewSalary = new JButton("View Salary");
        viewSalary.setBounds(75,80,185,25);
        viewSalary.addActionListener(this);
        panel.add(viewSalary);
        
        salary = new JLabel("");
        salary.setBounds(75,160,185,50);
        panel.add(salary);
        
        JButton backToMenu = new JButton("Back To Menu");
        backToMenu.setBounds(75,120,185,25);
        backToMenu.addActionListener(this);
        panel.add(backToMenu);
        
        
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == "View Salary")
        {
            salary.setText("<html>" + user.getFullName() + 
            " your current salary is: $" + user.getSalary() + "<html>");
        }
        else if(e.getActionCommand() == "Back To Menu")
        {
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
}
