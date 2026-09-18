@echo off
echo Compilando Servidor Descuento...
if not exist bin mkdir bin
javac -encoding UTF-8 -d bin src\frankyfranco\descuento\modelo\CalculoDescuento.java src\frankyfranco\descuento\servidor\ServidorUdp.java src\frankyfranco\descuento\servidor\SubProcesoCliente.java src\frankyfranco\descuento\vistas\VentanaPrincipal.java src\frankyfranco\descuento\Principal.java
if %errorlevel% equ 0 (
    echo Compilacion exitosa.
) else (
    echo Error durante la compilacion.
)
pause
