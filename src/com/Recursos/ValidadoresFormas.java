package com.Recursos;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;

public class ValidadoresFormas {

    private final List<JTextComponent> listaCamposTexto;
    private final Component componentePadre;
    private final List<JComboBox> listaCombos;
    private final List<JCheckBox> listaChecks;
    private final List<TableModel> listaTablas;

    public final static int CAMPOS_VALIDADO = 1;
    public final static int COMBOS_VALIDADOS = 2;
    public final static int CHECKS_VALIDADOS = 3;
    public final static int TABLAS_VALIDADAS = 4;

    public ValidadoresFormas(Component componentePadre) {
        this.componentePadre = componentePadre;
        listaCamposTexto = new ArrayList<>();
        listaCombos = new ArrayList<>();
        listaChecks = new ArrayList<>();
        listaTablas = new ArrayList<>();
    }

    public boolean comprobarCampos() {
        return !listaCamposTexto.stream().anyMatch(c -> c.getText().equals(""));
    }

    public boolean comprobarCombos() {
        return !listaCombos.stream().anyMatch(elemento -> elemento.getSelectedItem() == null);
    }

    public boolean comprobarChecks() {
        return !listaChecks.stream().anyMatch(elemento -> elemento.isSelected());
    }

    public boolean comprobarTablas() {
        listaTablas.forEach(System.out::println);
        return !listaTablas.stream().anyMatch(elemento -> elemento.getRowCount() == 0);
    }

    public void devuelveMensaje(int tipo) {
        switch (tipo) {
            case CAMPOS_VALIDADO:
                JOptionPane.showMessageDialog(componentePadre, "Todos los campos deben ser completados", "Error", JOptionPane.ERROR_MESSAGE);
                listaCamposTexto.stream().filter(c -> c.getText().equals("")).limit(1).forEach(c -> c.requestFocus());
                break;
            case COMBOS_VALIDADOS:
                JOptionPane.showMessageDialog(componentePadre, "Todos los combos deben ser completados", "Error", JOptionPane.ERROR_MESSAGE);
                listaCombos.stream().filter(elemento -> elemento.getSelectedItem() == null).forEach(elemento -> elemento.requestFocus());
                break;
            case CHECKS_VALIDADOS:
                JOptionPane.showMessageDialog(componentePadre, "Debe seleccionar por lo menos una opción del listado", "Error", JOptionPane.ERROR_MESSAGE);
                listaCombos.stream().filter(elemento -> elemento.getSelectedItem() == null).forEach(elemento -> elemento.requestFocus());
            case TABLAS_VALIDADAS:
                JOptionPane.showMessageDialog(componentePadre, "Todas las tablas deben tener por lo menos un elemento", "Error", JOptionPane.ERROR_MESSAGE);
                //listaCombos.stream().filter(elemento -> elemento.getSelectedItem() == null).forEach(elemento -> elemento.requestFocus());
                break;
        }

    }

    public void agregarCampoParaValidar(JTextComponent campo) {
        listaCamposTexto.add(campo);
    }

    public void agregarComboParaValidar(JComboBox combo) {
        listaCombos.add(combo);
    }

    public void agregarChecksParaValidar(JCheckBox check) {
        listaChecks.add(check);
    }

    public void agregarTablasParaValidar(TableModel tabla) {
        listaTablas.add(tabla);
    }

    public boolean compruebaCamposValidados() {
        if (!listaCamposTexto.isEmpty()) {
            if (!comprobarCampos()) {
                devuelveMensaje(CAMPOS_VALIDADO);
                return false;
            }
        } else if (!listaCombos.isEmpty()) {
            if (!comprobarCombos()) {
                devuelveMensaje(COMBOS_VALIDADOS);
                return false;
            }
        } else if (!listaChecks.isEmpty()) {
            if (!comprobarChecks()) {
                devuelveMensaje(CHECKS_VALIDADOS);
                return false;
            }
        }
        return true;
    }

}
