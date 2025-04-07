package uam.mx.pl;
import uam.mx.bl.GestorAlmacenCafe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EGCafe extends JFrame {

    private GestorAlmacenCafe gestorCafe;
    private JTextArea textArea;

    public EGCafe() {
        gestorCafe = new GestorAlmacenCafe();
        setTitle("Gestión del Almacén de Café");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botón de venta
        JButton btnVenta = new JButton("Venta");
        btnVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaVenta();
            }
        });

        // Agregar el botón en la parte inferior
        panel.add(btnVenta, BorderLayout.SOUTH);

        // Agregar el panel al frame
        add(panel);

        // Cargar datos iniciales
        actualizarListaCafes();
    }

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
                        actualizarListaCafes(); // Actualizar el listado de cafés
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

    private void actualizarListaCafes() {
        textArea.setText("Lista de Cafés en Almacén:\n");

        // Simulación: reemplázalo con datos reales de tu gestor de cafés
        textArea.append("Ejemplo de café en almacén\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EGCafe().setVisible(true));
    }
}
