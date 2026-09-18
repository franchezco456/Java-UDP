package frankyfranco.descuento.cliente;

import frankyfranco.descuento.cliente.infrastructure.adapter.in.ui.VentanaPrincipal;

/**
 * Punto de entrada (Bootstrap) del Cliente.
 */
public class Principal {

    public static void main(String[] args) {
        VentanaPrincipal v = new VentanaPrincipal();
        v.setLocationRelativeTo(null);
        v.setVisible(true);
    }
}