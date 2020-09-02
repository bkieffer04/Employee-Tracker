import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.io.File;
/**
 * @author (Brian   Kieffer)
 * @version (8/28/2020)
 * 
 * In this class it is the heart of every class in this program. Every class creates an EmployeeList object and 
 * acts upon the methods that are created in this class. The EmployeeList class is a binary tree based off UserTree.
 * I wanted to use this class as the main face of the program. I did not want to overwhelm the UserTree class with
 * multiple methods and also give the class the responsibility of importing and exporting the XML file. Many of the
 * methods in this class will provoke UserTree methods and UserTree will do many of the actions. 
 * 
 */
public class EmployeeList
{
    UserTree employeeList = new UserTree();
    /**
     * The constructor will import all users from the employee xml file and then add them to the UserTree object 
     * 'employeeList'.
     */
    public EmployeeList()
    {
         try
        {
            File inputFile = new File("employee.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("employees");
            
            for(int i = 0; i < nList.getLength(); i++)
            {
                Node nNode = nList.item(i);
                if(nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) nNode;
                    
                    NodeList employeeList = eElement.getElementsByTagName("EmployeesInfo");
                    
                    for(int count = 0; count < employeeList.getLength(); count++)
                    {
                        Node node1 = employeeList.item(count);
                        if(node1.getNodeType() == node1.ELEMENT_NODE)
                        {
                           Element person = (Element) node1;
                           User employee = new User(person.getAttribute("fullname"),
                           person.getAttribute("Username"),
                           person.getAttribute("Password"),
                           person.getAttribute("role"),
                           Integer.parseInt(person.getAttribute("salary")));
                           addEmployee(employee);
                        }
                    }
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void addEmployee(User newEmployee)
    {
        employeeList.add(newEmployee);
    }
    /**
     * This method will export all User objects in the employeeList into an XML file.
     */
    public void exportEmployeeList()
    {
        User [] exportList = employeeList.createList();
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            
            Element rootElement = doc.createElement("Business");
            doc.appendChild(rootElement);//This creates the main subject of the file
            
            Element employees = doc.createElement("employees");
            rootElement.appendChild(employees);
            
           for(int i = 0; i < exportList.length;i++)
            {
                Element personBio = doc.createElement("EmployeesInfo");
                
                Attr attrRole = doc.createAttribute("role");
                attrRole.setValue(exportList[i].getRole());
                personBio.setAttributeNode(attrRole);
                
                Attr attrSalary = doc.createAttribute("salary");
                attrSalary.setValue(exportList[i].stringSalary());
                personBio.setAttributeNode(attrSalary);
                
                Attr attrFullName = doc.createAttribute("fullname");
                attrFullName.setValue(exportList[i].getFullName());
                personBio.setAttributeNode(attrFullName);
                
                Attr attrUserName = doc.createAttribute("Username");
                attrUserName.setValue(exportList[i].getUserName());
                personBio.setAttributeNode(attrUserName);
                
                Attr attrPassword = doc.createAttribute("Password");
                attrPassword.setValue(exportList[i].getPassword());
                personBio.setAttributeNode(attrPassword);
                
                employees.appendChild(personBio);
           }
            
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("employee.xml"));
            transformer.transform(source, result);
            
            
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * The purpose of this method is to send an array of User objects. 
     */
    public User [] sendEmployeeList()
    {
        return employeeList.createList();
    }
    /**
     * This method provokes a method in the UserTree class and will return true if an employee is in the list.
     */
    public boolean searchForAnEmployee(String name)
    {
        return employeeList.contains(name);
    }
    /**
     * This method provokes a method in the UserTree class and will return all of the User object information based
     * off their full name.
     */
    public User giveEmployeeInfo(String name)
    {
        return employeeList.giveInfo(name);
    }
    /**
     * This method provokes a method in the UserTree class and will return User information based off the provided
     * username.
     */
    public User giveEmployeeInfoByUserName(String userName)
    {
        User [] userNameList = employeeList.createList();
        for(int i = 0; i < userNameList.length; i++)
        {
            if(userNameList[i].getUserName().equals(userName))
            {
                return userNameList[i];
            }
        }
        return null;
    }
    public int getSize()
    {
        return employeeList.size();
    }
    /**
     * This method will export a xml file, but if a User equals the target name, then the xml file will ignore
     * that array element and then continue exporting the rest of the users.
     */
    public void delete(String target)
    {
        User [] exportList = employeeList.createList();
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            
            Element rootElement = doc.createElement("Business");
            doc.appendChild(rootElement);//This creates the main subject of the file
            
            Element employees = doc.createElement("employees");
            rootElement.appendChild(employees);
            
           for(int i = 0; i < exportList.length;i++)
            {
                if(target.equals(exportList[i].getFullName()))
                {
                    continue;
                }
                Element personBio = doc.createElement("EmployeesInfo");
                
                Attr attrRole = doc.createAttribute("role");
                attrRole.setValue(exportList[i].getRole());
                personBio.setAttributeNode(attrRole);
                
                Attr attrSalary = doc.createAttribute("salary");
                attrSalary.setValue(exportList[i].stringSalary());
                personBio.setAttributeNode(attrSalary);
                
                Attr attrFullName = doc.createAttribute("fullname");
                attrFullName.setValue(exportList[i].getFullName());
                personBio.setAttributeNode(attrFullName);
                
                Attr attrUserName = doc.createAttribute("Username");
                attrUserName.setValue(exportList[i].getUserName());
                personBio.setAttributeNode(attrUserName);
                
                Attr attrPassword = doc.createAttribute("Password");
                attrPassword.setValue(exportList[i].getPassword());
                personBio.setAttributeNode(attrPassword);
                
                employees.appendChild(personBio);
           }
            
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("employee.xml"));
            transformer.transform(source, result);
            
            
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
