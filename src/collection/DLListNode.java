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
public class DLListNode{
    
     // Object stored in node
    public Object data;
    // Link to next node in linked list
    private DLListNode next;
    private DLListNode prev;
        
    DLListNode(Object data, DLListNode prev, DLListNode next){
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
    
    public Object getElement(){
        return data;
    }
        
    public DLListNode getNext(){
        return next;
    }
        
    public DLListNode getPrev(){
        return prev;
    }
    
    public void setNext(DLListNode nextNode){
        next = nextNode;
    }
        
    public void setPrev(DLListNode prevNode){
        prev = prevNode;
    }
    
   

       
}
