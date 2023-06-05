/*
Start(String ip, int port, String userName) metot, sunucuya bağlanmak için kullanılır. Verilen IP adresi ve bağlantı üzerinden bir
soket oluşturur. Ayrıca kullanıcı adını kaydeder. Ardından gerekli akışları (input-output stream) oluşturur ve bir ServerListener örneği
başlatır. Sunucuya JoinServer türünde bir mesaj gönderir.
Stop metot, istemciyi durdurur. Socket'i akışları kapatır ve bağlantıyı sonlandırır.
Display(String msg)metot, bir mesajı konsola yazdırır. Genellikle hata veya bağlantı durumuyla ilgili bilgileri göstermek için kullanılır.
Send(Message msg) metot, bir message nesnesini sunucuya gönderir. İlgili çıktı akışını kullanarak mesajı gönderir.
socket: Sunucuyla iletişimi sağlayan soket nesnesidir.
userName: İstemcinin kullanıcı adını tutan bir dizedir.
sInput: Sunucudan gelen verileri okumak için kullanılan bir ObjectInputStream nesnesidir.
sOutput: Sunucuya veri göndermek için kullanılan bir ObjectOutputStream nesnesidir.
listenMe: Sunucudan gelen mesajları dinlemek için kullanılan ServerListener sınıfından bir örnektir.
rooms: Sunucudaki odaların isimlerini tutan bir dizidr.
Bu kod, client tarafında sunucuya bağlanmayı sağlar, mesajları gönderir ve alır, bağlantıyı sonlandırır ve ilgili bilgileri 
konsolda gösterir. Bu sınıf, sunucuyla iletişim kurmayı sağlar ve mesajları gönderme ve alma işlevini uygulamakatadır.
*/


package chatClient;


import Message.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static Socket socket;
    public static String userName;
    public static ObjectInputStream sInput;
    public static ObjectOutputStream sOutput;
    public static ServerListener listenMe;
    public static ArrayList<String> rooms;

    public static void Start(String ip, int port, String userName) {
        try {
            Client.socket = new Socket(ip, port);
            Client.userName = userName;
            Client.Display("Servera bağlandı");
            Client.sInput = new ObjectInputStream(Client.socket.getInputStream());
            Client.sOutput = new ObjectOutputStream(Client.socket.getOutputStream());
            Client.listenMe = new ServerListener();
            Client.listenMe.start();

            Message msg = new Message(Message.Message_Type.JoinServer);
            msg.content = userName;
            Client.Send(msg);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Stop() {
        try {
            if (Client.socket != null) {
                Client.listenMe.stop();
                Client.socket.close();
                Client.sOutput.flush();
                Client.sOutput.close();
                Client.sInput.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Display(String msg) {
        System.out.println(msg);
    }

    public static void Send(Message msg) {
        try {
            Client.sOutput.flush();
            Client.sOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
