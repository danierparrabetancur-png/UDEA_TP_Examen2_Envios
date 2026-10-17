package fabricas;

import modelos.Envio;

// Factory Method: cada fábrica concreta decide qué tipo de Envio crear
public abstract class EnvioFactory {

    public abstract Envio crear(String codigo, String cliente, double peso, double distancia);
}
