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
public class Video {
    
    private String title;
    private int id;
    
    public Video(String title, int id){
        this.title = title;
        this.id = id;
    }
    
    public Video(String title){
        this.title = title;
        this.id = 0;
    }
    
    public String getTitle(){
        return title;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
//    @Override
//    public String toString(){
//        return "Id: " + id + " , Title: " + title;
//    }
    
    @Override
    public String toString(){
        return title;
    }
    
    @Override
    public boolean equals(Object other){
        //check if same type of object
        if (!(other instanceof Video)) {
            return false;
        }
        Video that = (Video) other;//create Video from Object
        return title.equals(that.getTitle());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.title);
        return hash;
    }
}
