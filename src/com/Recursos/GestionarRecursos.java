package com.Recursos;

import com.DAO.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class GestionarRecursos {

    public static void cerrarResultSet(ResultSet resultadoConsulta) throws DAOException {
        if (resultadoConsulta != null) {
            try {
                resultadoConsulta.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
                throw new DAOException("Error al cerrar ResultSet");
            }
        }
    }

    public static void cerrarPreparedStatement(PreparedStatement consulta) throws DAOException {
        if (consulta != null) {
            try {
                consulta.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
                throw new DAOException("Error al cerrar ResultSet");
            }
        }
    }

    public static void cerrarConexion(Connection conexion) throws DAOException {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
                throw new DAOException("Error al cerrar Connection");
            }
        }
    }

    public static void propagarError(Exception ex) {
        JOptionPane.showMessageDialog(null, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public static void propagarError(Exception ex, String mensaje) {
        JOptionPane.showMessageDialog(null, ex + " " + mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public static void propagarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public static void generarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, tipo);
    }

}
