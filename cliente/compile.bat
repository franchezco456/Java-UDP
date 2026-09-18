@echo off
echo Compilando Cliente Descuento...
if not exist bin mkdir bin
javac -encoding UTF-8 -d bin src\frankyfranco\descuento\cliente\vistas\VentanaPrincipal.java src\frankyfranco\descuento\cliente\Principal.java
if %errorlevel% equ 0 (
    echo Compilacion exitosa.
) else (
    echo Error durante la compilacion.
)
pause
