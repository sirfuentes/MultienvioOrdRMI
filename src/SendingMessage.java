
import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendingMessage extends Thread {

    int id;
    GroupMessage mensaje;
    ClientInterface cliente;
    ObjectGroup grupo;

    SendingMessage(GroupMessage mensaje,
            ObjectGroup grupo) throws RemoteException {


        this.mensaje = mensaje;
        this.grupo = grupo;

    }

    @Override
    public void run() {




        for (int i = 0; i < grupo.members.size(); i++) {
            
            GroupMember miembroGrupo = grupo.members.get(i);
            if (!miembroGrupo.alias.equals(mensaje.emisor.alias)) {

                try {
                    
                    
                    String URL = "//" + miembroGrupo.hostname + ":" + miembroGrupo.puerto + "/" + miembroGrupo.alias;
                    System.out.println("Voy a buscar al cliente: " + URL);
                    cliente = (ClientInterface) Naming.lookup(URL);

                    cliente.DepositMessage(mensaje);
                    
                } catch (RemoteException | NotBoundException | MalformedURLException ex) {
                    Logger.getLogger(SendingMessage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }




    }
}
