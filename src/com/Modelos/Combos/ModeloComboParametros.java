package com.Modelos.Combos;

import com.Modelos.Parametros;
import java.util.List;

public class ModeloComboParametros extends ModeloComboGeneral<Parametros> {

    private Parametros parametros;
    public static final int TIPO_CLIENTE = 1;
    public static final int TIPO_PRIORIDAD = 2;
    public static final int TIPO_ESTADO_TAREA = 3;
    public static final int TIPO_ESTADO_CLIENTE = 4;
    public static final int TIPO_REPONSABLE_SOPORTE = 5;
    public static final int TIPO_SISTEMA = 6;
    public static final int TIPO_CAPACITACION = 7;

    public ModeloComboParametros() {
        parametros = Parametros.SIN_DEFINIR;
    }

    public ModeloComboParametros(List<Parametros> datos) {
        super(datos);
        parametros = Parametros.SIN_DEFINIR;
    }

    public void actualizarCombo(int tipoActualizacion) {
        switch (tipoActualizacion) {
            case TIPO_CLIENTE:
                datos = parametros.devuelveParametrosTipoCliente();
                break;
            case TIPO_PRIORIDAD:
                datos = parametros.devuelveParametrosTipoPrioridad();
                break;
            case TIPO_ESTADO_TAREA:
                datos = parametros.devuelveParametrosTipoEstadoTarea();
                break;
            case TIPO_ESTADO_CLIENTE:
                datos = parametros.devuelveParametrosTipoEstadoCliente();
                break;
            case TIPO_REPONSABLE_SOPORTE:
                datos = parametros.devuelveParametrosTipoEncargadoSoporte();
                break;
            case TIPO_SISTEMA:
                datos = parametros.devuelveParametrosTipoSistema();
                break;
            case TIPO_CAPACITACION:
                datos = parametros.devuelveParametrosTipoCapacitacion();
                break;
        }
        super.actualizar();
    }

    @Override
    public Parametros obtenerElementoSeleccionado() {
        return null;
    }

    @Override
    public Parametros obtenerElementoPorId(Long id) {
        return datos.stream().filter(e -> e.getId() == id.intValue()).findFirst().get();
    }

}
