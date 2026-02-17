package campeonato.model.base;

import java.util.Objects;

/**
 * Representa un enfrentamiento entre dos participantes.
 * Garantiza que el resultado sea consistente con los jugadores inscritos.
 */
public class Partido<T extends ParticipanteAbstracto> {

    private final T participante1;
    private final T participante2;
    private T ganador;
    private T perdedor;

    /**
     * Constructor principal. Un partido nace sin resultado definido.
     */
    public Partido(T participante1, T participante2) {
        this.participante1 = Objects.requireNonNull(participante1, "El participante 1 no puede ser nulo");
        this.participante2 = Objects.requireNonNull(participante2, "El participante 2 no puede ser nulo");
        
        if (participante1.equals(participante2)) {
            throw new IllegalArgumentException("Un participante no puede jugar contra sí mismo");
        }
    }

    /**
     * Establece el ganador del partido y actualiza automáticamente el perdedor.
     * @param ganador Debe ser participante1 o participante2.
     */
    public void setGanador(T ganador) {
        if (ganador == null) {
            this.ganador = null;
            this.perdedor = null;
            return;
        }

        if (ganador.equals(participante1)) {
            this.ganador = participante1;
            this.perdedor = participante2;
        } else if (ganador.equals(participante2)) {
            this.ganador = participante2;
            this.perdedor = participante1;
        } else {
            throw new IllegalArgumentException("El ganador debe ser uno de los participantes del partido.");
        }
    }

    // Getters
    public T getParticipante1() { return participante1; }
    public T getParticipante2() { return participante2; }
    public T getGanador() { return ganador; }
    public T getPerdedor() { return perdedor; }

    /**
     * Determina si el partido ya tiene un resultado asignado.
     */
    public boolean tieneGanador() {
        return ganador != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Partido<?> partido)) return false;
        return Objects.equals(participante1, partido.participante1) && 
               Objects.equals(participante2, partido.participante2) && 
               Objects.equals(ganador, partido.ganador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participante1, participante2, ganador);
    }

    @Override
    public String toString() {
        String resultado = tieneGanador() ? " -> Ganador: " + ganador.getNombre() : " (Pendiente)";
        return "[%s vs %s]%s".formatted(participante1.getNombre(), participante2.getNombre(), resultado);
    }
}