package frankyfranco.descuento.cliente.vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 * @author FRANKY FRANCO
 */
public class VentanaPrincipal extends JFrame {

    private DatagramSocket socketUdp;
    private InetAddress direccionServidor;
    private int puertoServidor;
    private boolean conectado = false;

    // Variables declaration
    private JButton btnIniciar;
    private JButton btnIniciar1;
    private JTextField campoAltura;
    private JTextField campoIPServidor;
    private JTextField campoPeso;
    private JTextField campoPuertoServidor;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JPanel jPanel1;
    private JPanel jPanel3;
    private JTabbedPane jTabbedPane1;
    private JLabel txtEstado;
    private JLabel txtMensaje;
    private JLabel txtResultado;

    public VentanaPrincipal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new JLabel();
        jTabbedPane1 = new JTabbedPane();
        jPanel1 = new JPanel();
        jLabel2 = new JLabel();
        campoIPServidor = new JTextField();
        jLabel3 = new JLabel();
        campoPuertoServidor = new JTextField();
        btnIniciar = new JButton();
        jLabel4 = new JLabel();
        txtEstado = new JLabel();
        jPanel3 = new JPanel();
        jLabel5 = new JLabel();
        campoPeso = new JTextField();
        jLabel6 = new JLabel();
        campoAltura = new JTextField();
        btnIniciar1 = new JButton();
        jLabel7 = new JLabel();
        txtResultado = new JLabel();
        txtMensaje = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cliente Descuento de Compras");

        jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("CLIENTE DESCUENTO");

        jLabel2.setText("DIRECCION IP: ");
        campoIPServidor.setText("localhost");

        jLabel3.setText("PUERTO DE RED:");
        campoPuertoServidor.setText("9007");

        btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnIniciar.setForeground(new Color(0, 153, 51));
        btnIniciar.setText("Conectar");
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        jLabel4.setText("ESTADO: ");
        txtEstado.setForeground(new Color(255, 0, 51));
        txtEstado.setText("Desconectado");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoIPServidor, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoPuertoServidor))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEstado, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnIniciar, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(campoIPServidor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(campoPuertoServidor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEstado))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(btnIniciar)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("CONEXION", jPanel1);

        jLabel5.setText("PRECIO ORIGINAL ($):");
        jLabel6.setText("DESCUENTO (%):");

        btnIniciar1.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnIniciar1.setForeground(new Color(0, 153, 51));
        btnIniciar1.setText("CALCULAR");
        btnIniciar1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnIniciar1ActionPerformed(evt);
            }
        });

        jLabel7.setText("DESC: ");

        txtResultado.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtResultado.setForeground(new Color(255, 0, 51));
        txtResultado.setText("$0.00");

        txtMensaje.setBorder(BorderFactory.createTitledBorder(""));

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtResultado, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMensaje, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoAltura, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoPeso, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29)
                        .addComponent(btnIniciar1, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(campoPeso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(campoAltura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnIniciar1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtResultado, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMensaje, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CALCULAR DESCUENTO", jPanel3);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 437, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(jTabbedPane1, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnIniciarActionPerformed(ActionEvent evt) {
        String rawIp = campoIPServidor.getText().trim();
        if (rawIp.contains(":")) {
            String[] parts = rawIp.split(":");
            rawIp = parts[0];
            campoPuertoServidor.setText(parts[1]);
            campoIPServidor.setText(rawIp);
        }
        int puerto;
        try {
            puerto = Integer.parseInt(campoPuertoServidor.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El puerto debe ser un numero valido.");
            return;
        }

        if (btnIniciar.getText().equalsIgnoreCase("Conectar")) {
            try {
                direccionServidor = InetAddress.getByName(rawIp);
                puertoServidor = puerto;
                socketUdp = new DatagramSocket();
                socketUdp.setSoTimeout(5000);
                conectado = true;
                btnIniciar.setText("Desconectar");
                btnIniciar.setForeground(Color.RED);
                txtEstado.setText("Conectado");
                txtEstado.setForeground(Color.GREEN);
            } catch (IOException ex) {
                System.out.println("ERROR AL CONECTAR");
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "ERROR AL CONECTAR: " + ex.getMessage());
            }
        } else if (btnIniciar.getText().equalsIgnoreCase("Desconectar")) {
            if (socketUdp != null && !socketUdp.isClosed()) {
                socketUdp.close();
            }
            conectado = false;
            btnIniciar.setText("Conectar");
            txtEstado.setText("Desconectado");
            btnIniciar.setForeground(Color.GREEN);
            txtEstado.setForeground(Color.RED);
        }
    }

    private void btnIniciar1ActionPerformed(ActionEvent evt) {
        if (!conectado || socketUdp == null || socketUdp.isClosed()) {
            JOptionPane.showMessageDialog(this, "Cliente Offline, Conecte con el Servidor");
            return;
        }
        try {
            float precioOriginal = Float.parseFloat(campoPeso.getText().trim());
            float porcentajeDescuento = Float.parseFloat(campoAltura.getText().trim());
            Thread hilo = new Thread() {
                @Override
                public void run() {
                    try {
                        System.out.println("Precio Original: $" + precioOriginal);
                        System.out.println("Porcentaje Descuento: " + porcentajeDescuento + "%");

                        ByteBuffer bbEnvio = ByteBuffer.allocate(8);
                        bbEnvio.putFloat(precioOriginal);
                        bbEnvio.putFloat(porcentajeDescuento);
                        byte[] datosEnvio = bbEnvio.array();

                        DatagramPacket paqueteEnvio = new DatagramPacket(
                                datosEnvio, datosEnvio.length, direccionServidor, puertoServidor);
                        socketUdp.send(paqueteEnvio);
                        System.out.println("Datos enviados. Esperando respuesta...");

                        byte[] bufferRespuesta = new byte[8];
                        DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);
                        socketUdp.receive(paqueteRespuesta);

                        ByteBuffer bbRespuesta = ByteBuffer.wrap(paqueteRespuesta.getData());
                        float montoDescuento = bbRespuesta.getFloat();
                        float precioFinal = bbRespuesta.getFloat();

                        System.out.println("Monto Descuento: $" + montoDescuento + "\nPrecio Final: $" + precioFinal);
                        txtResultado.setText(String.format("$%.2f", montoDescuento));
                        txtMensaje.setText(String.format("Precio Final: $%.2f", precioFinal));
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(VentanaPrincipal.this, "ERROR con el cliente " + ex.getMessage());
                        System.out.println("ERROR con el cliente " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            };
            hilo.start();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese valores numericos validos para el precio original y el porcentaje de descuento.");
        }
    }

    public JLabel getTxtEstado() {
        return txtEstado;
    }

    public JButton getBtnIniciar() {
        return btnIniciar;
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }
}