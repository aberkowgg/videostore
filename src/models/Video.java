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
    private String id;
    
    public Video(String title, String id){
        this.title = title;
        this.id = id;
    }
    
    public String getTitle(){
        return title;
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
        return title;
    }
    
    public boolean isEqualTo(String title){
        return (Objects.equals(this.title, title));
        //&& title.equals(videoB.getTitle())
    }
}
