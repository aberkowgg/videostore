/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videostore;

import java.util.Scanner;
import models.*;
import java.util.UUID;
import java.util.Random;

/**
 *
 * @author andrewberkow
 */
public class VideoStore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        D.p(args[0]);
        double start = (System.currentTimeMillis());
        if(args.length > 0){
            if(args.length == 1 || args.length == 4 ){
                //switch for data structure
                Store videoStore;
                
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
                        videoStore = new AVLStore();
                        break; 
                    default:
                        videoStore = new Store();
                        break;
                }
                
                //create controller to handle videoStore methods called frequently. 
                VideoController vsc = new VideoController(videoStore, data_structure);
                
                //if 4 paramters, execute autmotion testing to compare run times.
                if(args.length == 4 ){
                    //D.isInteger(args[1]) && D.isInteger(args[2]) && D.isInteger(args[3])
                    //initilize videos
                    int number_of_videos = D.s2i(args[1]);
                    Video[] video_array = new Video[number_of_videos];
                    
                    for(int i = 0; i < number_of_videos; i++){
                        String video_title = D.randomString();
                        Video v = vsc.addVideo(video_title);
                        video_array[i] = v;
                    }
                    
                    //intialize customers
                    int number_of_customers = D.s2i(args[2]);
                    Customer[] customer_array = new Customer[number_of_customers];
                    
                    for(int i = 0; i < number_of_customers; i++){
                        String customer_name = D.randomString();
                        customer_array[i] = vsc.addCustomer(customer_name);
                    }
                    
                    
                    //initialize operations
                    int number_of_operations = D.s2i(args[1]);
                    String[] operations = new String[number_of_operations];
                    String[] possible_operations = {"videoInStore", "checkOutVideo", "checkInVideo"};
                    Random randomGenerator = new Random();
                    for(int i = 0; i < number_of_operations; i++){
                        int rand = randomGenerator.nextInt(100);
                        operations[i] = possible_operations[rand % 3];
                    }
                    
                    //execture operations NTBD - change the array to a queue.
                    for(int i = 0; i < number_of_operations; i++){
                        int rand_vid = randomGenerator.nextInt(number_of_videos);
                        String title = video_array[rand_vid].getTitle();
                        int rand_cust;
                        Customer customer;
                            
                            
                        switch (operations[i]){
                            case "videoInStore":
                                vsc.videoInStore(title);
                                break;
                            case "checkOutVideo":
                                rand_cust = randomGenerator.nextInt(number_of_customers);
                                customer = customer_array[rand_cust];
                                vsc.checkOutVideo(customer.getName(), D.i2s(customer.getId()), title);
                                break;
                            case "checkInVideo":
                                rand_cust = randomGenerator.nextInt(number_of_customers);
                                customer = customer_array[rand_cust];
                                vsc.checkInVideo(customer.getName(), D.i2s(customer.getId()), title);
                                break;
                            default:
                                D.p("error, " + operations[i] + " is not a possibile command for automated operations.");
                        }
                                
                    }
                    
                    double stop = (System.currentTimeMillis());
                    D.p("Service Time: " + (stop-start) + " millils");
                    
                }else{
                    
                    String[] video_titles = new String[18];
//                    video_titles[0] = "Danny";
//                    video_titles[1] = "Fart";
//                    video_titles[2] = "Owen";
//                    video_titles[3] = "Zack";
//                    video_titles[4] = "Yoko";

                    video_titles[0] = "3";
                    video_titles[1] = "2";
                    video_titles[2] = "1";
                    video_titles[3] = "4";
                    video_titles[4] = "5";
                    video_titles[5] = "6";
                    video_titles[6] = "7";
                    video_titles[7] = "96";
                    video_titles[8] = "95";
                    video_titles[9] = "94";
                    
                    video_titles[10] = "Fast & Furious";
                    video_titles[11] = "Xmen";
                    video_titles[12] = "Deadpool";
                    video_titles[13] = "Starwars";
                    video_titles[14] = "Harry Potter";
                    video_titles[15] = "Zack";
                    video_titles[16] = "Eeee";
                    video_titles[17] = "Aaaa";

                    String[] customer_names = new String[8];
                    customer_names[0] = "Andrew";
                    customer_names[1] = "Ben";
                    customer_names[2] = "Perons";
                    customer_names[3] = "Stella";
                    customer_names[4] = "Neil";
                    customer_names[5] = "zack";
                    customer_names[6] = "eeee";
                    customer_names[7] = "aaaa";


                    for(int i=0; i < video_titles.length; i++){
                        vsc.addVideo(video_titles[i]);
                    }
                    
                    for(int i=0; i < customer_names.length; i++){
                        vsc.addCustomer(customer_names[i]);
                    }

                    videoStore.printInStoreCustomers();
                    videoStore.printAllVideos();
                    vsc.checkOutVideo("Andrew", "1", "Deadpool");
                    vsc.checkOutVideo("Stella", "4", "Zack");
                    vsc.checkOutVideo("Andrew", "1", "Aaaa");
                    D.p("aftr Aaaa");
                    vsc.checkOutVideo("Andrew", "1", "Fast & Furious");
                    D.p("aftr Fast & Furious");
                    vsc.printCustomersVideos("Andrew", "1");
                    
                    vsc.printCustomersVideos("Andrew", "1");
                    vsc.checkOutVideo("Andrew", "1", "Harry Potter");
                    vsc.printCustomersVideos("Andrew", "1");
                    vsc.checkInVideo("Andrew", "1", "Harry Potter");
                    vsc.printCustomersVideos("Andrew", "1");
                    vsc.removeCustomer("Perons", "3");
                    videoStore.printInStoreCustomers();
                    videoStore.printAllVideos();
                    videoStore.printInStoreVideos();
                    String checked_out_videos = videoStore.getCheckedOutVideosString();
                    D.p(checked_out_videos);
                    vsc.printCustomersVideos("Andrew", "1");
                    
                    
                //}//this is just for when you want to access the operaitons while test

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
                                D.p("Please enter the title and id of video");
                                D.p("Title: ");
                                String video_title = KBinput.nextLine();
                                vsc.addVideo(video_title);
                                break;
                            case "2":
                                D.p("Please enter the title and id of video you wish to delete.");
                                D.p("Title: ");
                                String video_title_d = KBinput.nextLine();
                                vsc.removeVideo(video_title_d);
                                break;
                            case "3":
                                D.p("Please enter customer name");
                                D.p("Name: ");
                                String customer_name = KBinput.nextLine();
                                vsc.addCustomer(customer_name);
                                break;
                            case "4":
                                D.p("Please enter the name and id of the customer you wish to delete.");
                                D.p("Name: ");
                                String customer_name_d = KBinput.nextLine();
                                D.p("Id: ");
                                String custome_id_d = KBinput.nextLine();
                                if(D.isInteger(custome_id_d)){
                                    vsc.removeCustomer(customer_name_d, custome_id_d);
                                }else{
                                    D.p(custome_id_d + "is not an integer. Please retener valid ID.");
                                }
                                
                                break;
                            case "5":
                                D.p("Please enter the title and id of video");
                                D.p("Title: ");
                                String video_search_title = KBinput.nextLine();
                                boolean video_exists = vsc.videoInStore(video_search_title);
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
                                if(D.isInteger(custome_id_co)){
                                    boolean checked_out_successfully = vsc.checkOutVideo(customer_name_co, custome_id_co, video_title_co);
                                    D.p(Boolean.toString(checked_out_successfully));
                                }else{
                                    D.p(custome_id_co + "is not an integer. Please retener valid ID.");
                                }
                                
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
                                if(D.isInteger(custome_id_ci)){
                                    boolean checked_in_successfully = vsc.checkInVideo(customer_name_ci, custome_id_ci, video_title_ci);  //videoStore.checkin(cCheckin, vCheckIn);
                                    D.p(Boolean.toString(checked_in_successfully));
                                }else{
                                    D.p(custome_id_ci + "is not an integer. Please retener valid ID.");
                                }
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
                                String checked_out_videos_11 = videoStore.getCheckedOutVideosString();
                                D.p(checked_out_videos_11);
                                break;
                            case "12":
                                D.p("Please enter the name and id of the customer you wish to see current checkedout vidoes.");
                                D.p("Name: ");
                                String customer_name_vids = KBinput.nextLine();
                                D.p("Id: ");
                                String custome_id_vids = KBinput.nextLine();
                                vsc.printCustomersVideos(customer_name_vids, custome_id_vids);
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

                    } while (!"X".equals(again) && !"x".equals(again));
                }//end if if args == 1, un comment
            }else{
                D.p("Arguments are not in correct format");
            }
        }
    }
    
    
}

