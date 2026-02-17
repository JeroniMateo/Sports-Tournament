package campeonato.model.base;

import java.util.Objects;

/**
 * Clase base para cualquier entidad que compita en el torneo.
 * Se define como abstracta para evitar instancias genéricas sin especialización.
 */
public abstract class ParticipanteAbstracto {

    private final String nombre; // Final: el nombre no debería cambiar tras la creación

    /**
     * Constructor obligatorio. 
     * @param nombre Nombre del participante (no puede ser nulo o vacío).
     */
    protected ParticipanteAbstracto(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del participante no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public String getNombre() {
        return nombre;
    }

    // Eliminamos el setter de nombre para mantener la integridad de los datos del torneo

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // Verificación de clase estricta
        if (o == null || getClass() != o.getClass()) return false;
        ParticipanteAbstracto that = (ParticipanteAbstracto) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "%s{nombre='%s'}".formatted(getClass().getSimpleName(), nombre);
    }
}