/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import collection.MyAVLTree;
import collection.MyStructure;

/**
 *
 * @author andrewberkow
 */
public class AVLStore extends Store{
    MyStructure videoInStore = new MyAVLTree();
    MyStructure customerList = new MyAVLTree();
    
    @Override
    protected MyStructure getVideos(){
        return videoInStore;
    }
    
    @Override
    protected MyStructure getCustomers(){
        return customerList;
    }
    
    
}
