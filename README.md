# Sistema Distribuido de Cálculo de Descuento de Compras con Socket TCP en Java (Ejercicio 17)

Este repositorio contiene la implementación completa y separada del sistema distribuido para el cálculo del precio final y monto descontado de una compra, desarrollado mediante Sockets TCP en Java bajo el paquete **`frankyfranco.descuento`**.

El proyecto se encuentra dividido en 2 aplicaciones independientes:
1. **`servidor/`**: Servidor multihilo TCP con interfaz Swing para control de estado, puerto de red y registro de logs de conexiones y peticiones recibidas.
2. **`cliente/`**: Cliente TCP con interfaz Swing para conectarse al servidor, enviar el precio original del producto y porcentaje de descuento, y visualizar el monto descontado y el precio final de la compra.

---

## 📁 Estructura del Repositorio

```
ServidorTCP/
├── servidor/
│   ├── src/
│   │   └── frankyfranco/
│   │       └── descuento/
│   │           ├── Principal.java                       # Clase principal que lanza la GUI del servidor
│   │           ├── modelo/
│   │           │   └── CalculoDescuento.java            # Modelo de datos y lógica del cálculo de descuento
│   │           ├── servidor/
│   │           │   ├── ServidorTcp.java                 # Hilo de gestión del ServerSocket
│   │           │   └── SubProcesoCliente.java           # Hilo individual para atender a cada cliente conectado
│   │           └── vistas/
│   │               └── VentanaPrincipal.java            # Formulario Swing del servidor (Conexión y Log)
│   ├── bin/                                             # Clases compiladas (.class)
│   ├── compile.bat                                      # Script para compilar el servidor
│   └── run.bat                                          # Script para ejecutar el servidor
│
├── cliente/
│   ├── src/
│   │   └── frankyfranco/
│   │       └── descuento/
│   │           └── cliente/
│   │               ├── Principal.java                   # Clase principal que lanza la GUI del cliente
│   │               └── vistas/
│   │                   └── VentanaPrincipal.java        # Formulario Swing del cliente (Conexión y Calcular Descuento)
│   ├── bin/                                             # Clases compiladas (.class)
│   ├── compile.bat                                      # Script para compilar el cliente
│   └── run.bat                                          # Script para ejecutar el cliente
│
└── README.md
```

---

## ⚙️ Requisitos

- **Java JDK 8 o superior** (OpenJDK / Oracle JDK).

---

## 🚀 Instrucciones de Ejecución

### 1. Iniciar el Servidor

1. Abrir una terminal en la carpeta `servidor/` o hacer doble clic en `servidor/run.bat`:
   ```bash
   cd servidor
   javac -encoding UTF-8 -d bin src/frankyfranco/descuento/modelo/CalculoDescuento.java src/frankyfranco/descuento/servidor/SubProcesoCliente.java src/frankyfranco/descuento/servidor/ServidorTcp.java src/frankyfranco/descuento/vistas/VentanaPrincipal.java src/frankyfranco/descuento/Principal.java
   java -cp bin frankyfranco.descuento.Principal
   ```
2. En la ventana del **Servidor Descuento de Compras**:
   - Verifique el puerto de red (por defecto `9007`).
   - Haga clic en el botón **INICIAR**.
   - El estado cambiará a **ONLINE** en color verde y el servidor quedará a la escucha.

### 2. Iniciar el Cliente

1. Abrir otra terminal en la carpeta `cliente/` o hacer doble clic en `cliente/run.bat`:
   ```bash
   cd cliente
   javac -encoding UTF-8 -d bin src/frankyfranco/descuento/cliente/vistas/VentanaPrincipal.java src/frankyfranco/descuento/cliente/Principal.java
   java -cp bin frankyfranco.descuento.cliente.Principal
   ```
2. En la ventana del **Cliente Descuento de Compras**:
   - En la pestaña **CONEXION**: ingrese la IP del servidor (`localhost` o IP local) y el puerto (`9007`).
   - Haga clic en **Conectar**. El estado cambiará a **Conectado**.
   - Vaya a la pestaña **CALCULAR DESCUENTO**: ingrese el **PRECIO ORIGINAL ($)** (ej: `100.00`) y el **DESCUENTO (%)** (ej: `20`).
   - Haga clic en **CALCULAR**.
   - Se mostrará el **Monto Descontado ($)** (ej: `$20.00`) y el **Precio Final ($)** (ej: `Precio Final: $80.00`) calculados por el servidor.
   - En el servidor, podrá consultar el historial de operaciones en la pestaña **LOG DE CONEXIONES**.

---

## 📊 Ejercicio 17: Fórmulas de Cálculo

| Concepto | Fórmula |
|---|---|
| Dinero Descontado | `montoDescuento = precioOriginal * (porcentajeDescuento / 100)` |
| Precio Final | `precioFinal = precioOriginal - montoDescuento` |
