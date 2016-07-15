/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Objects;

/**
 *
 * @author andrewberkow
 */
public class Customer {
    private String name;
    private String id;
    
    public Customer(String name, String id){
        this.name = name;
        this.id = id;
    }
    
    public String getName(){
        return name;
    }
    
    public String getId(){
        return id;
    }
    
//    @Override
//    public String toString(){
//        return "Id: " + id + " , Title: " + title;
//    }
    
    @Override
    public String toString(){
        return name + id;
    }
    
    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 +  Integer.parseInt(id);
        hash = hash * 31 + name.hashCode();
        
        return hash;
    }
    
    public boolean isEqualTo(String name){
        return (Objects.equals(this.name, name));
        //&& title.equals(videoB.getTitle())
    }
}
