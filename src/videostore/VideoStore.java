/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videostore;

import java.util.Scanner;
import models.*;

/**
 *
 * @author andrewberkow
 */
public class VideoStore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // create Scanner
        Scanner KBinput = new Scanner(System.in);
        // create char varialbes
        String choice;
        String again;
        Store videoStore = new Store();
        
        int id_counter = 0;
        
        int customer_counter = 0;//for cusotmer
        
        String[] video_titles = new String[5];
        video_titles[0] = "Fast & Furious";
        video_titles[1] = "Xmen";
        video_titles[2] = "Deadpool";
        video_titles[3] = "Starwars";
        video_titles[4] = "Harry Potter";
        
        String[] customer_names = new String[5];
        customer_names[0] = "Andrew";
        customer_names[1] = "Ben";
        customer_names[2] = "Perons";
        customer_names[3] = "Stella";
        customer_names[4] = "Neil";
        
        
        for(int i=0; i < 5; i++){
            String video_id = D.i2s(id_counter);id_counter++;
            Video v = new Video(video_titles[i], video_id);
            videoStore.setVideoInStore(v);
            
            String customer_id = D.i2s(customer_counter);customer_counter++;
            Customer c = new Customer(customer_names[i], customer_id);
            videoStore.setCustomerInStore(c);
        }
        
        Customer owen = new Customer("Owen", "9");
        videoStore.setCustomerInStore(owen);
            
        videoStore.printInStoreCustomers();
        
        
        do {
            //ask the user which operation they would like to perform
            //Input Choice
            
            System.out.println("===========================" + "\n"
                    + "Select one of the following:" + "\n"
                    + "1: To add a video " + "\n"
                    + "2: To delete a video" + "\n"
                    + "3: To add a customer" + "\n"
                    + "4: delete a customer" + "\n"
                    + "5: To check if a particular video is in store" + "\n"
                    + "6: To check out a video" + "\n"
                    + "7: To check in a video" + "\n"
                    + "8: To print all customers" + "\n"
                    + "9: To print all videos" + "\n"
                    + "10: To print in store videos" + "\n"
                    + "11: To print all rent videos " + "\n"
                    + "12: To print the videos rent by a customer" + "\n"
                    + "13: To exit"+ "\n"
                    + "==========================="
            );
            //choice = KBinput.next().charAt(0);
            
            choice= KBinput.nextLine();

            System.out.println(choice);

            switch (choice) {//Method Calls
                case "1":
                    System.out.println("Please enter the title and id of video");
                    System.out.println("Title: ");
                    String video_title = KBinput.nextLine();//System.out.println(video_title);
//                    System.out.println("Id: ");
//                    String video_id = KBinput.nextLine();//System.out.println(video_id);
                    String video_id = D.i2s(videoStore.id_counter);videoStore.id_counter = videoStore.id_counter + 1;
                    Video v = new Video(video_title, video_id);
                    videoStore.setVideoInStore(v);
                    videoStore.printInStoreVideos();

                    break;
                case "2":
                    System.out.println("Please enter the title and id of video you wish to delete.");
                    System.out.println("Title: ");
                    String video_title_d = KBinput.nextLine();//System.out.println(video_title);
//                    System.out.println("Id: ");
//                    String video_id_d = KBinput.nextLine();//System.out.println(video_id);
                    //Video vDelete = new Video(video_title_d, video_id_d);
                    D.p("before delete:");
                    videoStore.printInStoreVideos();
                    
                    videoStore.removeVideoInStore(video_title_d);
                    
                    D.p("after delete:");
                    videoStore.printInStoreVideos();
                    break;
                case "3":
                    System.out.println("Please enter customer name");
                    System.out.println("Name: ");
                    String customer_name = KBinput.nextLine();//System.out.println(video_title);
//                    System.out.println("Id: ");
//                    String video_id = KBinput.nextLine();//System.out.println(video_id);
                    String customer_id = D.i2s(customer_counter);customer_counter++;
                    Customer c = new Customer(customer_name, customer_id);
                    videoStore.setCustomerInStore(c);
                    videoStore.printInStoreCustomers();
                    break;
                case "4":
                    System.out.println("Please enter the name and id of the customer you wish to delete.");
                    System.out.println("Title: ");
                    String customer_name_d = KBinput.nextLine();//System.out.println(video_title);
                    System.out.println("Id: ");
                    String custome_id_d = KBinput.nextLine();//System.out.println(video_id);
                    Customer cDelete = new Customer(customer_name_d, custome_id_d);
                    D.p("before delete:");
                    videoStore.printInStoreCustomers();
                    
                    videoStore.removeCustomerInStore(cDelete);
                    
                    D.p("after delete:");
                    videoStore.printInStoreCustomers();
                    break;
                case "5":
                    Customer d = new Customer("Owen", "9");
                    
                    D.p("before delete:");
                    videoStore.printInStoreCustomers();
                    
                    videoStore.removeCustomerInStore(d);
                    
                    D.p("after delete:");
                    videoStore.printInStoreCustomers();
                    break;
                case "6":
                    
                    break;
                case "7":
                    
                    break;
                case "8":
                    
                    break;
                case "9":
                    
                    break;
                case "10":
                    
                    break;
                case "11":
                    
                    break;
                case "12":
                    
                    break;
                case "13":
                    
                    break;
                
                
                default:
                    System.out.println("That is not a choice!");
                    break;
            }
            System.out.println("Would you like to play again? X to exit any other key to continue");
            again = KBinput.nextLine();

        } while (again != "X" && again != "x");
    }
    
}

/**
 *
 * @author andrewberkow
 */
class D {
    /**
   * Prints String
   * @param String
   */
   public static void p(String string){
       System.out.println(string);
   }
   
   /**
     * Integer to String
     * @param int
     * @return String
     */
    public static String i2s(int i){
      String s  = Integer.toString(i);
      return s;
    }
}
