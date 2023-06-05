/*
Bu sınıf, sohbet sunucusunun ana işlevselliğini sağlamaktadır.
serverSocket: Sunucunun kullanacağı ServerSocket nesnesini temsil ediyor.
IdClient: Bağlanan istemcilere atanan benzersiz kimlik numarasını tutmaktdır.
port: Sunucunun dinlemekte olduğu port numarasını temsil etmektedir.
runThread: Yeni istemci bağlantılarını dinlemek için kullanılan NewClientListener sınıfından bir nesnedir.
Clients: Sunucuya bağlı olan istemcilerin bir listesini içerir. Her bir istemci, client sınıfından bir nesnedir.
rooms: Sunucudaki sohbet odalarının bir listesini içerir. Her bir oda, Room sınıfından bir nesnedir.
Bu sınıf, sunucunun başlatılması, istemci bağlantılarının kabul edilmesi, mesajların gönderilmesi ve sohbet odalarının yönetilmesi
gibi temel işlevleri sağlar. Bununla beraber istemci ve oda bilgilerini tutmak için clients ve rooms listelerini kullanır. 
Sunucu, istemcilerle iletişim kurmak ve mesajları iletmek için client sınıfını kullanır.
*/


package ChatServer;

import Message.Message;
import Message.Message.Message_Type;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static ServerSocket serverSocket;
    public static int IdClient = 0;
    public static int port = 0;
    public static NewClientListener runThread;
    public static ArrayList<Client> Clients = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();

    public static void Start(int openport) {
        try {
            Server.port = openport;
            Server.serverSocket = new ServerSocket(Server.port);

            Server.runThread = new NewClientListener();
            Server.runThread.start();

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Send(Client cl, Message msg) {

        try {
            cl.sOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<String> ReturnRooms() {
        ArrayList<String> roomNames = new ArrayList<>();
        for (Room room : rooms) {
            roomNames.add(room.roomName);
        }
        return roomNames;
    }

    public static Room ReturnRoom(String roomName) {
        for (Room room : rooms) {
            if (roomName.equals(room.roomName)) {
                return room;
            }
        }
        return null;
    }

    public static void SendRoomInfo(String roomName, String message) {
        Room room = ReturnRoom(roomName);
        Message msg = new Message(Message_Type.JoinRoom);
        msg.content = message;
        for (Client client : room.Clients) {
            client.Send(msg);
        }
    }

    public static void BroadCastMessage(String roomName, String message, String senderClientName) {
        Room room = ReturnRoom(roomName);
        ArrayList<String> msgContent = new ArrayList<>();
        msgContent.add(senderClientName);
        msgContent.add(message);
        Message msg = new Message(Message_Type.RoomMessage);
        msg.content = msgContent;
        for (Client client : room.Clients) {
            if (senderClientName != client.name) {
                client.Send(msg);
            }
        }
    }

    public static Client JoinRoom(String roomName) {

        for (Client c : Clients) {
            if (c.roomName.equals(roomName)) {
                return c;
            }
        }
        return null;
    }
}
