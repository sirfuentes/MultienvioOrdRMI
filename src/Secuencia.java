import java.io.Serializable;

public class Secuencia implements Serializable{
    
    private int contador = 0;
    GroupMember gm;
    
    public Secuencia(GroupMember gm){
        this.gm = gm;
    }
    
    public int getContador(){
        return contador;
    }
    
    public void incrementarContador(){
         contador++;
    }
    
}
