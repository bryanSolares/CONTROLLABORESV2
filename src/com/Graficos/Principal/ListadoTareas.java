
package com.Graficos.Principal;

import java.awt.MouseInfo;


public class ListadoTareas extends javax.swing.JInternalFrame {


    public ListadoTareas() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JI_paramover = new javax.swing.JInternalFrame();
        JI_area_en_proceso = new javax.swing.JInternalFrame();
        JI_area_pendientes = new javax.swing.JInternalFrame();
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
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        JI_area_en_proceso.setResizable(true);
        JI_area_en_proceso.setTitle("EN PROCESO");
        JI_area_en_proceso.setVisible(true);

        javax.swing.GroupLayout JI_area_en_procesoLayout = new javax.swing.GroupLayout(JI_area_en_proceso.getContentPane());
        JI_area_en_proceso.getContentPane().setLayout(JI_area_en_procesoLayout);
        JI_area_en_procesoLayout.setHorizontalGroup(
            JI_area_en_procesoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );
        JI_area_en_procesoLayout.setVerticalGroup(
            JI_area_en_procesoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        JI_area_pendientes.setResizable(true);
        JI_area_pendientes.setTitle("PENDIENTES");
        JI_area_pendientes.setToolTipText("");
        JI_area_pendientes.setVisible(true);

        javax.swing.GroupLayout JI_area_pendientesLayout = new javax.swing.GroupLayout(JI_area_pendientes.getContentPane());
        JI_area_pendientes.getContentPane().setLayout(JI_area_pendientesLayout);
        JI_area_pendientesLayout.setHorizontalGroup(
            JI_area_pendientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );
        JI_area_pendientesLayout.setVerticalGroup(
            JI_area_pendientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        JI_area_finalizado.setResizable(true);
        JI_area_finalizado.setTitle("FINALIZADO");
        JI_area_finalizado.setVisible(true);

        javax.swing.GroupLayout JI_area_finalizadoLayout = new javax.swing.GroupLayout(JI_area_finalizado.getContentPane());
        JI_area_finalizado.getContentPane().setLayout(JI_area_finalizadoLayout);
        JI_area_finalizadoLayout.setHorizontalGroup(
            JI_area_finalizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );
        JI_area_finalizadoLayout.setVerticalGroup(
            JI_area_finalizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(JI_area_pendientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JI_area_en_proceso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JI_area_finalizado)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JI_area_pendientes)
            .addComponent(JI_area_en_proceso)
            .addComponent(JI_area_finalizado)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        System.out.println(MouseInfo.getPointerInfo().getLocation());
    }//GEN-LAST:event_formMouseMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame JI_area_en_proceso;
    private javax.swing.JInternalFrame JI_area_finalizado;
    private javax.swing.JInternalFrame JI_area_pendientes;
    private javax.swing.JInternalFrame JI_paramover;
    // End of variables declaration//GEN-END:variables
}
