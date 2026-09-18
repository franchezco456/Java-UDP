package frankyfranco.descuento;

import frankyfranco.descuento.infrastructure.adapter.in.ui.VentanaPrincipal;

/**
 * Clase principal de inicio (Bootstrap) para el Servidor.
 */
public class Principal {

    public static void main(String[] args) {
        VentanaPrincipal v = new VentanaPrincipal();
        v.setLocationRelativeTo(null);
        v.setVisible(true);
    }
}