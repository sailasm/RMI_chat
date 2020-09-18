import java.util.*;


public class Message //class with all the information that needs to be stored of the message
{
    String clientID;
    int messageID;
    String messageContent;
    String date;


   public Message( String clientID, int messageID, String messageContent, String date)  //constructor to make it easy to use with the saveMessages() method in the server side
    {
        this.clientID = clientID; 
        this.messageID = messageID; 
        this.messageContent = messageContent; 
        this.date = date;  
    }
    public String toString(){ //returns all the fields to a printable form 
        
        return "ClientID: " + clientID + " MessageID: " + Integer.toString(messageID) + " messageContent: " + messageContent + " Timestamp: " + date;
    }

    

}