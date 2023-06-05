/*
Server, Start(2000) çağrısı yapılarak, 2000 numaralı port üzerinden sunucu başlatılır. 
Bu, istemcilerin sunucuya bağlanabileceği bir iletişim oluşturur.
Bu kodun amacı, sunucu uygulamasını başlatmak ve belirli bir port üzerinden istemcilerin sunucuya bağlanmasını sağlamaktır.
*/


package ChatServer;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here        
        Server.Start(2000);
    }

}
