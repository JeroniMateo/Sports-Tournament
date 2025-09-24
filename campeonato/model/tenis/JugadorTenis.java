package campeonato.model.tenis;

import campeonato.model.base.ParticipanteAbstracto;

public class JugadorTenis extends ParticipanteAbstracto{

    private int puntosTotales;

    public JugadorTenis() {
        super();
        this.puntosTotales = 0;
    }

    public JugadorTenis(String nombre) {
        super(nombre);
        this.puntosTotales = 0;
    }

    public int getPuntosTotales() {
        return puntosTotales;
    }

    public void setPuntosTotales(int puntosTotales) {
        this.puntosTotales = puntosTotales;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + puntosTotales;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        JugadorTenis other = (JugadorTenis) obj;
        if (puntosTotales != other.puntosTotales)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "JugadorTenis [puntosTotales=" + puntosTotales + "]";
    }

    

}
