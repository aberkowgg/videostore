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
    private DLListNode ref;
    // refrence node to store removed nodes 
    
    public MyDLList(){
        head = new DLListNode(null, null, null);
        trail = new DLListNode(null, head, null);
        head.setNext(trail);
        length = 0;
    }
    
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
    
    /**
     * Tells is DLL is empty
     * @return 
     */
    @Override
    public boolean isEmpty() { return getSize() == 0; }
    
    /**
     * Inserts after head 
     * @param element 
     */
    @Override
    public void insert(Comparable element){
        addFirst(element);
    }
    
    /**
     * Adds element e to the front of the list.
     * @param element 
     */
    public void addFirst(Comparable element) {
        addBetween(element, head, head.getNext());
    }
    
    /**
     * Adds element e to the end of the list.
     * @param element
     */
    public void addLast(Comparable element) {
        addBetween(element, trail.getPrev(), trail);
    }
    
    /**
     * Adds element e to the linked list in between the given nodes.
     * @param element
     * @param predecessor
     * @param successor 
     */
    private void addBetween(Comparable element, DLListNode predecessor, DLListNode successor) {
        //NTDB REMOVE THIS BLOCK IS DON'T FIGURE OUT WHAT ITS FOR
//        if(head.getNext().getElement() != null){
//            System.out.println(head.getNext().getElement().toString());
//        }
        
        DLListNode newest = new DLListNode(element, predecessor, successor);// create and link a new node
        predecessor.setNext(newest);
        successor.setPrev(newest);
        length++;
    }
    
    /**
     * Clear DLL
     */
    @Override
    public void clear() {
        head.setNext(trail);
        trail.setPrev(head);
        length = 0;
    }
    
    /**
     * Check is DLL contains element
     * @param element
     * @return boolean
     */
    @Override
    public boolean contains(Comparable element){
        //D.p("DDL containts()");
        return get(element) != null;
    }
    
    /**
     * Gets element if in DLL
     * @param element
     * @return Comparable
     */
    @Override
    public Comparable get(Comparable element){
        //D.p("DDL get()");
        DLListNode needle = getNode(element);
        if(needle == null){
            return null;
        }else{
            return needle.getElement();
        }
    }
    
    /**
     * Search DLL for node that contains element and return node. If not found return null
     * @param element
     * @return DLListNode
     */
    private DLListNode getNode(Comparable element){
        /*
        ref = getHead();
        if(ref.getElement().equals(element)){
            return ref.getElement();
        }
        while(ref.getNext() != null && !ref.getNext().getElement().equals(element)){
            ref = ref.getNext();
        }
        if(ref.getNext() == null){
            return null;
        }
        return ref.getNext().getElement();
        return ref.getNext() != null;
        */
        
        /*
        ref = first();
        while(ref.getNext() != null && !ref.getElement().equals(element)){
            D.p(ref.getElement().toString());
            ref = ref.getNext();
        }
        if(ref.getElement() == null){
            return null;
        }
        return ref;
        */
        
        DLListNode ref = head.getNext();
        while(ref.getElement() != null && !ref.getElement().equals(element)){
            ref = ref.getNext();
        }
        if(ref == null){
            return null;
        }
        return ref;
    }
    
    /**
     * Removes object from list and return it
     * @param element
     * @return 
     */
    @Override
    public Comparable remove(Comparable element){
//         D.p("DDL remove()");
//        D.p("removeing");
        DLListNode delNode = getNode(element);
        if(delNode.getElement() == null){
            return null;
        }
        DLListNode predecessor = delNode.getPrev();
        DLListNode successor = delNode.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        length --;
        return delNode.getElement();
    }
    
    /**
     * Return DLL as comma split string
     * @return String
     */
    @Override
     public String toString(){
         //D.p("getting here");
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

    /**
     * Return DLL as an array
     * @return Comparable[]
     */ 
    @Override
    public Comparable[] toArray() {
        Comparable[] dll_array = new Comparable[getSize()];
        if(getSize() > 0){
            //D.p("Size: " + D.i2s(getSize()));
            ref = head.getNext();
            dll_array[0] = ref.getElement();
            int i = 1;
            while(null != ref.getNext().getElement()){
                ref = ref.getNext();
                dll_array[i] = ref.getElement();
                i++;
            }
            return dll_array;
        }else{
            return dll_array;
        }        
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
    
    public static Comparable[] concat(Comparable[] a, Comparable[] b) {
        int aLen = a.length;
        int bLen = b.length;
        Comparable[] c= new Comparable[aLen+bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
     }
}