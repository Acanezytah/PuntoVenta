/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package itson.puntoventa;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Akane
 */
public class PuntoVentaFrame extends javax.swing.JFrame {
    

    //---- Lista en donde se guardaran los productos que agregue el usuario.    
     ArrayList<Producto> listaProductos = new ArrayList<>();
     
    //---- Crea la variable en donde se asignará el modelo de la tabla. 
     DefaultTableModel modelo;  
     
    //--------- RF3 – Calcular total ------------
    /**
     * Metodo calcularSubtotal
     * 
     * Como el nombre indica calcula el subtotal de cada producto, pero este es diferente 
     * al calculo automatico dentro de la entidad de producto, este es exclusivo para
     * calcular el subtotal que cambia automaticamente en subtotalField.
     */ 
    private void calcularSubtotal() {

        try {

            //---- Si el precio o la cantidad están vacios, el subtotalField se deja en blanco
            if (precioField.getText().isEmpty() || cantidadField.getText().isEmpty()) {
                subtotalField.setText("");
                return;
            }

            //Obtiene el precio y la cantidad, con eso hace el calculo. Lo deja con un maximo de 2 decimales.
            double precio = Double.parseDouble(precioField.getText());
            int cantidad = Integer.parseInt(cantidadField.getText());

            double subtotal = precio * cantidad;

            subtotalField.setText(String.format("%.2f", subtotal));

        //Este catch sirve en caso de que el usuario ingrese un valor que no sea un número.
        } catch (NumberFormatException e) {
            subtotalField.setText("");
        }
    }
    
    
    /**
     * Metodo actualizarNumeros
     * 
     * Esta funcion sirve para el momento en que se elimine un producto de la tabla los números
     * se reacomoden y queden de nuevo en orden.
     */
    private void actualizarNumeros() {

    for (int i = 0; i < modelo.getRowCount(); i++) {
        modelo.setValueAt(i + 1, i, 0);
        }
    }
    
    // ****** RN7: El total debe ser la suma exacta de subtotales ******
    /**
     * Metodo calcularTotal
     * 
     * Suma los subtotales en la tabla y los suma para obtener el total.
     * @return total
     */
    private double calcularTotal() {

    double total = 0;

    for (Producto p : listaProductos) {
        total += p.getSubtotal();
    }

    return total;
}
    
    private void limpiarVenta() {

    listaProductos.clear();
    modelo.setRowCount(0);

}
    

