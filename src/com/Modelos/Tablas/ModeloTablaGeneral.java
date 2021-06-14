package com.Modelos.Tablas;

import com.DAO.DAOException;
import com.Recursos.GestionarRecursos;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import javax.swing.table.AbstractTableModel;

public abstract class ModeloTablaGeneral<T, D> extends AbstractTableModel {

    protected D solicitaModelo;
    protected Pair<ResultSetMetaData, List<T>> resultadoSQL = null;
    protected List<T> listaDatos = new ArrayList<>();
    protected ResultSetMetaData metadatos;

    public ModeloTablaGeneral(D solicitaModelo) {
        this.solicitaModelo = solicitaModelo;
    }

    protected void actualizarModelo() throws DAOException {
        this.listaDatos = resultadoSQL.getValue();
        //this.listaDatos = resultadoSQL.values().iterator().next();
        this.metadatos = resultadoSQL.getKey();
        //this.metadatos = resultadoSQL.keySet().iterator().next();
        this.fireTableDataChanged();
    }

    @Override
    public int getColumnCount() {
        int totalColumnas = 0;
        try {
            totalColumnas = metadatos.getColumnCount();
        } catch (SQLException ex) {
            GestionarRecursos.propagarError(ex);
        }
        return totalColumnas;
    }

    @Override
    public int getRowCount() {
        return listaDatos.size();
    }

    @Override
    public String getColumnName(int column) {
        try {
            return metadatos.getColumnLabel(column + 1);
        } catch (SQLException ex) {
            GestionarRecursos.propagarError(ex);
        }
        return "";
    }

    public List<T> getDataElements() {
        return listaDatos;
    }

    public ResultSetMetaData getDataHeader() {
        return metadatos;
    }

    public void addElementToData(T element) {
        this.listaDatos.add(element);
        this.fireTableDataChanged();
    }

    public void removeElementWithIndex(int index) {
        if (index <= listaDatos.size() && index > -1) {
            this.listaDatos.remove(index);
            this.fireTableDataChanged();
        }
    }

    public abstract void removeElementWithId(Long id);

    public T getElementByIndex(int index) {
        return this.listaDatos.get(index);
    }

    public abstract T getElementById(Long id);
}
