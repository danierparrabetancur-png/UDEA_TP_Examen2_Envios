package controladores;

import java.util.Map;

import fabricas.EnvioFactory;
import modelos.Envio;
import servicios.EnvioServicio;
import vistas.EnvioVista;

public class EnvioControlador {

    private final EnvioVista vista;
    private final EnvioServicio servicio;
    // Tipo de envío -> fábrica que lo crea
    private final Map<String, EnvioFactory> fabricas;

    public EnvioControlador(EnvioVista vista, EnvioServicio servicio, Map<String, EnvioFactory> fabricas) {
        this.vista = vista;
        this.servicio = servicio;
        this.fabricas = fabricas;

        this.vista.cargarTipos(fabricas.keySet());
        this.vista.setAgregarClick(evento -> vista.habilitarFormulario(true));
        this.vista.setGuardarClick(evento -> guardar());
        this.vista.setCancelarClick(evento -> cancelar());
        this.vista.setRetirarClick(evento -> retirar());
        this.vista.mostrarEnvios(servicio.getEnvios());
    }

    private void guardar() {
        String codigo = vista.getCodigo().trim();
        String cliente = vista.getCliente().trim();
        EnvioFactory fabrica = fabricas.get(vista.getTipoSeleccionado());

        if (codigo.isEmpty() || cliente.isEmpty() || fabrica == null) {
            vista.mostrarError("Debe ingresar tipo, código y cliente.");
            return;
        }

        double peso, distancia;
        try {
            peso = Double.parseDouble(vista.getPeso().trim());
            distancia = Double.parseDouble(vista.getDistancia().trim());
        } catch (NumberFormatException ex) {
            vista.mostrarError("El peso y la distancia deben ser números.");
            return;
        }
        if (peso <= 0 || distancia <= 0) {
            vista.mostrarError("El peso y la distancia deben ser mayores que cero.");
            return;
        }

        Envio envio = fabrica.crear(codigo, cliente, peso, distancia);
        if (servicio.agregar(envio)) {
            vista.mostrarEnvios(servicio.getEnvios());
            cancelar();
        } else {
            vista.mostrarError("Ya existe un envío con el código " + codigo + ".");
        }
    }

    private void retirar() {
        String codigo = vista.solicitarCodigo();
        if (codigo == null) {
            return; // el usuario canceló
        }
        codigo = codigo.trim();
        if (servicio.retirar(codigo)) {
            vista.mostrarEnvios(servicio.getEnvios());
        } else {
            vista.mostrarMensaje("El código " + codigo + " no existe.");
        }
    }

    private void cancelar() {
        vista.limpiarFormulario();
        vista.habilitarFormulario(false);
    }
}
