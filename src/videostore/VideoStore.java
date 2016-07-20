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
        
        //switch for data structure
        Store videoStore;
        String data_structure = "BST";
        switch(data_structure){
            case "SSL":
                videoStore = new Store();
                break;
            case "DLL":
                videoStore = new DLLStore();
                break;
            case "BST":
                videoStore = new BSTStore();
                break;    
            default:
                videoStore = new Store();
                break;
        }
        
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
            Video v = new Video(video_titles[i]);
            videoStore.setVideoInStore(v);
    
            Customer c = new Customer(customer_names[i]);
            //videoStore.setCustomerInStore(c);
        }
        
        Customer owen = new Customer("Owen");
//        videoStore.setCustomerInStore(owen);
//            
//        videoStore.printInStoreCustomers();
        
        
        do {
            //videoStore.printInStoreVideos();
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
                    String video_title = KBinput.nextLine();
                    Video v = new Video(video_title);
                    videoStore.setVideoInStore(v);
                    break;
                case "2":
                    System.out.println("Please enter the title and id of video you wish to delete.");
                    System.out.println("Title: ");
                    String video_title_d = KBinput.nextLine();
                    Video vDelete = new Video(video_title_d);
                    videoStore.removeVideoInStore(vDelete);
                    break;
                case "3":
                    System.out.println("Please enter customer name");
                    System.out.println("Name: ");
                    String customer_name = KBinput.nextLine();
                    Customer c = new Customer(customer_name);
                    videoStore.setCustomerInStore(c);
                    break;
                case "4":
                    System.out.println("Please enter the name and id of the customer you wish to delete.");
                    System.out.println("Name: ");
                    String customer_name_d = KBinput.nextLine();
                    System.out.println("Id: ");
                    String custome_id_d = KBinput.nextLine();
                    Customer cDelete = new Customer(customer_name_d, D.s2i(custome_id_d));
                    videoStore.removeCustomerInStore(cDelete);
                    break;
                case "5":
                    videoStore.printInStoreVideos();
                    System.out.println("Please enter the title and id of video");
                    System.out.println("Title: ");
                    String video_search_title = KBinput.nextLine();
                    Video searchVideo = new Video(video_search_title);
                    boolean video_exists = videoStore.contains(searchVideo);
                    D.p(Boolean.toString(video_exists));//NTDB  - formet the print a little nicer
                    
                    break;
                case "6":
                    System.out.println("Please enter the name and id of the customer you wish to delete.");
                    System.out.println("Name: ");
                    String customer_name_co = KBinput.nextLine();//System.out.println(video_title);
                    System.out.println("Id: ");
                    String custome_id_co = KBinput.nextLine();//System.out.println(video_id);
                    Customer cCheckout = new Customer(customer_name_co, D.s2i(custome_id_co));
                    
                    System.out.println("Please enter the title of the video you wish to checkout.");
                    System.out.println("Title: ");
                    String video_title_co = KBinput.nextLine();
                    Video vCheckOut = new Video(video_title_co);
                    
                    boolean checked_out_successfully = videoStore.checkout(cCheckout, vCheckOut);
                    D.p(Boolean.toString(checked_out_successfully));
                    
                    break;
                case "7":
                    System.out.println("Please enter the name and id of the customer you wish checkin.");
                    System.out.println("Name: ");
                    String customer_name_ci = KBinput.nextLine();//System.out.println(video_title);
                    System.out.println("Id: ");
                    String custome_id_ci = KBinput.nextLine();//System.out.println(video_id);
                    Customer cCheckin = new Customer(customer_name_ci, D.s2i(custome_id_ci));
                    
                    System.out.println("Please enter the title of the video you wish to checkin.");
                    System.out.println("Title: ");
                    String video_title_ci = KBinput.nextLine();
                    Video vCheckIn = new Video(video_title_ci);
                    
                    D.p("before checkin: ");
                    videoStore.printInStoreCustomers();videoStore.printInStoreVideos();
                    
                    boolean checked_in_successfully = videoStore.checkin(cCheckin, vCheckIn);
                    D.p(Boolean.toString(checked_in_successfully));
                    
                    D.p("after checkin: ");
                    videoStore.printInStoreCustomers();videoStore.printInStoreVideos();
                    
                    break;
                case "8":
                    videoStore.printInStoreCustomers();
                    break;
                case "9":
                    //NTBD
                    D.p("NTDB");
                    //videoStore.printAllVideos();
                    break;
                case "10":
                    videoStore.printInStoreVideos();
                    break;
                case "11":
                    D.p("NTDB");
                    String checked_out_videos = videoStore.getCheckedOutVideosString();
                    //D.p(checked_out_videos);
                    break;
                case "12":
                    System.out.println("Please enter the name and id of the customer you wish to see current checkedout vidoes.");
                    System.out.println("Name: ");
                    String customer_name_vids = KBinput.nextLine();//System.out.println(video_title);
                    System.out.println("Id: ");
                    String custome_id_vids = KBinput.nextLine();//System.out.println(video_id);
                    Customer cVideosCheckedOut = new Customer(customer_name_vids, D.s2i(custome_id_vids));
                    
                    videoStore.printCustomersVideos(cVideosCheckedOut);
                    
                    break;
                case "13":
                    D.p("Goodbye.");
                    again = "X";
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
    
    public static int s2i(String num_string){
      int s  = Integer.parseInt(num_string);
      return s;
    }
}
