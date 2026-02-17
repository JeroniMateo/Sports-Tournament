package campeonato.model.base;

public class Partido<T extends ParticipanteAbstracto> {

    private T participante1, participante2;
    private T ganador;
    private T perdedor;

    public Partido(){

    }

    public Partido(T participante1, T participante2) {
        this.participante1 = participante1;
        this.participante2 = participante2;
    }

        public Partido(T participante1, T participante2, T ganador, T perdedor) {
        this.participante1 = participante1;
        this.participante2 = participante2;
        this.ganador = ganador;
        this.perdedor = perdedor;
    }

    public T getParticipante1() {
        return participante1;
    }

    public void setParticipante1(T participante1) {
        this.participante1 = participante1;
    }

    public T getParticipante2() {
        return participante2;
    }

    public void setParticipante2(T participante2) {
        this.participante2 = participante2;
    }

    public T getGanador() {
        return ganador;
    }

    public void setGanador(T ganador) {
        this.ganador = ganador;
    }

    public T getPerdedor() {
        return perdedor;
    }

    public void setPerdedor(T perdedor) {
        this.perdedor = perdedor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((participante1 == null) ? 0 : participante1.hashCode());
        result = prime * result + ((participante2 == null) ? 0 : participante2.hashCode());
        result = prime * result + ((ganador == null) ? 0 : ganador.hashCode());
        result = prime * result + ((perdedor == null) ? 0 : perdedor.hashCode());
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
        Partido other = (Partido) obj;
        if (participante1 == null) {
            if (other.participante1 != null)
                return false;
        } else if (!participante1.equals(other.participante1))
            return false;
        if (participante2 == null) {
            if (other.participante2 != null)
                return false;
        } else if (!participante2.equals(other.participante2))
            return false;
        if (ganador == null) {
            if (other.ganador != null)
                return false;
        } else if (!ganador.equals(other.ganador))
            return false;
        if (perdedor == null) {
            if (other.perdedor != null)
                return false;
        } else if (!perdedor.equals(other.perdedor))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Partido [participante1=" + participante1 + ", participante2=" + participante2 + ", ganador=" + ganador
                + ", perdedor=" + perdedor + "]";
    }


    
}
