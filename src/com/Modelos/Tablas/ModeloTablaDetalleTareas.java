package com.Modelos.Tablas;

import com.DAO.DAOException;
import com.DAO.DAOTareas;
import com.Modelos.TareaDetalle;

public class ModeloTablaDetalleTareas extends ModeloTablaGeneral<TareaDetalle, DAOTareas> {

    private Long idTarea;

    public ModeloTablaDetalleTareas(DAOTareas solicitaModelo) {
        super(solicitaModelo);
    }

    @Override
    public void actualizarModelo() throws DAOException {
        resultadoSQL = solicitaModelo.buscarDetalles(idTarea == null ? 0 : idTarea);
        super.actualizarModelo();
    }

    @Override
    public void removeElementWithId(Long id) {
        if (id != 0) {
            listaDatos.removeIf(e -> e.getIdDetalle().equals(id));
            fireTableDataChanged();
        }
    }

    @Override
    public TareaDetalle getElementById(Long id) {
        return listaDatos.stream().filter(e -> e.getIdDetalle().equals(id)).findAny().get();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TareaDetalle detalle = listaDatos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return detalle.getIdTarea();
            case 1:
                return detalle.getIdDetalle();
            case 2:
                return detalle.getDescripcion();
            default:
                return "";
        }
    }

    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
    }

}
