package campeonato.dummy;

import campeonato.model.base.ParticipanteAbstracto;
import campeonato.model.base.Partido;
import java.util.List;
import java.util.Random;

public final class UtilsPartidos {

    private static final Random RANDOM = new Random();

    private UtilsPartidos() {
        throw new UnsupportedOperationException("Clase de utilidad.");
    }

    /**
     * Versión estática y genérica que espera DummyData.
     */
    public static <T extends ParticipanteAbstracto> void simulaPartidos(List<Partido<T>> partidos) {
        if (partidos != null) {
            for (Partido<T> partido : partidos) {
                simulaPartido(partido);
            }
        }
    }

    public static <T extends ParticipanteAbstracto> void simulaPartido(Partido<T> partido) {
        if (partido == null || partido.getParticipante1() == null || partido.getParticipante2() == null) {
            return;
        }

        // Simulación 50/50
        if (RANDOM.nextBoolean()) {
            partido.setGanador(partido.getParticipante1());
        } else {
            partido.setGanador(partido.getParticipante2());
        }
    }
}