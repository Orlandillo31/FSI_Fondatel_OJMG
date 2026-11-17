import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.math.BigDecimal;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ORLAN
 */
public class JF_Gerente extends javax.swing.JFrame {

    /**
     * Creates new form JF_Gerente
     */
    public JF_Gerente() {
        initComponents();
        BAlimentos.addActionListener(evt -> new JF_Menu().setVisible(true));
        BRegistrarAlimento.addActionListener(evt -> registrarAlimento());
        cargarAlimentos();
    }

    private void cargarAlimentos() {
        String sql = "SELECT idalimento AS ID, nombre AS Alimento, "
                + "categoria AS Categoría, precio AS Precio, estado AS Estado "
                + "FROM alimentos ORDER BY nombre";
        Conexion.mostrarVista(sql, TRegistros);
        TRegistros.getColumnModel().getColumn(1).setPreferredWidth(270);
    }

    private void registrarAlimento() {
        JTextField nombre = new JTextField(24);
        JTextField descripcion = new JTextField(24);
        JTextField precio = new JTextField(12);
        JComboBox<String> categoria = new JComboBox<>(
                new String[]{"Entrada", "Plato Principal", "Postre", "Bebida"});
        JComboBox<String> estado = new JComboBox<>(new String[]{"Disponible", "Agotado"});
        JTextField foto = new JTextField(24);
        foto.setEditable(false);
        JButton elegir = new JButton("Elegir...");
        JPanel selector = new JPanel(new BorderLayout(5, 0));
        selector.add(foto, BorderLayout.CENTER);
        selector.add(elegir, BorderLayout.EAST);
        elegir.addActionListener(evt -> {
            JFileChooser archivos = new JFileChooser();
            archivos.setAcceptAllFileFilterUsed(false);
            archivos.setFileFilter(new FileNameExtensionFilter("Imágenes JPG y PNG", "jpg", "jpeg", "png"));
            if (archivos.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                foto.setText(archivos.getSelectedFile().getAbsolutePath());
                foto.setCaretPosition(0);
            }
        });

        JPanel campos = new JPanel(new GridLayout(0, 2, 10, 7));
        campos.add(new JLabel("Nombre:")); campos.add(nombre);
        campos.add(new JLabel("Descripción:")); campos.add(descripcion);
        campos.add(new JLabel("Precio:")); campos.add(precio);
        campos.add(new JLabel("Categoría:")); campos.add(categoria);
        campos.add(new JLabel("Estado:")); campos.add(estado);
        campos.add(new JLabel("Fotografía:")); campos.add(selector);

        Object[] botones = {"Registrar", "Cancelar"};
        while (JOptionPane.showOptionDialog(this, campos, "Registrar alimento",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, botones, botones[0]) == 0) {
            String n = nombre.getText().trim();
            String d = descripcion.getText().trim();
            if (n.isEmpty() || n.length() > 100 || d.isEmpty() || d.length() > 255 || foto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Completa nombre, descripción y fotografía.");
                continue;
            }
            try {
                BigDecimal p = new BigDecimal(precio.getText().trim().replace(',', '.'));
                if (p.signum() <= 0 || p.compareTo(new BigDecimal("999999.99")) > 0
                        || p.stripTrailingZeros().scale() > 2) throw new NumberFormatException();
                File imagen = new File(foto.getText());
                if (!imagen.isFile() || imagen.length() > 5 * 1024 * 1024 || ImageIO.read(imagen) == null) {
                    JOptionPane.showMessageDialog(this, "Elige una imagen válida de hasta 5 MB.");
                    continue;
                }
                Alimento alimento = new Alimento(null, n, d, p.toPlainString(), imagen.getAbsolutePath(),
                        (String) categoria.getSelectedItem(), (String) estado.getSelectedItem());
                Conexion cnx = new Conexion();
                if (cnx.conectar("localhost", "fondatel", "root", "") != 1) {
                    JOptionPane.showMessageDialog(this, "No se pudo conectar con Fondatel.");
                    return;
                }
                int filas;
                try {
                    filas = alimento.insertar(cnx.con);
                } finally {
                    cnx.desconectar();
                }
                if (filas == 1) {
                    cargarAlimentos();
                    JOptionPane.showMessageDialog(this, "Alimento registrado.");
                    return;
                }
                JOptionPane.showMessageDialog(this, "No se pudo registrar el alimento.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Escribe un precio positivo con hasta dos decimales.");
            } catch (java.io.IOException ex) {
                JOptionPane.showMessageDialog(this, "No se pudo leer la fotografía.");
            }
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

        PEncabezado = new javax.swing.JPanel();
        LMarca = new javax.swing.JLabel();
        LTitulo = new javax.swing.JLabel();
        LDetalle = new javax.swing.JLabel();
        LSeccion = new javax.swing.JLabel();
        BAlimentos = new javax.swing.JButton();
        BRegistrarAlimento = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TRegistros = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fondatel | Administración");

        PEncabezado.setBackground(new java.awt.Color(49, 75, 62));
        LMarca.setText("FONDATEL  /  RESTAURANTE");
        LMarca.setFont(new java.awt.Font("Dialog", 1, 12));
        LMarca.setForeground(new java.awt.Color(247, 202, 138));
        LTitulo.setText("Administración");
        LTitulo.setFont(new java.awt.Font("Dialog", 1, 29));
        LTitulo.setForeground(new java.awt.Color(255, 255, 255));
        LDetalle.setText("Gestiona el catálogo del restaurante.");
        LDetalle.setForeground(new java.awt.Color(255, 255, 255));
        LSeccion.setText("Catálogo de alimentos");
        LSeccion.setFont(new java.awt.Font("Dialog", 1, 18));
        LSeccion.setForeground(new java.awt.Color(36, 54, 45));
        BAlimentos.setText("Ver menú");
        BRegistrarAlimento.setText("Registrar alimento");
        TRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Alimento", "Categoría", "Precio", "Estado"}
        ) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        TRegistros.setFillsViewportHeight(true);
        TRegistros.setRowHeight(26);
        jScrollPane1.setViewportView(TRegistros);

        javax.swing.GroupLayout PEncabezadoLayout = new javax.swing.GroupLayout(PEncabezado);
        PEncabezado.setLayout(PEncabezadoLayout);
        PEncabezadoLayout.setHorizontalGroup(
            PEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PEncabezadoLayout.createSequentialGroup()
                    .addGap(24)
                    .addGroup(PEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(LMarca)
                        .addComponent(LTitulo)
                        .addComponent(LDetalle))
                    .addGap(24))
        );
        PEncabezadoLayout.setVerticalGroup(
            PEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PEncabezadoLayout.createSequentialGroup()
                    .addGap(15)
                    .addComponent(LMarca)
                    .addGap(5)
                    .addComponent(LTitulo)
                    .addGap(3)
                    .addComponent(LDetalle)
                    .addGap(15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PEncabezado, 0, -1, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addGap(24)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(LSeccion)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(BAlimentos)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BRegistrarAlimento))
                        .addComponent(jScrollPane1, 400, 714, Short.MAX_VALUE))
                    .addGap(24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(PEncabezado, -2, -1, -2)
                    .addGap(20)
                    .addComponent(LSeccion)
                    .addGap(10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BAlimentos)
                        .addComponent(BRegistrarAlimento))
                    .addGap(12)
                    .addComponent(jScrollPane1, 200, 290, Short.MAX_VALUE)
                    .addGap(22))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(JF_Gerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JF_Gerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JF_Gerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JF_Gerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JF_Gerente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PEncabezado;
    private javax.swing.JLabel LMarca;
    private javax.swing.JLabel LTitulo;
    private javax.swing.JLabel LDetalle;
    private javax.swing.JLabel LSeccion;
    private javax.swing.JButton BAlimentos;
    private javax.swing.JButton BRegistrarAlimento;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable TRegistros;
    // End of variables declaration//GEN-END:variables
}
