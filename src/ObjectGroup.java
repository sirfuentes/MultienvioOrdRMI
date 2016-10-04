
import java.util.*;
import java.util.concurrent.locks.*;


public class ObjectGroup {

    String groupName;
    int idGroup;
    ArrayList<GroupMember> members;
    GroupMember propietario;
    int contador = 0;
    int envios = 0;
    boolean permite, opera;
    ReentrantLock lock = new ReentrantLock(true);
    Condition libre;
    ArrayList<Secuencia> listaNumSeq = new ArrayList<>();

    public ObjectGroup(String group, int id, String alias, String hostname, int puerto) {
        this.groupName = group;
        this.idGroup = id;
        members = new ArrayList();
        this.members.add(new GroupMember(alias, hostname, contador, id, puerto));
        this.propietario = members.get(0);
        this.contador = contador + 1;
        opera = false;
        permite = true;
        libre = lock.newCondition();
    }

    public GroupMember isMember(String alias) {
        for (int i = 0; i < this.members.size(); i++) {
            if (this.members.get(i).alias.equals(alias)) {
                return this.members.get(i);
            }
        }
        return null;
    }

    public synchronized GroupMember addMember(String alias, String hostname, int puerto) throws InterruptedException {
        if (permite) {
            lock.lock();
            if (this.isMember(alias) != null) {
                lock.unlock();
                return null;
            } else {
                if (opera) {
                    libre.await();
                }
                opera = true;
                GroupMember miembro = new GroupMember(alias, hostname, contador, idGroup, puerto);
                this.members.add(miembro);
                contador = contador + 1;
                opera = false;
                libre.signal();
                lock.unlock();
                return miembro;
            }
        } else {
            System.out.println("No se pudo añadir miembro en " + this.idGroup + ". Las altas/bajas están bloqueadas");
            return null;
        }
    }

    public synchronized boolean removeMember(String alias) throws InterruptedException {
        if (permite) {
            lock.lock();
            if (opera) {
                libre.await();
            }
            opera = true;
            GroupMember miembro = this.isMember(alias);
            if ((miembro != null) && (!miembro.equals(this.propietario))) {
                this.members.remove(miembro);
                opera = false;
                libre.signal();
                lock.unlock();
                return true;
            } else {
                opera = false;
                libre.signal();
                lock.unlock();
                return false;
            }
        } else {
            System.out.println("No se pudo eliminar miembro en" + this.idGroup + ". Las altas/bajas están bloqueadas");
            return false;
        }
    }

    public void StopMembers() {
        permite = false;
    }

    public void AllowMembers() {
        permite = true;
    }

    void Sending() {
        permite = false;
        envios++;
    }

    int nextSeqNumber(int memberid) {

        int j=0;
        for (int i = 0; i < members.size(); i++) 
        {
            if (members.get(i).idmember == memberid) {
                while( j < listaNumSeq.size()) { 
                    if (memberid == listaNumSeq.get(j).gm.idmember) {
                        listaNumSeq.get(j).incrementarContador();
                        return listaNumSeq.get(j).getContador();
                    }
                    j++;
                }
                Secuencia secuency = new Secuencia(members.get(i));
                listaNumSeq.add(secuency);
                return listaNumSeq.get(j).getContador();


            }
        }
        return -1;
    }
}
