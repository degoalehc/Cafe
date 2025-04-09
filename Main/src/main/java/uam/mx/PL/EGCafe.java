package uam.mx.PL;

import uam.mx.Bl.Dto.GestorAlmacenCafe;
import uam.mx.bl.dto.CafeDto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class EGCafe extends JFrame {

    private GestorAlmacenCafe gestorCafe;  // Conexión con el gestor de inventarios
    private JTable tableInventario;        // Tabla para mostrar el inventario de café
    private JButton btnVerInventario, btnVenta;  // Botones para ver inventario y realizar ventas

    public EGCafe() {
        gestorCafe = new GestorAlmacenCafe();  // Inicializamos el gestor

        // Configuración básica de la ventana
        setTitle("Gestión del Almacén de Café");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con BorderLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Panel de controles (botones)
        JPanel panelControles = new JPanel();
        panelControles.setLayout(new GridLayout(1, 2));  // Un solo renglón con 2 columnas

        // Botón para ver inventario
        btnVerInventario = new JButton("Ver Inventario");
        btnVerInventario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarInventario();  // Cuando se hace clic, mostramos el inventario
            }
        });
        
        // Botón para realizar venta
        btnVenta = new JButton("Realizar Venta");
        btnVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaVenta();  // Abrir la ventana de venta al hacer clic
            }
        });

        // Agregamos los botones al panel de controles
        panelControles.add(btnVerInventario);
        panelControles.add(btnVenta);

        // Panel de tabla para mostrar el inventario
        String[] columnNames = {"ID", "Nombre Producto", "Cantidad"};
        Object[][] data = {}; // Inicializamos vacío
        tableInventario = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(tableInventario);

        // Agregar todos los componentes al panel principal
        panel.add(panelControles, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);  // Añadimos el panel principal a la ventana
    }

    // Función para mostrar el inventario en la tabla
    private void mostrarInventario() {
        List<CafeDto> cafes = gestorCafe.obtenerTodosLosCafes();  // Consultar todos los cafés
        DefaultTableModel model = (DefaultTableModel) tableInventario.getModel();
        model.setRowCount(0);  // Limpiar la tabla antes de llenarla

        // Llenar la tabla con los datos de los cafés
        for (CafeDto cafe : cafes) {
            model.addRow(new Object[]{cafe.getId(), cafe.getNombre(), cafe.getCantidad()});
        }
    }

    // Función para abrir la ventana de venta
    private void abrirVentanaVenta() {
        JDialog ventaDialog = new JDialog(this, "Realizar Venta", true);  // Crear ventana emergente
        ventaDialog.setSize(300, 200);
        ventaDialog.setLayout(new GridLayout(3, 1));  // Tres filas para los componentes

        JLabel label = new JLabel("Ingrese la cantidad a vender:");
        JTextField cantidadField = new JTextField();  // Campo para ingresar la cantidad a vender

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cantidadTexto = cantidadField.getText();
                try {
                    int cantidad = Integer.parseInt(cantidadTexto);  // Convertir la cantidad a número
                    if (cantidad > 0) {
                        // Seleccionamos el producto de la tabla
                        int selectedRow = tableInventario.getSelectedRow();
                        if (selectedRow != -1) {
                            int idCafe = (int) tableInventario.getValueAt(selectedRow, 0);
                            String nombreCafe = (String) tableInventario.getValueAt(selectedRow, 1);
                            int cantidadInventario = (int) tableInventario.getValueAt(selectedRow, 2);

                            // Verificar si hay suficiente inventario
                            if (cantidad <= cantidadInventario) {
                                // Realizamos la venta y actualizamos el inventario
                                boolean ventaExitosa = procesarVenta(idCafe, cantidad);
                                if (ventaExitosa) {
                                    JOptionPane.showMessageDialog(ventaDialog, "Venta de " + cantidad + " unidades de " + nombreCafe + " realizada.");
                                    ventaDialog.dispose();  // Cerrar la ventana después de la venta
                                    mostrarInventario();  // Actualizar el inventario en la tabla
                                } else {
                                    JOptionPane.showMessageDialog(ventaDialog, "Error al procesar la venta.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(ventaDialog, "No hay suficiente inventario.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(ventaDialog, "Seleccione un producto de la lista.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(ventaDialog, "Ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ventaDialog, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Agregar los componentes al diálogo
        ventaDialog.add(label);
        ventaDialog.add(cantidadField);
        ventaDialog.add(btnConfirmar);

        ventaDialog.setVisible(true);  // Mostrar la ventana de venta
    }

    // Función para procesar la venta y actualizar el inventario
    private boolean procesarVenta(int idCafe, int cantidadVendida) {
        // Obtener café desde el inventario
        List<CafeDto> cafes = gestorCafe.obtenerTodosLosCafes();
        for (CafeDto cafe : cafes) {
            if (cafe.getId() == idCafe) {
                int cantidadRestante = cafe.getCantidad() - cantidadVendida;
                if (cantidadRestante >= 0) {
                    cafe.setCantidad(cantidadRestante);  // Actualizar la cantidad
                    return gestorCafe.actualizarCafe(cafe);  // Actualizar en la base de datos
                }
                break;
            }
        }
        return false;  // Si no hay suficiente inventario
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EGCafe().setVisible(true));  // Ejecutar la interfaz gráfica
    }
}
