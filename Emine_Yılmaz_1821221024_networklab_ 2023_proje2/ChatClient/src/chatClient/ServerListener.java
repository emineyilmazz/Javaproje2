/*
run() metot, ServerListener sınıfının ana çalışma metodu olarak Thread sınıfından miras alınır ve sunucudan gelen mesajları dinler.
Sürekli olarak sunucudan gelen mesajları okur ve mesaj türüne göre işlem yapar.
ReturnRoomsNames mesajı alındığında, gelen içerikteki odaların listesini alır ve client sınıfındaki rooms listesini günceller. 
Sonra, Main sınıfındaki lm1 nesnesine her odanın adını ekler ve Main sınıfında yer alan RefreshRooms metodu ile odaların listesinigünceller.
JoinRoom mesajı alındığında, gelen içerikteki odaya katılır. ChatFrame sınıfındaki JoinRoom() metodu kullanılarak ilgili odaya katılır.
RoomMessage mesajı alındığında, gelen içerikteki mesajı ChatFrame sınıfındaki ReceiveMessage() metodu ile alır ve işler.
Sunucuyla iletişimde bir hata oluşursa veya sunucuyla bağlantı kesilirse ilgili istisnaları işler ve döngüden çıkar.
Bu sınıf, istemci tarafında sunucudan gelen mesajları dinlemek ve işlemek için kullanılır. ServerListener sınıfı, bir Thread alt sınıfıdır
ve dinleme işlemini bağlantı aktif olduğu sürece devam ettirir. Sunucudan gelen mesajları dinleyen bir thread i temsil eder. 
Sunucudan gelen mesajları işler ve Client, Main ve ChatFrame sınıflarında uygun işlemleri yapar.
*/


package chatClient;

import Message.Message;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static chatClient.Client.sInput;

public class ServerListener extends Thread {

    @Override
    public void run() {
        while (Client.socket.isConnected()) {
            try {
                Message received = (Message) (sInput.readObject());

                switch (received.type) {

                    case ReturnRoomsNames:
                        Client.rooms = (ArrayList<String>) received.content;
                        Client.rooms.forEach((r) -> {
                            Main.main.lm1.addElement(r);
                        });
                        Main.main.RefreshRooms();
                        System.out.println("Reached rooms list from server...");

                        break;
                    case JoinRoom:
                        Thread.sleep(500);
                        ChatFrame.chatFrame.JoinRoom((String) (received.content));
                        System.out.println(received.content.toString() + " joined to room..");
                        break;
                    case RoomMessage:
                        ChatFrame.chatFrame.ReceiveMessage((ArrayList<String>) received.content);
                        break;
                }

            } catch (IOException | ClassNotFoundException ex) {

                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                break;
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
