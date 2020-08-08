package com.Modelos.Combos;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

public abstract class ModeloComboGeneral<T> extends DefaultComboBoxModel {

    protected List<T> datos = new ArrayList<>();

    public ModeloComboGeneral() {
    }

    public ModeloComboGeneral(List<T> datos) {
        this.datos = datos;
    }

    protected void actualizar() {
        if (datos != null && !datos.isEmpty()) {
            removeAllElements();
            addElement(null);
            datos.forEach(elemento -> {
                addElement(elemento);
            });
        }
    }

    public abstract T obtenerElementoSeleccionado();
    public abstract T obtenerElementoPorId(Long id);

}
