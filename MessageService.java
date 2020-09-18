import java.rmi.*;

public interface MessageService extends Remote {

  public String nextMessage(String clientID) throws RemoteException;

  public void newMessage(String clientID, String message) throws RemoteException;
 }
