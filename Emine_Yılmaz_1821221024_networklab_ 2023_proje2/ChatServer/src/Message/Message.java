/*
Message_Type adında bir iç içe enum  sınıfı tanımlanmıştır. Bu enum, mesaj türlerini temsil eder. 
JoinServer, CreateRoom, ReturnRoomsNames, JoinRoom ve RoomMessage olmak üzere beş farklı mesaj türü bulunur.
type adında bir Message_Type değişkeni tanımlanmıştır. Bu değişken, mesajın türünü belirtir.
content adında bir Object değişkeni tanımlanmıştır. Bu değişken, mesajın içeriğini temsil eder. İçeriğin türüne bağlı olarak farklı 
veri tiplerini içerebilir. Sınıf, bir Message_Type nesnesini parametre olarak alır ve type özelliğini bu parametreyle ayarlar.
Bu sınıf, client ve server arasında iletişim için kullanılan mesajları temsil eder. Mesajların türlerini ve içeriklerini temsil etmek
için kullanılan bir sınıfı içerir. Mesajlar, bu sınıfı kullanarak client ve server arasında iletilir.
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
