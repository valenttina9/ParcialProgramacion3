package programacion3.Parcial.model.pago;

import java.util.Locale;

public class BilleteraVirtualProcesador implements ProcesadorPago {

    @Override
    public String cobrar(double monto) {
        return "Cobro exitoso de $" + String.format(Locale.US, "%.2f", monto)
                + " realizado con Billetera Virtual";
    }
}
