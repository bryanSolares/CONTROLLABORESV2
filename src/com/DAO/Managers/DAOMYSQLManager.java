package com.DAO.Managers;

import com.DAO.DAOClientes;
import com.DAO.DAOTareas;
import com.MYSQLCRUD.CRUDMYSQLClientes;
import com.SQLSERVERCRUD.CRUDSQLTareas;
import java.sql.Connection;

public class DAOMYSQLManager implements DAOManager {

    private Connection conexion = null;
    private DAOClientes DAOClientes = null;
    private DAOTareas DAOTarea = null;

    public DAOMYSQLManager(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public DAOClientes getDAOClientes() {
        if (DAOClientes == null) {
            DAOClientes = new CRUDMYSQLClientes(conexion);
        }
        return DAOClientes;
    }

    @Override
    public DAOTareas getDAOTareas() {
        if (DAOTarea == null) {
            DAOTarea = new CRUDSQLTareas(conexion);
        }
        return DAOTarea;
    }

}
