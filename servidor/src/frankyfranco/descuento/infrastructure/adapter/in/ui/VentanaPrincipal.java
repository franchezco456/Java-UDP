package frankyfranco.descuento.infrastructure.adapter.in.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import frankyfranco.descuento.application.port.in.CalcularDescuentoUseCase;
import frankyfranco.descuento.application.port.in.ControlServidorUseCase;
import frankyfranco.descuento.application.usecase.CalcularDescuentoUseCaseImpl;
import frankyfranco.descuento.domain.service.CalculoDescuentoService;
import frankyfranco.descuento.infrastructure.adapter.in.udp.ServidorUdpAdapter;
import frankyfranco.descuento.infrastructure.adapter.out.logging.SwingLogAdapter;

/**
 * Adaptador Primario (Driving Adapter) de Interfaz Gráfica para el Servidor.
 * No contiene lógica de negocio ni sockets directamente; delega en ControlServidorUseCase.
 */
public class VentanaPrincipal extends JFrame {

    private ControlServidorUseCase controlServidor;

    // Variables declaration
    private JButton btnIniciar;
    private JButton btnLimpiar;
    private JTextArea cajaLog;
    private JTextField campoIP;
    private JTextField campoPuerto;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane1;
    private JTabbedPane jTabbedPane1;
    private JLabel txtEstado;

    public VentanaPrincipal() {
        initComponents();
    }

    public void inicializarServidor() {
        // Ensamble hexagonal: adaptadores <-> puertos <-> casos de uso
        SwingLogAdapter logAdapter = new SwingLogAdapter(cajaLog);
        CalculoDescuentoService domainService = new CalculoDescuentoService();
        CalcularDescuentoUseCase calcularUseCase = new CalcularDescuentoUseCaseImpl(domainService, logAdapter);
        this.controlServidor = new ServidorUdpAdapter(calcularUseCase, logAdapter);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new JLabel();
        jTabbedPane1 = new JTabbedPane();
        jPanel1 = new JPanel();
        jLabel2 = new JLabel();
        campoIP = new JTextField();
        jLabel3 = new JLabel();
        campoPuerto = new JTextField();
        btnIniciar = new JButton();
        jLabel4 = new JLabel();
        txtEstado = new JLabel();
        jPanel2 = new JPanel();
        jScrollPane1 = new JScrollPane();
        cajaLog = new JTextArea();
        btnLimpiar = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Servidor Descuento de Compras - Arquitectura Hexagonal");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("SERVIDOR DESCUENTO");

        jLabel2.setText("DIRECCION IP: ");
        jLabel3.setText("PUERTO DE RED:");
        campoPuerto.setText("9007");

        btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnIniciar.setForeground(new Color(0, 153, 51));
        btnIniciar.setText("INICIAR");
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        jLabel4.setText("ESTADO: ");
        txtEstado.setForeground(new Color(255, 0, 51));
        txtEstado.setText("DETENIDO");

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
                        .addComponent(campoIP, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoPuerto))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEstado, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnIniciar, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(campoIP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(campoPuerto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEstado))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(btnIniciar)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("CONEXION", jPanel1);

        cajaLog.setEditable(false);
        cajaLog.setColumns(20);
        cajaLog.setRows(5);
        jScrollPane1.setViewportView(cajaLog);

        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        jTabbedPane1.addTab("LOG DE CONEXIONES", jPanel2);

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
        if (btnIniciar.getText().equalsIgnoreCase("INICIAR")) {
            try {
                int puerto = Integer.parseInt(campoPuerto.getText().trim());
                inicializarServidor();
                controlServidor.iniciar(puerto);
                btnIniciar.setText("DETENER");
                btnIniciar.setForeground(Color.RED);
                txtEstado.setText("ONLINE");
                txtEstado.setForeground(Color.GREEN);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al iniciar servidor: " + ex.getMessage());
            }
        } else if (btnIniciar.getText().equalsIgnoreCase("DETENER")) {
            if (controlServidor != null) {
                controlServidor.detener();
            }
            btnIniciar.setText("INICIAR");
            btnIniciar.setForeground(new Color(0, 153, 51));
            txtEstado.setText("OFF LINE");
            txtEstado.setForeground(Color.RED);
        }
    }

    private void btnLimpiarActionPerformed(ActionEvent evt) {
        cajaLog.setText("");
    }

    private void formWindowOpened(WindowEvent evt) {
        try {
            campoIP.setEditable(false);
            String ip = InetAddress.getLocalHost().getHostAddress();
            campoIP.setText(ip);
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(this, "Falla en la conexion");
        }
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

        java.awt.EventQueue.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}