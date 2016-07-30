package collection;

/**
 * MySLList stores objects in a node. Each node is linked to the next node.
 * Two fields: SLListNode head and SLListNode ref. 
 * 
 * @author AndrewBerkow
 */
public class MySLList implements MyStructure{

    SLListNode head;// First SLListNode in the linked list
    SLListNode ref;// refrence node to store removed nodes 
    int length;

    /**
     * Constructor for MySLList
     */
    public MySLList() {
        head = null;
        length = 0;
    }
    
    /**
     * Return head node
     * @return SLListNode
     */
    protected SLListNode getHead(){
        return head;
    }
    
    /**
     * Set Node as head
     * @param node SLListNode you wish to set as head of list
     */
    public void setHead(SLListNode node){
        head = node;
    }
    
    /**
     * Return size
     * @return size
     */
    public int getSize(){
        return length;
    }
    
    /**
     * Insert object into front of list
     * @param element Object you wish to insert
     */
    @Override
    public void insert(Comparable element) {
        if (head == null) {
            head = new SLListNode(element, null);
        } else {
            head = new SLListNode(element, head);
        }
        length++;
    }

    /**
     * Clears the lined list
     */
    public void clear() {
        head = null;
        length = 0;
    }
    
    /**
     * Search list for element. Return true if exists. O(n)
     * NTDB - MAKE THIS CALL GET 
     * @param element
     * @return Boolean
     */
    @Override
    public boolean contains(Comparable element){
        ref = getHead();
        if(ref.getElement().equals(element)){
            return true;
        }
        while(ref.getNext() != null && !ref.getNext().getElement().equals(element)){
            ref = ref.getNext();
        }
        return ref.getNext() != null;
    }
    
    /**
     * Search for element and return if in SLL. 0(n)
     * @param element
     * @return Object
     */
    public Object get(Comparable element){
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
    }
    
    /**
     * Removes object from list
     * 
     * @param element Object you wish to remove from list
     * @return Object removed, returns null if does not exist.
     */
    @Override
    public Comparable remove(Comparable element){
        ref = getHead();
        if(ref.getElement().equals(element)){
            setHead(ref.getNext());
            return ref.getElement();
        }
        while(ref.getNext() != null && !ref.getNext().getElement().equals(element)){
            ref = ref.getNext();
        }
        if(null == ref.getNext()){
            return null;
        }
        Comparable rem = ref.getNext().getElement();
        ref.setNext(ref.getNext().getNext()); 
        length--;
        return rem;
    }
    
    /**
     * Checks if the list is empty 
     * 
     * @return True is head is empty, returns false if head contains a node
     */
     public boolean isEmpty() {
        return getHead() == null; 
    }
     
     /**
      * Generates a toString that contains all Objects in list, in order.
      * 
      * @return String containing all Objects in list, in order, seperated by commas. 
      */
    @Override
     public String toString(){
         String b = "";
         ref = getHead();
         while(ref != null){
             b += (ref.data.toString() + ", ");
             ref = ref.getNext();
         }
         return b; 
     }
        
    /**
     * Return SLL as an array
     * @return Comparable[]
     */ 
    @Override
    public Comparable[] toArray() {
        if(getSize() > 0){
            Comparable[] ssl_array = new Comparable[getSize()];
            ref = getHead();
            ssl_array[0] = ref.getElement();
            int i = 1;
            while(null != ref.getNext()){
                ref = ref.getNext();
                ssl_array[i] = ref.getElement();
                i++;
            }
            return ssl_array;
        }else{
            return null;
        }        
    }
    
}

    
    
    
