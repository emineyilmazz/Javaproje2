/*
NewClientListener sınıfı, Thread sınıfından türetilmiştir ve run metodu içerisinde çalışacak iş mantığı yer alır. Bu sınıf, sunucunun 
belirli bir bağlantı noktasını dinleyerek yeni istemci bağlantılarını kabul etmektedir.
run metodunda, sunucu soketi  kapatılmadığı sürece bir döngü içinde çalışılır. Server.serverSocket.accept() yöntemi ile yeni bir istemci 
bağlantısı kabul edilir ve bir soket nesnesi (clientSocket) oluşturulur. Daha sonra bu soket üzerinden yeni bir client örneği oluşturulur
ve bu örnek Server.cients listesine eklenir.
Bu işlem, sunucunun sürekli olarak yeni istemci bağlantılarını dinlemesini sağlar. Her bir yeni istemci bağlantısı için ayrı bir client 
örneği oluşturulur ve ilgili dinleme işlemleri başlatılır.
Yeni istemci bağlantılarını dinlemek ve her bir yeni istemci bağlantısı için bir client örneği oluşturmak için kullanılan 
bir thread'i temsil ediyor. Sunucunun yeni istemci bağlantılarını dinlemesi ve yönetmesi için kullanılıyor bu sınıf.
*/


package ChatServer;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewClientListener extends Thread {

    @Override
    public void run() {
        while (!Server.serverSocket.isClosed()) {
            try {
                System.out.println("Client Bekleniyor...");
                
                Socket clientSocket = Server.serverSocket.accept();
                
                System.out.println("Client Geldi...");
                
                Client nclient = new Client(clientSocket, Server.IdClient);

                Server.IdClient++;
                Server.Clients.add(nclient);
                
                nclient.listenThread.start();

            } catch (IOException ex) {
                Logger.getLogger(NewClientListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
