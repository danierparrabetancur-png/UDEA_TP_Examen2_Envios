package servicios;

import java.util.ArrayList;
import java.util.List;

import modelos.Envio;

public class EnvioServicio {

    private final List<Envio> envios = new ArrayList<>();

    // Retorna false si ya existe un envío con ese código
    public boolean agregar(Envio envio) {
        for (var e : envios) {
            if (e.getCodigo().equals(envio.getCodigo())) {
                return false;
            }
        }
        return envios.add(envio);
    }

    // Retorna false si el código no existe
    public boolean retirar(String codigo) {
        return envios.removeIf(e -> e.getCodigo().equals(codigo));
    }

    public List<Envio> getEnvios() {
        return envios;
    }
}
