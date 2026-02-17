package campeonato.model.base;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Torneo<T extends ParticipanteAbstracto> {

    private List<T> participantes;
    private SortedSet<Ronda<T>> rondas;

    public Torneo() {
        rondas = new TreeSet<>();
    }

    public Torneo(List<T> participantes) {
        this();
        this.participantes = participantes;
    }

    private void mostrarDatosRonda(Ronda<T> ronda) {
        System.out.println("Ronda: Nº %d - %s".formatted(ronda.getNumero(), ronda.getTitulo().toUpperCase()));
        System.out.println("================================");
        System.out.println();
        System.out.println("-------Partidos:-------");
        for (Partido<T> partido : ronda.getPartidos()) {
            System.out.println("J1: %s - J2: %s".formatted(partido.getParticipante1().getNombre(), partido.getParticipante2().getNombre()));
            if (partido.getGanador() != null) {
                System.out.println("Ganador: %s".formatted(partido.getGanador().getNombre()));
            }
        }
    }

    public void mostrarDatosUltimaRonda() {
        if (rondas.size() > 0) {
            mostrarDatosRonda(rondas.last());
        }
        else{
            System.out.println("No hay rondas en el torneo.");
            return;
        }
    }

    public void mostrarDatosTodasLasRondas() {
        if (rondas.size() > 0) {
            for (Ronda<T> ronda : rondas) {
                mostrarDatosRonda(ronda);
            }
        }
        else{
            System.out.println("No hay rondas en el torneo.");
            return;
        }
    }

    public List<T> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<T> participantes) {
        this.participantes = participantes;
    }

    public SortedSet<Ronda<T>> getRondas() {
        return rondas;
    }

    public void setRondas(SortedSet<Ronda<T>> rondas) {
        this.rondas = rondas;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((participantes == null) ? 0 : participantes.hashCode());
        result = prime * result + ((rondas == null) ? 0 : rondas.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Torneo other = (Torneo) obj;
        if (participantes == null) {
            if (other.participantes != null) {
                return false;
            }
        } else if (!participantes.equals(other.participantes)) {
            return false;
        }
        if (rondas == null) {
            if (other.rondas != null) {
                return false;
            }
        } else if (!rondas.equals(other.rondas)) {
            return false;
        }
        return true;
    }

    public Torneo(List<T> participantes, SortedSet<Ronda<T>> rondas) {
        this.participantes = participantes;
        this.rondas = rondas;
    }

    @Override
    public String toString() {
        return "Torneo [participantes=" + participantes + ", rondas=" + rondas + "]";
    }

}
