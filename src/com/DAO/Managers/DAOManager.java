package com.DAO.Managers;

import com.DAO.DAOClientes;
import com.DAO.DAOTareas;

public interface DAOManager {

    DAOClientes getDAOClientes();
    DAOTareas getDAOTareas();

}
