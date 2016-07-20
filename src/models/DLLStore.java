/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import collection.MyDLList;

/**
 *
 * @author andrewberkow
 */
public class DLLStore extends Store{
    
    MyDLList videoInStore = new MyDLList();
    MyDLList customerList = new MyDLList();
    
    @Override
    public void setVideoInStore(Video video){
        if(video.getId() == 0){
            id_counter++;
            video.setId(id_counter);
        }
        videoInStore.addFirst(video);
    }
    
    @Override
    protected MyDLList getVideos(){
        return videoInStore;
    }
    
    @Override
    protected MyDLList getCustomer(){
        return customerList;
    }

}
