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
    
    public boolean checkout(Customer customer, Video video){
        //check if customer is not in list
        if(!contains(customer)){
            System.out.println("Customer Does not exist. Creating.");//NTDB make unqiue ID
            setCustomerInStore(customer);//add customer
        }
        //check if video is in store
        if(contains(video)){
            Video checkedOutVideo = removeVideoInStore(video);
            Customer listCustomer =  (Customer) customerList.get(customer);
            listCustomer.rentVideo(checkedOutVideo);
            return true;
        }else{
            System.out.println(video.getTitle() + " is not in stock.");
            return false;
        }
    }
    
    public boolean checkin(Customer customer, Video video){
        //check if customer is not in list
        if(!contains(customer)){
            System.out.println("Customer Does not exist. Creating.");//NTDB make unqiue ID
            setCustomerInStore(customer);//add customer
        }
        
        Customer listCustomer =  (Customer) customerList.get(customer);
        //check if customer has video
        if(listCustomer.getVideos().contains(video)){
            Video checkedInVideo = listCustomer.checkInVideo(video);
            setVideoInStore(checkedInVideo);
            return true;
        }else{
            System.out.println(listCustomer.getName() + "does not have " + video.getTitle() );
            return false;
        }
    }
    
    public void printCustomersVideos(Customer customer){
        //check if customer is not in list
        if(!contains(customer)){
            System.out.println("Customer Does not exist.");//NTDB make unqiue ID
            return;
        }
        
        Customer listCustomer =  (Customer) customerList.get(customer);
        System.out.println(listCustomer.getVideos().toString());
        
    }
    
    public void setVideoInStore(Video video){
        videoInStore.insert(video);
    }
    
    public Video removeVideoInStore(Video video){
        return (Video) videoInStore.remove(video);
    }
    
    public Object removeVideoInStore(String video_title){
        return videoInStore.remove(video_title);
    }
    
    public void printInStoreVideos(){
        System.out.println(videoInStore.toString());
    }
    
    public boolean contains(Video video){
        return videoInStore.contains(video);
    }
    
    //customer stuff
    public void setCustomerInStore(Customer customer){
        customerList.insert(customer);
    }
    
    public void removeCustomerInStore(Customer customer){
        customerList.remove(customer);
    }
    
    public void printInStoreCustomers(){
        System.out.println(customerList.toString());
    }
    
    public boolean contains(Customer customer){
        return customerList.contains(customer);
    }
    
    public void printAllVideos(){
        System.out.println("Needs to be done.");
    }
}
