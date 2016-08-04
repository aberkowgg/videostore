package collection;

/**
 * Binary Search Tree BSTree starts with a root TreeNode. Stores int's
 * Each node contains a right and left link. Data less than the value
 * of the node gets stored to the left, greater than gets stored to the right.
 * Fields are root, left and right.
 * 
 * @author AndrewBerkow
 */
public class MyBSTree implements MyStructure{

    public TreeNode root;// TreeNode that is the root
    private int length;

    //NTBD - CAHGNE GETDATA TO GET DLEMENT FOR NODE
    /**
     * Constructor, contains no parameters
     */
    public MyBSTree() {
    }
    
    
    /**
     * Encapsulate root
     * @return root
     */
    public TreeNode getRoot(){
        return root;
    }
    
    public void setRoot(TreeNode node){
        root = node;
    }
    
    /**
     * Encapsulate size
     * @return size
     */
    @Override
    public int getSize(){
        return length;
    }

    /**
     * Add new node to tree. If tree is empty, root == data. 
     * Else call add(node, data)
     *
     * @param element data contained by new node
     */
    @Override
    public void insert(Comparable element) {
        if (getRoot() == null) {
            setRoot(new TreeNode(element, null, null));
            incrementSize();
        } else {
            incrementSize();
            add(getRoot(), element);
        }
    }

    /**
     * Recursive function called by insert(element)
     * 
     * @param node node that the new node will be the child of
     * @param data data contained in new node
     */
    protected TreeNode add(TreeNode node, Comparable element) {
        //if comes before in alpha
        if (node.getData().compareTo(element) > 0) {
            if (node.getLeft() == null) {
                node.setLeft(new TreeNode(element, null, null));
                rebalanceInsert(node, node.getLeft());//hook for sublcasses that rebalance
                return node;//return the parent of inserted node
            } else {
                return add(node.getLeft(), element);
            }
        } else {
            if (node.getRight() == null) {
                node.setRight(new TreeNode(element, null, null));
                rebalanceInsert(node, node.getRight());//hook for sublcasses that rebalance
                return node;//return the parent of inserted node
            } else {
                return add(node.getRight(), element);
            }
        }
    }
    
    /**
     * Checks if the tree contains the int
     * 
     * @param element the int you are searching the tree for
     * @return boolean
     */
    @Override
    public boolean contains(Comparable element) {
        return contains(getRoot(), element);
    }

    /**
     * Recursive statement used by contains(int data) 
     * 
     * @param node node you are checking the data of, and the data of its children
     * @param element what you are searching for
     * @return boolean
     */
    private boolean contains(TreeNode node, Comparable element) {
        if (node == null) {
            return false;
        } else if (element.compareTo(node.getData()) == 0){
            return true;
        } else if (element.compareTo(node.getData()) < 0) {
            return contains(node.getLeft(), element);
        } else {
            return contains(node.getRight(), element);
        }
    }
    
    /**
     * Get the element from BST
     * @param element
     * @return Object
     */
    @Override
    public Object get(Comparable element) {
        TreeNode node = getNode(element);
        if (node == null) {
            return null;
        }
        return node.getData();
    }
    
    /**
     * Get node containing element from BST
     * @param element
     * @return TreeNode
     */
    private TreeNode getNode(Comparable element) {
        return get(getRoot(), element);
    }

    /**
     * Recursive statement to search for node that matches element
     * @param node
     * @param element
     * @return TreeNode
     */
    private TreeNode get(TreeNode node, Comparable element) {
        if (node == null) {
            return null;//node is empty
        } else if (element.compareTo(node.getData()) == 0){
            return node;//match
        } else if (element.compareTo(node.getData()) < 0) {
            return get(node.left, element);
        } else {
            return get(node.right, element);
        }
    }
    
    /**
     * remove element from BST
     * @param element
     * @return Comparable element that was removed
     */
    @Override
    public Comparable remove(Comparable element){
        decremenetSize();
        TreeNode delNode = removeNode(element);
        if(delNode == null){
            return null;
        }else{
            return delNode.getData();
        }
    }
    
