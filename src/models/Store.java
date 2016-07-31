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
    
    MyStructure videoInStore = new MySLList();
    MyStructure customerList = new MySLList();
    public int id_counter = 0;
    public int customer_counter = 0;
    
    /**
     * Constructor 
     */
    public Store(){
        
    }
    
    /**
     * Encapsulate videoInStore
     * @return videoInStore
     */
    protected MyStructure getVideos(){
        return videoInStore;
    }
    
    /**
     * Encapsulate customerList
     * @return customerList
     */
    protected MyStructure getCustomers(){
        return customerList;
    }
    
    /**
     * Check if video in store
     * @param video
     * @return Boolean
     */
    public boolean contains(Video video){
        return getVideos().contains(video);
    }
    
    /**
     * Check if customer in store
     * @param customer
     * @return Boolean
     */
    public boolean contains(Customer customer){
        return getCustomers().contains(customer);
    }
    /**
     * Check video out of store to customer
     * @param customer
     * @param video
     * @return true if successful
     */
    public boolean checkout(Customer customer, Video video){
        //check if customer is not in list
        if(!contains(customer)){
            System.out.println("Customer Does not exist. Creating.");//NTDB remove print for final project
            setCustomerInStore(customer);//add customer
        }
        //check if video is in store
        if(contains(video)){
            Video checkedOutVideo = removeVideoInStore(video);
            Customer listCustomer =  (Customer) getCustomers().get(customer);
            listCustomer.rentVideo(checkedOutVideo);
            return true;
        }else{
            //return false if not in stock
            System.out.println(video.getTitle() + " is not in stock.");//NTDB remove print for final project
            return false;
        }
    }
    
    /**
     * Check video into store
     * @param customer
     * @param video
     * @return true if successful
     */
    public boolean checkin(Customer customer, Video video){
        //check if customer is not in list
        if(!contains(customer)){
            System.out.println("Customer Does not exist. Creating.");//NTDB make unqiue ID
            setCustomerInStore(customer);//add customer
        }
        Customer listCustomer =  (Customer) getCustomers().get(customer);
        //check if customer has video
        if(listCustomer.getVideos().contains(video)){
            Video checkedInVideo = listCustomer.checkInVideo(video);
            setVideoInStore(checkedInVideo);
            return true;
        }else{
            //customer does not have video
            System.out.println(listCustomer.getName() + "does not have " + video.getTitle() );
            return false;
        }
    }
    
    /**
     * Set a video in the store
     * @param video 
     * @return Video
     */
    public Video setVideoInStore(Video video){
        if(video.getId() == 0){
            id_counter++;
            video.setId(id_counter);
        }
        getVideos().insert(video);
        return video;
    }
    
    /**
     * Remove video from store
     * @param video
     * @return 
     */
    public Video removeVideoInStore(Video video){
        return (Video) getVideos().remove(video);
    }
    
    /**
     * Set customer in list customer list
     * @param customer 
     * @return Customer
     */
    public Customer setCustomerInStore(Customer customer){
        if(customer.getId() == 0){
            customer_counter++;
            customer.setId(customer_counter);
        }
        getCustomers().insert(customer);
        return customer;
    }
    
    /**
     * Remove customer from store
     * @param customer 
     */
    public void removeCustomerInStore(Customer customer){
        getCustomers().remove(customer);
    }
    
    /**
     * Print videos in stock
     */
    public void printInStoreVideos(){
        System.out.println(getVideos().toString());
    }
    
    /**
     * Print list of customer
     */
    public void printInStoreCustomers(){
        System.out.println(getCustomers().toString());
    }
    
    /**
     * Print video from a customer
     * @param customer 
     */
    public void printCustomersVideos(Customer customer){
        //check if customer is not in list
        if(!contains(customer)){
            System.out.println("Customer Does not exist.");//NTDB make unqiue ID
            return;
        }
        
        Customer listCustomer =  (Customer) getCustomers().get(customer);
        System.out.println(listCustomer.getVideos().toString());
        
    }
    
    /**
     * Iterates through each customer and creates list of videos for each customer
     * @return String 
     */
    public String getCheckedOutVideosString(){
        
        System.out.println("getting here");
        String video_string = "";
        Comparable[] customer_array = getCustomers().toArray();
        int num_of_customers = customer_array.length;
        if(num_of_customers > 0){
            for(int i = 0; i < num_of_customers; i++){
                Customer cust = (Customer) customer_array[i];
                video_string += cust.getVideos().toString();
            }
        }
        return video_string;
    }
    
    
    public void printAllVideos(){
        System.out.println(getVideos().toString() + getCheckedOutVideosString());
    }
}
