package com.Modelos.Tablas;

import com.DAO.DAOClientes;
import com.DAO.DAOException;
import com.Modelos.Cliente;
import com.Modelos.Parametros;

public class ModeloTablaClientes extends ModeloTablaGeneral<Cliente, DAOClientes> {

    public ModeloTablaClientes(DAOClientes solicitaModelo) {
        super(solicitaModelo);
    }

    @Override
    public void actualizarModelo() throws DAOException {
        resultadoSQL = solicitaModelo.buscarTodos();
        super.actualizarModelo();
    }

    public Cliente getElementById(Long id) {
        return listaDatos.stream().filter(e -> e.getId().equals(id)).findAny().get();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = listaDatos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return cliente.getId();
            case 1:
                return cliente.getNombre();
            case 2:
                return (String) Parametros.devuelveValorParametro(cliente.getEstado(), Parametros.DEVUELVE_VALOR);
            case 3:
                return cliente.getTelefono();
            case 4:
                return (String) Parametros.devuelveValorParametro(cliente.gettCliente(), Parametros.DEVUELVE_VALOR);
            case 5:
                return (String) Parametros.devuelveValorParametro(cliente.getId_sistema(), Parametros.DEVUELVE_VALOR);
            case 6:
                return cliente.getContacto();
            default:
                return "";
        }
    }

    public void removeElementWithId(Long id) {
        if (id != 0) {
            listaDatos.removeIf(e -> e.getId().equals(id));
            fireTableDataChanged();
        }
    }

}
