package frankyfranco.descuento.application.usecase;

import frankyfranco.descuento.application.port.in.CalcularDescuentoUseCase;
import frankyfranco.descuento.application.port.out.NotificadorLogPort;
import frankyfranco.descuento.domain.model.Descuento;
import frankyfranco.descuento.domain.model.ResultadoDescuento;
import frankyfranco.descuento.domain.service.CalculoDescuentoService;

/**
 * Caso de Uso que orquesta el cálculo de descuento y notifica los eventos pertinentes.
 */
public class CalcularDescuentoUseCaseImpl implements CalcularDescuentoUseCase {

    private final CalculoDescuentoService service;
    private final NotificadorLogPort logPort;

    public CalcularDescuentoUseCaseImpl(CalculoDescuentoService service, NotificadorLogPort logPort) {
        this.service = service;
        this.logPort = logPort;
    }

    @Override
    public ResultadoDescuento ejecutar(Descuento descuento) {
        ResultadoDescuento resultado = service.calcular(descuento);
        if (logPort != null) {
            logPort.registrarLog("PRECIO ORIGINAL: $" + descuento.getPrecioOriginal());
            logPort.registrarLog("PORCENTAJE DESCUENTO: " + descuento.getPorcentajeDescuento() + "%");
            logPort.registrarLog("MONTO DESCUENTO: $" + resultado.getMontoDescuento());
            logPort.registrarLog("PRECIO FINAL: $" + resultado.getPrecioFinal());
        }
        return resultado;
    }
}