/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import collection.MySLList;
import java.util.Objects;

/**
 *
 * @author andrewberkow
 */
public class Customer {
    private String name;
    public int id;
    public MySLList rentVideos = new MySLList();
    
    public Customer(String name, int id){
        this.name = name;
        this.id = id;
    }
    
    public Customer(String name){
        this.name = name;
        this.id = 0;
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
        System.out.println("REnting : " + video.toString());
        rentVideos.insert(video);
    }
    
    public Video checkInVideo(Video video){
        return (Video) rentVideos.remove(video);
    }
    
    public MySLList getVideos(){
        return rentVideos;
    }
    
    @Override
    public String toString(){
        return id + ": " + name + " , Videos: " + rentVideos.toString();
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
}
