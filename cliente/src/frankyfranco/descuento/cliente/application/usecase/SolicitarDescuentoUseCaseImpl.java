package frankyfranco.descuento.cliente.application.usecase;

import frankyfranco.descuento.cliente.application.port.in.SolicitarDescuentoUseCase;
import frankyfranco.descuento.cliente.application.port.out.ClienteRedPort;
import frankyfranco.descuento.cliente.domain.model.RespuestaDescuento;
import frankyfranco.descuento.cliente.domain.model.SolicitudDescuento;

/**
 * Caso de uso que orquesta la solicitud de descuento a través del puerto de red.
 */
public class SolicitarDescuentoUseCaseImpl implements SolicitarDescuentoUseCase {

    private final ClienteRedPort redPort;

    public SolicitarDescuentoUseCaseImpl(ClienteRedPort redPort) {
        this.redPort = redPort;
    }

    @Override
    public RespuestaDescuento procesar(SolicitudDescuento solicitud) throws Exception {
        if (!redPort.isConectado()) {
            throw new IllegalStateException("Cliente Offline, Conecte con el Servidor");
        }
        return redPort.enviarYRecibir(solicitud);
    }

    @Override
    public void conectar(String host, int puerto) throws Exception {
        redPort.establecerDestino(host, puerto);
    }

    @Override
    public void desconectar() {
        redPort.cerrar();
    }

    @Override
    public boolean estaConectado() {
        return redPort.isConectado();
    }
}