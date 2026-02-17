package campeonato.model.base;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Representa la entidad global del torneo.
 * Mantiene la lista de inscritos y el histórico de rondas generadas.
 */
public class Torneo<T extends ParticipanteAbstracto> {

    private final List<T> participantes;
    private final SortedSet<Ronda<T>> rondas;

    public Torneo(List<T> participantes) {
        this.participantes = (participantes != null) ? participantes : Collections.emptyList();
        this.rondas = new TreeSet<>();
    }

    /**
     * Devuelve el campeón del torneo si la gran final ha terminado.
     */
    public Optional<T> getCampeon() {
        if (!rondas.isEmpty()) {
            Ronda<T> ultima = rondas.last();
            if (ultima.isEsFinal() && ultima.isTerminada() && !ultima.getGanadores().isEmpty()) {
                return Optional.of(ultima.getGanadores().get(0));
            }
        }
        return Optional.empty();
    }

    /**
     * Genera una representación visual de los datos de una ronda.
     * He movido la lógica de System.out a un StringBuilder para que sea más versátil.
     */
    public String obtenerResumenRonda(Ronda<T> ronda) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== RONDA Nº %d: %s ===\n".formatted(ronda.getNumero(), ronda.getTitulo().toUpperCase()));
        
        for (Partido<T> partido : ronda.getPartidos()) {
            sb.append("  [PARTIDO] %s vs %s".formatted(
                partido.getParticipante1().getNombre(), 
                partido.getParticipante2().getNombre()));
            
            if (partido.tieneGanador()) {
                sb.append(" -> GANADOR: %s".formatted(partido.getGanador().getNombre()));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void mostrarUltimaRonda() {
        if (rondas.isEmpty()) {
            System.out.println("Torneo sin rondas iniciadas.");
        } else {
            System.out.println(obtenerResumenRonda(rondas.last()));
        }
    }

    public void mostrarHistoricoCompleto() {
        if (rondas.isEmpty()) {
            System.out.println("No hay datos históricos.");
        } else {
            rondas.forEach(r -> System.out.println(obtenerResumenRonda(r)));
        }
    }

    // Getters
    public List<T> getParticipantes() { return participantes; }
    public SortedSet<Ronda<T>> getRondas() { return rondas; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Torneo<?> torneo)) return false;
        return Objects.equals(participantes, torneo.participantes) && 
               Objects.equals(rondas, torneo.rondas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participantes, rondas);
    }

    @Override
    public String toString() {
        return "Torneo con %d participantes y %d rondas jugadas.".formatted(participantes.size(), rondas.size());
    }
}