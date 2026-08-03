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

    public double calcularMulta(CalculoMulta estrategia, int diasRetraso, boolean premium) {
        return estrategia.calcularMulta(diasRetraso, premium);
    }
    
    }

