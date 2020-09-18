import java.io.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;


public class Server extends UnicastRemoteObject implements MessageService{

  private static Logger logger = Logger.getLogger("Logger"); //Creating a Logger object to log the actions of the client
  ArrayList<Message> messageList = new ArrayList<Message>(); //Creating a list where the user messages will be stored
  int messageID = 0;  
  String latestMessages = "";
  boolean areThereMessages = false;
  int allowedMessages = 10; // ammmount of messages that is allowed on the messageList

  protected Server() throws RemoteException, IOException, SecurityException{ //Constructor with all the Execptions
    super();
  }

  @Override
  public void newMessage(String clientID, String message) throws RemoteException { 
    saveMessage(clientID, messageID, message, messageList);
    messageID++; //Increasing the messageID per each message
 }

  @Override
  public String nextMessage(String clientID) throws RemoteException {
    latestMessages = ""; //resetting the latestMessages string
    return giveMessages(messageList, clientID);

  }

  public static void main(String[] args)throws RemoteException, IOException, SecurityException  {
    try {
      FileHandler fh = new FileHandler("logfile.txt");
      logger.addHandler(fh);
      logger.setLevel(Level.ALL); //setting the logger to log everything

      Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099); //locating the registry
      Server server = new Server();
      registry.rebind("MessageService", server);  //binding the registry
      System.out.println("Server is running...");

    } catch (RemoteException e) {
      System.out.println("ERROR: Could not create the registry."); //Error schematics

      e.printStackTrace();
    }
    catch (IOException e) {
      System.out.println("ERROR: Could not log files"); //Error schematics

      e.printStackTrace();
    }
  }

  private void saveMessage(String clientID, int messageID, String message, ArrayList<Message> list) { // function to store messages
    if(allowedMessages <= messageID) {  //When the number of allowed messages is reached the messages are deleted according to the FIFO protocol 
       messageList.remove(0);
    }
    Date date = new Date(); //getting the date of the message arrival
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String timeStamp = formatter.format(date);
    Message msg = new Message(clientID, messageID, message, timeStamp); //Creating a new message object where all the information is stored in 
    list.add(msg); //Adding it to the messageList
    logger.info(msg.toString()); //logging the message
    
  }

  private String giveMessages(ArrayList<Message> messageList, String clientID) { //Function to retrieve the messages from the messageList
    logger.info("Client invoked the remote method nextMessage()"); //Logging that the client is invoking the method
    for (int i = 0; i < messageList.size(); i++) { //For-loop the compare the client ids to the client id of the client that is invoking the function
      if (messageList.get(i).clientID.toString().equals(clientID)) {
        areThereMessages = true;
        latestMessages = latestMessages + "\n" + messageList.get(i).toString();
      }           
      else{

      }
    }
    if (areThereMessages == false){
      latestMessages = null;   //if there are no messages for the client that is looking for them -> setting the string of the latest messages to null
    }
    else{

    }
    return(latestMessages);
  }
}







