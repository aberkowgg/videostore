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
public class MyDLList extends MySLList{
    
    private DLListNode head;
    private DLListNode trail;
    private int size;
    // refrence node to store removed nodes 
    
    public MyDLList(){
        head = new DLListNode(null, null, null);
        trail = new DLListNode(null, head, null);
        head.setNext(trail);
        size = 0;
    }
    
    /* Returns the number of elements in the linked list. */ 
    public int size() { return size; }
    
    /* Tests whether the linked list is empty. */
    public boolean isEmpty() { return size == 0; }
    
    /* Returns (but does not remove) the first element of the list. */ 
    public DLListNode first() {
        if (isEmpty()) return null;
        return head.getNext(); // first element is beyond header 
    }
    
    /* Returns (but does not remove) the last element of the list. */ 
    public DLListNode last() {
        if (isEmpty()) return null;
        return trail.getPrev(); // last element is before trailer 
    }
    
    private DLListNode getNode(Object element){
        DLListNode ref = head.getNext();
        while(ref.getElement() != null && !ref.getElement().equals(element)){
            ref = ref.getNext();
        }
        if(ref == null){
            return null;
        }
        return ref;
    }
    
    public Object get(Object element){
        D.p("DDL get()");
        DLListNode needle = getNode(element);
        if(needle == null){
            return null;
        }else{
            return needle.getElement();
        }
    }
    
    public boolean contains(Object element){
        D.p("DDL containts()");
        if(get(element) == null){
            return false;
        }
        return true;
    }
    
    /* Adds element e to the front of the list. */
    public void addFirst(Object obj) {
        addBetween(obj, head, head.getNext());
    }
    
    @Override
    public void insert(Object obj){
        D.p("DDL Insert()");
        addFirst(obj);
    }
    /* Adds element e to the end of the list. */
    public void addLast(Object obj) {
        addBetween(obj, trail.getPrev(), trail);
    }
    
    /* Adds element e to the linked list in between the given nodes. */
    private void addBetween(Object obj, DLListNode predecessor, DLListNode successor) {
        
        if(head.getNext().getElement() != null){
            System.out.println(head.getNext().getElement().toString());
        }
        // create and link a new node
        DLListNode newest = new DLListNode(obj, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
        System.out.println("size" +  D.i2s(size));
    }
    
    @Override
    public Object remove(Object obj){
         D.p("DDL remove()");
        D.p("removeing");
        DLListNode delNode = getNode(obj);
        if(delNode.getElement() == null){
            return null;
        }
        DLListNode predecessor = delNode.getPrev();
        DLListNode successor = delNode.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        return delNode.getElement();
    }
    
    
    
    @Override
     public String toString(){
         D.p("getting here");
         String b = "";
         DLListNode ref_ = head.getNext();
         while(ref_.getElement() != null){
             if(ref_.getNext().getElement() != null){
                b += (ref_.getElement().toString() + ", ");
             }else{
                b += (ref_.getElement().toString());
             }
             ref_ = ref_.getNext();
         }
         return b; 
     }

     
     
   
}

class D {
    /**
   * Prints String
   * @param String
   */
   public static void p(String string){
       System.out.println(string);
   }
   
   /**
     * Integer to String
     * @param int
     * @return String
     */
    public static String i2s(int i){
      String s  = Integer.toString(i);
      return s;
    }
    
    public static int s2i(String num_string){
      int s  = Integer.parseInt(num_string);
      return s;
    }
}