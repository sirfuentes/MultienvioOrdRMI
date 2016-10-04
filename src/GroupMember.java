
import java.io.*;

public class GroupMember implements Serializable{
    String alias;
    String hostname;
    int idmember;
    int idgroup;
    int puerto;
    //int numSeq;
    
    public GroupMember(String al, String host, int idm, int idg, int puerto){
        alias=al;
        hostname=host;
        idmember=idm;
        idgroup=idg;
        this.puerto = puerto;

     }
    
}
