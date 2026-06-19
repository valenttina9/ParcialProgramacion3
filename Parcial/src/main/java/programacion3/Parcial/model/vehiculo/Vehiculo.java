package programacion3.Parcial.model.vehiculo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import programacion3.Parcial.model.vehiculo.estado.EnEsperaState;
import programacion3.Parcial.model.vehiculo.estado.EstadoVehiculo;

public abstract class Vehiculo implements Comparable<Vehiculo> {

    private String patente;
    private int porcentajeBateria;
    private double tarifaBase;
    @JsonIgnore
    private EstadoVehiculo estado;

    protected Vehiculo() {
        this.estado = new EnEsperaState();
    }

    protected Vehiculo(String patente, int porcentajeBateria, double tarifaBase) {
        this.patente = patente;
        this.porcentajeBateria = porcentajeBateria;
        this.tarifaBase = tarifaBase;
        this.estado = new EnEsperaState();
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getPorcentajeBateria() {
        return porcentajeBateria;
    }

    public void setPorcentajeBateria(int porcentajeBateria) {
        this.porcentajeBateria = porcentajeBateria;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public String getEstadoActual() {
        return estado.getNombre();
    }

    public boolean puedeSerAlquilado() {
        return estado.puedeSerAlquilado();
    }

    public void iniciarViaje() {
        estado.iniciarViaje(this);
    }

    public void finalizarViaje() {
        estado.finalizarViaje(this);
    }

    public void enviarAReparacion() {
        estado.enviarAReparacion(this);
    }

    public void pasarAEspera() {
        estado.pasarAEspera(this);
    }

    public boolean tieneBateriaSuficiente() {
        return porcentajeBateria >= 15;
    }

    public void cambiarEstado(EstadoVehiculo nuevoEstado) {
        this.estado = nuevoEstado;
    }

    @Override
    public int compareTo(Vehiculo otro) {
        int comparacionPorBateria = Integer.compare(this.porcentajeBateria, otro.porcentajeBateria);
        if (comparacionPorBateria != 0) {
            return comparacionPorBateria;
        }
        return this.patente.compareToIgnoreCase(otro.patente);
    }

    public abstract String getTipo();
}
