/*
RefreshRooms()metot, odaların listesini günceller. roomsList öğesinin modelini temizler ve yeni bir liste oluşturur.
createARoomButtonActionPerformedmetot, Create A Room butonuna basıldığında çalışır. Bir Message nesnesi oluşturur, içeriğine odanın
adını ekler ve sunucuya gönderir. SOnra yeni bir ChatFrame penceresi oluşturur ve gösterir.
RefreshRoomsButtonActionPerformed metot, Refresh Rooms butonuna basıldığında çalışır. Odaların adlarını almak için bir Message nesnesi 
oluşturur ve sunucuya gönderir.
JoinARoomButtonActionPerformed metot, Join A Room butonuna basıldığında çalışır. Bir Message nesnesi oluşturur, içeriğine seçilen odanın
adını ekler ve sunucuya gönderir. Ardından yeni bir ChatFrame penceresi oluşturur ve gösterir.
connectServerButtonActionPerformed metot, Connect To Server butonuna basıldığında çalışır. Client sınıfındaki Start metodu aracılığıyla 
sunucuya bağlanır. Bu sınıf, kullanıcının sunucuya bağlanmasını, odaya katılmasını veya oda oluşturmasını sağlayan bir arayüz oluşturur.
Kullanıcı arayüzünü oluşturur ve kullanıcının sunucuya bağlanmasını, odalara katılmasını veya odalar oluşturmasını sağlar.
*/


package chatClient;

import Message.Message;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public static Main main;
    DefaultListModel lm1;

    public Main() {
        initComponents();
        lm1 = new DefaultListModel();
        main = this;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userNameField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        roomsList = new javax.swing.JList<>();
        JoinARoomButton = new javax.swing.JButton();
        RefreshRoomsButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        createARoomButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        roomNameField = new javax.swing.JTextField();
        connectServerButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("UserName : ");

        jScrollPane1.setViewportView(roomsList);

        JoinARoomButton.setText("Join A Room");
        JoinARoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JoinARoomButtonActionPerformed(evt);
            }
        });

        RefreshRoomsButton.setText("Refresh Rooms");
        RefreshRoomsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshRoomsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(RefreshRoomsButton, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(JoinARoomButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RefreshRoomsButton, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JoinARoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Join A Room", jPanel2);

        createARoomButton.setText("Create A Room");
        createARoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createARoomButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Room Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roomNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(createARoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roomNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(createARoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Create A Room", jPanel1);

        connectServerButton.setText("Connect To Server");
        connectServerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectServerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(connectServerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(userNameField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(connectServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void RefreshRooms() {
        roomsList.setModel(lm1);
    }

    private void createARoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createARoomButtonActionPerformed
        Message msg = new Message(Message.Message_Type.CreateRoom);
        msg.content = roomNameField.getText();
        Client.Send(msg);
        ChatFrame chatFrame = new ChatFrame(roomNameField.getText());
        chatFrame.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_createARoomButtonActionPerformed

    private void RefreshRoomsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshRoomsButtonActionPerformed
        lm1.clear();
        Message msg = new Message(Message.Message_Type.ReturnRoomsNames);
        Client.Send(msg);
    }//GEN-LAST:event_RefreshRoomsButtonActionPerformed

    private void JoinARoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JoinARoomButtonActionPerformed
        Message msg = new Message(Message.Message_Type.JoinRoom);
        msg.content = roomsList.getSelectedValue();
        Client.Send(msg);

        ChatFrame chatFrame = new ChatFrame(roomsList.getSelectedValue());
        chatFrame.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_JoinARoomButtonActionPerformed

    private void connectServerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectServerButtonActionPerformed
        Client.Start("127.0.0.1", 2000, userNameField.getText());
    }//GEN-LAST:event_connectServerButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JoinARoomButton;
    private javax.swing.JButton RefreshRoomsButton;
    private javax.swing.JButton connectServerButton;
    private javax.swing.JButton createARoomButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField roomNameField;
    private javax.swing.JList<String> roomsList;
    private javax.swing.JTextField userNameField;
    // End of variables declaration//GEN-END:variables
}
