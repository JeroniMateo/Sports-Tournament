package campeonato.model.service;

import campeonato.model.base.ParticipanteAbstracto;
import campeonato.model.base.Partido;
import campeonato.model.base.Ronda;
import campeonato.model.base.Torneo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Servicio encargado de la orquestación lógica del torneo.
 * Gestiona emparejamientos, sorteos y la progresión de rondas.
 */
public class ServicioRonda<T extends ParticipanteAbstracto> {

    private int rondasContador;
    private final Torneo<T> torneo;
    private final List<T> exentos; // Jugadores que pasan de ronda sin jugar (Bye)
    private final Random random;

    public ServicioRonda(Torneo<T> torneo) {
        this.torneo = torneo;
        this.exentos = new ArrayList<>();
        this.rondasContador = 0;
        this.random = new Random();
    }

    /**
     * Inicia la primera fase del torneo.
     */
    public Ronda<T> primeraRonda() {
        if (!torneo.getRondas().isEmpty()) {
            throw new IllegalStateException("El torneo ya ha comenzado.");
        }
        return generarFase(new ArrayList<>(torneo.getParticipantes()));
    }

    /**
     * Avanza a la siguiente fase basándose en los resultados de la anterior.
     */
    public Optional<Ronda<T>> siguienteRonda() {
        if (torneo.getRondas().isEmpty()) return Optional.empty();
        
        Ronda<T> anterior = torneo.getRondas().last();

        if (anterior.isEsFinal() || !anterior.isTerminada()) {
            return Optional.empty();
        }

        // Combinamos ganadores de la fase previa con los exentos que esperaban
        List<T> clasificados = new ArrayList<>(anterior.getGanadores());
        clasificados.addAll(exentos);
        exentos.clear();

        return Optional.of(generarFase(clasificados));
    }

    /**
     * Lógica central para crear partidos y gestionar el "Bye" (participante impar).
     */
    private Ronda<T> generarFase(List<T> competidores) {
        Collections.shuffle(competidores, random);

        // Si son impares, uno descansa y pasa directamente a la siguiente fase
        if (competidores.size() % 2 != 0) {
            T afortunado = competidores.remove(random.nextInt(competidores.size()));
            exentos.add(afortunado);
        }

        List<Partido<T>> partidos = nexosPartidos(competidores);
        rondasContador++;

        // Es la final si solo hay un partido y nadie espera en la recámara (exentos)
        boolean esFinal = (partidos.size() == 1 && exentos.isEmpty());
        
        String titulo = determinarNombreRonda(partidos.size(), esFinal);
        Ronda<T> nuevaRonda = new Ronda<>(rondasContador, titulo, partidos, esFinal);
        
        torneo.getRondas().add(nuevaRonda);
        return nuevaRonda;
    }

    private List<Partido<T>> nexosPartidos(List<T> lista) {
        List<Partido<T>> result = new ArrayList<>();
        for (int i = 0; i < lista.size(); i += 2) {
            result.add(new Partido<>(lista.get(i), lista.get(i + 1)));
        }
        return result;
    }

    private String determinarNombreRonda(int numPartidos, boolean esFinal) {
        if (esFinal) return "Gran Final";
        return switch (numPartidos) {
            case 8 -> "Octavos de Final";
            case 4 -> "Cuartos de Final";
            case 2 -> "Semifinales";
            default -> "Ronda " + rondasContador;
        };
    }
}