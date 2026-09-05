package unlar.programacion3.examenfinal.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import unlar.programacion3.examenfinal.model.Libro;
import unlar.programacion3.examenfinal.model.Materiales;
import unlar.programacion3.examenfinal.model.Revista;

@Repository
public class MaterialRepository {

    private final List<Materiales> materiales = new ArrayList<>();

    public MaterialRepository() {
        materiales.add(new Libro("L001", "Cien Años de Soledad", true, "Gabriel García Márquez"));
        materiales.add(new Libro("L002", "El Aleph", true, "Jorge Luis Borges"));
        materiales.add(new Libro("L003", "Rayuela", true, "Julio Cortázar"));
        materiales.add(new Revista("R001", "National Geographic", true, 305));
        materiales.add(new Revista("R002", "Muy Interesante", true, 412));
    }

    public List<Materiales> getMateriales() {
        return materiales;
    }
}
