/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import collection.MyBSTree;
import collection.MyStructure;

/**
 *
 * @author andrewberkow
 */
public class BSTStore extends Store{
    MyStructure videoInStore = new MyBSTree();
    MyStructure customerList = new MyBSTree();
    
    @Override
    protected MyStructure getVideos(){
        return videoInStore;
    }
    
    @Override
    protected MyStructure getCustomers(){
        return customerList;
    }
}
