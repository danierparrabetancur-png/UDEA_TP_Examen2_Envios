import java.util.LinkedHashMap;
import java.util.Map;

import controladores.EnvioControlador;
import fabricas.AereoFactory;
import fabricas.EnvioFactory;
import fabricas.MaritimoFactory;
import fabricas.TerrestreFactory;
import servicios.EnvioServicio;
import vistas.EnvioVista;

public class Logistica {

    public static void main(String[] args) {
        // Fábricas disponibles. Para agregar Express solo se añade una línea:
        // fabricas.put("Express", new ExpressFactory());
        Map<String, EnvioFactory> fabricas = new LinkedHashMap<>();
        fabricas.put("Terrestre", new TerrestreFactory());
        fabricas.put("Aéreo", new AereoFactory());
        fabricas.put("Marítimo", new MaritimoFactory());

        EnvioVista vista = new EnvioVista();
        new EnvioControlador(vista, new EnvioServicio(), fabricas);
        vista.setVisible(true);
    }
}
