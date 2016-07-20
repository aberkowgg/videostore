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
    public int customer_counter = 0;
    
    public Store(){
        
    }
    
    protected MySLList getVideos(){
        return videoInStore;
    }
    
    protected MySLList getCustomer(){
        return customerList;
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
            Customer listCustomer =  (Customer) getCustomer().get(customer);
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
        
        Customer listCustomer =  (Customer) getCustomer().get(customer);
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
        
        Customer listCustomer =  (Customer) getCustomer().get(customer);
        System.out.println(listCustomer.getVideos().toString());
        
    }
    
    public void setVideoInStore(Video video){
        if(video.getId() == 0){
            id_counter++;
            video.setId(id_counter);
        }
        getVideos().insert(video);
    }
    
    
    
    public Video removeVideoInStore(Video video){
        return (Video) getVideos().remove(video);
    }
    
    public Object removeVideoInStore(String video_title){
        return getVideos().remove(video_title);
    }
    
    public void printInStoreVideos(){
        System.out.println(getVideos().toString());
    }
    
    public boolean contains(Video video){
        return getVideos().contains(video);
    }
    
    //customer stuff
    public void setCustomerInStore(Customer customer){
        if(customer.getId() == 0){
            customer_counter++;
            customer.setId(customer_counter);
        }
        getCustomer().insert(customer);
    }
    
    public void removeCustomerInStore(Customer customer){
        getCustomer().remove(customer);
    }
    
    public void printInStoreCustomers(){
        System.out.println(getCustomer().toString());
    }
    
    public boolean contains(Customer customer){
        return getCustomer().contains(customer);
    }
    
    public String getCheckedOutVideosString(){
        System.out.println("getting here");
        String video_string = "";
        SLListNode ref = getCustomer().getHead();
        Customer tmp;
        
        if (ref != null){
            tmp = (Customer) ref.data;
            video_string += tmp.getVideos().toString();
        }
        while(ref.getNext() != null){
            tmp = (Customer) ref.getNext().data;
            ref = ref.getNext();
            video_string += tmp.getVideos().toString();
        }
        
        return video_string;
    }
    
    public void printAllVideos(){
        System.out.println(getVideos().toString() + getCheckedOutVideosString());
    }
}
