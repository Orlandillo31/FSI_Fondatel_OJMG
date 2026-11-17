
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class JF_Menu extends javax.swing.JFrame {

    Conexion cnx = new Conexion();
    
    private final String todo = "SELECT nombre, descripcion, precio, foto, categoria FROM menu";
    private final String categorias = "SELECT DISTINCT categoria FROM menu ORDER BY categoria";

    public JF_Menu() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("MENÚ DE FONDATEL");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        TMenu.setRowHeight(100);
        CBCategoria.setMaximumSize(new Dimension(180, 64));

        if (cnx.conectar("localhost", "fondatel", "root", "") == 1) {
            cnx.seleccionar(categorias, CBCategoria);
            cargarMenu(null);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent evt) {
                    cnx.desconectar();
                }
            });
        } else {
            jButton1.setEnabled(false);
            CBCategoria.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Sin conexión. Revisa que MySQL esté encendido.");
        }
    }

    private void cargarMenu(String categoria) {
        String sql = todo + (categoria == null ? "" : " WHERE categoria = ?")
                + " ORDER BY categoria, nombre";
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"Nombre", "Descripción", "Precio", "Foto", "Categoría"}, 0) {
            @Override
            public Class<?> getColumnClass(int columna) {
                return columna == 3 ? Icon.class : Object.class;
            }

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        try (PreparedStatement consulta = cnx.con.prepareStatement(sql)) {
            if (categoria != null) {
                consulta.setString(1, categoria);
            }
            try (ResultSet rs = consulta.executeQuery()) {
                while (rs.next()) {
                    modelo.addRow(new Object[]{rs.getString("nombre"),
                        rs.getString("descripcion"), rs.getBigDecimal("precio"),
                        mostrarFoto(rs.getBytes("foto")), rs.getString("categoria")});
                }
            }
            TMenu.setModel(modelo);
            TMenu.getColumnModel().getColumn(1).setPreferredWidth(290);
            TMenu.getColumnModel().getColumn(3).setMinWidth(96);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar el menú: " + ex.getMessage());
        }
    }

    private ImageIcon mostrarFoto(byte[] foto) {
        if (foto == null || foto.length == 0) {
            return null;
        }
        ImageIcon original = new ImageIcon(foto);
        if (original.getIconWidth() <= 0 || original.getIconHeight() <= 0) {
            return null;
        }
        double escala = Math.min(88.0 / original.getIconWidth(), 88.0 / original.getIconHeight());
        int ancho = Math.max(1, (int) (original.getIconWidth() * escala));
        int alto = Math.max(1, (int) (original.getIconHeight() * escala));
        return new ImageIcon(original.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        CBCategoria = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        TMenu = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setBackground(new java.awt.Color(204, 204, 255));
        jToolBar1.setRollover(true);

        jButton1.setText("COMPLETO");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        CBCategoria.setBackground(new java.awt.Color(204, 204, 255));
        CBCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBCategoria.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CATEGORIA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        CBCategoria.setMaximumSize(new java.awt.Dimension(100, 64));
        CBCategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBCategoriaItemStateChanged(evt);
            }
        });
        jToolBar1.add(CBCategoria);

        TMenu.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CBCategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBCategoriaItemStateChanged
        if (evt.getStateChange() != ItemEvent.SELECTED || cnx.con == null) {
            return;
        }
        String categoria = (String) evt.getItem();
        cargarMenu("Todos".equals(categoria) ? null : categoria);
    }//GEN-LAST:event_CBCategoriaItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if ("Todos".equals(CBCategoria.getSelectedItem())) {
            cargarMenu(null);
        } else {
            CBCategoria.setSelectedItem("Todos");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(JF_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JF_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JF_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JF_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JF_Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBCategoria;
    private javax.swing.JTable TMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
