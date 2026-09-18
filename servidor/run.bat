@echo off
echo Iniciando Servidor Descuento...
if not exist bin (
    call compile.bat
)
java -cp bin frankyfranco.descuento.Principal
