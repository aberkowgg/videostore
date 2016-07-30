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
public interface MyStructure {
    
    void insert(Comparable element);
    Comparable remove(Comparable element);
    boolean contains(Comparable element);
    Comparable[] toArray();
    
}
