package campeonato;

import campeonato.dummy.DummyData;
import campeonato.model.base.Ronda;
import campeonato.model.base.Torneo;
import campeonato.model.padel.ParejaPadel;
import campeonato.model.service.ServicioRonda;
import campeonato.model.tenis.JugadorTenis;
import java.util.Optional;

public class App {
    
    public static void main(String[] args) {
        simularCampeonato("TENIS", DummyData.generarJugadoresTenis());
        simularCampeonato("PÁDEL", DummyData.generarParejasPadel());
    }

    /**
     * Método genérico que unifica la lógica de ambos torneos.
     * Gracias a que usamos <T>, el código es idéntico para Tenis y Pádel.
     */
    public static <T extends campeonato.model.base.ParticipanteAbstracto> void simularCampeonato(String tipo, java.util.List<T> participantes) {
        System.out.println("\n=== INICIANDO TORNEO DE %s ===".formatted(tipo));
        
        Torneo<T> torneo = new Torneo<>(participantes);
        ServicioRonda<T> servicio = new ServicioRonda<>(torneo);

        // --- Primera Ronda ---
        Ronda<T> rondaActual = servicio.primeraRonda();
        procesarRonda(torneo, rondaActual);

        // --- Rondas Sucesivas ---
        while (!rondaActual.isEsFinal()) {
            Optional<Ronda<T>> siguiente = servicio.siguienteRonda();
            
            if (siguiente.isPresent()) {
                rondaActual = siguiente.get();
                procesarRonda(torneo, rondaActual);
            } else {
                break; 
            }
        }

        // --- Resultado Final ---
        torneo.getCampeon().ifPresent(c -> {
            System.out.println("\n🏆 ¡TENEMOS UN CAMPEÓN DE %s! 🏆".formatted(tipo));
            System.out.println("Felicidades a: " + c.getNombre());
        });
    }

    /**
     * Encapsula la lógica de ejecución de cada ronda.
     */
    private static <T extends campeonato.model.base.ParticipanteAbstracto> void procesarRonda(Torneo<T> torneo, Ronda<T> ronda) {
        DummyData.establecerDatosRonda(ronda);
        ronda.terminarRonda();
        torneo.mostrarUltimaRonda();
    }
}