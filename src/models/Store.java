/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import collection.*;
import models.*;

/**
 *
 * @author andrewberkow
 */
public class Store {
    
    MySLList videoInStore = new MySLList();
    MySLList customerList = new MySLList();
    public int id_counter = 0;
    
    public Store(){
        
    }
    
    public void setVideoInStore(Video video){
        videoInStore.insert(video);
    }
    
    public void removeVideoInStore(Video video){
        videoInStore.remove(video);
    }
    
    public void removeVideoInStore(String video_title){
        videoInStore.remove(video_title);
    }
    
    public void printInStoreVideos(){
        System.out.println(videoInStore.toString());
    }
    
    //customer stuff
    public void setCustomerInStore(Customer customer){
        customerList.insert(customer);
    }
    
    public void removeCustomerInStore(Customer customer){
        customerList.remove(customer);
    }
    
    public void removeCustomerInStore(String customer_name){
        customerList.remove(customer_name);
    }
    
    public void printInStoreCustomers(){
        System.out.println(customerList.toString());
    }
}
