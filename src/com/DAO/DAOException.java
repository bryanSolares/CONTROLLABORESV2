package com.DAO;

import javax.swing.JOptionPane;

public class DAOException extends Exception {

    public DAOException(String message, Throwable cause) {
        super(message, cause);
        JOptionPane.showMessageDialog(null, message,"ERROR",JOptionPane.ERROR_MESSAGE);
    }

    public DAOException(Throwable cause) {
        super(cause);
        JOptionPane.showMessageDialog(null, cause.toString(),"ERROR",JOptionPane.ERROR_MESSAGE);
    }

    public DAOException(String message) {
        super(message);
        JOptionPane.showMessageDialog(null, message,"ERROR",JOptionPane.ERROR_MESSAGE);
    }

}
