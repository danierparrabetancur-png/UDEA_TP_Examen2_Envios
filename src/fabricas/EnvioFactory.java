package fabricas;

import modelos.Envio;

// Factory Method
public abstract class EnvioFactory {

    public abstract Envio crear(String codigo, String cliente, double peso, double distancia);
}
