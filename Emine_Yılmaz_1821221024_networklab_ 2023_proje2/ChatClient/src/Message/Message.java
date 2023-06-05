/*
Message sınıfı, Message_Type a tanımlar. BU da  mesaj türlerini temsil ediyorr. 
JoinServer: Sunucuya katılmak için kullanılan bir mesaj.
CreateRoom: Oda oluşturmak için kullanılan bir mesaj.
ReturnRoomsNames: Mevcut odaların isimlerini döndüren bir mesaj.
JoinRoom: Bir odaya katılmak için kullanılan bir mesaj.
RoomMessage: Oda içindeki mesajları temsil eden bir mesaj.
Message sınıfı ayrıca type ve content adında iki özellik içeriyor. type, mesajın türünü temsil eden Message_Type içerir. 
content, mesajın içeriğini temsil eden bir nesnedir.
Bu kod, mesajların taşınması ve işlenmesi için kullanılabilecek genel bir yapı sağlıyor.
Mesaj türüne bağlı olarak, content özelliği farklı türlerde veriler içerebilir. 
JoinServer mesajı bir kullanıcı adı içerir, CreateRoom mesajı yeni oda için gerekli bilgileri içermektedir.
*/


package Message;


public class Message implements java.io.Serializable {

    public static enum Message_Type {
        JoinServer, CreateRoom, ReturnRoomsNames, JoinRoom, RoomMessage
    }

    public Message_Type type;
    public Object content;

    public Message(Message_Type t) {
        this.type = t;
    }

}
