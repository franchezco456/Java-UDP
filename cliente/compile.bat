@echo off
echo Compilando Cliente Descuento (Arquitectura Hexagonal)...
if not exist bin mkdir bin
javac -encoding UTF-8 -d bin src\frankyfranco\descuento\cliente\domain\model\*.java src\frankyfranco\descuento\cliente\application\port\in\*.java src\frankyfranco\descuento\cliente\application\port\out\*.java src\frankyfranco\descuento\cliente\application\usecase\*.java src\frankyfranco\descuento\cliente\infrastructure\adapter\out\udp\*.java src\frankyfranco\descuento\cliente\infrastructure\adapter\in\ui\*.java src\frankyfranco\descuento\cliente\Principal.java
if %errorlevel% equ 0 (
    echo Compilacion exitosa.
) else (
    echo Error durante la compilacion.
)
pause