import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.text.DecimalFormat;
import java.util.Locale;
/**
 * @author (Brian Kieffer)
 * @version (08/19/2020)
 * 
 * This class creates a simple window for executives to see the annual expenses of all their employee's salaries. 
 */
public class CalculateBudget_GUI implements ActionListener
{
    private JFrame frame;
    private JPanel panel;
    
    private EmployeeList addSalaries = new EmployeeList();
    
    private User user;
    /**
     * The constructor creates a window to see the total expenses of all the employee's salaries.
     */
    public  CalculateBudget_GUI(User currentUser)
    {
        user = currentUser;
        
        frame = new JFrame("Menu Directory");
        panel = new JPanel();
        
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        
        panel.setLayout(null);
        
        JLabel annualExpenses = new JLabel("Annual Expenses");
        annualExpenses.setBounds(85,100,180,15);
        panel.add(annualExpenses);
        
        JLabel value = new JLabel("");
        value.setBounds(195,100,180,15);
        panel.add(value);
        
        long conversion = calculation();
        DecimalFormat money = new DecimalFormat();
        String expenses = money.format(conversion);
        value.setText("$" + expenses);
        
        JButton backToMenu = new JButton("Back to Main Menu");
        backToMenu.setBounds(75,125,180,25);
        backToMenu.addActionListener(this);
        panel.add(backToMenu);
        
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        ExecutiveDirectory_GUI backToMenuOfOptions = new ExecutiveDirectory_GUI(user);
        frame.setVisible(false);
    }
    /**
     * This is a simple use of an array to add up all the employees salaries and return it to the constructor. 
     * I had create an User array called employees and extract each User's salary value and add them together. 
     */
    public long calculation()
    {
        long expenses = 0;
        User [] employees = addSalaries.sendEmployeeList();
        for(int i = 0; i < employees.length; i++)
        {
            expenses += employees[i].getSalary();
        }
        return expenses;
    }
}
