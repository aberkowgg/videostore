
package collection;

/**
 * Node for linked list
 * Two fields: Object data and SLListNode next
 * 
 * @author AndrewBerkow
 */
public class SLListNode {
    
        // Object stored in node
        public Comparable data;
        // Link to next node in linked list
        SLListNode next;

        /**
         * Constructor for SLListNode
         * 
         * @param data object you wish to store
         * @param next link to nexet node in list
         */
        SLListNode(Comparable data, SLListNode next) {
            this.data = data;
            this.next = next;
        }
        
       
        public Object getElement(){
            return data;
        }
        
        public SLListNode getNext(){
            return next;
        }
        
        
    
}
