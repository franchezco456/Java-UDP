package frankyfranco.descuento.application.port.out;

/**
 * Puerto de Salida (Driven Port) para desacoplar el registro de eventos y logs.
 */
public interface NotificadorLogPort {
    void registrarLog(String mensaje);
}