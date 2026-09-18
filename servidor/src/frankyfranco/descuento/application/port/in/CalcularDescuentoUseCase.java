package frankyfranco.descuento.application.port.in;

import frankyfranco.descuento.domain.model.Descuento;
import frankyfranco.descuento.domain.model.ResultadoDescuento;

/**
 * Puerto de Entrada (Driving Port) para el cálculo de descuentos.
 */
public interface CalcularDescuentoUseCase {
    ResultadoDescuento ejecutar(Descuento descuento);
}