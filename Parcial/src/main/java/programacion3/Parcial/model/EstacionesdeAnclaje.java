package programacion3.Parcial.model;
import java.util.List;
import lombok.Data;

@Data
public class EstacionesdeAnclaje {
    private String nombre;
    private List<Vehiculo> vehiculos;
}
