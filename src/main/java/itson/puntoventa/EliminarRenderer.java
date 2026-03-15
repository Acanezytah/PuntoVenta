/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.puntoventa;

/**
 *
 * @author Akane
 */
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/*
---- Clase que se encarga de pintar la columna eliminar
*/
public class EliminarRenderer extends DefaultTableCellRenderer {

    /**
     * Guarda la fila sobre la que actualmente está el mouse.
     * Se inicializa en -1 para indicar que no hay ninguna fila activa.
     */
    private int hoverRow = -1;

    /**
     * Guarda la columna sobre la que actualmente está el mouse.
     * Se usa junto con hoverRow para identificar la celda exacta.
     */
    private int hoverColumn = -1;

    /*
    Constructor donde se indica que el contenido de la celda se vea centrado.
    */
    public EliminarRenderer() {
        setHorizontalAlignment(CENTER);
    }
    
    
    /**
     * Metodo setHoverCell
     * ---- Esta función actualiza la celda en donde está posicionado el mouse.
     * 
     * Este método es llamado desde el MouseListener del JTable
     * para indicar qué celda debe mostrar el efecto hover.
     * 
     * @param row
     * @param column 
     */
    public void setHoverCell(int row, int column) {
        hoverRow = row;
        hoverColumn = column;
    }

    /**
     * Método sobrescrito de DefaultTableCellRenderer que controla
     * cómo se dibuja cada celda de la tabla.
     * 
     * Swing llama automáticamente a este método cada vez que
     * necesita pintar una celda del JTable.
     * 
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @return 
     */
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        //-- Obtiene el componente base que utilizar swing para pintar la celda.
        Component c = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        //-- Si la celda actual coincide con la celda donde está el mouse se pinta de un rojo más oscuro.
        if (row == hoverRow && column == hoverColumn) {
            c.setBackground(new Color(200, 0, 0));
        } else {
            //-- Si no, se pinta con un rojo normal.
            c.setBackground(Color.RED);
        }

        //-- El texto dentro de la celda se pinta de blanco.
        c.setForeground(Color.WHITE);

        //-- Devuelve el componente que Swing dibujará en la tabla
        return c;
    }
}
