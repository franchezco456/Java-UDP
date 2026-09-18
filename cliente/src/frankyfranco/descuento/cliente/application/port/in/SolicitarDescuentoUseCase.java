package frankyfranco.descuento.cliente.application.port.in;

import frankyfranco.descuento.cliente.domain.model.RespuestaDescuento;
import frankyfranco.descuento.cliente.domain.model.SolicitudDescuento;

/**
 * Puerto de Entrada para que la UI u otro cliente solicite el cálculo de descuento.
 */
public interface SolicitarDescuentoUseCase {
    RespuestaDescuento procesar(SolicitudDescuento solicitud) throws Exception;
    void conectar(String host, int puerto) throws Exception;
    void desconectar();
    boolean estaConectado();
}