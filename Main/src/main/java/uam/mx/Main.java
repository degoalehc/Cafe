package uam.mx;

import javax.swing.SwingUtilities;

import uam.mx.PL.EGCafe;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                new EGCafe().setVisible(true);
            }
        });
    }
}