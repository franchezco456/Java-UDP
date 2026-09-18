package frankyfranco.descuento.domain.model;

import java.io.Serializable;

/**
 * Representa los datos requeridos para calcular un descuento.
 * Pertenece a la capa de Dominio (Core).
 */
public class Descuento implements Serializable {

    private final float precioOriginal;
    private final float porcentajeDescuento;

    public Descuento(float precioOriginal, float porcentajeDescuento) {
        this.precioOriginal = precioOriginal;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public float getPrecioOriginal() {
        return precioOriginal;
    }

    public float getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public boolean esValido() {
        return precioOriginal >= 0 && porcentajeDescuento >= 0;
    }
}