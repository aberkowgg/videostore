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
     * @param data data contained by new node
     */
    @Override
    public void insert(Comparable element) {
        System.out.println("bst add");
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
                //length++;
                rebalanceInsert(node, node.getLeft());//hook for sublcasses that rebalance
                return node;//return the parent of inserted node
            } else {
                return add(node.getLeft(), element);
            }
        } else {
            if (node.getRight() == null) {
                node.setRight(new TreeNode(element, null, null));
                //length++;
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
            //node.getData().compareTo(data) > 0
        } else if (element.compareTo(node.getData()) < 0) {
            return contains(node.getLeft(), element);
        } else {
            return contains(node.getRight(), element);
        }
    }
    
    @Override
    public Object get(Comparable element) {
        TreeNode node = getNode(element);
        if (node == null) {
            return null;
        }
        return node.getData();
    }
    
    private TreeNode getNode(Comparable element) {
        return get(getRoot(), element);
    }

    private TreeNode get(TreeNode node, Comparable element) {
        if (node == null) {
            D.p("root empty");
            return null;
        } else if (element.compareTo(node.getData()) == 0){
            return node;
        } else if (element.compareTo(node.getData()) < 0) {
            return get(node.left, element);
        } else {
            return get(node.right, element);
        }
    }
    
    @Override
    public Comparable remove(Comparable element){
        TreeNode delNode = removeNode(element);
        if(delNode == null){
            D.p("doesnt exist");
            return null;
        }else{
            D.p("yay  " + delNode.getData().toString());
            decremenetSize();
            return delNode.getData();
        }
    }
    
    public void incrementSize(){
        length++;
    }
    
    public void decremenetSize(){
        length--;
    }
    
    private TreeNode removeNode(Comparable element) {
        //if the root node is the delete node
        if(element.compareTo(getRoot().getData()) == 0){
              setRoot(removeAndShift(getRoot()));
              return getRoot();
        }
        return remove(getRoot(), element);
    }

    private TreeNode remove(TreeNode node, Comparable element) {
        if (node == null) {
            D.p("node doesn't exist");
            return null;
        } 
        
        if (element.compareTo(node.getData()) < 0) {
            D.p("111?");
            if(element.compareTo(node.getLeft().getData()) == 0){
                D.p("?");
                TreeNode removedNode = node.getLeft();
                node.setLeft(removeAndShift(node.getLeft()));
                return removedNode;
            }
            return remove(node.getLeft(), element);
        } else {
            D.p("2222?");
            if(element.compareTo(node.getRight().getData()) == 0){
                TreeNode removedNode = node.getRight();
                D.p("333?");
                node.setRight(removeAndShift(node.getRight()));
                return removedNode;
            }
            return remove(node.getRight(), element);
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
                return null;
            }
            return deleteNode;
    }
    
    protected void rebalanceInsert(TreeNode parent, TreeNode x){
        D.p("BST relance.");
    }
    
    

    /**
     * Prints a string of the tree in pre order
     */
    public Comparable[] preorder() {
        if (getRoot() == null) {
            System.out.println("Tree is empty");
            return null;
        } else {
            //System.out.println("Pre order");
            return preorder(getRoot());
        }
        
    }

    /**
     * Recursive statement to generate pre order string
     * 
     * @param node node you are printing (if it has data) and the node that the
     * children of will be called recursed
     */
    private Comparable[] preorder(TreeNode node) {
        if (node == null) {
            return null;
        } else {
            Comparable[] bst_array = new Comparable[1];
            bst_array[0] = node.getData();
            
            D.p(node.data.toString() + "( height : " + node.getHeight() + " )");
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
     * prints the tree in order traversal
     */
    public Comparable[] inorder() {
        if (root == null) {
            System.out.println("Tree is empty");
            return null;
        } else {
            //System.out.println("In order");
            return inorder(root);
        }
    }
    
    /**
     * Recursive statement called by inorder()
     * 
     * @param node node you recursivly call the left child of, print than
     * recrusivly call the right child of
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
            
            //System.out.println(node.getData().toString() +",");
            
            Comparable[] bst_array_right = inorder(node.right);
            if(bst_array_right != null)
                bst_array = D.concat(bst_array, bst_array_right);
           return bst_array;
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
      
    @Override
    public String toString(){
        //toArray();
        String s = String.join(",", toStringArray());
        return s;
    }
    
    public String[] toStringArray(){
        D.p("toStringArray() .... Size: " + D.i2s(getSize()));
        String[] bst_s_array = new String[getSize()];
        Comparable[] preorder_arr = preorder();
        for(int i = 0; i < preorder_arr.length; i++){
            bst_s_array[i] = preorder_arr[i].toString();
        }
        return bst_s_array;
    }

    @Override
    public Comparable[] toArray() {
        Comparable[] bst_array = inorder();
//        System.out.println("print in order array");
//        for(int i = 0; i < bst_array.length; i++){
//            System.out.println(bst_array[i].toString());
//        }
        return bst_array;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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


