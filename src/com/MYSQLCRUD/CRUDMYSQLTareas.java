package com.MYSQLCRUD;

import com.DAO.DAOException;
import com.DAO.DAOTareas;
import com.Modelos.Tarea;
import com.Modelos.TareaDetalle;
import com.Recursos.GestionarRecursos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

public class CRUDMYSQLTareas implements DAOTareas {

    private Connection conexion = null;

    private final String guardarTarea
            = "INSERT INTO TAREAS(TITULO,ID_CLIENTE,DESCRIPCION,PRIORIDAD,ESTADO,DURACION,FECHA_INICIO,FECHA_FIN,AREA)"
            + " VALUES(?,?,?,?,?,?,?,?,?)";
    private final String editarTarea
            = "UPDATE TAREAS SET TITULO = ?,ID_CLIENTE = ?,DESCRIPCION = ?,PRIORIDAD = ?,"
            + "ESTADO = ?,DURACION = ?, FECHA_INICIO = ?,FECHA_FIN = ?, AREA = ? "
            + "WHERE ID_TAREA = ?";
    private final String borrarTarea = "DELETE FROM TAREAS WHERE ID_TAREA = ?";
    private final String buscarTodasLasTareas = "SELECT * FROM TAREAS WHERE ESTADO != 33 ";
    private final String buscarUnaTarea = "SELECT * FROM TAREAS WHERE ID_TAREA = ?";
    private final String modificaTiempos = "UPDATE TAREAS SET DURACION = ? WHERE ID_TAREA = ?";

    private final String agregarDetalleTarea = "INSERT INTO TAREAS_DETALLE (ID_TAREA,DESCRIPCION) VALUES (?,?)";
    private final String editarDetalleTarea = "UPDATE TAREAS_DETALLE SET DESCRIPCION = ? WHERE ID_DETALLE = ?";
    private final String borrarDetalleTarea = "DELETE FROM TAREAS_DETALLE WHERE ID_TAREA = ?";
    private final String buscarTodosLosDetalles = "SELECT * FROM TAREAS_DETALLE WHERE ID_TAREA = ?";

    public CRUDMYSQLTareas(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void crear(Tarea tarea) throws DAOException {
        PreparedStatement consultaPreparada = null;
        try {

            conexion.setAutoCommit(false);

            consultaPreparada = conexion.prepareStatement(guardarTarea, Statement.RETURN_GENERATED_KEYS);
            consultaPreparada.setString(1, tarea.getTitulo());
            consultaPreparada.setLong(2, tarea.getCliente());
            consultaPreparada.setString(3, tarea.getDescripcion());
            consultaPreparada.setInt(4, tarea.getPrioridad());
            consultaPreparada.setInt(5, tarea.getEstado());
            consultaPreparada.setString(6, tarea.getDuracionTarea());
            consultaPreparada.setTimestamp(7, Timestamp.valueOf(tarea.getFechaInicio()));
            consultaPreparada.setTimestamp(8, Timestamp.valueOf(tarea.getFechaFin()));
            consultaPreparada.setInt(9, tarea.getAreaResponsable());
            consultaPreparada.executeUpdate();

            Long idDisponible = 0L;
            ResultSet llave = consultaPreparada.getGeneratedKeys();

            if (llave.next()) {
                idDisponible = llave.getLong(1);
            } else {
                GestionarRecursos.propagarError("No se puede encontrar ID");
            }

            crearDetalles(tarea, idDisponible);

            conexion.commit();

        } catch (SQLException ex) {
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                GestionarRecursos.propagarError(ex1);
            }
            GestionarRecursos.propagarError(ex);
        } finally {
            try {
                conexion.setAutoCommit(true);
                GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
            } catch (SQLException ex) {
                GestionarRecursos.propagarError(ex);
            }
        }
    }

    private void crearDetalles(Tarea tarea, Long idDisponible) throws DAOException {
        try {
            PreparedStatement insercionDetalle = conexion.prepareStatement(agregarDetalleTarea);
            tarea.obtenerListaDetalles().forEach(e -> {
                try {
                    insercionDetalle.setLong(1, idDisponible);
                    insercionDetalle.setString(2, e.getDescripcion());
                    insercionDetalle.addBatch();
                } catch (SQLException ee) {
                    System.err.println(ee);
                }
            });

            insercionDetalle.executeBatch();
        } catch (SQLException ex) {
            GestionarRecursos.propagarError(ex);
        }
    }

