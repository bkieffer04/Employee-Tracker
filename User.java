
/**
 * @author (Brian Kieffer)
 * @version (8/28/2020)
 * 
 * This class creates a User object. It is a typical class that has getters and some setters for information. This class
 * is mainly used in the NewUser_GUI and has two different constructors, either will have to be complete to satisfy
 * the making of a new User object. 
 * 
 */
public class User
{
    private String firstName, lastName, userName, password, role;
    private int salary;
    /**
     * Typical constructor that sets all instance variables to null and zero.
     */
    public User()
    {
        firstName = null;
        lastName = null;
        userName = null;
        password = null;
        role = null;
        salary = 0;
    }
    /**
     * This constructor has parameters of firstName, lastName, userName, password, role, and salary to filled in
     * to create an User object.
     */
    public User(String newFirstName, String newLastName, String newUserName, 
    String newPassword, String newRole, int newSalary)
    {
        firstName = newFirstName;
        lastName = newLastName;
        userName = newUserName;
        password = newPassword;
        role = newRole;
        salary = newSalary;
    }
    /**
     * This constructor is different in that it takes a fullName instead of a firstName and lastName arguement.
     */
    public User(String fullName, String newUserName, String newPassword, String newRole,
    int newSalary)
    {
        String [] name = fullName.split(" ");
        firstName = name[0];
        lastName = name[1];
        userName = newUserName;
        password = newPassword;
        role = newRole;
        salary = newSalary;
    }
    public String getUserName()
    {
        return userName;
    }
    public void changeUserName(String newUserName)
    {
        userName = newUserName;
    }
    public String getPassword()
    {
        return password;
    }
    public void changePassword(String newPassword)
    {
        password = newPassword;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public String getFullName()
    {
        return (firstName + " " + lastName);
    }
    public String getRole()
    {
        return role;
    }
    public int getSalary()
    {
        return salary;
    }
    public String stringSalary()
    {
        String sal = Integer.toString(salary);
        return sal;
    }
    /**
     * This method organizes User objects by comparing first and last name to see what position they should be in
     * the UserTree
     */
    public int compareTo(User otherUser)
    {
        if(firstName.equals(otherUser.firstName) && lastName.equals(otherUser.lastName))
        {
            return 0;
        }
        else if(firstName.equals(otherUser.firstName))
        {
            int lengthOfLastName = lastName.length();
            int lengthOfOtherLastName = otherUser.lastName.length();
            int lengthOfLongestLastName;
            if(lengthOfLastName > lengthOfOtherLastName)
            {
                lengthOfLongestLastName = lengthOfLastName;
            }
            else
            {
                lengthOfLongestLastName = lengthOfOtherLastName;
            }
            for(int i = 0; i < lengthOfLongestLastName; i++)
            {
                if(lastName.charAt(i) == otherUser.lastName.charAt(i))
                {
                    continue;
                }
                else
                {
                    if(lastName.charAt(i) > otherUser.lastName.charAt(i))
                    {
                        return 1;
                    }
                    else
                    {
                        return -1;
                    }
                }
            }
        }
        else
        {
            int lengthOfFirstName = firstName.length();
            int lengthOfOtherFirstName = otherUser.firstName.length();
            int lengthOfLongestFirstName;
            if(lengthOfFirstName > lengthOfOtherFirstName)
            {
                lengthOfLongestFirstName = lengthOfFirstName;
            }
            else
            {
                lengthOfLongestFirstName = lengthOfOtherFirstName;
            }
            for(int i = 0; i < lengthOfLongestFirstName; i++)
            {
                if(firstName.charAt(i) == otherUser.firstName.charAt(i))
                {
                    continue;
                }
                else
                {
                    if(firstName.charAt(i) > otherUser.firstName.charAt(i))
                    {
                        return 1;
                    }
                    else
                    {
                        return -1;
                    }
                }
            }
        }
        return 0;
    }
    @Override
    public String toString()
    {
        return (role + "\n" + firstName + " " + lastName + "\n" + 
        userName + "\n" + password + "\n" + salary);
    }
}