    /**
     * Remove node from BST
     * @param element
     * @return TreeNode copy of node that was removed
     */
    private TreeNode removeNode(Comparable element) {
        //if the root node is the delete node
        if(element.compareTo(getRoot().getData()) == 0){
            TreeNode removedNode = new TreeNode(getRoot().getData(), null, null);
            //set root to the node that was shifted up after deletion
            setRoot(removeAndShift(getRoot()));
            //return the new route
            return removedNode;
        }
        return remove(getRoot(), element);
    }

    /**
     * Recursive statement that finds node to delete and returns copy of deleted node.
     * @param node
     * @param element
     * @return TreeNode node containing data of removed node
     */
    private TreeNode remove(TreeNode node, Comparable element) {
        if (node == null) {
            //node is leaf child of leaf, return nothing
            return null;
        } 
        
        //check if element lives on left or right of node
        if (element.compareTo(node.getData()) < 0) {//go left
            if(node.getLeft() != null){//failover to make sure there is something in left node
                if(element.compareTo(node.getLeft().getData()) == 0){
                    //left is the node we are looking for
                    //create a node node to hold the data of node we are about to delte
                    TreeNode removedNode = new TreeNode(node.getLeft().getData(), null, null);
                    //call remove and shift to remove the node and set the new node that took the deleted nodes place to left
                    node.setLeft(removeAndShift(node.getLeft()));
                    //rebalance hook to rebalance tree for subclass that balance tree
                    rebalanceRemove(node, node.getLeft());
                    //return the node we deleted
                    return removedNode;
                }
                return remove(node.getLeft(), element);//keep moving down tree
            }else{
                return null;//node does not exist
            }
        } else {//go right
            if(node.getRight() != null){//failover to make sure there is something in left node
                if(element.compareTo(node.getRight().getData()) == 0){
                    TreeNode removedNode = new TreeNode(node.getRight().getData(), null, null);//create a node to reutrn with data of removed node.
                    node.setRight(removeAndShift(node.getRight()));
                    rebalanceRemove(node, node.getRight());
                    return removedNode;
                }
                return remove(node.getRight(), element);
            }else{
                return null;
            }
        }
    }
    
    /**
     * Preform deletion of node and return the node that will takes its place
     * @param deleteNode
     * @return TreeNode the node that takes place of removed node
     */
    private TreeNode removeAndShift(TreeNode deleteNode){
            //if has two child notes
            TreeNode ref_right = deleteNode.getRight();
            TreeNode ref_left = deleteNode.getLeft();
            //check if node has two leaves, one leaf or is leaf 
            if(deleteNode.hasTwoLeaves()){
                //has two leafs
                if(ref_right.getLeft() == null){
                    //if right node has no left node. in this the right leaf will be the 
                    deleteNode = ref_right;
                    deleteNode.setLeft(ref_left);
                }else{
                    //node to the right of the deleted node has left children
                    TreeNode w = ref_right;
                    //set w to the leftmost child that also has a left node
                    while(w.getLeft().getLeft() != null){
                        w = w.getLeft();
                    }
                    
                    deleteNode.setParent(w);//set the parent of the delete node to the same parent that would replace it so we can refernce this in rebalance. This is not optimal but was required for fix bug in AVL
                    //get the data of the mode that will replace deleted node 
                    Comparable wData = w.getLeft().getData();
                    //check if the node that will be moved to deleted nodes spot has children
                    if(w.getLeft().isExteranlNode()){
                        //no children
                        w.setLeft(null);//set w's left to null
                    }else{
                        //has children (must be right since this is left most)
                        //set the left of w to the node that will replace the removed node's right child
                        w.setLeft(w.getLeft().getRight());
                    }
                    deleteNode.set(wData);//set data from removed node to root
                }
                
            }else if(deleteNode.hasLeft()){
                //if only left node just move left node one step up
                deleteNode = deleteNode.getLeft();
            }else if(deleteNode.hasRight()){
                //if only right node just move right node one step up
                deleteNode = deleteNode.getRight();
            }else{
                //has no nodes so do nothing
                return null;
            }
            return deleteNode;
    }
    
