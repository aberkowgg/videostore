package collection;
import java.util.Objects;
import java.util.Scanner;

/**
 * MySLList stores objects in a node. Each node is linked to the next node.
 * Two fields: SLListNode head and SLListNode ref. 
 * 
 * @author AndrewBerkow
 */
public class MySLList {

    // First SLListNode in the linked list
    SLListNode head;
    // refrence node to store removed nodes 
    SLListNode ref;

    /**
     * Constructor for MySLList
     */
    public MySLList() {
        head = null;
    }

    /**
     * Insert object into front of list
     * 
     * @param data Object you wish to insert
     */
    public void insert(Object data) {
        if (head == null) {
            head = new SLListNode(data, null);
        } else {
            head = new SLListNode(data, head);
        }
    }

    /**
     * Clears the lined list
     */
    public void clear() {
        head = null;
    }

    /**
     * Checks if the list contains an Object. Starts at head and moves to next each call
     * 
     * @param n the SLListNode you start check
     * @param element the Object you wish to see if the linked list contains
     * @return 
     */
    public boolean contains(SLListNode n, Object element) {
        if (n.data == element) {
            return true;
        }
        if (n == null) {
            return false;
        } else {
            return contains(n.next, element);
        }
    }
    /**
     * Removes object from list
     * 
     * @param element Object you wish to remove from list
     * @return Object removed, returns null if does not exist.
     */
    public Object remove(String title){
        SLListNode ref = head;
        System.out.println(head.getElement().toString());
        if(Objects.equals(head.getElement().toString(), title)){
            Object temp = head.data;
            head = head.next;
            return temp;
        }
        while(ref.next != null && !Objects.equals(head.getElement().toString(), title)){
            ref = ref.next;
        }
        if(ref.next == null){
            return null;
        }
        Object rem = ref.next.data;
        ref.next = ref.next.next;
        return rem;
           //return ref;
    }
    
    /**
     * Removes object from list
     * 
     * @param element Object you wish to remove from list
     * @return Object removed, returns null if does not exist.
     */
    public Object remove(Object element){
        SLListNode ref = head;
        System.out.println(head.getElement().toString());
        System.out.println(element.toString());
        if(Objects.equals(head.getElement().toString(), element.toString())){
            Object temp = head.data;
            head = head.next;
            return temp;
        }
        while(ref.next != null && !Objects.equals(ref.getElement().toString(), element.toString())){
            System.out.println(ref.getElement().toString());
            ref = ref.next;
        }
        if(ref.next == null){
            return null;
        }
        Object rem = ref.next.data;
        ref.next = ref.next.next;
        return rem;
    }
    /**
     * Checks if the list is empty 
     * 
     * @return True is head is empty, returns false if head contains a node
     */
     public boolean isEmpty() {
      
        return head == null; 
    }
     /**
      * Generates a toString that contains all Objects in list, in order.
      * 
      * @return String containing all Objects in list, in order, seperated by commas. 
      */
    @Override
     public String toString(){
         String b = "";
         SLListNode ref = head;
         while(ref != null){
             b += (ref.data.toString() + ", ");
             ref = ref.next;
         }
         return b; 
     }
     
     
     public void printMaxVal(int[] array_, int current_index, int currentMax){
        int l = array_.length;
        if(current_index >= l){
            System.out.println(currentMax);
        }
    }
}

    
    
    
