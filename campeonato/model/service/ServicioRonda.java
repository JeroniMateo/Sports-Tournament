package campeonato.model.service;

import campeonato.model.base.ParticipanteAbstracto;
import campeonato.model.base.Partido;
import campeonato.model.base.Ronda;
import campeonato.model.base.Torneo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ServicioRonda<T extends ParticipanteAbstracto> {

    private int rondasJugadas;
    private Torneo<T> torneo;
    private List<T> participantes;
    private List<T> participantesSiguienteRonda;

    public ServicioRonda() {
        rondasJugadas = 0;
    }

    public ServicioRonda(Torneo<T> torneo) {
        this();
        this.torneo = torneo;
        this.participantes = torneo.getParticipantes();
        this.participantesSiguienteRonda = new ArrayList<>();
    }

    private List<Partido<T>> sorteo(List<T> participantes) {
        List<T> copia = new ArrayList<>(participantes);
        Collections.shuffle(copia);

        List<Partido<T>> result = new ArrayList<>();
        for (int i = 0; i < copia.size(); i += 2) {
            result.add(new Partido<>(copia.get(i), copia.get(i + 1)));
        }
        return result;
    }

    private String getNombreRonda(int numRonda, int numParticipantes) {
        return switch (numParticipantes) {
            case 32 ->
                "Dieciseisavos de Final";
            case 16 ->
                "Octavos de Final";
            case 8 ->
                "Cuartos de Final";
            case 4 ->
                "Semifinal";
            case 2 ->
                "Final";
            default ->
                "Ronda %d".formatted(numRonda);
        };
    }

    public Ronda<T> primeraRonda() {
        participantesSiguienteRonda.clear();
        List<T> copia = new ArrayList<>(participantes);

        Random r = new Random();
        if (copia.size() % 2 != 0) {
            int pos = r.nextInt(copia.size());
            T elegido = copia.remove(pos);
            participantesSiguienteRonda.add(elegido);
        }

        List<Partido<T>> partidos = sorteo(copia);
        rondasJugadas++;
        Ronda<T> ronda = new Ronda<>(rondasJugadas, getNombreRonda(rondasJugadas, copia.size()), partidos, false);
        torneo.getRondas().add(ronda);
        return ronda;
    }

    public Ronda<T> siguienteRonda() {
        Ronda<T> rondaAnterior = torneo.getRondas().last();
        if (rondaAnterior.isEsFinal() || !rondaAnterior.isTerminada()) {
            List<T> participantes = rondaAnterior.getGanadores();
            participantes.addAll(participantesSiguienteRonda);
            participantesSiguienteRonda.clear();

            Random r = new Random();
            if (participantes.size() % 2 != 0) {
                int pos = r.nextInt(participantes.size());
                T elegido = participantes.remove(pos);
                participantesSiguienteRonda.add(elegido);
            }

            List<Partido<T>> partidos = sorteo(participantes);
            rondasJugadas++;
            boolean esFinal = participantes.size() == 2 && participantesSiguienteRonda.size() == 0;

            Ronda<T> ronda = new Ronda<>(rondasJugadas, getNombreRonda(rondasJugadas, participantes.size()), partidos, esFinal);
            torneo.getRondas().add(ronda);
            return ronda;

        }

    }

}
