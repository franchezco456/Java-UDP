package frankyfranco.descuento.servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import frankyfranco.descuento.modelo.CalculoDescuento;
import frankyfranco.descuento.vistas.VentanaPrincipal;

/**
 * @author FRANKY FRANCO
 */
public class SubProcesoCliente extends Thread {

    private DatagramSocket socket;
    private DatagramPacket paqueteRecibido;
    private String ip;
    private int puerto;
    private VentanaPrincipal ventana;

    public SubProcesoCliente(DatagramSocket socket, DatagramPacket paquete, VentanaPrincipal v) {
        this.socket = socket;
        this.paqueteRecibido = paquete;
        this.ip = paquete.getAddress().getHostAddress();
        this.puerto = paquete.getPort();
        this.ventana = v;
    }

    @Override
    public void run() {
        try {
            CalculoDescuento.ResultadoDescuento res = calcularDescuento();
            enviarRespuesta(res);
        } catch (Exception ex) {
            registrarLog("Error: " + ex.getMessage());
        }
    }

    public CalculoDescuento.ResultadoDescuento calcularDescuento() throws Exception {
        try {
            byte[] data = paqueteRecibido.getData();
            ByteBuffer bb = ByteBuffer.wrap(data);

            registrarLog("Esperando el PRECIO ORIGINAL: ");
            float precioOriginal = bb.getFloat();
            registrarLog("PRECIO ORIGINAL: $" + precioOriginal);

            registrarLog("Esperando el PORCENTAJE DE DESCUENTO: ");
            float porcentajeDescuento = bb.getFloat();
            registrarLog("PORCENTAJE DESCUENTO: " + porcentajeDescuento + "%");

            CalculoDescuento datosDescuento = new CalculoDescuento(precioOriginal, porcentajeDescuento);
            CalculoDescuento.ResultadoDescuento res = datosDescuento.getResultado();

            registrarLog("MONTO DESCUENTO: $" + res.montoDescuento);
            registrarLog("PRECIO FINAL: $" + res.precioFinal);

            return res;
        } catch (Exception ex) {
            String msg = "Error al capturar datos del cliente " + ip;
            registrarLog(msg);
            throw new Exception(msg);
        }
    }

    public void enviarRespuesta(CalculoDescuento.ResultadoDescuento res) throws IOException {
        // Respuesta: 2 floats (montoDescuento + precioFinal) = 8 bytes
        ByteBuffer bb = ByteBuffer.allocate(8);
        bb.putFloat(res.montoDescuento);
        bb.putFloat(res.precioFinal);

        byte[] respuesta = bb.array();
        InetAddress direccionCliente = paqueteRecibido.getAddress();
        DatagramPacket paqueteRespuesta = new DatagramPacket(respuesta, respuesta.length, direccionCliente, puerto);
        socket.send(paqueteRespuesta);

        registrarLog("Respuesta enviada a " + ip + ":" + puerto);
    }

    private void registrarLog(String msg) {
        String logMsg = log() + msg;
        System.out.println(logMsg);
        if (ventana != null && ventana.getCajaLog() != null) {
            ventana.getCajaLog().append(logMsg + "\n");
        }
    }

    public String log() {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        return ip + " -> " + f.format(new Date()) + " - ";
    }
}