package unlar.programacion3.examenfinal.interfaces;

import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class ProcesadorStrategy {

    private Map<String, CalculoMulta> estrategias;

    public ProcesadorStrategy() {
        estrategias = new HashMap<>();
        estrategias.put("Compania", new MultaCampania());
        estrategias.put("Normal", new MultaNormal());
        estrategias.put("FinDeSemana", new MultaFinDeSemana());
    }

    // resuelve la estrategia de multa a aplicar según el tipo (Normal, FinDeSemana, Compania)
    public double calcularMulta(String tipoEstrategia, int diasRetraso, boolean premium) {
        CalculoMulta estrategia;
        if (estrategias.containsKey(tipoEstrategia)) {
            estrategia = estrategias.get(tipoEstrategia);
        } else {
            estrategia = estrategias.get("Normal");
        }
        return estrategia.calcularMulta(diasRetraso, premium);
    }

    }

