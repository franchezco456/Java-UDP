package frankyfranco.descuento.infrastructure.adapter.out.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import frankyfranco.descuento.application.port.out.NotificadorLogPort;

/**
 * Adaptador Secundario (Driven Adapter) que implementa NotificadorLogPort 
 * para enviar los registros tanto a consola como a la interfaz gráfica Swing.
 */
public class SwingLogAdapter implements NotificadorLogPort {

    private final JTextArea cajaLog;

    public SwingLogAdapter(JTextArea cajaLog) {
        this.cajaLog = cajaLog;
    }

    @Override
    public void registrarLog(String mensaje) {
        String fecha = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a").format(new Date());
        String logCompleto = fecha + " - " + mensaje;
        
        System.out.println(logCompleto);

        if (cajaLog != null) {
            SwingUtilities.invokeLater(() -> cajaLog.append(logCompleto + "\n"));
        }
    }
}