    protected void rebalanceInsert(TreeNode parent, TreeNode x){
        //D.p("BST relance.");
    }
    
    protected void rebalanceRemove(TreeNode parent, TreeNode x){
        
    }
    
    /**
     * Increments size of BST
     */
    public void incrementSize(){
        length++;
    }
    
    /**
     * Increments size of BST
     */
    public void decremenetSize(){
        length--;
    }

    /**
     * Returns tree as a pre ordered array
     * @return Comparable[] pre ordered array of comparable objects
     */
    public Comparable[] preorder() {
        if (getRoot() == null) {
            return null;
        } else {
            return preorder(getRoot());
        }
        
    }

    /**
     * Recursive statement to generate pre order array
     * @param node node you are printing (if it has data) and the node that the
     * children of will be called recursed
     * @return Comparable[]
     */
    private Comparable[] preorder(TreeNode node) {
        if (node == null) {
            return null;
        } else {
            Comparable[] bst_array = new Comparable[1];
            bst_array[0] = node.getData();
            
            //D.p(node.data.toString() + "( height : " + node.getHeight() + " )");
            //System.out.println(node.getData().toString() +",");
            //s += preorder(node.left);
            //s += preorder(node.right);
            
            Comparable[] bst_array_left = preorder(node.left);
            if(bst_array_left != null)
                bst_array =  D.concat(bst_array, bst_array_left);
            
            Comparable[] bst_array_right = preorder(node.right);
            if(bst_array_right != null)
                bst_array = D.concat(bst_array, bst_array_right);
            
            return bst_array;
        }
    }

    /**
     * Returns an in order array of BEST
     * @return Comparable[]
     */
    public Comparable[] inorder() {
        if (root == null) {
            return null;
        } else {
            return inorder(root);
        }
    }
    
    /**
     * Recursive statement called by inorder()
     * 
     * @param node node you recursivly call the left child of, print than recrusivly call the right child of
     * @return Comparable[]
     */
    private Comparable[] inorder(TreeNode node) {
        if (node == null) {
            return null;
        } else {
            Comparable[] bst_array = new Comparable[1];
            bst_array[0] = node.getData();
            
            Comparable[] bst_array_left = inorder(node.left);
            if(bst_array_left != null)
                bst_array =  D.concat(bst_array_left, bst_array);
           
            Comparable[] bst_array_right = inorder(node.right);
            if(bst_array_right != null)
                bst_array = D.concat(bst_array, bst_array_right);
           return bst_array;
        }
    }
      
    /**
     * Returns comma split list of tree
     * @return String
     */
    @Override
    public String toString(){
        String s = String.join(",", toStringArray());
        return s;
    }
    
    /**
     * Returns array of tree with each node as a string
     * @return String[]
     */
    public String[] toStringArray(){
        String[] bst_s_array = new String[getSize()];
        Comparable[] preorder_arr = preorder();
        if(getSize() > 0)
            for(int i = 0; i < preorder_arr.length; i++){
                bst_s_array[i] = preorder_arr[i].toString();
            }
        return bst_s_array;
    }
    
    /**
     * Returns tree as array of Comparable elements
     * @return Comparable[]
     */
    @Override
    public Comparable[] toArray() {
        Comparable[] bst_array = preorder();
        return bst_array;
    }

}

/**
 * TreeNode that MyBSTree uses to store data and link to other nodes.
 * Three fields: TreeNode right, TreeNOde left and comparable element as data.
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
    
    TreeNode parent;
    
    int height;

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
        parent = null;
    }
    
    public TreeNode getLeft(){
        return left;
    }
    
    public TreeNode getRight(){
        return right;
    }
    
    public TreeNode getParent(){
        return parent;
    }
    
    public Comparable getData(){
        return data;
    }
    
    public int getHeight(){
        return height;
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
    
    public void setParent(TreeNode node){
        parent = node;
    }
    
    public void setHeight(int h){
        height = h;
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
    
    public int incrementHeight(){
        height++;
        return height;
    }
    
    
}


