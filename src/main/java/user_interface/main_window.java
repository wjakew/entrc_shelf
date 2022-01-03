/*
by Jakub Wawak 2021
kubawawak@gmail.com
all rights reserved
 */
package user_interface;

import configuration.Configuration;
import connector.Connector;
import connector.Parser;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *Object for creating 
 * @author jakubwawak
 */
public class main_window extends javax.swing.JFrame {

    /**
     * Creates new form main_window
     */
    Connector connector;
    Configuration configuration;
    
    int entrc_ic_item_id;
    
    private String exit_code,reload_code;
    
    
    public main_window(Connector connector,Configuration configuration) {
        initComponents();
        this.connector = connector;
        this.configuration = configuration;
        entrc_ic_item_id = -1;
        this.setLocationRelativeTo(null);
        load_window();
        setVisible(true);
    }
    
    /**
     * Function for loading table content
     * @param data 
     */
    void load_table(ArrayList<String> data){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ITEM ID");
        tableModel.addColumn("ITEM NAME");
        int counter = 0;
        for(String item : data){
            String[] raw_item = item.split("\n");
            tableModel.insertRow(counter, new Object[]{raw_item[0],raw_item[1]});
            counter++;
        }
        table_content.setModel(tableModel);
        table_content.setEnabled(false);
    }
    
    /**
     * Function for loading shelf content
     */
    void load_shelf_content(){
        try{
            Parser parser = new Parser(connector.load_shelf_content(configuration.entrc_ic_drawer_code));
            if ( !parser.get_boolean("exists") ){
                new message_window(this,true,"Drawer code is not correct. Exiting.","DRAWER CODE ERROR");
                System.exit(0);
            }
            if ( !parser.get_boolean("drawer_empty") && !parser.get_boolean("error") ){
                ArrayList<String> data = parser.get_arraylist("drawer_elements_glances");
                load_table(data);
            }
            else if (parser.get_boolean("error")){
                new message_window(this,true,"Error loading shelf content. API ERROR","API ERROR");
                System.exit(1);
            }
            else if (parser.get_boolean("drawer_empty")){
                new message_window(this,true,"Drawer is empty","DRAWER CONTENT");
            }
        }catch(NullPointerException e){
            new message_window(this,true,"Error loading shelf content","");
            System.exit(0);
        }
    }
    
    /**
     * Function for loading window
     */
    void load_window(){
        this.setTitle("SHELF - "+configuration.entrc_ic_drawer_code);
        field_pin.setText("");
        load_shelf_content();
        button_7.setText("7");
        button_9.setText("9");
        button_7.setForeground(Color.white);
        button_9.setForeground(Color.white);
        
        button_1.setEnabled(true);
        button_2.setEnabled(true);
        button_3.setEnabled(true);
        button_4.setEnabled(true);
        button_5.setEnabled(true);
        button_6.setEnabled(true);
        button_7.setEnabled(true);
        button_8.setEnabled(true);
        button_9.setEnabled(true);
        field_pin.setEnabled(true);
//        connector.worker_id = -1;
    }
    
    /**
     * Function for loading window authorized
     */
    void load_window_auth(){
        button_7.setText("<");
        button_9.setText(">");
        button_7.setForeground(Color.red);
        button_9.setForeground(Color.green);
        
        button_1.setEnabled(false);
        button_2.setEnabled(false);
        button_3.setEnabled(false);
        button_4.setEnabled(false);
        button_5.setEnabled(false);
        button_6.setEnabled(false);
        button_7.setEnabled(true);
        button_8.setEnabled(false);
        button_9.setEnabled(true);
        field_pin.setEnabled(false);
        table_content.setEnabled(true);
    }
    
    /**
     * Function for adding data to 
     * @param object 
     */
    void add_data(JButton object){
        String text = field_pin.getText();
        text = text+object.getText();
        field_pin.setText(text);
        // function for ending connection
        if ( text.length() == 6){
            function_keys_handler(text);
        }
        else if ( text.length() == 4){
            pin_enter_action(text);
        }
    }
    
    /**
     * Function for handling keys
     */
    void function_keys_handler(String command){
        if ( command.equals(exit_code)){
            new message_window(this,true,"System is exiting...","EXIT CODE");
            System.exit(0);
        }else if ( command.equals("reload_code")){
            load_window();
        }
        else{
            field_pin.setText("");
        }
    }

