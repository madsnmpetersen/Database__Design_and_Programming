/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computer_store; 

import java.sql.SQLException;
import org.apache.commons.lang3.StringUtils;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ShameOnU
 */
public class GUI extends javax.swing.JFrame 
{
    SQLHandler handler;
    DecimalFormat df = new DecimalFormat("#.##");
    /**
     * Creates new form GUI
     */
    public GUI(SQLHandler handler) {
        this.handler = handler;
        initComponents();
        updateTables();
    }
    
    private void updateTables()
    {
        fillSysTable(jTable2,handler.getAllSystems());
        fillCompTable(jTable1,handler.getAllComponents());
        jTable1.moveColumn(5, 2);
        jTable2.moveColumn(6, 1);
        jTable2.moveColumn(7, 2);
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
        jTable1 = new javax.swing.JTable();
        fillTable(jTable1,handler.getAllComponents());
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        fillSysTable(jTable2,handler.getAllSystems());
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        button3 = new java.awt.Button();
        button4 = new java.awt.Button();
        button5 = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Computer store user interface");
        setBounds(new java.awt.Rectangle(250, 250, 750, 550));
        setMinimumSize(new java.awt.Dimension(750, 550));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jScrollPane1.setMaximumSize(jScrollPane1.getPreferredSize());
        jScrollPane1.setPreferredSize(jScrollPane2.getPreferredSize());

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(jTable1);

        jScrollPane2.setMaximumSize(jScrollPane2.getPreferredSize());

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jLabel1.setText("Components");

        jLabel2.setText("Systems");

        try
        {
            jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(handler.resultSetToArray(handler.getAllSystemNames())));
        }
        catch(Exception e)
        {
            System.out.print(e);
            jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Error"}));
        }
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField1.setText("Input number");
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.setAutoscrolls(false);

        jLabel4.setText("System");

        jLabel5.setText("Quantity");

        jLabel6.setText("Price");