    @Override
    public void editar(Tarea tarea) throws DAOException {

        PreparedStatement consultaPreparada = null;

        try {

            conexion.setAutoCommit(false);

            consultaPreparada = conexion.prepareStatement(editarTarea);
            consultaPreparada.setString(1, tarea.getTitulo());
            consultaPreparada.setLong(2, tarea.getCliente());
            consultaPreparada.setString(3, tarea.getDescripcion());
            consultaPreparada.setInt(4, tarea.getPrioridad());
            consultaPreparada.setInt(5, tarea.getEstado());
            consultaPreparada.setString(6, tarea.getDuracionTarea());
            consultaPreparada.setTimestamp(7, Timestamp.valueOf(tarea.getFechaInicio()));
            consultaPreparada.setTimestamp(8, Timestamp.valueOf(tarea.getFechaFin()));
            consultaPreparada.setInt(9, tarea.getAreaResponsable());
            consultaPreparada.setLong(10, tarea.getId());

            if (consultaPreparada.executeUpdate() == 0) {
                throw new DAOException("Error al modificar Tarea");
            }

            if (!tarea.obtenerListaDetalles().isEmpty()) {
                borrarDetalles(tarea);
                crearDetalles(tarea, tarea.getId());
            }

            conexion.commit();

        } catch (DAOException | SQLException e) {
            try {
                conexion.rollback();
                GestionarRecursos.propagarError(e);
            } catch (SQLException ex) {
                GestionarRecursos.propagarError(e);
            }
        } finally {
            try {
                conexion.setAutoCommit(true);
                GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
            } catch (SQLException ex) {
                GestionarRecursos.propagarError(ex);
            }
        }
    }

    @Override
    public void borrar(Long id) throws DAOException {
        PreparedStatement consultaPreparada = null;
        try {
            consultaPreparada = conexion.prepareStatement(borrarTarea);
            consultaPreparada.setLong(1, id);

            if (consultaPreparada.executeUpdate() == 0) {
                throw new DAOException("Error al eliminar Tarea");
            }

        } catch (DAOException | SQLException e) {
            GestionarRecursos.propagarError(e);
        } finally {
            GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
        }
    }

    private void borrarDetalles(Tarea tarea) throws DAOException {
        PreparedStatement consultaPreparada = null;
        try {
            consultaPreparada = conexion.prepareStatement(borrarDetalleTarea);
            consultaPreparada.setLong(1, tarea.getId());
            consultaPreparada.executeUpdate();

        } catch (SQLException e) {
            GestionarRecursos.propagarError(e);
        } finally {
            GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
        }
    }

    public Tarea convierteElemento(ResultSet resultado) throws SQLException, DAOException {
        Tarea tarea;

        Long id = resultado.getLong(1);
        String titulo = resultado.getString(2);
        Long cliente = resultado.getLong(3);
        String descripcion = resultado.getString(4);
        int prioridad = resultado.getInt(5);
        int estado = resultado.getInt(6);
        String duracionTarea = resultado.getString(7);
        LocalDateTime horaInicio = LocalDateTime.of(resultado.getDate(8).toLocalDate(), resultado.getTime(8).toLocalTime());
        LocalDateTime horaFin = LocalDateTime.of(resultado.getDate(9).toLocalDate(), resultado.getTime(9).toLocalTime());
        int areaResponsable = resultado.getInt(10);

        tarea = new Tarea(titulo, cliente, prioridad, estado);
        tarea.setId(id);
        tarea.setDescripcion(descripcion);
        tarea.setDuracionTarea(duracionTarea);
        tarea.setFechaInicio(horaInicio);
        tarea.setFechaFin(horaFin);
        tarea.setAreaResponsable(areaResponsable);

        try {
            tarea.setListaDetalles(buscarDetalles(id).getValue());
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }

        return tarea;
    }

    @Override
    ///public Map<ResultSetMetaData, List<Tarea>> buscarTodos() throws DAOException {
    public Pair<ResultSetMetaData, List<Tarea>> buscarTodos() throws DAOException {

        PreparedStatement consultaPreparada = null;
        ResultSet resultados = null;

        try {
            consultaPreparada = conexion.prepareStatement(buscarTodasLasTareas);
            resultados = consultaPreparada.executeQuery();

        } catch (SQLException e) {
            GestionarRecursos.propagarError(e);
        }
        return devuelveListadoTareasConMetadatos(resultados);

    }

