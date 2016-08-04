/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

/**
 *
 * @author andrewberkow
 */
public class MyAVLTree extends MyBSTree{
    
    public TreeNode root;// TreeNode that is the root
    private int length;

    /**
     * Constructor, contains no parameters
     */
    public MyAVLTree() {
    }
    
    /**
     * Encapsulate root
     * @return root
     */
    @Override
    public TreeNode getRoot(){
        return root;
    }
    
    /**
     * Change root to node
     * @param node node that will be new root
     */
    @Override
    public void setRoot(TreeNode node){
        root = node;
        if(root != null)//makes sure root is a node. 
            root.setParent(null);
    }
    
    /**
     * Return length
     * @return int
     */
    @Override
    public int getSize(){
        return length;
    }
    
    /**
     * Increments size of BST
     */
    @Override
    public void incrementSize(){
        length++;
    }
    
    /**
     * Decrements size of BST
     */
    @Override
    public void decremenetSize(){
        length--;
    }
    
    /**
     * Add new node to tree. If tree is empty, root == data. Else call add(node, data)
     * @param element element contained by new node
     */
    @Override
    public void insert(Comparable element) {
        if (getRoot() == null) {
            setRoot(new TreeNode(element, null, null));
            getRoot().setHeight(1);
            incrementSize();
        } else {
            incrementSize();
            super.add(getRoot(), element);
        }
    }
    
    /**
     * Called to rebalance tree after node inserted
     * @param parent
     * @param x 
     */
    @Override
    public void rebalanceInsert(TreeNode parent, TreeNode x){
        x.setParent(parent);
        x.setHeight(1);
        TreeNode ref = x;
        remeasureInsert(ref);
        while(ref.getParent() != null){
            int balance = getBalance(ref.getParent());
            if(balance > 1 || balance < -1){
                TreeNode z = ref.getParent();
                rebalance(z);
                break;
            }
            ref = ref.getParent();
        }
        remeasureInsert(x);
    }
    
    /**
     * Called to rebalance tree after node is removed
     * @param parent node above deleted node
     * @param newChild node in the place of the node that was deleted
     */
    @Override
    public void rebalanceRemove(TreeNode parent, TreeNode newChild){
        TreeNode ref;
        //parent of deleted node
        if(newChild != null){
            ref = newChild.getParent();//get the old parent of the new child, this is where will we start searching for rebalanced nodes from.
            newChild.setParent(parent);//set new parent
            //need to set the parent relationship of the new child to both of its children
            if(newChild.hasLeft())
                newChild.getLeft().setParent(newChild);
            if(newChild.hasRight())
                newChild.getRight().setParent(newChild);
        }else{
            ref = parent;
        }
        
        remeasureInsert(ref);//re set heights of nodes before rebalance
        
        int b = getBalance(ref);
        if(b > 1 || b < -1){
            TreeNode z = ref.getParent();
            rebalance(z);
        }else{
           //NTBD t- this might not work since not check self
            while(ref.getParent() != null){
                int balance = getBalance(ref.getParent());
                if(balance > 1 || balance < -1){
                    TreeNode z = ref.getParent();
                    rebalance(z);
                    break;
                }
                ref = ref.getParent();
            } 
        }
        remeasureInsert(parent);//re set heights of nodes before rebalance
    }
    
    /**
     * Accepts unbalanced node and rebalances that node
     * @param z 
     */
    public void rebalance(TreeNode z){
        int balance = getBalance(z);
        TreeNode y;
        TreeNode x;
        //left cases
        if(balance > 1){
            y = z.getLeft();
            if(height(y.getRight()) > height(y.getLeft())){
                //left right
                x = y.getRight();
                rotateDoubleLeft(z, y, x);
            }else{
                //left left
                x = y.getLeft();
                rotateSingleLeft(z, y, x);
            }
            
        }
        //right cases
        if(balance < -1){
            y = z.getRight();
            if(height(y.getLeft()) > height(y.getRight())){
                //right left
                x = y.getLeft();
                rotateDoubleRight(z, y, x);
            }else{
                //right right
                x = y.getRight();
                rotateSingleRight(z, y, x);
            }
        }
    }
    
    /**
     * Preform single right rotation 
     * @param z
     * @param y
     * @param x 
     */
    public void rotateSingleRight(TreeNode z, TreeNode y, TreeNode x){
        TreeNode a = z;
        TreeNode b = y;
        TreeNode c = x;
        TreeNode t0 = z.getLeft();
        TreeNode t1 = y.getLeft();
        TreeNode t2 = x.getLeft();
        TreeNode t3 = x.getRight();
        rotation( a,  b,  c,  t0,  t1,  t2,  t3,  z);
    }
    
