package programacion3.Parcial.factory;

import org.springframework.stereotype.Component;
import programacion3.Parcial.exception.MetodoPagoNoSoportadoException;
import programacion3.Parcial.model.pago.BilleteraVirtualProcesador;
import programacion3.Parcial.model.pago.ProcesadorPago;
import programacion3.Parcial.model.pago.TarjetaCreditoProcesador;

@Component
public class ProcesadorPagoFactory {

    public ProcesadorPago crear(String metodoPago) {
        String valorNormalizado = metodoPago == null ? "" : metodoPago.trim().toUpperCase();

        return switch (valorNormalizado) {
            case "TARJETA", "TARJETA_CREDITO" -> new TarjetaCreditoProcesador();
            case "BILLETERA", "BILLETERA_VIRTUAL" -> new BilleteraVirtualProcesador();
            default -> throw new MetodoPagoNoSoportadoException("Metodo de pago no soportado: " + metodoPago);
        };
    }
}
