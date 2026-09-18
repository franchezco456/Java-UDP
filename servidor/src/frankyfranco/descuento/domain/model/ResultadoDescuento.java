package frankyfranco.descuento.domain.model;

import java.io.Serializable;

/**
 * Representa el resultado del cálculo de un descuento.
 * Pertenece a la capa de Dominio (Core).
 */
public class ResultadoDescuento implements Serializable {

    private final float montoDescuento;
    private final float precioFinal;
    private final String mensaje;

    public ResultadoDescuento(float montoDescuento, float precioFinal, String mensaje) {
        this.montoDescuento = montoDescuento;
        this.precioFinal = precioFinal;
        this.mensaje = mensaje;
    }

    public float getMontoDescuento() {
        return montoDescuento;
    }

    public float getPrecioFinal() {
        return precioFinal;
    }

    public String getMensaje() {
        return mensaje;
    }
}