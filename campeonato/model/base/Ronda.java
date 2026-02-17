package campeonato.model.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Representa una fase del torneo.
 * Gestiona el estado de sus partidos y la progresión de los ganadores.
 */
public class Ronda<T extends ParticipanteAbstracto> implements Comparable<Ronda<T>> {

    private final int numero;
    private final String titulo;
    private final List<Partido<T>> partidos;
    private final boolean esFinal;
    private boolean terminada;
    private final List<T> ganadores;

    public Ronda(int numero, String titulo, List<Partido<T>> partidos, boolean esFinal) {
        this.numero = numero;
        this.titulo = Objects.requireNonNull(titulo, "El título no puede ser nulo");
        this.partidos = (partidos != null) ? partidos : new ArrayList<>();
        this.esFinal = esFinal;
        this.ganadores = new ArrayList<>();
        this.terminada = false;
    }

    /**
     * Finaliza la ronda si todos los partidos tienen un ganador.
     * Es idempotente: puede llamarse varias veces sin duplicar ganadores.
     */
    public boolean terminarRonda() {
        if (partidos.isEmpty()) return false;

        // Verificar que todos los partidos tengan resultado
        boolean todosFinalizados = partidos.stream().allMatch(Partido::tieneGanador);

        if (todosFinalizados) {
            this.terminada = true;
            extraerGanadores();
            return true;
        }
        
        return false;
    }

    private void extraerGanadores() {
        ganadores.clear(); // Vital para evitar duplicados en llamadas sucesivas
        for (Partido<T> partido : partidos) {
            ganadores.add(partido.getGanador());
        }
    }

    /**
     * Devuelve una vista inmutable de los ganadores para proteger el estado interno.
     */
    public List<T> getGanadores() {
        if (!terminada) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(ganadores);
    }

    @Override
    public int compareTo(Ronda<T> o) {
        return Integer.compare(this.numero, o.numero);
    }

    // Getters esenciales
    public int getNumero() { return numero; }
    public String getTitulo() { return titulo; }
    public List<Partido<T>> getPartidos() { return partidos; }
    public boolean isEsFinal() { return esFinal; }
    public boolean isTerminada() { return terminada; }

    @Override
    public String toString() {
        return "Ronda %d: %s [Partidos: %d, Finalizada: %b]".formatted(numero, titulo, partidos.size(), terminada);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ronda<?> ronda)) return false;
        return numero == ronda.numero && Objects.equals(titulo, ronda.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, titulo);
    }
}