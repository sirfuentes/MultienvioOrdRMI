
import java.io.Serializable;
import java.util.ArrayList;

public class GroupMessage implements Serializable{
    
    GroupMember emisor;
    ArrayList<Byte> mensaje;
    //mensaje = new byte[];
    int seqnum;
    
    public GroupMessage(GroupMember emisor, ArrayList<Byte> mensaje, int seqnum){
    this.emisor = new GroupMember(emisor.alias, emisor.hostname, emisor.idmember, emisor.idgroup, emisor.puerto);
    this.mensaje = mensaje;
    this.seqnum = seqnum;
    }
}
