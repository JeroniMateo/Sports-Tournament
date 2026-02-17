package campeonato.model.base;

import java.util.List;

public class Ronda <T extends ParticipanteAbstracto> implements Comparable<Ronda<T>> {

    private int numero;
    private String titulo;
    private List<Partido<T>> partidos;
    private boolean esFinal;
    private boolean terminada;
    private List<T> ganadores;


public boolean terminarRonda() {
    if (partidos == null || partidos.isEmpty()) {
        this.terminada = false;
        return false;
    }

    for (Partido<T> partido : partidos) {
        if (partido.getGanador() == null) {
            this.terminada = false;
            return false;
        }
    }

    this.terminada = true;
    extraerGanadores();
    return true;
}



    private void extraerGanadores(){
        for (Partido<T> partido : partidos) {
           ganadores.add(partido.getGanador());
        }
    }

    public List<T> getGanadores() {
    if (this.terminada){
        return ganadores;
    } else {
        return null;
    }
}

  @Override
    public int compareTo(Ronda<T> o) {
        return Integer.compare(numero, o.numero);
    }

    public Ronda(int numero, String titulo, List<Partido<T>> partidos, boolean esFinal, boolean terminada,
            List<T> ganadores) {
        this.numero = numero;
        this.titulo = titulo;
        this.partidos = partidos;
        this.esFinal = esFinal;
        this.terminada = terminada;
        this.ganadores = ganadores;
    }




    @Override
    public String toString() {
        return "Ronda [numero=" + numero + ", titulo=" + titulo + ", partidos=" + partidos + ", esFinal=" + esFinal
                + ", terminada=" + terminada + ", ganadores=" + ganadores + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + numero;
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        result = prime * result + ((partidos == null) ? 0 : partidos.hashCode());
        result = prime * result + (esFinal ? 1231 : 1237);
        result = prime * result + (terminada ? 1231 : 1237);
        result = prime * result + ((ganadores == null) ? 0 : ganadores.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ronda other = (Ronda) obj;
        if (numero != other.numero)
            return false;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        if (partidos == null) {
            if (other.partidos != null)
                return false;
        } else if (!partidos.equals(other.partidos))
            return false;
        if (esFinal != other.esFinal)
            return false;
        if (terminada != other.terminada)
            return false;
        if (ganadores == null) {
            if (other.ganadores != null)
                return false;
        } else if (!ganadores.equals(other.ganadores))
            return false;
        return true;
    }


    public int getNumero() {
        return numero;
    }



public void setNumero(int numero) {
    this.numero = numero;
}



public String getTitulo() {
    return titulo;
}



public void setTitulo(String titulo) {
    this.titulo = titulo;
}



public List<Partido<T>> getPartidos() {
    return partidos;
}



public void setPartidos(List<Partido<T>> partidos) {
    this.partidos = partidos;
}



public boolean isEsFinal() {
    return esFinal;
}



public void setEsFinal(boolean esFinal) {
    this.esFinal = esFinal;
}



public boolean isTerminada() {
    return terminada;
}



public void setTerminada(boolean terminada) {
    this.terminada = terminada;
}






public void setGanadores(List<T> ganadores) {
    this.ganadores = ganadores;
    }

}
