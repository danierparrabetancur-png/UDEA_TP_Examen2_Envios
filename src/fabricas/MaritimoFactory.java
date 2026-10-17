package fabricas;

import modelos.Envio;
import modelos.Maritimo;

public class MaritimoFactory extends EnvioFactory {

    @Override
    public Envio crear(String codigo, String cliente, double peso, double distancia) {
        return new Maritimo(codigo, cliente, peso, distancia);
    }
}
