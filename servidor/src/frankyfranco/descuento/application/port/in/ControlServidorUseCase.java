package frankyfranco.descuento.application.port.in;

/**
 * Puerto de Entrada (Driving Port) para el ciclo de vida del servidor.
 */
public interface ControlServidorUseCase {
    void iniciar(int puerto) throws Exception;
    void detener();
    boolean estaActivo();
}