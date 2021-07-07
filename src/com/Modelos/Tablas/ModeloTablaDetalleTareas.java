package com.Modelos.Tablas;

import com.DAO.DAOException;
import com.DAO.DAOTareas;
import com.Modelos.TareaDetalle;
import java.util.List;

public class ModeloTablaDetalleTareas extends ModeloTablaGeneral<TareaDetalle, DAOTareas> {

    public ModeloTablaDetalleTareas(DAOTareas solicitaModelo) {
        super(solicitaModelo);
    }

    public void actualizarModelo(Long idTarea) throws DAOException {
        resultadoSQL = solicitaModelo.buscarDetalles(idTarea == null ? 0 : idTarea);
        super.actualizarModelo();
    }

    public void actualizarModelo(List<TareaDetalle> listadoDatos) throws DAOException {

        if (listadoDatos != null) {
            resultadoSQL = solicitaModelo.buscarDetalles(0L);
            this.listaDatos.clear();
            this.listaDatos.addAll(listadoDatos);
            this.metadatos = resultadoSQL.getKey();
            fireTableDataChanged();
        } else {
            super.actualizarModelo();
        }

    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TareaDetalle detalle = listaDatos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return detalle.getIdDetalle();
            case 1:
                return detalle.getIdTarea();
            case 2:
                return detalle.getDescripcion();
            default:
                return "";
        }
    }

}
