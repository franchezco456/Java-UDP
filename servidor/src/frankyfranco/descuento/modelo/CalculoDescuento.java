package frankyfranco.descuento.modelo;

import java.io.Serializable;

/**
 * @author FRANKY FRANCO
 */
public class CalculoDescuento implements Serializable {

    private float precioOriginal;
    private float porcentajeDescuento;

    public static class ResultadoDescuento {
        public float montoDescuento;
        public float precioFinal;
        public String mensaje;
    }

    public CalculoDescuento() {
    }

    public CalculoDescuento(float precioOriginal, float porcentajeDescuento) {
        this.precioOriginal = precioOriginal;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public ResultadoDescuento getResultado() {
        ResultadoDescuento res = new ResultadoDescuento();
        if (precioOriginal < 0 || porcentajeDescuento < 0) {
            res.montoDescuento = 0.0f;
            res.precioFinal = 0.0f;
            res.mensaje = "ERROR: El precio y el porcentaje de descuento deben ser mayores o iguales a 0";
            return res;
        }

        res.montoDescuento = precioOriginal * (porcentajeDescuento / 100.0f);
        res.precioFinal = precioOriginal - res.montoDescuento;
        res.mensaje = String.format("Descuento del %.2f%% aplicado correctamente.", porcentajeDescuento);
        return res;
    }

    public float getPrecioOriginal() {
        return precioOriginal;
    }

    public void setPrecioOriginal(float precioOriginal) {
        this.precioOriginal = precioOriginal;
    }

    public float getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(float porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }
}
