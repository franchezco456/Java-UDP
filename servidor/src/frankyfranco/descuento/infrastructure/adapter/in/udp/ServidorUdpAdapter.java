package frankyfranco.descuento.infrastructure.adapter.in.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import frankyfranco.descuento.application.port.in.CalcularDescuentoUseCase;
import frankyfranco.descuento.application.port.in.ControlServidorUseCase;
import frankyfranco.descuento.application.port.out.NotificadorLogPort;
import frankyfranco.descuento.domain.model.Descuento;
import frankyfranco.descuento.domain.model.ResultadoDescuento;

/**
 * Adaptador Primario (Driving Adapter) para UDP.
 * Escucha paquetes datagrama, invoca el caso de uso y responde al cliente.
 */
public class ServidorUdpAdapter extends Thread implements ControlServidorUseCase {

    private final CalcularDescuentoUseCase calcularDescuentoUseCase;
    private final NotificadorLogPort logPort;
    private DatagramSocket socket;
    private volatile boolean activo = false;
    private int puerto = 9007;

    public static final int BUFFER_SIZE = 8; // 2 floats: precio + porcentaje

    public ServidorUdpAdapter(CalcularDescuentoUseCase calcularDescuentoUseCase, NotificadorLogPort logPort) {
        this.calcularDescuentoUseCase = calcularDescuentoUseCase;
        this.logPort = logPort;
    }

    @Override
    public synchronized void iniciar(int puerto) throws Exception {
        if (activo) {
            return;
        }
        this.puerto = puerto;
        this.socket = new DatagramSocket(puerto);
        this.activo = true;
        this.start();
        if (logPort != null) {
            logPort.registrarLog("Servidor UDP iniciado y disponible en el Puerto " + puerto);
        }
    }

    @Override
    public synchronized void detener() {
        activo = false;
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        if (logPort != null) {
            logPort.registrarLog("Servidor UDP detenido.");
        }
    }

    @Override
    public boolean estaActivo() {
        return activo;
    }

    @Override
    public void run() {
        while (activo) {
            try {
                byte[] buffer = new byte[BUFFER_SIZE];
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);

                String ipCliente = paquete.getAddress().getHostAddress();
                int puertoCliente = paquete.getPort();

                if (logPort != null) {
                    logPort.registrarLog("Petición UDP recibida de " + ipCliente + ":" + puertoCliente);
                }

                // Atender cada datagrama en un hilo secundario
                procesarPeticion(paquete);

            } catch (IOException ex) {
                if (!activo) {
                    break; // Cierre normal al detener el servicio
                }
                if (logPort != null) {
                    logPort.registrarLog("Error en socket UDP: " + ex.getMessage());
                }
            }
        }
    }

    private void procesarPeticion(DatagramPacket paquete) {
        new Thread(() -> {
            try {
                ByteBuffer bb = ByteBuffer.wrap(paquete.getData());
                float precioOriginal = bb.getFloat();
                float porcentajeDescuento = bb.getFloat();

                Descuento descuento = new Descuento(precioOriginal, porcentajeDescuento);
                ResultadoDescuento resultado = calcularDescuentoUseCase.ejecutar(descuento);

                // Enviar respuesta: 2 floats = 8 bytes
                ByteBuffer bbResp = ByteBuffer.allocate(8);
                bbResp.putFloat(resultado.getMontoDescuento());
                bbResp.putFloat(resultado.getPrecioFinal());

                byte[] respBytes = bbResp.array();
                InetAddress ipCliente = paquete.getAddress();
                int puertoCliente = paquete.getPort();
                DatagramPacket paqueteResp = new DatagramPacket(respBytes, respBytes.length, ipCliente, puertoCliente);

                socket.send(paqueteResp);

                if (logPort != null) {
                    logPort.registrarLog("Respuesta enviada a " + ipCliente.getHostAddress() + ":" + puertoCliente);
                }
            } catch (Exception e) {
                if (logPort != null) {
                    logPort.registrarLog("Error al procesar paquete: " + e.getMessage());
                }
            }
        }).start();
    }
}