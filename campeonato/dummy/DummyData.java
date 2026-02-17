package campeonato.dummy;

import java.util.ArrayList;
import java.util.List;
import campeonato.model.base.ParticipanteAbstracto;
import campeonato.model.base.Ronda;
import campeonato.model.padel.ParejaPadel;
import campeonato.model.tenis.JugadorTenis;

/**
 * Clase de utilidad para la generación de datos de prueba (Mock Data).
 */
public final class DummyData {

    // Constructor privado para evitar instanciación
    private DummyData() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad.");
    }

    /**
     * Genera una lista predefinida de jugadores de tenis profesionales.
     */
    public static List<JugadorTenis> generarJugadoresTenis() {
        return new ArrayList<>(List.of(
            new JugadorTenis("Carlos Alcaraz"),
            new JugadorTenis("Jannik Sinner"),
            new JugadorTenis("Novak Djokovic"),
            new JugadorTenis("Alexander Zverev"),
            new JugadorTenis("Lorenzo Musetti"),
            new JugadorTenis("Alex de Minaur"),
            new JugadorTenis("Félix Auger-Aliassime"),
            new JugadorTenis("Taylor Fritz"),
            new JugadorTenis("Ben Shelton"),
            new JugadorTenis("Alexander Bublik"),
            new JugadorTenis("Aryna Sabalenka"),
            new JugadorTenis("Iga Swiatek"),
            new JugadorTenis("Elena Rybakina"),
            new JugadorTenis("Coco Gauff"),
            new JugadorTenis("Jessica Pegula"),
            new JugadorTenis("Amanda Anisimova"),
            new JugadorTenis("Mirra Andreeva"),
            new JugadorTenis("Jasmine Paolini"),
            new JugadorTenis("Elina Svitolina"),
            new JugadorTenis("Victoria Mboko")
        ));
    }

    /**
     * Genera una lista predefinida de parejas de pádel del ranking oficial.
     */
    public static List<ParejaPadel> generarParejasPadel() {
        return new ArrayList<>(List.of(
            new ParejaPadel("Coello/Tapia", "Arturo Coello", "Agustín Tapia"),
            new ParejaPadel("Chingotto/Galán", "Alejandro Galán", "Federico Chingotto"),
            new ParejaPadel("Lebrón/Di Nenno", "Juan Lebrón", "Martín Di Nenno"),
            new ParejaPadel("Stupa/Yanguas", "Franco Stupaczuk", "Mike Yanguas"),
            new ParejaPadel("Navarro/Cardona", "Paquito Navarro", "Pablo Cardona"),
            new ParejaPadel("Nieto/Sanz", "Coki Nieto", "Jon Sanz"),
            new ParejaPadel("Momo/Alonso", "Momo González", "Edu Alonso"),
            new ParejaPadel("Sanyo/Luque", "Sanyo Gutiérrez", "Vicente Luque"),
            new ParejaPadel("Bela/Libaak", "Fernando Belasteguín", "Tino Libaak"),
            new ParejaPadel("Campa/Leal", "Lucas Campagnolo", "Javi Leal"),
            new ParejaPadel("Ari/Paula", "Ariana Sánchez", "Paula Josemaría"),
            new ParejaPadel("Gemma/Claudia", "Gemma Triay", "Claudia Fernández"),
            new ParejaPadel("Delfi/Bea", "Delfina Brea", "Beatriz González"),
            new ParejaPadel("Martita/Sofia", "Marta Ortega", "Sofia Araújo"),
            new ParejaPadel("Sainz/Llaguno", "Lucía Sainz", "Patricia Llaguno"),
            new ParejaPadel("Castelló/Salazar", "Jessica Castelló", "Alejandra Salazar"),
            new ParejaPadel("Osoro/Iglesias", "Aranzazu Osoro", "Victoria Iglesias"),
            new ParejaPadel("Riera/Goenaga", "Virginia Riera", "Carmen Goenaga"),
            new ParejaPadel("Icardo/Jensen", "Tamara Icardo", "Claudia Jensen"),
            new ParejaPadel("Vero/Arantxa", "Vero Virseda", "Arantxa Soriano")
        ));
    }

    /**
     * Delegación limpia para la simulación de partidos de una ronda.
     */
    public static <T extends ParticipanteAbstracto> void establecerDatosRonda(Ronda<T> ronda) {
        if (ronda != null && ronda.getPartidos() != null) {
            UtilsPartidos.simulaPartidos(ronda.getPartidos());
        }
    }
}