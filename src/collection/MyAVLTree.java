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

    //NTBD - CAHGNE GETDATA TO GET DLEMENT FOR NODE
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
    
    @Override
    public void setRoot(TreeNode node){
        root = node;
        root.setParent(null);
    }
    
    @Override
    public int getSize(){
        return length;
    }
    
    @Override
    public void incrementSize(){
        length++;
    }
    
    @Override
    public void decremenetSize(){
        length--;
    }
    
    /**
     * Add new node to tree. If tree is empty, root == data. 
     * Else call add(node, data)
     * 
     * @param data data contained by new node
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
    
    
    @Override
    public void rebalanceInsert(TreeNode parent, TreeNode x){
        x.setParent(parent);
        x.setHeight(1);
        TreeNode ref = x;
        remeasureInsert(ref);
        //D.p("Before Balance : " + toString());
        while(ref.getParent() != null){
            int balance = getBalance(ref.getParent());
            //D.p("balance.... " + D.i2s(balance));
            if(balance > 1 || balance < -1){
                TreeNode z = ref.getParent();
                rebalance(z);
                break;
            }
            ref = ref.getParent();
        }
        remeasureInsert(x);
    }
    
    @Override
    public void rebalanceRemove(TreeNode parent, TreeNode newChild){
        TreeNode ref;
        //parent of deleted node
        if(newChild != null){
            newChild.setParent(parent);
            ref = newChild;
        }else{
            ref = parent;
        }
        
        remeasureInsert(ref);
        //D.p("Before Balance : " + toString());
        int b = getBalance(ref);
        if(b > 1 || b < -1){
            TreeNode z = ref.getParent();
            rebalance(z);
        }else{
           //NTBD t- this might not work since not check self
            while(ref.getParent() != null){
                int balance = getBalance(ref.getParent());
                //D.p("balance.... " + D.i2s(balance));
                if(balance > 1 || balance < -1){
                    TreeNode z = ref.getParent();
                    rebalance(z);
                    break;
                }

                ref = ref.getParent();
            } 
        }
        
        remeasureInsert(parent);
        //D.p("After Balance : " + toString());
        
    }
    
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
    
    public void rotateSingleRight(TreeNode z, TreeNode y, TreeNode x){
        //D.p("rotateSingleRight()**************");
        TreeNode a = z;
        TreeNode b = y;
        TreeNode c = x;
        TreeNode t0 = z.getLeft();
        TreeNode t1 = y.getLeft();
        TreeNode t2 = x.getLeft();
        TreeNode t3 = x.getRight();
        rotation( a,  b,  c,  t0,  t1,  t2,  t3,  z);
    }
    
    public void rotateSingleLeft(TreeNode z, TreeNode y, TreeNode x){
        //D.p("rotateSingleLeft()**************");
        TreeNode a = x;
        TreeNode b = y;
        TreeNode c = z;
        TreeNode t0 = x.getLeft();
        TreeNode t1 = x.getRight();
        TreeNode t2 = y.getRight();
        TreeNode t3 = z.getRight();
        rotation( a,  b,  c,  t0,  t1,  t2,  t3,  z);
    }
    
    public void rotateDoubleRight(TreeNode z, TreeNode y, TreeNode x){
        //D.p("rotateDoubleRight()**************");
        TreeNode a = z;
        TreeNode b = x;
        TreeNode c = y;
        TreeNode t0 = z.getLeft();
        TreeNode t1 = x.getLeft();
        TreeNode t2 = x.getRight();
        TreeNode t3 = y.getRight();
        rotation( a,  b,  c,  t0,  t1,  t2,  t3,  z);
    }
    
    
    
    public void rotateDoubleLeft(TreeNode z, TreeNode y, TreeNode x){
        //D.p("rotateDoubleLeft()**************");
        TreeNode a = y;
        TreeNode b = x;
        TreeNode c = z;
        TreeNode t0 = y.getLeft();
        TreeNode t1 = x.getLeft();
        TreeNode t2 = x.getRight();
        TreeNode t3 = z.getRight();
        rotation( a,  b,  c,  t0,  t1,  t2,  t3,  z);
    }
    
    
    
    
    public void rotation(TreeNode a, TreeNode b, TreeNode c, TreeNode t0, TreeNode t1, TreeNode t2, TreeNode t3, TreeNode z){
        //attatch z to rest of tree
        if(z.getParent() != null){
            //z.getParent().setRight(b);
            
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
        b.height = max(height(c.getLeft()), height(b.getRight())) + 1;
        remeasureInsert(b);
    }
    
    private void remeasureInsert(TreeNode x){
        TreeNode ref = x;
        //test to iterate through paraents
        while(ref.getParent() != null){
            ref.setHeight(max(height(ref.getLeft()), height(ref.getRight())) + 1);
            ref = ref.getParent();
        }
        ref.setHeight(max(height(ref.getLeft()), height(ref.getRight())) + 1);
    }
    
     // Get Balance factor of node N
    public int getBalance(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }
    
    // A utility function to get height of the tree
    int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }
    
    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }
    
}
