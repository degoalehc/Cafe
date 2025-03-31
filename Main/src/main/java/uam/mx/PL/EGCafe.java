package uam.mx.PL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EGCafe extends JFrame {

    private JTextArea textArea;
    private JTextField tfNombreProducto, tfCantidad;
    private JButton btnAgregarProducto, btnEliminarProducto, btnActualizarProducto, btnVenta;
    private JTable tableInventario;

    public EGCafe() {
        setTitle("Gestión de Inventario y Ventas de Cafetería");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con BorderLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Panel de Inventario
        JPanel panelControles = new JPanel();
        panelControles.setLayout(new GridLayout(3, 2));
        
        panelControles.add(new JLabel("Nombre del Producto:"));
        tfNombreProducto = new JTextField();
        panelControles.add(tfNombreProducto);

        panelControles.add(new JLabel("Cantidad:"));
        tfCantidad = new JTextField();
        panelControles.add(tfCantidad);

        btnAgregarProducto = new JButton("Agregar Producto");
        btnEliminarProducto = new JButton("Eliminar Producto");
        btnActualizarProducto = new JButton("Actualizar Producto");
        
        panelControles.add(btnAgregarProducto);
        panelControles.add(btnEliminarProducto);
        panelControles.add(btnActualizarProducto);

        // Panel de tabla para mostrar inventario
        String[] columnNames = {"ID", "Nombre Producto", "Cantidad"};
        Object[][] data = {}; // Inicializar vacío
        tableInventario = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(tableInventario);

        // Panel de venta
        btnVenta = new JButton("Realizar Venta");
        btnVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaVenta();
            }
        });

        // Agregar todos los paneles al panel principal
        panel.add(panelControles, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnVenta, BorderLayout.SOUTH);

        add(panel);

        // Eventos para los botones de inventario
        btnAgregarProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });

        btnEliminarProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        btnActualizarProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
        });
    }

    // Función para agregar producto
    private void agregarProducto() {
        String nombre = tfNombreProducto.getText();
        String cantidad = tfCantidad.getText();
        // Lógica para agregar producto en la base de datos y en la tabla
        JOptionPane.showMessageDialog(this, "Producto agregado: " + nombre);
    }

    // Función para eliminar producto
    private void eliminarProducto() {
        // Lógica para eliminar el producto seleccionado
        JOptionPane.showMessageDialog(this, "Producto eliminado");
    }

    // Función para actualizar producto
    private void actualizarProducto() {
        // Lógica para actualizar un producto
        JOptionPane.showMessageDialog(this, "Producto actualizado");
    }

    // Función para abrir la ventana de venta
    private void abrirVentanaVenta() {
        // Crear ventana emergente
        JDialog ventaDialog = new JDialog(this, "Realizar Venta", true);
        ventaDialog.setSize(300, 200);
        ventaDialog.setLayout(new GridLayout(3, 1));
        ventaDialog.setLocationRelativeTo(this);

        JLabel label = new JLabel("Ingrese la cantidad a vender:");
        JTextField cantidadField = new JTextField();

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cantidadTexto = cantidadField.getText();
                try {
                    int cantidad = Integer.parseInt(cantidadTexto);
                    if (cantidad > 0) {
                        JOptionPane.showMessageDialog(ventaDialog, "Venta de " + cantidad + " unidades realizada.");
                        ventaDialog.dispose(); // Cerrar la ventana después de la venta
                        // Aquí puedes agregar la lógica de actualizar el inventario después de una venta
                    } else {
                        JOptionPane.showMessageDialog(ventaDialog, "Ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ventaDialog, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
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
