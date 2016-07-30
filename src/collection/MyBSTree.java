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
    @Override
    public void insert(Comparable data) {
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
    private void add(TreeNode node, Comparable data) {
        //if comes before in alpha
        if (node.getData().compareTo(data) > 0) {
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
    @Override
    public boolean contains(Comparable data) {
        return contains(root, data);
    }

    /**
     * Recursive statement used by contains(int data) 
     * 
     * @param node node you are checking the data of, and the data of its children
     * @param data what you are searching for
     * @return 
     */
    private boolean contains(TreeNode node, Comparable data) {
        if (node == null) {
            return false;
        } else if (data.compareTo(node.getData()) == 0){
            return true;
            //node.getData().compareTo(data) > 0
        } else if (data.compareTo(node.getData()) > 0) {
            return contains(node.left, data);
        } else {
            return contains(node.right, data);
        }

    }
    
    
    private TreeNode getNode(Comparable data) {
        return get(root, data);
    }

    private TreeNode get(TreeNode node, Comparable data) {
        if (node == null) {
            D.p("root empty");
            return null;
        } else if (data.compareTo(node.getData()) == 0){
            return node;
        } else if (data.compareTo(node.getData()) < 0) {
            return get(node.left, data);
        } else {
            return get(node.right, data);
        }

    }
    
    
    @Override
    public Comparable remove(Comparable data){
        TreeNode delNode = removeNode(data);
        if(delNode == null){
            D.p("doesnt exist");
            return null;
        }else{
            D.p("yay  " + delNode.getData().toString());
            return delNode.getData();
        }
    }
    
    private TreeNode removeAndShift(TreeNode deleteNode){
            D.p("removeAndShift");
            //if has two child notes
            TreeNode ref_right = deleteNode.getRight();
            TreeNode ref_left = deleteNode.getLeft();
            //if node has two levaes
            if(deleteNode.hasTwoLeaves()){
                D.p("deleteNode.hasTwoLeaves");
                if(ref_right.getLeft() == null){//if right node has no left node
                    D.p("qqqqq");
                    TreeNode dRoot = deleteNode;
                    deleteNode = ref_right;
                    deleteNode.setLeft(ref_left);
                }else{
                    D.p("wwwwww");
                    TreeNode w = ref_right;
                    while(w.getLeft().getLeft() != null){
                        w = w.getLeft();
                    }
                    Comparable wData = w.getLeft().getData();//data 
                    if(w.getLeft().isExteranlNode()){
                        w.setLeft(null);//set w's left to null
                    }else{
                        w.setLeft(w.getLeft().getRight());
                    }
                    deleteNode.set(wData);//set data from removed node to root
                }
                
            }else if(deleteNode.hasLeft()){
                D.p("has left");
                deleteNode = deleteNode.getLeft();
            }else if(deleteNode.hasRight()){
                D.p("has right");
                deleteNode = deleteNode.getRight();
            }else{
                D.p("has neither");
                deleteNode.set(null);
                return null;
            }
            return deleteNode;
    }
    
    private TreeNode removeNode(Comparable data) {
        //if the root node is the delete node
        if(data.compareTo(root.getData()) == 0){
              root = removeAndShift(root);
              return root;
        }
            
        return remove(root, data);
    }

    private TreeNode remove(TreeNode node, Comparable data) {
        if (node == null) {
            D.p("node doesn't exist");
            return null;
        } 
        
        if (data.compareTo(node.getData()) < 0) {
            D.p("111?");
            if(data.compareTo(node.getLeft().getData()) == 0){
                D.p("?");
                node.setLeft(removeAndShift(node.getLeft()));
                return node;
            }
            return remove(node.getLeft(), data);
        } else {
            D.p("2222?");
            if(data.compareTo(node.getRight().getData()) == 0){
                D.p("333?");
                node.setRight(removeAndShift(node.getRight()));
                return node;
            }
            return remove(node.getRight(), data);
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
    Comparable data;

    /**
     * Constructor for TreeNode
     * 
     * @param data int stored in node
     * @param left link to left node
     * @param right link to right node
     */
    TreeNode(Comparable data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
    
    public TreeNode getLeft(){
        return left;
    }
    
    public TreeNode getRight(){
        return right;
    }
    
    public Comparable getData(){
        return data;
    }
    
    public void set(Comparable data){
        this.data = data;
    }
    
    public void setLeft(TreeNode node){
        left = node;
    }
    
    public void setRight(TreeNode node){
        right = node;
    }
    
    public boolean hasTwoLeaves(){
        return (getLeft() != null && getRight() != null);
    }
    
    public boolean isExteranlNode(){
        return (getLeft() == null && getRight() == null);
    }
    
    public boolean hasLeft(){
        return left != null;
    }
    
    public boolean hasRight(){
        return right != null;
    }
    
    
}
