package com.Controlador;

import com.DAO.DAOException;
import com.DAO.Managers.DAOMYSQLManager;
import com.DAO.Managers.DAOManager;
import com.DAO.Managers.DAOSQLSERVERManager;
import com.Recursos.GestionarRecursos;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GestorConexion {

    private Connection conexion;
    private String user, password, server, database;
    public final static byte SQLSERVER = 1;
    public final static byte MYSQL = 2;
    private Properties clientInfoConnection;
    private DatabaseMetaData dataBaseMetaData;

    public GestorConexion(String user, String password, String server, String database) {
        this.user = user;
        this.password = password;
        this.server = server;
        this.database = database;
    }

    public DAOManager iniciarConexion(byte tipoConexion) throws DAOException {
        switch (tipoConexion) {
            case SQLSERVER: {
                try {
                    iniciarConexionSQLSERVER();
                } catch (SQLException ex) {
                    GestionarRecursos.propagarError(ex);
                }
            }
            return new DAOSQLSERVERManager(conexion);

            case MYSQL: {
                try {
                    iniciarConexionMYSQL();
                } catch (SQLException ex) {
                    GestionarRecursos.propagarError(ex);
                }
            }
            return new DAOMYSQLManager(conexion);

            default:
                return null;
        }
    }

    private void iniciarConexionSQLSERVER() throws SQLException {
        conexion = DriverManager.getConnection("jdbc:sqlserver://"
                .concat(server)
                .concat(";databaseNAME=")
                .concat(database)
                .concat(";"), user, password);

        clientInfoConnection = conexion.getClientInfo();
        dataBaseMetaData = conexion.getMetaData();
    }

    private void iniciarConexionMYSQL() throws SQLException {
        conexion = DriverManager.getConnection(server, user, password);
        clientInfoConnection = conexion.getClientInfo();
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHost(String host) {
        this.server = host;
    }

    public void setDabase(String database) {
        this.database = database;
    }

    public Properties getClientInfoConnection() {
        return clientInfoConnection;
    }

    public DatabaseMetaData getDataBaseMetaData() {
        return dataBaseMetaData;
    }

    public Connection getConexion() {
        return conexion;
    }

}