/**
 * Class to control any functionality called more than once in main
 * @author andrewberkow
 */
class VideoController {
    
    public Store videoStore;
    public String data_structure;
    
    public VideoController(Store store, String data_structure){
        this.videoStore = store;
        this.data_structure = data_structure;
    }
    
    public Video addVideo(String video_title ){
        Video v = new Video(video_title);
        videoStore.setVideoInStore(v);
        return v;
    }
    
    public void removeVideo(String video_title ){
        Video vDelete = new Video(video_title);
        videoStore.removeVideoInStore(vDelete);
    }
    
    public Customer addCustomer(String name){
        Customer c = new Customer(name, data_structure);
        return videoStore.setCustomerInStore(c);
    }
    
    public void removeCustomer(String name,String id){
        Customer cDelete = new Customer(name, D.s2i(id), data_structure);
        videoStore.removeCustomerInStore(cDelete);
    }
    
    public boolean videoInStore(String video_title ){
        Video searchVideo = new Video(video_title);
        boolean video_exists = videoStore.contains(searchVideo);
        return video_exists;
    }
    
    public boolean checkOutVideo(String customer_name, String custome_id, String video_title){
        Customer cCheckout = new Customer(customer_name, D.s2i(custome_id), data_structure);
        Video vCheckOut = new Video(video_title);
        boolean checked_out_successfully = videoStore.checkout(cCheckout, vCheckOut);
        return checked_out_successfully;
    }
    
    public boolean checkInVideo(String customer_name, String custome_id, String video_title){
        Customer cCheckin = new Customer(customer_name, D.s2i(custome_id), data_structure);
        Video vCheckIn = new Video(video_title);
        return videoStore.checkin(cCheckin, vCheckIn);
    }
    
    public void printCustomersVideos(String customer_name, String customer_id){
        if(D.isInteger(customer_id)){
            Customer cVideosCheckedOut = new Customer(customer_name, D.s2i(customer_id),data_structure);
            videoStore.printCustomersVideos(cVideosCheckedOut);
        }else{
            D.p(customer_id + " is not an integer. Please retener valid ID.");
        }
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
    
    public static boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }
}
