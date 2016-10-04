
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInterface extends Remote{
    
    void DepositMessage(GroupMessage m) throws RemoteException;
            
    ArrayList<Byte> receiveGroupMessage(String galias) throws RemoteException;
}
