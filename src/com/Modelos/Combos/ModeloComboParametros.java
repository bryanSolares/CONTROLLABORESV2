package com.Modelos.Combos;

import com.Modelos.Parametros;
import java.util.List;

public class ModeloComboParametros extends ModeloComboGeneral<Parametros> {

    private Parametros parametros;

    public ModeloComboParametros() {
        parametros = Parametros.SIN_DEFINIR;
    }

    public ModeloComboParametros(List<Parametros> datos) {
        super(datos);
        parametros = Parametros.SIN_DEFINIR;
    }

        public void actualizarCombo(CBTiposCarga tipoActualizacion) {
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
    public Parametros obtenerElementoPorId(Long id) {
        return datos.stream().filter(e -> e.getId() == id.intValue()).findFirst().get();
    }

}
