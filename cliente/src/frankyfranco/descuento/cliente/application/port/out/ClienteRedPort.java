package frankyfranco.descuento.cliente.application.port.out;

import frankyfranco.descuento.cliente.domain.model.RespuestaDescuento;
import frankyfranco.descuento.cliente.domain.model.SolicitudDescuento;

/**
 * Puerto de Salida para desacoplar el transporte de red de la lógica de aplicación.
 */
public interface ClienteRedPort {
    void establecerDestino(String host, int puerto) throws Exception;
    RespuestaDescuento enviarYRecibir(SolicitudDescuento solicitud) throws Exception;
    void cerrar();
    boolean isConectado();
}