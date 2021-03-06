/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vrany.chatbot;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * Panel pro obecna nastaveni pripojeni chatbotu
 *
 * @author michal
 */
public class General extends javax.swing.JPanel {

    public static final int STATUS_CONNECTED_ALIVE = 0;
    public static final int STATUS_CONNECTED_NALIVE = 1;
    public static final int STATUS_NOT_CONNECTED = 2;

    Colors colors;
    ColorCellRenderer renderer;

    /**
     * Creates new form General
     */
    public General() {
        colors = new Colors();
        renderer = new ColorCellRenderer();
        initComponents();
        TableColumnModel tcm = colorsTable.getColumnModel();
        TableColumn tc = tcm.getColumn(Colors.COLOR);
        tc.setCellRenderer(renderer);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        userName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        channel = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        oauth = new javax.swing.JPasswordField();
        connect = new javax.swing.JButton();
        disconnect = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        notAlive = new javax.swing.JLabel();
        disconnected = new javax.swing.JLabel();
        connected = new javax.swing.JLabel();
        aa = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        colorsTable = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        jLabel7.setText("UserName / Nick");

        jLabel8.setText("Channel name (case sensitive)");

        jLabel9.setText("Oauth - generate for you on (i.e.) http://twitchapps.com/tmi/");

        connect.setText("Connect chat");

        disconnect.setText("Disconnect");

        notAlive.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        notAlive.setText("Not alive");
        jPanel1.add(notAlive);

        disconnected.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        disconnected.setText("Disconnected");
        jPanel1.add(disconnected);

        connected.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        connected.setText("Connected");
        jPanel1.add(connected);

        aa.setText("Anti aliasing?");
        aa.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                aaStateChanged(evt);
            }
        });

        jLabel1.setText("UserColors");

        colorsTable.setModel(colors);
        jScrollPane1.setViewportView(colorsTable);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cz/vrany/chatbot/New.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cz/vrany/chatbot/UpArrow.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cz/vrany/chatbot/DnArrow.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cz/vrany/chatbot/Delete.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(106, 106, 106)
                .addComponent(jLabel8))
            .addGroup(layout.createSequentialGroup()
                .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(channel, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel9)
            .addComponent(oauth, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(connect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(disconnect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(aa)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(channel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(oauth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(connect)
                        .addComponent(disconnect))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)))
                .addContainerGap(97, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void aaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_aaStateChanged
        ChatSettings.AA = aa.isSelected();
    }//GEN-LAST:event_aaStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Color color;
        if ((color = JColorChooser.showDialog(this, "Choose color directly", Color.black)) != null) {
            colors.addColor(color);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int row = colorsTable.getSelectedRow();
        if (row >= 0) {
            if (colors.moveUp(row)) {
                colorsTable.changeSelection(row - 1, 0, false, false);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int row = colorsTable.getSelectedRow();
        if (row >= 0) {
            if (colors.moveDn(row)) {
                colorsTable.changeSelection(row + 1, 0, false, false);
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int row = colorsTable.getSelectedRow();
        if (row >= 0) {
            colors.removeIcon(row);
            colorsTable.changeSelection(row, 0, false, false);
        }
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox aa;
    private javax.swing.JTextField channel;
    private javax.swing.JTable colorsTable;
    private javax.swing.JButton connect;
    private javax.swing.JLabel connected;
    private javax.swing.JButton disconnect;
    private javax.swing.JLabel disconnected;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel notAlive;
    private javax.swing.JPasswordField oauth;
    private javax.swing.JTextField userName;
    // End of variables declaration//GEN-END:variables

    public void addConnectButtonActionListener(ActionListener l) {
        connect.addActionListener(l);
    }

    public void addDisconnectButtonActionListener(ActionListener l) {
        disconnect.addActionListener(l);
    }

    void loadSettings(IniFile settings) {
        String inp;
        inp = settings.getKeyValue("Connection", "nick", "Insert Nick Name");
        userName.setText(inp);
        inp = settings.getKeyValue("Connection", "oauth", "Insert oauth from http://twitchapps.com/tmi/");
        oauth.setText(inp);
        inp = settings.getKeyValue("Connection", "channel", "Insert channel name, case sensitive!");
        channel.setText(inp);
        inp = settings.getKeyValue("General", "antiAliasing", "true");
        aa.setSelected(Boolean.parseBoolean(inp));
        ChatSettings.AA = aa.isSelected();
        colors.loadColors();
    }

    void saveSettings(IniFile settings) {
        settings.setValue("Connection", "nick", userName.getText());
        char[] pwd = oauth.getPassword();
        settings.setValue("Connection", "oauth", String.valueOf(pwd));
        for (int i = 0; i < pwd.length; i++) {
            pwd[i] = 0;
        }
        settings.setValue("Connection", "channel", channel.getText());
        settings.setValue("General", "antiAliasing", String.valueOf(aa.isSelected()));
        colors.saveColors();
    }

    TwitchChat newTwitchChat() {
        char[] pwd = oauth.getPassword();
        return new TwitchChat(userName.getText(), String.valueOf(pwd), channel.getText());
    }

    public void setConnectionStatus(int status) {
        switch (status) {
            case STATUS_CONNECTED_ALIVE:
                disconnected.setVisible(false);
                notAlive.setVisible(false);
                connected.setVisible(!connected.isVisible());
                break;
            case STATUS_CONNECTED_NALIVE:
                disconnected.setVisible(false);
                notAlive.setVisible(true);
                connected.setVisible(false);
                break;
            case STATUS_NOT_CONNECTED:
                disconnected.setVisible(true);
                notAlive.setVisible(false);
                connected.setVisible(false);
                break;
        }
    }

    Color getNewColor() {
        return colors.getNewColor();
    }

    String getChannelName() {
        return channel.getText();
    }

    String getOauth() {
        char[] pwd = oauth.getPassword();
        return String.valueOf(pwd);
    }

    String getUsername() {
        return userName.getText();
    }
}
