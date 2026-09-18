package frankyfranco.descuento.cliente.infrastructure.adapter.out.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import frankyfranco.descuento.cliente.application.port.out.ClienteRedPort;
import frankyfranco.descuento.cliente.domain.model.RespuestaDescuento;
import frankyfranco.descuento.cliente.domain.model.SolicitudDescuento;

/**
 * Adaptador Secundario (Driven Adapter) que implementa ClienteRedPort utilizando el protocolo UDP.
 */
public class ClienteUdpAdapter implements ClienteRedPort {

    private DatagramSocket socket;
    private InetAddress hostServidor;
    private int puertoServidor;
    private boolean conectado = false;

    private static final int BUFFER_SIZE = 8; // 2 floats: montoDescuento + precioFinal
    private static final int TIMEOUT_MS = 5000;

    @Override
    public void establecerDestino(String host, int puerto) throws Exception {
        this.hostServidor = InetAddress.getByName(host);
        this.puertoServidor = puerto;
        if (this.socket == null || this.socket.isClosed()) {
            this.socket = new DatagramSocket();
        }
        this.socket.setSoTimeout(TIMEOUT_MS);
        this.conectado = true;
    }

    @Override
    public RespuestaDescuento enviarYRecibir(SolicitudDescuento solicitud) throws Exception {
        if (!conectado || socket == null || socket.isClosed()) {
            throw new IOException("Socket UDP no disponible o desconectado");
        }

        // Empaquetar 2 floats: precio + porcentaje
        ByteBuffer bbEnvio = ByteBuffer.allocate(8);
        bbEnvio.putFloat(solicitud.getPrecioOriginal());
        bbEnvio.putFloat(solicitud.getPorcentajeDescuento());
        byte[] datosEnvio = bbEnvio.array();

        DatagramPacket paqueteEnvio = new DatagramPacket(
            datosEnvio, datosEnvio.length, hostServidor, puertoServidor
        );
        socket.send(paqueteEnvio);

        // Recibir respuesta: 2 floats
        byte[] bufferRespuesta = new byte[BUFFER_SIZE];
        DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);
        socket.receive(paqueteRespuesta);

        ByteBuffer bbResp = ByteBuffer.wrap(paqueteRespuesta.getData());
        float montoDescuento = bbResp.getFloat();
        float precioFinal = bbResp.getFloat();

        return new RespuestaDescuento(montoDescuento, precioFinal);
    }

    @Override
    public void cerrar() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        conectado = false;
    }

    @Override
    public boolean isConectado() {
        return conectado && socket != null && !socket.isClosed();
    }
}