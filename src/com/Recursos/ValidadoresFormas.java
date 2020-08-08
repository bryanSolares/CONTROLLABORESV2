package com.Recursos;

import com.toedter.calendar.JDateChooser;
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
    private final List<JDateChooser> listaCamposFecha;

    public final static int CAMPOS_VALIDADO = 1;
    public final static int COMBOS_VALIDADOS = 2;
    public final static int CHECKS_VALIDADOS = 3;
    public final static int TABLAS_VALIDADAS = 4;
    public final static int FECHAS_VALIDADAS = 5;

    public ValidadoresFormas(Component componentePadre) {
        this.componentePadre = componentePadre;
        listaCamposTexto = new ArrayList<>();
        listaCombos = new ArrayList<>();
        listaChecks = new ArrayList<>();
        listaTablas = new ArrayList<>();
        listaCamposFecha = new ArrayList<>();
    }

    private boolean comprobarCampos() {
        return listaCamposTexto.stream().anyMatch(c -> c.getText().equals(""));
    }

    private boolean comprobarCombos() {
        return listaCombos.stream().anyMatch(elemento -> elemento.getSelectedItem() == null);
    }

    private boolean comprobarChecks() {
        return listaChecks.stream().anyMatch(elemento -> elemento.isSelected());
    }

    private boolean comprobarTablas() {
        return listaTablas.stream().anyMatch(elemento -> elemento.getRowCount() == 0);
    }

    private boolean comprobarFechas() {
        return listaCamposFecha.stream().anyMatch(elemento -> elemento.getDate() == null);
    }

    private boolean comprobarFechaInicialMenorFinalMayor() {
        return listaCamposFecha.get(0).getDate().after(listaCamposFecha.get(1).getDate());
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
                break;
            case TABLAS_VALIDADAS:
                JOptionPane.showMessageDialog(componentePadre, "Todas las tablas deben tener por lo menos un elemento", "Error", JOptionPane.ERROR_MESSAGE);
                //listaCombos.stream().filter(elemento -> elemento.getSelectedItem() == null).forEach(elemento -> elemento.requestFocus());
                break;
            case FECHAS_VALIDADAS:
                JOptionPane.showMessageDialog(componentePadre, "Seleccionar Fechas Válidas", "Error", JOptionPane.ERROR_MESSAGE);
                listaCamposFecha.stream().filter(elemento -> elemento.getDate() == null).forEach(elemento -> elemento.requestFocus());
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

    public void agregarFechaParaValidar(JDateChooser fechero) {
        listaCamposFecha.add(fechero);
    }

    public boolean compruebaCamposValidados() {
        if (!listaCamposTexto.isEmpty()) {
            if (comprobarCampos()) {
                devuelveMensaje(CAMPOS_VALIDADO);
                return false;
            }
        }
        if (!listaCombos.isEmpty()) {
            if (comprobarCombos()) {
                devuelveMensaje(COMBOS_VALIDADOS);
                return false;
            }
        }
        if (!listaChecks.isEmpty()) {
            if (comprobarChecks()) {
                devuelveMensaje(CHECKS_VALIDADOS);
                return false;
            }
        }
        if (!listaCamposFecha.isEmpty()) {
            if (comprobarFechas()) {
                devuelveMensaje(FECHAS_VALIDADAS);
                return false;
            } else if (comprobarFechaInicialMenorFinalMayor()) {
                devuelveMensaje(FECHAS_VALIDADAS);
                return false;
            }
        }
        return true;
    }

}
