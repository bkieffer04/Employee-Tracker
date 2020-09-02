import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
/**
 * @author (Brian Kieffer)
 * @version (08/19/2020)
 * 
 * This class will display all employees in a ordered list organize by first name. If two employees have the same 
 * first name, then it will check last names and order in that way. 
 * 
 */
public class DirectoryList_GUI extends JFrame implements ActionListener
{
   private JList list;
   private JFrame frame;
    
   private String [] dirList;
   EmployeeList mainList = new EmployeeList();
   
   private User user;
   /**
    * The constructor creates the list window.
    */
   public DirectoryList_GUI(User currentUser)
   {
        user = currentUser;
        
        frame = new JFrame();
        JPanel panel = new JPanel();
        
        frame.setSize(350,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        
        panel.setLayout(null);
        
        createDirectory();
        
        setLayout(new FlowLayout());
        
        list = new JList(dirList);
        list.setSize(350,125);
        
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(6);
        add(new JScrollPane(list));
        panel.add(list);
        
        JButton backToMenu = new JButton("Back To Menu");
        backToMenu.setBounds(80,125,180,25);
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
    * This method extracts all accounts from the EmployeeList object and then puts it into a User array. The dirList
    * is short for directory list and is used to comply with the JList rules. So I had to extract each User element
    * from the User List and get each elements full name and place it into the dirList elements. 
    * 
    */
   public void createDirectory()
    {
        User [] list = mainList.sendEmployeeList();
        dirList = new String [list.length];
        int i = 0;
        while(i < dirList.length)
        {
            dirList[i] = list[i].getFullName();
            i++;
        }
    }
}
