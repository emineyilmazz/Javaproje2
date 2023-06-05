/*
Bu sınıf, sunucuya bağlı olan istemcileri temsil ediyor.
id: İstemcinin benzersiz kimlik numarasını temsil ediyor.
name: İstemcinin adını tutuyor.
roomName: İstemcinin bulunduğu sohbet odasının adını tutar.
soket: İstemciyle sunucu arasındaki soketi temsil eder.
sOutput: İstemciye mesaj göndermek için kullanılan ObjectOutputStream nesnesidir.
sInput: İstemciden mesaj almak için kullanılan ObjectInputStream nesnesidir.
listenThread: İstemcinin gelen mesajları dinlemek için kullanılan ClientListener sınıfından bir nesnedir.
Bu kod her bir istemciye ait bilgileri tutar ve istemciye mesaj gönderme işlemlerini gerçekleştirir. 
sOutput nesnesi üzerinden mesajları gönderirken, sInput nesnesi üzerinden istemciden gelen mesajları almaktadır.
Ayrıca, listenThread nesnesi aracılığıyla istemciye gelen mesajları dinlemektedir.
Sunucunun her bir istemciye ait bilgileri saklamak ve istemcilere iletişim kurmak için bir sınıftır.
*/


package ChatServer;

import Message.Message;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    int id;
    String name = "";
    String roomName = "";
    Socket soket;
    ObjectOutputStream sOutput;
    ObjectInputStream sInput;
    ClientListener listenThread;

    public Client(Socket gelenSoket, int id) {
        this.soket = gelenSoket;
        this.id = id;
        try {
            this.sOutput = new ObjectOutputStream(this.soket.getOutputStream());
            this.sInput = new ObjectInputStream(this.soket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.listenThread = new ClientListener(this);
    }

    public void Send(Message message) {
        try {
            this.sOutput.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
