@echo off
echo Iniciando Cliente Descuento...
if not exist bin (
    call compile.bat
)
java -cp bin frankyfranco.descuento.cliente.Principal