    /**
     * Creates new form PuntoVentaFrame
     */
    public PuntoVentaFrame() {
        initComponents();
        
        // SE ASIGNA MODELO DE TABLA
        
        modelo = (DefaultTableModel) jTable.getModel();
        
        // COLOR DE COLUMNA ELIMINAR USANDO LA CLASE EliminarRenderer
        // (Se crea una instancia)

        EliminarRenderer renderer = new EliminarRenderer();

        // Se le asigna a la columna 5 para que las celdas de esa columna se basen en la
        // lógica de EliminarRenderer.
        jTable.getColumnModel().getColumn(5).setCellRenderer(renderer);

        /*
        MouseMotionListener detecta cuando el mouse se mueve
        dentro del JTable.
       */
        jTable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

        public void mouseMoved(java.awt.event.MouseEvent e) {

            //-- Obtiene la fila y columna donde está actualmente posicionado el mouse.
            int row = jTable.rowAtPoint(e.getPoint());
            int column = jTable.columnAtPoint(e.getPoint());

            //-- Se actualiza el render de la celda y se repinta para el cambio de color.
            renderer.setHoverCell(row, column);

            jTable.repaint();
        }
    });

        /*
        Este MouseListener sirve para cuando el Mouse sale de la tabla.
        */
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {

        public void mouseExited(java.awt.event.MouseEvent e) {

            //-- Se desactiva el hover y vuelve al color rojo normal.
            renderer.setHoverCell(-1, -1);
            jTable.repaint();

        }
    });
        //--------- RF4 – Eliminar producto ------------
        // CLICKEAR EN COLUMNA ELIMINAR Y ELIMINAR EL PRODUCTO
        
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {

        public void mouseClicked(java.awt.event.MouseEvent evt) {

            //-- Obtiene la fila y columna donde se clickea
            int fila = jTable.rowAtPoint(evt.getPoint());
            int columna = jTable.columnAtPoint(evt.getPoint());

            //-- Columna 5 = columna "Eliminar"
            if (columna == 5) {

                //-- Muestra un cuadro de confirmación.
                int opcion = JOptionPane.showConfirmDialog(null,
                        "¿Eliminar producto?",
                        "Confirmar",
                        JOptionPane.YES_NO_OPTION);

                //-- Si el usuario confirma la eliminación se borra de la lista de productos y también
                // de la tabla.
                if (opcion == JOptionPane.YES_OPTION) {
                    listaProductos.remove(fila);
                    modelo.removeRow(fila);
                
                    //-- Se llama al metodo actualizarNumeros para que se vuelva a ordenar los # de la tabla.
                    actualizarNumeros();
                }
            }
        }
    });
        
        // ****** RN6: El subtotal debe calcularse automáticamente ******
        // CALCULAR SUBTOTAL AUTOMATICAMENTE EN SUBTOTALFIELD CADA QUE SE HAGA UN CAMBIO EN precioField y en cantidadField
        precioField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                calcularSubtotal();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                calcularSubtotal();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                calcularSubtotal();
            }
        });
        
        
        cantidadField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                calcularSubtotal();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                calcularSubtotal();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                calcularSubtotal();
            }
        });
        

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
        jTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nombreField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        subtotalField = new javax.swing.JTextField();
        agregarBtn = new javax.swing.JButton();
        finalizarVentaBtn = new javax.swing.JButton();
        precioField = new javax.swing.JTextField();
        cantidadField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Nombre", "Precio", "Cantidad", "Subtotal", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable);

        jLabel1.setFont(new java.awt.Font("Noto Sans JP", 0, 11)); // NOI18N
        jLabel1.setText("Nombre");

        jLabel2.setFont(new java.awt.Font("Noto Sans JP", 0, 11)); // NOI18N
        jLabel2.setText("Precio");

        jLabel3.setFont(new java.awt.Font("Noto Sans JP", 0, 11)); // NOI18N
        jLabel3.setText("Cantidad");

        jLabel4.setFont(new java.awt.Font("Noto Sans JP", 0, 11)); // NOI18N
        jLabel4.setText("Subtotal");

        jLabel5.setText("=");

        subtotalField.setEditable(false);
        subtotalField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subtotalFieldActionPerformed(evt);
            }
        });

        agregarBtn.setFont(new java.awt.Font("Noto Sans JP", 0, 11)); // NOI18N
        agregarBtn.setText("Agregar");
        agregarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBtnActionPerformed(evt);
            }
        });

        finalizarVentaBtn.setFont(new java.awt.Font("Noto Sans JP", 0, 11)); // NOI18N
        finalizarVentaBtn.setText("Finalizar Venta");
        finalizarVentaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarVentaBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(subtotalField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(158, 158, 158))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(finalizarVentaBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addComponent(jLabel2)
                                        .addGap(111, 111, 111))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(precioField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(cantidadField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                                .addComponent(agregarBtn)))))
                .addGap(57, 57, 57))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(agregarBtn)
                                .addComponent(precioField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cantidadField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(subtotalField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(1, 1, 1)))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(finalizarVentaBtn)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBtnActionPerformed
        
        // ****** RN8: No se pueden agregar más de 20 productos por venta ******
        /*
        Si el usuario clickea agregar y ya hay 20 productos en la tabla salta este mensaje y detiene la acción.
        */
        if (modelo.getRowCount() >= 20) {
        JOptionPane.showMessageDialog(this,
            "Solo se pueden agregar máximo 20 productos en la venta.");
        return;
    }
        
        // BLOQUE DEL NOMBRE
        String nombre = nombreField.getText();
        
        // ****** RN1: El nombre del producto es obligatorio ******
        /*
        Detiene al usuario de dejar el campo nombre vacio.
        */
        if (nombre.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "El nombre no puede estar vacío");
        return;
        }
        
        // BLOQUE DEL PRECIO 
        String precioTexto = precioField.getText();

        /*
        Detiene al usuario de dejar el campo precio vacio.
        */
        if (precioTexto.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "El precio no puede estar vacío");
        return;
        }

        double precio;

        /*
        Detienen al usuario de ingresar valores no validos en precio.
        */
        try {
            precio = Double.parseDouble(precioTexto);
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Ingrese un número válido para el precio");
            return;
        }

        // ****** RN2: El precio debe ser mayor a 0 ******
        if (precio <= 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "El precio debe ser mayor a 0");
            return;
        }

        // ****** RN5: El precio máximo permitido es 10,000 ******
        if (precio > 10000) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "El precio máximo permitido es 10,000");
            return;
        }
        
        // BLOQUE DE LA CANTIDAD

        String cantidadTexto = cantidadField.getText();

        /*
        Evita que el usuario deje el campo cantidad vacio.
        */
        if (cantidadTexto.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "La cantidad no puede estar vacía");
        return;
        }

        int cantidad;

        /*
        Evita que el usuario ingrese valores no validos en cantidad.
        */
        try {
            cantidad = Integer.parseInt(cantidadTexto);
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Ingrese un número entero para la cantidad");
            return;
        }

        
        // ****** RN3: La cantidad debe ser mayor a 0 ******
        if (cantidad <= 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "La cantidad debe ser mayor a 0");
            return;
        }

        // ****** RN4: La cantidad máxima permitida es 100 ******
        if (cantidad > 100) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "La cantidad máxima permitida es 100");
            return;
        }
        
        
        // AGREGAR FILA CON EL NUEVO PRODUCTO
        // --------- RF1 – Agregar producto ------------
        // --------- RF2 – Mostrar productos en la venta ------------
        
        Producto p = new Producto(nombre, precio, cantidad);
        listaProductos.add(p);
        
        modelo.addRow(new Object[]{
        modelo.getRowCount() + 1,
        p.getNombre(),
        p.getPrecio(),
        p.getCantidad(),
        p.getSubtotal(),
        "❌"
});
        
        // DEJAR LOS CAMPOS LIMPIOS DE NUEVO DESPUÉS DE HABER AGREGADO EL PRODUCTO A LA TABLA
        nombreField.setText("");
        precioField.setText("");
        cantidadField.setText("");
        subtotalField.setText("");

    }//GEN-LAST:event_agregarBtnActionPerformed

    private void subtotalFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subtotalFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subtotalFieldActionPerformed

    private void finalizarVentaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarVentaBtnActionPerformed
        
        /*
        Evita que el usuario finalice la venta si no hay productos agregados.
        */
        if (listaProductos.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No hay productos en la venta.");
            return;
        }
        
//--------- RF5 – Finalizar venta ------------
//Cuando el usuario presione Finalizar venta:
// • se mostrará el total final
// • la lista de productos se limpiará

        /*
        Se llama a la función calcular total.
        */
        double total = calcularTotal();
        
        /*
        Se abre el jDialog TotalVenta donde se muestra la venta total.
        El valor total es asignado al field dentro de TotalVenta.
        */
        TotalVenta dialog = new TotalVenta(this, true);

        dialog.setTotal(total);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        /*
        Una vez mostrado el total se limpia la tabla para que no haya persistencia.
        */
        limpiarVenta();
    }//GEN-LAST:event_finalizarVentaBtnActionPerformed

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
            java.util.logging.Logger.getLogger(PuntoVentaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PuntoVentaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PuntoVentaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PuntoVentaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PuntoVentaFrame().setVisible(true);
            }
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarBtn;
    private javax.swing.JTextField cantidadField;
    private javax.swing.JButton finalizarVentaBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField nombreField;
    private javax.swing.JTextField precioField;
    private javax.swing.JTextField subtotalField;
    // End of variables declaration//GEN-END:variables
}
