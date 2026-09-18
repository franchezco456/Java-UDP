package frankyfranco.descuento.domain.service;

import frankyfranco.descuento.domain.model.Descuento;
import frankyfranco.descuento.domain.model.ResultadoDescuento;

/**
 * Servicio de Dominio que encapsula las reglas de negocio del cálculo de descuentos.
 */
public class CalculoDescuentoService {

    public ResultadoDescuento calcular(Descuento descuento) {
        if (!descuento.esValido()) {
            return new ResultadoDescuento(
                0.0f,
                0.0f,
                "ERROR: El precio y el porcentaje de descuento deben ser mayores o iguales a 0"
            );
        }

        float montoDescuento = descuento.getPrecioOriginal() * (descuento.getPorcentajeDescuento() / 100.0f);
        float precioFinal = descuento.getPrecioOriginal() - montoDescuento;
        String mensaje = String.format("Descuento del %.2f%% aplicado correctamente.", descuento.getPorcentajeDescuento());

        return new ResultadoDescuento(montoDescuento, precioFinal, mensaje);
    }
}