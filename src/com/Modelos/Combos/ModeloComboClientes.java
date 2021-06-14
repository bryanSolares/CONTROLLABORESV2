package com.Modelos.Combos;

import com.DAO.DAOClientes;
import com.DAO.DAOException;
import com.Modelos.Cliente;
import com.Modelos.Parametros;
import com.Recursos.GestionarRecursos;
import java.util.Objects;
import java.util.stream.Collectors;

public class ModeloComboClientes extends ModeloComboGeneral<Cliente> {

    private DAOClientes daoClientes;

    public ModeloComboClientes(DAOClientes daoClientes) {
        this.daoClientes = daoClientes;
    }

    @Override
    public void actualizar() {
        try {
            //datos = daoClientes.buscarTodos().values().iterator().next().stream().filter(e -> e.getEstado() != Parametros.INACTIVO.getId()).collect(Collectors.toList());
            datos = daoClientes.buscarTodos().getValue().stream().filter(e -> e.getEstado() != Parametros.INACTIVO.getId()).collect(Collectors.toList());
            super.actualizar();
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }
    }

    @Override
    public Cliente obtenerElementoPorId(Long id) {
        return datos.stream().filter(e -> Objects.equals(e.getId(), id)).findFirst().get();
    }

}
