package fabricas;

import modelos.Envio;
import modelos.Aereo;

public class AereoFactory extends EnvioFactory {

    @Override
    public Envio crear(String codigo, String cliente, double peso, double distancia) {
        return new Aereo(codigo, cliente, peso, distancia);
    }
}
