import java.rmi.registry.*;
import java.util.*;


public class Client {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in); //creating a scanner object to scan the inputted text
        String clientID="Saku@windows"; // initializing the client name
        try
        {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099); //connecting to the same rmiregistery with the server to use the remote methods
            MessageService messageService = (MessageService) registry.lookup("MessageService");
            while (true) { //Infinite loop to input text once the program is started 
                String text=s.nextLine();   //scanning the inputted line

                if(text.equals("/nextMessage")){    //if the user enters /next message to the console while the client is running it invokes the remote method
                    System.out.println(messageService.nextMessage(clientID));   //printing the returned string containing the past messages
                }
                else{
                    messageService.newMessage(clientID, text); //sending a message to the server
                }
            }
        }
        catch (Exception e) 
        {
            System.out.println ("Client exception: " + e); //exception handling 
        }

    }
}