        button3.setLabel("Sell indicated number of systems");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        button4.setLabel("Sell component");
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        button5.setLabel("Restock all");
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        handler.closeConnection();
    }//GEN-LAST:event_formWindowClosing

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        jTextField1.setText("");
    }//GEN-LAST:event_jTextField1FocusGained

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if(jTextField1.hasFocus())
        {
            if(StringUtils.isNumeric(jTextField1.getText()))
            {
                int qty = Integer.parseInt(jTextField1.getText());
                if(qty == 1)
                    jTextField2.setText(""+df.format(handler.getSystemSellPrice((String)jComboBox1.getSelectedItem())));
                else if((qty > 1) && (qty <= 11))
                    jTextField2.setText(""+df.format(handler.getSystemSellPrice((String)jComboBox1.getSelectedItem())* qty * (1-(0.02*(qty-1)))));
                else
                    jTextField2.setText(""+df.format(handler.getSystemSellPrice((String)jComboBox1.getSelectedItem())* qty * 0.8));
            }
            else
                jTextField2.setText("invalid number");
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
               
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        if(jTable1.isColumnSelected(0))
        {
            try
            {
                if(handler.buyComponents(((String)jTable1.getValueAt(jTable1.getSelectedRow(), jTable1.getSelectedColumn())), 1))
                    updateTables();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        else
        {
            new Error("Please select a component name in the table").setVisible(true);
        }
    }//GEN-LAST:event_button4ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        try
        {
            if(handler.buySystems((String)jComboBox1.getSelectedItem(), Integer.parseInt(jTextField1.getText())))
                updateTables();
            else
                new Error("No enough systems in stock").setVisible(true);
        }
        catch(NumberFormatException | SQLException e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_button3ActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        if(handler.restockAll())
            updateTables();
    }//GEN-LAST:event_button5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button3;
    private java.awt.Button button4;
    private java.awt.Button button5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
    /**
     * Takes a JTable and a ResultSet as parameters and populates the JTable with the supplied ResultSet
     * @param table
     * @param rs 
     */
    private void fillTable(javax.swing.JTable table, java.sql.ResultSet rs)
    {
        try
        {

            //To remove previously added rows
            while(table.getRowCount() > 0) 
            {
                ((javax.swing.table.DefaultTableModel) table.getModel()).removeRow(0);
            }
            int columns = rs.getMetaData().getColumnCount();
            Object[] ids = new Object[columns];
            while(rs.next())
            {  
                Object[] row = new Object[columns];
                for (int i = 1; i <= columns; i++)
                {  
                    row[i - 1] = rs.getObject(i);
                }
                ((javax.swing.table.DefaultTableModel) table.getModel()).insertRow(rs.getRow()-1,row);
            }
            for (int i = 1; i <= columns; i++)
                {  
                    ids[i-1] = rs.getMetaData().getColumnName(i);
                }
            ((javax.swing.table.DefaultTableModel) table.getModel()).setColumnIdentifiers(ids);
            rs.close();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }        
    }
    
    private void fillCompTable(javax.swing.JTable table, java.sql.ResultSet rs)
    {
        try
        {

            //To remove previously added rows
            while(table.getRowCount() > 0) 
            {
                ((javax.swing.table.DefaultTableModel) table.getModel()).removeRow(0);
            }
            int columns = rs.getMetaData().getColumnCount();
            int rows = 0;
            java.util.ArrayList<String> helper = new java.util.ArrayList();
            //Adding column titles
            Object[] ids = new Object[columns];
            for (int i = 1; i <= columns; i++)
                {  
                    ids[i-1] = rs.getMetaData().getColumnName(i);
                }
            ((javax.swing.table.DefaultTableModel) table.getModel()).setColumnIdentifiers(ids); 
            //Adding rows from ResultSet to table model.
            while(rs.next())
            {
                helper.add(rs.getString("name"));
                rows++;
                Object[] row = new Object[columns];
                for (int i = 1; i <= columns; i++)
                {  
                    row[i - 1] = rs.getObject(i);
                }
                ((javax.swing.table.DefaultTableModel) table.getModel()).insertRow(rs.getRow()-1,row);
            }     
            
            //Adding new column with restock numbers            
            Object[] restock = new Object[rows];
            for(int i = 0 ; i < restock.length ; i++)
            {
                restock[i] = handler.getCompRestock(helper.get(i));
            }
            ((javax.swing.table.DefaultTableModel) table.getModel()).addColumn("# to Restock", restock);
            //Adding selling prices
            Object[] sellPrice = new Object[rows];
            for(int i = 0 ; i < sellPrice.length ; i++)
            {
                sellPrice[i] = ((int)jTable1.getValueAt(i, 1))*1.3;
            }
            ((javax.swing.table.DefaultTableModel) table.getModel()).addColumn("Selling price", sellPrice);
            
            rs.close();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }        
    }
    
    private void fillSysTable(javax.swing.JTable table, java.sql.ResultSet rs)
    {
        try
        {

            //To remove previously added rows
            while(table.getRowCount() > 0) 
            {
                ((javax.swing.table.DefaultTableModel) table.getModel()).removeRow(0);
            }
            int columns = rs.getMetaData().getColumnCount();
            int rows = 0;
            //Adding column headers
            Object[] ids = new Object[columns];
            for (int i = 1; i <= columns; i++)
                {  
                    ids[i-1] = rs.getMetaData().getColumnName(i);
                }
            ((javax.swing.table.DefaultTableModel) table.getModel()).setColumnIdentifiers(ids);
            java.util.ArrayList<String> systems = new java.util.ArrayList();
            //Adding rows from ResultSet
            while(rs.next())
            {
                systems.add(rs.getString(1));
                Object[] row = new Object[columns];
                for (int i = 1; i <= columns; i++)
                {  
                    row[i - 1] = rs.getObject(i);                        
                }
                ((javax.swing.table.DefaultTableModel) table.getModel()).insertRow(rs.getRow()-1,row);
                rows++;                
            }
            //Adding new column with prices
            Object[] prices = new Object[rows];
            for(int i = 0 ; i < prices.length ; i++)
            {
                prices[i] = handler.getSystemPrice(systems.get(i));
            }
            ((javax.swing.table.DefaultTableModel) table.getModel()).addColumn("Price", prices);
            //Adding new column with selling prices
            Object[] sellPrices = new Object[rows];
            for(int i = 0 ; i < sellPrices.length ; i++)
            {
                sellPrices[i] = handler.getSystemSellPrice(systems.get(i));
            }
            ((javax.swing.table.DefaultTableModel) table.getModel()).addColumn("Selling price", sellPrices);
            //Adding new column with current stock
            Object[] inStock = new Object[rows];
            for(int i = 0 ; i < prices.length ; i++)
            {
                inStock[i] = handler.systemsInStock(systems.get(i));
            }
            ((javax.swing.table.DefaultTableModel) table.getModel()).addColumn("In stock", inStock);
            
            rs.close();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }        
    }
}
