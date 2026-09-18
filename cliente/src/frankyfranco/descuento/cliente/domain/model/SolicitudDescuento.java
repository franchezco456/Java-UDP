package frankyfranco.descuento.cliente.domain.model;

import java.io.Serializable;

/**
 * Representa los datos de solicitud para calcular un descuento.
 * Pertenece a la capa de Dominio del Cliente.
 */
public class SolicitudDescuento implements Serializable {

    private final float precioOriginal;
    private final float porcentajeDescuento;

    public SolicitudDescuento(float precioOriginal, float porcentajeDescuento) {
        this.precioOriginal = precioOriginal;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public float getPrecioOriginal() {
        return precioOriginal;
    }

    public float getPorcentajeDescuento() {
        return porcentajeDescuento;
    }
}