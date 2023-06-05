/*
TheClient: Dinleme işlemini gerçekleştiren istemciyi temsil ediyor.
run metodunun içinde, istemcinin soketi bağlı olduğu sürece bir döngü çalışır. Döngü içinde, istemciden gelen mesajlar okunur 
ve bu mesajların türüne göre işlemler yapılır. İşlem türüne göre farklı durumlar ele alınır ve uygun işlemler gerçekleştirilir.
JoinServer: İstemcinin sunucuya katılma işlemi gerçekleştirdiği durum. İstemcinin adı alınır ve sunucu tarafında kaydedilir.
CreateRoom: İstemcinin bir sohbet odası oluşturma işlemi gerçekleştirdiği durum. Oluşturulan odanın bilgileri kaydedilir ve istemci odaya
eklenir.
ReturnRoomsNames: İstemcinin mevcut sohbet odalarının isimlerini almak için istekte bulunduğu durumdur. Sunucu, mevcut odaların isimlerini
içeren bir mesaj oluşturur ve istemciye gönderir.
JoinRoom: İstemcinin bir sohbet odasına katılma işlemi gerçekleştirdiği durumdur. İstemci, belirtilen odanın bilgilerini alır ve odaya 
eklenir.
RoomMessage: Bir sohbet odasında bir mesajın gönderildiği durumdur. Sunucu, mesajı oda üyelerine iletmek için gerekli işlemleri
gerçekleştirir.
Bu sınıf, sunucu tarafında her bir istemcinin gelen mesajları dinlemek ve bu mesajlara yanıt vermek için kullanılıyor.
Snucu tarafında her bir istemcinin gelen mesajları dinlemek ve bu mesajlara uygun şekilde yanıt vermek için kullanılan ClientListener 
sınıfını tanımlar.
*/ 


package ChatServer;

import Message.Message;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientListener extends Thread {

    Client TheClient;

    ClientListener(Client TheClient) {
        this.TheClient = TheClient;
    }

    @Override
    public void run() {
        while (TheClient.soket.isConnected()) {
            try {
                try {
                    Message msg = (Message) TheClient.sInput.readObject();

                    switch (msg.type) {
                        case JoinServer:
                            TheClient.name = msg.content.toString();
                            System.out.println("User " + TheClient.name + " has joined the server...");
                            break;
                        case CreateRoom:
                            TheClient.roomName = (String) msg.content;
                            Room room = new Room(TheClient.roomName);
                            room.Clients.add(TheClient);
                            Server.rooms.add(room);
                            System.out.println(TheClient.name + " has create a room named : " + TheClient.roomName);
                            break;
                        case ReturnRoomsNames:
                            Message newMsg = new Message(Message.Message_Type.ReturnRoomsNames);
                            newMsg.content = Server.ReturnRooms();
                            Server.Send(TheClient, newMsg);
                            System.out.println("User " + TheClient.name + " refreshing rooms...");
                            break;
                        case JoinRoom:
                            Room jRoom = Server.ReturnRoom(msg.content.toString());
                            TheClient.roomName = (String) msg.content;
                            jRoom.Clients.add(TheClient);

                            Server.SendRoomInfo(msg.content.toString(), TheClient.name);
                            break;
                        case RoomMessage:
                            Server.BroadCastMessage(TheClient.roomName, msg.content.toString(), TheClient.name);
                            break;
                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                Server.Clients.remove(TheClient);

            }
        }
    }
}
