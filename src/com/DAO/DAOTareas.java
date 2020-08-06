package com.DAO;

import com.Modelos.Tarea;
import com.Modelos.TareaDetalle;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Map;

public interface DAOTareas extends DAO<Tarea, Long> {

    Map<ResultSetMetaData, List<TareaDetalle>> buscarDetalles(Long idTarea) throws DAOException;

    
    
    /*void actualizarTiempos(List<Tarea> tarea) throws DAOException;
    void actualizarTiempoTarea(Tarea tarea) throws DAOException;
    Map<ResultSetMetaData, List<Tarea>> busquedaConFiltros(Long cliente, LocalDate fecha_ini, LocalDate fecha_fin) throws DAOException;*/

}
