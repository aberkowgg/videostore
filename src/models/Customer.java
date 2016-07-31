/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import collection.*;
import collection.MySLList;
import collection.MyStructure;
import java.util.Objects;

/**
 *
 * @author andrewberkow
 */
public class Customer implements Comparable {
    private String name;
    public int id;
    public MyStructure rentVideosSLL = new MySLList();
    public MyStructure rentVideosDLL = new MyDLList();
    public MyStructure rentVideosBST = new MyBSTree();
    public MyStructure rentVideosAVL = new MyAVLTree();
    public String structure = "SLL";
    
    public Customer(String name, int id, String structure){
        this.name = name;
        this.id = id;
        this.structure = structure;
    }
    
    public Customer(String name, String structure){
        this.name = name;
        this.id = 0;
        this.structure = structure;
    }
    
    public String getName(){
        return name;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void rentVideo(Video video){
        //System.out.println("REnting : " + video.toString());
        getVideos().insert(video);
    }
    
    public Video checkInVideo(Video video){
        return (Video) getVideos().remove(video);
    }
    
    public MyStructure getVideos(){
        MyStructure data_struct;
        switch(structure){
            case "SLL":
                data_struct =  rentVideosSLL;
                //System.out.println("SLL");
                break;
            case "DLL":
                data_struct = rentVideosDLL;
                //System.out.println("DLL");
                break;
            case "BST":
                data_struct = rentVideosBST;
                //System.out.println("BST");
                break;
            case "AVL":
                data_struct = rentVideosAVL;
                //System.out.println("AVL");
                break; 
            default:
                data_struct = rentVideosSLL;
                //System.out.println("default");
                break;
        }
        return data_struct;
    }
    
    @Override
    public String toString(){
        return id + ": " + name + " , Videos: " + getVideos().toString();
    }
    
    @Override
    public boolean equals(Object other){
        //check if same type of object
        if (!(other instanceof Customer)) {
            return false;
        }
        Customer that = (Customer) other;//create Video from Object
        return (name.equals(that.getName()) && id == that.getId());
    }

    @Override
    public int compareTo(Object obj) {
        if(this.getClass().equals(obj.getClass())){
            Customer cust2 = (Customer) obj;
            String customer_this = getName() + "_" + getId();
            String customer_that = cust2.getName() + "_" + cust2.getId();
            return customer_this.compareTo(customer_that);
        }else{
            throw new UnsupportedOperationException("Classes not of same type"); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
