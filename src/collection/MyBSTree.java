package collection;

/**
 * Binary Search Tree BSTree starts with a root TreeNode. Stores int's
 * Each node contains a right and left link. Data less than the value
 * of the node gets stored to the left, greater than gets stored to the right.
 * Fields are root, left and right.
 * 
 * @author AndrewBerkow
 */
public class MyBSTree extends MySLList{

    // TreeNode that is the root
    TreeNode root;
    // TreeNode to the left of the root
    TreeNode left;
    // TreeNode to the right of the root
    TreeNode right;

    /**
     * Constructor, contains no parameters
     */
    public MyBSTree() {
    }

    /**
     * Add new node to tree. If tree is empty, root == data. 
     * Else call add(node, data)
     * 
     * @param data data contained by new node
     */
    public void insert(Object data) {
        System.out.println("bst add");
        if (root == null) {
            root = new TreeNode(data, null, null);
        } else {
            add(root, data);
        }
    }

    /**
     * Recursive function called by add(data)
     * 
     * @param node node that the new node will be the child of
     * @param data data contained in new node
     */
    private void add(TreeNode node, Object data) {
        if (node.getData().hashCode() > data.hashCode()) {
            if (node.left == null) {
                node.left = new TreeNode(data, null, null);
            } else {
                add(node.left, data);
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode(data, null, null);
            } else {
                add(node.right, data);
            }
        }
    }

    /**
     * Prints a string of the tree in pre order
     */
    public String preorder() {
        String s = "";
        if (root == null) {
            System.out.println("Tree is empty");
        } else {
            s += preorder(root);
        }
        
        return s;
    }

    /**
     * Recursive statement to generate pre order string
     * 
     * @param node node you are printing (if it has data) and the node that the
     * children of will be called recursed
     */
    private String preorder(TreeNode node) {
        String s = "";
        if (node == null) {
            return "";
        } else {
            s += node.data + ",";
            s += preorder(node.left);
            s += preorder(node.right);
            return s;
        }
    }

    /**
     * prints the tree in order traversal
     */
    public void inorder() {
        if (root == null) {
            System.out.println("Tree is empty");
        } else {
            inorder(root);
        }
    }

    /**
     * Recursive statement called by inorder()
     * 
     * @param node node you recursivly call the left child of, print than
     * recrusivly call the right child of
     */
    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        } else {
            inorder(node.left);
            System.out.print(node.data + ",");
            inorder(node.right);
        }
    }
    /**
     * Prints the tree postorder traversal
     */
    public void postorder() {
        if (root == null) {
            System.out.println("Tree is empty");
        } else {
            postorder(root);
        }
    }

    /**
     * Recursive statement called by postorder()
     * 
     * @param node node you recursivly call the left child of, 
     * recrusivly call the right child of than print the data
     */
    private void postorder(TreeNode node) {
        if (node == null) {
            return;
        } else {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.data + ",");
        }
    }
    
    /**
     * Checks if the tree contains the int
     * 
     * @param data the int you are searching the tree for
     * @return 
     */
    public boolean contains(int data) {
        return contains(root, data);
    }

    /**
     * Recursive statement used by contains(int data) 
     * 
     * @param node node you are checking the data of, and the data of its children
     * @param data what you are searching for
     * @return 
     */
    private boolean contains(TreeNode node, Object data) {
        if (node == null) {
            return false;
        } else if (node.getData().hashCode() == data.hashCode()){
            return true;
        } else if (data.hashCode() < node.getData().hashCode()) {
            return contains(node.left, data);
        } else {
            return contains(node.right, data);
        }

    }
    
    @Override
    public String toString(){
        return preorder();
    }
}

/**
 * TreeNode that MyBSTree uses to store data and link to other nodes.
 * Three fields: TreeNode right, TreeNOde left and int data.
 * 
 * @author AndrewBerkow
 */
class TreeNode {
    // link to right node
    TreeNode right;
    // link to left node
    TreeNode left;
    // intergar stored in node
    Object data;

    /**
     * Constructor for TreeNode
     * 
     * @param data int stored in node
     * @param left link to left node
     * @param right link to right node
     */
    TreeNode(Object data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
    
    public Object getData(){
        return data;
    }
}
