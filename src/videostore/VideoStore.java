/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videostore;

import java.util.Scanner;
import java.util.UUID;
import java.util.Random;
import java.util.Objects;

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

/* Models */

/**
 * Video Model
 * @author andrewberkow
 */
class Video implements Comparable {
    
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
    
    @Override
    public int compareTo(Object obj) {
        if(this.getClass().equals(obj.getClass())){
            Video vid2 = (Video) obj;
            String vid_this = getTitle();
            String vid_that = vid2.getTitle();
            return vid_this.compareTo(vid_that);
        }else{
            throw new UnsupportedOperationException("Classes not of same type"); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}

/**
 * Customer Object
 * @author andrewberkow
 */
class Customer implements Comparable {
    private String name;
    public int id;
    public MyStructure rentVideosSLL = new MySLList();
    public MyStructure rentVideosDLL = new MyDLList();
    public MyStructure rentVideosBST = new MyBSTree();
    public MyStructure rentVideosAVL = new MyAVLTree();
    public String structure = "SLL";
    
    public Customer(String name, int id, String structure){
        this.name = name;
        this.id = id;
        this.structure = structure;
    }
    
    public Customer(String name, String structure){
        this.name = name;
        this.id = 0;
        this.structure = structure;
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
        //System.out.println("REnting : " + video.toString());
        getVideos().insert(video);
    }
    
    public Video checkInVideo(Video video){
        return (Video) getVideos().remove(video);
    }
    
    public MyStructure getVideos(){
        MyStructure data_struct;
        switch(structure){
            case "SLL":
                data_struct =  rentVideosSLL;
                //System.out.println("SLL");
                break;
            case "DLL":
                data_struct = rentVideosDLL;
                //System.out.println("DLL");
                break;
            case "BST":
                data_struct = rentVideosBST;
                //System.out.println("BST");
                break;
            case "AVL":
                data_struct = rentVideosAVL;
                //System.out.println("AVL");
                break; 
            default:
                data_struct = rentVideosSLL;
                //System.out.println("default");
                break;
        }
        return data_struct;
    }
    
    @Override
    public String toString(){
        return id + ": " + name + " , Videos: " + getVideos().toString();
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

    @Override
    public int compareTo(Object obj) {
        if(this.getClass().equals(obj.getClass())){
            Customer cust2 = (Customer) obj;
            String customer_this = getName() + "_" + getId();
            String customer_that = cust2.getName() + "_" + cust2.getId();
            return customer_this.compareTo(customer_that);
        }else{
            throw new UnsupportedOperationException("Classes not of same type"); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}

/**
 * Store class. Acts as super class for the different stores.
 * @author andrewberkow
 */
class Store {
    
    MyStructure videoInStore = new MySLList();
    MyStructure customerList = new MySLList();
    public int id_counter = 0;
    public int customer_counter = 0;
    
    /**
     * Constructor 
     */
    public Store(){
        
    }
    
    /**
     * Encapsulate videoInStore
     * @return videoInStore
     */
    protected MyStructure getVideos(){
        return videoInStore;
    }
    
    /**
     * Encapsulate customerList
     * @return customerList
     */
    protected MyStructure getCustomers(){
        return customerList;
    }
    
    /**
     * Check if video in store
     * @param video
     * @return Boolean
     */
    public boolean contains(Video video){
        return getVideos().contains(video);
    }
    
    /**
     * Check if customer in store
     * @param customer
     * @return Boolean
     */
    public boolean contains(Customer customer){
        return getCustomers().contains(customer);
    }
    /**
     * Check video out of store to customer
     * @param customer
     * @param video
     * @return true if successful
     */
    public boolean checkout(Customer customer, Video video){
        //check if customer is not in list
        if(!contains(customer)){
            System.out.println("Customer Does not exist. Creating.");//NTDB remove print for final project
            setCustomerInStore(customer);//add customer
        }
        //check if video is in store
        if(contains(video)){
            Video checkedOutVideo = removeVideoInStore(video);
            Customer listCustomer =  (Customer) getCustomers().get(customer);
            listCustomer.rentVideo(checkedOutVideo);
            return true;
        }else{
            //return false if not in stock
            //System.out.println(video.getTitle() + " is not in stock.");//NTDB remove print for final project
            return false;
        }
    }
    
    /**
     * Check video into store
     * @param customer
     * @param video
     * @return true if successful
     */
    public boolean checkin(Customer customer, Video video){
        //check if customer is not in list
        if(!contains(customer)){
            //System.out.println("Customer Does not exist. Creating.");//NTDB make unqiue ID
            setCustomerInStore(customer);//add customer
        }
        Customer listCustomer =  (Customer) getCustomers().get(customer);
        //check if customer has video
        if(listCustomer.getVideos().contains(video)){
            Video checkedInVideo = listCustomer.checkInVideo(video);
            setVideoInStore(checkedInVideo);
            return true;
        }else{
            //customer does not have video
            //System.out.println(listCustomer.getName() + "does not have " + video.getTitle() );
            return false;
        }
    }
    
    /**
     * Set a video in the store
     * @param video 
     * @return Video
     */
    public Video setVideoInStore(Video video){
        if(video.getId() == 0){
            id_counter++;
            video.setId(id_counter);
        }
        getVideos().insert(video);
        return video;
    }
    
    /**
     * Remove video from store
     * @param video
     * @return 
     */
    public Video removeVideoInStore(Video video){
        return (Video) getVideos().remove(video);
    }
    
    /**
     * Set customer in list customer list
     * @param customer 
     * @return Customer
     */
    public Customer setCustomerInStore(Customer customer){
        if(customer.getId() == 0){
            customer_counter++;
            customer.setId(customer_counter);
        }
        getCustomers().insert(customer);
        return customer;
    }
    
    /**
     * Remove customer from store
     * @param customer 
     */
    public void removeCustomerInStore(Customer customer){
        getCustomers().remove(customer);
    }
    
    /**
     * Print videos in stock
     */
    public void printInStoreVideos(){
        System.out.println(getVideos().toString());
    }
    
    /**
     * Print list of customer
     */
    public void printInStoreCustomers(){
        System.out.println(getCustomers().toString());
    }
    
    /**
     * Print video from a customer
     * @param customer 
     */
    public void printCustomersVideos(Customer customer){
        //check if customer is not in list
        if(!contains(customer)){
            System.out.println("Customer Does not exist.");//NTDB make unqiue ID
            return;
        }
        
        Customer listCustomer =  (Customer) getCustomers().get(customer);
        System.out.println(listCustomer.getVideos().toString());
        
    }
    
    /**
     * Iterates through each customer and creates list of videos for each customer
     * @return String 
     */
    public String getCheckedOutVideosString(){
        
        String video_string = "";
        Comparable[] customer_array = getCustomers().toArray();
        int num_of_customers = customer_array.length;
        if(num_of_customers > 0){
            for(int i = 0; i < num_of_customers; i++){
                Customer cust = (Customer) customer_array[i];
                video_string += cust.getVideos().toString();
            }
        }
        return video_string;
    }
    
    
    public void printAllVideos(){
        System.out.println(getVideos().toString() + getCheckedOutVideosString());
    }
}


/**
 * Subclass of store for DLL
 * @author andrewberkow
 */
class DLLStore extends Store{
    
    MyDLList videoInStore = new MyDLList();
    MyDLList customerList = new MyDLList();
    
    @Override
    public Video setVideoInStore(Video video){
        if(video.getId() == 0){
            id_counter++;
            video.setId(id_counter);
        }
        videoInStore.addFirst(video);
        return video;
    }
    
    @Override
    protected MyDLList getVideos(){
        return videoInStore;
    }
    
    @Override
    protected MyDLList getCustomers(){
        return customerList;
    }

}


/**
 * Subclass of Store for BST.
 * @author andrewberkow
 */
class BSTStore extends Store{
    MyStructure videoInStore = new MyBSTree();
    MyStructure customerList = new MyBSTree();
    
    @Override
    protected MyStructure getVideos(){
        return videoInStore;
    }
    
    @Override
    protected MyStructure getCustomers(){
        return customerList;
    }
}


/**
 * Subclass of store for AVL Tree
 * @author andrewberkow
 */
class AVLStore extends Store{
    MyStructure videoInStore = new MyAVLTree();
    MyStructure customerList = new MyAVLTree();
    
    @Override
    protected MyStructure getVideos(){
        return videoInStore;
    }
    
    @Override
    protected MyStructure getCustomers(){
        return customerList;
    }
}

/* Model End */

/* Collections */

/**
 * Interface for the different data structures in this project
 * @author andrewberkow
 */
interface MyStructure {
    
    void insert(Comparable element);
    Comparable remove(Comparable element);
    boolean contains(Comparable element);
    Comparable[] toArray();
    Object get(Comparable element);
    int getSize();
    
}


/**
 * MySLList is singly linked list. Nodes store comparable object
 * @author andrewberkow
 */
class MySLList implements MyStructure{

    SLListNode head;// First SLListNode in the linked list
    SLListNode ref;// refrence node to store removed nodes 
    int length;

    /**
     * Constructor for MySLList
     */
    public MySLList() {
        head = null;
        length = 0;
    }
    
    /**
     * Return head node
     * @return SLListNode
     */
    protected SLListNode getHead(){
        return head;
    }
    
    /**
     * Set Node as head
     * @param node SLListNode you wish to set as head of list
     */
    public void setHead(SLListNode node){
        head = node;
    }
    
    /**
     * Return size
     * @return size
     */
    @Override
    public int getSize(){
        return length;
    }
    
    /**
     * Insert object into front of list
     * @param element Object you wish to insert
     */
    @Override
    public void insert(Comparable element) {
        if (head == null) {
            head = new SLListNode(element, null);
        } else {
            head = new SLListNode(element, head);
        }
        length++;
    }

    /**
     * Clears the lined list
     */
    public void clear() {
        head = null;
        length = 0;
    }
    
    /**
     * Search list for element. Return true if exists. O(n)
     * NTDB - MAKE THIS CALL GET 
     * @param element
     * @return Boolean
     */
    @Override
    public boolean contains(Comparable element){
        ref = getHead();
        if(getSize() > 0){
            if(ref.getElement().equals(element)){
                return true;
            }
            while(ref.getNext() != null && !ref.getNext().getElement().equals(element)){
                ref = ref.getNext();
            }
            return ref.getNext() != null;
        }else{
            return false;
        }
    }
    
    /**
     * Search for element and return if in SLL. 0(n)
     * @param element
     * @return Object
     */
    @Override
    public Object get(Comparable element){
        ref = getHead();
        if(ref.getElement().equals(element)){
            return ref.getElement();
        }
        while(ref.getNext() != null && !ref.getNext().getElement().equals(element)){
            ref = ref.getNext();
        }
        if(ref.getNext() == null){
            return null;
        }
        return ref.getNext().getElement();
    }
    
    /**
     * Removes object from list
     * 
     * @param element Object you wish to remove from list
     * @return Object removed, returns null if does not exist.
     */
    @Override
    public Comparable remove(Comparable element){
        ref = getHead();
        if(ref.getElement().equals(element)){
            setHead(ref.getNext());
            return ref.getElement();
        }
        while(ref.getNext() != null && !ref.getNext().getElement().equals(element)){
            ref = ref.getNext();
        }
        if(null == ref.getNext()){
            return null;
        }
        Comparable rem = ref.getNext().getElement();
        ref.setNext(ref.getNext().getNext()); 
        length--;
        return rem;
    }
    
    /**
     * Checks if the list is empty 
     * 
     * @return True is head is empty, returns false if head contains a node
     */
     public boolean isEmpty() {
        return getHead() == null; 
    }
     
     /**
      * Generates a toString that contains all Objects in list, in order.
      * 
      * @return String containing all Objects in list, in order, seperated by commas. 
      */
    @Override
     public String toString(){
         String b = "";
         ref = getHead();
         while(ref != null){
             b += (ref.data.toString() + ", ");
             ref = ref.getNext();
         }
         return b; 
     }
        
    /**
     * Return SLL as an array
     * @return Comparable[]
     */ 
    @Override
    public Comparable[] toArray() {
        if(getSize() > 0){
            Comparable[] ssl_array = new Comparable[getSize()];
            ref = getHead();
            ssl_array[0] = ref.getElement();
            int i = 1;
            while(null != ref.getNext()){
                ref = ref.getNext();
                ssl_array[i] = ref.getElement();
                i++;
            }
            return ssl_array;
        }else{
            return null;
        }        
    }
    
}

/**
 * Node for linked list
 * Two fields: Object data and SLListNode next
 * 
 * @author AndrewBerkow
 */
class SLListNode {
    
        // Object stored in node
        public Comparable data;
        // Link to next node in linked list
        SLListNode next;

        /**
         * Constructor for SLListNode
         * 
         * @param data object you wish to store
         * @param next link to nexet node in list
         */
        SLListNode(Comparable data, SLListNode next) {
            this.data = data;
            this.next = next;
        }
        
       
        public Comparable getElement(){
            return data;
        }
        
        public SLListNode getNext(){
            return next;
        }
        
        public void setNext(SLListNode next){
            this.next = next;
        }
        
}

/**
 * MyDLList is double linked list. Nodes store comparable object. Extends MySLList
 * @author andrewberkow
 */
class MyDLList extends MySLList{
    
    private DLListNode head;
    private DLListNode trail;
    private DLListNode ref;
    // refrence node to store removed nodes 
    
    public MyDLList(){
        head = new DLListNode(null, null, null);
        trail = new DLListNode(null, head, null);
        head.setNext(trail);
        length = 0;
    }
    
    /* Returns (but does not remove) the first element of the list. */ 
    public DLListNode first() {
        if (isEmpty()) return null;
        return head.getNext(); // first element is beyond header 
    }
    
    /* Returns (but does not remove) the last element of the list. */ 
    public DLListNode last() {
        if (isEmpty()) return null;
        return trail.getPrev(); // last element is before trailer 
    }
    
    /**
     * Tells is DLL is empty
     * @return 
     */
    @Override
    public boolean isEmpty() { return getSize() == 0; }
    
    /**
     * Inserts after head 
     * @param element 
     */
    @Override
    public void insert(Comparable element){
        addFirst(element);
    }
    
    /**
     * Adds element e to the front of the list.
     * @param element 
     */
    public void addFirst(Comparable element) {
        addBetween(element, head, head.getNext());
    }
    
    /**
     * Adds element e to the end of the list.
     * @param element
     */
    public void addLast(Comparable element) {
        addBetween(element, trail.getPrev(), trail);
    }
    
    /**
     * Adds element e to the linked list in between the given nodes.
     * @param element
     * @param predecessor
     * @param successor 
     */
    private void addBetween(Comparable element, DLListNode predecessor, DLListNode successor) {
        DLListNode newest = new DLListNode(element, predecessor, successor);// create and link a new node
        predecessor.setNext(newest);
        successor.setPrev(newest);
        length++;
    }
    
    /**
     * Clear DLL
     */
    @Override
    public void clear() {
        head.setNext(trail);
        trail.setPrev(head);
        length = 0;
    }
    
    /**
     * Check is DLL contains element
     * @param element
     * @return boolean
     */
    @Override
    public boolean contains(Comparable element){
        return get(element) != null;
    }
    
    /**
     * Gets element if in DLL
     * @param element
     * @return Comparable
     */
    @Override
    public Comparable get(Comparable element){
        DLListNode needle = getNode(element);
        if(needle == null){
            return null;
        }else{
            return needle.getElement();
        }
    }
    
    /**
     * Search DLL for node that contains element and return node. If not found return null
     * @param element
     * @return DLListNode
     */
    private DLListNode getNode(Comparable element){
        DLListNode ref = head.getNext();
        while(ref.getElement() != null && !ref.getElement().equals(element)){
            ref = ref.getNext();
        }
        if(ref == null){
            return null;
        }
        return ref;
    }
    
    /**
     * Removes object from list and return it
     * @param element
     * @return 
     */
    @Override
    public Comparable remove(Comparable element){
        DLListNode delNode = getNode(element);
        if(delNode.getElement() == null){
            return null;
        }
        DLListNode predecessor = delNode.getPrev();
        DLListNode successor = delNode.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        length --;
        return delNode.getElement();
    }
    
    /**
     * Return DLL as comma split string
     * @return String
     */
    @Override
     public String toString(){
         String b = "";
         DLListNode ref_ = head.getNext();
         while(ref_.getElement() != null){
             if(ref_.getNext().getElement() != null){
                b += (ref_.getElement().toString() + ", ");
             }else{
                b += (ref_.getElement().toString());
             }
             ref_ = ref_.getNext();
         }
         return b; 
     }

    /**
     * Return DLL as an array
     * @return Comparable[]
     */ 
    @Override
    public Comparable[] toArray() {
        Comparable[] dll_array = new Comparable[getSize()];
        if(getSize() > 0){
            ref = head.getNext();
            dll_array[0] = ref.getElement();
            int i = 1;
            while(null != ref.getNext().getElement()){
                ref = ref.getNext();
                dll_array[i] = ref.getElement();
                i++;
            }
            return dll_array;
        }else{
            return dll_array;
        }        
    } 
}

/**
 * DLListNode node for DLL
 * @author andrewberkow
 */
class DLListNode{
    
     // Object stored in node
    public Comparable data;
    // Link to next node in linked list
    private DLListNode next;
    private DLListNode prev;
        
    DLListNode(Comparable data, DLListNode prev, DLListNode next){
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
    
    public Comparable getElement(){
        return data;
    }
        
    public DLListNode getNext(){
        return next;
    }
        
    public DLListNode getPrev(){
        return prev;
    }
    
    public void setNext(DLListNode nextNode){
        next = nextNode;
    }
        
    public void setPrev(DLListNode prevNode){
        prev = prevNode;
    }
       
}


/**
 * Binary Search Tree BSTree starts with a root TreeNode. Stores int's
 * Each node contains a right and left link. Data less than the value
 * of the node gets stored to the left, greater than gets stored to the right.
 * Fields are root, left and right.
 * 
 * @author AndrewBerkow
 */
class MyBSTree implements MyStructure{

    public TreeNode root;// TreeNode that is the root
    private int length;

    //NTBD - CAHGNE GETDATA TO GET DLEMENT FOR NODE
    /**
     * Constructor, contains no parameters
     */
    public MyBSTree() {
    }
    
    
    /**
     * Encapsulate root
     * @return root
     */
    public TreeNode getRoot(){
        return root;
    }
    
    public void setRoot(TreeNode node){
        root = node;
    }
    
    /**
     * Encapsulate size
     * @return size
     */
    @Override
    public int getSize(){
        return length;
    }

    /**
     * Add new node to tree. If tree is empty, root == data. 
     * Else call add(node, data)
     *
     * @param element data contained by new node
     */
    @Override
    public void insert(Comparable element) {
        if (getRoot() == null) {
            setRoot(new TreeNode(element, null, null));
            incrementSize();
        } else {
            incrementSize();
            add(getRoot(), element);
        }
    }

    /**
     * Recursive function called by insert(element)
     * 
     * @param node node that the new node will be the child of
     * @param data data contained in new node
     */
    protected TreeNode add(TreeNode node, Comparable element) {
        //if comes before in alpha
        if (node.getData().compareTo(element) > 0) {
            if (node.getLeft() == null) {
                node.setLeft(new TreeNode(element, null, null));
                rebalanceInsert(node, node.getLeft());//hook for sublcasses that rebalance
                return node;//return the parent of inserted node
            } else {
                return add(node.getLeft(), element);
            }
        } else {
            if (node.getRight() == null) {
                node.setRight(new TreeNode(element, null, null));
                rebalanceInsert(node, node.getRight());//hook for sublcasses that rebalance
                return node;//return the parent of inserted node
            } else {
                return add(node.getRight(), element);
            }
        }
    }
    
    /**
     * Checks if the tree contains the int
     * 
     * @param element the int you are searching the tree for
     * @return boolean
     */
    @Override
    public boolean contains(Comparable element) {
        return contains(getRoot(), element);
    }

    /**
     * Recursive statement used by contains(int data) 
     * 
     * @param node node you are checking the data of, and the data of its children
     * @param element what you are searching for
     * @return boolean
     */
    private boolean contains(TreeNode node, Comparable element) {
        if (node == null) {
            return false;
        } else if (element.compareTo(node.getData()) == 0){
            return true;
        } else if (element.compareTo(node.getData()) < 0) {
            return contains(node.getLeft(), element);
        } else {
            return contains(node.getRight(), element);
        }
    }
    
    /**
     * Get the element from BST
     * @param element
     * @return Object
     */
    @Override
    public Object get(Comparable element) {
        TreeNode node = getNode(element);
        if (node == null) {
            return null;
        }
        return node.getData();
    }
    
    /**
     * Get node containing element from BST
     * @param element
     * @return TreeNode
     */
    private TreeNode getNode(Comparable element) {
        return get(getRoot(), element);
    }

    /**
     * Recursive statement to search for node that matches element
     * @param node
     * @param element
     * @return TreeNode
     */
    private TreeNode get(TreeNode node, Comparable element) {
        if (node == null) {
            return null;//node is empty
        } else if (element.compareTo(node.getData()) == 0){
            return node;//match
        } else if (element.compareTo(node.getData()) < 0) {
            return get(node.left, element);
        } else {
            return get(node.right, element);
        }
    }
    
    /**
     * remove element from BST
     * @param element
     * @return Comparable element that was removed
     */
    @Override
    public Comparable remove(Comparable element){
        decremenetSize();
        TreeNode delNode = removeNode(element);
        if(delNode == null){
            return null;
        }else{
            return delNode.getData();
        }
    }
    
    /**
     * Remove node from BST
     * @param element
     * @return TreeNode copy of node that was removed
     */
    private TreeNode removeNode(Comparable element) {
        //if the root node is the delete node
        if(element.compareTo(getRoot().getData()) == 0){
            TreeNode removedNode = new TreeNode(getRoot().getData(), null, null);
            //set root to the node that was shifted up after deletion
            setRoot(removeAndShift(getRoot()));
            //return the new route
            return removedNode;
        }
        return remove(getRoot(), element);
    }

    /**
     * Recursive statement that finds node to delete and returns copy of deleted node.
     * @param node
     * @param element
     * @return TreeNode node containing data of removed node
     */
    private TreeNode remove(TreeNode node, Comparable element) {
        if (node == null) {
            //node is leaf child of leaf, return nothing
            return null;
        } 
        
        //check if element lives on left or right of node
        if (element.compareTo(node.getData()) < 0) {//go left
            if(node.getLeft() != null){//failover to make sure there is something in left node
                if(element.compareTo(node.getLeft().getData()) == 0){
                    //left is the node we are looking for
                    //create a node node to hold the data of node we are about to delte
                    TreeNode removedNode = new TreeNode(node.getLeft().getData(), null, null);
                    //call remove and shift to remove the node and set the new node that took the deleted nodes place to left
                    node.setLeft(removeAndShift(node.getLeft()));
                    //rebalance hook to rebalance tree for subclass that balance tree
                    rebalanceRemove(node, node.getLeft());
                    //return the node we deleted
                    return removedNode;
                }
                return remove(node.getLeft(), element);//keep moving down tree
            }else{
                return null;//node does not exist
            }
        } else {//go right
            if(node.getRight() != null){//failover to make sure there is something in left node
                if(element.compareTo(node.getRight().getData()) == 0){
                    TreeNode removedNode = new TreeNode(node.getRight().getData(), null, null);//create a node to reutrn with data of removed node.
                    node.setRight(removeAndShift(node.getRight()));
                    rebalanceRemove(node, node.getRight());
                    return removedNode;
                }
                return remove(node.getRight(), element);
            }else{
                return null;
            }
        }
    }
    
    /**
     * Preform deletion of node and return the node that will takes its place
     * @param deleteNode
     * @return TreeNode the node that takes place of removed node
     */
    private TreeNode removeAndShift(TreeNode deleteNode){
            //if has two child notes
            TreeNode ref_right = deleteNode.getRight();
            TreeNode ref_left = deleteNode.getLeft();
            //check if node has two leaves, one leaf or is leaf 
            if(deleteNode.hasTwoLeaves()){
                //has two leafs
                if(ref_right.getLeft() == null){
                    //if right node has no left node. in this the right leaf will be the 
                    deleteNode = ref_right;
                    deleteNode.setLeft(ref_left);
                }else{
                    //node to the right of the deleted node has left children
                    TreeNode w = ref_right;
                    //set w to the leftmost child that also has a left node
                    while(w.getLeft().getLeft() != null){
                        w = w.getLeft();
                    }
                    
                    deleteNode.setParent(w);//set the parent of the delete node to the same parent that would replace it so we can refernce this in rebalance. This is not optimal but was required for fix bug in AVL
                    //get the data of the mode that will replace deleted node 
                    Comparable wData = w.getLeft().getData();
                    //check if the node that will be moved to deleted nodes spot has children
                    if(w.getLeft().isExteranlNode()){
                        //no children
                        w.setLeft(null);//set w's left to null
                    }else{
                        //has children (must be right since this is left most)
                        //set the left of w to the node that will replace the removed node's right child
                        w.setLeft(w.getLeft().getRight());
                    }
                    deleteNode.set(wData);//set data from removed node to root
                }
                
            }else if(deleteNode.hasLeft()){
                //if only left node just move left node one step up
                deleteNode = deleteNode.getLeft();
            }else if(deleteNode.hasRight()){
                //if only right node just move right node one step up
                deleteNode = deleteNode.getRight();
            }else{
                //has no nodes so do nothing
                return null;
            }
            return deleteNode;
    }
    
    protected void rebalanceInsert(TreeNode parent, TreeNode x){
        //D.p("BST relance.");
    }
    
    protected void rebalanceRemove(TreeNode parent, TreeNode x){
        
    }
    
    /**
     * Increments size of BST
     */
    public void incrementSize(){
        length++;
    }
    
    /**
     * Increments size of BST
     */
    public void decremenetSize(){
        length--;
    }

    /**
     * Returns tree as a pre ordered array
     * @return Comparable[] pre ordered array of comparable objects
     */
    public Comparable[] preorder() {
        if (getRoot() == null) {
            return null;
        } else {
            return preorder(getRoot());
        }
        
    }

    /**
     * Recursive statement to generate pre order array
     * @param node node you are printing (if it has data) and the node that the
     * children of will be called recursed
     * @return Comparable[]
     */
    private Comparable[] preorder(TreeNode node) {
        if (node == null) {
            return null;
        } else {
            Comparable[] bst_array = new Comparable[1];
            bst_array[0] = node.getData();
            
            //D.p(node.data.toString() + "( height : " + node.getHeight() + " )");
            //System.out.println(node.getData().toString() +",");
            //s += preorder(node.left);
            //s += preorder(node.right);
            
            Comparable[] bst_array_left = preorder(node.left);
            if(bst_array_left != null)
                bst_array =  D.concat(bst_array, bst_array_left);
            
            Comparable[] bst_array_right = preorder(node.right);
            if(bst_array_right != null)
                bst_array = D.concat(bst_array, bst_array_right);
            
            return bst_array;
        }
    }

    /**
     * Returns an in order array of BEST
     * @return Comparable[]
     */
    public Comparable[] inorder() {
        if (root == null) {
            return null;
        } else {
            return inorder(root);
        }
    }
    
    /**
     * Recursive statement called by inorder()
     * 
     * @param node node you recursivly call the left child of, print than recrusivly call the right child of
     * @return Comparable[]
     */
    private Comparable[] inorder(TreeNode node) {
        if (node == null) {
            return null;
        } else {
            Comparable[] bst_array = new Comparable[1];
            bst_array[0] = node.getData();
            
            Comparable[] bst_array_left = inorder(node.left);
            if(bst_array_left != null)
                bst_array =  D.concat(bst_array_left, bst_array);
           
            Comparable[] bst_array_right = inorder(node.right);
            if(bst_array_right != null)
                bst_array = D.concat(bst_array, bst_array_right);
           return bst_array;
        }
    }
      
    /**
     * Returns comma split list of tree
     * @return String
     */
    @Override
    public String toString(){
        String s = String.join(",", toStringArray());
        return s;
    }
    
    /**
     * Returns array of tree with each node as a string
     * @return String[]
     */
    public String[] toStringArray(){
        String[] bst_s_array = new String[getSize()];
        Comparable[] preorder_arr = preorder();
        if(getSize() > 0)
            for(int i = 0; i < preorder_arr.length; i++){
                bst_s_array[i] = preorder_arr[i].toString();
            }
        return bst_s_array;
    }
    
    /**
     * Returns tree as array of Comparable elements
     * @return Comparable[]
     */
    @Override
    public Comparable[] toArray() {
        Comparable[] bst_array = preorder();
        return bst_array;
    }

}

/**
 * TreeNode that MyBSTree uses to store data and link to other nodes.
 * Four fields: TreeNode right, TreeNOde left, TreeNOde Parent and comparable element as data.
 * 
 * @author AndrewBerkow
 */
class TreeNode {
    // link to right node
    TreeNode right;
    // link to left node
    TreeNode left;
    // intergar stored in node
    Comparable data;
    
    TreeNode parent;
    
    int height;

    /**
     * Constructor for TreeNode
     * 
     * @param data Comparable stored in node
     * @param left link to left node
     * @param right link to right node
     */
    TreeNode(Comparable data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
        parent = null;
    }
    
    public TreeNode getLeft(){
        return left;
    }
    
    public TreeNode getRight(){
        return right;
    }
    
    public TreeNode getParent(){
        return parent;
    }
    
    public Comparable getData(){
        return data;
    }
    
    public int getHeight(){
        return height;
    }
    
    public void set(Comparable data){
        this.data = data;
    }
    
    public void setLeft(TreeNode node){
        left = node;
    }
    
    public void setRight(TreeNode node){
        right = node;
    }
    
    public void setParent(TreeNode node){
        parent = node;
    }
    
    public void setHeight(int h){
        height = h;
    }
    
    public boolean hasTwoLeaves(){
        return (getLeft() != null && getRight() != null);
    }
    
    public boolean isExteranlNode(){
        return (getLeft() == null && getRight() == null);
    }
    
    public boolean hasLeft(){
        return left != null;
    }
    
    public boolean hasRight(){
        return right != null;
    }
    
    public int incrementHeight(){
        height++;
        return height;
    }
    
}

/**
 * MyAVL Tree extends BST. Makes use of balance hooks and the nodes will store a parent relationship.
 * @author andrewberkow
 */
class MyAVLTree extends MyBSTree{
    
    public TreeNode root;// TreeNode that is the root
    private int length;

    /**
     * Constructor, contains no parameters
     */
    public MyAVLTree() {
    }
    
    /**
     * Encapsulate root
     * @return root
     */
    @Override
    public TreeNode getRoot(){
        return root;
    }
    
    /**
     * Change root to node
     * @param node node that will be new root
     */
    @Override
    public void setRoot(TreeNode node){
        root = node;
        if(root != null)//makes sure root is a node. 
            root.setParent(null);
    }
    
    /**
     * Return length
     * @return int
     */
    @Override
    public int getSize(){
        return length;
    }
    
    /**
     * Increments size of BST
     */
    @Override
    public void incrementSize(){
        length++;
    }
    
    /**
     * Decrements size of BST
     */
    @Override
    public void decremenetSize(){
        length--;
    }
    
    /**
     * Add new node to tree. If tree is empty, root == data. Else call add(node, data)
     * @param element element contained by new node
     */
    @Override
    public void insert(Comparable element) {
        if (getRoot() == null) {
            setRoot(new TreeNode(element, null, null));
            getRoot().setHeight(1);
            incrementSize();
        } else {
            incrementSize();
            super.add(getRoot(), element);
        }
    }
    
    /**
     * Called to rebalance tree after node inserted
     * @param parent
     * @param x 
     */
    @Override
    public void rebalanceInsert(TreeNode parent, TreeNode x){
        x.setParent(parent);
        x.setHeight(1);
        TreeNode ref = x;
        remeasureInsert(ref);
        while(ref.getParent() != null){
            int balance = getBalance(ref.getParent());
            if(balance > 1 || balance < -1){
                TreeNode z = ref.getParent();
                rebalance(z);
                break;
            }
            ref = ref.getParent();
        }
        remeasureInsert(x);
    }
    
    /**
     * Called to rebalance tree after node is removed
     * @param parent node above deleted node
     * @param newChild node in the place of the node that was deleted
     */
    @Override
    public void rebalanceRemove(TreeNode parent, TreeNode newChild){
        TreeNode ref;
        //parent of deleted node
        if(newChild != null){
            ref = newChild.getParent();//get the old parent of the new child, this is where will we start searching for rebalanced nodes from.
            newChild.setParent(parent);//set new parent
            //need to set the parent relationship of the new child to both of its children
            if(newChild.hasLeft())
                newChild.getLeft().setParent(newChild);
            if(newChild.hasRight())
                newChild.getRight().setParent(newChild);
        }else{
            ref = parent;
        }
        
        remeasureInsert(ref);//re set heights of nodes before rebalance
        
        int b = getBalance(ref);
        if(b > 1 || b < -1){
            TreeNode z = ref.getParent();
            rebalance(z);
        }else{
           //NTBD t- this might not work since not check self
            while(ref.getParent() != null){
                int balance = getBalance(ref.getParent());
                if(balance > 1 || balance < -1){
                    TreeNode z = ref.getParent();
                    rebalance(z);
                    break;
                }
                ref = ref.getParent();
            } 
        }
        remeasureInsert(parent);//re set heights of nodes before rebalance
    }
    
    /**
     * Accepts unbalanced node and rebalances that node
     * @param z 
     */
    public void rebalance(TreeNode z){
        int balance = getBalance(z);
        TreeNode y;
        TreeNode x;
        //left cases
        if(balance > 1){
            y = z.getLeft();
            if(height(y.getRight()) > height(y.getLeft())){
                //left right
                x = y.getRight();
                rotateDoubleLeft(z, y, x);
            }else{
                //left left
                x = y.getLeft();
                rotateSingleLeft(z, y, x);
            }
            
        }
        //right cases
        if(balance < -1){
            y = z.getRight();
            if(height(y.getLeft()) > height(y.getRight())){
                //right left
                x = y.getLeft();
                rotateDoubleRight(z, y, x);
            }else{
                //right right
                x = y.getRight();
                rotateSingleRight(z, y, x);
            }
        }
    }
    
    /**
     * Preform single right rotation 
     * @param z
     * @param y
     * @param x 
     */
    public void rotateSingleRight(TreeNode z, TreeNode y, TreeNode x){
        TreeNode a = z;
        TreeNode b = y;
        TreeNode c = x;
        TreeNode t0 = z.getLeft();
        TreeNode t1 = y.getLeft();
        TreeNode t2 = x.getLeft();
        TreeNode t3 = x.getRight();
        rotation( a,  b,  c,  t0,  t1,  t2,  t3,  z);
    }
    
    /**
     * Preform single left rotation 
     * @param z
     * @param y
     * @param x 
     */
    public void rotateSingleLeft(TreeNode z, TreeNode y, TreeNode x){
        TreeNode a = x;
        TreeNode b = y;
        TreeNode c = z;
        TreeNode t0 = x.getLeft();
        TreeNode t1 = x.getRight();
        TreeNode t2 = y.getRight();
        TreeNode t3 = z.getRight();
        rotation( a,  b,  c,  t0,  t1,  t2,  t3,  z);
    }
    
    /**
     * Preform double right rotation 
     * @param z
     * @param y
     * @param x 
     */
    public void rotateDoubleRight(TreeNode z, TreeNode y, TreeNode x){
        TreeNode a = z;
        TreeNode b = x;
        TreeNode c = y;
        TreeNode t0 = z.getLeft();
        TreeNode t1 = x.getLeft();
        TreeNode t2 = x.getRight();
        TreeNode t3 = y.getRight();
        rotation( a,  b,  c,  t0,  t1,  t2,  t3,  z);
    }
    
    /**
     * Preform double left rotation 
     * @param z
     * @param y
     * @param x 
     */
    public void rotateDoubleLeft(TreeNode z, TreeNode y, TreeNode x){
        TreeNode a = y;
        TreeNode b = x;
        TreeNode c = z;
        TreeNode t0 = y.getLeft();
        TreeNode t1 = x.getLeft();
        TreeNode t2 = x.getRight();
        TreeNode t3 = z.getRight();
        rotation( a,  b,  c,  t0,  t1,  t2,  t3,  z);
    }
    
    
    /**
     * Performs the rotation
     * @param a
     * @param b
     * @param c
     * @param t0
     * @param t1
     * @param t2
     * @param t3
     * @param z 
     */
    public void rotation(TreeNode a, TreeNode b, TreeNode c, TreeNode t0, TreeNode t1, TreeNode t2, TreeNode t3, TreeNode z){
        //attatch z to rest of tree
        if(z.getParent() != null){
            if(z.getParent().hasTwoLeaves()){
                if(z.getData().compareTo(z.getParent().getLeft().getData()) == 0){//ref node is the left of parent
                    z.getParent().setLeft(b);
                }else{
                    z.getParent().setRight(b);
                }
            }else if(z.getParent().hasLeft()){
                z.getParent().setLeft(b);
            }else{
                z.getParent().setRight(b);
            }
            
            b.setParent(z.getParent());
        }else{
            b.setParent(null);
            setRoot(b);
        }
        //set z to left of y
        a.setParent(b);
        b.setLeft(a);
        a.setLeft(t0);
        a.setRight(t1);
        if(t0 != null)
            t0.setParent(a);
        if(t1 != null)
            t1.setParent(a);
        //set x to right of y
        c.setParent(b);
        b.setRight(c);
        c.setLeft(t2);
        c.setRight(t3);
        if(t2 != null)
            t2.setParent(c);
        if(t3 != null)
            t3.setParent(c);
        //update heights
        a.height = max(height(a.getLeft()), height(a.getRight())) + 1;
        c.height = max(height(c.getLeft()), height(c.getRight())) + 1;
        //b.height = max(height(c.getLeft()), height(b.getRight())) + 1;
        remeasureInsert(b);
    }
    
    /**
     * Rebalances node and iterates up the parent nodes to balance each one
     * @param x 
     */
    private void remeasureInsert(TreeNode x){
        TreeNode ref = x;
        //test to iterate through paraents
        while(ref.getParent() != null){
            ref.setHeight(max(height(ref.getLeft()), height(ref.getRight())) + 1);
            ref = ref.getParent();
        }
        ref.setHeight(max(height(ref.getLeft()), height(ref.getRight())) + 1);
    }
    
     /**
      * Return an in for the different of left and right children
      * @param node
      * @return int
      */
    public int getBalance(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }
    
    /**
     * Return height of node, return 0 if node is empty
     * @param node
     * @return int
     */
    int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }
    
    /**
     * Accepts two ints and returns the max
     * @param a
     * @param b
     * @return 
     */
    int max(int a, int b) {
        return (a > b) ? a : b;
    }
    
}

/* Collections End */

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
    
    public static Comparable[] concat(Comparable[] a, Comparable[] b) {
        int aLen = a.length;
        int bLen = b.length;
        Comparable[] c= new Comparable[aLen+bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
     }
}
