/*
by Jakub Wawak 2021
kubawawak@gmail.com
all rights reserved
 */
package user_interface;

/**
 *Window for presenting messages to user
 * @author jakubwawak
 */
public class message_window extends javax.swing.JDialog {

    /**
     * Creates new form message_window
     */
    public message_window(java.awt.Frame parent, boolean modal,String message,String title) {
        super(parent, modal);
        this.setTitle(title);
        initComponents();
        this.setLocationRelativeTo(null);
        field_message.setText(message);
        field_message.setEditable(false);
        setVisible(true);
    }
    
    public message_window(javax.swing.JDialog parent, boolean modal,String message,String title) {
        super(parent, modal);
        this.setTitle(title);
        initComponents();
        this.setLocationRelativeTo(null);
        field_message.setText(message);
        field_message.setEditable(false);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        field_message = new javax.swing.JTextArea();
        button_ok = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ENTRC_SHELF Message");

        field_message.setColumns(20);
        field_message.setRows(5);
        jScrollPane1.setViewportView(field_message);

        button_ok.setText("Ok");
        button_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_okActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(149, Short.MAX_VALUE)
                .addComponent(button_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_ok, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_okActionPerformed
        dispose();
    }//GEN-LAST:event_button_okActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_ok;
    private javax.swing.JTextArea field_message;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}