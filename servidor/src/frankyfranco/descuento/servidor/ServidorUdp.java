package frankyfranco.descuento.servidor;

import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
import frankyfranco.descuento.vistas.VentanaPrincipal;

/**
 * @author FRANKY FRANCO
 */
public class ServidorUdp extends Thread {

    private Boolean estado;
    private Integer puerto = 9007;
    private DatagramSocket servicio;
    private VentanaPrincipal ventana;

    // Tamano del buffer de entrada: 2 floats (precio + porcentaje) = 8 bytes
    public static final int BUFFER_SIZE = 8;

    public ServidorUdp(Integer puerto, VentanaPrincipal v) {
        if (puerto != null && puerto != 0) {
            this.puerto = puerto;
        }
        this.ventana = v;
    }

    @Override
    public void run() {
        super.run();
        iniciarServicio();
    }

    public void iniciarServicio() {
        try {
            servicio = new DatagramSocket(puerto);
            estado = true;
            if (ventana != null) {
                ventana.getBtnIniciar().setText("DETENER");
                ventana.getTxtEstado().setText("ONLINE");
                ventana.getTxtEstado().setForeground(Color.GREEN);
                ventana.getBtnIniciar().setForeground(Color.RED);
            }
            String msg = log() + "Servidor UDP disponible en el Puerto " + puerto;
            System.out.println(msg);
            if (ventana != null && ventana.getCajaLog() != null) {
                ventana.getCajaLog().append(msg + "\n");
            }

            while (estado) {
                byte[] buffer = new byte[BUFFER_SIZE];
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                servicio.receive(paquete);

                String ip = paquete.getAddress().getHostAddress();
                msg = log() + "Peticion UDP recibida de " + ip + ":" + paquete.getPort();
                System.out.println(msg);
                if (ventana != null && ventana.getCajaLog() != null) {
                    ventana.getCajaLog().append(msg + "\n");
                }

                SubProcesoCliente atencion = new SubProcesoCliente(servicio, paquete, ventana);
                atencion.start();
            }
        } catch (IOException ex) {
            if (estado == null || !estado) {
                return;
            }
            String msg = log() + "ERROR al abrir el puerto " + puerto;
            System.out.println(msg);
            if (ventana != null) {
                if (ventana.getCajaLog() != null) {
                    ventana.getCajaLog().append(msg + "\n");
                }
                ventana.getBtnIniciar().setText("INICIAR");
                ventana.getTxtEstado().setText("OFF LINE");
            }
        }
    }

    public void detenerServicio() {
        if (estado != null && estado) {
            estado = false;
            if (ventana != null) {
                ventana.getBtnIniciar().setText("INICIAR");
                ventana.getBtnIniciar().setForeground(Color.GREEN);
                ventana.getTxtEstado().setText("OFF LINE");
                ventana.getTxtEstado().setForeground(Color.RED);
            }
            if (servicio != null && !servicio.isClosed()) {
                servicio.close();
            }
            String msg = log() + "Servidor UDP detenido";
            System.out.println(msg);
            if (ventana != null && ventana.getCajaLog() != null) {
                ventana.getCajaLog().append(msg + "\n");
            }
        }
    }

    public String log() {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        return f.format(new Date()) + " - ";
    }
}