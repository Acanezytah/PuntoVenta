/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package itson.puntoventa;

import java.util.ArrayList;

/**
 *
 * @author Akane
 */
public class PuntoVenta {

    public static void main(String[] args) {
        
        //---- Se modifican las ventanas de confimación para que aparezcan en español.
        
        javax.swing.UIManager.put("OptionPane.yesButtonText", "Sí");
        javax.swing.UIManager.put("OptionPane.noButtonText", "No");
        javax.swing.UIManager.put("OptionPane.cancelButtonText", "Cancelar");

        
        //---- Se modifica la interfaz para que al abrir PuntoVentaFrame aparezca con Nimbus.
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //---- Llamar a abrir punto venta Frame 
        
        java.awt.EventQueue.invokeLater(() -> {
            PuntoVentaFrame frame = new PuntoVentaFrame();
            frame.setVisible(true);
        });

    }
}