    /**
     * Preform single left rotation 
     * @param z
     * @param y
     * @param x 
     */
    public void rotateSingleLeft(TreeNode z, TreeNode y, TreeNode x){
        TreeNode a = x;
        TreeNode b = y;
        TreeNode c = z;
        TreeNode t0 = x.getLeft();
        TreeNode t1 = x.getRight();
        TreeNode t2 = y.getRight();
        TreeNode t3 = z.getRight();
        rotation( a,  b,  c,  t0,  t1,  t2,  t3,  z);
    }
    
    /**
     * Preform double right rotation 
     * @param z
     * @param y
     * @param x 
     */
    public void rotateDoubleRight(TreeNode z, TreeNode y, TreeNode x){
        TreeNode a = z;
        TreeNode b = x;
        TreeNode c = y;
        TreeNode t0 = z.getLeft();
        TreeNode t1 = x.getLeft();
        TreeNode t2 = x.getRight();
        TreeNode t3 = y.getRight();
        rotation( a,  b,  c,  t0,  t1,  t2,  t3,  z);
    }
    
    /**
     * Preform double left rotation 
     * @param z
     * @param y
     * @param x 
     */
    public void rotateDoubleLeft(TreeNode z, TreeNode y, TreeNode x){
        TreeNode a = y;
        TreeNode b = x;
        TreeNode c = z;
        TreeNode t0 = y.getLeft();
        TreeNode t1 = x.getLeft();
        TreeNode t2 = x.getRight();
        TreeNode t3 = z.getRight();
        rotation( a,  b,  c,  t0,  t1,  t2,  t3,  z);
    }
    
    
    /**
     * Performs the rotation
     * @param a
     * @param b
     * @param c
     * @param t0
     * @param t1
     * @param t2
     * @param t3
     * @param z 
     */
    public void rotation(TreeNode a, TreeNode b, TreeNode c, TreeNode t0, TreeNode t1, TreeNode t2, TreeNode t3, TreeNode z){
        //attatch z to rest of tree
        if(z.getParent() != null){
            if(z.getParent().hasTwoLeaves()){
                if(z.getData().compareTo(z.getParent().getLeft().getData()) == 0){//ref node is the left of parent
                    z.getParent().setLeft(b);
                }else{
                    z.getParent().setRight(b);
                }
            }else if(z.getParent().hasLeft()){
                z.getParent().setLeft(b);
            }else{
                z.getParent().setRight(b);
            }
            
            b.setParent(z.getParent());
        }else{
            b.setParent(null);
            setRoot(b);
        }
        //set z to left of y
        a.setParent(b);
        b.setLeft(a);
        a.setLeft(t0);
        a.setRight(t1);
        if(t0 != null)
            t0.setParent(a);
        if(t1 != null)
            t1.setParent(a);
        //set x to right of y
        c.setParent(b);
        b.setRight(c);
        c.setLeft(t2);
        c.setRight(t3);
        if(t2 != null)
            t2.setParent(c);
        if(t3 != null)
            t3.setParent(c);
        //update heights
        a.height = max(height(a.getLeft()), height(a.getRight())) + 1;
        c.height = max(height(c.getLeft()), height(c.getRight())) + 1;
        //b.height = max(height(c.getLeft()), height(b.getRight())) + 1;
        remeasureInsert(b);
    }
    
    /**
     * Rebalances node and iterates up the parent nodes to balance each one
     * @param x 
     */
    private void remeasureInsert(TreeNode x){
        TreeNode ref = x;
        //test to iterate through paraents
        while(ref.getParent() != null){
            ref.setHeight(max(height(ref.getLeft()), height(ref.getRight())) + 1);
            ref = ref.getParent();
        }
        ref.setHeight(max(height(ref.getLeft()), height(ref.getRight())) + 1);
    }
    
     /**
      * Return an in for the different of left and right children
      * @param node
      * @return int
      */
    public int getBalance(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }
    
    /**
     * Return height of node, return 0 if node is empty
     * @param node
     * @return int
     */
    int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }
    
    /**
     * Accepts two ints and returns the max
     * @param a
     * @param b
     * @return 
     */
    int max(int a, int b) {
        return (a > b) ? a : b;
    }
    
}
