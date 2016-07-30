/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import collection.MyBSTree;

/**
 *
 * @author andrewberkow
 */
public class BSTStore extends Store{
    MyBSTree videoInStore = new MyBSTree();
    MyBSTree customerList = new MyBSTree();
    
    @Override
    protected MyBSTree getVideos(){
        return videoInStore;
    }
    
    @Override
    protected MyBSTree getCustomers(){
        return customerList;
    }
}
