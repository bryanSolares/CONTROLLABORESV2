package com.Modelos;

import java.util.Objects;

public class TareaDetalle implements IModelos{

    private Long idDetalle;
    private Long idTarea;
    private String descripcion;

    public TareaDetalle() {
        this.idDetalle = 0L;
        this.idTarea = 0L;
        this.descripcion = "";
    }

    public TareaDetalle(Long idDetalle, Long idTarea, String descripcion) {
        this.idDetalle = idDetalle;
        this.idTarea = idTarea;
        this.descripcion = descripcion;
    }

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = (int) (29 * hash + this.idDetalle);
        return hash;
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
        final TareaDetalle other = (TareaDetalle) obj;
        return Objects.equals(this.idDetalle, other.idDetalle);
    }

    @Override
    public String toString() {
        return "TareaDetalle{" + "idDetalle=" + idDetalle + ", idTarea=" + idTarea + ", descripcion=" + descripcion + '}';
    }
}
