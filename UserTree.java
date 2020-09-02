
/**
 * @author (Brian   Kieffer)
 * @version (8/28/2020)
 * 
 * This class is the creation of my binary search tree. It consists of methods to organize the information 
 * alphabetically based off first name. Has many other methods that consists of removing Users, finding a user,
 * and sending user information.
 * 
 */
public class UserTree
{
    private static class UserTreeNode
    {
        private User data;
        private UserTreeNode leftLink;
        private UserTreeNode rightLink;
        public UserTreeNode(User newData, UserTreeNode newLeftLink,
        UserTreeNode newRightLink)
        {
            data = newData;
            leftLink = newLeftLink;
            rightLink = newRightLink;
        }
    }
    private UserTreeNode root;
    int i = 0;
    User [] list;
    /**
     * A constuctor to iniate the binary tree.
     */
    public UserTree()
    {
        root = null;
    }
    public void add(User item)
    {
        root = insertInSubtree(item, root);
    }
    public boolean contains(String item)
    {
        return isInSubtree(item, root);
    }
    public void showElements()
    {
        showElementsInSubtree(root);
    }
    public int size()
    {
        return size(root);
    }
    public UserTreeNode delete(String target)
    {
        return deleteNode(target, root);
    }
    public int size(UserTreeNode node)
    {
        if(node == null)
        {
            return 0;
        }
        else
        {
            return(size(node.leftLink) + 1 + size(node.rightLink));
        }
    }
    public User [] createList()
    {
        list = new User[size()];
        return inOrder(root);
    }
    public User giveInfo(String name)
    {
        return findEmployee(name, root);
    }
    /**
     * This method will add a User object in the tree alphabetically.
     */
    private static UserTreeNode insertInSubtree(User item, UserTreeNode subTreeRoot)
    {
        if(subTreeRoot == null)
        {
            return new UserTreeNode(item, null, null);
        }
        else if(item.compareTo(subTreeRoot.data) < 0)
        {
            subTreeRoot.leftLink = insertInSubtree(item, subTreeRoot.leftLink);
            return subTreeRoot;
        }
        else
        {
            subTreeRoot.rightLink = insertInSubtree(item, subTreeRoot.rightLink);
            return subTreeRoot;
        }
    }
    /**
     * This method will return true if the target is found in the list.
     */
    private static boolean isInSubtree(String target, UserTreeNode subTreeRoot)
    {
        if(subTreeRoot == null)
        {
            return false;
        }
        else if(subTreeRoot.data.getFullName().equals(target))
        {
            return true;
        }
        else if(target.compareTo(subTreeRoot.data.getFullName()) < 0)
        {
            return isInSubtree(target, subTreeRoot.leftLink);
        }
        else if(target.compareTo(subTreeRoot.data.getFullName()) > 0)
        {
            return isInSubtree(target, subTreeRoot.rightLink);
        }
        else 
        {
            return false;
        }
    }
    /**
     * This method will search and return a User object.
     */
    private static User findEmployee(String target, UserTreeNode subTreeRoot)
    {
        if(subTreeRoot == null)
        {
            return null;
        }
        else if(subTreeRoot.data.getFullName().equals(target))
        {
            return subTreeRoot.data;
        }
        else if(target.compareTo(subTreeRoot.data.getFullName()) < 0)
        {
            return findEmployee(target, subTreeRoot.leftLink);
        }
        else if(target.compareTo(subTreeRoot.data.getFullName()) > 0)
        {
            return findEmployee(target, subTreeRoot.rightLink);
        }
        else 
        {
            return null;
        }
    }
    /**
     * This method will delete a specific node.
     */
    public UserTreeNode deleteNode(String target, UserTreeNode subTreeRoot)
    {
        if(subTreeRoot == null)
        {
            return root;
        }
        if(target.compareTo(subTreeRoot.data.getFullName()) < 0)
        {
            deleteNode(target, subTreeRoot.leftLink);
        }
        else if(target.compareTo(subTreeRoot.data.getFullName()) > 0)
        {
            deleteNode(target, subTreeRoot.rightLink);
        }
        else
        {
            if(subTreeRoot.leftLink == null && subTreeRoot.rightLink == null)
            {
                return null;
            }
            else if(subTreeRoot.leftLink == null)
            {
                return subTreeRoot.rightLink;
            }
            else if(subTreeRoot.rightLink == null)
            {
                return subTreeRoot.leftLink;
            }
            else
            {
                UserTreeNode temp1 = subTreeRoot.leftLink;
                UserTreeNode temp2 = subTreeRoot.rightLink;
                subTreeRoot = null;
                add(temp1.data);
                add(temp2.data);
            }
        }
        return subTreeRoot;
    }
    /**
     * Will print all tree elements.
     */
    private static void showElementsInSubtree(UserTreeNode subTreeRoot)
    {
        if(subTreeRoot != null)
        {
            showElementsInSubtree(subTreeRoot.leftLink);
            System.out.println(subTreeRoot.data + " " );
            showElementsInSubtree(subTreeRoot.rightLink);
        }
    }
    /**
     * Returns a User array consisting of users that sorted in alphabetical order.
     */
    public User [] inOrder(UserTreeNode subTreeRoot)
    {
        if(i >= size())
        {
            i = 0;
        }
        if(subTreeRoot == null)
        {
            return list;
        }
        inOrder(subTreeRoot.leftLink);
        list[i] = subTreeRoot.data;
        i++;
        inOrder(subTreeRoot.rightLink);
        return list;
    }
    /**
     * Checks to see if the tree is empty.
     */
    public boolean isEmpty()
    {
        if(root == null)
        {
            return true;
        }
        return false;
    }
}

