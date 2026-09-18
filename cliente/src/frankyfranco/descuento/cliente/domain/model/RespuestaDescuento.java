package frankyfranco.descuento.cliente.domain.model;

import java.io.Serializable;

/**
 * Representa la respuesta calculada recibida desde el servidor.
 * Pertenece a la capa de Dominio del Cliente.
 */
public class RespuestaDescuento implements Serializable {

    private final float montoDescuento;
    private final float precioFinal;

    public RespuestaDescuento(float montoDescuento, float precioFinal) {
        this.montoDescuento = montoDescuento;
        this.precioFinal = precioFinal;
    }

    public float getMontoDescuento() {
        return montoDescuento;
    }

    public float getPrecioFinal() {
        return precioFinal;
    }
}