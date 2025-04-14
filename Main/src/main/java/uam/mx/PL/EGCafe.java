package uam.mx.pl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import uam.mx.pl.bl.dto.GestorAlmacenCafe;

public class EGCafe extends JFrame {

    private GestorAlmacenCafe gestorCafe;
    private JTextField tfNombreProducto, tfCantidad;
    private JButton btnAgregarProducto, btnEliminarProducto, btnActualizarProducto, btnVenta;
    private JTable tableInventario;
    private DefaultTableModel tableModel;

    public EGCafe() {
        gestorCafe = new GestorAlmacenCafe();
        setTitle("Gestión del Almacén de Café");
        setSize(800, 500); // Dimensiones ajustadas para mejor presentación
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10)); // Espaciado agregado

        // Panel de controles con BoxLayout
        JPanel panelControles = new JPanel();
        panelControles.setLayout(new BoxLayout(panelControles, BoxLayout.Y_AXIS)); // Configuración en eje Y
        panelControles.setBorder(BorderFactory.createTitledBorder("Control de Inventario"));

        panelControles.add(new JLabel("Nombre del Producto:"));
        tfNombreProducto = new JTextField();
        tfNombreProducto.setMaximumSize(new Dimension(Integer.MAX_VALUE, tfNombreProducto.getPreferredSize().height)); // Ajuste de tamaño
        panelControles.add(tfNombreProducto);

        panelControles.add(new JLabel("Cantidad:"));
        tfCantidad = new JTextField();
        tfCantidad.setMaximumSize(new Dimension(Integer.MAX_VALUE, tfCantidad.getPreferredSize().height)); // Ajuste de tamaño
        panelControles.add(tfCantidad);

        btnAgregarProducto = new JButton("Agregar Producto");
        btnEliminarProducto = new JButton("Eliminar Producto");
        btnActualizarProducto = new JButton("Actualizar Producto");

        panelControles.add(btnAgregarProducto);
        panelControles.add(btnEliminarProducto);
        panelControles.add(btnActualizarProducto);

        // Panel de tabla con datos dinámicos
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre Producto", "Cantidad"}, 0);
        tableInventario = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableInventario);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Inventario Actual"));

        // Panel de venta con botón centrado
        JPanel panelVenta = new JPanel();
        btnVenta = new JButton("Realizar Venta");
        panelVenta.add(btnVenta);

        // Agregar paneles al principal
        panelPrincipal.add(panelControles, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(panelVenta, BorderLayout.SOUTH);

        add(panelPrincipal);

        // Eventos para los botones
        btnAgregarProducto.addActionListener(e -> agregarProducto());
        btnEliminarProducto.addActionListener(e -> eliminarProducto());
        btnActualizarProducto.addActionListener(e -> actualizarProducto());
        btnVenta.addActionListener(e -> abrirVentanaVenta());
    }

    private void agregarProducto() {
        String nombre = tfNombreProducto.getText();
        String cantidad = tfCantidad.getText();
        try {
            int cantidadNumerica = Integer.parseInt(cantidad);
            tableModel.addRow(new Object[]{tableModel.getRowCount() + 1, nombre, cantidadNumerica});
            JOptionPane.showMessageDialog(this, "Producto agregado: " + nombre);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad inválida. Ingrese un número.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarProducto() {
        int filaSeleccionada = tableInventario.getSelectedRow();
        if (filaSeleccionada != -1) {
            tableModel.removeRow(filaSeleccionada);
            JOptionPane.showMessageDialog(this, "Producto eliminado.");
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarProducto() {
        int filaSeleccionada = tableInventario.getSelectedRow();
        if (filaSeleccionada != -1) {
            String nombre = tfNombreProducto.getText();
            String cantidad = tfCantidad.getText();
            try {
                int cantidadNumerica = Integer.parseInt(cantidad);
                tableModel.setValueAt(nombre, filaSeleccionada, 1);
                tableModel.setValueAt(cantidadNumerica, filaSeleccionada, 2);
                JOptionPane.showMessageDialog(this, "Producto actualizado.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Cantidad inválida. Ingrese un número.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirVentanaVenta() {
        JDialog ventaDialog = new JDialog(this, "Realizar Venta", true);
        ventaDialog.setSize(300, 200);
        ventaDialog.setLocationRelativeTo(this);
        ventaDialog.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel label = new JLabel("Ingrese la cantidad a vender:");
        JTextField cantidadField = new JTextField();
        JButton btnConfirmar = new JButton("Confirmar");

        btnConfirmar.addActionListener(e -> {
            String cantidadTexto = cantidadField.getText();
            try {
                int cantidad = Integer.parseInt(cantidadTexto);
                if (cantidad > 0) {
                    JOptionPane.showMessageDialog(ventaDialog, "Venta de " + cantidad + " unidades realizada.");
                    ventaDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(ventaDialog, "Ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ventaDialog, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        ventaDialog.add(label);
        ventaDialog.add(cantidadField);
        ventaDialog.add(btnConfirmar);
        ventaDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EGCafe().setVisible(true));
    }
}