    @Override
    public Tarea buscarUno(Long id) throws DAOException {
        Tarea tarea = null;
        PreparedStatement consultaPreparada = null;
        ResultSet resultados = null;
        try {
            consultaPreparada = conexion.prepareStatement(buscarUnaTarea);
            consultaPreparada.setLong(1, id);
            resultados = consultaPreparada.executeQuery();

            if (resultados.next()) {
                tarea = convierteElemento(resultados);
            }

        } catch (SQLException e) {
            GestionarRecursos.propagarError(e);
        } finally {
            GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
            GestionarRecursos.cerrarResultSet(resultados);
        }

        return tarea;
    }
//
//    public void actualizarTiempos(List<Tarea> tareas) throws DAOException {
//        final PreparedStatement consultaPreparada = null;
//        try {
//
//            conexion.setAutoCommit(false);
//            if (!tareas.isEmpty()) {
//                consultaPreparada = conexion.prepareStatement(modificaTiempos);
//                tareas.forEach(t -> {
//                    try {
//                        consultaPreparada.setString(1, t.getDuracionTarea());
//                        consultaPreparada.setLong(2, t.getId());
//                        consultaPreparada.addBatch();
//                    } catch (SQLException ex) {
//                        GestionarRecursos.propagarError(ex);
//                    }
//                });
//
//                consultaPreparada.executeBatch();
//                conexion.commit();
//            }
//
//        } catch (SQLException e) {
//            try {
//                conexion.rollback();
//                GestionarRecursos.propagarError(e);
//            } catch (SQLException ex) {
//                GestionarRecursos.propagarError(ex);
//            }
//        } finally {
//            try {
//                conexion.setAutoCommit(true);
//                GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
//            } catch (SQLException ex) {
//                GestionarRecursos.propagarError(ex);
//            }
//        }
//    }

//    public void actualizarTiempoTarea(Tarea tarea) throws DAOException {
//        try {
//            conexion.setAutoCommit(false);
//            consultaPreparada = conexion.prepareStatement(modificaTiempos);
//            consultaPreparada.setString(1, tarea.getDuracionTarea());
//            consultaPreparada.setLong(2, tarea.getId());
//            consultaPreparada.executeUpdate();
//            conexion.commit();
//        } catch (SQLException e) {
//            try {
//                conexion.rollback();
//                GestionarRecursos.propagarError(e);
//            } catch (SQLException ex) {
//                GestionarRecursos.propagarError(ex);
//            }
//        } finally {
//            try {
//                conexion.setAutoCommit(true);
//                GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
//            } catch (SQLException ex) {
//                GestionarRecursos.propagarError(ex);
//            }
//        }
//    }
//    public Map<ResultSetMetaData, List<Tarea>> busquedaConFiltros(Long cliente, LocalDate fecha_ini, LocalDate fecha_fin) throws DAOException {
//
//        String filtro1 = "SELECT * FROM TAREAS WHERE ID_CLIENTE = ?";
//        String filtro2 = "SELECT * FROM TAREAS WHERE FECHA_INICIO >= ? AND FECHA_FIN <= ?";
//        String filtro3 = "SELECT * FROM TAREAS WHERE ID_CLIENTE = ? AND FECHA_INICIO >= ? AND FECHA_FIN <= ?";
//
//        try {
//
//            if (cliente == null && fecha_ini == null && fecha_fin == null) {
//
//                return buscarTodos();
//
//            } else if (cliente != null && fecha_ini == null && fecha_fin == null) {
//
//                consultaPreparada = conexion.prepareStatement(filtro1);
//                consultaPreparada.setLong(1, cliente);
//                resultados = consultaPreparada.executeQuery();
//
//                return devuelveListadoTareasConMetadatos(resultados);
//
//            } else if (cliente == null && fecha_ini != null && fecha_fin != null) {
//
//                consultaPreparada = conexion.prepareStatement(filtro2);
//                consultaPreparada.setTimestamp(1, Timestamp.valueOf(fecha_ini.atTime(LocalTime.MIN)));
//                consultaPreparada.setTimestamp(2, Timestamp.valueOf(fecha_fin.atTime(LocalTime.MAX)));
//                resultados = consultaPreparada.executeQuery();
//
//                return devuelveListadoTareasConMetadatos(resultados);
//
//            } else if (cliente != null && fecha_ini != null && fecha_fin != null) {
//
//                consultaPreparada = conexion.prepareStatement(filtro3);
//                consultaPreparada.setLong(1, cliente);
//                consultaPreparada.setTimestamp(2, Timestamp.valueOf(fecha_ini.atTime(LocalTime.MIN)));
//                consultaPreparada.setTimestamp(3, Timestamp.valueOf(fecha_fin.atTime(LocalTime.MAX)));
//                resultados = consultaPreparada.executeQuery();
//
//                return devuelveListadoTareasConMetadatos(resultados);
//
//            }
//
//        } catch (DAOException | SQLException e) {
//            throw new DAOException("Error en consulta avanzada" + getClass() + e);
//        } finally {
////            GestionarRecursos.cerrarPreparedStatement(consulta);
////            GestionarRecursos.cerrarResultSet(resultadosConsulta);
//        }
//        return null;
//
//    }
    private Pair<ResultSetMetaData, List<Tarea>> devuelveListadoTareasConMetadatos(ResultSet resultadoTareas) throws DAOException {

        ///Map<ResultSetMetaData, List<Tarea>> mapeoDatos = new HashMap<>();
        Pair<ResultSetMetaData, List<Tarea>> mapeoDatos = null;
        ResultSetMetaData metadatos;
        List<Tarea> listaElementos = new ArrayList<>();
        PreparedStatement consultaPreparada = null;
        try {

            consultaPreparada = conexion.prepareStatement(buscarTodosLosDetalles);

            while (resultadoTareas.next()) {
                consultaPreparada.setLong(1, resultadoTareas.getLong(1));
                listaElementos.add(convierteElemento(resultadoTareas));
            }

            metadatos = resultadoTareas.getMetaData();
            //mapeoDatos.put(metadatos, listaElementos);
            mapeoDatos = new Pair<>(metadatos, listaElementos);

        } catch (SQLException ex) {
            GestionarRecursos.propagarError(ex);
        } finally {
            GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
            GestionarRecursos.cerrarResultSet(resultadoTareas);
        }
        return mapeoDatos;

    }

