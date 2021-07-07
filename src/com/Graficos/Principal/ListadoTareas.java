package com.Graficos.Principal;

import com.DAO.DAOException;
import com.DAO.DAOTareas;
import com.DAO.Managers.DAOManager;
import com.Graficos.Tareas.TareaResumen;
import com.Modelos.Tarea;
import com.Recursos.GestionarRecursos;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

public class ListadoTareas extends javax.swing.JInternalFrame {
    
    private final DAOManager daoManager;
    private final DAOTareas daoTareas;
    private List<Tarea> listaTareas;
    private final List<TareaResumen> listaItems;
    
    public ListadoTareas(DAOManager manager) {
        initComponents();
        this.daoTareas = manager.getDAOTareas();
        this.listaTareas = new ArrayList<>();
        this.listaItems = new ArrayList<>();
        this.daoManager = manager;
        this.iniciar();
    }
    
    private void iniciar() {
        try {
            this.listaTareas = this.daoTareas.buscarTodos().getValue();
            this.renderizarItems();
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }
    }
    
    public void renderizarItems() {
        this.listaTareas.forEach(tarea -> {
            TareaResumen item = new TareaResumen(tarea, this.daoManager);
            listaItems.add(item);
            this.JP_area_pendientes.add(new TareaResumen(tarea, this.daoManager));
        });
        this.JP_area_pendientes.setLayout(new GridLayout(this.listaItems.size(), 1));
        this.updateUI();
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JI_paramover = new javax.swing.JInternalFrame();
        JI_area_en_proceso = new javax.swing.JInternalFrame();
        JI_area_pendientes = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        JP_area_pendientes = new javax.swing.JPanel();
        JI_area_finalizado = new javax.swing.JInternalFrame();

        JI_paramover.setVisible(true);

        javax.swing.GroupLayout JI_paramoverLayout = new javax.swing.GroupLayout(JI_paramover.getContentPane());
        JI_paramover.getContentPane().setLayout(JI_paramoverLayout);
        JI_paramoverLayout.setHorizontalGroup(
            JI_paramoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 206, Short.MAX_VALUE)
        );
        JI_paramoverLayout.setVerticalGroup(
            JI_paramoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 201, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("ESTADOS DE TAREAS");

        JI_area_en_proceso.setResizable(true);
        JI_area_en_proceso.setTitle("EN PROCESO");
        JI_area_en_proceso.setVisible(true);
        JI_area_en_proceso.getContentPane().setLayout(new java.awt.GridLayout(20, 0));

        JI_area_pendientes.setResizable(true);
        JI_area_pendientes.setTitle("PENDIENTES");
        JI_area_pendientes.setToolTipText("");
        JI_area_pendientes.setVisible(true);

        JP_area_pendientes.setLayout(new java.awt.GridLayout());
        jScrollPane1.setViewportView(JP_area_pendientes);

        JI_area_pendientes.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        JI_area_finalizado.setResizable(true);
        JI_area_finalizado.setTitle("FINALIZADO");
        JI_area_finalizado.setVisible(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(JI_area_pendientes, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(JI_area_en_proceso, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(JI_area_finalizado, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JI_area_pendientes, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
            .addComponent(JI_area_en_proceso, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
            .addComponent(JI_area_finalizado, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame JI_area_en_proceso;
    private javax.swing.JInternalFrame JI_area_finalizado;
    private javax.swing.JInternalFrame JI_area_pendientes;
    private javax.swing.JInternalFrame JI_paramover;
    private javax.swing.JPanel JP_area_pendientes;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
