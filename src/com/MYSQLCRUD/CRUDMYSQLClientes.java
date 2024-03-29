package com.MYSQLCRUD;

import com.DAO.DAOClientes;
import com.DAO.DAOException;
import com.Modelos.Cliente;
import com.Recursos.GestionarRecursos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

public class CRUDMYSQLClientes implements DAOClientes {

    private Connection conexion = null;
    private final String crearCliente = "INSERT INTO CLIENTES (NOMBRE, ESTADO, TELEFONO, TIPO_CLIENTE, ID_SISTEMA, CONTACTO) VALUES (?,?,?,?,?,?)";
    private final String editarCliente = "UPDATE CLIENTES SET NOMBRE = ?, ESTADO = ?, TELEFONO = ?, TIPO_CLIENTE = ?, ID_SISTEMA = ?, CONTACTO = ? WHERE ID_CLIENTE = ?";
    private final String borrarCliente = "DELETE FROM CLIENTES WHERE ID_CLIENTE = ?";
    private final String buscarTodosLosClientes = "SELECT * FROM VC_CLIENTES";
    private final String buscarUnCliente = "SELECT * FROM CLIENTES WHERE ID_CLIENTE = ?";

    public CRUDMYSQLClientes(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void crear(Cliente cliente) throws DAOException {

        PreparedStatement consultaPreparada = null;

        try {
            consultaPreparada = conexion.prepareStatement(crearCliente);
            consultaPreparada.setString(1, cliente.getNombre());
            consultaPreparada.setInt(2, cliente.getEstado());
            consultaPreparada.setString(3, cliente.getTelefono());
            consultaPreparada.setInt(4, cliente.gettCliente());
            consultaPreparada.setInt(5, cliente.getId_sistema());
            consultaPreparada.setString(6, cliente.getContacto());

            if (consultaPreparada.executeUpdate() == 0) {
                throw new DAOException("Error al crear Cliente");
            }

        } catch (SQLException e) {
            GestionarRecursos.propagarError(e);
        } finally {
            GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
        }
    }

    @Override
    public void editar(Cliente cliente) throws DAOException {

        PreparedStatement consultaPreparada = null;
        try {

            consultaPreparada = conexion.prepareStatement(editarCliente);
            consultaPreparada.setString(1, cliente.getNombre());
            consultaPreparada.setInt(2, cliente.getEstado());
            consultaPreparada.setString(3, cliente.getTelefono());
            consultaPreparada.setInt(4, cliente.gettCliente());
            consultaPreparada.setInt(5, cliente.getId_sistema());
            consultaPreparada.setString(6, cliente.getContacto());
            consultaPreparada.setLong(7, cliente.getId());

            if (consultaPreparada.executeUpdate() == 0) {
                throw new DAOException("Error al editar Cliente");
            }

        } catch (SQLException e) {
            GestionarRecursos.propagarError(e);
        } finally {
            GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
        }
    }

    @Override
    public void borrar(Long id) throws DAOException {
        PreparedStatement consultaPreparada = null;
        try {
            consultaPreparada = conexion.prepareStatement(borrarCliente);
            consultaPreparada.setLong(1, id);

            if (consultaPreparada.executeUpdate() == 0) {
                throw new DAOException("Error al eliminar Cliente, No puede eliminar un cliente que tiene tareas existentes");
            }

        } catch (DAOException | SQLException e) {
            GestionarRecursos.propagarError(e);
        } finally {
            GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
        }
    }

    public Cliente convierteElemento(ResultSet resultado) throws SQLException {
        Cliente cliente;
        Long id = resultado.getLong(1);
        String nombre = resultado.getString(2);
        int estado = resultado.getInt(3);
        String telefono = resultado.getString(4);
        int tipoCliente = resultado.getInt(5);
        int id_sistema = resultado.getInt(6);
        String contacto = resultado.getString(7);

        cliente = new Cliente(nombre, telefono, tipoCliente, id_sistema, contacto);
        cliente.setId(id);
        cliente.setEstado(estado);

        return cliente;
    }

    @Override
    //public Map<ResultSetMetaData, List<Cliente>> buscarTodos() throws DAOException {
    public Pair<ResultSetMetaData, List<Cliente>> buscarTodos() throws DAOException {

        ///Map<ResultSetMetaData, List<Cliente>> datosTabla = new HashMap<>();
        Pair<ResultSetMetaData, List<Cliente>> datosTabla = null;// = new Pair<>(null,null);
        List<Cliente> listaClientes = new ArrayList<>();
        ResultSetMetaData metaData;
        PreparedStatement consultaPreparada = null;
        ResultSet resultados = null;

        try {

            consultaPreparada = conexion.prepareStatement(buscarTodosLosClientes);
            resultados = consultaPreparada.executeQuery();
            metaData = resultados.getMetaData();

            while (resultados.next()) {
                listaClientes.add(convierteElemento(resultados));
            }


            datosTabla = new Pair<>(metaData, listaClientes); 
            //datosTabla.put(metaData, listaClientes);

        } catch (SQLException e) {
            GestionarRecursos.propagarError(e);
        } finally {
            GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
            GestionarRecursos.cerrarResultSet(resultados);
        }
        return datosTabla;
    }

    @Override
    public Cliente buscarUno(Long id) throws DAOException {
        Cliente cliente = null;
        PreparedStatement consultaPreparada = null;
        ResultSet resultados = null;

        try {

            consultaPreparada = conexion.prepareStatement(buscarUnCliente);
            consultaPreparada.setLong(1, id);
            resultados = consultaPreparada.executeQuery();

            if (resultados.next()) {
                cliente = convierteElemento(resultados);
            }

        } catch (SQLException e) {
            GestionarRecursos.propagarError(e);
        } finally {
            GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
            GestionarRecursos.cerrarResultSet(resultados);
        }
        return cliente;
    }

}