    @Override
    public Pair<ResultSetMetaData, List<TareaDetalle>> buscarDetalles(Long idTarea) throws DAOException {
        Pair<ResultSetMetaData, List<TareaDetalle>> datosTabla = null;
        Tarea t = new Tarea();
        ResultSetMetaData metadata;
        PreparedStatement consultaPreparada = null;
        ResultSet resultados = null;

        try {
            consultaPreparada = conexion.prepareStatement(buscarTodosLosDetalles);
            consultaPreparada.setLong(1, idTarea);
            resultados = consultaPreparada.executeQuery();

            while (resultados.next()) {
                t.agregarElementoAListaDetalles(new TareaDetalle(resultados.getLong(1), resultados.getLong(2), resultados.getString(3)));
            }

            metadata = resultados.getMetaData();
            ///datosTabla.put(metadata, t.obtenerListaDetalles());
            datosTabla = new Pair<>(metadata, t.obtenerListaDetalles());

        } catch (SQLException ex) {
            GestionarRecursos.propagarError(ex);
        } finally {
            GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
            GestionarRecursos.cerrarResultSet(resultados);
        }
        return datosTabla;
    }

//    public List<TareaDetalle> buscarDetallesLista(Long idTarea) throws DAOException {
//        PreparedStatement consultaPreparada = null;
//        List<TareaDetalle> detalles = new ArrayList<>();
//        try {
//            consultaPreparada = conexion.prepareStatement(buscarTodosLosDetalles);
//            consultaPreparada.setLong(1, idTarea);
//            ResultSet result = consultaPreparada.executeQuery();
//
//            while (result.next()) {
//                detalles.add(new TareaDetalle(result.getLong(1), result.getLong(2), result.getString(3)));
//            }
//
//        } catch (SQLException ex) {
//            GestionarRecursos.propagarError(ex);
//        }finally {
//            GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
//        }
//        return detalles;
//    }
}
