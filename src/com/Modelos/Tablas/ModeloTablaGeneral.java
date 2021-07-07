package com.Modelos.Tablas;

import com.DAO.DAOException;
import com.Recursos.GestionarRecursos;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javafx.util.Pair;
import javax.swing.table.AbstractTableModel;

public abstract class ModeloTablaGeneral<T, D> extends AbstractTableModel {

    protected D solicitaModelo;
    protected Pair<ResultSetMetaData, List<T>> resultadoSQL = null;
    protected LinkedList<T> listaDatos = new LinkedList<>();
    protected ResultSetMetaData metadatos;

    public ModeloTablaGeneral(D solicitaModelo) {
        this.solicitaModelo = solicitaModelo;
    }

    protected void actualizarModelo() throws DAOException {
        this.listaDatos.clear();
        this.listaDatos.addAll(resultadoSQL.getValue());
        this.metadatos = resultadoSQL.getKey();
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
        this.listaDatos.addLast(element);
        this.fireTableDataChanged();
    }

    public T removeFirstElement() {
        T elementDeleted = this.listaDatos.removeFirst();
        this.fireTableDataChanged();
        return elementDeleted;
    }

    public T removeLastElement() {
        T elementDeleted = this.listaDatos.removeLast();
        this.fireTableDataChanged();
        return elementDeleted;
    }

    public T removeElementWithIndex(int index) {
        T elementDeleted = this.listaDatos.remove(index);
        this.fireTableDataChanged();
        return elementDeleted;
    }

    public T getElementByIndex(int index) {
        return this.listaDatos.get(index);
    }

}
