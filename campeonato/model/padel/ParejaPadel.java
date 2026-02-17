package campeonato.model.padel;

import campeonato.model.base.ParticipanteAbstracto;
import java.util.Objects;

/**
 * Representa una pareja de pádel. 
 * Especialización de ParticipanteAbstracto que gestiona dos nombres individuales.
 */
public class ParejaPadel extends ParticipanteAbstracto {

    private final String jugador1;
    private final String jugador2;

    /**
     * Constructor para parejas con nombre de equipo y jugadores definidos.
     */
    public ParejaPadel(String nombreEquipo, String jugador1, String jugador2) {
        super(nombreEquipo);
        this.jugador1 = (jugador1 == null || jugador1.isBlank()) ? "TBD" : jugador1.trim();
        this.jugador2 = (jugador2 == null || jugador2.isBlank()) ? "TBD" : jugador2.trim();
    }

    /**
     * Constructor simplificado cuando el nombre del equipo es la unión de los jugadores.
     */
    public ParejaPadel(String jugador1, String jugador2) {
        this(jugador1 + "/" + jugador2, jugador1, jugador2);
    }

    /**
     * Sobrescribe getNombre para ofrecer una identidad completa.
     * Ejemplo: "Coello/Tapia (Arturo Coello y Agustín Tapia)"
     */
    @Override
    public String getNombre() {
        return "%s (%s y %s)".formatted(super.getNombre(), jugador1, jugador2);
    }

    public String getJugador1() { return jugador1; }
    public String getJugador2() { return jugador2; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParejaPadel that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(jugador1, that.jugador1) && 
               Objects.equals(jugador2, that.jugador2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), jugador1, jugador2);
    }

    @Override
    public String toString() {
        return "Pareja Padel: " + getNombre();
    }
}