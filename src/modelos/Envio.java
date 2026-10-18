package modelos;

public abstract class Envio {
    private String codigo;
    private String cliente;
    private double peso;
    private double distancia;

    public Envio(String codigo, String cliente, double peso, double distancia) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.peso = peso;
        this.distancia = distancia;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCliente() {
        return cliente;
    }

    public double getPeso() {
        return peso;
    }

    public double getDistancia() {
        return distancia;
    }

    // Nombre del medio de transporte 
    public abstract String getTipo();

    // Cada tipo de envío calcula su tarifa 
    public abstract double calcularTarifa();

    // para no repetir código en las subclases
    protected double calcularTarifa(double tarifaBasePorKm, double recargoPorKg) {
        return distancia * tarifaBasePorKm + peso * recargoPorKg;
    }
}
