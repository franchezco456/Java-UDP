@echo off
echo Compilando Servidor Descuento (Arquitectura Hexagonal)...
if not exist bin mkdir bin
javac -encoding UTF-8 -d bin src\frankyfranco\descuento\domain\model\*.java src\frankyfranco\descuento\domain\service\*.java src\frankyfranco\descuento\application\port\in\*.java src\frankyfranco\descuento\application\port\out\*.java src\frankyfranco\descuento\application\usecase\*.java src\frankyfranco\descuento\infrastructure\adapter\in\udp\*.java src\frankyfranco\descuento\infrastructure\adapter\in\ui\*.java src\frankyfranco\descuento\infrastructure\adapter\out\logging\*.java src\frankyfranco\descuento\Principal.java
if %errorlevel% equ 0 (
    echo Compilacion exitosa.
) else (
    echo Error durante la compilacion.
)
pause