    /**
     * Function for entering pin
     * @param pin 
     */
    void pin_enter_action(String pin){
        try{
            Parser parser = new Parser(connector.authorize_user(configuration.entrc_ic_drawer_code,pin,configuration.apptoken));
            switch(parser.get_string("authorization")){
                case "no_authorization":
                    new message_window(this,true,"Użytkownik nie posiada uprawnień do szafki","");
                    break;
                case "account_blocked":
                    new message_window(this,true,"Twoje konto jest zablokowane","");
                    break;
                case "no_auth":
                    new message_window(this,true,"Błędny kod PIN","");
                    break;
                default:
                    load_window_auth();
                    connector.worker_id = parser.get_int("worker_id");
                    break;
            }
            field_pin.setText("");
        }catch(NumberFormatException e){
            new message_window(this,true,"Błędny pin.","BŁĄD PIN");
        }catch(Exception e){
            new message_window(this,true,"Error:/n"+e.toString(),"APP ERROR");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        field_pin = new javax.swing.JTextField();
        button_1 = new javax.swing.JButton();
        button_3 = new javax.swing.JButton();
        button_2 = new javax.swing.JButton();
        button_6 = new javax.swing.JButton();
        button_5 = new javax.swing.JButton();
        button_4 = new javax.swing.JButton();
        button_7 = new javax.swing.JButton();
        button_8 = new javax.swing.JButton();
        button_9 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_content = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SHELF");

        field_pin.setFont(new java.awt.Font("sansserif", 0, 48)); // NOI18N
        field_pin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        field_pin.setText("XXXX");
        field_pin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                field_pinMouseClicked(evt);
            }
        });

        button_1.setFont(new java.awt.Font("sansserif", 0, 80)); // NOI18N
        button_1.setForeground(new java.awt.Color(255, 255, 255));
        button_1.setText("1");
        button_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_1ActionPerformed(evt);
            }
        });

        button_3.setFont(new java.awt.Font("sansserif", 0, 80)); // NOI18N
        button_3.setForeground(new java.awt.Color(255, 255, 255));
        button_3.setText("3");
        button_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_3ActionPerformed(evt);
            }
        });

        button_2.setFont(new java.awt.Font("sansserif", 0, 80)); // NOI18N
        button_2.setForeground(new java.awt.Color(255, 255, 255));
        button_2.setText("2");
        button_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_2ActionPerformed(evt);
            }
        });

        button_6.setFont(new java.awt.Font("sansserif", 0, 80)); // NOI18N
        button_6.setForeground(new java.awt.Color(255, 255, 255));
        button_6.setText("6");
        button_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_6ActionPerformed(evt);
            }
        });

        button_5.setFont(new java.awt.Font("sansserif", 0, 80)); // NOI18N
        button_5.setForeground(new java.awt.Color(255, 255, 255));
        button_5.setText("5");
        button_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_5ActionPerformed(evt);
            }
        });

        button_4.setFont(new java.awt.Font("sansserif", 0, 80)); // NOI18N
        button_4.setForeground(new java.awt.Color(255, 255, 255));
        button_4.setText("4");
        button_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_4ActionPerformed(evt);
            }
        });

        button_7.setFont(new java.awt.Font("sansserif", 0, 80)); // NOI18N
        button_7.setForeground(new java.awt.Color(255, 255, 255));
        button_7.setText("7");
        button_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_7ActionPerformed(evt);
            }
        });

        button_8.setFont(new java.awt.Font("sansserif", 0, 80)); // NOI18N
        button_8.setForeground(new java.awt.Color(255, 255, 255));
        button_8.setText("8");
        button_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_8ActionPerformed(evt);
            }
        });

        button_9.setFont(new java.awt.Font("sansserif", 0, 80)); // NOI18N
        button_9.setForeground(new java.awt.Color(255, 255, 255));
        button_9.setText("9");
        button_9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_9ActionPerformed(evt);
            }
        });

        table_content.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table_content);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(field_pin, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(button_1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button_2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(button_3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button_7, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_8, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_9, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button_4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_6, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(field_pin, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button_1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button_4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button_7, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_9, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_8, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_1ActionPerformed
        add_data(button_1);
    }//GEN-LAST:event_button_1ActionPerformed

    private void button_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_2ActionPerformed
        add_data(button_2);
    }//GEN-LAST:event_button_2ActionPerformed

    private void button_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_3ActionPerformed
        add_data(button_3);
    }//GEN-LAST:event_button_3ActionPerformed

    private void button_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_4ActionPerformed
        add_data(button_4);
    }//GEN-LAST:event_button_4ActionPerformed

    private void button_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_5ActionPerformed
        add_data(button_5);
    }//GEN-LAST:event_button_5ActionPerformed

    private void button_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_6ActionPerformed
        add_data(button_6);
    }//GEN-LAST:event_button_6ActionPerformed

    private void button_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_7ActionPerformed
        if ( button_7.getText().equals("7"))
            add_data(button_7);
        else{
            load_window();
        }
    }//GEN-LAST:event_button_7ActionPerformed

    private void button_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_8ActionPerformed
        add_data(button_8);
    }//GEN-LAST:event_button_8ActionPerformed

    private void button_9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_9ActionPerformed
        if ( button_9.getText().equals("9"))    
            add_data(button_9);
        else{
            // button in mode ">"
            int row = table_content.getSelectedRow();
            int column = 0;
            try{
                int entrc_ic_item_id = Integer.parseInt(table_content.getModel().getValueAt(row, column).toString().split(":")[1]);
                System.out.println("Selected entrc_ic_item_id="+entrc_ic_item_id);
                new item_manipulation_window(this,true,connector,entrc_ic_item_id);
                load_window();
            }catch(Exception e){
                new message_window(this,true,"Failed to load item data","");
            }
        }
    }//GEN-LAST:event_button_9ActionPerformed

    private void field_pinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field_pinMouseClicked
        field_pin.setText("");
    }//GEN-LAST:event_field_pinMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_1;
    private javax.swing.JButton button_2;
    private javax.swing.JButton button_3;
    private javax.swing.JButton button_4;
    private javax.swing.JButton button_5;
    private javax.swing.JButton button_6;
    private javax.swing.JButton button_7;
    private javax.swing.JButton button_8;
    private javax.swing.JButton button_9;
    private javax.swing.JTextField field_pin;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_content;
    // End of variables declaration//GEN-END:variables
}
