
import java.util.ArrayList;


public class NodoGrupo{
    
    ObjectGroup grupo;
    ArrayList<Secuencia> sequency = new ArrayList<>();
    
    public NodoGrupo(ObjectGroup grupo, Secuencia sequency){
        this.grupo = grupo;
        this.sequency.add(sequency);
    }
    
}
