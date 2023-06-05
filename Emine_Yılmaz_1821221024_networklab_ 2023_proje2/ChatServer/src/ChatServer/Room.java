/*
Bu sınıf, bir sohbet odasını temsil ediyor.
Clients: Sohbet odasına bağlı olan istemcilerin bir listesini içerir. Her bir istemci, client sınıfından bir nesnedir.
roomName: Sohbet odasının adını temsil eder.
bU kod, sohbet odasının istemcilerini yönetmek için kullanılır. Her bir sohbet odası, bağlı olan istemcilerin listesini tutar.
İstemciler, client sınıfından oluşturulan nesnelerdir. Room sınıfı, bu istemci listesini tutarak sohbet odasının işlevselliğini sağlar.
*/


package ChatServer;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {

    public ArrayList<Client> Clients;
    public String roomName;

    public Room(String roomName) {
        this.roomName = roomName;
        this.Clients = new ArrayList<Client>();
    }
}
