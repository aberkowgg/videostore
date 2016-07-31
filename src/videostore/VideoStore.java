/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videostore;

import java.util.Scanner;
import models.*;
import java.util.UUID;

/**
 *
 * @author andrewberkow
 */
public class VideoStore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        D.pArgs(args);
        if(args.length > 0){
            if(args.length == 1 || args.length == 4 ){
                
                //switch for data structure
                Store videoStore;
                D.p(args[0]);
                String data_structure = args[0];
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
                    case "AVL":
                        videoStore = new BSTStore();
                        break; 
                    default:
                        videoStore = new Store();
                        break;
                }
                VideoController vsc = new VideoController(videoStore);
                if(args.length == 4){
                    
                    int number_of_videos = D.s2i(args[1]);
                    Video[] video_array = new Video[number_of_videos];
                    int number_of_customers = D.s2i(args[2]);
                    Customer[] customer_array = new Customer[number_of_customers];
                    int number_of_operations = D.s2i(args[1]);
                    
                    for(int i = 0; i < number_of_videos; i++){
                        String video_title = D.randomString();
                        Video v = vsc.addVideo(video_title);
                        //v = videoStore.setVideoInStore(v);
                        video_array[i] = v;
                    }
                    
                    for(int i = 0; i < number_of_customers; i++){
                        String customer_name = D.randomString();
                        Customer c = new Customer(customer_name);
                        c = videoStore.setCustomerInStore(c);
                        customer_array[i] = c;
                    }
                    
                }else{
                    
                    String[] video_titles = new String[8];
                    video_titles[0] = "Fast & Furious";
                    video_titles[1] = "Xmen";
                    video_titles[2] = "Deadpool";
                    video_titles[3] = "Starwars";
                    video_titles[4] = "Harry Potter";
                    video_titles[5] = "Zack";
                    video_titles[6] = "Eeee";
                    video_titles[7] = "Aaaa";

                    String[] customer_names = new String[8];
                    customer_names[0] = "Andrew";
                    customer_names[1] = "Ben";
                    customer_names[2] = "Perons";
                    customer_names[3] = "Stella";
                    customer_names[4] = "Neil";
                    customer_names[5] = "zack";
                    customer_names[6] = "eeee";
                    customer_names[7] = "aaaa";


                    for(int i=0; i < 8; i++){
//                        Video v = new Video(video_titles[i]);
//                        videoStore.setVideoInStore(v);
                        vsc.addVideo(video_titles[i]);

                        Customer c = new Customer(customer_names[i]);
                        videoStore.setCustomerInStore(c);
                    }

                    
                    vsc.checkOutVideo("Andrew", "1", "Aaaa");
                    vsc.checkOutVideo("Stella", "4", "Zack");

                    // create Scanner
                    Scanner KBinput = new Scanner(System.in);
                    // create char varialbes
                    String choice;
                    String again;

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

                        choice= KBinput.nextLine();

                        switch (choice) {//Method Calls
                            case "1":
                                System.out.println("Please enter the title and id of video");
                                System.out.println("Title: ");
                                String video_title = KBinput.nextLine();
                                vsc.addVideo(video_title);
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
                                D.p("Please enter the name and id of the customer who requests to checkout video.");
                                D.p("Name: ");
                                String customer_name_co = KBinput.nextLine();
                                D.p("Id: ");
                                String custome_id_co = KBinput.nextLine();
                                D.p("Please enter the title of the video you wish to checkout.");
                                D.p("Title: ");
                                String video_title_co = KBinput.nextLine();
                                boolean checked_out_successfully = vsc.checkOutVideo(customer_name_co, custome_id_co, video_title_co);
                                D.p(Boolean.toString(checked_out_successfully));

                                break;
                            case "7":
                                D.p("Please enter the name and id of the customer you wish checkin.");
                                D.p("Name: ");
                                String customer_name_ci = KBinput.nextLine();
                                D.p("Id: ");
                                String custome_id_ci = KBinput.nextLine();
                                D.p("Please enter the title of the video you wish to checkin.");
                                D.p("Title: ");
                                String video_title_ci = KBinput.nextLine();
                                boolean checked_in_successfully = vsc.checkInVideo(customer_name_ci, custome_id_ci, video_title_ci);  //videoStore.checkin(cCheckin, vCheckIn);
                                D.p(Boolean.toString(checked_in_successfully));
                                break;
                            case "8":
                                videoStore.printInStoreCustomers();
                                break;
                            case "9":
                                videoStore.printAllVideos();
                                break;
                            case "10":
                                videoStore.printInStoreVideos();
                                break;
                            case "11":
                                String checked_out_videos = videoStore.getCheckedOutVideosString();
                                D.p(checked_out_videos);
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
            }else{
                D.p("Arguments are not in correct format");
            }
        }
    }
    
    
}

class VideoController {
    
    public Store videoStore;
    
    public VideoController(Store store){
        this.videoStore = store;
    }
    
    public Video addVideo(String video_title ){
        Video v = new Video(video_title);
        videoStore.setVideoInStore(v);
        return v;
    }
    
    public boolean checkOutVideo(String customer_name, String custome_id, String video_title){
        Customer cCheckout = new Customer(customer_name, D.s2i(custome_id));
        Video vCheckOut = new Video(video_title);
        boolean checked_out_successfully = videoStore.checkout(cCheckout, vCheckOut);
        return checked_out_successfully;
    }
    
    public boolean checkInVideo(String customer_name, String custome_id, String video_title){
        Customer cCheckin = new Customer(customer_name, D.s2i(custome_id));
        Video vCheckIn = new Video(video_title);
        return videoStore.checkin(cCheckin, vCheckIn);
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
    
    /**
    * Prints String[]
    * @param String[]
    */
    public static void pArgs(String[] array_){
        int l = array_.length;
        String s = "";
        for(int i = 0; i < l; i++){
            s += ", " + array_[i];
        }
        D.p(s);
    }
    
    public static String randomString(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }
}
