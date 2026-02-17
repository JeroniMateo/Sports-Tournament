package campeonato.model.tenis;

import campeonato.model.base.ParticipanteAbstracto;
import java.util.Objects;

/**
 * Representa a un tenista individual.
 * Incluye un sistema de puntuación para rankings o simulaciones basadas en nivel.
 */
public class JugadorTenis extends ParticipanteAbstracto {

    private int puntosTotales;

    /**
     * Constructor estándar para nuevos jugadores.
     */
    public JugadorTenis(String nombre) {
        super(nombre);
        this.puntosTotales = 0;
    }

    /**
     * Constructor para jugadores con puntuación de ranking inicial.
     */
    public JugadorTenis(String nombre, int puntosTotales) {
        super(nombre);
        this.puntosTotales = Math.max(0, puntosTotales); // Evita puntos negativos
    }

    public int getPuntosTotales() {
        return puntosTotales;
    }

    public void setPuntosTotales(int puntosTotales) {
        this.puntosTotales = Math.max(0, puntosTotales);
    }

    /**
     * Método de utilidad para actualizar puntos tras una victoria.
     */
    public void añadirPuntos(int puntos) {
        if (puntos > 0) {
            this.puntosTotales += puntos;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JugadorTenis that)) return false;
        if (!super.equals(o)) return false;
        return puntosTotales == that.puntosTotales;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), puntosTotales);
    }

    @Override
    public String toString() {
        return "Tenista: %s [%d pts]".formatted(getNombre(), puntosTotales);
    }
}