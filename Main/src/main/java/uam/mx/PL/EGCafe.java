package uam.mx.pl;

import uam.mx.BL.dto.GestorCafe;


import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class EGCafe extends JFrame {

    private GestorCafe gestionCafes;
    private JTextArea textArea;

    public EGCafe(){
        
        gestionCafes = new GestorCafe();
        setTitle("Gestíón del almacen de Cafe");
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
    }






    public static void main(String[]args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                new EGCafe().setVisible(true);
            }
        });
    }
}
